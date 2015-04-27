package com.utn.architecture.hangman.managers;

import java.util.ArrayList;

public class WordLayoutManager {
    
    public ArrayList<String> generateArray(String word) {
        ArrayList<String> wordArray = new ArrayList<>();        
        for(int i = 0; i < word.length(); i++) {
            wordArray.add(String.valueOf(word.charAt(i)));
        }   
        return wordArray;
    }
    
//    public ArrayAdapter getAdapter() {
//        
//    }
}
