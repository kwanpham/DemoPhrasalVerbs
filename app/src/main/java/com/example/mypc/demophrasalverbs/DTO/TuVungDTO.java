package com.example.mypc.demophrasalverbs.DTO;

import java.io.Serializable;

/**
 * Created by MyPC on 24/03/2018.
 */

public class TuVungDTO implements Serializable{
    private int id , yeuthich;
    private String tuVung , nghia , vidu ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTuVung() {
        return tuVung;
    }

    public void setTuVung(String tuVung) {
        this.tuVung = tuVung;
    }

    public String getNghia() {
        return nghia;
    }

    public void setNghia(String nghia) {
        this.nghia = nghia;
    }

    public String getVidu() {
        return vidu;
    }

    public void setVidu(String vidu) {
        this.vidu = vidu;
    }

    public int getYeuthich() {
        return yeuthich;
    }

    public void setYeuthich(int yeuthich) {
        this.yeuthich = yeuthich;
    }
}
