package com.example.project_sem4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    ImageView btnBack;
    Button btnSignin;
    EditText ed_username, ed_password;
    String str_username,str_password;
    String url = "http://192.168.0.124:80/api/login";
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = LoginActivity.this.getSharedPreferences("status", Context.MODE_PRIVATE);
        int statuslog = pref.getInt("statuslogin",0);
//        if(statuslog==1) {
//            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
//            intent.putExtra("username", pref.getString("username", ""));
//            startActivity(intent);
//        }

        ed_username = findViewById(R.id.Login_usernameTxt);
        ed_password = findViewById(R.id.Login_passwordTxt);
        btnSignin =  findViewById(R.id.Login_signinTbl);
        btnBack = findViewById(R.id.Login_backTbl);

            btnSignin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ed_username.getText().toString().equals("")){
                        Toast.makeText(LoginActivity.this, "Nama pengguna harus diisi", Toast.LENGTH_SHORT).show();
                    }else if (ed_password.getText().toString().equals("")) {
                        Toast.makeText(LoginActivity.this, "Kata sandi harus diisi", Toast.LENGTH_SHORT).show();
                    } else {

                        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                        progressDialog.setTitle("Mohon Tunggu...");

                        progressDialog.show();
                        str_username = ed_username.getText().toString().trim();
                        str_password = ed_password.getText().toString().trim();


//                    Response
                        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                ed_username.setText("");
                                ed_password.setText("");

                                SharedPreferences.Editor editor = pref.edit();
                                editor.putInt("statuslogin", 1);
                                editor.putString("username",str_username);
                                editor.putString("password",str_password);
                                editor.commit();
                                Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                                finish();
                            }
//                     Error
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        ) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> params = new HashMap<String,String>();
                                params.put("username",str_username);
                                params.put("password",str_password);
                                return params;
                            }
                        };

                        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                        requestQueue.add(request);

                    }

                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                    finish();
                }

            });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
