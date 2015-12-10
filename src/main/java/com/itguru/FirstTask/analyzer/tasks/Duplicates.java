package com.itguru.FirstTask.analyzer.tasks;

import com.itguru.FirstTask.analyzer.Command;
import com.itguru.FirstTask.analyzer.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Duplicates implements Command {

    private List<String> resultList;

    @Override
    public void execute(String text) {
        long startTime = System.nanoTime() / 1000000;
        List<String> words = TextUtils.getAllWords(text);
        List<String> resultList = new ArrayList<String>();
        int countDuplicates = 0;
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.size(); j++) {
                if (countDuplicates == 3) break;
                if (i != j) {
                    words.set(i, TextUtils.validateWord(words.get(i)));
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
        System.out.println("elapsed time: " + elapsedTime + " millis" + '\n');
        this.resultList = processedResultList;
    }

    //For test
    public List<String> execute(String text, boolean test) {
        execute(text);
        return resultList;
    }
}