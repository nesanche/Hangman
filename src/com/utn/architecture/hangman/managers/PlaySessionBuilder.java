package com.utn.architecture.hangman.managers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.utn.architecture.hangman.data.access.DataAccessORMLite;
import com.utn.architecture.hangman.data.access.IDataAccess;
import com.utn.architecture.hangman.model.Player;
import com.utn.architecture.hangman.model.PlayerWord;
import com.utn.architecture.hangman.model.Word;

public class PlaySessionBuilder implements IPlaySessionBuilder {

    private static IDataAccess databaseAccess;
    private RuntimeExceptionDao<Player, Integer> playerDao;
    private RuntimeExceptionDao<Word, Integer> wordDao;
    private RuntimeExceptionDao<PlayerWord, Integer> playerWordDao;

    public IPlaySession buildPlaySession(String username, Context context,
	    int lifes, int coins) {
	databaseAccess = getDataAccess(context);
	playerDao = databaseAccess.getPlayerDao();
	wordDao = databaseAccess.getWordDao();
	playerWordDao = databaseAccess.getPlayerWordDao();
	Player player = null;
	Word wordToPlay = null;
	try {
	    // get the player with the username
	    player = getPlayer(username);
	    if (player == null)
		return null;
	    // get the words that the player already played
	    ArrayList<Word> wordsOfPlayer = (ArrayList<Word>) lookupWordsForPlayer(player);
	    // get the word to play
	    wordToPlay = getUnplayedWord(wordsOfPlayer);
	} catch (SQLException e) {

	}

	IPlaySession playSession = new PlaySession(player, wordToPlay, lifes,
		coins);
	return playSession;
    }

    private IDataAccess getDataAccess(Context context) {
	if (databaseAccess == null) {
	    databaseAccess = OpenHelperManager.getHelper(context,
		    DataAccessORMLite.class);
	}
	return databaseAccess;
    }

    private Player getPlayer(String username) throws SQLException {
	QueryBuilder<Player, Integer> queryBuilder = playerDao.queryBuilder();
	ArrayList<Player> playerList = null;
	queryBuilder.where().eq(Player.USERNAME_FIELD, username);
	PreparedQuery<Player> preparedQuery = queryBuilder.prepare();
	playerList = (ArrayList<Player>) playerDao.query(preparedQuery);
	return (playerList.size() != 0) ? playerList.get(0) : null;
    }

    private List<Word> lookupWordsForPlayer(Player player) throws SQLException {
	PreparedQuery<Word> wordsOfPlayerQuery = makeWordsForPlayerQuery();
	wordsOfPlayerQuery.setArgumentHolderValue(0, player);
	return wordDao.query(wordsOfPlayerQuery);
    }

    private PreparedQuery<Word> makeWordsForPlayerQuery() throws SQLException {
	// build our inner query for PlayerWord objects
	QueryBuilder<PlayerWord, Integer> playerWordPQ = playerWordDao
		.queryBuilder();
	// just select the post-id field
	playerWordPQ.selectColumns(PlayerWord.PLAYER_ID_FIELD);
	SelectArg userSelectArg = new SelectArg();
	// you could also just pass in user1 here
	playerWordPQ.where().eq(PlayerWord.PLAYER_ID_FIELD, userSelectArg);
	// build our outer query for Post objects
	QueryBuilder<Word, Integer> wordQB = wordDao.queryBuilder();
	// where the id matches in the post-id from the inner query
	wordQB.where().in(Word.ID_FIELD, playerWordPQ);
	return wordQB.prepare();
    }

    private Word getUnplayedWord(List<Word> words) throws SQLException {
	QueryBuilder<Word, Integer> wordQB = wordDao.queryBuilder();
	ArrayList<Integer> idWords = new ArrayList<Integer>();
	for (Word word : words)
	    idWords.add(word.getId());
	wordQB.where().notIn(Word.ID_FIELD, idWords);
	PreparedQuery<Word> unplayedWordsQuery = wordQB.prepare();
	ArrayList<Word> unplayedWords = (ArrayList<Word>) wordDao
		.query(unplayedWordsQuery);
	Random randomGenerator = new Random();
	return unplayedWords.get(randomGenerator.nextInt(unplayedWords.size()));

    }
}
