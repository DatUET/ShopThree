package com.example.shopthree.Model.DangNhap_DangKy;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.shopthree.ConnectInternet.DownloadJSON;
import com.example.shopthree.Model.ObjectClass.NhanVien;
import com.example.shopthree.View.TrangChu.TrangchuActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DangNhap_Model {

    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;
    public AccessToken getTokenFB()
    {
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };

        accessToken = AccessToken.getCurrentAccessToken();
        return accessToken;
    }


    public void removeTokenFB()
    {
        accessTokenTracker.stopTracking();
    }

    public GoogleApiClient getGoogleApiClient(Context context, GoogleApiClient.OnConnectionFailedListener failedListener)
    {
        GoogleApiClient mGoogleApiClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleApiClient = new GoogleApiClient.Builder(context).enableAutoManage((AppCompatActivity) context, failedListener).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        return mGoogleApiClient;
    }

    public GoogleSignInResult getInformationUserGoogle(GoogleApiClient googleApiClient)
    {
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone())
        {
            return opr.get();
        }
        else
        {
            return null;
        }
    }

    public Boolean KiemtraDangnhap(Context context, String tendangnhap, String matkhau)
    {
        Boolean check = false;

        String link = TrangchuActivity.SERVER_NAME;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "KiemTraDangNhap");

        HashMap<String, String> hsTenDangNhap = new HashMap<>();
        hsTenDangNhap.put("tendangnhap", tendangnhap);

        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau", matkhau);

        attrs.add(hsHam);
        attrs.add(hsTenDangNhap);
        attrs.add(hsMatKhau);

        DownloadJSON downloadJSON = new DownloadJSON(link, attrs);
        downloadJSON.execute();
        try {
            String data = downloadJSON.get();
            Log.d("data dang nhap", data);
            JSONObject jsonObject = new JSONObject(data);
            String ketqua = jsonObject.getString("ketqua");
            if(ketqua.equals("true"))
            {
                check = true;

                String tennv = jsonObject.getString("tennv");
                int manv = Integer.parseInt(jsonObject.getString("manv"));
                updateCachedDangnhap(context, tennv, manv);
            }
            else
            {
                check = false;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return check;
    }

    public String getCachedDangnhap(Context context)
    {
        SharedPreferences cachedDangnhap =  context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        String tennv = cachedDangnhap.getString("tennv", "");
        return tennv;
    }

    public int getCacheManvDangnhap(Context context)
    {
        SharedPreferences cachedDangnhap =  context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        int manv = cachedDangnhap.getInt("manv", 0);
        return manv;
    }

    public void updateCachedDangnhap(Context context, String data, int manv)
    {
        SharedPreferences cachedDangnhap =  context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cachedDangnhap.edit();
        editor.putString("tennv", data);
        editor.putInt("manv", manv);
        editor.commit();
    }
}
