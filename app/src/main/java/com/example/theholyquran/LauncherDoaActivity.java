package com.example.theholyquran;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.theholyquran.R;
import com.example.theholyquran.databinding.ActivityLauncherDoaBinding;
import com.example.theholyquran.model.Doa;

public class LauncherDoaActivity extends AppCompatActivity {

    private ActivityLauncherDoaBinding binding;

    private Doa doa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLauncherDoaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getData();
    }

    private void getData() {
        doa = (Doa) getIntent().getSerializableExtra("Doa");
        changeStateActivity();
    }

    private void changeStateActivity() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(this, DoaDetailActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).putExtra("Doa", doa));
            finish();
        }, 3000);
    }
}
