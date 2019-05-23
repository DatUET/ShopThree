package com.example.shopthree.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopthree.Adapter.DienTuAdapter;
import com.example.shopthree.Model.ObjectClass.DienTu;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.Model.ObjectClass.ThuongHieu;
import com.example.shopthree.Presenter.DienTu.PresenterLogicDienTu;
import com.example.shopthree.R;
import com.example.shopthree.View.TrangChu.ViewDienTu;

import java.util.ArrayList;
import java.util.List;

public class FragmentDientu extends Fragment implements ViewDienTu {

    RecyclerView recyclerView;
    List<DienTu> dienTuList;
    PresenterLogicDienTu presenterLogicDienTu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dien_tu_layout, container, false);

        recyclerView = view.findViewById(R.id.recyclerDienTu);
        dienTuList = new ArrayList<>();

        presenterLogicDienTu = new PresenterLogicDienTu(this);
        presenterLogicDienTu.getDanhSachDienTu();
        return view;
    }

    @Override
    public void showDanhSach(List<DienTu> dienTus) {

        dienTuList = dienTus;

        DienTuAdapter dienTuAdapter = new DienTuAdapter(getContext(), dienTuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dienTuAdapter);

        dienTuAdapter.notifyDataSetChanged();
    }
}
