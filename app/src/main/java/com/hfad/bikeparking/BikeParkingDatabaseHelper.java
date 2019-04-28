package com.hfad.bikeparking;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BikeParkingDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "bikeparking";

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USER ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, " +
                "EMAIL TEXT, " +
                "AUTH TEXT, " +
                "STRIKES INTEGER);");
        db.execSQL("CREATE TABLE BIKE_RACK (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "LATITUDE REAL," +
                "LONGITUDE REAL," +
                "PHOTO INTEGER," +
                "ADDED_BY INTEGER," +
                "NOTES NUMERIC," +
                "FOREIGN KEY (photo) REFERENCES PHOTO(_id)," +
                "FOREIGN KEY (added_by) REFERENCES USER(_id));");
        db.execSQL("CREATE TABLE NOTES ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "RACK_ID INTEGER, " +
                "USER_ID INTEGER, " +
                "TIME NUMERIC, " +
                "NOTE TEXT, " +
                "FOREIGN KEY (rack_id) REFERENCES BIKE_RACK(_id), " +
                "FOREIGN KEY (user_id) REFERENCES USER(_id));");
        db.execSQL("CREATE TABLE NOTES (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "RACK_ID INTEGER," +
                "USER_ID INTEGER," +
                "TIME NUMERIC," +
                "SPACE_REMAINING INTEGER," +
                "FOREIGN KEY (rack_id) REFERENCES BIKE_RACK(_id)," +
                "FOREIGN KEY (user_id) REFERENCES USER(_id));");
        db.execSQL("CREATE TABLE PHOTO (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "RACK_ID INTEGER,\n" +
                "USER_ID INTEGER,\n" +
                "TIME NUMERIC,\n" +
                "PHOTO BLOB,\n" +
                "RATING INTEGER,\n" +
                "FOREIGN KEY (rack_id) REFERENCES BIKE_RACK(_id)," +
                "FOREIGN KEY (user_id) REFERENCES USER(_id));");
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
