package com.kushagra.kushagra.test.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.kushagra.kushagra.test.model.Details;
import com.kushagra.kushagra.test.model.User;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {



    private static final int DATABASE_VER = 8;
    private static final String DATABASE_NAME = "KUSHAGRA";
    private static final String TABLE_NAME = "Details";

    //
    private static final String KEY_ID="Id";
    //
    private static final  String KEY_PRADESH=" Pradesh";
    private static final  String KEY_JILLA="Jilla";
    private static final  String KEY_NAGARPALIKA=" Nagarpalika";
    private static final  String KEY_WARD=" Ward";
    private static final  String KEY_BASTI="Basti";
    private static final  String KEY_TOLENAME="Tole";
    private static final  String KEY_SADAKNAME="Sadak";

    //
    private static final String KEY_LAT="Latitude";
    private static final String KEY_LNG="Longitude";
    private static final String KEY_ALT="Altitude";

    //
    private static final String KEY_JATI="Jati";
    private static final String KEY_VASA="Vasa";
    private static final String KEY_DHARMA="Dharma";
    //
    private static final String KEY_GHARDHANINAME="GhardhaniName";
    private static final String KEY_GHARDHANISEX="GhardhaniSex";
    private static final String KEY_GHARDHANIPHONE="GhardhaniPhone";




    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("

                + KEY_ID+" INTEGER PRIMARY KEY,"
                +KEY_LAT+ " TEXT,"
                +KEY_LNG+ " TEXT,"
                +KEY_ALT+" TEXT,"
                +KEY_PRADESH+ " TEXT,"
                +KEY_JILLA+ " TEXT,"
                +KEY_NAGARPALIKA+ " TEXT,"
                +KEY_WARD+ " TEXT,"
                +KEY_BASTI+ " TEXT,"
                +KEY_TOLENAME+ " TEXT,"
                +KEY_SADAKNAME+ " TEXT,"
                +KEY_JATI+ " TEXT,"
                +KEY_VASA+ " TEXT,"
                +KEY_DHARMA+ " TEXT,"
                +KEY_GHARDHANINAME+ " TEXT,"
                +KEY_GHARDHANISEX+ " TEXT,"
                +KEY_GHARDHANIPHONE+ " TEXT"
                +")";

        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    //CRUDE
    public void addDetail(Details details){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(KEY_LAT,details.getLatitude());
        Log.d("HELLO2",details.getLatitude());
        values.put(KEY_LNG,details.getLongitude());
        values.put(KEY_ALT,details.getAltitude());
        values.put(KEY_PRADESH,details.getPradesh());
        values.put(KEY_JILLA,details.getJilla());
        values.put(KEY_NAGARPALIKA,details.getNagarpalika());
        values.put(KEY_WARD,details.getWard());
        Log.d("HELLOWARD",details.getWard());
        values.put(KEY_BASTI,details.getBasti());
        values.put(KEY_TOLENAME,details.getTole());
        values.put(KEY_SADAKNAME,details.getSadak());
        values.put(KEY_JATI,details.getJati());
        values.put(KEY_VASA,details.getVasa());
        values.put(KEY_DHARMA,details.getDharma());
        values.put(KEY_GHARDHANINAME,details.getGhardhaniname());
        values.put(KEY_GHARDHANISEX,details.getGhardhanisex());
        values.put(KEY_GHARDHANIPHONE,details.getGhardhaniphone());
        Log.d("TESTJ",details.getJati());
       // Log.d("TESTV",details.getVasa());
        //Log.d("TESTD",details.getDharma());



        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public int updateDetail(Details details){

        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values =new ContentValues();

        values.put(KEY_LAT,details.getLatitude());
        values.put(KEY_LNG,details.getLongitude());
        values.put(KEY_ALT,details.getAltitude());
        values.put(KEY_PRADESH,details.getPradesh());
        values.put(KEY_JILLA,details.getJilla());
        values.put(KEY_NAGARPALIKA,details.getNagarpalika());
        values.put(KEY_WARD,details.getWard());
        values.put(KEY_BASTI,details.getBasti());
        values.put(KEY_TOLENAME,details.getTole());
        values.put(KEY_SADAKNAME,details.getSadak());
        values.put(KEY_JATI,details.getJati());
        values.put(KEY_VASA,details.getVasa());
        values.put(KEY_DHARMA,details.getDharma());

        return db.update(TABLE_NAME,values,KEY_ID+ "=?", new String[]{String.valueOf(details.getId())});


    }

    public void deleteDetail(Details details){

        SQLiteDatabase db =this.getWritableDatabase();

        db.delete(TABLE_NAME,KEY_ID+" =?",new String[]{String.valueOf(details.getId())});

        db.close();


    }

    public Details getDetail(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{
                        KEY_ID,
                        KEY_LAT,
                        KEY_LNG,
                        KEY_ALT,
                        KEY_PRADESH,
                        KEY_JILLA,
                        KEY_NAGARPALIKA,
                        KEY_WARD,
                        KEY_BASTI,
                        KEY_TOLENAME,
                        KEY_SADAKNAME,
                        KEY_JATI,
                        KEY_VASA,
                        KEY_DHARMA,
                        KEY_GHARDHANINAME,
                        KEY_GHARDHANIPHONE,
                        KEY_GHARDHANISEX

                },KEY_ID+"=?",
                new String[]{String.valueOf(id)},null,null,null,null);

        if (cursor!=null)
            cursor.moveToFirst();

        return new Details(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getString(9),
                cursor.getString(10),
                cursor.getString(11), //jati
                cursor.getString(12),   //vasa
                cursor.getString(13),  //dharma
                cursor.getString(14), //ghardhaniname
                cursor.getString(15), //ghardhanisex
                cursor.getString(16) //ghardhaniphone
            );
    }

    public List<Details> getAllDetails(){

        List<Details> lstDetail = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                Details details = new Details();
                details.setId(cursor.getInt(0));
                details.setLatitude(cursor.getString(1));
                details.setLongitude(cursor.getString(2));
                details.setAltitude(cursor.getString(3));
                details.setPradesh(cursor.getString(4));
                details.setJilla(cursor.getString(5));
                details.setNagarpalika(cursor.getString(6));
                details.setWard(cursor.getString(7));
                details.setBasti(cursor.getString(8));
                details.setTole(cursor.getString(9));
                details.setSadak(cursor.getString(10));
                details.setJati(cursor.getString(11));
                details.setVasa(cursor.getString(12));
                details.setDharma(cursor.getString(13));
                details.setGhardhaniname(cursor.getString(14));
                details.setGhardhanisex(cursor.getString(15));
                details.setGhardhaniphone(cursor.getString(16));




                lstDetail.add(details);
            }
            while (cursor.moveToNext());

        }

        return lstDetail;

    }
}
