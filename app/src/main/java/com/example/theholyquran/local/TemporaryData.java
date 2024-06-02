package com.example.theholyquran.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.theholyquran.model.Ayat;
import com.example.theholyquran.model.Doa;
import com.example.theholyquran.model.Surah;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class TemporaryData {

    private static SharedPreferences mTemporaryPrefences;
    private static final String PREFS_NAME = "last_surah_quran_read";

    private static String tmp_jsonlist = "tmp_jsonlist";
    private static String tmp_jsonlistIndo = "tmp_jsonlistIndo";
    private static String tmp_jsonTitle = "tmp_jsonTitle";

    public static void saveLastSurah(Context context, String jsonlist, String jsonlistIndo, String jsonTitle) {
        mTemporaryPrefences = context.getSharedPreferences(context.getPackageName() + PREFS_NAME, Context.MODE_PRIVATE);
        mTemporaryPrefences.edit().putString(tmp_jsonlist, jsonlist).apply();
        mTemporaryPrefences.edit().putString(tmp_jsonlistIndo, jsonlistIndo).apply();
        mTemporaryPrefences.edit().putString(tmp_jsonTitle, jsonTitle).apply();
    }

    public static String getJsonList(Context context) {
        mTemporaryPrefences = context.getSharedPreferences(context.getPackageName() + PREFS_NAME, Context.MODE_PRIVATE);
        return mTemporaryPrefences.getString(tmp_jsonlist, "");
    }

    public static String getJsonlistIndo(Context context) {
        mTemporaryPrefences = context.getSharedPreferences(context.getPackageName() + PREFS_NAME, Context.MODE_PRIVATE);
        return mTemporaryPrefences.getString(tmp_jsonlistIndo, "");
    }

    public static String getJsonTitle(Context context) {
        mTemporaryPrefences = context.getSharedPreferences(context.getPackageName() + PREFS_NAME, Context.MODE_PRIVATE);
        return mTemporaryPrefences.getString(tmp_jsonTitle, "");
    }



}
