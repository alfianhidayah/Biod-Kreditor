package com.example.biod.user;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.biod.R;
import com.example.biod.model.model_kreditor.DataKreditor;
import com.example.biod.storage.SharedPrefManager;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;


/**
 * A simple {@link Fragment} subclass.
 */
public class BayarFragment extends Fragment {

    private ImageView gambarQR;
    private ProgressDialog progressDialog;

    public BayarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bayar, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Deklarasi
        gambarQR = view.findViewById(R.id.gambarQR);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Mengambil Data..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        //GENERATE QR CODE
        DataKreditor data = SharedPrefManager.getInstance(getActivity()).getKreditor();
        String data_id = data.getId_kreditor();

        QRGEncoder qrgEncoder = new QRGEncoder(data_id, null, QRGContents.Type.TEXT, 500);
        qrgEncoder.setColorBlack(Color.rgb(0, 170, 225));
        try {
            Bitmap qrBits = qrgEncoder.getBitmap();
            // Setting Bitmap to ImageView
            gambarQR.setImageBitmap(qrBits);
            progressDialog.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
