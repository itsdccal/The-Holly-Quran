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

import java.util.List;


public class SurahFragment extends Fragment {

    private RecyclerView rv_surah;
    private SurahAdapter surahAdapter;
    private List<Surah> surahList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_surah, container, false);
        rv_surah = view.findViewById(R.id.rv_surah);

        rv_surah.setLayoutManager(new LinearLayoutManager(getActivity()));

        surahAdapter = new SurahAdapter(getContext(), surahList);

        rv_surah.setAdapter(surahAdapter);

        return view;
    }
}
