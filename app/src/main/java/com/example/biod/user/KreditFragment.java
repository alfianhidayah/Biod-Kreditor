package com.example.biod.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biod.R;
import com.example.biod.adapter.BarangAdapter;
import com.example.biod.api.RetrofitClient;
import com.example.biod.model.model_barang.DataBarang;
import com.example.biod.model.model_barang.ResponseBarang;
import com.example.biod.model.model_kreditor.DataKreditor;
import com.example.biod.model.model_kreditor.ResponseLogin;
import com.example.biod.model.model_kreditor.ResponsePassword;
import com.example.biod.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class KreditFragment extends Fragment {

    private RecyclerView recyclerViewBarang;
    private BarangAdapter adapter;
    private List<DataBarang> barang;
    private ProgressDialog progressDialog;
    private TextView adaBarang;
    public KreditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kredit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DataKreditor data = SharedPrefManager.getInstance(getActivity()).getKreditor();
        String id = data.getId_kreditor();

        adaBarang = view.findViewById(R.id.adaBarang);

        recyclerViewBarang = view.findViewById(R.id.recyclerViewBarang);
        recyclerViewBarang.setLayoutManager(new LinearLayoutManager(getActivity()));

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Mengambil Data Barang Kredit..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        adaBarang.setVisibility(View.GONE);

        Call<ResponseBarang> call = RetrofitClient.getInstance().getApi().getBarang(id);

        call.enqueue(new Callback<ResponseBarang>() {
            @Override
            public void onResponse(Call<ResponseBarang> call, Response<ResponseBarang> response) {
                progressDialog.hide();
                ResponseBarang responseBarang = response.body();
                if (response.body().getStatus()){
                    adaBarang.setVisibility(View.GONE);
                    barang = response.body().getBarang();
                    adapter =  new BarangAdapter(getActivity(), barang);
                    recyclerViewBarang.setAdapter(adapter);
                } else{
                    adaBarang.setVisibility(View.VISIBLE);
                    recyclerViewBarang.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBarang> call, Throwable t) {
                adaBarang.setVisibility(View.VISIBLE);
                recyclerViewBarang.setVisibility(View.GONE);
            }
        });

    }
}
