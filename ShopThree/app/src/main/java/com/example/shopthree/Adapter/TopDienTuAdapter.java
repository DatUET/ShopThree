package com.example.shopthree.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shopthree.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.shopthree.Model.ObjectClass.DienTu;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.R;
import com.example.shopthree.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class TopDienTuAdapter extends RecyclerView.Adapter<TopDienTuAdapter.ViewHolderTopDienTu> {

    Context context;
    List<SanPham> sanPhamList;
    int layout;


    public TopDienTuAdapter(Context context, int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public ViewHolderTopDienTu onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, viewGroup, false);

        ViewHolderTopDienTu viewHolderTopDienTu = new ViewHolderTopDienTu(view);
        return viewHolderTopDienTu;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderTopDienTu viewHolderTopDienTu, int i) {
        final SanPham sanPham = sanPhamList.get(i);

        Picasso.with(context).load(sanPham.getANHLON()).resize(140, 140).into(viewHolderTopDienTu.imgSanPham, new Callback() {
            @Override
            public void onSuccess() {
                viewHolderTopDienTu.procesDownloadTop.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });

        ChiTietKhuyenMai chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();
        long giatien = sanPham.getGIA();

        if(chiTietKhuyenMai != null)
        {
            int phantramkm = chiTietKhuyenMai.getPHANTRAMKM();
            if(phantramkm != 0) {
                NumberFormat numberFormat = new DecimalFormat("###,###");
                String gia = numberFormat.format(giatien);

                viewHolderTopDienTu.txtGiamGia.setVisibility(View.VISIBLE);
                viewHolderTopDienTu.txtGiamGia.setPaintFlags(viewHolderTopDienTu.txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                viewHolderTopDienTu.txtGiamGia.setText(gia + " VNĐ");

                phantramkm = chiTietKhuyenMai.getPHANTRAMKM();
                giatien = giatien * phantramkm / 100;
            }
            else
            {
                viewHolderTopDienTu.txtGiamGia.setVisibility(View.INVISIBLE);
            }

        }

        viewHolderTopDienTu.txtTenSP.setText(sanPham.getTENSP());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        viewHolderTopDienTu.txtGiaTien.setText(gia + " VNĐ");

        if(sanPham.getSOLUONGTONKHO() > 0)
		{
			viewHolderTopDienTu.txtTranhthaimathang.setText("Còn hàng ");
			viewHolderTopDienTu.txtTranhthaimathang.setTextColor(Color.BLACK);
		}
        else
		{
			viewHolderTopDienTu.txtTranhthaimathang.setText("Hết hàng");
			viewHolderTopDienTu.txtTranhthaimathang.setTextColor(Color.parseColor("#D81313"));
		}

        viewHolderTopDienTu.cardView.setTag(sanPham.getMASP());

        viewHolderTopDienTu.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent iChitietSP = new Intent(context, ChiTietSanPhamActivity.class);
                    iChitietSP.putExtra("masp", (int) v.getTag());
                    context.startActivity(iChitietSP);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolderTopDienTu extends RecyclerView.ViewHolder {
        ImageView imgSanPham;
        TextView txtTenSP, txtGiaTien, txtGiamGia, txtTranhthaimathang;
        ProgressBar procesDownloadTop;
        CardView cardView;


        public ViewHolderTopDienTu(@NonNull View itemView) {
            super(itemView);

            imgSanPham = itemView.findViewById(R.id.imgTopDienthoaiDientu);
            txtTenSP = itemView.findViewById(R.id.txtTiedeTopDienthoaiDientu);
            txtGiamGia = itemView.findViewById(R.id.txtGiamgiaDientu);
            txtGiaTien = itemView.findViewById(R.id.txtGiaDientu);
			txtTranhthaimathang = itemView.findViewById(R.id.txtTranhthaimathang);
            procesDownloadTop = itemView.findViewById(R.id.procesDownloadTop);
            cardView = itemView.findViewById(R.id.cardView);


        }
    }
}
