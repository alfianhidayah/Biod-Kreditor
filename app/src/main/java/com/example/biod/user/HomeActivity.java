package com.example.biod.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.biod.R;
import com.example.biod.model.model_barang.DataBarang;
import com.example.biod.model.model_transaksi.DataTransaksi;
import com.example.biod.storage.SharedPrefManager;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.List;

import static com.example.biod.R.*;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    ChipNavigationBar bottom_nav;
    FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_home);

        bottom_nav = findViewById(R.id.bottom_nav);

        if (savedInstanceState==null){
            bottom_nav.setItemSelected(R.id.home,true);
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction()
                    .replace(id.fragment_container, homeFragment)
                    .commit();
        }

        bottom_nav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {

                Fragment fragment = null;
                switch (id){
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.kredit:
                        fragment = new KreditFragment();
                        break;
                    case R.id.bayar:
                        fragment = new BayarFragment();
                        break;
                    case R.id.riwayat:
                        fragment = new RiwayatFragment();
                        break;
                    case R.id.pengaturan:
                        fragment = new PengaturanFragment();
                        break;
                }

                if(fragment!=null){
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }else {
                    Log.e(TAG,"Error in Creating Fragment");
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

//        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//            startActivity(intent);
//        }


    }
}
