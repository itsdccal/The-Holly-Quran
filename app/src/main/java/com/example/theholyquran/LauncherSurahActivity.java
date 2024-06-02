package com.example.theholyquran;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.theholyquran.R;
import com.example.theholyquran.databinding.ActivityLauncherSurahBinding;

public class LauncherSurahActivity extends AppCompatActivity {

    private ActivityLauncherSurahBinding binding;

    private String json, jsonIndo, jsonTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLauncherSurahBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getData();
    }

    private void getData() {
        json = getIntent().getStringExtra("jsonlist");
        jsonIndo = getIntent().getStringExtra("jsonlistIndo");
        jsonTitle = getIntent().getStringExtra("jsonTitle");
        changeStateActivity();
    }

    private void changeStateActivity() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(this, SurahActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("jsonlist", json);
            intent.putExtra("jsonlistIndo", jsonIndo);
            intent.putExtra("jsonTitle", jsonTitle);
            startActivity(intent);
            finish();
        }, 3000);
    }
}
