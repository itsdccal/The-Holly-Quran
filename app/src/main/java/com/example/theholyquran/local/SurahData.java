package com.example.theholyquran.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.theholyquran.model.Surah;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class SurahData {
    private static final String PREFS_NAME = "quran_prefs";
    private static final String SURAH_KEY_ARABIC = "surah_list_arabic";
    private static final String SURAH_KEY_INDO = "surah_list_indo";

    public static void saveSurahListIndo(Context context, List<Surah> surahList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(surahList);
        editor.putString(SURAH_KEY_INDO, json);
        editor.apply();
    }

    public static List<Surah> getSurahListIndo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(SURAH_KEY_INDO, null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Surah>>() {}.getType();
            return gson.fromJson(json, type);
        } else {
            return null;
        }
    }

    public static void saveSurahListArabic(Context context, List<Surah> surahList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(surahList);
        editor.putString(SURAH_KEY_ARABIC, json);
        editor.apply();
    }

    public static List<Surah> getSurahListArabic(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(SURAH_KEY_ARABIC, null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Surah>>() {}.getType();
            return gson.fromJson(json, type);
        } else {
            return null;
        }
    }
}
