package com.example.shopthree.Model.ChiTietSanPham;

import com.example.shopthree.ConnectInternet.DownloadJSON;
import com.example.shopthree.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.shopthree.Model.ObjectClass.ChiTietSanPham;
import com.example.shopthree.Model.ObjectClass.DanhGia;
import com.example.shopthree.Model.ObjectClass.SanPham;
import com.example.shopthree.View.TrangChu.TrangchuActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChiTietSanPham_Model {

    public SanPham getChiTietSP(String tenham, String tenmang, int masp)
    {
        SanPham sanPham = new SanPham();
        List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();

        List<HashMap<String, String>> atttr = new ArrayList<>();
        String dataJSON = "";

        String link = TrangchuActivity.SERVER_NAME ;

        HashMap<String, String> hashHam = new HashMap<>();
        hashHam.put("ham", tenham);

        HashMap<String, String> hashMasp = new HashMap<>();
        hashMasp.put("masp", masp + "");

        atttr.add(hashHam);
        atttr.add(hashMasp);

        DownloadJSON downloadJSON = new DownloadJSON(link, atttr);
        downloadJSON.execute();
        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhsachThuongHieu = jsonObject.getJSONArray(tenmang);

            int count = jsonArrayDanhsachThuongHieu.length();

            for (int i=0; i<count; i++)
            {
                JSONObject object = jsonArrayDanhsachThuongHieu.getJSONObject(i);
                ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                chiTietKhuyenMai.setPHANTRAMKM(object.getInt("PHANTRAMKM"));

                sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);
                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHNHO(object.getString("ANHNHO"));
                sanPham.setSOLUONG(object.getInt("SOLUONG"));
                sanPham.setTHONGTIN(object.getString("THONGTIN"));
                sanPham.setMALOAISP(object.getInt("MALOAISP"));
                sanPham.setMATHUONGHIEU(object.getInt("MATHUONGHIEU"));
                sanPham.setMANV(object.getInt("MANV"));
                sanPham.setLUOTMUA(object.getInt("LUOTMUA"));
                sanPham.setTENNV(object.getString("TENNV"));
                sanPham.setSOLUONGTONKHO(object.getInt("SOLUONGTONKHO"));

                JSONArray jsonArrayThongSoKyThuat = object.getJSONArray("THONGSOKYTHUAT");
                for (int j=0; j<jsonArrayThongSoKyThuat.length(); j++)
                {
                    JSONObject jsonObject1 = jsonArrayThongSoKyThuat.getJSONObject(j);
                    for(int k=0; k<jsonObject1.names().length(); k++)
                    {
                        String tenchitiet = jsonObject1.names().getString(k);
                        String giatrichitiet = jsonObject1.getString(tenchitiet);

                        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                        chiTietSanPham.setTENCHITIET(tenchitiet);
                        chiTietSanPham.setGIATRI(giatrichitiet);

                        chiTietSanPhamList.add(chiTietSanPham);
                    }
                }
                sanPham.setChiTietSanPhamList(chiTietSanPhamList);
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return sanPham;
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
