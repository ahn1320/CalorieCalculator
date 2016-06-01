package com.comp3617.jinanproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    static final String tableName="INTAKE";
    static final String intakeId="IntakeID";
    static final String categories="Category";
    static final String foodNumber="FoodNo";
    static final String unitCalorie="UnitCal";

    public DataBaseHelper(Context context, String name,CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase _db)
    {
        _db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE);
        _db.execSQL("CREATE TABLE "+tableName+"("+
                     intakeId+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                     categories +" TEXT, "+ foodNumber + " Integer, " +
                     unitCalorie + " Integer);");

        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Sandwich', 1, 410);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Sandwich', 2, 360);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Sandwich', 3, 490);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Sandwich', 4, 430);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Sandwich', 5, 350);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Sandwich', 6, 300);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Sandwich', 7, 330);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Sandwich', 8, 410);");

        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Beverage', 1, 0);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Beverage', 2, 70);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Beverage', 3, 0);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Beverage', 4, 240);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Beverage', 5, 260);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Beverage', 6, 250);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Beverage', 7, 110);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Beverage', 8, 250);");

        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Donut', 1, 190);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Donut', 2, 190);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Donut', 3, 190);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Donut', 4, 320);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Donut', 5, 220);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Donut', 6, 210);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Donut', 7, 240);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Donut', 8, 300);");

        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Muffin', 1, 390);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Muffin', 2, 330);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Muffin', 3, 410);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Muffin', 4, 280);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Muffin', 5, 350);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Muffin', 6, 390);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Muffin', 7, 390);");
        _db.execSQL("INSERT INTO INTAKE VALUES(null, 'Muffin', 8, 360);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        Log.w("TaskDBAdapter", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");

        _db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");

        onCreate(_db);
    }
}
