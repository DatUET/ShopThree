package com.example.shopthree.View.DanhGia;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.shopthree.Model.ObjectClass.DanhGia;
import com.example.shopthree.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.shopthree.R;
import com.example.shopthree.View.TrangChu.TrangchuActivity;

import java.util.List;

public class DanhGiaActivity extends AppCompatActivity implements ViewDanhGia {

    TextInputLayout inputTieudeDanhgia, inputNoidungDanhgia;
    EditText txtNoidungDanhgia, txtTieudeDanhgia;
    RatingBar rbDanhgia;
    int masp;
    int sosao = 0;
    Button btnDongy;
    PresenterLogicDanhGia presenterLogicDanhGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_gia_layout);

        addControl();
        addEvent();
    }

    private void addEvent() {
        rbDanhgia.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                sosao = (int) rating;
            }
        });

        btnDongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tennguoidung = "";
                if(TrangchuActivity.tennv != null && !TrangchuActivity.tennv.equals(""))
                {
                    tennguoidung = TrangchuActivity.tennv;
                }
                else if(TrangchuActivity.username != null && !TrangchuActivity.username.equals(""))
                {
                    tennguoidung = TrangchuActivity.username;
                }
                String tieude = txtTieudeDanhgia.getText().toString();
                String noidung = txtNoidungDanhgia.getText().toString();

                if(tieude.trim().length() > 0)
                {
                    inputTieudeDanhgia.setErrorEnabled(false);
                    inputTieudeDanhgia.setError("");
                }
                else
                {
                    inputTieudeDanhgia.setErrorEnabled(true);
                    inputTieudeDanhgia.setError("Bạn chưa nhập tiêu đề");
                }
                if(noidung.trim().length() > 0)
                {
                    inputNoidungDanhgia.setErrorEnabled(false);
                    inputNoidungDanhgia.setError("");
                }
                else
                {
                    inputNoidungDanhgia.setErrorEnabled(true);
                    inputNoidungDanhgia.setError("Bạn chưa nhập nội dung");
                }

                if(!inputTieudeDanhgia.isErrorEnabled() && !inputNoidungDanhgia.isErrorEnabled())
                {
                    DanhGia danhGia = new DanhGia();
                    danhGia.setMASP(masp);
                    danhGia.setNOIDUNG(noidung);
                    danhGia.setTIEUDE(tieude);
                    danhGia.setTENTHIETBI(tennguoidung);
                    danhGia.setSOSAO(sosao);

                    presenterLogicDanhGia.ThemDanhGia(danhGia);
                    finish();
                }
            }
        });
    }

    private void addControl() {
        inputTieudeDanhgia = findViewById(R.id.inputTieudeDanhgia);
        inputNoidungDanhgia = findViewById(R.id.inputNoidungDanhgia);
        txtTieudeDanhgia = findViewById(R.id.txtTieudeDanhgia);
        txtNoidungDanhgia = findViewById(R.id.txtNoidungDanhgia);
        rbDanhgia = findViewById(R.id.rbDanhgia);
        btnDongy = findViewById(R.id.btnDongy);

        masp = getIntent().getIntExtra("masp", 0);

        presenterLogicDanhGia = new PresenterLogicDanhGia(this);
    }

    @Override
    public void DanhGiaThanhCong() {
        Toast.makeText(this, "Đánh giá thành công", Toast.LENGTH_SHORT);
    }

    @Override
    public void DanhGiaThatBai() {
        Toast.makeText(this, "Bạn không thể đánh giá sản phẩm này", Toast.LENGTH_SHORT);
    }

    @Override
    public void showDanhSachDanhGiaTheoSP(List<DanhGia> danhGiaList) {

    }
}
