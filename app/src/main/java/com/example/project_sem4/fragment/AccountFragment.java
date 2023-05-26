package com.example.project_sem4.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.project_sem4.LoginActivity;
import com.example.project_sem4.MainActivity;
import com.example.project_sem4.R;
import com.example.project_sem4.RegisterActivity;
import com.example.project_sem4.constant;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class AccountFragment extends Fragment {

    private ImageView Logout,selectPhoto;
    private ShapeableImageView viewPhoto;

    String id_user;

    SharedPreferences pref;

    SharedPreferences.Editor editor;
    EditText txtNamaLengkap, txtnoTelp, txtNamaPerum, txtRt, txtRw, txtDesa,txtKecamatan,txtKota,txtProvinsi;
    AppCompatButton buttonCreate, buttonUpdate;
    String getProfile = constant.Home+"user";
    String getDatadiri = constant.Home+"dataDiris";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

//       Pengenalan data

        pref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        editor =pref.edit();
        buttonCreate = view.findViewById(R.id.profile_createData);
        buttonUpdate = view.findViewById(R.id.profile_updateData);
        txtNamaLengkap = view.findViewById(R.id.profile_nama);
        txtnoTelp = view.findViewById(R.id.profile_noTelp);
        txtNamaPerum = view.findViewById(R.id.profile_nama_perum);
        txtRt = view.findViewById(R.id.profile_Rt);
        txtRw = view.findViewById(R.id.profile_Rw);
        txtDesa = view.findViewById(R.id.profile_desa);
        txtKecamatan = view.findViewById(R.id.profile_kecamatan);
        txtKota = view.findViewById(R.id.profile_kota);
        txtProvinsi = view.findViewById(R.id.profile_provinsi);
        selectPhoto = view.findViewById(R.id.profile_pilihFoto);
        viewPhoto = view.findViewById(R.id.profile_viewFoto);
        Logout = view.findViewById(R.id.btnLogout);

//        Operation

//        Memanggil data user (akun) menggunakan Token
        getProfile();



//        Insert Data
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setTitle("Mohon Tunggu...");

                if (txtNamaLengkap.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Kolom nama harap diisi", Toast.LENGTH_SHORT).show();
                }else if (txtnoTelp.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Kolom no. Telpon harap diisi", Toast.LENGTH_SHORT).show();
                }else if (txtNamaPerum.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Kolom Perumahan harap diisi", Toast.LENGTH_SHORT).show();
                }else if (txtRt.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Kolom Rt harap diisi", Toast.LENGTH_SHORT).show();
                }else if (txtRw.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Kolom Rw harap diisi", Toast.LENGTH_SHORT).show();
                }else if (txtDesa.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Kolom desa harap diisi", Toast.LENGTH_SHORT).show();
                }else if (txtKecamatan.getText().toString().equals("")){
                    Toast.makeText(getContext(), " Kolom kecamatan harap diisi", Toast.LENGTH_SHORT).show();
                }else if (txtKota.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Kolom kota harap diisi", Toast.LENGTH_SHORT).show();
                }else if (txtProvinsi.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Kolom provinsi harap diisi", Toast.LENGTH_SHORT).show();
                }else {
                    StringRequest request = new StringRequest(Request.Method.POST, getDatadiri, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject json = new JSONObject(response);
                                String message = json.getString("message");
                            if (message.equals("success")){
                                Toast.makeText(getContext(), "Data telah dibuat", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getContext(), "Data gagal dibuat", Toast.LENGTH_SHORT).show();
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

                            params.put("nama_lengkap",txtNamaLengkap.getText().toString().trim());
                            params.put("nama_perumahan",txtNamaPerum.getText().toString().trim());
                            params.put("no_telp",txtnoTelp.getText().toString().trim());
                            params.put("rt",txtRt.getText().toString().trim());
                            params.put("rw",txtRw.getText().toString().trim());
                            params.put("desa",txtDesa.getText().toString().trim());
                            params.put("kecamatan",txtKecamatan.getText().toString().trim());
                            params.put("kota",txtKota.getText().toString().trim());
                            params.put("provinsi",txtProvinsi.getText().toString().trim());
                            params.put("id_user",id_user.trim());
                            params.put("photo","blablabla.jpg");
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                    requestQueue.add(request);

                    txtNamaLengkap.setKeyListener(null);
                    txtnoTelp.setKeyListener(null);
                    txtNamaPerum.setKeyListener(null);
                    txtRt.setKeyListener(null);
                    txtRw.setKeyListener(null);
                    txtDesa.setKeyListener(null);
                    txtKecamatan.setKeyListener(null);
                    txtKota.setKeyListener(null);
                    txtProvinsi.setKeyListener(null);

                }
            }
        });


//       Logout

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pref = requireContext().getSharedPreferences("login", Context.MODE_PRIVATE);
                editor =pref.edit();
                editor.clear();
                editor.putString("masuk", "false");
                editor.commit();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });



//       Mengambil foto
        selectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });







        return view;
    }
public void getProfile(){

        StringRequest request = new StringRequest(Request.Method.GET, getProfile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object =new JSONObject(response);
                    String success = object.getString("success");

                    if (success.equals("true")){
                        JSONObject user = object.getJSONObject("data");
//                        txtNamaLengkap.setText(user.getString("id_user").trim());
                        id_user = user.getString("id_user");

                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = pref.getString("token","");
                HashMap<String,String> map= new HashMap<>();
                map.put("Authorization", "Bearer"+" "+token);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);

}

}
