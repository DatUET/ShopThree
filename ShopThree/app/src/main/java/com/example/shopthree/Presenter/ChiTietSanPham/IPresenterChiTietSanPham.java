package com.example.shopthree.Presenter.ChiTietSanPham;

import android.content.Context;

import com.example.shopthree.Model.ObjectClass.SanPham;

public interface IPresenterChiTietSanPham {
    void getChitirtSP(int maSP);
    void getDanhSachDanhGia(int masp, int limit);
    void ThenGioHang(SanPham sanPham, Context context);
}
