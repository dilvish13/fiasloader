package ru.progmatik.main.services;

import fias.wsdl.DownloadFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.progmatik.main.webclient.FiasClient;
import ru.progmatik.main.other.UtilClass;

import javax.xml.soap.SOAPException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * класс работает по расписанию, скачивает список файлов ФИАС, сравнивает с имеющимися в архиве,
 * решает какие надо скачать и скачивает их
 */
@Service
public class DownloadFilesScheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FiasClient fiasClient;

    @Value("${work.archDir:archive}")
    private String archDir;

    @Value("${download.dir}")
    private String downloadDir;
    @Value("${work.dir:work}")
    private String workDir;

    @Value("${download.tmp:tmp}")
    private String tmpDir;

    @Value("${download.need:true}")
    private Boolean needDownload;

    private List<DownloadFileInfo> fiasFilesList = new ArrayList<>();
    private Map<Integer,File> archFilesMap = new HashMap<>();
    private Map<Integer,File> workFilesMap = new HashMap<>();

    @Scheduled(fixedRateString = "${download.period:3600000}") // every hour
    public void checkAndGetFiasFiles() throws SOAPException, IOException {
        if (StringUtils.isEmpty(downloadDir)) {
            downloadDir = workDir;
        }
        if (needDownload) {

            logger.info(String.format("Start downloading (tmpDir=%s, archDir=%s, downloadDir=%s, needDownload=%s)",
                    tmpDir, archDir, downloadDir, needDownload));
            if (fiasFilesList != null) {
                fiasFilesList.clear();
            }

            fiasFilesList = fiasClient.getAllDownloadFileList();

            if (fiasFilesList == null || fiasFilesList.isEmpty()) {
                logger.error("Empty fias files list!");
                return;
            }

            Map<Integer, String> filesMapForDownload = getFileMapForDownload();

            // если список на скачивание непустой - запускаем скачивание
            downloadFiles(filesMapForDownload, tmpDir);
        }
    }

    private void downloadFiles(Map<Integer, String> filesMapForDownload, String tempFolder) {
        // run by sorted list of versions
        logger.info(String.format("Start downloading %d file(s)", filesMapForDownload.size()));

        for (Integer versionId : filesMapForDownload.keySet().stream().sorted().collect(Collectors.toList())) {
            String url = filesMapForDownload.get(versionId);

            File tmpDir = new File(tempFolder);
            if (!tmpDir.exists()) {
                tmpDir.mkdir();
            }

            String tmpfilename = tempFolder + File.separatorChar + versionId.toString() + ".rar";

            try {
                logger.info(String.format("Download file %s ...", tmpfilename));
                UtilClass.downLoadFileFromURL(tmpfilename, url);
                File tmpFile = new File(tmpfilename);
                if (tmpFile.exists()) {
                    tmpFile.renameTo(new File(downloadDir + File.separatorChar + tmpFile.getName()));
                }
            } catch (IOException e) {
                logger.error("Exception while downloading file " + url);
                e.printStackTrace();
            }
        }
        logger.info("Downloading finished");
    }

    /**
     * get map of files and their version numbers
     * @return
     */
    private Map<Integer, String> getFileMapForDownload(){
        Map<Integer, String> fileMapForDownload = new HashMap<>();

        if(archFilesMap != null){
            archFilesMap.clear();
        }

        if(workFilesMap != null){
            workFilesMap.clear();
        }

        // получаем список файлов в архивной папке
        if(archDir == null || archDir.isEmpty()) {
            archDir = "archive";
        }

        archFilesMap = UtilClass.getDirFiles(archDir, "rar");

        // получаем список файлов в папке для обработки (возможно какие-то еще не обработались либо скачаны частично)
        if(workDir == null || workDir.isEmpty()) {
            workDir = "work";
        }
        workFilesMap = UtilClass.getDirFiles(workDir, "rar");

        // определяем какие файлы надо скачать

        // если обе папки пусты - возвращем только имя последнего ПОЛНОГО архива
        if(archFilesMap.isEmpty() && workFilesMap.isEmpty()){

            DownloadFileInfo totalArch  = fiasFilesList
                    .stream()
                    .max(Comparator.comparingInt(DownloadFileInfo::getVersionId)).get();
            fileMapForDownload.put(totalArch.getVersionId(), totalArch.getFiasCompleteXmlUrl());
        }else {
            // Нам нужен максимальный номер скачанной версии
            // объединяем все списки файлов в один
            workFilesMap.putAll(archFilesMap);
            // находим максимальный номер версии
            Integer maxVersion = workFilesMap.keySet().stream().max(Comparator.naturalOrder()).get();

            // все файлы с версией выше добавляем на скачивание
            for (DownloadFileInfo downloadFileInfo : fiasFilesList) {
                if(downloadFileInfo.getVersionId() > maxVersion){
                    // добавляем только ИНКРЕМЕНТЫ
                    fileMapForDownload.put(downloadFileInfo.getVersionId(), downloadFileInfo.getFiasDeltaXmlUrl());
                }
            }
        }
        return fileMapForDownload;
    }

}
