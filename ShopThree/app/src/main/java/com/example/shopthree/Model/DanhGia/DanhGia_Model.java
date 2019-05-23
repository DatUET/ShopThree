package com.example.shopthree.Model.DanhGia;

import android.util.Log;

import com.example.shopthree.ConnectInternet.DownloadJSON;
import com.example.shopthree.Model.ObjectClass.DanhGia;
import com.example.shopthree.View.TrangChu.TrangchuActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DanhGia_Model {

    public boolean ThemDanhGia(DanhGia danhGia)
    {
        Boolean check = false;

        List<HashMap<String, String>> atttr = new ArrayList<>();
        String link = TrangchuActivity.SERVER_NAME;

        HashMap<String, String> hashHam = new HashMap<>();
        hashHam.put("ham", "ThemDanhGia");

        HashMap<String, String> hashMaSP = new HashMap<>();
        hashMaSP.put("masp", danhGia.getMASP() + "");

        HashMap<String, String> hasTieDe = new HashMap<>();
        hasTieDe.put("tieude", danhGia.getTIEUDE());

        HashMap<String, String> hashNoidung = new HashMap<>();
        hashNoidung.put("noidung", danhGia.getNOIDUNG());

        HashMap<String, String> hashSoSao = new HashMap<>();
        hashSoSao.put("sosao", danhGia.getSOSAO() + "");

        HashMap<String, String> hashThietBi = new HashMap<>();
        hashThietBi.put("tenthietbi", danhGia.getTENTHIETBI());

        atttr.add(hashHam);
        atttr.add(hashMaSP);
        atttr.add(hasTieDe);
        atttr.add(hashNoidung);
        atttr.add(hashSoSao);
        atttr.add(hashThietBi);

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

    public List<DanhGia> getDanhSachDanhGia(int maSP, int limit)
    {
        List<DanhGia> danhGias = new ArrayList<>();
        List<HashMap<String, String>> atttr = new ArrayList<>();
        String dataJSON = "";

        String link = TrangchuActivity.SERVER_NAME ;

        HashMap<String, String> hashHam = new HashMap<>();
        hashHam.put("ham", "LayDanhSachDanhGiaTheoMaSP");

        HashMap<String, String> hashMaSP = new HashMap<>();
        hashMaSP.put("masp", maSP+"");

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
            JSONArray jsonArrayDanhsachThuongHieu = jsonObject.getJSONArray("DANHSACHDANHGIA");

            int count = jsonArrayDanhsachThuongHieu.length();

            for (int i=0; i<count; i++)
            {
                DanhGia danhGia = new DanhGia();
                JSONObject object = jsonArrayDanhsachThuongHieu.getJSONObject(i);

                danhGia.setMASP(object.getInt("MASP"));
                danhGia.setNOIDUNG(object.getString("NOIDUNG"));
                danhGia.setTENTHIETBI(object.getString("TENTHIETBI"));
                danhGia.setNGAYDANHGIA(object.getString("NGAYDANHGIA"));
                danhGia.setSOSAO(object.getInt("SOSAO"));
                danhGia.setMADG(object.getString("MADG"));
                danhGia.setTIEUDE(object.getString("TIEUDE"));

                danhGias.add(danhGia);
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return danhGias;
    }
}
