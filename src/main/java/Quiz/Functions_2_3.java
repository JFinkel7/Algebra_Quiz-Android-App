package Quiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Random;

import Database.DatabaseOpenHandler;
import Database.DbEvent;
import QuizRepository.IFunctions_2_3_Questions;


public class Functions_2_3 implements IFunctions_2_3_Questions {
    //*****
    //
    private static final String DB_NAME = "Quiz";
    private static final String TABLE_NAME = "Functions_2_3";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_1 = "Question";
    private static final String COLUMN_2 = "Solution";
    private static final String EMPTY_STRING = "";
    private int randomIndex = 1;
    // DbDataSet
    private SQLiteDatabase db;
    private DatabaseOpenHandler dbHandler;
    private DbEvent dbEvent;
    //*****

    /******CONSTRUCTOR******/
    public Functions_2_3(Context context) {
        this.dbEvent = new DbEvent(1, DB_NAME, TABLE_NAME, COLUMN_ID, COLUMN_1, COLUMN_2);
        this.dbHandler = new DatabaseOpenHandler(context, dbEvent);
        this.db = dbHandler.getWritableDatabase();
        //*** Adding Data To The Table If None Exists ***
        if (!(dbEvent.getRowCount(db) >= 1)) {
            dbEvent.insertTwoTableData(db, QUESTION_1, SOLUTION_1);
            dbEvent.insertTwoTableData(db, QUESTION_2, SOLUTION_2);
            dbEvent.insertTwoTableData(db, QUESTION_3, SOLUTION_3);
            dbEvent.insertTwoTableData(db, QUESTION_4, SOLUTION_4);
            dbEvent.insertTwoTableData(db, QUESTION_5, SOLUTION_5);
            dbEvent.insertTwoTableData(db, QUESTION_6, SOLUTION_6);
            dbEvent.insertTwoTableData(db, QUESTION_7, SOLUTION_7);
            dbEvent.insertTwoTableData(db, QUESTION_8, SOLUTION_8);
        }
    }

    /*========================================CLASS METHODS========================================*/
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
            String solution = getRandomSolution();
            //*** Returns True If User Input Matches With The Solution ***
            return (userInput.equals(solution));
        }
        return (false);
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
