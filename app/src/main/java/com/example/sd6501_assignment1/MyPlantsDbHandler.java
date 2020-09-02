package com.example.sd6501_assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class MyPlantsDbHandler extends SQLiteOpenHelper {

    private static final String TAG = "MyPlantsDbHandler"; // Debugging for MyPlantsDbHandler.java

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "myplantsdb";
    private static final String TABLE_Plants = "growingdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_PLANT = "plantname";
    private static final String KEY_DATE_TIME_PLANTED = "datetimeplanted";
    private static final String KEY_GERM = "germinationdate";
    private static final String KEY_TRANS = "transplantdate";
    private static final String KEY_HARV = "harvestdate";

    public MyPlantsDbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d(TAG, "onCreate: Plants table created");

        String CREATE_TABLE = "CREATE TABLE " + TABLE_Plants + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_PLANT + " TEXT,"
                + KEY_DATE_TIME_PLANTED + " TEXT,"
                + KEY_GERM + " TEXT,"
                + KEY_TRANS + " TEXT,"
                + KEY_HARV + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    // Drop outdated table if it exists then create the table again
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        Log.d(TAG, "onUpgrade: Upgrade plant table");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Plants);
        onCreate(db);
    }

    // Create new record
    public void insertPlantDetails(String plantName, String dateTimePlanted, String germinationDate,
                                   String transplantDate, String harvestDate){

        Log.d(TAG, "insertPlantDetails: " + plantName + "," + dateTimePlanted + ", "
                + germinationDate + "," + transplantDate + "," + harvestDate);

        // Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values where the column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_PLANT, plantName);
        cValues.put(KEY_DATE_TIME_PLANTED, dateTimePlanted);
        cValues.put(KEY_GERM, germinationDate);
        cValues.put(KEY_TRANS, transplantDate);
        cValues.put(KEY_HARV, harvestDate);

        // Insert the new row and return the primary key of the new row
        long newRowId = db.insert(TABLE_Plants,null, cValues);

        db.close();
    }

    // Get and return the contents of the db
    public ArrayList<HashMap<String, String>> getPlants(){

        Log.d(TAG, "getPlants: Get plant records from db");

        SQLiteDatabase db = this.getWritableDatabase();

        // Creates an arrayList that contains a hashMap for the return type
        ArrayList<HashMap<String, String>> plantList = new ArrayList<>();

        // SQL query to get the info from the table
        String query = "SELECT id, plantname, dateTimePlanted, germinationDate, transplantdate, " +
                "harvestdate FROM "+ TABLE_Plants;

        // Pass the rawQuery result to a Cursor instance
        Cursor cursor = db.rawQuery(query,null);

        // Loop to collect the contents of the db, save it to a hash map, then add it to the array.
        while (cursor.moveToNext()){

            HashMap<String,String> plant = new HashMap<>();
            plant.put("id",cursor.getString(cursor.getColumnIndex(KEY_ID)));
            plant.put("plantname",cursor.getString(cursor.getColumnIndex(KEY_PLANT)));
            plant.put("datetimeplanted",cursor.getString(cursor.getColumnIndex(KEY_DATE_TIME_PLANTED)));
            plant.put("germinationdate",cursor.getString(cursor.getColumnIndex(KEY_GERM)));
            plant.put("transplantdate",cursor.getString(cursor.getColumnIndex(KEY_TRANS)));
            plant.put("harvestdate",cursor.getString(cursor.getColumnIndex(KEY_HARV)));

            plantList.add(plant);
        }
        return  plantList;
    }

    // Delete record from db
    public void deletePlant(int plantId) {

        Log.d(TAG, "deletePlant: plantId = " + plantId);

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Plants, KEY_ID+" = ?", new String[]{String.valueOf(plantId)});
        db.close();
    }

    // Update plant record
    public int updatePlantDetails(int id, String germinationDate, String transplantDate,
                                  String harvestDate){

        Log.d(TAG, "updatePlantDetails: " + id + "," + germinationDate + "," + transplantDate
                + "," + harvestDate);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_GERM, germinationDate);
        contentValues.put(KEY_TRANS, transplantDate);
        contentValues.put(KEY_HARV, harvestDate);

        int count = db.update(TABLE_Plants, contentValues, KEY_ID+" =?",
                new String[]{String.valueOf(id)});

        return count;
    }
}
