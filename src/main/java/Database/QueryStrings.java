package Database;

public interface QueryStrings {

    //** TABLE **/
    String CREATE_ONE_COLUMN_TABLE = "CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s NVARCHAR(250));";
    String CREATE_TWO_COLUMN_TABLE = "CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s NVARCHAR(250), %s NVARCHAR(250));";
    String DROP_TABLE = "DROP TABLE IF EXISTS %s ";

    //** SELECT **/
    String SELECT_ALL = "SELECT * FROM %s; ";
    String SELECT_NEXT_ID = "SELECT ID FROM %s, WHERE ID = %s";
    String SELECT_ITEM_FROM_TABLE = "SELECT %s FROM %s;";
    String SELECT_TABLE_WHERE_ITEM_EXISTS = "SELECT %s FROM %s WHERE %s = %s;";
    String GET_ROW_COUNT = "SELECT Count(*) FROM %s";
}
