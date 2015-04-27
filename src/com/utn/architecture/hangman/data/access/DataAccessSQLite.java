package com.utn.architecture.hangman.data.access;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.j256.ormlite.dao.Dao;
import com.utn.architecture.hangman.model.Player;
import com.utn.architecture.hangman.model.PlayerWord;
import com.utn.architecture.hangman.model.Word;

public class DataAccessSQLite extends SQLiteOpenHelper implements IDataAccess {

    public DataAccessSQLite(Context context) {
        super(context, DataConstants.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataConstants.TABLE_PLAYER_CREATE_QUERY);
        db.execSQL(DataConstants.TABLE_WORD_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    @Override
    public void insert(String tableName, HashMap<String, String> values) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db == null)
            return;
        Iterator<Map.Entry<String, String>> it = values.entrySet().iterator();
        ContentValues contentValues = new ContentValues();
        while (it.hasNext()) {
            Map.Entry<String, String> pairs = (Map.Entry<String, String>) it
                    .next();
            contentValues.put(pairs.getKey(), pairs.getValue());
            // it.remove();
        }
        db.insert(tableName, null, contentValues);
    }

    @Override
    public LinkedList<LinkedList<String>> select(String query,
            String[] selectionArgs) {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db == null)
            return null;
        Cursor cursor = db.rawQuery(query, selectionArgs);
        LinkedList<LinkedList<String>> table = new LinkedList<LinkedList<String>>();
        if (cursor.moveToFirst()) {
            do {
                LinkedList<String> row = new LinkedList<String>();
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    row.addLast(cursor.getString(i));
                }
                table.addLast(row);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return table;
    }

    @Override
    public void delete(String tableName, int id, String idName) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db == null)
            return;
        db.delete(tableName, idName + " = ? ",
                new String[] { Integer.toString(id) });

    }

    @Override
    public void update(String tableName, HashMap<String, String> values,
            int id, String idName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Iterator<Map.Entry<String, String>> it = values.entrySet().iterator();
        ContentValues contentValues = new ContentValues();
        while (it.hasNext()) {
            Map.Entry<String, String> pairs = (Map.Entry<String, String>) it
                    .next();
            contentValues.put(pairs.getKey(), pairs.getValue());
            // it.remove();
        }
        db.update(tableName, contentValues, idName + " = ? ",
                new String[] { Integer.toString(id) });
    }

    @Override
    public Dao<Player, Integer> getPlayerDao() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Dao<Word, Integer> getWordDao() {
        // TODO Auto-generated method stub
        return null;
    }

    public Dao<PlayerWord, Integer> getPlayerWordDao() {
        // TODO Auto-generated method stub
        return null;
    }

}
