package com.example.biod.model.model_barang;

public class DataBarang {
    private String nama_barang, angsuran, nominal_angsuran, uang_muka, kredit_total, kredit_masuk, sisa_kredit, status;

    public DataBarang(String nama_barang, String angsuran, String nominal_angsuran, String uang_muka, String kredit_total, String kredit_masuk, String sisa_kredit, String status) {
        this.nama_barang = nama_barang;
        this.angsuran = angsuran;
        this.nominal_angsuran = nominal_angsuran;
        this.uang_muka = uang_muka;
        this.kredit_total = kredit_total;
        this.kredit_masuk = kredit_masuk;
        this.sisa_kredit = sisa_kredit;
        this.status = status;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public String getAngsuran() {
        return angsuran;
    }

    public String getNominal_angsuran() {
        return nominal_angsuran;
    }

    public String getUang_muka() {
        return uang_muka;
    }

    public String getKredit_total() {
        return kredit_total;
    }

    public String getKredit_masuk() {
        return kredit_masuk;
    }

    public String getSisa_kredit() {
        return sisa_kredit;
    }

    public String getStatus() {
        return status;
    }
}
