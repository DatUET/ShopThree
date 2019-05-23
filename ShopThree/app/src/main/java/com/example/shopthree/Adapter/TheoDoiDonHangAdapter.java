package com.example.shopthree.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shopthree.Model.ObjectClass.ChiTietHoaDon;
import com.example.shopthree.Model.ObjectClass.HoaDon;
import com.example.shopthree.R;
import com.example.shopthree.View.ChiTietDonHang.ChiTietDonHangActivity;
import com.example.shopthree.View.TheoDoiDonHang.ViewTheoDoiDonHang;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class TheoDoiDonHangAdapter extends RecyclerView.Adapter<TheoDoiDonHangAdapter.ViewHolderTheoDoiDonHang> {

    Context context;
    List<HoaDon> hoaDonList;
    int layout;

    public TheoDoiDonHangAdapter(Context context, List<HoaDon> hoaDonList, int layout) {
        this.context = context;
        this.hoaDonList = hoaDonList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public TheoDoiDonHangAdapter.ViewHolderTheoDoiDonHang onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, viewGroup, false);

        ViewHolderTheoDoiDonHang viewHolderTheoDoiDonHang = new ViewHolderTheoDoiDonHang(view);
        return viewHolderTheoDoiDonHang;
    }

    @Override
    public void onBindViewHolder(@NonNull TheoDoiDonHangAdapter.ViewHolderTheoDoiDonHang viewHolderTheoDoiDonHang, int i) {
        HoaDon hoaDon = hoaDonList.get(i);

        String donhang = "";
        List<ChiTietHoaDon> chiTietHoaDonList = hoaDon.getChiTietHoaDonList();
        int count = chiTietHoaDonList.size();
        for(int j=0; j<count; j++)
        {
            ChiTietHoaDon chiTietHoaDon = chiTietHoaDonList.get(j);
            donhang += chiTietHoaDon.getSOLUONG() + "x" + chiTietHoaDon.getTENSP() + " , ";
        }
        viewHolderTheoDoiDonHang.txtDonhang.setText(donhang);
        viewHolderTheoDoiDonHang.txtNgaydathang.setText(hoaDon.getNGAYMUA());
        viewHolderTheoDoiDonHang.txtNgaygiahang.setText(hoaDon.getNGAYGIAO());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String tonggiatien = numberFormat.format(hoaDon.getTONGGIATIEN());
        viewHolderTheoDoiDonHang.txtTonggiatien.setText(tonggiatien + " VNĐ");

        viewHolderTheoDoiDonHang.txtTrangthai.setText(hoaDon.getTRANGTHAI());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        DanhSachHinhSPHoaDonAdapter danhSachHinhSPHoaDonAdapter = new DanhSachHinhSPHoaDonAdapter(context, hoaDon.getChiTietHoaDonList(), R.layout.custom_hinhsp_theodoidonhang_layout);

        viewHolderTheoDoiDonHang.rycyclerAnhdonhang.setLayoutManager(layoutManager);
        viewHolderTheoDoiDonHang.rycyclerAnhdonhang.setAdapter(danhSachHinhSPHoaDonAdapter);
        danhSachHinhSPHoaDonAdapter.notifyDataSetChanged();

        viewHolderTheoDoiDonHang.cardView.setTag(hoaDon);
        viewHolderTheoDoiDonHang.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChitietHD = new Intent(context, ChiTietDonHangActivity.class);
                iChitietHD.putExtra("hoadon",(HoaDon) v.getTag());
                context.startActivity(iChitietHD);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hoaDonList.size();
    }


    public class ViewHolderTheoDoiDonHang extends RecyclerView.ViewHolder {
        TextView txtDonhang, txtNgaydathang, txtNgaygiahang, txtTonggiatien, txtTrangthai;
        RecyclerView rycyclerAnhdonhang;
        CardView cardView;

        public ViewHolderTheoDoiDonHang(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            txtDonhang = itemView.findViewById(R.id.txtDonhang);
            txtNgaydathang = itemView.findViewById(R.id.txtNgaydathang);
            txtNgaygiahang = itemView.findViewById(R.id.txtNgaygiahang);
            txtTonggiatien = itemView.findViewById(R.id.txtTonggiatien);
            txtTrangthai = itemView.findViewById(R.id.txtTrangthai);
            rycyclerAnhdonhang = itemView.findViewById(R.id.rycyclerAnhdonhang);
        }
    }
}
