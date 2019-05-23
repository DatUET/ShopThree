package com.example.shopthree.Presenter.GioHang;

import android.content.Context;

import com.example.shopthree.Model.GioHang.GioHang_Model;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.View.GioHang.ViewGioHang;

import java.util.List;

public class PresenterLogicGioHang implements IPresenterGioHang {

    GioHang_Model gioHang_model;
    ViewGioHang viewGioHang;

    public PresenterLogicGioHang(ViewGioHang viewGioHang) {
        gioHang_model = new GioHang_Model();
        this.viewGioHang = viewGioHang;
    }

    @Override
    public void getDanhSachSPTrongGioHang(Context context) {
        gioHang_model.MoKetNoiSQL(context);
        List<SanPham> sanPhamList = gioHang_model.getDanhSachSPGioHang();
        if(sanPhamList.size() > 0)
        {
            viewGioHang.showDanhSachSPTrongGioHang(sanPhamList);
        }
    }
}
