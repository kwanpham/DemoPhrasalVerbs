package com.example.mypc.demophrasalverbs.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by MyPC on 24/03/2018.
 */

public class MyDatabase  {

    private Context context;

    private String DB_PATH = "/data/data/com.example.mypc.demophrasalverbs/databases";
    private static String DB_NAME = "WordDb.db";
    private SQLiteDatabase myDatabase;


    public MyDatabase(Context context) {


       this.context = context;



    }





    public SQLiteDatabase StoreDatabase() {
        ///data/data/{package của bạn}/databases
        String path = "/data/data/com.example.mypc.demophrasalverbs/databases";
        File pathDb = new File(path);
        try {
            // kiểm tra nếu có thư mục databases thì sẽ tạo
            if (!pathDb.exists()){
                pathDb.mkdir();
            }
            // kiểm tra file đó chưa tồn tại thì copy vào
            // điều kiện này tránh trường hợp mỗi lần mở ứng dụng sẽ copy vào.
            if (!new File(path + "/" + DB_NAME).exists()) {
                copy(path);
            }
        } catch (IOException e) {
            Log.d("IOException", e.getMessage());
        }
        Log.i("DB",context.getDatabasePath(DB_NAME).getPath());
        return SQLiteDatabase.openDatabase(context.getDatabasePath(DB_NAME).getPath(),null,  SQLiteDatabase.OPEN_READWRITE);
    }
    /*
     * Thực hiện đọc file và ghi file
     */
    private void copy(String path) throws IOException {

        // mở file trong thư mục assets
        InputStream is = context.getAssets().open(DB_NAME);
        FileOutputStream fos = new FileOutputStream(path + "/" + DB_NAME);
        byte buffer[] = new byte[1024];
        int length;

        // đọc và ghi vào thư mục databases
        while ((length = is.read(buffer)) > 0) {
            fos.write(buffer, 0, length);
        }


        is.close();
        fos.flush();
        fos.close();
    }



}
