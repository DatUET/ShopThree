package com.example.shopthree.Model.TheoDoiDonHang;

import android.util.Log;

import com.example.shopthree.ConnectInternet.DownloadJSON;
import com.example.shopthree.Model.ObjectClass.ChiTietHoaDon;
import com.example.shopthree.Model.ObjectClass.HoaDon;
import com.example.shopthree.View.TrangChu.TrangchuActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TheoDoiDonHang_Model {
    public List<HoaDon> getHoaDon(int manv)
    {
        List<HoaDon> listHoaDon = new ArrayList<>();
        List<HashMap<String, String >> atttr = new ArrayList<>();
        String link = TrangchuActivity.SERVER_NAME;

        HashMap<String, String> hashHam = new HashMap<>();
        hashHam.put("ham", "TheoDoiDonHang");

        HashMap<String, String> hashManv = new HashMap<>();
        hashManv.put("manv", manv + "");

        atttr.add(hashHam);
        atttr.add(hashManv);

        DownloadJSON downloadJSON = new DownloadJSON(link, atttr);
        downloadJSON.execute();

        String dataJSON;
        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("HOADON");

            int count = jsonArray.length();

            for(int i=0; i<count; i++)
            {
                HoaDon hoaDon = new HoaDon();
                JSONObject object = jsonArray.getJSONObject(i);

                hoaDon.setMAHD(object.getInt("MAHD"));
                hoaDon.setNGAYMUA(object.getString("NGAYMUA"));
                hoaDon.setNGAYGIAO(object.getString("NGAYGIAO"));
                hoaDon.setTRANGTHAI(object.getString("TRANGTHAI"));
                hoaDon.setTENNGUOINHAN(object.getString("TENNGUOINHAN"));
                hoaDon.setSODT(object.getString("SODT"));
                hoaDon.setDIACHI(object.getString("DIACHI"));
                hoaDon.setTONGGIATIEN(object.getInt("TONGGIATIEN"));
                hoaDon.setCHUYENKHOAN(object.getInt("CHUYENKHOAN"));
                hoaDon.setChiTietHoaDonList(getChiTietHoaDon(object.getInt("MAHD")));

                listHoaDon.add(hoaDon);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return listHoaDon;
    }

    public List<ChiTietHoaDon> getChiTietHoaDon(int mahd)
    {
        List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
        List<HashMap<String, String>> atttr = new ArrayList<>();
        String dataJSON;

        String link = TrangchuActivity.SERVER_NAME;

        HashMap<String, String> hashHam = new HashMap<>();
        hashHam.put("ham", "LayChiTietHoaDon");

        HashMap<String, String> hashMaHD = new HashMap<>();
        hashMaHD.put("mahd", mahd + "");

        atttr.add(hashHam);
        atttr.add(hashMaHD);

        DownloadJSON downloadJSON = new DownloadJSON(link, atttr);
        downloadJSON.execute();
        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArray = jsonObject.getJSONArray("CHITIETHOADON");

            int count = jsonArray.length();

            for(int i=0; i<count; i++)
            {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                JSONObject object = jsonArray.getJSONObject(i);

                chiTietHoaDon.setTENSP(object.getString("TENSP"));
                chiTietHoaDon.setMASP(object.getInt("MASP"));
                chiTietHoaDon.setGIA(object.getInt("GIA"));
                chiTietHoaDon.setANHSP(TrangchuActivity.SERVER + object.getString("ANH"));
                chiTietHoaDon.setSOLUONG(object.getInt("SOLUONG"));
                chiTietHoaDon.setDONGIASAUKM(object.getInt("DONGIA"));

                chiTietHoaDonList.add(chiTietHoaDon);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  chiTietHoaDonList;
    }
}
