package com.example.shopthree.View.ChiTietSanPham;

import com.example.shopthree.Model.ObjectClass.DanhGia;
import com.example.shopthree.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewChiTietSanPham {
    void showChiTietSanPham(SanPham sanPham);
    void showSlideSanPham(String[] linkhinhsp);
    void showDanhSachDanhGia(List<DanhGia> danhGiaList);
    void addGioHangThanhCong();
    void addGioHangThatBai();
}
