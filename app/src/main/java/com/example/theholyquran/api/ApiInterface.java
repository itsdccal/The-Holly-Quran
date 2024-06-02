package com.example.theholyquran.api;

import com.example.theholyquran.model.Cek;
import com.example.theholyquran.model.Data;
import com.example.theholyquran.model.Doa;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("quran/id.indonesian")
    Call<Data> getData();

    @GET("quran/quran-uthmani")
    Call<Cek> getDataArabic();

    @GET("quran/{lang}")
    Call<Cek> getCek(@Path("lang") String lang);

    @GET("surah/1/id.indonesian")
    Call<Cek> getSurahTranslation(/*@Path("id")int id*/);

    // id.indonesian

    @GET("/api")
    Call<ResponseBody> getAllDoa();

}

