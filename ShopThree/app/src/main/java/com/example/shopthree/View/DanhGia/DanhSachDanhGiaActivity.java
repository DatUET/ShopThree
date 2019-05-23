package com.example.shopthree.View.DanhGia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.shopthree.Adapter.DanhGiaAdapter;
import com.example.shopthree.Model.ObjectClass.DanhGia;
import com.example.shopthree.Model.ObjectClass.ILoadMore;
import com.example.shopthree.Model.ObjectClass.LoadMoreScroll;
import com.example.shopthree.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.shopthree.R;

import java.util.ArrayList;
import java.util.List;

public class DanhSachDanhGiaActivity extends AppCompatActivity implements ViewDanhGia, ILoadMore {

    RecyclerView recyclerDanhsachdanhgia;
    ProgressBar progressBar;
    int masp = 0;
    PresenterLogicDanhGia presenterLogicDanhGia;
    List<DanhGia> allDanhGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_danh_gia_layout);

        recyclerDanhsachdanhgia = findViewById(R.id.recyclerDanhsachdanhgia);
        progressBar = findViewById(R.id.progress_bar);

        masp = getIntent().getIntExtra("masp", 0);
        allDanhGia = new ArrayList<>();

        presenterLogicDanhGia = new PresenterLogicDanhGia(this);
        presenterLogicDanhGia.getDanhSachDanhGiaTheoSP(masp, 0, progressBar);

    }

    @Override
    public void DanhGiaThanhCong() {

    }

    @Override
    public void DanhGiaThatBai() {

    }

    @Override
    public void showDanhSachDanhGiaTheoSP(List<DanhGia> danhGiaList) {
        allDanhGia.addAll(danhGiaList);

        DanhGiaAdapter danhGiaAdapter = new DanhGiaAdapter(this, allDanhGia, 0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerDanhsachdanhgia.setLayoutManager(layoutManager);
        recyclerDanhsachdanhgia.setAdapter(danhGiaAdapter);
        recyclerDanhsachdanhgia.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        danhGiaAdapter.notifyDataSetChanged();
    }

    @Override
    public void LoadMore(int tongitem) {
        presenterLogicDanhGia.getDanhSachDanhGiaTheoSP(masp, tongitem, progressBar);
    }
}
