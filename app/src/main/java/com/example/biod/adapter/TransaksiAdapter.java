package com.example.biod.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biod.R;
import com.example.biod.model.model_transaksi.DataTransaksi;

import java.util.List;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.TransaksiViewHolder> {

    private Context mCtx;
    private List<DataTransaksi> transaksi;

    //constructor deklarad
    public TransaksiAdapter(Context mCtx, List<DataTransaksi> transaksi) {
        this.mCtx = mCtx;
        this.transaksi = transaksi;
    }

    @NonNull
    @Override
    public TransaksiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_riwayat, parent, false);
        return new TransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransaksiViewHolder holder, int position) {
        DataTransaksi dataTransaksi = transaksi.get(position);

        holder.nama_barang.setText(dataTransaksi.getNama_barang());
        holder.nominal_transaksi.setText("Rp. " + String.format("%,.0f", Double.parseDouble(dataTransaksi.getNominal_transaksi().toString())));
        holder.id_transaksi.setText(dataTransaksi.getId_transaksi());
        holder.tanggal_transaksi.setText(dataTransaksi.getTanggal_transaksi());

    }

    @Override
    public int getItemCount() {
        return transaksi.size();
    }

    public class TransaksiViewHolder extends RecyclerView.ViewHolder {

        private TextView tanggal_transaksi, id_transaksi, nominal_transaksi, nama_barang;

        public TransaksiViewHolder(@NonNull View itemView) {
            super(itemView);

            tanggal_transaksi = itemView.findViewById(R.id.tanggal_transaksi);
            id_transaksi = itemView.findViewById(R.id.id_transaksi);
            nominal_transaksi = itemView.findViewById(R.id.nominal_transaksi);
            nama_barang = itemView.findViewById(R.id.nama_barang);
        }
    }
}
