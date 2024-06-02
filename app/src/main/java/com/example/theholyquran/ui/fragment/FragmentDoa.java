package com.example.theholyquran.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.theholyquran.adapter.ListDoaAdapter;
import com.example.theholyquran.databinding.FragmentDoaBinding;
import com.example.theholyquran.local.DoaData;
import com.example.theholyquran.route.Constant;
import com.example.theholyquran.api.ApiInterface;

import java.io.IOException;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentDoa extends Fragment {

    private FragmentDoaBinding binding;
    private ApiInterface apiInterface;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDoaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initializeLogic();
        initializeClick();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initializeLogic() {
        setupList();
        loadDoa();
    }

    private void initializeClick() {
        binding.swiperefreshlayout.setOnRefreshListener(() -> {
            binding.swiperefreshlayout.setRefreshing(false);
            loadDoa();
        });
    }

    private void loadDoa() {
        binding.swiperefreshlayout.setRefreshing(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_ROOT_URL_DOA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.getAllDoa();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                binding.swiperefreshlayout.setRefreshing(false);
                if (response.isSuccessful()) {
                    String responseBody = null;
                    try {
                        responseBody = response.body().string();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (responseBody != null) {
                        DoaData.saveData(getContext(), responseBody);
                        setupList();
                    } else {

                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                binding.swiperefreshlayout.setRefreshing(false);
                Toast.makeText(getContext(), Log.getStackTraceString(t), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupList() {
        binding.recyclerviewDoa.setAdapter(new ListDoaAdapter(getContext(), DoaData.getData(getContext())));
        binding.recyclerviewDoa.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}