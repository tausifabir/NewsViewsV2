package com.example.newsviewsv2.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.newsviewsv2.MainActivity;
import com.example.newsviewsv2.R;

public class SplashThirdAcitivity extends AppCompatActivity {


    private TextView helloTV;
    private TextView nameTV;
    private int progressUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_third_acitivity);


        Intent intent = getIntent();
        helloTV = findViewById(R.id.hello);
        nameTV = findViewById(R.id.name);



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

        Intent intent = new Intent(SplashThirdAcitivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
