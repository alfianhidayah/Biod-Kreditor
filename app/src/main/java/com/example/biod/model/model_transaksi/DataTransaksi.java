package com.example.biod.model.model_transaksi;

public class DataTransaksi {
    String tanggal_transaksi, id_transaksi, nominal_transaksi, nama_barang;

    public DataTransaksi(String tanggal_transaksi, String id_transaksi, String nominal_transaksi, String nama_barang) {
        this.tanggal_transaksi = tanggal_transaksi;
        this.id_transaksi = id_transaksi;
        this.nominal_transaksi = nominal_transaksi;
        this.nama_barang = nama_barang;
    }

    public String getTanggal_transaksi() {
        return tanggal_transaksi;
    }

    public String getId_transaksi() {
        return id_transaksi;
    }

    public String getNominal_transaksi() {
        return nominal_transaksi;
    }

    public String getNama_barang() {
        return nama_barang;
    }
}
