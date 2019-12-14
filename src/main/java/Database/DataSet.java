package Database;

public abstract class DataSet {

    private int version;
    private String databaseName;
    private String tableName;
    private String ID;
    private String column1;
    private String column2;
    private String column3;

    /***** Added Retrieve 1.3 *****/
    //*** Sets All Items ***
    // Constructor | 1 Column Table
    public DataSet(int version, String databaseName, String tableName, String ID, String column1) {
        this.setVersion(version);
        this.setDatabaseName(databaseName);
        this.setID(ID);
        this.setTableName(tableName);
        this.setColumn1(column1);
    }

    /***Overloaded Constructor | 2 Column Table***/
    DataSet(int version, String databaseName, String tableName, String ID, String column1, String column2) {
        this.setVersion(version);
        this.setDatabaseName(databaseName);
        this.setID(ID);
        this.setTableName(tableName);
        this.setColumn1(column1);
        this.setColumn2(column2);
    }

    /***Overloaded Constructor | 3 Column Table***/
    DataSet(int version, String databaseName, String tableName, String ID, String column1, String column2, String column3) {
        this.setVersion(version);
        this.setDatabaseName(databaseName);
        this.setID(ID);
        this.setTableName(tableName);
        this.setColumn1(column1);
        this.setColumn2(column2);
        this.setColumn3(column3);
    }

    /***Default Constructor***/
    public DataSet() {
    }


    /**** SETTERS ****/
    public void setVersion(int version) {
        this.version = version;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }


    /**** GETTERS ****/

    public int getVersion() {
        return version;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getID() {
        return ID;
    }

    public String getColumn1() {
        return column1;
    }

    public String getColumn2() {
        return column2;
    }

    public String getColumn3() {
        return column3;
    }

}


