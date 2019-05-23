package com.example.shopthree.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.shopthree.Model.ObjectClass.ChiTietHoaDon;
import com.example.shopthree.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DanhSachHinhSPHoaDonAdapter extends RecyclerView.Adapter<DanhSachHinhSPHoaDonAdapter.ViewHolderHinhSP> {
    Context context;
    List<ChiTietHoaDon> chiTietHoaDonList;
    int layout;

    public DanhSachHinhSPHoaDonAdapter(Context context, List<ChiTietHoaDon> chiTietHoaDonList, int layout) {
        this.context = context;
        this.chiTietHoaDonList = chiTietHoaDonList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolderHinhSP onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, viewGroup, false);

        ViewHolderHinhSP viewHolderHinhSP = new ViewHolderHinhSP(view);
        return viewHolderHinhSP;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderHinhSP viewHolderHinhSP, int i) {
        ChiTietHoaDon chiTietHoaDon = chiTietHoaDonList.get(i);

        Picasso.with(context).load(chiTietHoaDon.getANHSP()).resize(100, 100).into(viewHolderHinhSP.imgHinhsp, new Callback() {
            @Override
            public void onSuccess() {
                viewHolderHinhSP.procesHinhsp.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public int getItemCount() {
        return chiTietHoaDonList.size();
    }

    public class ViewHolderHinhSP extends RecyclerView.ViewHolder {
        ProgressBar procesHinhsp;
        ImageView imgHinhsp;

        public ViewHolderHinhSP(@NonNull View itemView) {
            super(itemView);

            imgHinhsp = itemView.findViewById(R.id.imgHinhsp);
            procesHinhsp = itemView.findViewById(R.id.procesHinhsp);
        }
    }
}
