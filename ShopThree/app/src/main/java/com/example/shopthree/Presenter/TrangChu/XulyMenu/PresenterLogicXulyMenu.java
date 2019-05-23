package com.example.shopthree.Presenter.TrangChu.XulyMenu;

import com.example.shopthree.ConnectInternet.DownloadJSON;
import com.example.shopthree.Model.DangNhap_DangKy.DangNhap_Model;
import com.example.shopthree.Model.ObjectClass.Loaisanpham;
import com.example.shopthree.Model.TrangChu.XulyMenu.JsonMenu;
import com.example.shopthree.View.TrangChu.TrangchuActivity;
import com.example.shopthree.View.TrangChu.ViewXulyMenu;
import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PresenterLogicXulyMenu implements IPresenterXulyMenu {
    String usernameFB;

    ViewXulyMenu viewXulyMenu;

    public PresenterLogicXulyMenu(ViewXulyMenu viewXulyMenu) {
        this.viewXulyMenu = viewXulyMenu;
    }

    @Override
    public void getDanhSachMenu() {
        List<Loaisanpham> loaisanphamList = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String, String>> atttr = new ArrayList<>();

        String link = TrangchuActivity.SERVER_NAME;

        HashMap<String, String> hashHam = new HashMap<>();
        hashHam.put("ham", "LayDanhSachMenu");

        HashMap<String, String> hashMaloaicha = new HashMap<>();
        hashMaloaicha.put("maloaicha", "0");

        atttr.add(hashMaloaicha);
        atttr.add(hashHam);

        DownloadJSON downloadJSON = new DownloadJSON(link, atttr);
        downloadJSON.execute();
        try {
            dataJSON = downloadJSON.get();
            JsonMenu jsonMenu = new JsonMenu();
            loaisanphamList = jsonMenu.ParsenMenu(dataJSON);
            viewXulyMenu.showDanhSachSP(loaisanphamList);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public AccessToken getTenUserFB() {
        DangNhap_Model dangNhap_model = new DangNhap_Model();
        AccessToken accessToken = dangNhap_model.getTokenFB();
        return accessToken;
    }
}
