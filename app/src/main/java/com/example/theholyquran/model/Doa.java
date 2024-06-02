package com.example.theholyquran.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Doa implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("doa")
    @Expose
    private String doa;
    @SerializedName("ayat")
    @Expose
    private String ayat;
    @SerializedName("latin")
    @Expose
    private String latin;
    @SerializedName("artinya")
    @Expose
    private String artinya;

    public Doa(String id, String doa, String ayat, String latin, String artinya) {
        super();
        this.id = id;
        this.doa = doa;
        this.ayat = ayat;
        this.latin = latin;
        this.artinya = artinya;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoa() {
        return doa;
    }

    public void setDoa(String doa) {
        this.doa = doa;
    }

    public String getAyat() {
        return ayat;
    }

    public void setAyat(String ayat) {
        this.ayat = ayat;
    }

    public String getLatin() {
        return latin;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }

    public String getArtinya() {
        return artinya;
    }

    public void setArtinya(String artinya) {
        this.artinya = artinya;
    }

}