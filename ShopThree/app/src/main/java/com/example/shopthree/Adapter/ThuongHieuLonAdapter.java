package com.example.shopthree.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shopthree.Model.ObjectClass.ThuongHieu;
import com.example.shopthree.R;
import com.example.shopthree.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ThuongHieuLonAdapter extends RecyclerView.Adapter<ThuongHieuLonAdapter.ViewHolderThuongHieu> {

    Context context;
    List<ThuongHieu> thuongHieus;
    boolean check;

    public ThuongHieuLonAdapter(Context context, List<ThuongHieu> thuongHieus, boolean check) {
        this.context = context;
        this.thuongHieus = thuongHieus;
        this.check = check;
    }

    @NonNull
    @Override
    public ViewHolderThuongHieu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recyclerview_thuonghieulon_layout, viewGroup, false);

        ViewHolderThuongHieu viewHolder = new ViewHolderThuongHieu(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderThuongHieu viewHolder, int i) {

        final ThuongHieu thuongHieu = thuongHieus.get(i);
        viewHolder.txtTieudeThuonghieu.setText(thuongHieu.getTENTHUONGHIEU());
        Picasso.with(context).load(thuongHieu.getHINHTHUONGHIEU()).resize(120, 120).into(viewHolder.imgHinhThuonghieu, new Callback() {
            @Override
            public void onSuccess() {
                viewHolder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                HienThiSanPhamTheoDanhMucActivity hienThiSanPhamTheoDanhMucActivity = new HienThiSanPhamTheoDanhMucActivity();

                Bundle bundle = new Bundle();

                bundle.putInt("MALOAI", thuongHieu.getMATHUONGHIEU());
                bundle.putBoolean("CHECK", check);
                bundle.putString("TENLOAI", thuongHieu.getTENTHUONGHIEU());

                hienThiSanPhamTheoDanhMucActivity.setArguments(bundle);
                fragmentTransaction.addToBackStack("TrangchuActivity");
                fragmentTransaction.replace(R.id.themFragment, hienThiSanPhamTheoDanhMucActivity);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return thuongHieus.size();
    }

    public class ViewHolderThuongHieu extends RecyclerView.ViewHolder {
        TextView txtTieudeThuonghieu;
        ImageView imgHinhThuonghieu;
        ProgressBar progressBar;
        LinearLayout linearLayout;
        public ViewHolderThuongHieu(@NonNull View itemView) {
            super(itemView);

            txtTieudeThuonghieu = itemView.findViewById(R.id.txtTieuDeThuonghieulonDientu);
            imgHinhThuonghieu = itemView.findViewById(R.id.imgHinhThuonghieulonDientu);
            progressBar = itemView.findViewById(R.id.procesDownload);
            linearLayout = itemView.findViewById(R.id.lnThuonghieulon);
        }
    }
}
