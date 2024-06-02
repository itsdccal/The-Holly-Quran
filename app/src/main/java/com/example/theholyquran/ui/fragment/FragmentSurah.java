package com.example.theholyquran.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.theholyquran.LauncherSurahActivity;
import com.example.theholyquran.adapter.SurahAdapter;
import com.example.theholyquran.api.ApiClient;
import com.example.theholyquran.api.ApiInterface;
import com.example.theholyquran.databinding.FragmentSurahBinding;
import com.example.theholyquran.local.SurahData;
import com.example.theholyquran.local.TemporaryData;
import com.example.theholyquran.model.Cek;
import com.example.theholyquran.model.Data;
import com.example.theholyquran.model.Surah;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSurah extends Fragment {

    private FragmentSurahBinding binding;
    private SurahAdapter surahAdapter;
    private static final String ARABIC = "quran-uthmani";
    private static final String INDO = "id.indonesian";

    private List<Surah> surahsArabic = new ArrayList<>();
    private List<Surah> surahsIndo = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSurahBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initializeLogic();
        initializeClick();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (TemporaryData.getJsonTitle(getContext()).equals("")) {
            binding.textLastSurah.setVisibility(View.GONE);
        } else {
            binding.textLastSurah.setVisibility(View.VISIBLE);
            binding.textLastSurah.setText("Surat terakhir dibaca :\n" + TemporaryData.getJsonTitle(getContext()));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    private void initializeLogic() {

        binding.rvSurah.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvSurah.setHasFixedSize(true);

        if (SurahData.getSurahListArabic(getContext()) != null) {
            surahsArabic.clear();
            surahsArabic.addAll(SurahData.getSurahListArabic(getContext()));
            surahsIndo.clear();
            surahsIndo.addAll(SurahData.getSurahListIndo(getContext()));
            surahAdapter = new SurahAdapter(getContext(), surahsArabic, surahsIndo);
            binding.rvSurah.setAdapter(surahAdapter);
        } else {
            surahAdapter = new SurahAdapter(getContext(), surahsArabic, surahsIndo);
            binding.rvSurah.setAdapter(surahAdapter);
            ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
            Call<Cek> callArabic = apiInterface.getCek(ARABIC);
            Call<Cek> callIndo = apiInterface.getCek(INDO);
            getDataListArabic(callArabic);
            getDataTarjim(callIndo);
        }


    }

    private void initializeClick() {
        binding.swiperefreshlayout.setOnRefreshListener(() -> {
            binding.swiperefreshlayout.setRefreshing(false);
            initializeLogic();
        });

        binding.textLastSurah.setOnClickListener( v -> {
            Intent intent = new Intent(getContext(), LauncherSurahActivity.class);
            intent.putExtra("jsonlist", TemporaryData.getJsonList(getContext()));
            intent.putExtra("jsonlistIndo", TemporaryData.getJsonlistIndo(getContext()));
            intent.putExtra("jsonTitle", TemporaryData.getJsonTitle(getContext()));
            getContext().startActivity(intent);
        });

    }

    private void getDataTarjim(Call<Cek> callIndo) {
        callIndo.enqueue(new Callback<Cek>() {
            @Override
            public void onResponse(@NonNull Call<Cek> call, @NonNull Response<Cek> response) {
                binding.swiperefreshlayout.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    Data data = response.body().getData();
                    SurahData.saveSurahListIndo(getContext(), data.getSurahs());
                    surahAdapter.notifyDataSetChanged();
                } else {
                    showError("Data tidak ditemukan");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Cek> call, @NonNull Throwable t) {
                showError("Gagal mengakses data: " + t.getMessage());
                binding.swiperefreshlayout.setRefreshing(false);

            }
        });
    }

    private void getDataListArabic(Call<Cek> call) {
        call.enqueue(new Callback<Cek>() {
            @Override
            public void onResponse(@NonNull Call<Cek> call, @NonNull Response<Cek> response) {
                binding.swiperefreshlayout.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    Data data = response.body().getData();
                    SurahData.saveSurahListArabic(getContext(), data.getSurahs());
                    surahAdapter.notifyDataSetChanged();
                } else {
                    showError("Data tidak ditemukan");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Cek> call, @NonNull Throwable t) {
                showError("Gagal mengakses data: " + t.getMessage());
                binding.swiperefreshlayout.setRefreshing(false);

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