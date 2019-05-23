package com.example.shopthree.Model.ObjectClass;

import java.util.List;

public class KhuyenMai {
    int MAKM, MALOAISP;
    String TENKHUYENMAI, NGAYBATDAU, NGAYKETTHUC, HINHKHUYENMAI, TENLOAISP;
    List<SanPham> DanhSahSPKhuyenMai;

    public String getTENLOAISP() {
        return TENLOAISP;
    }

    public void setTENLOAISP(String TENLOAISP) {
        this.TENLOAISP = TENLOAISP;
    }

    public int getMAKM() {
        return MAKM;
    }

    public void setMAKM(int MAKM) {
        this.MAKM = MAKM;
    }

    public int getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public String getTENKHUYENMAI() {
        return TENKHUYENMAI;
    }

    public void setTENKHUYENMAI(String TENKHUYENMAI) {
        this.TENKHUYENMAI = TENKHUYENMAI;
    }

    public String getNGAYBATDAU() {
        return NGAYBATDAU;
    }

    public void setNGAYBATDAU(String NGAYBATDAU) {
        this.NGAYBATDAU = NGAYBATDAU;
    }

    public String getNGAYKETTHUC() {
        return NGAYKETTHUC;
    }

    public void setNGAYKETTHUC(String NGAYKETTHUC) {
        this.NGAYKETTHUC = NGAYKETTHUC;
    }

    public String getHINHKHUYENMAI() {
        return HINHKHUYENMAI;
    }

    public void setHINHKHUYENMAI(String HINHKHUYENMAI) {
        this.HINHKHUYENMAI = HINHKHUYENMAI;
    }

    public List<SanPham> getDanhSahSPKhuyenMai() {
        return DanhSahSPKhuyenMai;
    }

    public void setDanhSahSPKhuyenMai(List<SanPham> danhSahSPKhuyenMai) {
        DanhSahSPKhuyenMai = danhSahSPKhuyenMai;
    }
}
