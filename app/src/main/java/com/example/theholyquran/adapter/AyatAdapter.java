package com.example.theholyquran.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.theholyquran.R;
import com.example.theholyquran.model.Ayat;

import java.util.List;

public class AyatAdapter extends RecyclerView.Adapter<AyatAdapter.Holder> {

    private Context context;
    private List<Ayat> list;
    private List<Ayat> listIndo;

    public AyatAdapter(Context context, List<Ayat> list, List<Ayat> listIndo) {
        this.context = context;
        this.list = list;
        this.listIndo = listIndo;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_ayat, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (list != null && listIndo != null) {
            Ayat ayat = list.get(position);
            Ayat ayatIndo = listIndo.get(position);
            if (ayat != null && ayatIndo != null) {
                holder.tvNomorAyat.setText(ayat.getNumberInSurah());
                holder.tvArabic.setText(ayat.getText());
                holder.tvTerjemahan.setText(ayatIndo.getText());
            }
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tvNomorAyat, tvArabic, tvTerjemahan;

        public Holder(View itemView) {
            super(itemView);
            tvNomorAyat = itemView.findViewById(R.id.tvNomorAyat);
            tvArabic = itemView.findViewById(R.id.tvArabic);
            tvTerjemahan = itemView.findViewById(R.id.tvTerjemahan);
        }
    }
}
