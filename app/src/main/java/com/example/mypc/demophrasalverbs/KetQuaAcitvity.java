package com.example.mypc.demophrasalverbs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class KetQuaAcitvity extends AppCompatActivity {

    TextView tvDiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua_acitvity);


        int diem = getIntent().getIntExtra("diem" , 0);

        tvDiem= (TextView) findViewById(R.id.tvDiem);
        tvDiem.setText(diem +"/10");

    }


}
