package com.example.newsviewsv2.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.newsviewsv2.R;

public class SplashfirstAcitivity extends AppCompatActivity {


    private ProgressBar progressBar;
    private int progressUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashfirst_acitivity);
        progressBar = findViewById(R.id.ProgressBar);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
                progressBar.setProgress(progressUp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public void startApp(){

        Intent intent = new Intent(SplashfirstAcitivity.this, SplashSecondAcitivity.class);
        startActivity(intent);
        finish();
    }
}
