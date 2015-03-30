package com.utn.architecture.hangman.ui;

import android.app.Activity;
import android.os.Bundle;

import com.example.hangman.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.utn.architecture.hangman.data.access.DataAccessORMLite;
import com.utn.architecture.hangman.data.access.IDataAccess;
import com.utn.architecture.hangman.model.Word;

public class StartActivity extends Activity {

    private IDataAccess databaseAccess = null;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_start);
	this.databaseAccess = getDataAccess();
	RuntimeExceptionDao<Word, Integer> wordDao = (RuntimeExceptionDao<Word, Integer>) databaseAccess
		.getObjectDao(Word.class);

    }

    private IDataAccess getDataAccess() {
	if (databaseAccess == null) {
	    databaseAccess = OpenHelperManager.getHelper(this,
		    DataAccessORMLite.class);
	}
	return databaseAccess;
    }

    @Override
    protected void onDestroy() {
	super.onDestroy();
	if (databaseAccess != null
		&& databaseAccess instanceof DataAccessORMLite) {
	    OpenHelperManager.releaseHelper();
	    databaseAccess = null;
	}
    }

}
