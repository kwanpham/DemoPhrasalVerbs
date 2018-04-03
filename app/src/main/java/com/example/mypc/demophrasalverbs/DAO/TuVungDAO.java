package com.example.mypc.demophrasalverbs.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mypc.demophrasalverbs.DTO.TuVungDTO;
import com.example.mypc.demophrasalverbs.Database.MyDatabase;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 24/03/2018.
 */

public class TuVungDAO {

    SQLiteDatabase database;
    Context context;

    public TuVungDAO(Context context) {
        this.context = context;
        MyDatabase myDatabase = new MyDatabase(context);
        database = myDatabase.StoreDatabase();
        Log.d("read", database.isReadOnly() + "");
        Log.d("datapath", database + "");


    }

    public List<TuVungDTO> layTatCaCacTu() {

        List<TuVungDTO> tuVungDTOList = new ArrayList<>();
        String truyvan = "select  id , name , meaning, synonym , favorite from Words ";
        Cursor cursor = database.rawQuery(truyvan, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TuVungDTO tuVungDTO = new TuVungDTO();
            tuVungDTO.setId(cursor.getInt(0));
            tuVungDTO.setTuVung(cursor.getString(1));
            tuVungDTO.setNghia(cursor.getString(2));
            tuVungDTO.setVidu(cursor.getString(3));
            tuVungDTO.setYeuthich(cursor.getInt(4));

            tuVungDTOList.add(tuVungDTO);

            cursor.moveToNext();
        }

        cursor.close();
        return tuVungDTOList;
    }

    public List<TuVungDTO> layTatCaCacTuYeuThich() {

        List<TuVungDTO> tuVungDTOList = new ArrayList<>();
        String truyvan = "select  id , name , meaning, synonym , favorite from Words where favorite = 1";
        Cursor cursor = database.rawQuery(truyvan, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TuVungDTO tuVungDTO = new TuVungDTO();
            tuVungDTO.setId(cursor.getInt(0));
            tuVungDTO.setTuVung(cursor.getString(1));
            tuVungDTO.setNghia(cursor.getString(2));
            tuVungDTO.setVidu(cursor.getString(3));
            tuVungDTO.setYeuthich(cursor.getInt(4));

            tuVungDTOList.add(tuVungDTO);

            cursor.moveToNext();
        }

        cursor.close();
        return tuVungDTOList;
    }

    public void danhDauYeuThichTheoId(int id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("favorite", 1);

        long kiemtra = database.update("Words", contentValues, "Words.id = " + id, null);
        Log.d("kiemtradanhdau", kiemtra + "  " + id);

    }

    public void boDanhDauYeuThichTheoId(int id){
        ContentValues contentValues = new ContentValues();
        contentValues.put("favorite", 0);

        long kiemtra = database.update("Words", contentValues, "Words.id = " + id, null);
        Log.d("kiemtrabo", kiemtra + "  " + id);
    }

    public boolean checkYeuThichTheoId(int id){
        String truyvan = "Select favorite from Words where Words.id = " + id;
        Cursor cursor = database.rawQuery(truyvan , null);
        cursor.moveToFirst();
        int checkYeuThich = cursor.getInt(0);
        return (checkYeuThich == 1);
    }

    public void closeDataBase() {
        database.close();
    }
}
