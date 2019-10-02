package com.AddHashtags.MyHashtags;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableName = "MYTAGLIST";
        String sql = "create table if not exists " + tableName + "(_no integer PRIMARY KEY autoincrement, tagName text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > 1) {
            String tableName = "MYTAGLIST";
            db.execSQL("drop table if exists " + tableName);

            String sql = "create table if not exists " + tableName + "(_no integer PRIMARY KEY autoincrement, tagName text)";
            db.execSQL(sql);
        }
    }

    public SQLiteDatabase openDatabase(Context context, String databaseName) {
        DatabaseHelper helper = new DatabaseHelper(context, databaseName, null, 3);

        SQLiteDatabase database = helper.getWritableDatabase();

        return database;
    }

    public void createTable(SQLiteDatabase database, String tableName) {
        Log.d("createTable()", "createTable()");
        if (database != null) {
            String sql = "create table if not exists " + tableName + "(_no integer PRIMARY KEY autoincrement, tagName text)";
            database.execSQL(sql);
        }
    }

    public void insertData(SQLiteDatabase database, String tagName) {
        if (database != null) {
            String sql = "insert into MYTAGLIST(tagName) values(?)";
            Object[] params = {tagName};
            database.execSQL(sql, params);
        }
    }

    public void deleteData(SQLiteDatabase database, String tagName){
        if(database != null){
            String sql = "delete from MYTAGLIST where tagName =" + "'" + tagName + "'" ;
            database.execSQL(sql);
        }
    }

    public ArrayList<String> selectData(SQLiteDatabase database, String tableName) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (database != null) {
            String sql = "select tagName from " + tableName;
            Cursor cursor = database.rawQuery(sql, null);
            Log.d("조회된 데이터개수 :", Integer.toString(cursor.getCount()));

            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String tagName = cursor.getString(0);
                arrayList.add(tagName);
                Log.d("조회된 데이터 : ", tagName);
            }
            cursor.close();
        }
        return arrayList;
    }
}
