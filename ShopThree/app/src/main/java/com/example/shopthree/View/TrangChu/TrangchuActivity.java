package com.example.shopthree.View.TrangChu;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shopthree.Adapter.DanhGiaAdapter;
import com.example.shopthree.Adapter.ExpanAdapter;
import com.example.shopthree.Adapter.TheoDoiDonHangAdapter;
import com.example.shopthree.Adapter.ViewpagerAdapter;
import com.example.shopthree.Model.DangNhap_DangKy.DangNhap_Model;
import com.example.shopthree.Model.GioHang.GioHang_Model;
import com.example.shopthree.Model.ObjectClass.Loaisanpham;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.shopthree.Presenter.TrangChu.XulyMenu.PresenterLogicXulyMenu;
import com.example.shopthree.R;
import com.example.shopthree.View.DangNhap_DangKy.DangNhapActivity;
import com.example.shopthree.View.GioHang.GioHangActivity;
import com.example.shopthree.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;
import com.example.shopthree.View.TheoDoiDonHang.ThoidoidonhangActivity;
import com.example.shopthree.View.TimKiem.TimKiemActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.karan.churi.PermissionManager.PermissionManager;

import org.json.JSONObject;

import java.util.List;


public class TrangchuActivity extends AppCompatActivity implements ViewXulyMenu, GoogleApiClient.OnConnectionFailedListener, AppBarLayout.OnOffsetChangedListener {

