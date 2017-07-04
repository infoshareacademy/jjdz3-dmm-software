package com.dmmsoft.app.file;

import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenue;
import com.dmmsoft.app.appconfiguration.AppConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by milo on 04.07.17.
 */
public class RemoteDownloader {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteDownloader.class);
    private AppConfigurationProvider appConfigurationProvider = new AppConfigurationProvider().getConfiguration();
    private final String DATE_PATTERN = "yyyyMMdd";


    public void getModelFilesFromRemoteLocation() throws IOException {
        try {
            this.download(appConfigurationProvider.getCurrencyUrl().getFileUrl(),
                    appConfigurationProvider.getCurrencyBackupFolderPath().getFolderPath());

            this.download(appConfigurationProvider.getFundUrl().getFileUrl(),
                    appConfigurationProvider.getFundBackupFolderPath().getFolderPath());

        } catch (IOException ex) {
            LOGGER.error("FAILED To download file from remote location: {}", ex.getMessage());
        }
    }

    private void download(String sourceURL, String targetDirectory) throws IOException {
        URL url = new URL(sourceURL);
        String fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1, sourceURL.length());
        Path targetPath = new File(targetDirectory
                .concat(File.separator)
                .concat(this.getFileNameWithDate(fileName)))
                .toPath();

        Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        LOGGER.info("File saved, source:{} target:{}",sourceURL , targetPath);
    }

    private String getFileNameWithDate(String defaultFileName) {

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        String formattedDate = localDate.format(formatter);

        return formattedDate
                .concat("_")
                .concat(defaultFileName);
    }


}
