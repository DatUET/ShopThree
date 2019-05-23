package com.example.shopthree.Model.GioHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shopthree.Model.GioHang.DataSanPham;
import com.example.shopthree.Model.ObjectClass.SanPham;

import java.util.ArrayList;
import java.util.List;

public class GioHang_Model {

    SQLiteDatabase sqLiteDatabase;

    public void MoKetNoiSQL(Context context)
    {
        DataSanPham dataSanPham = new DataSanPham(context);
        sqLiteDatabase = dataSanPham.getWritableDatabase();
    }

    public boolean ThemGioHang(SanPham sanPham)
    {
        long gia = sanPham.getGIA();
        int phantramkm = sanPham.getChiTietKhuyenMai().getPHANTRAMKM();
        if(phantramkm != 0)
        {
            gia = gia * phantramkm/100;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_GIOHANG_MASP, sanPham.getMASP());
        contentValues.put(DataSanPham.TB_GIOHANG_TENSP, sanPham.getTENSP());
        contentValues.put(DataSanPham.TB_GIOHANG_GIATIEN, gia);
        contentValues.put(DataSanPham.TB_GIOHANG_HINHANH, sanPham.getHINHGIOHANG());
        contentValues.put(DataSanPham.TB_GIOHANG_SOLUONG, sanPham.getSOLUONG());
        contentValues.put(DataSanPham.TB_GIOHANG_SOLUONGTONKHO, sanPham.getSOLUONGTONKHO());

        long id = sqLiteDatabase.insert(DataSanPham.TB_GIOHANG, null, contentValues);
        if(id > 0)
        {
            return true;
        }
        else {

            return false;
        }
    }

    public List<SanPham> getDanhSachSPGioHang()
    {
        List<SanPham> sanPhamList = new ArrayList<>();

        String truyvan = "SELECT * FROM " + DataSanPham.TB_GIOHANG;

        Cursor cursor = sqLiteDatabase.rawQuery(truyvan, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            int masp = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_MASP));
            String tensp = cursor.getString(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_TENSP));
            int gia = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_GIATIEN));
            byte[] hinhanh = cursor.getBlob(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_HINHANH));
            int soluong = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_SOLUONG));
            int soluongtonkho = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_SOLUONGTONKHO));

            SanPham sanPham = new SanPham();
            sanPham.setMASP(masp);
            sanPham.setTENSP(tensp);
            sanPham.setGIA(gia);
            sanPham.setHINHGIOHANG(hinhanh);
            sanPham.setSOLUONG(soluong);
            sanPham.setSOLUONGTONKHO(soluongtonkho);

            sanPhamList.add(sanPham);

            cursor.moveToNext();
        }

        return sanPhamList;
    }

    public boolean deleteSPGioHang(int masp)
    {
        int check = sqLiteDatabase.delete(DataSanPham.TB_GIOHANG, DataSanPham.TB_GIOHANG_MASP + "=" + masp, null);
        if(check > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean updateSoLuongSPGioHang(int masp, int soluong)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_GIOHANG_SOLUONG, soluong);

        int id = sqLiteDatabase.update(DataSanPham.TB_GIOHANG, contentValues, DataSanPham.TB_GIOHANG_MASP + " = " + masp, null);

        if(id > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
