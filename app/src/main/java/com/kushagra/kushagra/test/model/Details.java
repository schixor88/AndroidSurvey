package com.kushagra.kushagra.test.model;

public class Details {

    private int Id;
    private String latitude,longitude,altitude;
    private String pradesh,jilla,nagarpalika,ward,basti,tole,sadak;
    private String jati,vasa,dharma;
    private String ghardhaniname,ghardhanisex,ghardhaniphone;

    public Details() {
    }

    public Details(int id,
                   String latitude,
                   String longitude,
                   String altitude,
                   String pradesh,
                   String jilla,
                   String nagarpalika,
                   String ward,
                   String basti,
                   String tole,
                   String sadak,
                   String jati,
                   String vasa,
                   String dharma,
                   String ghardhaniname,
                   String ghardhanisex,
                   String ghardhaniphone
                )
    {
        Id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.pradesh = pradesh;
        this.jilla = jilla;
        this.nagarpalika = nagarpalika;
        this.ward = ward;
        this.basti = basti;
        this.tole = tole;
        this.sadak = sadak;
        this.jati = jati;
        this.vasa = vasa;
        this.dharma = dharma;
        this.ghardhaniname = ghardhaniname;
        this.ghardhanisex = ghardhanisex;
        this.ghardhaniphone = ghardhaniphone;
    }

    public String getJati() {
        return jati;
    }

    public void setJati(String jati) {
        this.jati = jati;
    }

    public String getVasa() {
        return vasa;
    }

    public void setVasa(String vasa) {
        this.vasa = vasa;
    }

    public String getDharma() {
        return dharma;
    }

    public void setDharma(String dharma) {
        this.dharma = dharma;
    }

    public String getPradesh() {
        return pradesh;
    }

    public void setPradesh(String pradesh) {
        this.pradesh = pradesh;
    }

    public String getJilla() {
        return jilla;
    }

    public void setJilla(String jilla) {
        this.jilla = jilla;
    }

    public String getNagarpalika() {
        return nagarpalika;
    }

    public void setNagarpalika(String nagarpalika) {
        this.nagarpalika = nagarpalika;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getBasti() {
        return basti;
    }

    public void setBasti(String basti) {
        this.basti = basti;
    }

    public String getTole() {
        return tole;
    }

    public void setTole(String tole) {
        this.tole = tole;
    }

    public String getSadak() {
        return sadak;
    }

    public void setSadak(String sadak) {
        this.sadak = sadak;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getGhardhaniname() {
        return ghardhaniname;
    }

    public void setGhardhaniname(String ghardhaniname) {
        this.ghardhaniname = ghardhaniname;
    }

    public String getGhardhanisex() {
        return ghardhanisex;
    }

    public void setGhardhanisex(String ghardhanisex) {
        this.ghardhanisex = ghardhanisex;
    }

    public String getGhardhaniphone() {
        return ghardhaniphone;
    }

    public void setGhardhaniphone(String ghardhaniphone) {
        this.ghardhaniphone = ghardhaniphone;
    }
}
