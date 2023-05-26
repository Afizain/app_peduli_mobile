package com.example.project_sem4.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project_sem4.R;
import com.example.project_sem4.constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;


public class BuatAduanFragment extends Fragment {
    AppCompatButton btn_submit;
    EditText txt_keluhan;
    String getDataaduan = constant.Home+"dataAduans";


    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_buat_aduan, container, false);
        // Required empty public constructor
        btn_submit = view.findViewById(R.id.BuatAduan_submitBtn);
        txt_keluhan = view.findViewById(R.id.BuatAduan_keluhanTxt);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("Mohon Tunggu...");
                // Inflate the layout for this fragment

                if (txt_keluhan.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Kolom keluhan harap diisi", Toast.LENGTH_SHORT).show();
                }else {
                    StringRequest request = new StringRequest(Request.Method.POST, getDataaduan, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject json = new JSONObject(response);
                                String message = json.getString("message");
                                if (message.equals("success")){
                                    Toast.makeText(getContext(), "Pengaduan telah diterima, terima kasih telah memakai layanan kami", Toast.LENGTH_SHORT).show();
                                    FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                                    fr.replace(R.id.fragment_container, new HomeFragment());
                                    fr.commit();
                                }else {
                                    Toast.makeText(getContext(), "Pengaduan gagal dibuat", Toast.LENGTH_SHORT).show();
                                }
                            }catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String,String>();
                            params.put("statement",txt_keluhan.getText().toString().trim());
                            params.put("id_user","01");
                            params.put("bukti_foto","blablabla.jpg");
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    requestQueue.add(request);

                }
            }
        });

            return view;
    }






}