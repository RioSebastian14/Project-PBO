package com.tomoro;

import java.util.ArrayList;

public class Transaksi implements CetakStruk {
    private String idTransaksi;
    private String tanggal;
    private Double totalHarga;
    private ArrayList<ProdukTomoro> listProduk;

    //constructor
    public Transaksi(String idTransaksi, String tanggal) {
        this.idTransaksi = idTransaksi;
        this.tanggal = tanggal;
        this.listProduk = new ArrayList<>();
        this.totalHarga = 0.0;
    }

    //getter dan setter
    public String getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(String idTransaksi) { this.idTransaksi = idTransaksi; }

    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }

    public Double getTotalHarga() { return totalHarga; }

    public ArrayList<ProdukTomoro> getListProduk() { return listProduk; }

    //tambah produk ke daftar pesanan
    public void tambahProduk(ProdukTomoro produk) {
        listProduk.add(produk);
        hitungTotal();
    }

    //hitung total harga seluruh pesanan
    public double hitungTotal() {
        double total = 0;
        for (ProdukTomoro p : listProduk) {
            total += p.hitungTotalHarga();
        }
        this.totalHarga = total;
        return total;
    }

    //cetak nota transaksi gabungan
    @Override
    public void cetakNota() {
        System.out.println("==========================================");
        System.out.println("            " + STORE_NAME);
        System.out.println("==========================================");
        System.out.println("ID Transaksi : " + idTransaksi);
        System.out.println("Tanggal      : " + tanggal);
        System.out.println("------------------------------------------");
        System.out.println("Detail Pesanan:");
        for (ProdukTomoro p : listProduk) {
            System.out.println("- " + p.getNamaProduk() + " (Rp " + p.hitungTotalHarga() + ")");
        }
        System.out.println("------------------------------------------");
        System.out.println("Total Bayar  : Rp " + hitungTotal());
        System.out.println("==========================================");
        System.out.println();
    }
}