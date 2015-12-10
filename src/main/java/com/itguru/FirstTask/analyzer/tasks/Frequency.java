package com.itguru.FirstTask.analyzer.tasks;

import com.itguru.FirstTask.analyzer.Command;
import com.itguru.FirstTask.analyzer.utils.TextUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Frequency implements Command {

    private Map<String, Integer> resultMap;

    @Override
    public void execute(String text) {
        long startTime = System.nanoTime() / 1000000;
        Map<String, Integer> wordsMap = new HashMap<>();
        List<String> words = TextUtils.getAllWords(text);
        words.forEach(word -> {
            word = TextUtils.validateWord(word);
            if (wordsMap.containsKey(word)) wordsMap.put(word, wordsMap.get(word) + 1);
            else wordsMap.put(word, 1);
        });

        Map<String, Integer> resultMap = new TreeMap<>((word1, word2) -> word2.compareToIgnoreCase(word1));
        int maxWord1Count = 0, maxWord2Count = 0;
        String maxWord1 = "", maxWord2 = "";
        for (Map.Entry entry : wordsMap.entrySet()) {
            if (((Integer) entry.getValue()) > maxWord1Count) {
                maxWord1Count = (Integer) entry.getValue();
                maxWord1 = (String) entry.getKey();
            } else if (((Integer) entry.getValue()) > maxWord2Count && ((Integer) entry.getValue()) < maxWord1Count) {
                maxWord2Count = (Integer) entry.getValue();
                maxWord2 = (String) entry.getKey();
            }
        }

        resultMap.put(maxWord1, maxWord1Count);
        resultMap.put(maxWord2, maxWord2Count);

        resultMap.forEach((key, value) -> System.out.println(key + " : " + value));
        long elapsedTime = System.nanoTime() / 1000000 - startTime;
        System.out.println("elapsed time: " + elapsedTime + " millis" + '\n');
        this.resultMap = resultMap;
    }

    //For test
    public Map<String, Integer> execute(String text, boolean test) {
        execute(text);
        return this.resultMap;
    }

}