package com.nowakartur.pdfwordcounter;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static com.nowakartur.pdfwordcounter.PdfInfoMapper.SPACE;

public class PdfWordCounter {

    public static long countNumberOfWordsInPdf(Path path) throws IOException {
        String pathname = path.toString();
        PDDocument document = Loader.loadPDF(new File(pathname));
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        String[] texts = text.split(SPACE);
        document.close();
        return texts.length;
    }
}
