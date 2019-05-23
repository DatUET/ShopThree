package com.example.shopthree.Presenter.DanhGia;

import android.view.View;
import android.widget.ProgressBar;

import com.example.shopthree.Model.DanhGia.DanhGia_Model;
import com.example.shopthree.Model.ObjectClass.DanhGia;
import com.example.shopthree.View.DanhGia.ViewDanhGia;

import java.util.List;

public class PresenterLogicDanhGia implements IPresenterDanhGia {

    ViewDanhGia viewDanhGia;
    DanhGia_Model danhGia_model;

    public PresenterLogicDanhGia(ViewDanhGia viewDanhGia) {
        this.viewDanhGia = viewDanhGia;
        danhGia_model = new DanhGia_Model();
    }

    @Override
    public void ThemDanhGia(DanhGia danhGia) {
        boolean check = danhGia_model.ThemDanhGia(danhGia);
        if(check)
        {
            viewDanhGia.DanhGiaThanhCong();
        }
        else
        {
            viewDanhGia.DanhGiaThatBai();
        }
    }

    @Override
    public void getDanhSachDanhGiaTheoSP(int masp, int limit, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        List<DanhGia> danhGiaList = danhGia_model.getDanhSachDanhGia(masp, limit);

        if(danhGiaList.size() > 0)
        {
            progressBar.setVisibility(View.GONE);
            viewDanhGia.showDanhSachDanhGiaTheoSP(danhGiaList);
        }
    }
}
