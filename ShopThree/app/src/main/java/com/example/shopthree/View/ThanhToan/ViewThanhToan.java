package com.example.shopthree.View.ThanhToan;

import com.example.shopthree.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewThanhToan {
    void DatHangThanhCong();
    void DatHangThatBai();
    void getDanhSachSPTrongGioHang(List<SanPham> sanPhamList);
}
