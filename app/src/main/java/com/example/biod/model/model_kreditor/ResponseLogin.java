package com.example.biod.model.model_kreditor;

import com.example.biod.model.model_barang.DataBarang;

import java.util.List;

public class ResponseLogin {
    private Boolean status;
    private String message;
    private DataKreditor data = null;

    public ResponseLogin(Boolean status, String message, DataKreditor data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public DataKreditor getData() {
        return data;
    }
}
