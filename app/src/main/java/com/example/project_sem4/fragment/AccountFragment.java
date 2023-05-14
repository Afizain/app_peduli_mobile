package com.example.project_sem4.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.project_sem4.LoginActivity;
import com.example.project_sem4.MainActivity;
import com.example.project_sem4.R;

import java.util.Objects;


public class AccountFragment extends Fragment {

    ImageView Logout;
    SharedPreferences pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        Logout = view.findViewById(R.id.btnLogout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = AccountFragment.this.getActivity().getSharedPreferences("status", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
            }
        });
        return view;
    }
}