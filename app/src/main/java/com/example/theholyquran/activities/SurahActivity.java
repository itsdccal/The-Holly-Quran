package com.example.theholyquran.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theholyquran.R;
import com.example.theholyquran.adapter.AyatAdapter;
import com.example.theholyquran.model.Ayat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SurahActivity extends AppCompatActivity {

    private ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah);
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(v -> {
            finish();
        });

        String json = getIntent().getStringExtra("jsonlist");
        String jsonIndo = getIntent().getStringExtra("jsonlistIndo");
        String jsonTitle = getIntent().getStringExtra("jsonTitle");
        Type type = new TypeToken<List<Ayat>>() {
        }.getType();

        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvSubTitle = findViewById(R.id.tvSubTitle);
        TextView tvInfo = findViewById(R.id.tvInfo);

        tvSubTitle.setText(jsonIndo);
        tvSubTitle.findViewById(R.id.tvSubTitle);
        tvSubTitle.setText(jsonIndo);

        tvTitle.setText(jsonTitle);
        tvTitle.findViewById(R.id.tvTitle);
        tvTitle.setText(jsonTitle);


        Gson gson = new Gson();
        List<Ayat> ayatList = gson.fromJson(json, type);
        List<Ayat> ayatListIndo = gson.fromJson(jsonIndo, type);

        RecyclerView rvAyat = findViewById(R.id.rvAyat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        AyatAdapter ayatAdapter = new AyatAdapter(this, ayatList, ayatListIndo);
        rvAyat.setLayoutManager(layoutManager);
        rvAyat.setHasFixedSize(true);
        rvAyat.setAdapter(ayatAdapter);

    }
}