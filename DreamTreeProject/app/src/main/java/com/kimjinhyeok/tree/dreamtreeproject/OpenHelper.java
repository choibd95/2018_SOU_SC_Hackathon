package com.kimjinhyeok.tree.dreamtreeproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kim bo sung on 2017-10-27.
 */

public class OpenHelper extends SQLiteOpenHelper {
    final static String DBName ="bank.db";

    public OpenHelper(Context ctx){
        super(ctx, DBName,null, 1);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        String sql = "CREATE TABLE bank (minus INTEGER DEFAULT 0, division text, con text, intime text, money INTEGER, banking INTEGER ,seq INTEGER Primary Key);";
        String sql = "CREATE TABLE bank(seq INTEGER Primary Key,money INTEGER DEFAULT 0,div TEXT,con TEXT,intime TEXT,del_bool TEXT DEFAULT 'N')";
        db.execSQL(sql);


//        String insql = "INSERT INTO bank (division, con, intime, money, banking,minus) VALUES ('수입','구청 지급','2017:11:01',100000,100000,0);";
        String insql = "INSERT INTO bank(div, con, intime, money) VALUES('수입','구청 지급','17:11:01 00:00',100000)";
        db.execSQL(insql);

  //      db.execSQL("insert into text(uname) values('hong')");
 //       db.execSQL("insert into text(uname) values('lee')");





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists test");

        onCreate(db);

    }
}
