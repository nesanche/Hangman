package com.utn.architecture.hangman.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.utn.architecture.hangman.data.access.DataConstants;

@DatabaseTable(tableName = DataConstants.TABLE_PLAYER_NAME)
public class Player {

    public static final String ID_FIELD = "id_player";
    public static final String USERNAME_FIELD = "username";
    public static final String LEVEL_FIELD = "level";

    @DatabaseField(generatedId = true, columnName = ID_FIELD, useGetSet = true, dataType = DataType.INTEGER)
    private int id;
    @DatabaseField(useGetSet = true, canBeNull = false, dataType = DataType.STRING)
    private String username;
    /*
     * @DatabaseField(useGetSet = true, canBeNull = false, dataType =
     * DataType.STRING) private String password;
     */
    @DatabaseField(useGetSet = true, canBeNull = false, dataType = DataType.INTEGER)
    private int level;

    public Player() {

    }

    public Player(String username, int level) {
        this.username = username;
        this.level = level;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
