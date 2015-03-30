package com.utn.architecture.hangman.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.utn.architecture.hangman.data.access.DataConstants;

@DatabaseTable(tableName = DataConstants.TABLE_WORD_NAME)
public class Word {

    @DatabaseField(generatedId = true, useGetSet = true, dataType = DataType.INTEGER)
    private int id;
    @DatabaseField(useGetSet = true, canBeNull = false, dataType = DataType.STRING)
    private String word;
    @DatabaseField(useGetSet = true, canBeNull = false, dataType = DataType.INTEGER)
    private int length;
    @DatabaseField(useGetSet = true, canBeNull = false, dataType = DataType.INTEGER)
    private int difficulty;

    public Word() {

    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getWord() {
	return word;
    }

    public void setWord(String word) {
	this.word = word;
    }

    public int getLength() {
	return length;
    }

    public void setLength(int length) {
	this.length = length;
    }

    public int getDifficulty() {
	return difficulty;
    }

    public void setDifficulty(int difficulty) {
	this.difficulty = difficulty;
    }

}
