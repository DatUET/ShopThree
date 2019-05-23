package com.example.shopthree.Model.HienThiSanPhamTheoDanhMuc;

import android.util.Log;

import com.example.shopthree.ConnectInternet.DownloadJSON;
import com.example.shopthree.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.View.TrangChu.TrangchuActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HienThiSanPhamTheoDanhMuc_Model {

    public List<SanPham> getDanhSachSPTheoMaLoai(int maSP, String tenmang, String tenham, int limit)
    {
        Log.d("Ma loại", maSP + "");
        List<SanPham> sanPhams = new ArrayList<>();
        List<HashMap<String, String>> atttr = new ArrayList<>();
        String dataJSON = "";

        String link = TrangchuActivity.SERVER_NAME ;

        HashMap<String, String> hashHam = new HashMap<>();
        hashHam.put("ham", tenham);

        HashMap<String, String> hashMaSP = new HashMap<>();
        hashMaSP.put("maloaisp", maSP+"");

        HashMap<String, String> hashLimit = new HashMap<>();
        hashLimit.put("limit", limit+"");

        atttr.add(hashHam);
        atttr.add(hashMaSP);
        atttr.add(hashLimit);

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
                sanPham.setANHNHO(object.getString("HINHSANPHAMNHO"));
                sanPham.setSOLUONGTONKHO(object.getInt("SOLUONGTONKHO"));

                ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                chiTietKhuyenMai.setPHANTRAMKM(object.getInt("PHANTRAMKM"));

                sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);

                sanPhams.add(sanPham);
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return sanPhams;
    }

}
