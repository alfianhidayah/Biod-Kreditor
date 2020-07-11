package com.example.biod.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.biod.R;
import com.example.biod.api.RetrofitClient;
import com.example.biod.model.model_barang.DataBarang;
import com.example.biod.model.model_kreditor.ResponseLogin;
import com.example.biod.storage.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText editTextId;
    private TextInputEditText editTextPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextId = (TextInputEditText) findViewById(R.id.IDBiod);
        editTextPassword = (TextInputEditText) findViewById(R.id.passwordBiod);

        findViewById(R.id.btnMasuk).setOnClickListener(this);
        findViewById(R.id.btnLupaPassword).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnMasuk:
                userLogin();
                break;
            case R.id.btnLupaPassword:
                lupaPassword();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }

    private void lupaPassword()
    {
        Intent intent = new Intent(this, LupaPassword.class);
        startActivity(intent);
    }


    private void userLogin() {
        String id = editTextId.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //validasi
        if (id.isEmpty()){
            editTextId.setError("Masukkan ID !");
            editTextId.requestFocus();
        }
        if(password.isEmpty()){
            editTextPassword.setError("Masukkan Password !");
            editTextPassword.requestFocus();
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<ResponseLogin> call = RetrofitClient
                .getInstance().getApi().userLogin(id, password);

        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                progressDialog.hide();
                ResponseLogin responseLogin = response.body();

                if (response.body().getStatus()){
                    Toast.makeText(MainActivity.this, responseLogin.getMessage(), Toast.LENGTH_LONG). show();
                    //save data kreditor
                    SharedPrefManager.getInstance(MainActivity.this)
                            .saveKreditor(responseLogin.getData());
//                    SharedPrefManager.getInstance(MainActivity.this)
//                            .saveBarang((DataBarang) responseLogin.getBarang());

                    Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, responseLogin.getMessage(), Toast.LENGTH_LONG). show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

            }
        });
    }
}
