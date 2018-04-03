package com.example.mypc.demophrasalverbs.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mypc.demophrasalverbs.DTO.CauHoiDTO;
import com.example.mypc.demophrasalverbs.Database.MyDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 31/03/2018.
 */

public class CauHoiDao  {

    private SQLiteDatabase sqLiteDatabase;


    public CauHoiDao(Context context) {
        MyDatabase myDatabase = new MyDatabase(context);
        sqLiteDatabase = myDatabase.StoreDatabase();

    }

    public List<CauHoiDTO> layTatCaCauHoiTheoId(int id){
        List<CauHoiDTO> cauHoiDTOList = new ArrayList<>();

        StringBuilder cauHoiId = new StringBuilder();                   // Lấy câu hỏi theo số id level của database
        for(int i = (id*10 - 10 ) ; i <= (id*10) ; i++){

            if(i== id*10){
                cauHoiId.append(i+" )" );
            }
            else if (i == ( id*10 -10))
                cauHoiId.append("( "+ i +" , ");
            else
                cauHoiId.append(i + " , ");
        }
        String query = "Select *from quest where id in " + cauHoiId ;
        Cursor cursor = sqLiteDatabase.rawQuery(query ,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            CauHoiDTO temp = new CauHoiDTO();

            temp.setId(cursor.getInt(0));
            temp.setQuest(cursor.getString(1));
            temp.setAnswer(cursor.getString(2));
            temp.setA(cursor.getString(3));
            temp.setB(cursor.getString(4));
            temp.setC(cursor.getString(5));
            temp.setD(cursor.getString(6));

            cauHoiDTOList.add(temp);
            cursor.moveToNext();
        }

        cursor.close();

        return cauHoiDTOList;

    }


}
