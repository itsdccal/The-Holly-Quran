package com.example.theholyquran.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Surah implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("number")
    @Expose
    private String number;

    @SerializedName("englishName")
    @Expose
    private String englishName;

    @SerializedName("englishNameTranslation")
    @Expose
    private String translateName;

    @SerializedName("revelationType")
    @Expose
    private String type;

    @SerializedName("ayahs")
    @Expose
    private List<Ayat> ayatList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getTranslateName() {
        return translateName;
    }

    public void setTranslateName(String translateName) {
        this.translateName = translateName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Ayat> getAyatList() {
        return ayatList;
    }

    public void setAyatList(List<Ayat> ayatList) {
        this.ayatList = ayatList;
    }
}
