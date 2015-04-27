package com.utn.architecture.hangman.ui;

import java.util.ArrayList;
import java.util.LinkedList;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hangman.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.utn.architecture.hangman.data.access.DataAccessORMLite;
import com.utn.architecture.hangman.data.access.IDataAccess;
import com.utn.architecture.hangman.managers.PlaySession;

public class StartActivity extends Activity {

    private GridView gvLetters;
    private GridView palabra;
    private IDataAccess databaseAccess = null;
    private static Typeface typeFace;
    private LinkedList<TextView> lettersList;
    private TextView previousLetter;
    private final ArrayList<Letter> word = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        typeFace = Typeface.createFromAsset(getAssets(), "font/font.ttf");

        gvLetters = (GridView) findViewById(R.id.gvLetters);

        gvLetters.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, PlaySession
                        .getLettersArray()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view
                        .findViewById(android.R.id.text1);
                text.setTypeface(typeFace, Typeface.BOLD);
                text.setTextColor(Color.BLUE);
                return view;
            }
        });

        gvLetters.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                analizeWord((String) ((TextView) view).getText());
            }
        });

        this.getActionBar().hide();
        this.checkScreenRotation();

        this.createWord();
        
        // this.databaseAccess = getDataAccess();
        // RuntimeExceptionDao<Word, Integer> wordDao = databaseAccess
        // .getWordDao();

    }

    private void analizeWord(final String letter) {
        palabra.setAdapter(new ArrayAdapter<Letter>(this,
                android.R.layout.simple_list_item_1, word) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view
                        .findViewById(android.R.id.text1);
                text.setText(word.get(position).getLetter());
                text.setTypeface(null, Typeface.BOLD);
                if (letter.equals(text.getText()) || word.get(position).isVisible()) {
                    text.setVisibility(View.VISIBLE);
                    text.setTextColor(Color.BLACK);
                    word.get(position).setVisible(true);
                } else {
                    text.setVisibility(View.INVISIBLE);
                }
                return view;
            }
        });
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

    private void checkScreenRotation() {
        Resources res = getResources();
        Drawable portrait = res.getDrawable(R.drawable.portrait_page);
        Drawable landscape = res.getDrawable(R.drawable.landscape_page);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.background_img);

        WindowManager window = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = window.getDefaultDisplay();
        int num = display.getRotation();
        if (num == 0) {
            layout.setBackground(portrait);
        } else if (num == 1 || num == 3) {
            layout.setBackground(landscape);
        } else {
            layout.setBackground(portrait);
        }
    }

    public void createWord() {
        String[] wor = { "A", "R", "Q", "U", "I", "T", "E", "C", "T", "U", "R",
                "A" };

        for (String letter : wor) {
            Letter letra = new Letter(letter, false);
            word.add(letra);
        }

        RelativeLayout rl = new RelativeLayout(this);
        RelativeLayout.LayoutParams lprl = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lprl.addRule(RelativeLayout.ABOVE, R.id.gvLetters);

        rl.setLayoutParams(lprl);
        
        palabra = new GridView(this);
        palabra.setNumColumns(12);
        
        palabra.setAdapter(new ArrayAdapter<Letter>(this,
                android.R.layout.simple_list_item_1, word) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view
                        .findViewById(android.R.id.text1);
                text.setText(word.get(position).getLetter());
                text.setTypeface(null, Typeface.BOLD);
                text.setTextColor(Color.BLACK);
                text.setVisibility(View.INVISIBLE);
                return view;
            }
        });
        

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.background_img);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ABOVE, R.id.gvLetters);
        palabra.setLayoutParams(params);
        rl.addView(palabra);
        layout.addView(rl);
    }
}

class Letter {
    private String letter;
    private boolean isVisible;

    public Letter(String letter2, boolean b) {
        letter = letter2;
        isVisible = b;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
