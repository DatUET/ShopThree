package com.example.shopthree.Presenter.ChiTietSanPham;

import android.content.Context;
import android.util.Log;

import com.example.shopthree.Model.ChiTietSanPham.ChiTietSanPham_Model;
import com.example.shopthree.Model.GioHang.GioHang_Model;
import com.example.shopthree.Model.ObjectClass.DanhGia;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.View.ChiTietSanPham.ViewChiTietSanPham;

import java.util.List;

public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham {

    ViewChiTietSanPham viewChiTietSanPham;
    ChiTietSanPham_Model chiTietSanPham_model;
    GioHang_Model gioHang_model;

    public PresenterLogicChiTietSanPham()
    {
        gioHang_model = new GioHang_Model();
    }

    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        chiTietSanPham_model = new ChiTietSanPham_Model();
        gioHang_model = new GioHang_Model();
    }

    @Override
    public void getChitirtSP(int maSP) {
        SanPham sanPham = chiTietSanPham_model.getChiTietSP("LaySanPhamVsChitietTheoMaSP", "CHITIETSANPHAM", maSP);
        if(sanPham.getMASP() > 0)
        {
            String[] linkhinhsp = sanPham.getANHNHO().split(",");
            viewChiTietSanPham.showSlideSanPham(linkhinhsp);
            viewChiTietSanPham.showChiTietSanPham(sanPham);
        }
    }

    @Override
    public void getDanhSachDanhGia(int masp, int limit) {
        List<DanhGia> danhGias = chiTietSanPham_model.getDanhSachDanhGia(masp, limit);

        if(danhGias.size() > 0)
        {
            viewChiTietSanPham.showDanhSachDanhGia(danhGias);
        }
    }

    @Override
    public void ThenGioHang(SanPham sanPham, Context context) {
        gioHang_model.MoKetNoiSQL(context);
        boolean check = gioHang_model.ThemGioHang(sanPham);
        if(check)
        {
            viewChiTietSanPham.addGioHangThanhCong();
        }
        else
        {
            viewChiTietSanPham.addGioHangThatBai();
        }
    }

    public int countSoSPTrongGioHang(Context context)
    {
        gioHang_model.MoKetNoiSQL(context);
        List<SanPham> sanPhamList = gioHang_model.getDanhSachSPGioHang();
        return sanPhamList.size();
    }
}
