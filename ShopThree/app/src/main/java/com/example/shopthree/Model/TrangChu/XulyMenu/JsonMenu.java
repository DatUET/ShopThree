package com.example.shopthree.Model.TrangChu.XulyMenu;

import android.os.Bundle;
import android.util.Log;

import com.example.shopthree.ConnectInternet.DownloadJSON;
import com.example.shopthree.Model.ObjectClass.Loaisanpham;
import com.example.shopthree.View.TrangChu.TrangchuActivity;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonMenu {

    String usernameFB;
    public List<Loaisanpham> ParsenMenu(String data)
    {
        List<Loaisanpham> dsLoaisanpham = new ArrayList<>();

        try
        {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray loaisanpham = jsonObject.getJSONArray("LOAISANPHAM");
            for(int i=0;i<loaisanpham.length();i++)
            {
                JSONObject value = loaisanpham.getJSONObject(i);

                Loaisanpham typeSanPham = new Loaisanpham();
                typeSanPham.setMALOAISP(Integer.parseInt(value.getString("MALOAISP")));
                typeSanPham.setMALOAICHA(Integer.parseInt(value.getString("MALOAI_CHA")));
                typeSanPham.setTENLOAISP(value.getString("TENLOAISP"));

                dsLoaisanpham.add(typeSanPham);
            }
        }
        catch (Exception ex)
        {
            Log.e("Lỗi file jsonmenu", ex.toString());
        }
        return dsLoaisanpham;
    }

    public List<Loaisanpham> getloaisptheomaloai(int maloai)
    {
        List<Loaisanpham> loaisanphamList = new ArrayList<>();
        List<HashMap<String, String>> atttr = new ArrayList<>();
        String dataJSON = "";

        String link = TrangchuActivity.SERVER_NAME ;

        HashMap<String, String> hashHam = new HashMap<>();
        hashHam.put("ham", "LayDanhSachMenu");

        HashMap<String, String> hashMaloaicha = new HashMap<>();
        hashMaloaicha.put("maloaicha", String.valueOf(maloai));

        atttr.add(hashMaloaicha);
        atttr.add(hashHam);

        DownloadJSON downloadJSON = new DownloadJSON(link, atttr);
        downloadJSON.execute();
        try {
            dataJSON = downloadJSON.get();
            JsonMenu jsonMenu = new JsonMenu();
            loaisanphamList = jsonMenu.ParsenMenu(dataJSON);
        }
        catch (Exception ex)
        {
            Log.e("Lỗi lấy sp theo mã", ex.toString());
        }
        return loaisanphamList;
    }

}
