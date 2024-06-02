package com.example.theholyquran.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.theholyquran.model.Doa;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class DoaData {

    private static final String APP_PREFS_NAME = ".tmp.data.doa.cfg";

    private static SharedPreferences userData;
    private static final String DOA_DATA = "DOA_DATA";

    public static void cleanData(Context context) {
        userData = context.getSharedPreferences(context.getPackageName() + APP_PREFS_NAME, Context.MODE_PRIVATE);
        userData.edit().clear().apply();
    }

    public static void saveData(Context context, String jsonData) {
        userData = context.getSharedPreferences(context.getPackageName() + APP_PREFS_NAME, Context.MODE_PRIVATE);
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gson = builder.create();
        ArrayList<Doa> doaList = gson.fromJson(jsonData, new TypeToken<ArrayList<Doa>>() {
        }.getType());
        userData.edit().putString(DOA_DATA, gson.toJson(doaList, new TypeToken<ArrayList<Doa>>() {
        }.getType())).apply();
    }

    public static ArrayList<Doa> getData(Context context) {
        userData = context.getSharedPreferences(context.getPackageName() + APP_PREFS_NAME, Context.MODE_PRIVATE);
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gson = builder.create();
        ArrayList<Doa> itemDoa = gson.fromJson(userData.getString(DOA_DATA, ""), new TypeToken<ArrayList<Doa>>() {
        }.getType());
        if (itemDoa == null){
            itemDoa = new ArrayList<Doa>();
        }
        return itemDoa;
    }


}
