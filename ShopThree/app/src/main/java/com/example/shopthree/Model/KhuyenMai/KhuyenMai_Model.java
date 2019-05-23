package com.example.shopthree.Model.KhuyenMai;

import android.util.Log;

import com.example.shopthree.ConnectInternet.DownloadJSON;
import com.example.shopthree.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.shopthree.Model.ObjectClass.ChiTietSanPham;
import com.example.shopthree.Model.ObjectClass.KhuyenMai;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.View.TrangChu.TrangchuActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KhuyenMai_Model {

    public List<KhuyenMai> getDanhSachSPTheoMaLoai(String tenham, String tenmang)
    {
        List<KhuyenMai> khuyenMais = new ArrayList<>();
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
            JSONArray jsonArrayDanhsachKhuyenMai = jsonObject.getJSONArray(tenmang);

            int count = jsonArrayDanhsachKhuyenMai.length();

            for (int i=0; i<count; i++)
            {
                JSONObject object = jsonArrayDanhsachKhuyenMai.getJSONObject(i);
                KhuyenMai khuyenMai = new KhuyenMai();
                khuyenMai.setMAKM(object.getInt("MAKM"));
                khuyenMai.setTENKHUYENMAI(object.getString("TENKM"));
                khuyenMai.setTENLOAISP(object.getString("TENLOAISP"));
                khuyenMai.setHINHKHUYENMAI(TrangchuActivity.SERVER + object.getString("HINHKHUYENMAI"));

                List<SanPham> sanPhamList = new ArrayList<>();

                JSONArray arrayDanhSachSP = object.getJSONArray("DANHSACHSANPHAM");
                int demSP = arrayDanhSachSP.length();
                for(int j=0;j<demSP;j++)
                {
                    JSONObject objectSP = arrayDanhSachSP.getJSONObject(j);
                    SanPham sanPham = new SanPham();
                    sanPham.setMASP(objectSP.getInt("MASP"));
                    sanPham.setTENSP(objectSP.getString("TENSP"));
                    sanPham.setGIA(objectSP.getInt("GIA"));
                    sanPham.setANHLON(TrangchuActivity.SERVER + objectSP.getString("ANHLON"));
                    sanPham.setANHNHO(TrangchuActivity.SERVER + objectSP.getString("ANHNHO"));
                    sanPham.setSOLUONGTONKHO(objectSP.getInt("SOLUONG"));

                    ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                    chiTietKhuyenMai.setPHANTRAMKM(objectSP.getInt("PHANTRAMKM"));

                    sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);

                    sanPhamList.add(sanPham);
                }

                khuyenMai.setDanhSahSPKhuyenMai(sanPhamList);
                khuyenMais.add(khuyenMai);

            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return khuyenMais;
    }
}
