package com.nowakartur.pdfwordcounter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class PdfWordCounterApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(PdfWordCounterApplication.class);

    private final PdfFileFounder pdfFileFounder;

    private final String folderToTraver;

    public PdfWordCounterApplication(PdfFileFounder pdfFileFounder, @Value("${app.folder-to-traver}") String folderToTraver) {
        this.pdfFileFounder = pdfFileFounder;
        this.folderToTraver = folderToTraver;
    }

    public static void main(String[] args) {
        SpringApplication.run(PdfWordCounterApplication.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        LOG.info("Start");
        long startTime = System.currentTimeMillis();
        countWordsInPdfFiles();

        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime) / 1000;
        LOG.info("Application running time: {}s", totalTime);
        LOG.info("End");
    }

    private void countWordsInPdfFiles() throws IOException {
        List<Path> pdfFiles = pdfFileFounder.findPdfFiles(folderToTraver);

        Comparator<PdfInfo> comparator = Comparator.comparing(PdfInfo::numberOfWords);
        List<PdfInfo> pdfInfos = PdfInfoMapper.mapToPdfInfo(pdfFiles).stream()
                .sorted(comparator)
                .toList();

        LOG.info("Pdfs found: {}", pdfFiles.size());
        pdfInfos.forEach(info -> LOG.info("{} - {} words", info.name(), info.numberOfWords()));
    }
}
