package com.nowakartur.pdfwordcounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PdfWordCounterTest {

    @Test
    public void whenCountNumberOfWords_shouldReturnAlmostCorrectNumberOfWords() throws IOException {
        Path path = Paths.get("src", "test", "resources", "500_words.pdf");

        long actualNumberOfWords = PdfWordCounter.countNumberOfWordsInPdf(path);

        Assertions.assertEquals(505, actualNumberOfWords);
    }

    @Test
    public void whenCountNumberOfWordsInLargeFile_shouldReturnAlmostCorrectNumberOfWords() throws IOException {
        Path path = Paths.get("src", "test", "resources", "10_000_words.pdf");

        long actualNumberOfWords = PdfWordCounter.countNumberOfWordsInPdf(path);

        Assertions.assertEquals(10109, actualNumberOfWords);
    }
}
