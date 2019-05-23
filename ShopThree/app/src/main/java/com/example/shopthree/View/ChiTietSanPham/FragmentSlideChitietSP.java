package com.example.shopthree.View.ChiTietSanPham;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shopthree.R;
import com.squareup.picasso.Picasso;

public class FragmentSlideChitietSP extends Fragment {
    View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_slide_chitietsp_layout, container, false);
        ImageView imageView = view.findViewById(R.id.imgSlide);
        Bundle bundle = getArguments();
        String linkhinh = bundle.getString("linkhinh");
        Picasso.with(getContext()).load(linkhinh).into(imageView);

        mView = view;
        return view;
    }
}
