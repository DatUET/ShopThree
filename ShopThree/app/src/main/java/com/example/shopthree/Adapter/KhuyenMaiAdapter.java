package com.example.shopthree.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shopthree.Model.ObjectClass.KhuyenMai;
import com.example.shopthree.R;

import java.util.List;

public class KhuyenMaiAdapter extends RecyclerView.Adapter<KhuyenMaiAdapter.ViewHolderKhuyenMai> {

    Context context;
    List<KhuyenMai> khuyenMaiList;

    public KhuyenMaiAdapter(Context context, List<KhuyenMai> khuyenMaiList) {
        this.context = context;
        this.khuyenMaiList = khuyenMaiList;
    }

    @NonNull
    @Override
    public ViewHolderKhuyenMai onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_item_khuyenmai_layout, viewGroup, false);


        ViewHolderKhuyenMai viewHolderKhuyenMai = new ViewHolderKhuyenMai(view);
        return viewHolderKhuyenMai;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderKhuyenMai viewHolderKhuyenMai, int i) {
        KhuyenMai khuyenMai = khuyenMaiList.get(i);
        viewHolderKhuyenMai.txtTieudekhuyenmai.setText(khuyenMai.getTENLOAISP());

        TopDienTuAdapter topDienTuAdapter = new TopDienTuAdapter(context, R.layout.custom_top_dienthoai_maytinhbang_layout, khuyenMai.getDanhSahSPKhuyenMai());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        viewHolderKhuyenMai.recyclerItemkhuyenmai.setLayoutManager(layoutManager);
        viewHolderKhuyenMai.recyclerItemkhuyenmai.setAdapter(topDienTuAdapter);
        topDienTuAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return khuyenMaiList.size();
    }

    public class ViewHolderKhuyenMai extends RecyclerView.ViewHolder {

        RecyclerView recyclerItemkhuyenmai;
        TextView txtTieudekhuyenmai;

        public ViewHolderKhuyenMai(@NonNull View itemView) {
            super(itemView);

            recyclerItemkhuyenmai = itemView.findViewById(R.id.recyclerItemkhuyenmai);
            txtTieudekhuyenmai = itemView.findViewById(R.id.txtTieudekhuyenmai);
        }
    }
}
