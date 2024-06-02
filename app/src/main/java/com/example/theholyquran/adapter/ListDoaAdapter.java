package com.example.theholyquran.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.theholyquran.LauncherDoaActivity;
import com.example.theholyquran.R;
import com.example.theholyquran.model.Doa;

import java.util.ArrayList;
import java.util.List;

public class ListDoaAdapter extends RecyclerView.Adapter<ListDoaAdapter.Holder> {

    private Context context;
    private ArrayList<Doa> listOfDoa;

    public ListDoaAdapter(Context context, ArrayList<Doa> listDoa) {
        this.context = context;
        this.listOfDoa = listDoa;

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_doa, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Doa doa = listOfDoa.get(position);
        holder.item_text.setText(doa.getDoa());
        holder.item_number.setText(String.valueOf(position + 1));
        holder.item_base.setOnClickListener( v -> {
            context.startActivity(new Intent(context, LauncherDoaActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).putExtra("Doa", doa));
        });
    }


    @Override
    public int getItemCount() {
        return listOfDoa.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView item_text, item_number;
        LinearLayout item_base;

        public Holder(View itemView) {
            super(itemView);
            item_base = itemView.findViewById(R.id.item_base);
            item_text = itemView.findViewById(R.id.item_text);
            item_number = itemView.findViewById(R.id.item_number);

        }
    }
}

