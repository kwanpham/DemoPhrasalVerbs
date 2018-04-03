package com.example.mypc.demophrasalverbs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypc.demophrasalverbs.DAO.CauHoiDao;
import com.example.mypc.demophrasalverbs.DAO.QuizDao;
import com.example.mypc.demophrasalverbs.DTO.CauHoiDTO;

import java.util.List;

public class CauHoiActivity extends AppCompatActivity {

    private TextView tvCauHoi;
    private RadioGroup rgLuaChon;
    private RadioButton rbA, rbB, rbC, rbD;
    private Button btnNext;

    private CauHoiDao cauHoiDao;
    private QuizDao quizDao;
    private List<CauHoiDTO> cauHoiDTOList;


    int diem = 0;
    int id;

    int flagNextQuestion;      // để ấn lại nút button 2 lần

    int flagRadioButtonDapAnDung;       // id radio button chứa đáp án đúng


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cau_hoi);

        id = getIntent().getIntExtra("id", 0);

        cauHoiDao = new CauHoiDao(this);
        cauHoiDTOList = cauHoiDao.layTatCaCauHoiTheoId(id);

        quizDao = new QuizDao(this);


        initView();
        chayCauHoi(0);       // đệ quy phần trắc nhiệm


    }

    private void initView() {

        tvCauHoi = (TextView) findViewById(R.id.tvCauHoi);
        rgLuaChon = (RadioGroup) findViewById(R.id.rgLuaChon);
        rbA = (RadioButton) findViewById(R.id.a);
        rbB = (RadioButton) findViewById(R.id.b);
        rbC = (RadioButton) findViewById(R.id.c);
        rbD = (RadioButton) findViewById(R.id.d);
        btnNext = (Button) findViewById(R.id.btnNext);

    }

    private void chayCauHoi(final int n) {

        btnNext.setText("Check");

        rgLuaChon.clearCheck();
        flagNextQuestion = 0;


        rbA.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        rbB.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        rbC.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        rbD.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        rbA.setClickable(true);
        rbB.setClickable(true);
        rbC.setClickable(true);
        rbD.setClickable(true);

                                                                   // set tất cả lại mặc định cho câu hỏi kế tiếp
        final CauHoiDTO temp = cauHoiDTOList.get(n);

        tvCauHoi.setText(temp.getQuest());


        rbA.setText(temp.getA());
        rbB.setText(temp.getB());
        rbC.setText(temp.getC());
        rbD.setText(temp.getD());

        flagRadioButtonDapAnDung =                                          // Tim ra radio button chua dap an dung
                temp.getAnswer().equals(temp.getA()) ? R.id.a :
                        temp.getAnswer().equals(temp.getB()) ? R.id.b :
                                temp.getAnswer().equals(temp.getC()) ? R.id.c :
                                        temp.getAnswer().equals(temp.getD()) ? R.id.d : 0;

        rgLuaChon.setClickable(false);

        btnNext.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {

                if (flagNextQuestion == 0) {           // Check lần ấn button đầu tiên
                    if (rgLuaChon.getCheckedRadioButtonId() != -1) {       // check xem đã chọn đáp án chưa

                        btnNext.setText("Next");

                        if (rgLuaChon.getCheckedRadioButtonId() == flagRadioButtonDapAnDung) {        // chọn rồi thì check kết quả

                            diem++;
                            RadioButton rbDapAnDung = (RadioButton) findViewById(rgLuaChon.getCheckedRadioButtonId());
                            rbDapAnDung.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icons8_checked_40, 0);
                        } else {
                            RadioButton rbDapAnSai = (RadioButton) findViewById(rgLuaChon.getCheckedRadioButtonId());
                            rbDapAnSai.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icons8_multiply_40, 0);

                            RadioButton rbDapAnDung = (RadioButton) findViewById(flagRadioButtonDapAnDung);
                            rbDapAnDung.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icons8_checked_40, 0);

                        }
                        flagNextQuestion++;

                        rbA.setClickable(false);
                        rbB.setClickable(false);
                        rbC.setClickable(false);
                        rbD.setClickable(false);         // Không cho click đáp án




                    } else {

                        Toast.makeText(CauHoiActivity.this, "Ban chua chon dap an", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    if (n != cauHoiDTOList.size() - 1) {
                        chayCauHoi(n + 1);
                    } else {

                        quizDao.updateDiemTheoIdLevel(id , diem);
                        Intent intentKetQua = new Intent(CauHoiActivity.this, KetQuaAcitvity.class);
                        intentKetQua.putExtra("diem", diem);
                        startActivity(intentKetQua);
                        finish();
                    }

                }
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
