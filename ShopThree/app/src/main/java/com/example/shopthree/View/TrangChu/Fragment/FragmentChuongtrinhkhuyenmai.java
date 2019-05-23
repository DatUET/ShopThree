package com.example.shopthree.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.example.shopthree.Adapter.KhuyenMaiAdapter;
import com.example.shopthree.Model.ObjectClass.KhuyenMai;
import com.example.shopthree.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.example.shopthree.R;
import com.example.shopthree.View.TrangChu.ViewKhuyenMai;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FragmentChuongtrinhkhuyenmai extends Fragment implements ViewKhuyenMai {

    RecyclerView recyclerDanhsachkhuyenmai;
    ViewFlipper lnHinhkhuyenmai;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chuong_trinh_khuyen_mai_layout, container, false);

        recyclerDanhsachkhuyenmai = view.findViewById(R.id.recyclerDanhsachkhuyenmai);
        lnHinhkhuyenmai = view.findViewById(R.id.lnHinhkhuyenmai);

        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai(this);
        presenterLogicKhuyenMai.getDanhSachKhuyenMai();
        return view;

    }

    @Override
    public void showDanhSachKhuyenMai(List<KhuyenMai> khuyenMaiList) {

        int demhinh = khuyenMaiList.size();
        if(demhinh > 5)
        {
            demhinh = 5;
        }
        else
        {
            demhinh = khuyenMaiList.size();
        }
        lnHinhkhuyenmai.removeAllViews();
        for (int i=0;i<demhinh;i++)
        {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
            imageView.setLayoutParams(layoutParams);

            Picasso.with(getContext()).load(khuyenMaiList.get(i).getHINHKHUYENMAI()).resize(720, 100).into(imageView);
            lnHinhkhuyenmai.addView(imageView);
            lnHinhkhuyenmai.setFlipInterval(4000);
            lnHinhkhuyenmai.setAutoStart(true);
            lnHinhkhuyenmai.setInAnimation(getActivity(), android.R.anim.slide_in_left);
            lnHinhkhuyenmai.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
        }

        KhuyenMaiAdapter khuyenMaiAdapter = new KhuyenMaiAdapter(getContext(), khuyenMaiList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerDanhsachkhuyenmai.setLayoutManager(layoutManager);
        recyclerDanhsachkhuyenmai.setAdapter(khuyenMaiAdapter);
        khuyenMaiAdapter.notifyDataSetChanged();
    }
}
