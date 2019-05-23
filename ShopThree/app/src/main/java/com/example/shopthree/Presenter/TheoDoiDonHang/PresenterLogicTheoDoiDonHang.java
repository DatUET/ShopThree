package com.example.shopthree.Presenter.TheoDoiDonHang;

import com.example.shopthree.Model.ObjectClass.HoaDon;
import com.example.shopthree.Model.TheoDoiDonHang.TheoDoiDonHang_Model;
import com.example.shopthree.View.TheoDoiDonHang.ViewTheoDoiDonHang;

import java.util.List;

public class PresenterLogicTheoDoiDonHang implements IPresenterTheoDoiDonHang {

    ViewTheoDoiDonHang viewTheoDoiDonHang;
    TheoDoiDonHang_Model theoDoiDonHang_model;

    public PresenterLogicTheoDoiDonHang(ViewTheoDoiDonHang viewTheoDoiDonHang) {
        this.viewTheoDoiDonHang = viewTheoDoiDonHang;
        theoDoiDonHang_model = new TheoDoiDonHang_Model();
    }

    @Override
    public void getHoaDon(int manv) {
        List<HoaDon> hoaDonList = theoDoiDonHang_model.getHoaDon(manv);
        viewTheoDoiDonHang.showDanhSachHoaDon(hoaDonList);
    }
}
