package ru.progmatik.main.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.progmatik.main.other.UtilClass;

import java.io.File;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * сервис работает по расписанию. Обнаруживает файлы в рабочей папке и запускает их в обработку
 */
@Service
public class ProcessFilesScheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${work.archDir:archive}")
    private String archDir;
    @Value("${work.dir:work}")
    private String workDir;
    @Value("${work.fileMasks:AS_ADDROBJ,AS_SOCRBASE,AS_DEL_ADDROBJ}")
    private String workFileMasks;

    @Autowired
    private ProcessFileController processFileController;

    @Scheduled(fixedRateString = "${work.period:86400000}") // default every day
    public void proceedFiasFiles() {
        // получаем список файлов в рабочей папке
        logger.info(String.format("Start processing (work.archDir=%s, work.dir=%s, work.fileMasks=%s).",
                archDir, workDir, workFileMasks));
        Map<Integer,File> workFilesMap = UtilClass.getDirFiles(workDir, "rar");
        Collection<File> files =  workFilesMap.values();
        String[] fileMasks = workFileMasks.split(",");
        // обработка с переносом в архив
        boolean unpackSuccess = !processFileController.needUnpack || !files.isEmpty();
        // Если требуется распаковка, а архива нет - распаковка неудачна
        if (unpackSuccess) {
            logger.info(String.format("RAR-files for proceed to database: %d", files.size()));
            for (File file : files) {
                unpackSuccess = processFileController.processFiasRarFile(file, Arrays.stream(fileMasks));
                if (!unpackSuccess) {
                    break;
                }
            }
        }
        // Распаковка прошла удачно или не требуется
        if (unpackSuccess) {
            unpackSuccess = processFileController.processFiasXmlFile(() -> Arrays.stream(fileMasks));
        }
        // Создание таблицы с данными для выгрузки для репозитория ATG.
        if (unpackSuccess) {
            unpackSuccess = processFileController.createAtgCities();
        }
        if (unpackSuccess)
            if (processFileController.needUnpack) {
                processFileController.clearUnpackFolder();
                for (File file : files) {
                    if (file != null) {
                        file.renameTo(new File(archDir + File.separatorChar + file.getName()));
                    }
                }
            } else {
                logger.info("Raw XML files processed, application will be listening for export requests");
                // System.exit(0);
            }

    }
}
