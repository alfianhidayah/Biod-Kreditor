package com.example.biod.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.biod.R;
import com.example.biod.storage.SharedPrefManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    //Deklarasi
    private TextView namaKreditor, idKreditor, alamatKreditor, nomorHpKreditor, pekerjaanKreditor,nomorKTPKreditor;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        namaKreditor = view.findViewById(R.id.namaKreditor);
        idKreditor = view.findViewById(R.id.idKreditor);
        alamatKreditor = view.findViewById(R.id.alamatKreditor);
        nomorHpKreditor = view.findViewById(R.id.nomorHpKreditor);
        pekerjaanKreditor = view.findViewById(R.id.pekerjaanKreditor);
        nomorKTPKreditor = view.findViewById(R.id.nomorKTPKreditor);

        namaKreditor.setText(SharedPrefManager.getInstance(getActivity()).getKreditor().getNama_kreditor());
        idKreditor.setText(SharedPrefManager.getInstance(getActivity()).getKreditor().getId_kreditor());
        alamatKreditor.setText(SharedPrefManager.getInstance(getActivity()).getKreditor().getAlamat());
        nomorHpKreditor.setText(SharedPrefManager.getInstance(getActivity()).getKreditor().getNomor_hp());
        pekerjaanKreditor.setText(SharedPrefManager.getInstance(getActivity()).getKreditor().getPekerjaan());
        nomorKTPKreditor.setText(SharedPrefManager.getInstance(getActivity()).getKreditor().getNomor_ktp());
    }
}
