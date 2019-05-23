package com.example.shopthree.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shopthree.Model.ObjectClass.DienTu;
import com.example.shopthree.R;

import java.util.List;

public class DienTuAdapter extends RecyclerView.Adapter<DienTuAdapter.ViewHolderDienTu> {

    Context context;
    List<DienTu> dienTuList;

    public DienTuAdapter(Context context, List<DienTu> dienTuList) {
        this.context = context;
        this.dienTuList = dienTuList;
    }

    @NonNull
    @Override
    public ViewHolderDienTu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recyclerview_dientu_layout, viewGroup, false);

        ViewHolderDienTu viewHolder = new ViewHolderDienTu(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDienTu viewHolderDienTu, int i) {
        DienTu dienTu = dienTuList.get(i);

        viewHolderDienTu.txtTenSPNoiBat.setText(dienTu.getTennoibat());
        viewHolderDienTu.txtTenTopSPNoiBat.setText(dienTu.getTentopnoibat());

        ThuongHieuLonAdapter thuongHieuLonAdapter = new ThuongHieuLonAdapter(context, dienTu.getThuongHieus(), dienTu.isThuonghieu());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false);

        viewHolderDienTu.recyclerViewThuonghieuLon.setLayoutManager(layoutManager);
        viewHolderDienTu.recyclerViewThuonghieuLon.setAdapter(thuongHieuLonAdapter);
        thuongHieuLonAdapter.notifyDataSetChanged();

        /////////////////////////////////////////////////////////

        TopDienTuAdapter topDienTuAdapter = new TopDienTuAdapter(context, R.layout.custom_top_dienthoai_maytinhbang_layout, dienTu.getSanPhams());
        RecyclerView.LayoutManager layoutManagerTop = new LinearLayoutManager(context, LinearLayout.HORIZONTAL, false);

        viewHolderDienTu.recyclerViewTopSanpham.setLayoutManager(layoutManagerTop);
        viewHolderDienTu.recyclerViewTopSanpham.setAdapter(topDienTuAdapter);
    }

    @Override
    public int getItemCount() {
        return dienTuList.size();
    }

    public class ViewHolderDienTu extends RecyclerView.ViewHolder {

        ImageView imgKhuyenmaiDientu;
        RecyclerView recyclerViewThuonghieuLon, recyclerViewTopSanpham;
        TextView txtTenSPNoiBat, txtTenTopSPNoiBat;

        public ViewHolderDienTu(@NonNull View itemView) {
            super(itemView);

            recyclerViewThuonghieuLon = itemView.findViewById(R.id.recyclerThuongHieuLon);
            recyclerViewTopSanpham = itemView.findViewById(R.id.recyclerTopDienthoaiMaytinhbang);
            imgKhuyenmaiDientu = itemView.findViewById(R.id.imgKhuyenmaidientu);
            txtTenSPNoiBat = itemView.findViewById(R.id.txtTenSPNoiBat);
            txtTenTopSPNoiBat = itemView.findViewById(R.id.txtTenTopSPNoiBat);
        }
    }
}
