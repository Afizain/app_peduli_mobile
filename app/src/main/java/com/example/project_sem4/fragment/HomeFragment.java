package com.example.project_sem4.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.project_sem4.R;
import com.example.project_sem4.fragment.AccountFragment;


public class HomeFragment extends Fragment {
    Button btn_buatlaporan, btn_statuslaporan, btn_informasikegiatan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btn_buatlaporan = view.findViewById(R.id.btnBuatLaporan);
        btn_statuslaporan = view.findViewById(R.id.btnStatusLaporan);
        btn_informasikegiatan = view.findViewById(R.id.btnInformationEvent);

        btn_buatlaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new BuatAduanFragment());
                fr.commit();
//                Intent intent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent);
            }
        });
        btn_statuslaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new StatusAduanFragment());
                fr.commit();
            }
        });
        btn_informasikegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new InformasiKegiatanFragment());
                fr.commit();
            }
        });

        return view;

    }


}
