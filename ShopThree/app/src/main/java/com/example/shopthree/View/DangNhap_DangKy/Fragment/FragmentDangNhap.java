package com.example.shopthree.View.DangNhap_DangKy.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopthree.Model.DangNhap_DangKy.DangNhap_Model;
import com.example.shopthree.R;
import com.example.shopthree.View.TrangChu.TrangchuActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;


import java.util.Arrays;

public class FragmentDangNhap extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    Button btnFBLogin, btnGGLogin, btnLogin;
    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    public static int LOG_IN_GOOGLE_PLUS = 123;
    ProgressDialog progressDialog;
    DangNhap_Model dangNhap_model;
    EditText txtusername, txtpassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmant_dangnhap_layout,container, false);

        DangNhap_Model modelDN = new DangNhap_Model();
        mGoogleApiClient = modelDN.getGoogleApiClient(getContext(), this);

        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent iTrangchu = new Intent(getActivity(), TrangchuActivity.class);
                startActivity(iTrangchu);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        addControl(view);
        addEvents(view);
        return view;
    }

    private void addEvents(View view) {

        btnFBLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
            }
        });

        btnGGLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGooglePlus = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(iGooglePlus, LOG_IN_GOOGLE_PLUS);
                showProgressDialog();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangNhap_model = new DangNhap_Model();
                String tendangnhap = txtusername.getText().toString();
                String password = txtpassword.getText().toString();
                Boolean check = dangNhap_model.KiemtraDangnhap(getActivity(), tendangnhap, password);
                if(check)
                {
                    Intent iTrangchu = new Intent(getActivity(), TrangchuActivity.class);
                    startActivity(iTrangchu);
                }
                else
                    Toast.makeText(getActivity(), "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void addControl(View view) {
        btnFBLogin = view.findViewById(R.id.btnFBLogin);
        btnGGLogin = view.findViewById(R.id.btnGGLogin);
        btnLogin = view.findViewById(R.id.btnLogin);
        txtusername = view.findViewById(R.id.txtUser);
        txtpassword = view.findViewById(R.id.txtPass);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if(requestCode == LOG_IN_GOOGLE_PLUS)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess())
            {
                progressDialog.cancel();
                Intent iTrangchu = new Intent(getActivity(), TrangchuActivity.class);
                startActivity(iTrangchu);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void showProgressDialog()
    {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }
}
