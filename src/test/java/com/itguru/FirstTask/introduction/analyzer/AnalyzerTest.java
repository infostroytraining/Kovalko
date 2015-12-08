package com.itguru.FirstTask.introduction.analyzer;

import com.itguru.FirstTask.analyzer.Analyzer;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class AnalyzerTest {

    Analyzer analyzer = new Analyzer();

    @Test
    public void testGetTwoFrequentWords() throws IOException {
        String text = analyzer.getContentFromFile("text.txt");
        Map<String, Integer> twoFrequentWords = analyzer.getTwoFrequentWords(text);
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
        String text = analyzer.getContentFromFile("text.txt");
        List<String> twoFrequentWords = analyzer.getThreeLongestWords(text);
        assertNotNull(twoFrequentWords);
        assertEquals(twoFrequentWords.get(0), "non-characteristic");
        assertEquals(twoFrequentWords.get(1), "embarrassing");
        assertEquals(twoFrequentWords.get(2), "variations");
    }

    @Test
    public void testFirstThreeWordsWithDuplicates() throws IOException{
        String text = analyzer.getContentFromFile("text.txt");
        List<String> words = analyzer.firstThreeWordsWithDuplicates(text);
        assertEquals(words.get(0), "FO");
        assertEquals(words.get(1), "ERA");
        assertEquals(words.get(2), "MEROL");
    }

    @Test(expected = IOException.class)
    public void testGetContentFromFile() throws IOException {
        String t = analyzer.getContentFromFile("text.txt");
        throw new IOException();
    }

}