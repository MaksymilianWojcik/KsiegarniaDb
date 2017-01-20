package com.example.maks.ksiegarniaonline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by MAKS on 19.01.2017.
 */

public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context context) {
        super(context, "library.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(" +
                "id integer primary key autoincrement," +
                "login text," +
                "password text);"
        );

        if (db.isOpen()){
            Log.e("Baza danych", "utworzono / nawiazano polaczenie");
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table libraryBooks(" +
                "id integer primary key autoincrement," +
                "title text," +
                "author text" +
                "released text);"
        );

        //tutaj chyba id nie trzeba autoinkrementowac i primary key ustawiac, bo to moze miec
        //jakas relacje z tabela library books i wtedy pewne bedzie mozna pobrac title authora itp
        db.execSQL("create table myBooks(" +
                "bookid integer);"
        );
        //tak samo tutaj
        db.execSQL("create table userBooks(" +
                "userid integer," +
                "bookid integer);"
        );
    }


    public void addUser(String login, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("login", login);
        values.put("password", password);

        try {
            db.insertOrThrow("users", null, values);
            Log.e("Baza danych", "udalo sie dodac uzytokwnika");


        } catch(IllegalArgumentException e){
            Log.e("Baza danych", "Nie udalo sie dodac uzytkownika");
        }
    }

    public List<User> getUsers() {
        List<User> usersList = new ArrayList<>();

        String[] columns = {"id", "login", "password"};

        SQLiteDatabase db = getReadableDatabase();
        Cursor query = db.query("users", columns, null, null, null, null, null, null);
        while(query.moveToNext()){
            usersList.add(new User(query.getString(0), query.getString(1), query.getString(2)));
        }
        query.close();
        return usersList;
    }

    public boolean isUserinDb(String login){
        List<User> userinDb = getUsers();
        for (User user : userinDb){
            if(login.equals(user.getUserLogin())){
                Log.e("baza danych", "login zajety!");

                return true;
            }
        }
        return false;
    }





}
