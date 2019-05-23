package com.example.shopthree.Presenter.ThanhToan;

import android.content.Context;

import com.example.shopthree.Model.GioHang.GioHang_Model;
import com.example.shopthree.Model.ObjectClass.HoaDon;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.Model.ThanhToan.ThanhToan_Model;
import com.example.shopthree.View.ThanhToan.ViewThanhToan;

import java.util.List;

public class PresenterLogicThanhToan implements IPresenterThanhToan {

    ViewThanhToan viewThanhToan;
    ThanhToan_Model thanhToan_model;
    GioHang_Model gioHang_model;
    List<SanPham> sanPhamList;

    public PresenterLogicThanhToan(ViewThanhToan viewThanhToan, Context context) {
        this.viewThanhToan = viewThanhToan;
        thanhToan_model = new ThanhToan_Model();
        gioHang_model = new GioHang_Model();
        gioHang_model.MoKetNoiSQL(context);
    }

    @Override
    public void ThemHoaDon(HoaDon hoaDon) {
        boolean check = thanhToan_model.ThemThanhToan(hoaDon);
        boolean checkThemLuotMua = true;

        for (int i=0;i<sanPhamList.size();i++)
        {
            SanPham sanPham = sanPhamList.get(i);
            if(!thanhToan_model.ThemLuotMua(sanPham.getMASP(), sanPham.getSOLUONG()))
            {
                checkThemLuotMua = false;
            }
        }

        if(check && checkThemLuotMua)
        {
            viewThanhToan.DatHangThanhCong();

            int count = sanPhamList.size();
            for(int i=0; i<count; i++)
            {
                gioHang_model.deleteSPGioHang(sanPhamList.get(i).getMASP());
            }
        }
        else
        {
            viewThanhToan.DatHangThatBai();
        }
    }

    @Override
    public void getDanhSachSPTrongGioHang() {
        sanPhamList = gioHang_model.getDanhSachSPGioHang();
        viewThanhToan.getDanhSachSPTrongGioHang(sanPhamList);
    }
}
