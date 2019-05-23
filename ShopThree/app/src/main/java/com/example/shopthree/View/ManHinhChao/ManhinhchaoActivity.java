package com.example.shopthree.View.ManHinhChao;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.shopthree.R;
import com.example.shopthree.View.TrangChu.TrangchuActivity;
import com.karan.churi.PermissionManager.PermissionManager;

public class ManhinhchaoActivity  extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchao_layout);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }
                catch (Exception ex)
                {

                }
                finally {
                    Intent intent = new Intent(ManhinhchaoActivity.this, TrangchuActivity.class);
                    startActivity(intent);
                }
            }
        });
        thread.start();
    }

}
