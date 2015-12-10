package com.itguru.FirstTask.analyzer.tasks;

import com.itguru.FirstTask.analyzer.Command;
import com.itguru.FirstTask.analyzer.utils.TextUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Length implements Command {

    private List<String> words;

    @Override
    public void execute(String text) {
        long startTime = System.nanoTime() / 1000000;

        List<String> words = TextUtils.getAllWords(text).stream().map(TextUtils::validateWord).distinct().collect(Collectors.toList());
        Collections.sort(words, (o1, o2) -> o2.length() - o1.length());

        words.subList(0, 3).forEach(word -> System.out.println(word + " : " + word.length()));

        long elapsedTime = System.nanoTime() / 1000000 - startTime;
        System.out.println("elapsed time: " + elapsedTime + " millis" + '\n');
        this.words = words.subList(0, 3);
    }

    //For test
    public List<String> execute(String text, boolean test) {
        execute(text);
        return this.words;
    }
}