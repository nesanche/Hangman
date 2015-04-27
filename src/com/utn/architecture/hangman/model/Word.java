package com.utn.architecture.hangman.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.utn.architecture.hangman.data.access.DataConstants;

@DatabaseTable(tableName = DataConstants.TABLE_WORD_NAME)
public class Word {

    public static final String ID_FIELD = "id_word";

    @DatabaseField(generatedId = true, columnName = ID_FIELD, useGetSet = true, dataType = DataType.INTEGER)
    private int id;
    @DatabaseField(useGetSet = true, canBeNull = false, dataType = DataType.STRING)
    private String word;
    @DatabaseField(useGetSet = true, canBeNull = false, dataType = DataType.INTEGER)
    private int length;
    @DatabaseField(useGetSet = true, canBeNull = false, dataType = DataType.INTEGER)
    private int difficulty;

    public Word() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    private class DifficultyCalculator implements IDifficultyCalculator {

        public float calculateDifficulty(String word) {
            Map<Character, Integer> numberOfChars = new HashMap<Character, Integer>();

            for (int i = 0; i < word.length(); ++i) {
                char charAt = word.charAt(i);

                if (!numberOfChars.containsKey(charAt)) {
                    numberOfChars.put(charAt, 1);
                } else {
                    numberOfChars.put(charAt, numberOfChars.get(charAt) + 1);
                }
            }
            Iterator<Entry<Character, Integer>> iterator = numberOfChars
                    .entrySet().iterator();
            Properties properties = new Properties();
            try {
                InputStream inputStream = getClass().getClassLoader()
                        .getResourceAsStream("frequencies_es.properties");
                properties.load(inputStream);
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
                return -1;
            }
            float acum = 0;
            int differentChars = numberOfChars.size();
            while (iterator.hasNext()) {
                Map.Entry<Character, Integer> pair = (Map.Entry<Character, Integer>) iterator
                        .next();
                String num = properties.getProperty(pair.getKey() + "");
                acum += 1 / Float.parseFloat(num) * pair.getValue();
                iterator.remove();
            }
            return 10 / (differentChars / (acum * word.length()));
        }

        private Properties getProperties(String fileName) throws IOException {
            Properties properties = new Properties();
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream(fileName);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + fileName
                        + "' not found in the classpath");
            }
            return properties;
        }
    }

}
