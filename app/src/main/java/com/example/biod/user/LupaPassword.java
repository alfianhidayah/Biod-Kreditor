package com.example.biod.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.biod.R;
import com.example.biod.api.RetrofitClient;
import com.example.biod.model.model_kreditor.ResponseLogin;
import com.example.biod.model.model_kreditor.ResponsePassword;
import com.example.biod.storage.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LupaPassword extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText noHpLupaPassword,emailLupaPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        noHpLupaPassword = (TextInputEditText) findViewById(R.id.noHpLupaPassword);
        emailLupaPassword = (TextInputEditText) findViewById(R.id.emailLupaPassword);

        findViewById(R.id.btnBackLogin).setOnClickListener(this);
        findViewById(R.id.btnKirimEmail).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBackLogin:
                backLogin();
                break;
            case R.id.btnKirimEmail:
                lupaPassword();
                break;
        }
    }

    private void backLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void lupaPassword()
    {
        String nohp = noHpLupaPassword.getText().toString().trim();
        String email = emailLupaPassword.getText().toString().trim();

        //validasi
        if (nohp.isEmpty()){
            noHpLupaPassword.setError("Masukkan No HP !");
            noHpLupaPassword.requestFocus();
        }
        if(email.isEmpty()){
            emailLupaPassword.setError("Masukkan Email !");
            noHpLupaPassword.requestFocus();
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mengirim Email .. Sabar cuy");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<ResponsePassword> call = RetrofitClient.getInstance()
                .getApi().lupaPassword(nohp, email);

        call.enqueue(new Callback<ResponsePassword>() {
            @Override
            public void onResponse(Call<ResponsePassword> call, Response<ResponsePassword> response) {
                progressDialog.hide();
                ResponsePassword responsePassword = response.body();


                if (response.body().getStatus()){
                    Toast.makeText(LupaPassword.this, responsePassword.getMessage(), Toast.LENGTH_LONG). show();

                    Intent intent = new Intent(LupaPassword.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                } else {
                    Toast.makeText(LupaPassword.this, responsePassword.getMessage(), Toast.LENGTH_LONG). show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePassword> call, Throwable t) {

            }
        });
    }




}