    public static final String SERVER_NAME = "http://10.0.3.2/shopthree/loaisanpham.php";
    public static final String SERVER = "http://10.0.3.2/shopthree";

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    PresenterLogicXulyMenu logicXulyMenu;
    public static String username;
    public static String tennv;
    public static int manv;
    public static AccessToken accessToken;
    Menu menu;
    public static DangNhap_Model dangNhapmodel;
    MenuItem itlogin, itLogout;
    public static GoogleApiClient mGoogleApiClient;
    public static GoogleSignInResult googleSignInResult;
    AppBarLayout appbar;
    CollapsingToolbarLayout collaps_toolbar;
    TextView txtGioHang;
    Button btnTimkiem;
    LinearLayout lnlayout;
    boolean onPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.trangchu_layout);

        addCotrol();
    }

    private void addCotrol() {
        dangNhapmodel = new DangNhap_Model();
        mGoogleApiClient = dangNhapmodel.getGoogleApiClient(this, this);
        appbar = findViewById(R.id.appbar);
        collaps_toolbar = findViewById(R.id.collaps_toolbar);
        btnTimkiem = findViewById(R.id.btnTimkiem);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = findViewById(R.id.tablauout);
        viewPager = findViewById(R.id.viewpager);
        expandableListView = findViewById(R.id.epmenu);

        ViewpagerAdapter adapter = new ViewpagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        drawerLayout = findViewById(R.id.drawerlayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        logicXulyMenu = new PresenterLogicXulyMenu(this);
        logicXulyMenu.getDanhSachMenu();

        appbar.addOnOffsetChangedListener(this);

        btnTimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lnlayout.getAlpha() == 1.0)
                {
                    Intent iTimkiem = new Intent(TrangchuActivity.this, TimKiemActivity.class);
                    startActivity(iTimkiem);
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.trangchu_menu, menu);
        this.menu = menu;

        PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
        MenuItem itGiohang = this.menu.findItem(R.id.itshoppingcart);
        View customGiohang = MenuItemCompat.getActionView(itGiohang);
        txtGioHang = customGiohang.findViewById(R.id.txtSoLuongSPGioHang);
        txtGioHang.setText(presenterLogicChiTietSanPham.countSoSPTrongGioHang(this) + "");

        customGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGiohang = new Intent(TrangchuActivity.this, GioHangActivity.class);
                startActivity(iGiohang);
            }
        });

        itlogin = menu.findItem(R.id.itlogin);
        itLogout = menu.findItem(R.id.itlogout);

        accessToken = logicXulyMenu.getTenUserFB();
        googleSignInResult = dangNhapmodel.getInformationUserGoogle(mGoogleApiClient);

        if(accessToken != null) {
            GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        username = object.getString("name");
                        itlogin.setTitle(username);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });


            Bundle parameter = new Bundle();
            parameter.putString("fields", "name");
            request.setParameters(parameter);
            request.executeAsync();
        }

        if(googleSignInResult != null)
        {
            username = googleSignInResult.getSignInAccount().getDisplayName();
            Log.d("Name", username);
            itlogin.setTitle(username);
        }

        tennv = dangNhapmodel.getCachedDangnhap(this);
        manv = dangNhapmodel.getCacheManvDangnhap(this);
        if(!tennv.equals(""))
        {
            itlogin.setTitle(tennv);
        }

        if(accessToken != null || googleSignInResult != null || !tennv.equals(""))
        {
            itLogout.setVisible(true);
        }

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();
        switch (id)
        {
            case R.id.itlogin:
                if(accessToken == null && googleSignInResult == null && dangNhapmodel.getCachedDangnhap(this).equals("")) {
                    Intent iDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(iDangNhap);
                }
                break;

            case R.id.itlogout:
                AlertDialog.Builder alerdialog = new AlertDialog.Builder(TrangchuActivity.this);
                alerdialog.setTitle("Thông báo")
                        .setMessage("Bạn có muốn đăng xuất?\nĐăng xuất có thể làm mất sản phẩm trong giỏ hàng của bạn")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(accessToken != null)
                                {
                                    LoginManager.getInstance().logOut();
                                    TrangchuActivity.this.menu.clear();
                                    TrangchuActivity.this.onCreateOptionsMenu(TrangchuActivity.this.menu);
                                }

                                if(googleSignInResult != null)
                                {
                                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                                    TrangchuActivity.this.menu.clear();
                                    TrangchuActivity.this.onCreateOptionsMenu(TrangchuActivity.this.menu);
                                }

                                if(!dangNhapmodel.getCachedDangnhap(TrangchuActivity.this).equals(""))
                                {
                                    dangNhapmodel.updateCachedDangnhap(TrangchuActivity.this, "", 0);
                                    TrangchuActivity.this.menu.clear();
                                    TrangchuActivity.this.onCreateOptionsMenu(TrangchuActivity.this.menu);
                                }
                                GioHang_Model gioHang_model = new GioHang_Model();
                                gioHang_model.MoKetNoiSQL(TrangchuActivity.this);
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

            case R.id.itsearch:
                Intent iTimKiem = new Intent(TrangchuActivity.this, TimKiemActivity.class);
                startActivity(iTimKiem);
                break;
            case R.id.itshoppingcart:
                Intent iGioHang = new Intent(TrangchuActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
                break;
            case R.id.itconten:
                Intent iTheodoidonhang = new Intent(TrangchuActivity.this, ThoidoidonhangActivity.class);
                startActivity(iTheodoidonhang);
                break;
        }
        return true;
    }

    @Override
    public void showDanhSachSP(List<Loaisanpham> dsloaisanpham) {
        ExpanAdapter expandAdapter = new ExpanAdapter(TrangchuActivity.this, dsloaisanpham);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

        if(collaps_toolbar.getHeight() + i <= 1.5 * ViewCompat.getMinimumHeight(collaps_toolbar))
        {
            lnlayout = appBarLayout.findViewById(R.id.lnsearch);
            lnlayout.animate().alpha(0).setDuration(200);

            MenuItem itsearch = menu.findItem(R.id.itsearch);
            itsearch.setVisible(true);
        }
        else
        {
            lnlayout = appBarLayout.findViewById(R.id.lnsearch);
            lnlayout.animate().alpha(1).setDuration(200);

            try {
                MenuItem itsearch = menu.findItem(R.id.itsearch);
                itsearch.setVisible(false);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    protected void onResume() {
        super.onResume();
        if(onPause)
        {
            PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
            txtGioHang.setText(presenterLogicChiTietSanPham.countSoSPTrongGioHang(this) + "");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }
}
