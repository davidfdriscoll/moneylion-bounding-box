package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class IOReader {
    /**
     * @param inputStream input stream (normally stdin)
     * @return boolean array, where true corresponds to an asterisk in the input stream
     * and false to a hyphen
     */
    public static boolean[][] parseFromInputStream(InputStream inputStream) {
        List<boolean[]> resultList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line = reader.readLine();
            while (line != null && !line.isEmpty()) {
                boolean[] array = new boolean[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    array[i] = line.charAt(i) == '*';
                }
                resultList.add(array);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList.toArray(new boolean[0][]);
    }
}
