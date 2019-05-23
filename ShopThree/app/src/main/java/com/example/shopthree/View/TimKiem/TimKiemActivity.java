package com.example.shopthree.View.TimKiem;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.shopthree.Adapter.TopDienTuAdapter;
import com.example.shopthree.Model.ObjectClass.ILoadMore;
import com.example.shopthree.Model.ObjectClass.LoadMoreScroll;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.Presenter.TimKiem.PresenterLogicTimKiem;
import com.example.shopthree.R;

import java.util.List;

public class TimKiemActivity extends AppCompatActivity implements ViewTimKiem, ILoadMore {

    Toolbar toolbar;
    RecyclerView recyclerTimkiem;
    SearchView searchView;
    PresenterLogicTimKiem presenterLogicTimKiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tim_kiem_layout);

        addControl();
        addEvent();
    }

    private void addEvent() {

    }

    private void addControl() {
        toolbar = findViewById(R.id.toolbar);
        recyclerTimkiem = findViewById(R.id.recyclerTimkiem);

        setSupportActionBar(toolbar);
        presenterLogicTimKiem = new PresenterLogicTimKiem(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timkiem_menu, menu);

        MenuItem itsearch = menu.findItem(R.id.itsearch);
        searchView = (SearchView) MenuItemCompat.getActionView(itsearch);
        searchView.setIconified(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                presenterLogicTimKiem.TimKiemSPTheoTen(s, 0);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public void TimKiemThanhCong(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        TopDienTuAdapter topDienTuAdapter = new TopDienTuAdapter(this, R.layout.custom_list_topdienthoaivamaytinhbang_layout, sanPhamList);

        recyclerTimkiem.setLayoutManager(layoutManager);
        recyclerTimkiem.setAdapter(topDienTuAdapter);
        recyclerTimkiem.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        topDienTuAdapter.notifyDataSetChanged();
    }

    @Override
    public void TimKiemThatBai() {

    }

    @Override
    public void LoadMore(int tongitem) {

    }
}
