package com.example.shopthree.View.HienThiSanPhamTheoDanhMuc;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shopthree.Adapter.TopDienTuAdapter;
import com.example.shopthree.Model.ObjectClass.ILoadMore;
import com.example.shopthree.Model.ObjectClass.LoadMoreScroll;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.shopthree.Presenter.HienThiSanPhamTheoDanhMuc.PresenterLogicHienThiSanPhamTheoDanhMuc;
import com.example.shopthree.R;
import com.example.shopthree.View.GioHang.GioHangActivity;
import com.example.shopthree.View.TrangChu.TrangchuActivity;
import com.example.shopthree.View.TrangChu.ViewHienThiSanPhamTheoDanhMuc;

import java.util.List;

public class HienThiSanPhamTheoDanhMucActivity extends Fragment implements ViewHienThiSanPhamTheoDanhMuc, ILoadMore {

    RecyclerView recyclerHienThiSPTheoDanhMuc;
    Button btnThayDoiTrangThaiRecycler;
    boolean gridView = true;
    RecyclerView.LayoutManager layoutManager;
    PresenterLogicHienThiSanPhamTheoDanhMuc sanPhamTheoDanhMuc;
    int maloai;
    boolean check;
    TopDienTuAdapter topDienTuAdapter;
    Toolbar toolbar;
    List<SanPham> sanPhamList1;
    ProgressBar progressBar;
    TextView txtGioHang;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    Menu menu;
    boolean onPause = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.hien_thi_san_pham_theo_danh_muc_layout, container, false);

        setHasOptionsMenu(false);

        recyclerHienThiSPTheoDanhMuc = view.findViewById(R.id.recyclerHienThiSPTheoDanhMuc);
        btnThayDoiTrangThaiRecycler = view.findViewById(R.id.btnThayDoiTrangThaiRecycler);
        toolbar = view.findViewById(R.id.toolbar);
        progressBar = view.findViewById(R.id.progessloadmore);
        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();

        Bundle bundle = getArguments();
        maloai = bundle.getInt("MALOAI", 0);
        String tenloai = bundle.getString("TENLOAI");
        check = bundle.getBoolean("CHECK", false);

        sanPhamTheoDanhMuc = new PresenterLogicHienThiSanPhamTheoDanhMuc(this);
        sanPhamTheoDanhMuc.getDanhSachSanPhamTheoMaLoai(maloai, check);

        ///////////////////////////////////////////////////
        btnThayDoiTrangThaiRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridView = !gridView;
                sanPhamTheoDanhMuc.getDanhSachSanPhamTheoMaLoai(maloai, check);
            }
        });

        toolbar.setTitle(tenloai);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack("TrangchuActivity", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        return view;
    }


    @Override
    public void HienThiDanhSachSP(List<SanPham> sanPhamList) {
        sanPhamList1 = sanPhamList;
        if (gridView)
        {
            layoutManager = new GridLayoutManager(getContext(), 2);
            topDienTuAdapter = new TopDienTuAdapter(getContext(), R.layout.custom_top_dienthoai_maytinhbang_layout, sanPhamList1);
        }
        else
        {
            layoutManager = new LinearLayoutManager(getContext());
            topDienTuAdapter = new TopDienTuAdapter(getContext(), R.layout.custom_list_topdienthoaivamaytinhbang_layout, sanPhamList1);
        }
        recyclerHienThiSPTheoDanhMuc.setLayoutManager(layoutManager);
        recyclerHienThiSPTheoDanhMuc.setAdapter(topDienTuAdapter);
        recyclerHienThiSPTheoDanhMuc.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        topDienTuAdapter.notifyDataSetChanged();

    }

    @Override
    public void LoiHienThiDanhSachSP() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.trangchu_menu, menu);

        this.menu = menu;

        MenuItem itGiohang = this.menu.findItem(R.id.itshoppingcart);
        View customGiohang = MenuItemCompat.getActionView(itGiohang);
        txtGioHang = customGiohang.findViewById(R.id.txtSoLuongSPGioHang);

        txtGioHang.setText(presenterLogicChiTietSanPham.countSoSPTrongGioHang(getContext()) + "");

        customGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGiohang = new Intent(getContext(), GioHangActivity.class);
                startActivity(iGiohang);
            }
        });
    }

    @Override
    public void LoadMore(int tongitem) {
        List<SanPham> listSPloadmore = sanPhamTheoDanhMuc.getDanhSachSanPhamTheoMaLoaiLoadMore(maloai, check, tongitem, progressBar);
        sanPhamList1.addAll(listSPloadmore);

        recyclerHienThiSPTheoDanhMuc.post(new Runnable() {
            @Override
            public void run() {
                topDienTuAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

        if(onPause) {
            txtGioHang.setText(presenterLogicChiTietSanPham.countSoSPTrongGioHang(getContext()) + "");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(txtGioHang != null) {
            onPause = true;
        }
    }
}
