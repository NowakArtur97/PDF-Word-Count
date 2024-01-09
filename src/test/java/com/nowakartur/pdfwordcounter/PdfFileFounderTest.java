package com.nowakartur.pdfwordcounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PdfFileFounderTest {

    @Test
    public void whenCountFiles_shouldReturnCorrectNumberOfFiles() throws IOException {
        PdfFileFounder pdfFileFounder = new PdfFileFounder();
        String directoryWithTestFiles = Paths.get("src", "test", "resources", "main_path", "nested_path").toAbsolutePath().toString();

        List<Path> actualPdfFiles = pdfFileFounder.findPdfFiles(directoryWithTestFiles);

        Assertions.assertEquals(3, actualPdfFiles.size());
    }

    @Test
    public void whenCountFilesWithNestedDirectory_shouldReturnCorrectNumberOfFiles() throws IOException {
        PdfFileFounder pdfFileFounder = new PdfFileFounder();
        String directoryWithTestFiles = Paths.get("src", "test", "resources", "main_path").toAbsolutePath().toString();

        List<Path> actualPdfFiles = pdfFileFounder.findPdfFiles(directoryWithTestFiles);

        Assertions.assertEquals(5, actualPdfFiles.size());
    }
}
