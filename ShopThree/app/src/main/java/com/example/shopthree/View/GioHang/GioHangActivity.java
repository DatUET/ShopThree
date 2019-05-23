package com.example.shopthree.View.GioHang;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shopthree.Adapter.GioHangAdapter;
import com.example.shopthree.Model.DangNhap_DangKy.DangNhap_Model;
import com.example.shopthree.Model.GioHang.GioHang_Model;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.Presenter.GioHang.PresenterLogicGioHang;
import com.example.shopthree.R;
import com.example.shopthree.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.example.shopthree.View.DangNhap_DangKy.DangNhapActivity;
import com.example.shopthree.View.ThanhToan.ThanhToanActivity;
import com.example.shopthree.View.TrangChu.TrangchuActivity;

import java.util.ArrayList;
import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang {

    RecyclerView recyclerGiohang;
    PresenterLogicGioHang presenterLogicGioHang;
    Toolbar toolbar;
    Button btnMuangay;
    boolean onPause = false;
    List<SanPham> sanPhamList;
    DangNhap_Model dangNhap_model = new DangNhap_Model();
    String tennv = "";
    int manv = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gio_hang_layout);

        addCotrol();
        addEvent();

    }

    private void addEvent() {
        btnMuangay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(TrangchuActivity.accessToken == null && TrangchuActivity.googleSignInResult == null && TrangchuActivity.tennv.equals("") )
                {
                    AlertDialog.Builder alerdialog = new AlertDialog.Builder(GioHangActivity.this);
                    alerdialog.setTitle("Thông báo")
                            .setMessage("Bạn chưa đăng nhập")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    alerdialog.show();
                }
                else {
                    if (sanPhamList.size() > 0 && !tennv.equals("")) {
                        Intent iThanhtoan = new Intent(GioHangActivity.this, ThanhToanActivity.class);
                        startActivity(iThanhtoan);
                    } else {
                        if (sanPhamList.size() == 0) {
                            Toast.makeText(GioHangActivity.this, "Bạn chưa có sản phẩm trong giỏ hàng", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(GioHangActivity.this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(GioHangActivity.this, DangNhapActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }

    private void addCotrol() {
        sanPhamList = new ArrayList<>();
        tennv = dangNhap_model.getCachedDangnhap(this);
        manv = dangNhap_model.getCacheManvDangnhap(this);

        recyclerGiohang = findViewById(R.id.recyclerGiohang);
        presenterLogicGioHang = new PresenterLogicGioHang(this);
        presenterLogicGioHang.getDanhSachSPTrongGioHang(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnMuangay = findViewById(R.id.btnMuangay);

    }

    @Override
    public void showDanhSachSPTrongGioHang(List<SanPham> sanPhamList) {
        this.sanPhamList = sanPhamList;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        GioHangAdapter gioHangAdapter = new GioHangAdapter(this, sanPhamList);
        recyclerGiohang.setLayoutManager(layoutManager);
        recyclerGiohang.setAdapter(gioHangAdapter);
        gioHangAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(onPause)
        {
            GioHang_Model gioHang_model = new GioHang_Model();
            gioHang_model.MoKetNoiSQL(this);
            this.sanPhamList = gioHang_model.getDanhSachSPGioHang();
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            GioHangAdapter gioHangAdapter = new GioHangAdapter(this, sanPhamList);
            recyclerGiohang.setLayoutManager(layoutManager);
            recyclerGiohang.setAdapter(gioHangAdapter);
            gioHangAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }
}
