package com.unvin.simpleaccount.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createAccountSQL = "CREATE TABLE account"+
                "(idx integer primary key autoincrement,"+
                "detail text ,"+
                "flag integer ,"+    //boolean 0: 수입/ 받아야할 돈, 1: 지출/보내야할 돈
                "value int ,"+
                "datetime text,"+
                "category text)";

        String createShareSQL = "CREATE TABLE share"+
                "(idx integer primary key autoincrement,"+
                "flag integer ,"+    //boolean 0: 수입/ 받아야할 돈, 1: 지출/보내야할 돈
                "value int ,"+
                "who text,"+
                "detail text,"+
                "datetime text)";

        sqLiteDatabase.execSQL(createAccountSQL);
        sqLiteDatabase.execSQL(createShareSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public String insertAccountSQL = "insert into account(flag, value, detail, datetime, category) values (?, ?, ?, ?, ?)";
    public String selectAccountSQL = "select flag, value, detail, datetime, category from account";  // 내역 전체 불러오기
    public String selectShareSQL = "SELECT * FROM share";
    public String insertShareSQL = "INSERT INTO share (flag, value, who, detail, datetime)"+
            "VALUES (?, ?, ?, ?, ?)";
    public String selectValueOneDaySQL = "SELECT flag, value FROM account WHERE datetime LIKE ?";
    public String selectInAccountSQL = "select value, detail, datetime, category from account where flag=0";   // 수입 내역만 불러오기
    public String selectConAccountSQL = "select value, detail, datetime, category from account where flag=1";   // 지출 내역만 불러오기
    public String selectGetShareSQL = "select value from share where flag=0"; // 받을 돈
    public String selectSendShareSQL = "select value from share where flag=1"; // 보낼 돈
}
