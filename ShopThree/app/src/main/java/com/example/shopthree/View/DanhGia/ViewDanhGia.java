package com.example.shopthree.View.DanhGia;

import com.example.shopthree.Model.ObjectClass.DanhGia;

import java.util.List;

public interface ViewDanhGia {
    void DanhGiaThanhCong();
    void DanhGiaThatBai();
    void showDanhSachDanhGiaTheoSP(List<DanhGia> danhGiaList);
}
