package com.itguru.FirstTask.analyzer;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Analyzer {

    public Map<String, Integer> getTwoFrequentWords(String text) {
        long startTime = System.nanoTime() / 1000000;
        Map<String, Integer> wordsMap = new HashMap<>();
        List<String> words = this.getAllWords(text);
        words.forEach(word -> {
            word = validateWord(word);
            if (wordsMap.containsKey(word)) wordsMap.put(word, wordsMap.get(word) + 1);
            else wordsMap.put(word, 1);
        });
        //wordsMap.forEach((word, count) -> System.out.println(word + " : " + count));

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

        System.out.println();
        resultMap.forEach((key, value) -> System.out.println(key + " : " + value));
        long elapsedTime = System.nanoTime() / 1000000 - startTime;
        System.out.println('\n' + "elapsed time: " + elapsedTime + " millis");

        return resultMap;
    }

    public List<String> getThreeLongestWords(String text) {
        long startTime = System.nanoTime() / 1000000;

        //Set<String> wordsSet = new HashSet<>(getAllWords(text));
        //List<String> words = new ArrayList<String>(wordsSet);
        List<String> words = getAllWords(text).stream().map(this::validateWord).distinct().collect(Collectors.toList());
        Collections.sort(words, (o1, o2) -> o2.length() - o1.length());

        words.subList(0, 3).forEach(word -> System.out.println(word + " : " + word.length()));

        long elapsedTime = System.nanoTime() / 1000000 - startTime;
        System.out.println('\n' + "elapsed time: " + elapsedTime + " millis");

        return words.subList(0, 3);
    }

    public List<String> firstThreeWordsWithDuplicates(String text) {
        long startTime = System.nanoTime() / 1000000;
        List<String> words = getAllWords(text);
        List<String> resultList = new ArrayList<String>();
        int countDuplicates = 0;
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.size(); j++) {
                if (countDuplicates == 3) break;
                if (i != j) {
                    words.set(i, validateWord(words.get(i)));
                    if (words.get(i).equals(words.get(j)) && !resultList.contains(words.get(i))) {
                        resultList.add(words.get(i));
                        countDuplicates++;
                    }
                }
            }
        }

        List<String> processedResultList = resultList.stream().
                map(item -> new StringBuilder(item).reverse().toString().toUpperCase())
                .sorted((el1, el2) -> el1.length() - el2.length())
                .collect(Collectors.toList());

        processedResultList.forEach(System.out::println);

        long elapsedTime = System.nanoTime() / 1000000 - startTime;
        System.out.println('\n' + "elapsed time: " + elapsedTime + " millis");

        return processedResultList;

    }

    public String getContentFromFile(String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(path));
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        StringBuilder result = new StringBuilder();
        while ((line = fileReader.readLine()) != null) result.append(line);
        return result.toString();
    }

    public List<String> getAllWords(String text) {
        return Arrays.asList(text.split("\\s+"));
    }

    public String validateWord(String word) {
        if (!Character.isLetter(word.charAt(0)))
            word = word.substring(1);
        else if (!Character.isLetter(word.charAt(word.length() - 1)))
            word = word.substring(0, word.length() - 1);
        return word;
    }
}