package com.example.theholyquran.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.theholyquran.R;
import com.example.theholyquran.model.Ayat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SurahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah);

        String json = getIntent().getStringExtra("jsonlist");
        String jsonIndo = getIntent().getStringExtra("jsonlistIndo");
        String jsonTitle = getIntent().getStringExtra("jsonTitle");
        Type type = new TypeToken<List<Ayat>>() {
        }.getType();

        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(jsonTitle);
        tvTitle.findViewById(R.id.tvTitle);
        tvTitle.setText(jsonTitle);

        Gson gson = new Gson();
        List<Ayat> ayatList = gson.fromJson(json, type);
        List<Ayat> ayatListIndo = gson.fromJson(jsonIndo, type);

//        RecyclerView rvAyat = findViewById(R.id.rvAyat);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        AyatAdapter ayatAdapter = new AyatAdapter(this, ayatList, ayatListIndo);
//        rvAyat.setLayoutManager(layoutManager);
//        rvAyat.setHasFixedSize(true);
//        rvAyat.setAdapter(ayatAdapter);

    }
}