package com.utn.architecture.hangman.managers;

import java.util.ArrayList;

import com.utn.architecture.hangman.model.Player;
import com.utn.architecture.hangman.model.Word;

public class PlaySession implements IPlaySession {

    private Word word;
    private Player player;
    private int lifes;
    private int coins;

    public PlaySession(Player player, Word word, int lifes, int coins) {
	this.word = word;
	this.player = player;
	this.lifes = lifes;
	this.coins = coins;
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
	return positions;
    }

    @Override
    public ArrayList<Integer> getWordPositionsOfPosition(int position) {
	char letter = word.getWord().charAt(position);
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

}
