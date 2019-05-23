package com.example.shopthree.View.ChiTietSanPham;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopthree.Adapter.DanhGiaAdapter;
import com.example.shopthree.Adapter.ViewPagerSlideAdapter;
import com.example.shopthree.Model.ChiTietSanPham.ChiTietSanPham_Model;
import com.example.shopthree.Model.GioHang.GioHang_Model;
import com.example.shopthree.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.shopthree.Model.ObjectClass.ChiTietSanPham;
import com.example.shopthree.Model.ObjectClass.DanhGia;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.shopthree.R;
import com.example.shopthree.View.DangNhap_DangKy.DangNhapActivity;
import com.example.shopthree.View.DanhGia.DanhGiaActivity;
import com.example.shopthree.View.DanhGia.DanhSachDanhGiaActivity;
import com.example.shopthree.View.GioHang.GioHangActivity;
import com.example.shopthree.View.ThanhToan.ThanhToanActivity;
import com.example.shopthree.View.TheoDoiDonHang.ThoidoidonhangActivity;
import com.example.shopthree.View.TrangChu.TrangchuActivity;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, GoogleApiClient.OnConnectionFailedListener {

    ViewPager viewPager;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    TextView[] txtDots;
    LinearLayout layoutDots;
    List<Fragment> fragmentList;
    TextView txtTenSP, txtGiaTien, txtTenCHDongGoi, txtThongtinchitiet, txtVietdanhgia, txtXemtatcarnhanxet, txtGioHang, txtGiamGia, txtTranhthaimathang;
    Toolbar toolbar;
    ImageView imgXemthemchitiet;
    boolean isExpend = false;
    LinearLayout lnThongSo;
    int masp, soluongtonkho;
    List<DanhGia> danhGiaList;
    RecyclerView recyclerDanhgia;
    RatingBar rbDanhgia;
    ImageButton btnGiohang;
    SanPham sanPhamGioHang;
    Button btnMuangay;
    boolean onPause = false;
    Menu menu;
    MenuItem itlogin, itlogout;
    DanhGiaAdapter danhGiaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chi_tiet_san_pham_layout);

        viewPager = findViewById(R.id.viewpagerSlideShow);
        layoutDots = findViewById(R.id.layoutDots);
        txtTenSP = findViewById(R.id.txtTenSP);
        txtGiaTien = findViewById(R.id.txtGiaTien);
        toolbar = findViewById(R.id.toolbar);
        txtTenCHDongGoi = findViewById(R.id.txtTenCHDongGoi);
        txtThongtinchitiet = findViewById(R.id.txtThongtinchitiet);
        imgXemthemchitiet = findViewById(R.id.imgXemthemchitiet);
        lnThongSo = findViewById(R.id.lnThongSo);
        txtVietdanhgia = findViewById(R.id.txtVietdanhgia);
        recyclerDanhgia = findViewById(R.id.recyclerDanhgia);
        txtXemtatcarnhanxet = findViewById(R.id.txtXemtatcarnhanxet);
        rbDanhgia = findViewById(R.id.rbDanhgia);
        btnGiohang = findViewById(R.id.btnGiohang);
        btnMuangay = findViewById(R.id.btnMuangay);
        txtGiamGia = findViewById(R.id.txtGiamGia);
        txtTranhthaimathang = findViewById(R.id.txtTranhthaimathang);
        danhGiaList = new ArrayList<>();

        setSupportActionBar(toolbar);

        final int masp = getIntent().getIntExtra("masp", 0);
        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.getChitirtSP(masp);
        presenterLogicChiTietSanPham.getDanhSachDanhGia(masp, 0);

        addDotsSlide(0);

        rbDanhgia.setRating(getSoSaoDanhGia(masp));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                addDotsSlide(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        txtVietdanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TrangchuActivity.accessToken == null && TrangchuActivity.googleSignInResult == null && TrangchuActivity.tennv.equals("") )
                {
                    AlertDialog.Builder alerdialog = new AlertDialog.Builder(ChiTietSanPhamActivity.this);
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
                    Intent iThemdanhgia = new Intent(ChiTietSanPhamActivity.this, DanhGiaActivity.class);
                    iThemdanhgia.putExtra("masp", masp);
                    startActivity(iThemdanhgia);
                }
            }
        });

        txtXemtatcarnhanxet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDanhSachDanhGia = new Intent(ChiTietSanPhamActivity.this, DanhSachDanhGiaActivity.class);
                iDanhSachDanhGia.putExtra("masp", masp);
                startActivity(iDanhSachDanhGia);
            }
        });

        btnGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(soluongtonkho <= 0)
                {
                    AlertDialog.Builder alerdialog = new AlertDialog.Builder(ChiTietSanPhamActivity.this);
                    alerdialog.setTitle("Thông báo")
                            .setMessage("Hiện tại sản phẩm đã hết hàng")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    alerdialog.show();
                }
                else {
                    FragmentSlideChitietSP fragment = (FragmentSlideChitietSP) fragmentList.get(0);
                    View view = fragment.mView;
                    ImageView imageView = view.findViewById(R.id.imgSlide);
                    Bitmap bitmap = Bitmap.createBitmap(((BitmapDrawable) imageView.getDrawable()).getBitmap());

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] hinhgiohang = byteArrayOutputStream.toByteArray();

                    sanPhamGioHang.setHINHGIOHANG(hinhgiohang);
                    sanPhamGioHang.setSOLUONG(1);

                    presenterLogicChiTietSanPham.ThenGioHang(sanPhamGioHang, ChiTietSanPhamActivity.this);
                }

            }
        });

        btnMuangay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TrangchuActivity.accessToken == null && TrangchuActivity.googleSignInResult == null && TrangchuActivity.tennv.equals("") )
                {
                    AlertDialog.Builder alerdialog = new AlertDialog.Builder(ChiTietSanPhamActivity.this);
                    alerdialog.setTitle("Thông báo")
                            .setMessage("Bạn chưa đăng nhập")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    alerdialog.show();
                }
                else if(soluongtonkho <= 0)
                {
                    AlertDialog.Builder alerdialog = new AlertDialog.Builder(ChiTietSanPhamActivity.this);
                    alerdialog.setTitle("Thông báo")
                            .setMessage("Hiện tại sản phẩm đã hết hàng")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    alerdialog.show();
                }
                else {
                    FragmentSlideChitietSP fragment = (FragmentSlideChitietSP) fragmentList.get(0);
                    View view = fragment.mView;
                    ImageView imageView = view.findViewById(R.id.imgSlide);
                    Bitmap bitmap = Bitmap.createBitmap(((BitmapDrawable) imageView.getDrawable()).getBitmap());

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] hinhgiohang = byteArrayOutputStream.toByteArray();

                    sanPhamGioHang.setHINHGIOHANG(hinhgiohang);
                    sanPhamGioHang.setSOLUONG(1);

                    presenterLogicChiTietSanPham.ThenGioHang(sanPhamGioHang, ChiTietSanPhamActivity.this);

                    Intent iThanhToan = new Intent(ChiTietSanPhamActivity.this, ThanhToanActivity.class);
                    startActivity(iThanhToan);
                }
            }
        });
    }


    @Override
    public void showChiTietSanPham(final SanPham sanPham) {

        masp = sanPham.getMASP();
        soluongtonkho = sanPham.getSOLUONGTONKHO();

        sanPhamGioHang = sanPham;
        sanPhamGioHang.setSOLUONGTONKHO(sanPham.getSOLUONG());
        txtTenSP.setText(sanPham.getTENSP());
        if(sanPham.getSOLUONGTONKHO() > 0)
        {
            txtTranhthaimathang.setText("Còn " + sanPham.getSOLUONGTONKHO() + " sản phẩm");
            txtTranhthaimathang.setTextColor(Color.BLACK);
        }
        else
        {
            txtTranhthaimathang.setText("Hết hàng ");
            txtTranhthaimathang.setTextColor(Color.parseColor("#D81313"));
        }

        ChiTietKhuyenMai chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();
        long giatien = sanPham.getGIA();

        if(chiTietKhuyenMai != null)
        {
            int phantramkm = chiTietKhuyenMai.getPHANTRAMKM();

            if(phantramkm != 0) {
                NumberFormat numberFormat = new DecimalFormat("###,###");
                String gia = numberFormat.format(giatien);

                txtGiamGia.setVisibility(View.VISIBLE);
                txtGiamGia.setPaintFlags(txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                txtGiamGia.setText(gia + " VNĐ");
                giatien = giatien * phantramkm / 100;
            }

        }

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        txtGiaTien.setText(gia + " VNĐ");

        txtTenCHDongGoi.setText(sanPham.getTENNV());
        if(sanPham.getTHONGTIN().length() > 0) {
            txtThongtinchitiet.setText(sanPham.getTHONGTIN().substring(0, 100));
        }
        else
        {
            txtThongtinchitiet.setText("");
        }

        if(sanPham.getTHONGTIN().length() < 100)
        {
            imgXemthemchitiet.setVisibility(View.GONE);
        }
        else
        {
            imgXemthemchitiet.setVisibility(View.VISIBLE);
            imgXemthemchitiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isExpend = !isExpend;
                    if(isExpend)
                    {
                        Drawable drawable = ContextCompat.getDrawable(ChiTietSanPhamActivity.this, R.drawable.icon_keyboard_arrow_up_black);
                        txtThongtinchitiet.setText(sanPham.getTHONGTIN());
                        imgXemthemchitiet.setImageDrawable(drawable);
                        lnThongSo.setVisibility(View.VISIBLE);
                        showThongSo(sanPham);
                    }
                    else
                    {
                        Drawable drawable = ContextCompat.getDrawable(ChiTietSanPhamActivity.this, R.drawable.icon_keyboard_arrow_down_black);
                        txtThongtinchitiet.setText(sanPham.getTHONGTIN().substring(0, 100));
                        imgXemthemchitiet.setImageDrawable(drawable);
                        lnThongSo.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    private void showThongSo(SanPham sanPham)
    {
        List<ChiTietSanPham> chiTietSanPhams = sanPham.getChiTietSanPhamList();

        lnThongSo.removeAllViews();

        TextView txtTieuDeThongSo = new TextView(this);
        txtTieuDeThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        txtTieuDeThongSo.setText("Thông số kỹ thuật");
        lnThongSo.addView(txtTieuDeThongSo);

        for(int i=0; i<chiTietSanPhams.size(); i++) {
            LinearLayout lnChitiet = new LinearLayout(this);
            lnChitiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lnChitiet.setOrientation(LinearLayout.HORIZONTAL);

            TextView txtTenChitiet = new TextView(this);
            txtTenChitiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txtTenChitiet.setText(chiTietSanPhams.get(i).getTENCHITIET());

            TextView txtGiaTriChitiet = new TextView(this);
            txtGiaTriChitiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txtGiaTriChitiet.setText(chiTietSanPhams.get(i).getGIATRI());

            lnChitiet.addView(txtTenChitiet);
            lnChitiet.addView(txtGiaTriChitiet);

            lnThongSo.addView(lnChitiet);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;

        getMenuInflater().inflate(R.menu.trangchu_menu, menu);

        MenuItem itGiohang = this.menu.findItem(R.id.itshoppingcart);
        View customGiohang = MenuItemCompat.getActionView(itGiohang);
        txtGioHang = customGiohang.findViewById(R.id.txtSoLuongSPGioHang);

        txtGioHang.setText(presenterLogicChiTietSanPham.countSoSPTrongGioHang(this) + "");

        customGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGiohang = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(iGiohang);
            }
        });

        itlogin = menu.findItem(R.id.itlogin);
        itlogout = menu.findItem(R.id.itlogout);

        if(TrangchuActivity.tennv != null && !TrangchuActivity.tennv.equals(""))
        {
            itlogin.setTitle(TrangchuActivity.tennv);
            itlogout.setVisible(true);
        }
        else
        {
            if(TrangchuActivity.username != null && !TrangchuActivity.username.equals(""))
            {
                itlogin.setTitle(TrangchuActivity.username);
                itlogout.setVisible(true);
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.itlogin:
                if (TrangchuActivity.accessToken == null && TrangchuActivity.googleSignInResult == null && TrangchuActivity.tennv.equals("")) {
                    Intent iDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(iDangNhap);
                }
                break;

            case R.id.itlogout:
                AlertDialog.Builder alerdialog = new AlertDialog.Builder(ChiTietSanPhamActivity.this);
                alerdialog.setTitle("Thông báo")
                        .setMessage("Bạn chưa đăng nhập")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (TrangchuActivity.accessToken != null) {
                                    LoginManager.getInstance().logOut();
                                    ChiTietSanPhamActivity.this.menu.clear();
                                    TrangchuActivity.username = null;
                                    ChiTietSanPhamActivity.this.onCreateOptionsMenu(ChiTietSanPhamActivity.this.menu);
                                }

                                if (TrangchuActivity.googleSignInResult != null) {
                                    Auth.GoogleSignInApi.signOut(TrangchuActivity.mGoogleApiClient);
                                    ChiTietSanPhamActivity.this.menu.clear();
                                    TrangchuActivity.username = null;
                                    ChiTietSanPhamActivity.this.onCreateOptionsMenu(ChiTietSanPhamActivity.this.menu);
                                }

                                if (!TrangchuActivity.tennv.equals("")) {
                                    TrangchuActivity.dangNhapmodel.updateCachedDangnhap(ChiTietSanPhamActivity.this, "", 0);
                                    ChiTietSanPhamActivity.this.menu.clear();
                                    TrangchuActivity.tennv = "";
                                    ChiTietSanPhamActivity.this.onCreateOptionsMenu(ChiTietSanPhamActivity.this.menu);
                                }
                                GioHang_Model gioHang_model = new GioHang_Model();
                                gioHang_model.MoKetNoiSQL(ChiTietSanPhamActivity.this);
                                List<SanPham> sanPhams = gioHang_model.getDanhSachSPGioHang();
                                for(int i=0;i<sanPhams.size(); i++)
                                {
                                    gioHang_model.deleteSPGioHang(sanPhams.get(i).getMASP());
                                }
                                sanPhams.clear();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                alerdialog.show();

                break;

            case R.id.itconten:
                Intent iTheodoidonhang = new Intent(ChiTietSanPhamActivity.this, ThoidoidonhangActivity.class);
                startActivity(iTheodoidonhang);
                break;
        }
        return true;
    }

    @Override
    public void showSlideSanPham(String[] linkhinhsp) {
        fragmentList = new ArrayList<>();
        for(int i=0; i<linkhinhsp.length; i++)
        {
            FragmentSlideChitietSP fragmentSlideChitietSP = new FragmentSlideChitietSP();
            Bundle bundle = new Bundle();
            bundle.putString("linkhinh", TrangchuActivity.SERVER + linkhinhsp[i]);
            fragmentSlideChitietSP.setArguments(bundle);

            fragmentList.add(fragmentSlideChitietSP);
        }

        ViewPagerSlideAdapter viewPagerSlideAdapter = new ViewPagerSlideAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(viewPagerSlideAdapter);
        viewPagerSlideAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDanhSachDanhGia(List<DanhGia> danhGiaList) {

        this.danhGiaList = danhGiaList;
        danhGiaAdapter = new DanhGiaAdapter(this, this.danhGiaList, 3);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerDanhgia.setLayoutManager(layoutManager);
        recyclerDanhgia.setAdapter(danhGiaAdapter);

        danhGiaAdapter.notifyDataSetChanged();
    }

    @Override
    public void addGioHangThanhCong() {
        Toast.makeText(this, "Sản phẩm đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        txtGioHang.setText(presenterLogicChiTietSanPham.countSoSPTrongGioHang(this) + "");
    }

    @Override
    public void addGioHangThatBai() {
        Toast.makeText(this, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
    }

    private void addDotsSlide(int position)
    {
        txtDots = new TextView[fragmentList.size()];
        layoutDots.removeAllViews();
        for(int i=0; i<fragmentList.size(); i++)
        {
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));
            txtDots[i].setTextSize(40);
            txtDots[i].setTextColor(Color.GRAY);

            layoutDots.addView(txtDots[i]);
        }

        txtDots[position].setTextColor(Color.parseColor("#db92e8"));
    }

    private float getSoSaoDanhGia(int masp)
    {
        int tongsosao = 0;
        float sosaoTB = 0;
        List<DanhGia> danhGiaList1 = new ArrayList<>();
        ChiTietSanPham_Model chiTietSanPham_model = new ChiTietSanPham_Model();
        danhGiaList1 = chiTietSanPham_model.getDanhSachDanhGia(masp, 0);
        if(danhGiaList1.size() > 0) {
            for (DanhGia danhGia : danhGiaList1) {
                tongsosao += danhGia.getSOSAO();
            }
            sosaoTB = tongsosao / danhGiaList1.size();
        }
        return sosaoTB;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(onPause)
        {
            txtGioHang.setText(presenterLogicChiTietSanPham.countSoSPTrongGioHang(this) + "");
            this.danhGiaList.clear();
            danhGiaAdapter = new DanhGiaAdapter(this, this.danhGiaList, 3);
            danhGiaAdapter.notifyDataSetChanged();
            presenterLogicChiTietSanPham.getDanhSachDanhGia(masp, 0);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
