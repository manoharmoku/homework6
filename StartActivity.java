package com.example.homework6;
// StartActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this, abc.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);
    }
}
