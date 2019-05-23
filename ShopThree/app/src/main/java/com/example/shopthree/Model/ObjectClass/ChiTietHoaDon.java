package com.example.shopthree.Model.ObjectClass;

import java.io.Serializable;

public class ChiTietHoaDon implements Serializable {
    int MAHD, MASP, SOLUONG, GIA, DONGIASAUKM;
    String TENSP, ANHSP;

    public int getMAHD() {
        return MAHD;
    }

    public void setMAHD(int MAHD) {
        this.MAHD = MAHD;
    }

    public int getMASP() {
        return MASP;
    }

    public void setMASP(int MASP) {
        this.MASP = MASP;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public int getGIA() {
        return GIA;
    }

    public void setGIA(int GIA) {
        this.GIA = GIA;
    }

    public int getDONGIASAUKM() {
        return DONGIASAUKM;
    }

    public void setDONGIASAUKM(int DONGIASAUKM) {
        this.DONGIASAUKM = DONGIASAUKM;
    }

    public String getTENSP() {
        return TENSP;
    }

    public void setTENSP(String TENSP) {
        this.TENSP = TENSP;
    }

    public String getANHSP() {
        return ANHSP;
    }

    public void setANHSP(String ANHSP) {
        this.ANHSP = ANHSP;
    }
}
