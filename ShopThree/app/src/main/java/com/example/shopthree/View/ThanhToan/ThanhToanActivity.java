package com.example.shopthree.View.ThanhToan;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopthree.Model.GioHang.GioHang_Model;
import com.example.shopthree.Model.ObjectClass.ChiTietHoaDon;
import com.example.shopthree.Model.ObjectClass.ChiTietSanPham;
import com.example.shopthree.Model.ObjectClass.HoaDon;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.Presenter.ThanhToan.PresenterLogicThanhToan;
import com.example.shopthree.R;
import com.example.shopthree.View.TrangChu.TrangchuActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements ViewThanhToan{

    Toolbar toolbar;
    EditText txtTennguoinhan, txtDiachi, txtSodienthoai;
    ImageButton btnNhantienkhigiaohang, btnChuyenkhoan;
    CheckBox cbThoathuan;
    Button btnThanhtoan;
    PresenterLogicThanhToan presenterLogicThanhToan;
    TextView txtNhantienkhigiaohang, txtChuyenkhoan, txtTongsotien;
    LinearLayout lnNhantienkhigiaohang, lnChuyenkhoan;
    int chonHinhThucThanhToan = -1;
    List<SanPham> sanPhamList;
    GioHang_Model gioHang_model;

    List<ChiTietHoaDon> hoaDonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanh_toan_layout);

        addControl();
        addEvent();
    }

    private void addEvent() {

        int tongsotien = 0;
        final int tonggiatien;
        for(int i=0;i<sanPhamList.size();i++)
        {
            long gia = sanPhamList.get(i).getGIA();
            int soluong = sanPhamList.get(i).getSOLUONG();

            tongsotien += gia * soluong;
        }
        tonggiatien = tongsotien;

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String tongsothanhtoan = numberFormat.format(tongsotien);
        txtTongsotien.setText(tongsothanhtoan + " VNĐ");

        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tennguoinhan = txtTennguoinhan.getText().toString();
                String diachi = txtDiachi.getText().toString();
                String sodt = txtSodienthoai.getText().toString();

                if(tennguoinhan.trim().length() > 0 && diachi.trim().length() > 0 && sodt.trim().length() > 0)
                {
                    if(cbThoathuan.isChecked())
                    {
                        if(chonHinhThucThanhToan == -1)
                        {
                            Toast.makeText(ThanhToanActivity.this, "Bạn chưa chọn hình thức thanh toán", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            HoaDon hoaDon = new HoaDon();
                            hoaDon.setTENNGUOINHAN(tennguoinhan);
                            hoaDon.setDIACHI(diachi);
                            hoaDon.setSODT(sodt);
                            hoaDon.setCHUYENKHOAN(chonHinhThucThanhToan);
                            hoaDon.setChiTietHoaDonList(hoaDonList);
                            hoaDon.setMANV(TrangchuActivity.manv);
                            hoaDon.setTONGGIATIEN(tonggiatien);
                            presenterLogicThanhToan.ThemHoaDon(hoaDon);
                        }
                    }
                    else
                    {
                        Toast.makeText(ThanhToanActivity.this, "Bạn chưa cam kết thông tin", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(ThanhToanActivity.this, "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNhantienkhigiaohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonHinhThucGiaoHang(txtNhantienkhigiaohang, txtChuyenkhoan);
                chonHinhThucThanhToan = 0;
            }
        });

        btnChuyenkhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonHinhThucGiaoHang(txtChuyenkhoan, txtNhantienkhigiaohang);
                chonHinhThucThanhToan = 1;
            }
        });

    }

    private void ChonHinhThucGiaoHang(TextView txtDuocChon, TextView txtKhongChon)
    {
        txtDuocChon.setTextColor(Color.parseColor("#FF673AB7"));
        txtKhongChon.setTextColor(Color.BLACK);
    }

    private void addControl() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtTennguoinhan = findViewById(R.id.txtTennguoinhan);
        txtDiachi = findViewById(R.id.txtDiachi);
        txtSodienthoai = findViewById(R.id.txtSodienthoai);
        btnNhantienkhigiaohang = findViewById(R.id.btnNhantienkhigiaohang);
        btnChuyenkhoan = findViewById(R.id.btnChuyenkhoan);
        cbThoathuan = findViewById(R.id.cbThoathuan);
        btnThanhtoan = findViewById(R.id.btnThanhtoan);
        txtNhantienkhigiaohang = findViewById(R.id.txtNhantienkhigiaohang);
        txtChuyenkhoan = findViewById(R.id.txtChuyenkhoan);
        lnNhantienkhigiaohang = findViewById(R.id.lnNhantienkhigiaohang);
        lnChuyenkhoan = findViewById(R.id.lnChuyenkhoan);
        txtTongsotien = findViewById(R.id.txtTongsotien);

        gioHang_model = new GioHang_Model();
        gioHang_model.MoKetNoiSQL(this);
        sanPhamList = gioHang_model.getDanhSachSPGioHang();

        presenterLogicThanhToan = new PresenterLogicThanhToan(this, this);
        presenterLogicThanhToan.getDanhSachSPTrongGioHang();
    }

    @Override
    public void DatHangThanhCong() {
        Toast.makeText(this, "Bạn đã đặt hàng thành công", Toast.LENGTH_SHORT).show();
        Intent iTrangchu = new Intent(this, TrangchuActivity.class);
        startActivity(iTrangchu);
    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(this, "Đặt hàng thất bại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDanhSachSPTrongGioHang(List<SanPham> sanPhamList) {

        for(int i=0; i<sanPhamList.size(); i++)
        {
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setMASP(sanPhamList.get(i).getMASP());
            chiTietHoaDon.setSOLUONG(sanPhamList.get(i).getSOLUONG());

            hoaDonList.add(chiTietHoaDon);
        }
    }
}
