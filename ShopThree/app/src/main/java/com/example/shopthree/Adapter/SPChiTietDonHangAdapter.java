package com.example.shopthree.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shopthree.Model.ObjectClass.ChiTietHoaDon;
import com.example.shopthree.Model.ObjectClass.ChiTietSanPham;
import com.example.shopthree.R;
import com.example.shopthree.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class SPChiTietDonHangAdapter extends RecyclerView.Adapter<SPChiTietDonHangAdapter.ViewHolderSPChiTietDonHang> {
    Context context;
    List<ChiTietHoaDon> chiTietHoaDonList;
    int layout;

    public SPChiTietDonHangAdapter(Context context, List<ChiTietHoaDon> chiTietHoaDonList, int layout) {
        this.context = context;
        this.chiTietHoaDonList = chiTietHoaDonList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolderSPChiTietDonHang onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, viewGroup, false);

        ViewHolderSPChiTietDonHang viewHolderSPChiTietDonHang = new ViewHolderSPChiTietDonHang(view);
        return  viewHolderSPChiTietDonHang;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderSPChiTietDonHang viewHolderSPChiTietDonHang, int i) {
        final ChiTietHoaDon chiTietHoaDon = chiTietHoaDonList.get(i);

        Picasso.with(context).load(chiTietHoaDon.getANHSP()).into(viewHolderSPChiTietDonHang.imgHinhsp, new Callback() {
            @Override
            public void onSuccess() {
                viewHolderSPChiTietDonHang.procesDownload.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });
        viewHolderSPChiTietDonHang.txtTensp.setText(chiTietHoaDon.getTENSP());
        viewHolderSPChiTietDonHang.txtSoluong.setText(chiTietHoaDon.getSOLUONG() + "");
        viewHolderSPChiTietDonHang.txtDongia.setText(chiTietHoaDon.getDONGIASAUKM() + "");
        int gia = chiTietHoaDon.getSOLUONG() * chiTietHoaDon.getDONGIASAUKM();
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String tonggiatien = numberFormat.format(gia);
        viewHolderSPChiTietDonHang.txtTonggiatien.setText( tonggiatien + " VNĐ");

        viewHolderSPChiTietDonHang.cardView.setTag(chiTietHoaDon.getMASP());
        viewHolderSPChiTietDonHang.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChitietSP = new Intent(context, ChiTietSanPhamActivity.class);
                iChitietSP.putExtra("masp", chiTietHoaDon.getMASP());
                context.startActivity(iChitietSP);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chiTietHoaDonList.size();
    }

    public class ViewHolderSPChiTietDonHang extends RecyclerView.ViewHolder {

        CardView cardView;
        ProgressBar procesDownload;
        ImageView imgHinhsp;
        TextView txtTensp, txtSoluong, txtDongia, txtTonggiatien;

        public ViewHolderSPChiTietDonHang(@NonNull View itemView) {
            super(itemView);

            procesDownload = itemView.findViewById(R.id.procesDownload);
            imgHinhsp = itemView.findViewById(R.id.imgHinhsp);
            txtTensp = itemView.findViewById(R.id.txtTensp);
            txtSoluong = itemView.findViewById(R.id.txtSoluong);
            txtDongia = itemView.findViewById(R.id.txtDongia);
            txtTonggiatien = itemView.findViewById(R.id.txtTonggiatien);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
