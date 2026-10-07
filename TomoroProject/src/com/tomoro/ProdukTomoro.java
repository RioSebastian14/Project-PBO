package com.tomoro;

public abstract class ProdukTomoro {
    private String namaProduk;
    private double hargaBase;

    public ProdukTomoro(String namaProduk, double hargaBase) {
        this.namaProduk = namaProduk;
        this.hargaBase = hargaBase;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public double getHargaBase() {
        return hargaBase;
    }

    public void setHargaBase(double hargaBase) {
        this.hargaBase = hargaBase;
    }

    //abstract method wajib override di subclass
    public abstract double hitungTotalHarga();
}