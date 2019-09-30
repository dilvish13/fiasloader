package ru.progmatik.main.services;

import com.github.junrar.exception.RarException;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.fias.Object;
import ru.fias.ObjectGuid;
import ru.fias.Socr;
import ru.progmatik.main.DAO.AddrObjDAOBatchDelete;
import ru.progmatik.main.DAO.AddrObjDAOBatchInsert;
import ru.progmatik.main.DAO.CreateAtgCitiesTable;
import ru.progmatik.main.DAO.DBService;
import ru.progmatik.main.DAO.SocrDAOBatchInsert;
import ru.progmatik.main.other.JunrarWithProgress;
import ru.progmatik.main.other.XMLFileReader;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * сервис предназначен для обработки скачанных файлов
 */
@Service
public class ProcessFileController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${work.batch_size.xml:1000}")
    private int BATCH_SIZE;

    @Value("${unpack.need:true}")
    Boolean needUnpack;
    @Value("${unpack.dir:unpack}")
    private String unpackDir;

    @Value("${addrobj.frombatchn:0}")
    private int addrObjFromBatchN;
    @Value("${addrobj.tobatchn:-1}")
    private int addrObjToBatchN;
    private File UNPACKFOLDER;

    boolean processFiasRarFile(final File fiasRarFile, Stream<String> fileMasksToExtract) {

        logger.info(String.format(
                "Start processing controller (unpack.need=%s, unpack.dir=%s, work.fileMasks=%s, addrobj.frombatchn=%s, addrobj.tobatchn=%s)",
                needUnpack, unpackDir, fileMasksToExtract.collect(Collectors.toList()), addrObjFromBatchN, addrObjToBatchN));
        boolean unpackSuccess = true;
        try {
            UNPACKFOLDER = new File(unpackDir);
            if (fiasRarFile != null && needUnpack) {
                unpackSuccess = extractRarFile(fiasRarFile, fileMasksToExtract);
            }
        } catch (Exception e) {
            logger.error("processFiasRarFile error", e);
            e.printStackTrace();
            unpackSuccess = false;
        }
        return unpackSuccess;
    }

    boolean processFiasXmlFile(Supplier<Stream<String>> fileMasksToExtract) {
        boolean unpackSuccess = true;
        UNPACKFOLDER = UNPACKFOLDER != null ? UNPACKFOLDER : new File(unpackDir);
        if (UNPACKFOLDER.listFiles() == null) {
            logger.error("Empty $unpack.dir with $unpack.need=false! Uncompatible parameters?");
        }
        File[] unpackedFiles = UNPACKFOLDER.listFiles();
        int xmlFilesCount = unpackedFiles != null ? unpackedFiles.length : 0;
        logger.info(String.format("Unpacked files count: %d", xmlFilesCount));
        if (xmlFilesCount > 0) {
            // Если без распаковки - необходимо фильтровать набор файлов
            List<File> files = Stream.of(unpackedFiles)
                    .filter(fileName -> needUnpack || fileMasksToExtract.get().anyMatch(mask -> fileName.getName().contains(mask)))
                    .collect(Collectors.toList());
            logger.info(String.format("Unpacked files count to be processed: %d", files.size()));
            try (Connection connection = dbService.getConnection()) {
                for (File sourceFile : files) {
                    if (!sourceFile.isDirectory()
                            && FilenameUtils.getExtension(sourceFile.getName()).equalsIgnoreCase("xml")) {
                        String filename = FilenameUtils.getName(sourceFile.getName());
                        if (filename.contains("AS_SOCRBASE")) {
                            processSocr(sourceFile, connection);
                        } else if (filename.contains("AS_ADDROBJ")) {
                            processAddrObj(sourceFile, connection);
                        } else if (filename.contains("AS_DEL_ADDROBJ")) {
                            processAddrObjDel(sourceFile, connection);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("processFiasXmlFile error", e);
                e.printStackTrace();
                unpackSuccess = false;
            }
        }
        return unpackSuccess;
    }

    @Autowired
    private AddrObjDAOBatchInsert addrObjDAOBatchInsert;

    @Autowired
    private DBService dbService;

    private void processAddrObj(File sourceFile, Connection connection){
        long totalCnt = 0;
        try(XMLFileReader xmlFileReader = new XMLFileReader(sourceFile, ru.fias.Object.class)) {
            long startNanotime = System.nanoTime();
            // бежим по файлу и создаем объекты
            long indexFrom = addrObjFromBatchN * BATCH_SIZE;
            long indexTo = addrObjToBatchN * BATCH_SIZE;
            while (xmlFileReader.hasNext()) {
                List<Object> objectList = xmlFileReader.readAddrObjFromStream(BATCH_SIZE);
                totalCnt = totalCnt + objectList.size();
                if (totalCnt >= indexFrom && (totalCnt < indexTo || indexTo < 0)) {
                    // insert into base here
                    addrObjDAOBatchInsert.insertAddrObjArray(objectList, connection);
                    long endNanotime = System.nanoTime();
                    long duration = ((endNanotime - startNanotime) / 1000000000);
                    long diff = 0;
                    if (duration > 0) {
                        startNanotime = System.nanoTime();
                        diff = objectList.size() / duration;
                    }
                    logger.info(String.format("Address objects inserted, last index: %d; Avg. speed: %d records/sec", totalCnt, diff));
                } else {
                    logger.info(String.format("Address objects ignored, last index: %d;", totalCnt));
                }
            }
            logger.info("Address objects insert finished");
        } catch (Exception e) {
            logger.error("processAddrObj error", e);
            e.printStackTrace();
        }
    }

    @Autowired
    private AddrObjDAOBatchDelete addrObjDAOBatchDelete;

    private void processAddrObjDel(File sourceFile, Connection connection){
        long totalCnt = 0;
        try(XMLFileReader xmlFileReader = new XMLFileReader(sourceFile, ru.fias.ObjectGuid.class)) {
            long startNanotime = System.nanoTime();
            // бежим по файлу и создаем объекты
            long indexFrom = addrObjFromBatchN * BATCH_SIZE;
            long indexTo = addrObjToBatchN * BATCH_SIZE;
            while (xmlFileReader.hasNext()) {
                List<ObjectGuid> objectGuidList = xmlFileReader.readObjectGuidFromStream(BATCH_SIZE, "Object");
                totalCnt = totalCnt + objectGuidList.size();
                if (totalCnt >= indexFrom && (totalCnt < indexTo || indexTo < 0)) {
                    // insert into base here
                    addrObjDAOBatchDelete.deleteAddrObjArray(objectGuidList, connection);
                    long endNanotime = System.nanoTime();
                    long duration = ((endNanotime - startNanotime) / 1000000000);
                    long diff = 0;
                    if (duration > 0) {
                        startNanotime = System.nanoTime();
                        diff = totalCnt / duration;
                    }
                    logger.info(String.format("Address objects deleted, last index: %d; Avg. speed: %d records/sec", totalCnt, diff));
                } else {
                    logger.info(String.format("Address objects ignored (not deleted), last index: %d;", totalCnt));
                }
            }
            logger.info("Address objects deleting finished");
        } catch (Exception e) {
            logger.error("processAddrObjDel error", e);
            e.printStackTrace();
        }
    }

    @Autowired
    private SocrDAOBatchInsert socrDAOBatchInsert;

    private void processSocr(File sourceFile, Connection connection) {
        long totalCnt = 0;
        try(XMLFileReader xmlFileReader = new XMLFileReader(sourceFile, Socr.class)){
            long startTime = System.nanoTime();
            // бежим по файлу и создаем объекты
            while (xmlFileReader.hasNext()) {
                List<Socr> socrList = xmlFileReader.readSocrFromStream(BATCH_SIZE);
                totalCnt = totalCnt + socrList.size();
                // insert into base here
                socrDAOBatchInsert.insertSocrArray(socrList, connection);
                long duration = System.nanoTime() - startTime;
                long diff = 0;
                if (duration > 0) {
                    startTime = System.nanoTime();
                    diff = totalCnt * 1000000000 / duration;
                }
                logger.info(String.format("Socr objects inserted: %d; Avg. speed: %d records/sec", totalCnt, diff));
            }
            logger.info("Socr objects insert finished");
        } catch (Exception e) {
            logger.error("processSocr error", e);
            e.printStackTrace();
        }
    }

    @Autowired
    private CreateAtgCitiesTable createAtgCitiesTable;

    boolean createAtgCities() {
        boolean modifySuccess = true;
        try (Connection connection = dbService.getConnection()) {
            createAtgCitiesTable.createAtgCities(connection);
            logger.info("Creating atg cities table finished");
        } catch (Exception e) {
            logger.error("Creating atg cities table stopped due to error", e);
            e.printStackTrace();
            modifySuccess = false;
        }
        return modifySuccess;
    }

    private boolean extractRarFile(File fiasRarFile, Stream<String> fileMasksToExtract) throws IOException, RarException {
        if(fiasRarFile == null) return false;
        if (!Files.exists(fiasRarFile.toPath())) {
            throw new IOException(String.format("File not found: %s", fiasRarFile.toPath()));
        }
        prepareUnpackFolder();
        logger.info("extract file " + fiasRarFile.toPath());
        JunrarWithProgress.extract(fiasRarFile, UNPACKFOLDER, fileMasksToExtract, (curr, total) -> {
            logger.info("Extracting XML from RAR: %s %%", new BigDecimal(100.0 * curr / total).setScale(2, ROUND_HALF_UP));
        });
        logger.info(fiasRarFile.toPath() + " extracted");
        return true;
    }

    private void prepareUnpackFolder() {
        if (!UNPACKFOLDER.exists()) {
            UNPACKFOLDER.mkdir();
        }

        clearUnpackFolder();
    }

    void clearUnpackFolder(){
        for(File file : Objects.requireNonNull(UNPACKFOLDER.listFiles())){
            file.delete();
        }
        logger.info("Unpack folder cleared");
    }
}
