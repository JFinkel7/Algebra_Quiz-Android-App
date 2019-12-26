package Quiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import Database.DatabaseOpenHandler;
import Database.DbEvent;
import QuizRepository.IFunctions_2_3Questions;


public class Functions_2_3 extends Configuration implements IFunctions_2_3Questions {
    //*****
    private static final String DB_NAME = "Quiz";
    private static final String TABLE_NAME = "Functions_2_3";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_1 = "Question";
    private static final String COLUMN_2 = "Solution";
    private static final String COLUMN_3 = "VideoPath";
    //*****

    /******CONSTRUCTOR******/
    public Functions_2_3(Context context) {
        DbEvent dbEvent = new DbEvent(1, DB_NAME, TABLE_NAME, COLUMN_ID, COLUMN_1, COLUMN_2, COLUMN_3);
        DatabaseOpenHandler dbHandler = new DatabaseOpenHandler(context, dbEvent);
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        super.configDbEntry(db, dbEvent);
        //*** Adding Data To The Table If None Exists ***
        if (!(dbEvent.getRowCount(db) >= 1)) {
            dbEvent.insertThreeTableData(db, QUESTION_1, SOLUTION_1, QUESTION_1_VIDEO_PATH);
            dbEvent.insertThreeTableData(db, QUESTION_2, SOLUTION_2, QUESTION_2_VIDEO_PATH);
            dbEvent.insertThreeTableData(db, QUESTION_3, SOLUTION_3, QUESTION_3_VIDEO_PATH);
            dbEvent.insertThreeTableData(db, QUESTION_4, SOLUTION_4, QUESTION_4_VIDEO_PATH);
            dbEvent.insertThreeTableData(db, QUESTION_5, SOLUTION_5, QUESTION_5_VIDEO_PATH);
            dbEvent.insertThreeTableData(db, QUESTION_6, SOLUTION_6, QUESTION_6_VIDEO_PATH);
            dbEvent.insertThreeTableData(db, QUESTION_7, SOLUTION_7, QUESTION_7_VIDEO_PATH);
            dbEvent.insertThreeTableData(db, QUESTION_8, SOLUTION_8, QUESTION_8_VIDEO_PATH);
        }
    }
}//END OF CLASS
