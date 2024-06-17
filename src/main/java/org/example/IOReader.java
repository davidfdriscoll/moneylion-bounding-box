package org.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class IOReader {
    /**
     * @param inputStream input stream (normally stdin)
     * @return boolean array, where true corresponds to an asterisk in the input stream
     * and false to a hyphen
     */
    public static boolean[][] parseFromInputStream(InputStream inputStream) {
        Stream<String> textStream = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines();

        return textStream.map(string -> {
            boolean[] array = new boolean[string.length()];
            for (int i = 0; i < string.length(); i++) {
                array[i] = string.charAt(i) == '*';
            }
            return array;
        }).toArray(boolean[][]::new);
    }
}
