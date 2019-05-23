package com.example.shopthree.Presenter.KhuyenMai;

import com.example.shopthree.Model.KhuyenMai.KhuyenMai_Model;
import com.example.shopthree.Model.ObjectClass.KhuyenMai;
import com.example.shopthree.View.TrangChu.ViewKhuyenMai;

import java.util.List;


public class PresenterLogicKhuyenMai implements IPresenterKhuyenMai {

    ViewKhuyenMai viewKhuyenMai;
    KhuyenMai_Model khuyenMai_model;

    public PresenterLogicKhuyenMai(ViewKhuyenMai viewKhuyenMai) {
        this.viewKhuyenMai = viewKhuyenMai;
        khuyenMai_model = new KhuyenMai_Model();
    }

    @Override
    public void getDanhSachKhuyenMai() {
        List<KhuyenMai> khuyenMaiList = khuyenMai_model.getDanhSachSPTheoMaLoai("LayDanhSachKhuyenMai", "DANHSACHKHUYENMAI");
        if(khuyenMaiList.size() > 0)
        {
            viewKhuyenMai.showDanhSachKhuyenMai(khuyenMaiList);
        }
    }
}
