package com.tomoro;

public class Beverage extends ProdukTomoro {
    private String size;
    private String milkType;
    private String topping;

    //constructor
    public Beverage(String namaProduk, double hargaBase, String size, String milkType, String topping) {
        super(namaProduk, hargaBase);
        this.size = size;
        this.milkType = milkType;
        this.topping = topping;
    }

    //getter dan setter
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getMilkType() { return milkType; }
    public void setMilkType(String milkType) { this.milkType = milkType; }

    public String getTopping() { return topping; }
    public void setTopping(String topping) { this.topping = topping; }

    //hitung total harga
    @Override
    public double hitungTotalHarga() {
        double total = getHargaBase();
        if (size.equalsIgnoreCase("Large")) {
            total += 4000;
        }
        if (!topping.equalsIgnoreCase("No Topping") && !topping.equalsIgnoreCase("-")) {
            total += 5000;
        }
        return total;
    }
}