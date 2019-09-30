package ru.progmatik.main.other;

import com.github.junrar.Archive;
import com.github.junrar.ExtractDestination;
import com.github.junrar.Junrar;
import com.github.junrar.LocalFolderExtractor;
import com.github.junrar.UnrarCallback;
import com.github.junrar.Volume;
import com.github.junrar.exception.RarException;
import com.github.junrar.impl.FileVolumeManager;
import com.github.junrar.rarfile.FileHeader;
import com.github.junrar.vfs2.provider.rar.FileSystem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * Junrar с прогрес-баром и фильтром по списку файлов.
 *
 * @author M. Golovatiy {@literal <mgolovatiy@at-consulting.ru>}
 */

public class JunrarWithProgress extends Junrar {

    private static final Log logger = LogFactory.getLog(JunrarWithProgress.class);
    private static FileSystem fileSystem = new FileSystem();

    private static void validateRarPath(final File rar) {
        if (rar == null) {
            throw new RuntimeException("archive and destination must me set");
        }
        if (!rar.exists()) {
            throw new IllegalArgumentException("the archive does not exit: " + rar);
        }
        if (!rar.isFile()) {
            throw new IllegalArgumentException("First argument should be a file but was " + rar.getAbsolutePath());
        }
    }

    private static void validateDestinationPath(final File destinationFolder) {
        if (destinationFolder == null) {
            throw new RuntimeException("archive and destination must me set");
        }
        if (!destinationFolder.exists() || !destinationFolder.isDirectory()) {
            throw new IllegalArgumentException("the destination must exist and point to a directory: " + destinationFolder);
        }
    }

    private static Archive createArchiveOrThrowException(final File file, final BiConsumer<Long, Long> onProgress)
            throws RarException, IOException {
        try {
            return new Archive(new FileVolumeManager(file), new UnrarCallback() {
                @Override
                public boolean isNextVolumeReady(Volume nextVolume) {
                    return false;
                }

                @Override
                public void volumeProgressChanged(long current, long total) {
                    onProgress.accept(current, total);
                }
            });
        } catch (final RarException | IOException e) {
            logger.error(e);
            throw e;
        }
    }

    private static File tryToExtract(
            final ExtractDestination destination,
            final Archive arch,
            final FileHeader fileHeader
    ) throws IOException, RarException {
        final String fileNameString = fileHeader.getFileNameString();
        if (fileHeader.isEncrypted()) {
            logger.warn("file is encrypted cannot extract: " + fileNameString);
            return null;
        }
        logger.info("extracting: " + fileNameString);
        if (fileHeader.isDirectory()) {
            return destination.createDirectory(fileHeader);
        } else {
            return destination.extract(arch, fileHeader);
        }
    }

    private static List<File> extractArchiveTo(final Archive arch, final Stream<String> fileMasksToExtract,
                                               final ExtractDestination destination) throws IOException, RarException {
        if (arch.isEncrypted()) {
            logger.warn("archive is encrypted cannot extract");
            arch.close();
            return new ArrayList<File>();
        }
        final List<File> extractedFiles = new ArrayList<File>();
        try {
            for (final FileHeader fh : arch) {
                try {
                    File file = null;
                    String fileName = fh.getFileNameString();
                    if (fileMasksToExtract == null || fileMasksToExtract.anyMatch(fileName::contains)) {
                        file = tryToExtract(destination, arch, fh);
                    }
                    if (file != null) {
                        extractedFiles.add(file);
                    }
                } catch (final IOException e) {
                    logger.error("error extracting the file", e);
                    throw e;
                } catch (final RarException e) {
                    logger.error("error extraction the file", e);
                    throw e;
                }
            }
        } finally {
            arch.close();
        }
        return extractedFiles;
    }

    public static List<File> extract(final File rar, final File destinationFolder, final Stream<String> fileMasksToExtract,
                                     final BiConsumer<Long, Long> onProgress) throws RarException, IOException {
        validateRarPath(rar);
        validateDestinationPath(destinationFolder);

        final Archive archive = createArchiveOrThrowException(rar, onProgress);
        LocalFolderExtractor lfe = new LocalFolderExtractor(destinationFolder, fileSystem);
        return extractArchiveTo(archive, fileMasksToExtract, lfe);
    }
}