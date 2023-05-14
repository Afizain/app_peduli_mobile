package com.example.project_sem4.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.project_sem4.R;
import com.example.project_sem4.fragment.AccountFragment;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    Button btn_buatlaporan, btn_statuslaporan, btn_informasikegiatan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btn_buatlaporan = view.findViewById(R.id.Homefragment_buatLaporanTbl);
        btn_statuslaporan = view.findViewById(R.id.Homefragment_statusLaporanTbl);
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

        //        ImageSlider
        ArrayList<SlideModel> imageList = new ArrayList<>(); //create image list

        imageList.add(new SlideModel("https://www.bfi.co.id/Blog/Blog%20New/Tempat%20wisata/Tempat%20Wisata%20di%20Indonesia.jpg", "Wisata Inonesia", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://gdb.voanews.com/40213EFE-56CA-41BD-9666-63F599DC8B77_w650_r1_s.jpg", "Tsunami Aceh 2004", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://www.dpr.go.id/images_pemberitaan/images/November%202021/WhatsApp%20Image%202021-11-01%20at%2011.10.51(2).jpeg", "Ketua DPR RI", ScaleTypes.CENTER_CROP));

        ImageSlider imageSlider = view.findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);



        return view;




    }


}
