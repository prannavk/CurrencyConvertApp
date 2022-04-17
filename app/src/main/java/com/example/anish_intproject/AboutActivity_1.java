package com.example.anish_intproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity_1 extends AppCompatActivity {

    Button fcc_ref, fb_fcc_ref, c_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about1);
        Intent abt_g = getIntent();

        fcc_ref = (Button) findViewById(R.id.button_key_fcc);
        fb_fcc_ref = (Button) findViewById(R.id.button_share_fb);
        c_list = (Button) findViewById(R.id.button23cl);

        fcc_ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://free.currencyconverterapi.com/";
                Intent site_fcc = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(site_fcc);
            }
        });

        fb_fcc_ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.facebook.com/dialog/share?app_id=140586622674265&display=popup&href=https%3A%2F%2Ffree.currencyconverterapi.com%2F%23.YgC5e7SgiDg.facebook&redirect_uri=http%3A%2F%2Fs7.addthis.com%2Fstatic%2Fthankyou.html";
                Intent site_share_fb = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(site_share_fb);
            }
        });

        c_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abl = new Intent(Intent.ACTION_VIEW,Uri.parse("https://free.currconv.com/api/v7/currencies?q=USD&apiKey=5fdb987da4a5c16abfb5"));
                startActivity(abl);
            }
        });

    }
}