package com.utn.architecture.hangman.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.utn.architecture.hangman.data.access.DataConstants;

@DatabaseTable(tableName = DataConstants.TABLE_PLAYER_NAME)
public class Player {

    @DatabaseField(generatedId = true, useGetSet = true, dataType = DataType.INTEGER)
    private int id;
    @DatabaseField(useGetSet = true, canBeNull = false, dataType = DataType.STRING)
    private String username;
    @DatabaseField(useGetSet = true, canBeNull = false, dataType = DataType.STRING)
    private String password;
    @DatabaseField(useGetSet = true, canBeNull = false, dataType = DataType.INTEGER)
    private String level;

    public Player() {

    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getLevel() {
	return level;
    }

    public void setLevel(String level) {
	this.level = level;
    }

}
