package com.example.shopthree.Model.DangNhap_DangKy;

import com.example.shopthree.ConnectInternet.DownloadJSON;
import com.example.shopthree.View.TrangChu.TrangchuActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KiemTraEmail_Model {
    public Boolean TrungEmail(String email)
    {
        Boolean check = false;

        List<HashMap<String, String>> atttr = new ArrayList<>();
        String link = TrangchuActivity.SERVER_NAME;

        HashMap<String, String> hashHam = new HashMap<>();
        hashHam.put("ham", "KiemTraEmail");

        HashMap<String, String> hashEmail = new HashMap<>();
        hashEmail.put("email", email);

        atttr.add(hashHam);
        atttr.add(hashEmail);

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
}
