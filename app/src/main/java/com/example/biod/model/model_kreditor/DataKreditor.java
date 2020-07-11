package com.example.biod.model.model_kreditor;

public class DataKreditor {
    private String id_kreditor, nama_kreditor, pekerjaan, alamat, nomor_hp, nomor_ktp, password,desa_id;

    public DataKreditor(String id_kreditor, String nama_kreditor, String pekerjaan, String alamat, String nomor_hp, String nomor_ktp, String password, String desa_id) {
        this.id_kreditor = id_kreditor;
        this.nama_kreditor = nama_kreditor;
        this.pekerjaan = pekerjaan;
        this.alamat = alamat;
        this.nomor_hp = nomor_hp;
        this.nomor_ktp = nomor_ktp;
        this.password = password;
        this.desa_id = desa_id;
    }


    public String getId_kreditor() {
        return id_kreditor;
    }

    public String getNama_kreditor() {
        return nama_kreditor;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNomor_hp() {
        return nomor_hp;
    }

    public String getNomor_ktp() {
        return nomor_ktp;
    }

    public String getPassword() {
        return password;
    }

    public String getDesa_id() {
        return desa_id;
    }

}
