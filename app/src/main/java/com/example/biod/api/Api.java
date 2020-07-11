package com.example.biod.api;

import com.example.biod.model.model_barang.ResponseBarang;
import com.example.biod.model.model_kreditor.ResponseLogin;
import com.example.biod.model.model_kreditor.ResponsePassword;
import com.example.biod.model.model_transaksi.ResponseTransaksi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
@FormUrlEncoded
    @POST("auth")
    Call<ResponseLogin> userLogin(
        @Field("id_kreditor") String id,
        @Field("password") String password);

@FormUrlEncoded
    @PUT("kreditor")
    Call<ResponseLogin> updateKreditor(
        @Field("id_kreditor") String id,
        @Field("nama_kreditor") String nama,
        @Field("nomor_hp") String noHP,
        @Field("nomor_ktp") String noKTP,
        @Field("alamat") String alamat);

@FormUrlEncoded
    @PUT("kreditor")
    Call<ResponsePassword> updatePassword(
            //Field itu inputan di API bukan Database
        @Field("id_kreditor") String id,
        @Field("password") String password,
        @Field("password2") String password2,
        @Field("password_lama") String password_lama
        );

    @FormUrlEncoded
    @PUT("kreditor")
    Call<ResponsePassword> lupaPassword(
            //Field itu inputan di API bukan Database
            @Field("nomor_hp") String nohp,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("barang")
    Call<ResponseBarang> getBarang(
            //Field itu inputan di API bukan Database
            @Field("id_kreditor") String id
    );

    @FormUrlEncoded
    @POST("transaksi")
    Call<ResponseTransaksi> getTransaksi(
            @Field("id_kreditor") String id
    );

}
