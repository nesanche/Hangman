package com.utn.architecture.hangman.data.access;

import java.util.HashMap;
import java.util.LinkedList;

import com.j256.ormlite.dao.Dao;
import com.utn.architecture.hangman.model.Player;
import com.utn.architecture.hangman.model.PlayerWord;
import com.utn.architecture.hangman.model.Word;

public interface IDataAccess {

    void insert(String tableName, HashMap<String, String> values);

    LinkedList<LinkedList<String>> select(String query, String[] selectionArgs);

    void delete(String tableName, int id, String idName);

    void update(String tableName, HashMap<String, String> values, int id,
            String idName);

    Dao<Player, Integer> getPlayerDao();

    Dao<Word, Integer> getWordDao();

    Dao<PlayerWord, Integer> getPlayerWordDao();

}