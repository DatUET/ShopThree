package com.example.shopthree.Model.ObjectClass;

import java.io.Serializable;
import java.util.List;

public class HoaDon implements Serializable {

    int MAHD, CHUYENKHOAN, MANV, TONGGIATIEN;
    String NGAYMUA, NGAYGIAO, TRANGTHAI, TENNGUOINHAN, SODT, DIACHI, MACHUYENKHOAN;
    List<ChiTietHoaDon> chiTietHoaDonList;

    public int getMAHD() {
        return MAHD;
    }

    public void setMAHD(int MAHD) {
        this.MAHD = MAHD;
    }

    public int getCHUYENKHOAN() {
        return CHUYENKHOAN;
    }

    public void setCHUYENKHOAN(int CHUYENKHOAN) {
        this.CHUYENKHOAN = CHUYENKHOAN;
    }

    public String getNGAYMUA() {
        return NGAYMUA;
    }

    public void setNGAYMUA(String NGAYMUA) {
        this.NGAYMUA = NGAYMUA;
    }

    public String getNGAYGIAO() {
        return NGAYGIAO;
    }

    public void setNGAYGIAO(String NGAYGIAO) {
        this.NGAYGIAO = NGAYGIAO;
    }

    public String getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(String TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getTENNGUOINHAN() {
        return TENNGUOINHAN;
    }

    public void setTENNGUOINHAN(String TENNGUOINHAN) {
        this.TENNGUOINHAN = TENNGUOINHAN;
    }

    public String getSODT() {
        return SODT;
    }

    public void setSODT(String SODT) {
        this.SODT = SODT;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public String getMACHUYENKHOAN() {
        return MACHUYENKHOAN;
    }

    public void setMACHUYENKHOAN(String MACHUYENKHOAN) {
        this.MACHUYENKHOAN = MACHUYENKHOAN;
    }

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    public int getTONGGIATIEN() {
        return TONGGIATIEN;
    }

    public void setTONGGIATIEN(int TONGGIATIEN) {
        this.TONGGIATIEN = TONGGIATIEN;
    }

    public List<ChiTietHoaDon> getChiTietHoaDonList() {
        return chiTietHoaDonList;
    }

    public void setChiTietHoaDonList(List<ChiTietHoaDon> chiTietHoaDonList) {
        this.chiTietHoaDonList = chiTietHoaDonList;
    }
}
