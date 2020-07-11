package com.example.biod.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.biod.R;
import com.example.biod.api.RetrofitClient;
import com.example.biod.model.model_kreditor.DataKreditor;
import com.example.biod.model.model_kreditor.ResponseLogin;
import com.example.biod.model.model_kreditor.ResponsePassword;
import com.example.biod.storage.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PengaturanFragment extends Fragment implements View.OnClickListener {

    private TextInputEditText ubahNama, ubahAlamat, ubahNomorHP, ubahNomorKTP;
    private TextInputEditText passwordLama, passwordBaru, passwordBaru2;
    private ProgressDialog progressDialog;

    public PengaturanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pengaturan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //inisialisasi
        ubahNama = view.findViewById(R.id.ubahNama);
        ubahAlamat = view.findViewById(R.id.ubahAlamat);
        ubahNomorHP = view.findViewById(R.id.ubahNomorHP);
        ubahNomorKTP = view.findViewById(R.id.ubahNomorKTP);

        passwordLama = view.findViewById(R.id.passwordLama);
        passwordBaru = view.findViewById(R.id.passwordBaru);
        passwordBaru2 = view.findViewById(R.id.passwordBaru2);

        view.findViewById(R.id.btnUbahBiodata).setOnClickListener(this);
        view.findViewById(R.id.btnUbahPassword).setOnClickListener(this);
        view.findViewById(R.id.btnLogout).setOnClickListener(this);

        //ambil data dan masukan ke ubah biodata agar kreditor tidak susah ngetik ulang
        ubahNama.setText(SharedPrefManager.getInstance(getActivity()).getKreditor().getNama_kreditor());
        ubahAlamat.setText(SharedPrefManager.getInstance(getActivity()).getKreditor().getAlamat());
        ubahNomorHP.setText(SharedPrefManager.getInstance(getActivity()).getKreditor().getNomor_hp());
        ubahNomorKTP.setText(SharedPrefManager.getInstance(getActivity()).getKreditor().getNomor_ktp());

    }

    private void logout(){
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        SharedPrefManager.getInstance(getActivity()).clear();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        progressDialog.hide();
    }

    private void updateBiodata()
    {
        DataKreditor data = SharedPrefManager.getInstance(getActivity()).getKreditor();

        String nama = ubahNama.getText().toString().trim();
        String noHP = ubahNomorHP.getText().toString().trim();
        String noKTP = ubahNomorKTP.getText().toString().trim();
        String alamat = ubahAlamat.getText().toString().trim();
        String id = data.getId_kreditor();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Memperbarui data..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<ResponseLogin> call = RetrofitClient.getInstance()
                .getApi().updateKreditor(
                        id,
                        nama,
                        noHP,
                        noKTP,
                        alamat
                );
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                progressDialog.hide();
                ResponseLogin responseLogin = response.body();
                if (response.body().getStatus()){
                    Toast.makeText(getActivity(), responseLogin.getMessage(), Toast.LENGTH_LONG). show();

                    //save data kreditor
                    SharedPrefManager.getInstance(getActivity())
                            .saveKreditor(responseLogin.getData());

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                } else {
                    Toast.makeText(getActivity(), responseLogin.getMessage(), Toast.LENGTH_LONG). show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

            }
        });
    }

    private void updatePassword()
    {
        DataKreditor data = SharedPrefManager.getInstance(getActivity()).getKreditor();
        String id = data.getId_kreditor();
        String password = passwordBaru.getText().toString().trim();
        String password2 = passwordBaru2.getText().toString().trim();
        String password_lama = passwordLama.getText().toString().trim();

        //validasi
        //validasi
        if (password.isEmpty()){
            passwordBaru.setError("Masukkan Kata Sandi Baru");
            passwordBaru.requestFocus();
        }
        if(password2.isEmpty()){
            passwordBaru2.setError("Masukkan Kata Sandi Konfirmasi");
            passwordBaru2.requestFocus();
        }
        if(password_lama.isEmpty()){
            passwordLama.setError("Masukkan Kata Sandi Lama");
            passwordLama.requestFocus();
        }

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Memperbarui kata sandi..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<ResponsePassword> call = RetrofitClient.getInstance().getApi()
                .updatePassword(
                        id,
                        password,
                        password2,
                        password_lama
                );

        call.enqueue(new Callback<ResponsePassword>() {
            @Override
            public void onResponse(Call<ResponsePassword> call, Response<ResponsePassword> response) {
                progressDialog.hide();
                ResponsePassword responsePassword = response.body();
                if (response.body().getStatus()){
                    Toast.makeText(getActivity(), responsePassword.getMessage(), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else{
                    Toast.makeText(getActivity(), responsePassword.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePassword> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnUbahBiodata:
                updateBiodata();
                break;
            case R.id.btnUbahPassword:
                updatePassword();
                break;
            case R.id.btnLogout:
                logout();
                break;
        }
    }
}
