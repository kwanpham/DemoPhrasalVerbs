package com.example.mypc.demophrasalverbs.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mypc.demophrasalverbs.DTO.QuizDTO;
import com.example.mypc.demophrasalverbs.Database.MyDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 29/03/2018.
 */

public class QuizDao {

    SQLiteDatabase database;
    Context context;

    public QuizDao(Context context) {
        this.context = context;
        MyDatabase myDatabase = new MyDatabase(context);
        database = myDatabase.StoreDatabase();
        Log.d("read", database.isReadOnly() + "");
        Log.d("datapath", database + "");
    }

    public List<QuizDTO> layTatCaCacQuiz() {

        List<QuizDTO> quizDTOList = new ArrayList<>();
        String truyvan = "select  *from  Level";
        Cursor cursor = database.rawQuery(truyvan, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            QuizDTO quizDTO = new QuizDTO();
            quizDTO.setId(cursor.getInt(0));
            quizDTO.setTenQuiz(cursor.getString(1));
            quizDTO.setDiemSo(cursor.getInt(2));

            quizDTOList.add(quizDTO);
            cursor.moveToNext();
        }

        cursor.close();
        return quizDTOList;
    }

    public void closeDataBase() {
        database.close();
    }

    public boolean updateDiemTheoIdLevel(int id ,int diem){
        ContentValues contentValues = new ContentValues();
        contentValues.put("lscore" , diem);
        long kiemtra = database.update("Level",contentValues,"_id = " +id ,null);
        return (kiemtra!=0);
    }


}
