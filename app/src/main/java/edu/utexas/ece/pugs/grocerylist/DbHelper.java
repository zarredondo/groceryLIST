package edu.utexas.ece.pugs.grocerylist;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by manuellopez on 3/19/18.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "GroceryList";
    public static final String TABLE_NAME = "Things_to_Buy";
    public static final String COLUMN_NAME = "Items";
    private static final int DB_VERSION = 1;

    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL);", TABLE_NAME, COLUMN_NAME);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = String.format("DELETE TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(query);
        onCreate(db);
    }

    public void insertNewItem(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item);
        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteItem(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_NAME + " = ?", new String[]{item});
        db.close();
    }

    public ArrayList<String> getGroceryList(){
        ArrayList<String> groceryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[]{COLUMN_NAME}, null, null, null, null, null);
        while(c.moveToNext()){
            int index = c.getColumnIndex(COLUMN_NAME);
            groceryList.add(c.getString(index));
        }
        c.close();
        db.close();
        return groceryList;
    }
}
