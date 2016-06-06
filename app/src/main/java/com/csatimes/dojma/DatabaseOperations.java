package com.csatimes.dojma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vikramaditya Kukreja on 23-05-2016.
 */
public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int databaseVersion = 1;

    public String CREATE_QUERY = "CREATE TABLE " +
            TableData.TableInfo.TABLE_NAME + "(" +
            TableData.TableInfo.tablePostID + " TEXT PRIMARY KEY," +
            TableData.TableInfo.tableTitle + " TEXT," +
            TableData.TableInfo.tableAuthor + " TEXT," +
            TableData.TableInfo.tableDate + " TEXT," +
            TableData.TableInfo.tableUpdateDate + " TEXT," +
            TableData.TableInfo.tableImageURL + " TEXT," +
            TableData.TableInfo.tableURL + " TEXT," +
            TableData.TableInfo.tableImageSavedLoc + " TEXT );";

    public DatabaseOperations(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, databaseVersion);
    }

    public void insertRow(Herald.HeraldItemObject input) {
        SQLiteDatabase sdb = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.tablePostID, input.postID);
        cv.put(TableData.TableInfo.tableTitle, input.title);
        cv.put(TableData.TableInfo.tableAuthor, input.author);
        cv.put(TableData.TableInfo.tableDate, input.date.charAt(8) + input.date.charAt(9) + input
                .date.charAt(5) + input.date.charAt(6) + input.date.substring(0, 4));
        cv.put(TableData.TableInfo.tableUpdateDate, "update");
        cv.put(TableData.TableInfo.tableImageURL, input.imageURL);
        cv.put(TableData.TableInfo.tableImageSavedLoc, Herald.ROOT_DIRECTORY + "/" + Herald
                .dojmaFolderName + "/" + input.postID + ".jpeg");

        sdb.insert(TableData.TableInfo.TABLE_NAME, null, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(CREATE_QUERY);
    }

    public void insertRow(String postid, String title, String postURL, String date, String updateDate, String
            author, String imageurl) {
        SQLiteDatabase sdb = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.tablePostID, postid);
        cv.put(TableData.TableInfo.tableAuthor, author);
        cv.put(TableData.TableInfo.tableTitle, title);
        cv.put(TableData.TableInfo.tableImageURL, imageurl);
        cv.put(TableData.TableInfo.tableUpdateDate, updateDate);
        cv.put(TableData.TableInfo.tableDate, date);
        cv.put(TableData.TableInfo.tableURL, postURL);
        cv.put(TableData.TableInfo.tableImageSavedLoc, Herald.ROOT_DIRECTORY + "/" + Herald
                .dojmaFolderName + "/" + postid + ".jpeg");
        sdb.insert(TableData.TableInfo.TABLE_NAME, null, cv);
    }

    public Cursor getInformation() {
        SQLiteDatabase sdb = getReadableDatabase();
        String[] columns = {TableData.TableInfo.tablePostID,
                TableData.TableInfo.tableAuthor,
                TableData.TableInfo.tableTitle,
                TableData.TableInfo.tableImageURL,
                TableData.TableInfo.tableUpdateDate,
                TableData.TableInfo.tableDate,
                TableData.TableInfo.tableURL,
                TableData.TableInfo.tableImageSavedLoc};

        return sdb.query(TableData.TableInfo.TABLE_NAME, columns, null, null, null, null, null);

    }
}
