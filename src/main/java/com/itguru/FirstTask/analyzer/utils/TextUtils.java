package com.itguru.FirstTask.analyzer.utils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class TextUtils {

    public static String getContentFromFile(String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(path));
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        StringBuilder result = new StringBuilder();
        while ((line = fileReader.readLine()) != null) result.append(line);
        return result.toString();
    }

    public static List<String> getAllWords(String text) {
        return Arrays.asList(text.split("\\s+"));
    }

    public static String validateWord(String word) {
        if (!Character.isLetter(word.charAt(0)))
            word = word.substring(1);
        else if (!Character.isLetter(word.charAt(word.length() - 1)))
            word = word.substring(0, word.length() - 1);
        return word;
    }
}