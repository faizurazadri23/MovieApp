package com.faizurazadri.movieapp.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.faizurazadri.movieapp.R;
import com.faizurazadri.movieapp.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {

    ActivitySplashScreenBinding splashScreenBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashScreenBinding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(splashScreenBinding.getRoot());
    }
}