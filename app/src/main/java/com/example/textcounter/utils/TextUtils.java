package com.example.textcounter.utils;

import com.example.textcounter.R;

public class TextUtils {
    public static int getWordCount(String input) {
        input = input.trim();
        if(input.isEmpty()) {
            return -1;
        }
        String[] textParts = input.split("\\s+");
        return textParts.length;
    }
    public static int getCharCount(String input) {
        if(input.isEmpty()) {
            return -1;
        }
        return input.length();
    }
}
