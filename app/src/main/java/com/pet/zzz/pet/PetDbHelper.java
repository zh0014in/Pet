package com.pet.zzz.pet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zzz on 2016/7/30.
 */
public class PetDbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PetContract.PetEntry.TABLE_NAME + " (" +
                    PetContract.PetEntry._ID + " INTEGER PRIMARY KEY," +
                    PetContract.PetEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    PetContract.PetEntry.COLUMN_NAME_NAME + TEXT_TYPE +
                    " );" +
                    "CREATE TABLE " + EventContract.EventEntry.TABLE_NAME + " (" +
                    EventContract.EventEntry._ID + " INTEGER PRIMARY KEY," +
                    EventContract.EventEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    EventContract.EventEntry.COLUMN_NAME_PET_ID + TEXT_TYPE + COMMA_SEP +
                    EventContract.EventEntry.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
                    EventContract.EventEntry.COLUMN_NAME_LENGTH_BACK + TEXT_TYPE + COMMA_SEP +
                    EventContract.EventEntry.COLUMN_NAME_LENGTH_FRONT + TEXT_TYPE + COMMA_SEP +
                    " FOREIGN KEY ("+EventContract.EventEntry.COLUMN_NAME_PET_ID+") REFERENCES "+PetContract.PetEntry.TABLE_NAME+"("+PetContract.PetEntry._ID+")" +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PetContract.PetEntry.TABLE_NAME + " " +
                    "DROP TABLE IF EXISTS " + EventContract.EventEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Pet.db";

    public PetDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
