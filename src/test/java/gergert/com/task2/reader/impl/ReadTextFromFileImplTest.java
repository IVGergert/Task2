package gergert.com.task2.reader.impl;

import com.gergert.task2.reader.impl.ReadTextFromFileImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadTextFromFileImplTest {

    ReadTextFromFileImpl readTextFromFile = new ReadTextFromFileImpl();

    @Test
    void read(@TempDir Path tempDir) {
        Path tempFile = tempDir.resolve("test_text.txt");

        List<String> expected = Arrays.asList("Hello World and peaple. I love you");

        try {
            Files.write(tempFile, expected);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String filePath = tempFile.toString();

        List<String> actual = readTextFromFile.read(filePath);

        assertEquals(expected, actual);

    }
}