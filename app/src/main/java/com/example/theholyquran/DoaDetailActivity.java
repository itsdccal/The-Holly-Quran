package com.example.theholyquran;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.theholyquran.R;
import com.example.theholyquran.databinding.ActivityDetailDoaBinding;
import com.example.theholyquran.model.Doa;

public class DoaDetailActivity extends AppCompatActivity {

    private ActivityDetailDoaBinding binding;

    private Doa doa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailDoaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getData();
        initializeLogic();
        initializeClick();
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Handle the back button action here
        finish();
        return true;
    }

    private void getData() {
        doa = (Doa) getIntent().getSerializableExtra("Doa");
        if (doa == null) {
            return;
        }
        setTitle(doa.getDoa());
        binding.viewIsiDoa.fillAyat.setText(doa.getAyat());
        binding.viewIsiDoa.fillLatin.setText(doa.getLatin());
        binding.viewIsiDoa.fillArti.setText(doa.getArtinya());
    }

    private void initializeLogic() {

    }

    private void initializeClick() {

    }
}