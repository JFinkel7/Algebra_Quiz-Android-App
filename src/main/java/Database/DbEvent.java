package Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/****** ADDED THIS CLASS IN Retrieve 1.3 ******/
public class DbEvent extends DataSet implements QueryStrings {
    //private ContentValues values = new ContentValues();
    //private Cursor cursor;
    private static final String RETRIEVE_TABLE_DATA_ERROR = "Null Pointer Thrown At RetrieveTableData";

    // Creates 1 Column DbDataSet
    public DbEvent(int version, String databaseName, String tableName, String ID, String column1) {
        super(version, databaseName, tableName, ID, column1);
    }

    // Creates 2 Column DbDataSet
    public DbEvent(int version, String databaseName, String tableName, String ID, String column1, String column2) {
        super(version, databaseName, tableName, ID, column1, column2);
    }

    // Creates 3 Column DbDataSet
    public DbEvent(int version, String databaseName, String tableName, String ID, String column1, String column2, String column3) {
        super(version, databaseName, tableName, ID, column1, column2, column3);
    }
    
    public DbEvent(){}


    // <======================== ONE TABLE ========================>
    public String createOneColumnTable() {
        return (String.format(CREATE_ONE_COLUMN_TABLE, getTableName(), getID(), getColumn1()));

    }

    // *** INSERTING INTO TABLES *** ↓
    public boolean insertOneTableData(SQLiteDatabase db, String content1) {
        ContentValues values = new ContentValues();
        values.put(getColumn1(), content1);
        long row = db.insertOrThrow(getTableName(), null, values);
        return (row != (-1));
    }

    // <======================== TWO TABLE ========================>
    // *** CREATING TABLES *** ↓
    public String createTwoColumnTable() {
        return (String.format(CREATE_TWO_COLUMN_TABLE, getTableName(), getID(), getColumn1(), getColumn2()));
    }

    // *** INSERTING INTO TABLES *** ↓
    public boolean insertTwoTableData(SQLiteDatabase db, String content1, String content2) {
        ContentValues values = new ContentValues();
        values.put(getColumn1(), content1);
        values.put(getColumn2(), content2);
        long row = db.insertOrThrow(getTableName(), null, values);
        return (row != (-1));
    }

    // *** RETRIEVING TABLES DATA *** ↓
    public String retrieveTableData(SQLiteDatabase db, String chosenColumn, int rowID) throws NullPointerException {
        Cursor result = db.rawQuery(String.format(SELECT_TABLE_WHERE_ITEM_EXISTS, chosenColumn, getTableName(), getID(), rowID), null);
        if (result != null) {
            while (result.moveToNext()) {
                // Returns 0 If Column Exists | Returns -1 If It Does'nt
                int columnIndex = result.getColumnIndex(chosenColumn);
                if (columnIndex != (-1)) {
                    return (result.getString(columnIndex));
                }
            }
            result.close();
        } else {
            throw new NullPointerException(RETRIEVE_TABLE_DATA_ERROR);
        }
        return (null);
    }

    // <======================== TABLE FUNCTIONALITY ========================>
    public String dropTable() {
        return (String.format(DROP_TABLE, getTableName()));
    }


    public int getColumnCount(SQLiteDatabase db) {
        Cursor result = db.rawQuery(String.format(SELECT_ALL, getTableName()), null);
        if (result != null) {
            while (result.moveToNext()) {
                // Returns 0 If Column Exists | Returns -1 If It Does'nt
                int columnCount = result.getColumnCount();
                if (columnCount != (-1)) {
                    return (columnCount);
                }
            }
            result.close();
        }return 0;
    }

    public int getRowCount(SQLiteDatabase db) {
        Cursor result = db.rawQuery(String.format(SELECT_ALL, getTableName()), null);
        if (result != null) {
            while (result.moveToNext()) {
                // Returns 0 If Column Exists | Returns -1 If It Does'nt
                int rowCount = result.getCount();
                if (rowCount != (-1)) {
                    return (rowCount);
                }
            }
            result.close();
        }return 0;
    }
}// END OF CLASS
