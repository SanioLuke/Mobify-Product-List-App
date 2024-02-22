package com.example.mobifyproductlistapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobifyproductlistapp.databinding.ActivitySplashScreenBinding;
import com.example.mobifyproductlistapp.utils.AppUtils;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppUtils.lightBackgroundStatusBarDesign(SplashScreen.this);

        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreen.this, HomeActivity.class));
            finish();
        }, 3000);
    }
}