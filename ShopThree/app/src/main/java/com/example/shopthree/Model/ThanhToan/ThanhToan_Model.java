package com.example.shopthree.Model.ThanhToan;

import android.util.Log;

import com.example.shopthree.ConnectInternet.DownloadJSON;
import com.example.shopthree.Model.ObjectClass.ChiTietHoaDon;
import com.example.shopthree.Model.ObjectClass.DanhGia;
import com.example.shopthree.Model.ObjectClass.HoaDon;
import com.example.shopthree.View.TrangChu.TrangchuActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThanhToan_Model {

    public boolean ThemThanhToan(HoaDon hoaDon)
    {
        Boolean check = false;

        List<HashMap<String, String>> atttr = new ArrayList<>();
        String link = TrangchuActivity.SERVER_NAME;

        HashMap<String, String> hashHam = new HashMap<>();
        hashHam.put("ham", "ThemHoaDon");

        List<ChiTietHoaDon> chiTietHoaDonList = hoaDon.getChiTietHoaDonList();
        String chuoijson = "{\"DANHSACHSANPHAM\" :[ ";
        for (int i=0; i<chiTietHoaDonList.size(); i++)
        {
            chuoijson += "{";
            chuoijson += "\"masp\" : " + chiTietHoaDonList.get(i).getMASP() + ",";
            chuoijson += "\"soluong\" : " + chiTietHoaDonList.get(i).getSOLUONG();
            if(i == chiTietHoaDonList.size() - 1)
            {
                chuoijson += "}";
            }
            else
            {
                chuoijson += "},";
            }


        }
        chuoijson += "]}";

        HashMap<String, String> hashDanhSachSP = new HashMap<>();
        hashDanhSachSP.put("danhsachsanpham", chuoijson);

        HashMap<String, String> hasTenNguoiNhan = new HashMap<>();
        hasTenNguoiNhan.put("tennguoinhan", hoaDon.getTENNGUOINHAN());

        HashMap<String, String> hashDiaChi = new HashMap<>();
        hashDiaChi.put("diachi", hoaDon.getDIACHI());

        HashMap<String, String> hashChuyenKhoan = new HashMap<>();
        hashChuyenKhoan.put("chuyenkhoan", hoaDon.getCHUYENKHOAN() + "");

        HashMap<String, String> hashSoDienThoai = new HashMap<>();
        hashSoDienThoai.put("sodt", hoaDon.getSODT());

        HashMap<String, String> hashManv = new HashMap<>();
        hashManv.put("manv", hoaDon.getMANV() + "");

        HashMap<String, String> hashTonggiatien = new HashMap<>();
        hashTonggiatien.put("tonggiatien", hoaDon.getTONGGIATIEN() + "");

        atttr.add(hashHam);
        atttr.add(hashDanhSachSP);
        atttr.add(hasTenNguoiNhan);
        atttr.add(hashDiaChi);
        atttr.add(hashChuyenKhoan);
        atttr.add(hashSoDienThoai);
        atttr.add(hashManv);
        atttr.add(hashTonggiatien);

        DownloadJSON downloadJSON = new DownloadJSON(link, atttr);
        downloadJSON.execute();

        try {
            String data = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(data);
            String ketqua = jsonObject.getString("ketqua");
            if(ketqua.equals("true"))
            {
                check = true;
            }
            else
            {
                check = false;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return check;
    }

    public boolean ThemLuotMua(int maSP, int soluongmua)
    {
        Boolean check = false;

        List<HashMap<String, String>> atttr = new ArrayList<>();
        String link = TrangchuActivity.SERVER_NAME;

        HashMap<String, String> hashHam = new HashMap<>();
        hashHam.put("ham", "ThemLuotMua");

        HashMap<String, String> hashMaSP = new HashMap<>();
        hashMaSP.put("masp", maSP + "");

        HashMap<String, String> hashSoLuongMua = new HashMap<>();
        hashSoLuongMua.put("soluongsp", soluongmua + "");

        atttr.add(hashHam);
        atttr.add(hashMaSP);
        atttr.add(hashSoLuongMua);

        DownloadJSON downloadJSON = new DownloadJSON(link, atttr);
        downloadJSON.execute();

        try {
            String data = downloadJSON.get();
            Log.d("them lươt mua", data);
            JSONObject jsonObject = new JSONObject(data);

            String ketqua = jsonObject.getString("ketqua");
            if(ketqua.equals("true"))
            {
                check = true;
            }
            else
            {
                check = false;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return check;
    }

}
