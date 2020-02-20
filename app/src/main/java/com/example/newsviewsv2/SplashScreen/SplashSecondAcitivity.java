package com.example.newsviewsv2.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsviewsv2.R;

public class SplashSecondAcitivity extends AppCompatActivity {


    private TextView showText;
    private int progressUp;
    private ImageView newsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_second_acitivity);
        newsImage = findViewById(R.id.newsIconID);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        showText = findViewById(R.id.showTV);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }


        });

        thread.start();


    }




    public void doWork(){

        for(progressUp=20;progressUp <= 100;progressUp=progressUp+20){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public void startApp(){

        Intent intent = new Intent(SplashSecondAcitivity.this, SplashThirdAcitivity.class);
        startActivity(intent);
        finish();
    }
}
