package org.example;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class IOReaderTest {
    @Test
    public void testParseFromInputStreamGroups() throws FileNotFoundException {
        File initialFile = new File("src/test/resources/groups.txt");
        InputStream fileStream = new FileInputStream(initialFile);
        boolean[][] actualArray = IOReader.parseFromInputStream(fileStream);
        boolean[][] expectedArray = new boolean[][]{
                {true, true, false, false, false, false, false, false, false, true, true, true},
                {false, true, false, false, true, true, false, false, true, true, true, false},
                {false, false, false, false, false, true, true, true, false, false, true, true},
                {false, false, false, false, false, false, false, true, true, true, false, false}
        };
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void testParseFromInputStreamSimple() throws FileNotFoundException {
        File initialFile = new File("src/test/resources/simple.txt");
        InputStream fileStream = new FileInputStream(initialFile);
        boolean[][] actualArray = IOReader.parseFromInputStream(fileStream);
        boolean[][] expectedArray = new boolean[][]{
                {false, false, false, false},
                {false, true, true, false},
                {false, true, true, false},
                {false, false, false, false}
        };
        assertArrayEquals(expectedArray, actualArray);
    }
}