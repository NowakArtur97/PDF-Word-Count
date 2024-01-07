package com.nowakartur.pdfwordcounter;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class PdfFileFounder {

    private static final String PDF_EXTENSION = ".pdf";

    public List<Path> findPdfFiles(String folderToTravers) throws IOException {
        Path path = Paths.get(folderToTravers);
        return Files.find(path, Integer.MAX_VALUE, (filePath, fileAttr) -> fileAttr.isRegularFile())
                .filter(this::isPdfFile)
                .toList();
    }

    private boolean isPdfFile(Path f) {
        return f.toString().endsWith(PDF_EXTENSION);
    }
}
