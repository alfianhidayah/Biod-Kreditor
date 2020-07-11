package com.example.biod.user;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.biod.R;
import com.example.biod.adapter.BarangAdapter;
import com.example.biod.adapter.TransaksiAdapter;
import com.example.biod.api.RetrofitClient;
import com.example.biod.model.model_barang.ResponseBarang;
import com.example.biod.model.model_kreditor.DataKreditor;
import com.example.biod.model.model_transaksi.DataTransaksi;
import com.example.biod.model.model_transaksi.ResponseTransaksi;
import com.example.biod.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RiwayatFragment extends Fragment {

    private RecyclerView recyclerViewTransaksi;
    private TransaksiAdapter adapter;
    private List<DataTransaksi> transaksi;
    private ProgressDialog progressDialog;

    public RiwayatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_riwayat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //ambil data id yang sudah disimpan
        DataKreditor data = SharedPrefManager.getInstance(getActivity()).getKreditor();
        String id = data.getId_kreditor();

        recyclerViewTransaksi = view.findViewById(R.id.recyclerViewTransaksi);
        recyclerViewTransaksi.setLayoutManager(new LinearLayoutManager(getActivity()));

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Mengambil Data Transaksi..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<ResponseTransaksi> call = RetrofitClient.getInstance().getApi().getTransaksi(id);

        call.enqueue(new Callback<ResponseTransaksi>() {
            @Override
            public void onResponse(Call<ResponseTransaksi> call, Response<ResponseTransaksi> response) {
                progressDialog.hide();
                if (response.body().getStatus() == true){
                    transaksi = response.body().getTransaksi();
                    adapter =  new TransaksiAdapter(getActivity(), transaksi);
                    recyclerViewTransaksi.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseTransaksi> call, Throwable t) {

            }
        });


    }
}
