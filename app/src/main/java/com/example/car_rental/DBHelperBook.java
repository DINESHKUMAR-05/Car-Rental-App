package com.example.car_rental;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperBook extends SQLiteOpenHelper {

    public DBHelperBook(Context context) {
        super(context, "BOOK.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table booking(email Text, car Text, type Text ,cost Text,pdate Text ,ptime Text,rdate Text,rtime Text,dfname Text,dlname Text,license Text,phone Text,plocation Text,dlocation Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists booking");
    }
    public Boolean insertBookingData(String email, String car,String type, String cost,String pdate, String ptime,String rdate, String rtime,String dfname, String dlname, String lic,String phno,String plocation,String dlocation){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("car",car);
        contentValues.put("type", type);
        contentValues.put("cost", cost);
        contentValues.put("pdate", pdate);
        contentValues.put("ptime",ptime);
        contentValues.put("rdate",rdate);
        contentValues.put("rtime", rtime);
        contentValues.put("dfname", dfname);
        contentValues.put("dlname", dlname);
        contentValues.put("license",lic);
        contentValues.put("phone",phno);
        contentValues.put("plocation",plocation);
        contentValues.put("dlocation",dlocation);

        long result = MyDB.insert("booking", null, contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public String retrievecar(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select car from booking where email = ?", new String[]{em});
        String car="";
        if (cursor.moveToLast()) {
            car = cursor.getString(0);
        }
        return car;
    }
    public String retrievetype(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select type from booking where email = ?", new String[]{em});
        String type="";
        if (cursor.moveToLast()) {
            type = cursor.getString(0);
        }
        return type;
    }
    public String retrievecost(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select cost from booking where email = ?", new String[]{em});
        String cost="";
        if (cursor.moveToLast()) {
            cost = cursor.getString(0);
        }
        return cost;
    }
    public String retrievepdate(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select pdate from booking where email = ?", new String[]{em});
        String pdate="";
        if (cursor.moveToLast()) {
            pdate = cursor.getString(0);
        }
        return pdate;
    }
    public String retrieveptime(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select ptime from booking where email = ?", new String[]{em});
        String ptime="";
        if (cursor.moveToLast()) {
            ptime = cursor.getString(0);
        }
        return ptime;
    }
    public String retrieverdate(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select rdate from booking where email = ?", new String[]{em});
        String rdate="";
        if (cursor.moveToLast()) {
            rdate = cursor.getString(0);
        }
        return rdate;
    }
    public String retrievertime(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select rtime from booking where email = ?", new String[]{em});
        String rtime="";
        if (cursor.moveToLast()) {
            rtime = cursor.getString(0);
        }
        return rtime;
    }
    public String retrievedfname(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select dfname from booking where email = ?", new String[]{em});
        String dfname="";
        if (cursor.moveToLast()) {
            dfname = cursor.getString(0);
        }
        return dfname;
    }
    public String retrievedlname(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select dlname from booking where email = ?", new String[]{em});
        String dlname="";
        if (cursor.moveToLast()) {
            dlname = cursor.getString(0);
        }
        return dlname;
    }
    public String retrievelicense(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select license from booking where email = ?", new String[]{em});
        String license="";
        if (cursor.moveToLast()) {
            license = cursor.getString(0);
        }
        return license;
    }
    public String retrievephone(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select phone from booking where email = ?", new String[]{em});
        String phone="";
        if (cursor.moveToLast()) {
            phone = cursor.getString(0);
        }
        return phone;
    }
    public String retrieveplocation(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select plocation from booking where email = ?", new String[]{em});
        String plocation="";
        if (cursor.moveToLast()) {
            plocation = cursor.getString(0);
        }
        return plocation;
    }
    public String retrievedlocation(String em) {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select dlocation from booking where email = ?", new String[]{em});
        String dlocation="";
        if (cursor.moveToLast()) {
            dlocation = cursor.getString(0);
        }
        return dlocation;
    }

}

