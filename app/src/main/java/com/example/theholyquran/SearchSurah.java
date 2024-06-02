package com.example.theholyquran;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theholyquran.R;
import com.example.theholyquran.adapter.SurahSearchAdapter;
import com.example.theholyquran.databinding.ActivitySearchSurahBinding;
import com.example.theholyquran.local.SurahData;
import com.example.theholyquran.model.Surah;
import com.example.theholyquran.adapter.SurahAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchSurah extends AppCompatActivity {

    private ActivitySearchSurahBinding binding;

    private SurahSearchAdapter surahAdapter;
    private List<Surah> surahList = new ArrayList<>();

    private List<Surah> surahsArabic = new ArrayList<>();
    private List<Surah> surahsIndo = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchSurahBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        setTitle("Cari surah...");
        if (SurahData.getSurahListArabic(getApplicationContext()) != null) {
            surahsArabic.clear();
            surahsArabic.addAll(SurahData.getSurahListArabic(getApplicationContext()));
            surahsIndo.clear();
            surahsIndo.addAll(SurahData.getSurahListIndo(getApplicationContext()));
            surahAdapter = new SurahSearchAdapter(getApplicationContext(), surahsArabic, surahsIndo);
            binding.recyclerViewSurah.setAdapter(surahAdapter);
            binding.recyclerViewSurah.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (surahAdapter != null) {
                    if (surahAdapter.getItemCount() > 0) {
                        surahAdapter.filter(query);
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (surahAdapter != null) {
                    if (surahAdapter.getItemCount() > 0) {
                        surahAdapter.filter(newText);
                    }
                }
                return false;
            }
        });


        return true;
    }

}