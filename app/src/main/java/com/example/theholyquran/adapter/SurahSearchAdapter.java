package com.example.theholyquran.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theholyquran.LauncherSurahActivity;
import com.example.theholyquran.R;
import com.example.theholyquran.databinding.ItemSurahBinding;
import com.example.theholyquran.local.TemporaryData;
import com.example.theholyquran.model.Surah;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SurahSearchAdapter extends RecyclerView.Adapter<SurahSearchAdapter.SurahViewHolder> {

    private static final String TAG = "SurahSearchAdapter";

    private Context context;
    private List<Surah> surahList;
    private List<Surah> surahListFull;
    private List<Surah> surahListIndo;
    private List<Surah> surahListIndoFull;

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());

    public SurahSearchAdapter(Context context, List<Surah> surahList, List<Surah> surahListIndo) {
        this.context = context;
        this.surahList = new ArrayList<>(surahList);  // Ensure the list is mutable
        this.surahListFull = new ArrayList<>(surahList);
        this.surahListIndo = new ArrayList<>(surahListIndo);  // Ensure the list is mutable
        this.surahListIndoFull = new ArrayList<>(surahListIndo);
    }

    @NonNull
    @Override
    public SurahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_surah, parent, false);
        return new SurahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SurahViewHolder holder, int position) {
        Surah surah = surahList.get(position);
        Surah surahIndo = surahListIndo.get(position);

        holder.binding.tvName.setText(surah.getName());
        holder.binding.tvAyat.setText(surah.getEnglishName());
        holder.binding.tvNumber.setText(String.valueOf(surah.getNumber()));
        holder.binding.tvInfo.setText(surah.getType() + " - " + surah.getAyatList().size() + " Ayat");

        holder.binding.itemBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = gson.toJson(surah.getAyatList());
                String jsonIndo = surahIndo != null ? gson.toJson(surahIndo.getAyatList()) : "";
                TemporaryData.saveLastSurah(context, json, jsonIndo, surah.getEnglishName());
                executor.execute(() -> {
                    Intent intent = new Intent(context, LauncherSurahActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("jsonlist", json);
                    intent.putExtra("jsonlistIndo", jsonIndo);
                    intent.putExtra("jsonTitle", surah.getEnglishName());
                    handler.post(() -> {
                        context.startActivity(intent);
                    });
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return surahList.size();
    }

    public void filter(String text) {
        if (surahListFull.isEmpty() || surahListIndoFull.isEmpty()) {
            return;
        }
        Log.d(TAG, "Filtering with text: " + text);
        surahList.clear();
        surahListIndo.clear();
        if (text.isEmpty()) {
            surahList.addAll(surahListFull);
            surahListIndo.addAll(surahListIndoFull);
        } else {
            text = text.toLowerCase();
            for (Surah surah : surahListFull) {
                if (surah.getEnglishName().toLowerCase().contains(text)) {
                    surahList.add(surah);
                }
            }
            for (Surah surahIndo : surahListIndoFull) {
                if (surahIndo.getEnglishName().toLowerCase().contains(text)) {
                    surahListIndo.add(surahIndo);
                }
            }
        }
        Log.d(TAG, "Filtered Surah count: " + surahList.size());
        handler.post(this::notifyDataSetChanged);
    }

    public static class SurahViewHolder extends RecyclerView.ViewHolder {
        private ItemSurahBinding binding;

        public SurahViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemSurahBinding.bind(itemView);
        }
    }
}
