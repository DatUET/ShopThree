package com.example.shopthree.View.ChiTietDonHang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.shopthree.Adapter.SPChiTietDonHangAdapter;
import com.example.shopthree.Model.ObjectClass.HoaDon;
import com.example.shopthree.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ChiTietDonHangActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtNguoinhan, txtDiachigiahang, txtSDT, txtNgaydathang, txtNgaygiahang, txtTonggiatien, txtTrangthai;
    RecyclerView recyclerTheodoidonhang;
    HoaDon hoaDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chi_tiet_don_hang_layout);

        addControl();
        addEvent();
    }

    private void addEvent() {
        setSupportActionBar(toolbar);

        txtNguoinhan.setText(hoaDon.getTENNGUOINHAN());
        txtDiachigiahang.setText(hoaDon.getDIACHI());
        txtSDT.setText(hoaDon.getSODT());
        txtNgaydathang.setText(hoaDon.getNGAYMUA());
        txtNgaygiahang.setText(hoaDon.getNGAYGIAO());
        txtTrangthai.setText(hoaDon.getTRANGTHAI());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String tonggiatien = numberFormat.format(hoaDon.getTONGGIATIEN());
        txtTonggiatien.setText( tonggiatien+ " VNĐ");
        txtTrangthai.setText(hoaDon.getTRANGTHAI());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        SPChiTietDonHangAdapter spChiTietDonHangAdapter = new SPChiTietDonHangAdapter(this, hoaDon.getChiTietHoaDonList(), R.layout.custom_list_sptrongdonhang_layout);
        recyclerTheodoidonhang.setLayoutManager(layoutManager);
        recyclerTheodoidonhang.setAdapter(spChiTietDonHangAdapter);

        spChiTietDonHangAdapter.notifyDataSetChanged();
    }

    private void addControl() {
        hoaDon = (HoaDon) getIntent().getSerializableExtra("hoadon");
        toolbar = findViewById(R.id.toolbar);
        txtNguoinhan = findViewById(R.id.txtNguoinhan);
        txtDiachigiahang = findViewById(R.id.txtDiachigiahang);
        txtSDT = findViewById(R.id.txtSDT);
        txtNgaydathang = findViewById(R.id.txtNgaydathang);
        txtNgaygiahang = findViewById(R.id.txtNgaygiahang);
        txtTonggiatien = findViewById(R.id.txtTonggiatien);
        txtTrangthai = findViewById(R.id.txtTrangthai);
        recyclerTheodoidonhang = findViewById(R.id.recyclerTheodoidonhang);
    }
}
