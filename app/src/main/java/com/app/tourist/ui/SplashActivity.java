package com.app.tourist.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.WindowManager;

import com.app.tourist.MainActivity;
import com.app.tourist.R;

public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_SCREEN_DELAY = 1000; // 2 secondes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*getWindow().getDecorView().getWindowInsetsController().hide(
                android.view.WindowInsets.Type.statusBars()
        );*/

        setContentView(R.layout.activity_splash);
        
        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(5*1000);

                    Intent intent =new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);

                    finish();
                } catch (Exception e) {
                }
            }
        };

        background.start();
    }
}