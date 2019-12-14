package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseOpenHandler extends SQLiteOpenHelper {
    private DbEvent dbEvent;


    public DatabaseOpenHandler(Context context, DbEvent dbEvent) {
        super(context, dbEvent.getTableName(), null, dbEvent.getVersion());
        this.dbEvent = dbEvent;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(dbEvent.createThreeColumnTable());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dbEvent.dropTable());
        onCreate(db);

    }


}// END OF CLASS
