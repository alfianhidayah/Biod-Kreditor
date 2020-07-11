package com.example.biod.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biod.R;
import com.example.biod.model.model_barang.DataBarang;

import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.BarangViewHolder> {

    //deklarasi
    private Context mCtx;
    private List<DataBarang> barang;

    //constructor properti mctx dan list
    public BarangAdapter(Context mCtx, List<DataBarang> barang) {
        this.mCtx = mCtx;
        this.barang = barang;
    }

    @NonNull
    @Override
    public BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_kredit, parent, false);
        return new BarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangViewHolder holder, int position) {
        DataBarang dataBarang = barang.get(position);

        holder.nama_barang.setText(dataBarang.getNama_barang());
        holder.angsuran.setText(dataBarang.getAngsuran());
        holder.nominal_angsuran.setText("Rp. "+ String.format("%,.0f", Double.parseDouble(dataBarang.getNominal_angsuran().toString())));
        holder.uang_muka.setText("Rp. "+ String.format("%,.0f", Double.parseDouble(dataBarang.getUang_muka().toString())));
        holder.kredit_total.setText("Rp. "+ String.format("%,.0f", Double.parseDouble(dataBarang.getKredit_total().toString())));
        holder.kredit_masuk.setText("Rp. "+ String.format("%,.0f", Double.parseDouble(dataBarang.getKredit_masuk().toString())));
        holder.sisa_kredit.setText("Rp. "+ String.format("%,.0f", Double.parseDouble(dataBarang.getSisa_kredit().toString())));
        holder.status.setText(dataBarang.getStatus());

        if (dataBarang.getSisa_kredit().equals("0")){
            holder.bg_status.setBackgroundColor(Color.parseColor("#00CF6E"));
        }
    }

    @Override
    public int getItemCount() {
        return barang.size();
    }

    class BarangViewHolder extends RecyclerView.ViewHolder{

        TextView nama_barang, angsuran, nominal_angsuran, uang_muka, kredit_total, kredit_masuk, sisa_kredit,status;
        LinearLayout bg_status;

        public BarangViewHolder(@NonNull View itemView) {
            super(itemView);

            nama_barang = itemView.findViewById(R.id.namaBarang);
            angsuran = itemView.findViewById(R.id.jenisAngsuran);
            nominal_angsuran = itemView.findViewById(R.id.angsuran);
            uang_muka = itemView.findViewById(R.id.uangMuka);
            kredit_total = itemView.findViewById(R.id.harusDibayar);
            kredit_masuk = itemView.findViewById(R.id.pembayaranMasuk);
            sisa_kredit = itemView.findViewById(R.id.sisaPembayaran);
            status = itemView.findViewById(R.id.statusKredit);
            bg_status = itemView.findViewById(R.id.bg_status);
        }
    }
}
