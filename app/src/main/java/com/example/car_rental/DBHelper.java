package com.example.car_rental;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "USERS.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(email Text primary key, username Text, license Text , phone Text , DOB Text ,password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String email, String username, String license, String phone, String DOB, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("username", username);
        contentValues.put("license", license);
        contentValues.put("phone", phone);
        contentValues.put("DOB", DOB);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean Checkemailpassword(String em, String pwd) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ? and password = ?", new String[]{em, pwd});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;


    }

    public String retrieveusername(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select username from users where email = ?", new String[]{em});
        String username="";
        if (cursor.moveToLast()) {
            username = cursor.getString(0);
        }
        return username;
    }
    public String retrievelicense(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select license from users where email = ?", new String[]{em});
        String lic="";
        if (cursor.moveToLast()) {
            lic = cursor.getString(0);
        }
        return lic;
    }
    public String retrievephone(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select phone from users where email = ?", new String[]{em});
        String phone="";
        if (cursor.moveToLast()) {
            phone = cursor.getString(0);
        }
        return phone;
    }
    public String retrieveDOB(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select DOB from users where email = ?", new String[]{em});
        String dob="";
        if (cursor.moveToLast()) {
            dob = cursor.getString(0);
        }
        return dob;
    }
}

