package com.utn.architecture.hangman.model;

public interface IDifficultyCalculator {

    public static final int VERY_EASY_DIFFICULTY = 1;

    public static final int EASY_DIFFICULTY = 2;

    public static final int MEDIUM_DIFFICULTY = 3;

    public static final int HARD_DIFFICULTY = 4;

    public static final int VERY_HARD_DIFFICULTY = 5;

    float calculateDifficulty(String word);
}
