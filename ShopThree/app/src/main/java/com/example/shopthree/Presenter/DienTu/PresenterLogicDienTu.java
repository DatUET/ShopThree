package com.example.shopthree.Presenter.DienTu;

import com.example.shopthree.Model.DienTu.DienTu_Model;
import com.example.shopthree.Model.ObjectClass.DienTu;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.Model.ObjectClass.ThuongHieu;
import com.example.shopthree.View.TrangChu.ViewDienTu;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicDienTu implements IPresenterDienTu {

    ViewDienTu viewDienTu;
    DienTu_Model dienTu_model;

    public PresenterLogicDienTu(ViewDienTu viewDienTu) {
        this.viewDienTu = viewDienTu;
        this.dienTu_model = new DienTu_Model();
    }

    @Override
    public void getDanhSachDienTu() {

        List<DienTu> dienTus = new ArrayList<>();

        List<ThuongHieu> thuongHieus = dienTu_model.getDanhSachThuongHieu("LayDanhSachCacThuongHieuLon", "DANHSACHTHUONGHIEU");
        List<SanPham> sanPhams = dienTu_model.getDanhSachTopSP("LayDanhSachTopDienThoaiVaMayTinhBang", "TOPDIENTHOAI&MAYTINHBANG");

        DienTu dienTu = new DienTu();
        dienTu.setThuongHieus(thuongHieus);
        dienTu.setSanPhams(sanPhams);
        dienTu.setTennoibat("Thương hiệu lớn");
        dienTu.setTentopnoibat("Top điện thoại và máy tính bảng");
        dienTu.setThuonghieu(true);
        dienTus.add(dienTu);

        List<SanPham> phuKienList = dienTu_model.getDanhSachTopSP("LayDanhSachTopPhuKien", "TOPPHUKIEN");
        List<ThuongHieu> topPhuKienList = dienTu_model.getDanhSachThuongHieu("LayDanhSachPhuKien", "DANHSACHPHUKIEN");

        DienTu dienTu1 = new DienTu();
        dienTu1.setThuongHieus(topPhuKienList);
        dienTu1.setSanPhams(phuKienList);
        dienTu1.setTennoibat("Phụ kiện");
        dienTu1.setTentopnoibat("Top phụ kiện");
        dienTu1.setThuonghieu(false);
        dienTus.add(dienTu1);

        List<SanPham> tienIchList = dienTu_model.getDanhSachTopSP("LayTopTienIch", "TOPTIENICH");
        List<ThuongHieu> toptienIchList = dienTu_model.getDanhSachThuongHieu("LayDanhSachTienIch", "DANHSACHTIENICH");

        DienTu dienTu2 = new DienTu();
        dienTu2.setThuongHieus(toptienIchList);
        dienTu2.setSanPhams(tienIchList);
        dienTu2.setTennoibat("Tiện ích");
        dienTu2.setTentopnoibat("Top video & tivi");
        dienTu2.setThuonghieu(false);
        dienTus.add(dienTu2);

        if(thuongHieus.size()>0 && sanPhams.size()>0)
        {
            viewDienTu.showDanhSach(dienTus);
        }
    }
}
