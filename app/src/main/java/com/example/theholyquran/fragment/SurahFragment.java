package com.example.theholyquran.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theholyquran.R;
import com.example.theholyquran.adapter.SurahAdapter;
import com.example.theholyquran.model.Surah;


public class SurahFragment extends Fragment {

    private RecyclerView recyclerView;
    private SurahAdapter surahAdapter;
    private List<Surah> surahList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_surah, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        surahAdapter = new SurahAdapter(surahList);
        recyclerView.setAdapter(surahAdapter);
        return view;
    }
}
