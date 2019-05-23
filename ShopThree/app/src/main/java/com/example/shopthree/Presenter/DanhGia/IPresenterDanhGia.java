package com.example.shopthree.Presenter.DanhGia;

import android.widget.ProgressBar;

import com.example.shopthree.Model.ObjectClass.DanhGia;

public interface IPresenterDanhGia {
    void ThemDanhGia(DanhGia danhGia);
    void getDanhSachDanhGiaTheoSP(int masp, int limit, ProgressBar progressBar);
}
