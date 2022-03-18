package com.example.textcounter;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.textcounter.utils.TextUtils;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void getCharsCount_Given_Empty_Result_MinusOne() {
        String given = "";
        int expected = -1;
        int actualValue = TextUtils.getCharCount(given);
        assertEquals(expected, actualValue);
    }

    @Test
    public void getCharsCount_Given_TenChars_Result_Ten() {
        String given = "LabasLabas";
        int expected = 10;
        int actualValue = TextUtils.getCharCount(given);
        assertEquals(expected, actualValue);
    }

    @Test(expected = NullPointerException.class)
    public void getCharsCount_Given_Null_Result_Exception() {
        String given = null;
        TextUtils.getCharCount(given);
    }

    @Test
    public void getWordCount_Given_Empty_Result_MinusOne() {
        String given = "";
        int expected = -1;
        int actualValue = TextUtils.getWordCount(given);
        assertEquals(expected, actualValue);
    }

    @Test
    public void getWordCount_Given_ThreeWords_Result_Three() {
        String given = "Labas, kaip sekas?";
        int expected = 3;
        int actualValue = TextUtils.getWordCount(given);
        assertEquals(expected, actualValue);
    }

    @Test
    public void getWordCount_Given_OneWordWithSpace_Result_One() {
        String given = "...Labas.... ";
        int expected = 1;
        int actualValue = TextUtils.getWordCount(given);
        assertEquals(expected, actualValue);
    }

    @Test
    public void getWordCount_Given_TwoWordsWithMultiSpace_Result_Two() {
        String given = "Prispausta    space";
        int expected = 2;
        int actualValue = TextUtils.getWordCount(given);
        assertEquals(expected, actualValue);
    }

    @Test(expected = NullPointerException.class)
    public void getWordCount_Given_Null_Result_Exception() {
        String given = null;
        TextUtils.getWordCount(given);
    }
}