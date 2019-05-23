package com.example.shopthree.View.TheoDoiDonHang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.shopthree.Adapter.TheoDoiDonHangAdapter;
import com.example.shopthree.Model.ObjectClass.HoaDon;
import com.example.shopthree.Presenter.TheoDoiDonHang.PresenterLogicTheoDoiDonHang;
import com.example.shopthree.R;
import com.example.shopthree.View.TrangChu.TrangchuActivity;

import java.util.List;

public class ThoidoidonhangActivity extends AppCompatActivity implements ViewTheoDoiDonHang{

    RecyclerView recyclerTheodoidonhang;
    PresenterLogicTheoDoiDonHang presenterLogicTheoDoiDonHang;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theodoidonhang_layout);

        addControl();
        addEvent();
    }

    private void addEvent() {

    }

    private void addControl() {
        int manv = TrangchuActivity.manv;
        recyclerTheodoidonhang = findViewById(R.id.recyclerTheodoidonhang);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenterLogicTheoDoiDonHang = new PresenterLogicTheoDoiDonHang(this);
        presenterLogicTheoDoiDonHang.getHoaDon(manv);
    }

    @Override
    public void showDanhSachHoaDon(List<HoaDon> hoaDonList) {
        TheoDoiDonHangAdapter theoDoiDonHangAdapter = new TheoDoiDonHangAdapter(this, hoaDonList, R.layout.custom_theodoidonhang_layout);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerTheodoidonhang.setLayoutManager(layoutManager);
        recyclerTheodoidonhang.setAdapter(theoDoiDonHangAdapter);

        theoDoiDonHangAdapter.notifyDataSetChanged();
    }
}
