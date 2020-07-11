package com.example.biod.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.biod.model.model_barang.DataBarang;
import com.example.biod.model.model_kreditor.DataKreditor;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "biod_pref";
    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx){
        this.mCtx = mCtx;
    }

    public static synchronized SharedPrefManager getInstance(Context mCtx){
        if (mInstance == null){
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }

    public void saveKreditor(DataKreditor kreditor ){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("id_kreditor", kreditor.getId_kreditor());
        editor.putString("nama_kreditor", kreditor.getNama_kreditor());
        editor.putString("pekerjaan", kreditor.getPekerjaan());
        editor.putString("alamat", kreditor.getAlamat());
        editor.putString("nomor_hp", kreditor.getNomor_hp());
        editor.putString("nomor_ktp", kreditor.getNomor_ktp());
        editor.putString("password", kreditor.getPassword());

        editor.putString("desa_id", kreditor.getDesa_id());

        editor.apply();
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("id_kreditor", "-1") != "-1";
    }

    public DataKreditor getKreditor(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new DataKreditor(
          sharedPreferences.getString("id_kreditor", "-1"),
          sharedPreferences.getString("nama_kreditor", null),
          sharedPreferences.getString("pekerjaan", null),
          sharedPreferences.getString("alamat", null),
          sharedPreferences.getString("nomor_hp", null),
          sharedPreferences.getString("nomor_ktp", null),
          sharedPreferences.getString("password", null),
          sharedPreferences.getString("desa_id", null)
        );
    }

    public void clear(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
