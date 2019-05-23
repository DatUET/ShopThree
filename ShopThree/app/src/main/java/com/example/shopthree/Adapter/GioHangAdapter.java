package com.example.shopthree.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopthree.Model.GioHang.GioHang_Model;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolderGioHang> {

    Context context;
    List<SanPham> sanPhamList;
    GioHang_Model gioHang_model;

    public GioHangAdapter(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        gioHang_model = new GioHang_Model();
    }

    @NonNull
    @Override
    public ViewHolderGioHang onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_gio_hang_layout, viewGroup, false);

        ViewHolderGioHang viewHolderGioHang = new ViewHolderGioHang(view);
        return viewHolderGioHang;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderGioHang viewHolderGioHang, final int i) {
        gioHang_model.MoKetNoiSQL(context);

        final SanPham sanPham = sanPhamList.get(i);

        viewHolderGioHang.txtTieudegiohang.setText(sanPham.getTENSP());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        final String gia = numberFormat.format(sanPham.getGIA());
        viewHolderGioHang.txtGiagiohang.setText(gia + " VNĐ");

        byte[] hinhgiohang = sanPham.getHINHGIOHANG();
        Bitmap bitmaphinhgiohang = BitmapFactory.decodeByteArray(hinhgiohang, 0, hinhgiohang.length);
        viewHolderGioHang.imgHinhgiohang.setImageBitmap(bitmaphinhgiohang);

        viewHolderGioHang.progressBar.setVisibility(View.GONE);

        viewHolderGioHang.imgXoasanpham.setTag(sanPham.getMASP());
        viewHolderGioHang.imgXoasanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHang_Model gioHang_model = new GioHang_Model();
                gioHang_model.MoKetNoiSQL(context);
                gioHang_model.deleteSPGioHang((Integer) v.getTag());
                sanPhamList.remove(i);
                notifyDataSetChanged();
            }
        });

        viewHolderGioHang.txtSoluongSP.setText(sanPham.getSOLUONG() + "");

        viewHolderGioHang.btnTangsoluongSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(viewHolderGioHang.txtSoluongSP.getText().toString());
                int soluongtonkho = sanPham.getSOLUONGTONKHO();
                if(soluong < soluongtonkho)
                {
                    soluong++;
                }
                else
                {
                    Toast.makeText(context, "Số lượng sản phẩm bạn muốn mua vượt quá số lượng có trong kho", Toast.LENGTH_SHORT).show();
                }
                gioHang_model.updateSoLuongSPGioHang(sanPham.getMASP(), soluong);
                viewHolderGioHang.txtSoluongSP.setText(soluong + "");
            }
        });

        viewHolderGioHang.btnGiamsoluongSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(viewHolderGioHang.txtSoluongSP.getText().toString());
                if(soluong > 1)
                {
                    soluong--;
                }
                gioHang_model.updateSoLuongSPGioHang(sanPham.getMASP(), soluong);
                viewHolderGioHang.txtSoluongSP.setText(soluong + "");
            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {
        TextView txtTieudegiohang, txtGiagiohang, txtSoluongSP;
        ImageView imgHinhgiohang, imgXoasanpham;
        ProgressBar progressBar;
        ImageButton btnGiamsoluongSP, btnTangsoluongSP;

        public ViewHolderGioHang(@NonNull View itemView) {
            super(itemView);

            txtTieudegiohang = itemView.findViewById(R.id.txtTieudegiohang);
            txtGiagiohang = itemView.findViewById(R.id.txtGiagiohang);
            imgHinhgiohang = itemView.findViewById(R.id.imgHinhgiohang);
            imgXoasanpham = itemView.findViewById(R.id.imgXoasanpham);
            progressBar = itemView.findViewById(R.id.procesDownload);
            txtSoluongSP = itemView.findViewById(R.id.txtSoluongSP);
            btnGiamsoluongSP = itemView.findViewById(R.id.btnGiamsoluongSP);
            btnTangsoluongSP = itemView.findViewById(R.id.btnTangsoluongSP);
        }
    }
}
