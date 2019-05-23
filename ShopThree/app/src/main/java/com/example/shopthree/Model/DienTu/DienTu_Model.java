package com.example.shopthree.Model.DienTu;

import android.util.Log;

import com.example.shopthree.ConnectInternet.DownloadJSON;
import com.example.shopthree.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.shopthree.Model.ObjectClass.Loaisanpham;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.Model.ObjectClass.ThuongHieu;
import com.example.shopthree.Model.TrangChu.XulyMenu.JsonMenu;
import com.example.shopthree.View.TrangChu.TrangchuActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DienTu_Model {


    public List<SanPham> getDanhSachTopSP(String tenham, String tenmang)
    {
        List<SanPham> topSP = new ArrayList<>();
        List<HashMap<String, String>> atttr = new ArrayList<>();
        String dataJSON = "";

        String link = TrangchuActivity.SERVER_NAME ;

        HashMap<String, String> hashHam = new HashMap<>();
        hashHam.put("ham", tenham);

        atttr.add(hashHam);

        DownloadJSON downloadJSON = new DownloadJSON(link, atttr);
        downloadJSON.execute();
        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhsachThuongHieu = jsonObject.getJSONArray(tenmang);

            int count = jsonArrayDanhsachThuongHieu.length();

            for (int i=0; i<count; i++)
            {
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDanhsachThuongHieu.getJSONObject(i);

                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHLON(object.getString("HINHSANPHAM"));
                sanPham.setSOLUONGTONKHO(object.getInt("SOLUONGTONKHO"));

                ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                chiTietKhuyenMai.setPHANTRAMKM(object.getInt("PHANTRAMKM"));

                sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);


                topSP.add(sanPham);
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return topSP;
    }

    public List<ThuongHieu> getDanhSachThuongHieu(String tenham, String tenmang)
    {
        List<ThuongHieu> thuongHieuList = new ArrayList<>();

        List<HashMap<String, String>> atttr = new ArrayList<>();
        String dataJSON = "";

        String link = TrangchuActivity.SERVER_NAME ;

        HashMap<String, String> hashHam = new HashMap<>();
        hashHam.put("ham", tenham);

        atttr.add(hashHam);

        DownloadJSON downloadJSON = new DownloadJSON(link, atttr);
        downloadJSON.execute();
        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhsachThuongHieu = jsonObject.getJSONArray(tenmang);

            int count = jsonArrayDanhsachThuongHieu.length();

            for (int i=0; i<count; i++)
            {
                ThuongHieu thuongHieu = new ThuongHieu();
                JSONObject object = jsonArrayDanhsachThuongHieu.getJSONObject(i);

                thuongHieu.setMATHUONGHIEU(object.getInt("MASP"));
                thuongHieu.setTENTHUONGHIEU(object.optString("TENSP"));
                thuongHieu.setHINHTHUONGHIEU(object.getString("HINHSANPHAM"));

                thuongHieuList.add(thuongHieu);
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return thuongHieuList;
    }
}
