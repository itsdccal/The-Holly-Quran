package com.example.theholyquran.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.theholyquran.R;
import com.example.theholyquran.model.Surah;

import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.SurahViewHolder> {

    private Context context;
    private List<Surah> list;
    private List<Surah> listIndo;
    private View view;

    public SurahAdapter(Context context, List<Surah> list, List<Surah> listIndo) {
        this.context = context;
        this.list = list;
        this.listIndo = listIndo;
    }

    public SurahAdapter(Context context, List<Surah> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SurahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        view = LayoutInflater.from(context).inflate(R.layout.layout_surah, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SurahViewHolder holder, int position) {
        Surah surah = list.get(position);
        holder.tvNumber.setText(String.valueOf(surah.getNumber()));
        holder.tvAyat.setText(surah.getName());
        holder.tvInfo.setText(surah.getPlace() + " - " + surah.getAyatCount() + " Ayat");
        holder.tvName.setText(surah.getArabicName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SurahViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNumber, tvAyat, tvInfo, tvName;

        public Holder(View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvAyat = itemView.findViewById(R.id.tvAyat);
            tvInfo = itemView.findViewById(R.id.tvInfo);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}

