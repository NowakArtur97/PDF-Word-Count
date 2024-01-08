package com.nowakartur.pdfwordcounter;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class PdfInfoMapper {

    public static final String PDF_EXTENSION = ".pdf";
    private static final String SPACE = " ";

    public static List<PdfInfo> mapToPdfInfo(List<Path> pdfFiles) {
        return pdfFiles.stream()
                .map(PdfInfoMapper::mapToPdfInfo)
                .toList();
    }

    private static PdfInfo mapToPdfInfo(Path path) {
        try {
            String pathname = path.toString();
            PDDocument document = Loader.loadPDF(new File(pathname));
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            String[] texts = text.split(SPACE);
            String fileName = getFileName(path);
            return new PdfInfo(fileName, Arrays.stream(texts).count());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFileName(Path path) {
        return path.getFileName().toString().replace(PDF_EXTENSION, "");
    }
}
