package com.comp3617.jinanproject;

import java.sql.SQLException;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class LoginDataBaseAdapter {
    static final String DATABASE_NAME = "login.db";

    static final int DATABASE_VERSION = 1;

    public static final int NAME_COLUMN = 1;

    // SQL Statement to create a new database.
    static final String DATABASE_CREATE = "create table " + "LOGIN" + "( "
            + "ID" + " integer primary key autoincrement,"
            + "USERNAME  text,PASSWORD text); ";

    static final String INTAKE_CREATE = "create table " + "INTAKE" + "( "
            + "IntakeID" + " integer primary key autoincrement,"
            + "Category text,FoodNo integer,UnitCal integer); ";

    // Variable to hold the database instance
    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private final DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }

    // Method to open the Database
    public LoginDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public LoginDataBaseAdapter openRead() throws SQLException {
        db = dbHelper.getReadableDatabase();
        return this;
    }

    // Method to close the Database
    public void close() {
        db.close();
    }

    // method returns an Instance of the Database
    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    // method to insert a record in Table
    public void insertEntry(String userName, String password) {
        ContentValues newValues = new ContentValues();
        // Assign values for each column.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD", password);

        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        Toast.makeText(context, "User Info Saved", Toast.LENGTH_LONG).show();
    }

    // method to delete a Record of UserName
    public int deleteEntry(String UserName) {

        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("LOGIN", where,
                new String[] { UserName });
        Toast.makeText(
                context,
                "Number fo Entry Deleted Successfully : "
                        + numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;

    }

    // method to get the password of userName
    public String getSinlgeEntry(String userName) {
        Cursor cursor = db.query("LOGIN", null, " USERNAME=?",
                new String[] { userName }, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
            return "NOT EXIST";
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        return password;
    }

    // Method to Update an Existing Record
    public void updateEntry(String userName, String password) {
        // create object of ContentValues
        ContentValues updatedValues = new ContentValues();
        // Assign values for each Column.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);

        String where = "USERNAME = ?";
        db.update("LOGIN", updatedValues, where, new String[] { userName });
    }

    public ArrayList<String> getAll() {
        ArrayList<String> allNames = new ArrayList<String>();

        Cursor cursor = db.rawQuery("select EXERCISETYPE from EXERCISE", null);

        if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {
                String firstName = cursor.getString(cursor
                        .getColumnIndex("EXERCISETYPE"));

                allNames.add(firstName);
                cursor.moveToNext();
            }
        }
        return allNames;
    }

    public int getIntakeCal(String category, int foodNo) {
        int intakeUnitCal = 0;
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT UnitCal FROM INTAKE "
                + "WHERE Category ='" + category + "'AND   FoodNo ='" + foodNo
                + "'", null);
        if (cursor != null && cursor.moveToFirst())
            intakeUnitCal = cursor.getInt(cursor.getColumnIndex("UnitCal"));
        cursor.close();
        return intakeUnitCal;
    }

}
