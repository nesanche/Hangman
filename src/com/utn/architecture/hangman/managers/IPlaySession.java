package com.utn.architecture.hangman.managers;

import java.util.ArrayList;

public interface IPlaySession {

    ArrayList<Integer> getWordPositionsOfLetter(char letter);

    ArrayList<Integer> getWordPositionsOfPosition(int position);

}
