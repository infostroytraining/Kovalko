package com.itguru.FirstTask.introduction.analyzer;

import com.itguru.FirstTask.analyzer.tasks.Duplicates;
import com.itguru.FirstTask.analyzer.tasks.Frequency;
import com.itguru.FirstTask.analyzer.tasks.Length;
import com.itguru.FirstTask.analyzer.tasks.TaskFactory;
import com.itguru.FirstTask.analyzer.utils.TextUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class AnalyzerTest {

    @Test
    public void testGetTwoFrequentWords() throws IOException {
        String text = TextUtils.getContentFromFile("text.txt");
        Frequency command = (Frequency) TaskFactory.getInstance("frequency");
        assertNotNull(command);
        Map<String, Integer> twoFrequentWords = command.execute(text, true);
        assertNotNull(twoFrequentWords);
        int index = 1;
        for (Map.Entry entry : twoFrequentWords.entrySet()) {
            if (index == 1) {
                assertEquals(entry.getKey(), "of");
                assertEquals(entry.getValue(), 6);
            } else if (index == 2) {
                assertEquals(entry.getKey(), "Lorem");
                assertEquals(entry.getValue(), 5);
            }
            index++;
        }
    }

    @Test
    public void testGetThreeLongestWords() throws IOException {
        String text = TextUtils.getContentFromFile("text.txt");
        Length lengthTask = (Length) TaskFactory.getInstance("length");
        assertNotNull(lengthTask);
        List<String> twoFrequentWords = lengthTask.execute(text, true);
        assertNotNull(twoFrequentWords);
        assertEquals(twoFrequentWords.get(0), "non-characteristic");
        assertEquals(twoFrequentWords.get(1), "embarrassing");
        assertEquals(twoFrequentWords.get(2), "variations");
    }

    @Test
    public void testFirstThreeWordsWithDuplicates() throws IOException{
        String text = TextUtils.getContentFromFile("text.txt");
        Duplicates duplicates = (Duplicates) TaskFactory.getInstance("duplicates");
        assertNotNull(duplicates);
        List<String> words = duplicates.execute(text, true);
        assertEquals(words.get(0), "FO");
        assertEquals(words.get(1), "ERA");
        assertEquals(words.get(2), "MEROL");
    }

    @Test(expected = IOException.class)
    public void testGetContentFromFile() throws IOException {
        String text = TextUtils.getContentFromFile("text.txt");
        throw new IOException();
    }

}