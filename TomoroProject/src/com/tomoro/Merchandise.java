package com.tomoro;

public class Merchandise extends ProdukTomoro {
    private String varian;

    //constructor
    public Merchandise(String namaProduk, double hargaBase, String varian) {
        super(namaProduk, hargaBase);
        this.varian = varian;
    }

    //getter dan setter
    public String getVarian() { return varian; }
    public void setVarian(String varian) { this.varian = varian; }

    //hitung total harga
    @Override
    public double hitungTotalHarga() {
        return getHargaBase();
    }
}