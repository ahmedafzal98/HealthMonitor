package com.example.tablayoutdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class sqliteDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "register_db";
    public static final String TABLE_NAME = "register_tbl";
    public static final String COL_1= "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "AGE";
    public static final String COL_4 = "HEIGHT";
    public static final String COL_5 = "WEIGHT";


    public static final String TABLE_HEALTH_NAME = "common_health_tbl";
    public static final String COL_ID= "C_ID";
    public static final String COL_HEALTH_NAME = "HEALTH_NAME";
    public static final String COL_INFO = "INFO";

    public static final String TABLE_HEALTH_DETAILS = "health_details";
    public static final String COL_ID2  = "C_ID2";
    public static final String BLOOD_PRESSURE  = "BP";
    public static final String BLOOD_SUGAR_PRESSURE  = "BS";
    public static final String CALORIE_INTAKE  = "CI";
    public static final String DATE  = "CURRENTDATE";

    public sqliteDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 11);
        SQLiteDatabase db = this.getWritableDatabase();

    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT , AGE INTEGER , HEIGHT TEXT , WEIGHT TEXT )");
        // insertData("zz",10,"a","aa");
        db.execSQL("create table " + TABLE_HEALTH_NAME + "(C_ID INTEGER PRIMARY KEY AUTOINCREMENT , HEALTH_NAME TEXT , INFO TEXT)");
        db.execSQL("create table " + TABLE_HEALTH_DETAILS  + "(C_ID2 INTEGER PRIMARY KEY AUTOINCREMENT , BP TEXT , BS TEXT , CI TEXT , CURRENTDATE TEXT)");
//        db.execSQL("DELETE FROM " + TABLE_HEALTH_DETAILS );

    }
    public boolean insertData(String name , int age , String height , String weight){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2 , name);
        contentValues.put(COL_3 , age);
        contentValues.put(COL_4 , height);
        contentValues.put(COL_5 , weight);
       long result = db.insert(TABLE_NAME , null , contentValues);

       if (result == -1){

           return false;

       }else {

           return true;
       }

    }

    public boolean insertList(String health_name , String info){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_HEALTH_NAME , health_name);
        contentValues.put(COL_INFO , info);
       long result = db.insert(TABLE_HEALTH_NAME , null , contentValues);

       if (result == -1){

           return false;

       }else {

           return true;
       }

    }
    public boolean insertDetails(int blood_press , int blood_sugar , int calorie_intake){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BLOOD_PRESSURE , blood_press);
        contentValues.put(BLOOD_SUGAR_PRESSURE , blood_sugar);
        contentValues.put(CALORIE_INTAKE , calorie_intake);
//        contentValues.put(DATE , current_date);
       long result = db.insert(TABLE_HEALTH_DETAILS , null , contentValues);

       if (result == -1){
           return false;
       }else {
           return true;
       }
    }

    public boolean updateData(String id , String name , String age , String height, String weight){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1 , id);
        contentValues.put(COL_2 , name);
        contentValues.put(COL_3 , age);
        contentValues.put(COL_4 , height);
        contentValues.put(COL_5 , weight);
        db.update(TABLE_NAME , contentValues , "ID = ?" , new String[]{id});
        return true;
    }

    public Cursor getGraphData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_HEALTH_DETAILS,null);
        return res;
    }
    public void deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HEALTH_DETAILS,null,null);
    }

    public Cursor getListName(){
        SQLiteDatabase db = this.getWritableDatabase();
       Cursor res = db.rawQuery("select * from " + TABLE_HEALTH_NAME,null);
       return res;
    }
    public Cursor getAllDAta(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        // insertDAra
        return res;
    }
    public void clearData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HEALTH_NAME,null,null);
    }

    public Cursor getRow(String data){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " WHERE " + COL_1  + " = " + data  ,null);
        // insertDAra
        return res;

        // select * from registration_tbl  where id =

    }


}
