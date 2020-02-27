package com.example.finalexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class MyDB{

    private MyDatabaseHelper dbHelper;

    private SQLiteDatabase database;


    public final static String CON_TABLE="Contactes"; // name of table
    public final static String CON_ID="_id"; // id
    public final static String CON_NAME="name";  // name
    public final static String CON_DATE="date";
    public final static String CON_DONE="done";

    /**
     *
     * @param context
     */
    public MyDB(Context context){
        dbHelper = new MyDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }


    public long createRecords(int id, String name,String date,int done){
        ContentValues values = new ContentValues();
        values.put(CON_ID,id);
        values.put(CON_NAME,name);
        values.put(CON_DATE,date);
        values.put(CON_DONE,done);
        return database.insert(CON_TABLE, null, values);
    }

    public void updateRecords(int id, String name, String date, int done){
        ContentValues cv = new ContentValues();
        cv.put("_id",id);
        cv.put("name",name);
        cv.put("date",date);
        cv.put("done",done);
        database.update(CON_TABLE,cv,"_id = "+id,null);
    }
    public void deleteItem(int s){
        database.delete(CON_TABLE, "_id = " + s,null);
    }

    public Cursor selectRecords() {
        String[] cols = new String[] {CON_ID,CON_NAME,CON_DATE,CON_DONE};
        Cursor mCursor = database.query(true, CON_TABLE,cols,null
                , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }

    public int getCount(){
        int count = (int) DatabaseUtils.queryNumEntries(database,CON_TABLE);
        return count;
    }
}