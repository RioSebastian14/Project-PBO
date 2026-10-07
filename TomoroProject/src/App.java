import com.tomoro.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static final String FILE_PATH = "src/produk.txt";
    private static ArrayList<Transaksi> listTransaksi = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //baca data awal dari file txt
        bacaDataFile();

        int pilihan = 0;
        do {
            System.out.println("\nSISTEM MANAJEMEN RR Cafe UNTAR");
            System.out.println("1. Tampilkan Semua Struk Transaksi");
            System.out.println("2. Buat Transaksi Baru (Multi-Order)");
            System.out.println("3. Simpan Data ke File txt");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");

            try {
                pilihan = Integer.parseInt(scanner.nextLine());
                switch (pilihan) {
                    case 1:
                        tampilkanSemuaTransaksi();
                        break;
                    case 2:
                        tambahTransaksi(scanner);
                        break;
                    case 3:
                        simpanDataFile();
                        break;
                    case 4:
                        System.out.println("Program selesai.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid, pilih 1-4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: input pilihan harus angka!");
            }
        } while (pilihan != 4);

        scanner.close();
    }

    //baca file txt
    private static void bacaDataFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("File produk.txt belum ada");
                return;
            }

            Scanner reader = new Scanner(file);
            listTransaksi.clear();
            Transaksi txAktif = null;

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");
                String tipe = data[0].trim();

                if (tipe.equalsIgnoreCase("TRANSAKSI")) {
                    txAktif = new Transaksi(data[1].trim(), data[2].trim());
                    listTransaksi.add(txAktif);
                } else if (txAktif != null) {
                    if (tipe.equalsIgnoreCase("BEVERAGE")) {
                        txAktif.tambahProduk(new Beverage(data[1].trim(), Double.parseDouble(data[2].trim()), data[3].trim(), data[4].trim(), data[5].trim()));
                    } else if (tipe.equalsIgnoreCase("FOOD")) {
                        txAktif.tambahProduk(new Food(data[1].trim(), Double.parseDouble(data[2].trim()), Boolean.parseBoolean(data[3].trim())));
                    } else if (tipe.equalsIgnoreCase("MERCHANDISE")) {
                        txAktif.tambahProduk(new Merchandise(data[1].trim(), Double.parseDouble(data[2].trim()), data[3].trim()));
                    }
                }
            }
            reader.close();
            System.out.println("Berhasil membaca " + listTransaksi.size() + " transaksi dari " + FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }
    }

    //tampilkan semua transaksi
    private static void tampilkanSemuaTransaksi() {
        if (listTransaksi.isEmpty()) {
            System.out.println("Belum ada data transaksi.");
            return;
        }

        System.out.println("\nDAFTAR STRUK TRANSAKSI");
        for (Transaksi t : listTransaksi) {
            t.cetakNota();
        }
    }

    //tambah transaksi baru dengan multi order
    private static void tambahTransaksi(Scanner scanner) {
        System.out.print("Masukkan ID Transaksi: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Tanggal (DD/MM/YYYY): ");
        String tgl = scanner.nextLine();

        Transaksi tx = new Transaksi(id, tgl);

        boolean lanjut = true;
        while (lanjut) {
            System.out.println("\nPilih Jenis Produk:");
            System.out.println("1. Beverage");
            System.out.println("2. Food");
            System.out.println("3. Merchandise");
            System.out.print("Pilihan (1-3): ");

            try {
                int subPilihan = Integer.parseInt(scanner.nextLine());
                switch (subPilihan) {
                    case 1:
                        System.out.print("Nama Minuman: ");
                        String namaB = scanner.nextLine();
                        System.out.print("Harga Base: ");
                        double hargaB = Double.parseDouble(scanner.nextLine());
                        System.out.print("Size (Medium/Large): ");
                        String size = scanner.nextLine();
                        System.out.print("Jenis Susu: ");
                        String milk = scanner.nextLine();
                        System.out.print("Topping: ");
                        String topping = scanner.nextLine();

                        tx.tambahProduk(new Beverage(namaB, hargaB, size, milk, topping));
                        System.out.println("Beverage berhasil ditambahkan ke transaksi!");
                        break;

                    case 2:
                        System.out.print("Nama Makanan: ");
                        String namaF = scanner.nextLine();
                        System.out.print("Harga Base: ");
                        double hargaF = Double.parseDouble(scanner.nextLine());
                        System.out.print("Dihangatkan? (true/false): ");
                        boolean isWarmed = Boolean.parseBoolean(scanner.nextLine());

                        tx.tambahProduk(new Food(namaF, hargaF, isWarmed));
                        System.out.println("Food berhasil ditambahkan ke transaksi!");
                        break;

                    case 3:
                        System.out.print("Nama Merchandise: ");
                        String namaM = scanner.nextLine();
                        System.out.print("Harga Base: ");
                        double hargaM = Double.parseDouble(scanner.nextLine());
                        System.out.print("Varian: ");
                        String varian = scanner.nextLine();

                        tx.tambahProduk(new Merchandise(namaM, hargaM, varian));
                        System.out.println("Merchandise berhasil ditambahkan ke transaksi!");
                        break;

                    default:
                        System.out.println("Pilihan jenis produk tidak valid!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Gagal: input harga harus angka");
            }

            System.out.print("Tambah item lain ke transaksi ini? (y/n): ");
            String jawab = scanner.nextLine();
            if (!jawab.equalsIgnoreCase("y")) {
                lanjut = false;
            }
        }

        listTransaksi.add(tx);
        System.out.println("Transaksi berhasil disimpan!");
    }

    //simpan data balik ke file txt
    private static void simpanDataFile() {
        try {
            File file = new File(FILE_PATH);

            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            FileWriter writer = new FileWriter(file);
            for (Transaksi t : listTransaksi) {
                writer.write("TRANSAKSI," + t.getIdTransaksi() + "," + t.getTanggal() + "\n");
                for (ProdukTomoro p : t.getListProduk()) {
                    if (p instanceof Beverage) {
                        Beverage b = (Beverage) p;
                        writer.write("BEVERAGE," + b.getNamaProduk() + "," + b.getHargaBase() + "," + b.getSize() + "," + b.getMilkType() + "," + b.getTopping() + "\n");
                    } else if (p instanceof Food) {
                        Food f = (Food) p;
                        writer.write("FOOD," + f.getNamaProduk() + "," + f.getHargaBase() + "," + f.getWarmed() + "\n");
                    } else if (p instanceof Merchandise) {
                        Merchandise m = (Merchandise) p;
                        writer.write("MERCHANDISE," + m.getNamaProduk() + "," + m.getHargaBase() + "," + m.getVarian() + "\n");
                    }
                }
            }
            writer.close();
            System.out.println("Data berhasil disimpan ke " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file: " + e.getMessage());
        }
    }
}