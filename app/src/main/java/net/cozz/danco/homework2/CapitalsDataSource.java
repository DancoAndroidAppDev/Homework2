package net.cozz.danco.homework2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danco on 10/26/14.
 */
public class CapitalsDataSource {

    private SQLiteDatabase database;
    private DBHandler dbHelper;
    private String[] columns = {DBHandler.KEY_ID, DBHandler.KEY_STATE, DBHandler.KEY_CAPITAL};


    public CapitalsDataSource(Context context) {
        dbHelper = new DBHandler(context);
    }


    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }


    public void close() {
        dbHelper.close();
    }


    public Capital addCapital(String stateName, String cityName) {
        Capital capital = null;
        ContentValues values = new ContentValues();
        values.put(DBHandler.KEY_STATE, stateName);
        values.put(DBHandler.KEY_CAPITAL, cityName);
        long insertId = database.insert(DBHandler.TABLE_CAPITALS, null, values);
        Cursor cursor = database.query(DBHandler.TABLE_CAPITALS, columns,
                DBHandler.KEY_ID + "=" + insertId, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            capital = new Capital(cursor);
            cursor.close();
        }

        return capital;
    }


    public List<Capital> getCapitals() {
        List<Capital> captials = new ArrayList<Capital>();

        Cursor cursor = database.query(DBHandler.TABLE_CAPITALS, columns,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Capital capital = new Capital(cursor);
            captials.add(capital);
            cursor.moveToNext();
        }

        cursor.close();

        return captials;
    }


    public Capital getCapital(String state) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DBHandler.TABLE_CAPITALS,
                columns,
                DBHandler.KEY_STATE + " =?" + state,
                null, null, null, null);
        cursor.moveToFirst();
        Capital capital = new Capital(cursor);

        return capital;
    }
}
