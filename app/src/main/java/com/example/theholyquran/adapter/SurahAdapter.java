package com.example.theholyquran.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.theholyquran.LauncherSurahActivity;
import com.example.theholyquran.R;

import com.example.theholyquran.local.TemporaryData;
import com.example.theholyquran.model.Surah;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.SurahViewHolder> {

    private Context context;
    private final List<Surah> list;
    private List<Surah> listIndo;
    private View view;

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());

    public SurahAdapter(Context context, List<Surah> list, List<Surah> listIndo) {
        this.context = context;
        this.list = list;
        this.listIndo = listIndo != null ? listIndo : new ArrayList<>();
    }

    @NonNull
    @Override
    public SurahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        view = LayoutInflater.from(context).inflate(R.layout.item_surah, parent, false);
        return new SurahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SurahViewHolder holder, int position) {
        final Surah surah = list.get(position);
        final Surah surahIndo = position < listIndo.size() ? listIndo.get(position) : null;

        holder.tvNumber.setText(String.valueOf(surah.getNumber()));
        holder.tvAyat.setText(surah.getEnglishName());
        holder.tvInfo.setText(surah.getType() + " - " + surah.getAyatList().size() + " Ayat");
        holder.tvName.setText(surah.getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = gson.toJson(surah.getAyatList());
                String jsonIndo = surahIndo != null ? gson.toJson(surahIndo.getAyatList()) : "";
                TemporaryData.saveLastSurah(context, json, jsonIndo, surah.getEnglishName());
                executor.execute(() -> {
                    Intent intent = new Intent(context, LauncherSurahActivity.class);
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
        return list.size();
    }

    public class SurahViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvNumber;
        private final TextView tvAyat;
        private final TextView tvInfo;
        private final TextView tvName;

        public SurahViewHolder(View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvAyat = itemView.findViewById(R.id.tvAyat);
            tvInfo = itemView.findViewById(R.id.tvInfo);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
