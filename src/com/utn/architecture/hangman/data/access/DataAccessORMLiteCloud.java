package com.utn.architecture.hangman.data.access;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.utn.architecture.hangman.model.Player;
import com.utn.architecture.hangman.model.PlayerWord;
import com.utn.architecture.hangman.model.Word;

public class DataAccessORMLiteCloud implements IDataAccess {

    private static final String DATABASE_URL = "jdbc:mysql://96.30.30.72/2096/inntec_hangman";

    private JdbcConnectionSource jdbcConnectionSource;

    public DataAccessORMLiteCloud() {
        try {
            jdbcConnectionSource = new JdbcConnectionSource(DATABASE_URL,
                    "inntec", "felipeCAB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(String tableName, HashMap<String, String> values) {
        // TODO Auto-generated method stub

    }

    @Override
    public LinkedList<LinkedList<String>> select(String query,
            String[] selectionArgs) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(String tableName, int id, String idName) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(String tableName, HashMap<String, String> values,
            int id, String idName) {
        // TODO Auto-generated method stub

    }

    @Override
    public Dao<Player, Integer> getPlayerDao() {
        try {
            return DaoManager.createDao(jdbcConnectionSource, Player.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Dao<Word, Integer> getWordDao() {
        try {
            return DaoManager.createDao(jdbcConnectionSource, Word.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Dao<PlayerWord, Integer> getPlayerWordDao() {
        try {
            return DaoManager.createDao(jdbcConnectionSource, PlayerWord.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
