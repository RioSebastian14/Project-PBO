package com.tomoro;

public class Food extends ProdukTomoro {
    private boolean isWarmed;

    //constructor
    public Food(String namaProduk, double hargaBase, boolean isWarmed) {
        super(namaProduk, hargaBase);
        this.isWarmed = isWarmed;
    }

    //getter dan setter
    public boolean getWarmed() { return isWarmed; }
    public boolean isWarmed() { return isWarmed; }
    public void setWarmed(boolean isWarmed) { this.isWarmed = isWarmed; }

    //hitung total harga
    @Override
    public double hitungTotalHarga() {
        return getHargaBase();
    }
}