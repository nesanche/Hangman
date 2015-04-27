package com.utn.architecture.hangman.data.access;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.utn.architecture.hangman.model.Player;
import com.utn.architecture.hangman.model.PlayerWord;
import com.utn.architecture.hangman.model.Word;

public class DataAccessORMLite extends OrmLiteSqliteOpenHelper implements
        IDataAccess {

    public DataAccessORMLite(Context context) {
        super(context, DataConstants.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DataAccessSQLite.class.getName(), "onCreate");
            TableUtils.createTableIfNotExists(connectionSource, Player.class);
            TableUtils.createTableIfNotExists(connectionSource, Word.class);
            TableUtils.createTableIfNotExists(connectionSource,
                    PlayerWord.class);
        } catch (SQLException e) {
            Log.e(DataAccessSQLite.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
            int oldVersion, int newVersion) {
        try {
            Log.i(DataAccessSQLite.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Player.class, true);
            TableUtils.dropTable(connectionSource, Word.class, true);
            TableUtils.dropTable(connectionSource, PlayerWord.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DataAccessSQLite.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        super.close();
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
            return getDao(Player.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Dao<Word, Integer> getWordDao() {
        try {
            return getDao(Word.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Dao<PlayerWord, Integer> getPlayerWordDao() {
        try {
            return getDao(PlayerWord.class);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void instertTestData() {
        Dao<Player, Integer> playerDao = getPlayerDao();
        try {
            playerDao.create(new Player("Franqui", 1));
            playerDao.create(new Player("Nico", 1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
