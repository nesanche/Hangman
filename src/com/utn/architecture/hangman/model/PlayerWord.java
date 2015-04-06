package com.utn.architecture.hangman.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.utn.architecture.hangman.data.access.DataConstants;

@DatabaseTable(tableName = DataConstants.TABLE_WORDPLAYER_NAME)
public class PlayerWord {

    public static final String PLAYER_ID_FIELD = "player_id";
    public static final String WORD_ID_FIELD = "word_id";

    @DatabaseField(generatedId = true, useGetSet = true, dataType = DataType.INTEGER)
    private int id;

    @DatabaseField(foreign = true, columnName = PLAYER_ID_FIELD, canBeNull = false, useGetSet = true)
    private Player player;

    @DatabaseField(foreign = true, columnName = WORD_ID_FIELD, canBeNull = false, useGetSet = true)
    private Word word;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public Player getPlayer() {
	return player;
    }

    public void setPlayer(Player player) {
	this.player = player;
    }

    public Word getWord() {
	return word;
    }

    public void setWord(Word word) {
	this.word = word;
    }

}
