package com.remon.digicure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeFragment extends Fragment {



    public HomeFragment() {

    }

   LinearLayout Medilinear;

    LinearLayout  Diaglinear;

    TextView seeAll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Medilinear = view.findViewById(R.id.medicine_top_id);
        Diaglinear = view.findViewById(R.id.diagnostic_top_id);
        seeAll = view.findViewById(R.id.see_all_Text);

        Diaglinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment diagnos = new DiagnosticFragment();
                FragmentTransaction dg = getActivity().getSupportFragmentManager().beginTransaction();
                dg.replace(R.id.Const_Main_Dash,diagnos).commit();
            }
        });

        seeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment doctors = new DoctorFragment();
                FragmentTransaction doc = getActivity().getSupportFragmentManager().beginTransaction();
                doc.replace(R.id.Const_Main_Dash,doctors).commit();

            }
        });

        Medilinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment medicine = new MedicineFragment();
                FragmentTransaction md = getActivity().getSupportFragmentManager().beginTransaction();
                md.replace(R.id.Const_Main_Dash,medicine).commit();
            }
        });

        return view;
    }
}