package com.example.biod.model.model_barang;

import java.util.List;

public class ResponseBarang {
    //responsebarang
    private boolean status;
    private List<DataBarang> barang = null;

    public ResponseBarang(boolean status, List<DataBarang> barang) {
        this.status = status;
        this.barang = barang;
    }

    public boolean getStatus() {
        return status;
    }

    public List<DataBarang> getBarang() {
        return barang;
    }
}
