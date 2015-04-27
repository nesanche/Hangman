package com.utn.architecture.hangman.multiplayer;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class HangmanTurn {

    public static final String TAG = "HangmanTurn";

    public String data = "";
    public int turnCounter;

    public HangmanTurn() {

    }

    // This is the byte array we will write out to the TBMP API.
    public byte[] persist() {
        JSONObject retVal = new JSONObject();

        try {
            retVal.put("data", data);
            retVal.put("turnCounter", turnCounter);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String st = retVal.toString();

        Log.d(TAG, "==== PERSISTING\n" + st);

        return st.getBytes(Charset.forName("UTF-8"));
    }

    // Creates a new instance of Hangman.
    static public HangmanTurn unpersist(byte[] byteArray) {

        if (byteArray == null) {
            Log.d(TAG, "Empty array---possible bug.");
            return new HangmanTurn();
        }

        String st = null;
        try {
            st = new String(byteArray, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
            return null;
        }

        Log.d(TAG, "====UNPERSIST \n" + st);

        HangmanTurn retVal = new HangmanTurn();

        try {
            JSONObject obj = new JSONObject(st);

            if (obj.has("data")) {
                retVal.data = obj.getString("data");
            }
            if (obj.has("turnCounter")) {
                retVal.turnCounter = obj.getInt("turnCounter");
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return retVal;
    }
}
