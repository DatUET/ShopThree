package com.example.shopthree.View.DangNhap_DangKy;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.shopthree.Adapter.ViewPagerAdapterDangNhap;
import com.example.shopthree.R;

public class DangNhapActivity extends AppCompatActivity {

    TabLayout tablogin;
    ViewPager viewPagerlogin;
    Toolbar toolbarDangnhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap_layout);

        addControl();
    }

    private void addControl() {
        tablogin = findViewById(R.id.tabLogin);
        viewPagerlogin = findViewById(R.id.viewPagerlogin);
        toolbarDangnhap = findViewById(R.id.toolbarDangnhap);

        setSupportActionBar(toolbarDangnhap);

        ViewPagerAdapterDangNhap viewPagerAdapterDangNhap = new ViewPagerAdapterDangNhap(getSupportFragmentManager());
        viewPagerlogin.setAdapter(viewPagerAdapterDangNhap);
        viewPagerAdapterDangNhap.notifyDataSetChanged();

        tablogin.setupWithViewPager(viewPagerlogin);
    }
}
