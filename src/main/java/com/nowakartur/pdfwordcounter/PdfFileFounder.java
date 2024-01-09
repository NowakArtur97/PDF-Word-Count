package com.nowakartur.pdfwordcounter;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.BiPredicate;

import static com.nowakartur.pdfwordcounter.PdfInfoMapper.PDF_EXTENSION;

@Component
public class PdfFileFounder {

    public List<Path> findPdfFiles(String folderToTraver) throws IOException {
        Path path = Paths.get(folderToTraver);
        return Files.find(path, Integer.MAX_VALUE, isRegularFile())
                .filter(this::isPdfFile)
                .toList();
    }

    private BiPredicate<Path, BasicFileAttributes> isRegularFile() {
        return (filePath, fileAttr) -> fileAttr.isRegularFile();
    }

    private boolean isPdfFile(Path f) {
        return f.toString().endsWith(PDF_EXTENSION);
    }
}
