package com.example.mypc.demophrasalverbs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.mypc.demophrasalverbs.DAO.TuVungDAO;
import com.example.mypc.demophrasalverbs.DTO.TuVungDTO;

public class ChiTietTuVungAcitivity extends AppCompatActivity {

    private TextView tvTuVungChiTiet, tvPhienAmChiTiet, tvNghiaChiTiet, tvNghiaDichChiTiet, tvViDuChiTiet;
    private Button btnSpeakerChiTiet, btnDichChiTiet;
    private CheckBox cbYeuThichChiTiet;
    private TuVungDAO tuVungDAO;
    private TuVungDTO tuVungDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chi_tiet_tu_vung_acitivity);

        Bundle bundle = getIntent().getExtras();
        tuVungDTO = (TuVungDTO) bundle.getSerializable("tuvung");
        InitView();
    }

    private void InitView() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTuVungChiTiet = (TextView) findViewById(R.id.tvTuVungChiTiet);
        tvPhienAmChiTiet = (TextView) findViewById(R.id.tvPhienAmChiTiet);
        tvNghiaChiTiet = (TextView) findViewById(R.id.tvNghiaChiTiet);
        tvNghiaDichChiTiet = (TextView) findViewById(R.id.tvNghiaDichChiTiet);
        tvViDuChiTiet = (TextView) findViewById(R.id.tvViDuChiTiet);

        btnSpeakerChiTiet = (Button) findViewById(R.id.btnSpeakerChiTiet);
        btnDichChiTiet = (Button) findViewById(R.id.btnSpeakerChiTiet);

        cbYeuThichChiTiet = (CheckBox) findViewById(R.id.cbYeuThichChiTiet);

        tuVungDAO = new TuVungDAO(this);

        tvTuVungChiTiet.setText(tuVungDTO.getTuVung());
        tvNghiaChiTiet.setText(tuVungDTO.getNghia());
        tvViDuChiTiet.setText(tuVungDTO.getTuVung());

        cbYeuThichChiTiet.setChecked(tuVungDTO.getYeuthich() == 1);
        cbYeuThichChiTiet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tuVungDAO.danhDauYeuThichTheoId(tuVungDTO.getId());
                    tuVungDTO.setYeuthich(1);
                } else {
                    tuVungDAO.boDanhDauYeuThichTheoId(tuVungDTO.getId());
                    tuVungDTO.setYeuthich(0);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBackTuVungFrament = new Intent();
        Bundle bundleBack = new Bundle();
        bundleBack.putSerializable("tuvung", tuVungDTO);
        intentBackTuVungFrament.putExtras(bundleBack);

    }
}
