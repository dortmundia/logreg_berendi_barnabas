package com.example.logreg_berendi_barnabas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBhelper extends SQLiteOpenHelper{
    public static  final  int DB_VERSION =1;
    public static  final  String DB_NAME ="logreg.db";

    public static  final  String FELHASZNALO_TABLE ="users";

    public static  final  String COL_ID ="id";
    public static  final  String COL_NEV ="nev"; //teljesnev
    public static  final  String COL_NICK ="nickname"; //felhasználónév
    public static  final  String COL_EMAIL="email";
    public static  final  String COL_PW ="jelszo";


    public DBhelper(Context context)
    {
        super(context,
                DB_NAME,
                null,
                DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql="CREATE TABLE IF NOT EXISTS " + FELHASZNALO_TABLE +" ( "+
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EMAIL + " VARCHAR(255) NOT NULL, " +
                COL_NEV + " VARCHAR(255) NOT NULL, " +
                COL_NICK + " VARCHAR(255) NOT NULL, " +
                COL_PW + " VARCHAR(235) NOT NULL )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }


    //id csere
    public boolean emailEllenorzo(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT *FROM "+ FELHASZNALO_TABLE+" WHERE  "+ COL_EMAIL+" = ?",new String[]{email} );
        return result.getCount()==1;
    }
    public boolean nickEllenorzo(String nick)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT *FROM "+ FELHASZNALO_TABLE+" WHERE  "+ COL_NICK+" = ?",new String[]{nick} );
        return result.getCount()==1;
    }


    public boolean pwEllenorzo(String nick)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT *FROM "+ FELHASZNALO_TABLE+" WHERE  "+ COL_PW+" = ?",new String[]{nick} );
        return result.getCount()==1;
    }



    public boolean nevEllenorzo(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT *FROM "+ FELHASZNALO_TABLE+" WHERE  "+ COL_NEV+" = ? " ,new String[]{name} );
        return result.getCount()==1;
    }


    public boolean logWNickEllenorzo(String nick, String pw)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT *FROM "+ FELHASZNALO_TABLE+" WHERE  "+ COL_NICK+" = ? AND "+COL_PW+"=? ",new String[]{nick,pw} );
        return result.getCount()==1;
    }
    public boolean logWeMailEllenorzo(String email, String pw)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT *FROM "+ FELHASZNALO_TABLE+" WHERE  "+ COL_EMAIL+" = ? AND "+COL_PW+"=? ",new String[]{email,pw} );
        return result.getCount()==1;
    }

    public boolean adatRogzites(String nev, String nick, String email, String jelszo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NEV, nev);
        values.put(COL_NICK, nick);
        values.put(COL_EMAIL, email);
        values.put(COL_PW, jelszo);
        return db.insert(FELHASZNALO_TABLE,null,values)!=-1;
}
