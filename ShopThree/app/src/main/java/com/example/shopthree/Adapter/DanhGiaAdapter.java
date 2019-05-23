package com.example.shopthree.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.shopthree.Model.ObjectClass.DanhGia;
import com.example.shopthree.R;
import com.example.shopthree.View.DanhGia.ViewDanhGia;

import java.util.List;

public class DanhGiaAdapter extends RecyclerView.Adapter<DanhGiaAdapter.ViewHolderDanhGia> {

    List<DanhGia> danhGiaList;
    int limit;
    Context context;

    public DanhGiaAdapter(Context context, List<DanhGia> danhGiaList, int limit) {
        this.danhGiaList = danhGiaList;
        this.limit = limit;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDanhGia onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_chitiet_danhgia_layout, viewGroup, false);

        ViewHolderDanhGia viewHolderDanhGia = new ViewHolderDanhGia(view);
        return viewHolderDanhGia;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDanhGia viewHolderDanhGia, int i) {
        DanhGia danhGia = danhGiaList.get(i);

        viewHolderDanhGia.txtTieudeDanhgia.setText(danhGia.getTIEUDE());
        viewHolderDanhGia.txtDuocdangboi.setText("Được đăng bởi " + danhGia.getTENTHIETBI() + "\nNgày: " + danhGia.getNGAYDANHGIA());
        viewHolderDanhGia.txtNoidungdanhgia.setText(danhGia.getNOIDUNG());
        viewHolderDanhGia.rbDanhgia.setRating(danhGia.getSOSAO());
    }

    @Override
    public int getItemCount() {
        int dong = 0;
        if(danhGiaList.size() < limit)
        {
            dong = danhGiaList.size();
        }
        else
        {
            if(limit != 0)
            {
                dong = limit;
            }
            else
            {
                dong = danhGiaList.size();
            }
        }
        return dong;
    }

    public class ViewHolderDanhGia extends RecyclerView.ViewHolder {
        TextView txtTieudeDanhgia, txtDuocdangboi, txtNoidungdanhgia;
        RatingBar rbDanhgia;

        public ViewHolderDanhGia(@NonNull View itemView) {
            super(itemView);

            txtTieudeDanhgia = itemView.findViewById(R.id.txtTieudeDanhgia);
            txtDuocdangboi = itemView.findViewById(R.id.txtDuocdangboi);
            txtNoidungdanhgia = itemView.findViewById(R.id.txtNoidungdanhgia);
            rbDanhgia = itemView.findViewById(R.id.rbDanhgia);
        }
    }
}
