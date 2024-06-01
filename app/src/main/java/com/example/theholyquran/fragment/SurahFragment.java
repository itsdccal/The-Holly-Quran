package com.example.theholyquran.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theholyquran.R;
import com.example.theholyquran.adapter.SurahAdapter;
import com.example.theholyquran.api.ApiClient;
import com.example.theholyquran.api.ApiInterface;
import com.example.theholyquran.model.Cek;
import com.example.theholyquran.model.Data;
import com.example.theholyquran.model.Surah;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurahFragment extends Fragment {

    private RecyclerView rv_surah;
    private SurahAdapter surahAdapter;
    private static final String ARABIC = "quran-uthmani";
    private static final String INDO = "id.indonesian";

    private final List<Surah> surahsArabic = new ArrayList<>();
    private final List<Surah> surahsIndo = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_surah, container, false);
        rv_surah = view.findViewById(R.id.rv_surah);

        rv_surah.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_surah.setHasFixedSize(true);
        surahAdapter = new SurahAdapter(getContext(), surahsArabic, surahsIndo);

        rv_surah.setAdapter(surahAdapter);
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        Call<Cek> callArabic = apiInterface.getCek(ARABIC);
        Call<Cek> callIndo = apiInterface.getCek(INDO);

        getDataListArabic(callArabic);
        getDataTarjim(callIndo);

        return view;
    }

    private void getDataTarjim(Call<Cek> callIndo) {
        callIndo.enqueue(new Callback<Cek>() {
            @Override
            public void onResponse(@NonNull Call<Cek> call, @NonNull Response<Cek> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Data data = response.body().getData();
                    surahsIndo.clear();
                    surahsIndo.addAll(data.getSurahs());
                    surahAdapter.notifyDataSetChanged();
                } else {
                    showError("Data tidak ditemukan");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Cek> call, @NonNull Throwable t) {
                showError("Gagal mengakses data: " + t.getMessage());
            }
        });
    }

    private void getDataListArabic(Call<Cek> call) {
        call.enqueue(new Callback<Cek>() {
            @Override
            public void onResponse(@NonNull Call<Cek> call, @NonNull Response<Cek> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Data data = response.body().getData();
                    surahsArabic.clear();
                    surahsArabic.addAll(data.getSurahs());
                    surahAdapter.notifyDataSetChanged();
                } else {
                    showError("Data tidak ditemukan");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Cek> call, @NonNull Throwable t) {
                showError("Gagal mengakses data: " + t.getMessage());
            }
        });
    }

    private void showError(String message) {
        if (getContext() != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
        Log.d("error", message);
    }
}
