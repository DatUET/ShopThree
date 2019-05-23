package com.example.shopthree.Presenter.TimKiem;

import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.Model.TimKiem.TimKiem_Model;
import com.example.shopthree.View.TimKiem.ViewTimKiem;

import java.util.List;

public class PresenterLogicTimKiem implements IPresenterTimKiem{

    ViewTimKiem viewTimKiem;
    TimKiem_Model timKiem_model;

    public PresenterLogicTimKiem(ViewTimKiem viewTimKiem) {
        this.viewTimKiem = viewTimKiem;
        timKiem_model = new TimKiem_Model();
    }

    @Override
    public void TimKiemSPTheoTen(String tenSP, int limit) {
        List<SanPham> sanPhamList = timKiem_model.getDanhSachSPTheoMaLoai(tenSP, "DANHSACHSANPHAM","TimKiemSanPhamTheoTenSP", limit);
        if(sanPhamList.size() > 0)
        {
            viewTimKiem.TimKiemThanhCong(sanPhamList);
        }
        else
        {
            viewTimKiem.TimKiemThatBai();
        }
    }
}
