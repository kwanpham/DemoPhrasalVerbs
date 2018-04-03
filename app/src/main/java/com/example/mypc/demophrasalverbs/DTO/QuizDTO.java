package com.example.mypc.demophrasalverbs.DTO;

/**
 * Created by MyPC on 29/03/2018.
 */

public class QuizDTO {

    private int id , diemSo;
    private String tenQuiz ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiemSo() {
        return diemSo;
    }

    public void setDiemSo(int diemSo) {
        this.diemSo = diemSo;
    }

    public String getTenQuiz() {
        return tenQuiz;
    }

    public void setTenQuiz(String tenQuiz) {
        this.tenQuiz = tenQuiz;
    }
}
