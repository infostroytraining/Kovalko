package com.itguru.FirstTask.introduction;

/**
 * Проверка орфографии
 *
 * Некоторые люди не обращают внимание на орфографию. Например, не пишут новое
 * предложение с заглавной буквы. Или не ставят пробел после знаков препинания.
 *
 * Ваша задача: исправить их ошибки.
 *
 * Что нужно сделать:
 *
 * 1. Каждое новое предложение должно начинаться с заглавной буквы.
 * 2. После знаков препинания (точка и запятая) должны быть пробелы.
 */


import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class TextUtils {
    public String correctText(String text) {
        if (text == null) return "";
        text = setCorrectSpaces(text);
        List<Character> separators = Arrays.asList(' ', ',', '.', '\n', '\t', '!', '?');
        StringTokenizer sentences = new StringTokenizer(text, ".!?", true);
        StringBuilder builder = new StringBuilder();
        while (sentences.hasMoreElements()) {
            String sentence = sentences.nextToken();
            if (Character.isLowerCase(sentence.charAt(0)))
                sentence = String.valueOf(sentence.charAt(0)).toUpperCase() + sentence.substring(1);
            else if (sentence.charAt(0) == ' ')
                sentence = " " + String.valueOf(sentence.charAt(1)).toUpperCase() + sentence.substring(2);
            builder.append(sentence);
        }
        return validateSentence(builder, separators);
    }

    public String setCorrectSpaces(String text) {
        return text.replaceAll("\\s+", " ").trim();
    }

    public String validateSentence(StringBuilder sentence, List<Character> separators) {
        if (sentence == null) return "";
        for (int i = 0; i < sentence.length(); i++)
            if (separators.contains(sentence.charAt(i)) && i + 1 < sentence.length() && !separators.contains(sentence.charAt(i + 1))) {
                sentence.insert(i + 1, " ");
                i++;
            }
        return setCorrectSpaces(sentence.toString());
    }
}