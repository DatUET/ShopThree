package com.example.shopthree.Presenter.HienThiSanPhamTheoDanhMuc;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.shopthree.Model.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMuc_Model;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.View.TrangChu.ViewHienThiSanPhamTheoDanhMuc;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicHienThiSanPhamTheoDanhMuc implements IPresenterHienThiSanPhamTheoDanhMuc {

    ViewHienThiSanPhamTheoDanhMuc viewHienThiSanPhamTheoDanhMuc;
    HienThiSanPhamTheoDanhMuc_Model hienThiSanPhamTheoDanhMuc_model;

    public PresenterLogicHienThiSanPhamTheoDanhMuc(ViewHienThiSanPhamTheoDanhMuc viewHienThiSanPhamTheoDanhMuc) {
        this.viewHienThiSanPhamTheoDanhMuc = viewHienThiSanPhamTheoDanhMuc;
        hienThiSanPhamTheoDanhMuc_model = new HienThiSanPhamTheoDanhMuc_Model();
    }

    @Override
    public void getDanhSachSanPhamTheoMaLoai(int masp, boolean check) {
        List<SanPham> sanPhamList = new ArrayList<>();
        if(check)
        {
            sanPhamList = hienThiSanPhamTheoDanhMuc_model.getDanhSachSPTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoMaThuongHieu", 0);
        }
        else {
            sanPhamList = hienThiSanPhamTheoDanhMuc_model.getDanhSachSPTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoMaLoaiDanhMuc", 0);
        }
        if(sanPhamList.size() > 0)
        {
            viewHienThiSanPhamTheoDanhMuc.HienThiDanhSachSP(sanPhamList);
        }
        else
        {
            viewHienThiSanPhamTheoDanhMuc.LoiHienThiDanhSachSP();
        }
    }

    public List<SanPham> getDanhSachSanPhamTheoMaLoaiLoadMore(int masp, boolean check, int limit, ProgressBar progressBar)
    {
        progressBar.setVisibility(View.VISIBLE);
        List<SanPham> sanPhamList = new ArrayList<>();
        if(check)
        {
            sanPhamList = hienThiSanPhamTheoDanhMuc_model.getDanhSachSPTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoMaThuongHieu", limit);
        }
        else {
            sanPhamList = hienThiSanPhamTheoDanhMuc_model.getDanhSachSPTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoMaLoaiDanhMuc", limit);
        }
        if(sanPhamList.size() >= 0)
        {
            progressBar.setVisibility(View.GONE);
        }
        return sanPhamList;
    }
}
