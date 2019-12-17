package Quiz;

import android.database.sqlite.SQLiteDatabase;

import java.util.Random;

import Database.DbEvent;

public abstract class Configuration {
    private static final String EMPTY_STRING = "";
    private SQLiteDatabase db;
    private DbEvent dbEvent;
    private int randomIndex = 1;

    // ** THIS MUST BE USED IN ORDER FOR REST OF THE CLASS TO WORK **
    protected void configDbEntry(SQLiteDatabase db, DbEvent dbEvent) {
        this.db = db;
        this.dbEvent = dbEvent;
    }


    // *** GETS The (1st) Question From The SQLite Table ***
    public String getQuestion() {
        return (dbEvent.retrieveTableData(db, dbEvent.getColumn1(), 1));
    }


    // *** (Overloaded) GETS The A Chosen Question From The SQLite Table Based On The ID Value ***
    public String getQuestion(int id) {
        return (dbEvent.retrieveTableData(db, dbEvent.getColumn1(), id));
    }

    //*** Gets A Specific Solution Based On The Matching Solution ID ***
    public String getSolution(int id) {
        if (id > 0) {
            return (dbEvent.retrieveTableData(db, dbEvent.getColumn2(), id));
        }
        return (EMPTY_STRING);
    }

    //*** Gets A Specific VideoPath Based On The Matching Table ID ***
    public int getVideoPath(int id) {
        if (id > 0) {
            return (Integer.parseInt(dbEvent.retrieveTableData(db, dbEvent.getColumn3(), id)));
        }
        return (0);
    }

    // *** GETS Random Question From The SQLite Table ***
    public String getRandomQuestion() {
        Random randomEvent = new Random();
        final int MIN = 1;
        final int MAX = dbEvent.getRowCount(db);
        randomIndex = randomEvent.nextInt(MAX) + MIN;
        //*** Returns The Random Int Value ***
        return (dbEvent.retrieveTableData(db, dbEvent.getColumn1(), randomIndex));
    }

    //*** Gets Solution From The Matching Random Question ***
    public String getRandomSolution() {
        return (dbEvent.retrieveTableData(db, dbEvent.getColumn2(), randomIndex));
    }

    //*** Gets Video Path That Matches With The Random Question (RANDOM_INDEX) ***
    public int getRandomVideoPath() {
        return (Integer.parseInt(dbEvent.retrieveTableData(db, dbEvent.getColumn3(), randomIndex)));
    }

    //*** Checks To See If The Question Is Correct Based On The Matching Input ***
    public boolean checkQuestion(String userInput) {
        if (!(userInput.isEmpty())) {// Continue
            String solution = getRandomSolution();
            //*** Returns True If User Input Matches With The Solution ***
            return (userInput.equals(solution));
        }
        return (false);
    }


}
