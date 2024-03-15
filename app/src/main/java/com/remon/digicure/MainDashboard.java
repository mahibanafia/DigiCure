package com.remon.digicure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainDashboard extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        bottomNavigationView = findViewById(R.id.bottoms_Nev);

        getSupportFragmentManager().beginTransaction().replace(R.id.Const_Main_Dash, new HomeFragment()).commit();

        bottomNavigationView.setSelectedItemId(R.id.nev_home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){

                    case R.id.nev_home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.nev_doctor:
                        fragment = new DoctorFragment();
                        break;

                    case R.id.nev_mdicine:
                        fragment = new MedicineFragment();
                        break;

                    case R.id.nev_diagnostic:
                        fragment = new DiagnosticFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.Const_Main_Dash, fragment).commit();

                return true;
            }
        });
    }
}