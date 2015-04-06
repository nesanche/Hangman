package com.utn.architecture.hangman.data.access;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
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
	// db.execSQL(DataConstants.TABLE_PLAYER_CREATE_QUERY);
	// db.execSQL(DataConstants.TABLE_WORD_CREATE_QUERY);
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
    public RuntimeExceptionDao<Player, Integer> getPlayerDao() {
	return getRuntimeExceptionDao(Player.class);
    }

    @Override
    public RuntimeExceptionDao<Word, Integer> getWordDao() {
	return getRuntimeExceptionDao(Word.class);
    }

    @Override
    public RuntimeExceptionDao<PlayerWord, Integer> getPlayerWordDao() {
	return getRuntimeExceptionDao(PlayerWord.class);
    }
}
