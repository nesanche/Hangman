package com.utn.architecture.hangman.managers;

import java.util.ArrayList;
import java.util.Arrays;

import com.utn.architecture.hangman.model.Player;
import com.utn.architecture.hangman.model.Word;

public class PlaySession implements IPlaySession {

    private Word word;
    private Player player;
    private int lifes;
    private int coins;
    private ArrayList<String> letters;

    public PlaySession(Player player, Word word, int lifes, int coins) {
        this.word = word;
        this.player = player;
        this.lifes = lifes;
        this.coins = coins;
        this.letters = new ArrayList<String>();
        String[] lettersArray = new String[] { "A", "B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z" };
        this.letters = (ArrayList<String>) Arrays.asList(lettersArray);
        ;
    }

    @Override
    public ArrayList<Integer> getWordPositionsOfLetter(char letter) {
        boolean found = false;
        ArrayList<Integer> positions = new ArrayList<Integer>();
        for (int i = word.getWord().indexOf(letter); i >= 0; i = word.getWord()
                .indexOf(letter, i + i)) {
            found = true;
            positions.add(i);
        }
        if (!found)
            positions.add(-1);
        this.letters.remove(this.letters.indexOf(letter + ""));
        return positions;
    }

    @Override
    public ArrayList<Integer> getWordPositionsOfPosition(int position) {
        char letter = word.getWord().charAt(position);
        this.letters.remove(this.letters.indexOf(letter + ""));
        return getWordPositionsOfLetter(letter);
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public ArrayList<String> getLetters() {
        return letters;
    }

    public void setLetters(ArrayList<String> letters) {
        this.letters = letters;
    }

}
