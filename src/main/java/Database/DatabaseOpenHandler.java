package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseOpenHandler extends SQLiteOpenHelper {
    private DbEvent dbContext;


    public DatabaseOpenHandler(Context context, DbEvent dbContext) {
        super(context, dbContext.getTableName(), null, dbContext.getVersion());
        this.dbContext = dbContext;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(dbContext.createTwoColumnTable());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dbContext.dropTable());
        onCreate(db);

    }


}// END OF CLASS
