package com.example.shopthree.Presenter.DangKy;

import com.example.shopthree.Model.DangNhap_DangKy.DangKy_Model;
import com.example.shopthree.Model.ObjectClass.NhanVien;
import com.example.shopthree.View.DangNhap_DangKy.ViewDangKy;

public class PresenterLogicDangKy implements IPresenterDangKy {

    ViewDangKy viewDangKy;
    DangKy_Model dangKy_model;

    public PresenterLogicDangKy(ViewDangKy viewDangKy) {
        this.viewDangKy = viewDangKy;
        dangKy_model = new DangKy_Model();
    }

    @Override
    public void ThucHienDangKy(NhanVien nhanVien) {
        Boolean check = dangKy_model.DangKyThanhVien(nhanVien);
        if(check)
        {
            viewDangKy.DangKyThanhCong();
        }
        else
        {
            viewDangKy.DangKyThatBai();
        }
    }
}
