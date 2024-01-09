package com.nowakartur.pdfwordcounter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class PdfInfoMapper {

    public static final String PDF_EXTENSION = ".pdf";
    public static final String SPACE = " ";

    public static List<PdfInfo> mapToPdfInfo(List<Path> pdfFiles) {
        return pdfFiles.stream()
                .map(PdfInfoMapper::mapToPdfInfo)
                .toList();
    }

    private static PdfInfo mapToPdfInfo(Path path) {
        try {
            long numberOfWords = PdfWordCounter.countNumberOfWordsInPdf(path);
            String fileName = getFileName(path);
            return new PdfInfo(fileName, numberOfWords);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFileName(Path path) {
        return path.getFileName().toString().replace(PDF_EXTENSION, "");
    }
}
