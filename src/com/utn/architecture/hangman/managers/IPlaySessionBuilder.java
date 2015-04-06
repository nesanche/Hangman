package com.utn.architecture.hangman.managers;

import java.sql.SQLException;

import android.content.Context;

public interface IPlaySessionBuilder {

    IPlaySession buildPlaySession(String username, Context context, int lifes,
	    int coins) throws SQLException;
}
