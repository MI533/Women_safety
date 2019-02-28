package com.example.Mi5.womensafety;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDBHandler extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PHONE = "_phone";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String query = "CREATE TABLE " +  TABLE_CONTACTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_PHONE + " TEXT" +");";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);

    }

    public void addContact(Contacts contacts)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PHONE, contacts.get_phone());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

   public void deleteContact()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);

    }

    public String getData()
    {
        String[] col=new String[]{COLUMN_ID,COLUMN_PHONE};
        SQLiteDatabase db = getWritableDatabase();
        Cursor c=db.query(TABLE_CONTACTS, col, null, null, null, null, null);
        String res="";

        int id=c.getColumnIndex(COLUMN_ID);
        int prod=c.getColumnIndex(COLUMN_PHONE);

        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
        {
            res=res+c.getString(prod)+";\n";
        }

        return res;

    }


}
