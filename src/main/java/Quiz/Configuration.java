package Quiz;

import android.database.sqlite.SQLiteDatabase;

import java.util.Random;

import Database.DbEvent;

public abstract class Configuration {
    private static final String EMPTY_STRING = "";
    private SQLiteDatabase db;
    private DbEvent dbEvent;
    private int randomIndex = 1;

    public Configuration(SQLiteDatabase db, DbEvent dbEvent) {
        this.db = db;
        this.dbEvent = dbEvent;
    }

    // *** GETS The (1st) Question From The SQLite Table ***
    public String getQuestion() {
        return (dbEvent.retrieveTableData(db, dbEvent.getColumn1(), 1));
    }

    /*** (INFO) ADDED METHOD ***/
    // *** (Overloaded) GETS The A Chosen Question From The SQLite Table Based On The ID Value ***
    public String getQuestion(final int ID) {
        return (dbEvent.retrieveTableData(db, dbEvent.getColumn1(), ID));
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

    //*** Checks To See If The Question Is Correct Based On The Matching Input ***
    public boolean checkQuestion(String userInput) {
        if (!(userInput.isEmpty())) {// Continue
            //*** Returns True If User Input Matches With The Solution ***
            return (userInput.equals(getRandomSolution()));
        }
        return (false);
    }

    /*** (INFO) ADDED METHOD ***/
    public int getRandomVideoPath() {
        return (Integer.parseInt(dbEvent.retrieveTableData(db, dbEvent.getColumn3(), randomIndex)));
    }

    /*** (INFO) ADDED METHOD ***/
    //*** Gets A Specific Solution Based On The Matching Solution ID ***
    public String getSolution(int Id) {
        if (Id > 0) {
            return (dbEvent.retrieveTableData(db, dbEvent.getColumn2(), Id));
        }
        return (EMPTY_STRING);
    }
}
