package com.example.shopthree.Model.DangNhap_DangKy;

import android.util.Log;

import com.example.shopthree.ConnectInternet.DownloadJSON;
import com.example.shopthree.Model.ObjectClass.NhanVien;
import com.example.shopthree.View.TrangChu.TrangchuActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DangKy_Model {

    public Boolean DangKyThanhVien(NhanVien nhanVien) {
        Boolean check = false;

        List<HashMap<String, String>> atttr = new ArrayList<>();
        String link = TrangchuActivity.SERVER_NAME;

        HashMap<String, String> hashHam = new HashMap<>();
        hashHam.put("ham", "DangKyThanhVien");

        HashMap<String, String> hashTenNV = new HashMap<>();
        hashTenNV.put("tennv", nhanVien.getTenNV());

        HashMap<String, String> hashTenDN = new HashMap<>();
        hashTenDN.put("tendangnhap", nhanVien.getTenDangNhap());

        HashMap<String, String> hashMatKhau = new HashMap<>();
        hashMatKhau.put("matkhau", nhanVien.getMatKhau());

        HashMap<String, String> hashMaloaiNV = new HashMap<>();
        hashMaloaiNV.put("maloainv", String.valueOf(nhanVien.getMaLoaNV()));

        HashMap<String, String> hashEmailDocQuyen = new HashMap<>();
        hashEmailDocQuyen.put("emaildocquyen", nhanVien.getEmaiDocQuyen());

        HashMap<String, String> hashNgaysinh = new HashMap<>();
        hashNgaysinh.put("ngaysinh", nhanVien.getNgaySinh());

        HashMap<String, String> hashGioitinh = new HashMap<>();
        hashGioitinh.put("gioitinh", nhanVien.getGioTinh());

        HashMap<String, String> hashSDT = new HashMap<>();
        hashSDT.put("sdt", nhanVien.getSodt());

        HashMap<String, String> hasDiachi = new HashMap<>();
        hasDiachi.put("diachi", nhanVien.getDiaChi());

        atttr.add(hashHam);
        atttr.add(hashTenNV);
        atttr.add(hashTenDN);
        atttr.add(hashMatKhau);
        atttr.add(hashMaloaiNV);
        atttr.add(hashEmailDocQuyen);
        atttr.add(hashNgaysinh);
        atttr.add(hashGioitinh);
        atttr.add(hashSDT);
        atttr.add(hasDiachi);

        DownloadJSON downloadJSON = new DownloadJSON(link, atttr);
        downloadJSON.execute();

        try {
            String data = downloadJSON.get();
            Log.d("data dk", data );
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
