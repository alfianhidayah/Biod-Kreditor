package com.example.biod.model.model_transaksi;

import java.util.List;

public class ResponseTransaksi {

    public boolean status;
    public List<DataTransaksi> transaksi;

    public ResponseTransaksi(boolean status, List<DataTransaksi> transaksi) {
        this.status = status;
        this.transaksi = transaksi;
    }

    public boolean getStatus() {
        return status;
    }

    public List<DataTransaksi> getTransaksi() {
        return transaksi;
    }
}
