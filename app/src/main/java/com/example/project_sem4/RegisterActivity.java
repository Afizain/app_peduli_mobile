package com.example.project_sem4;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegisterActivity extends AppCompatActivity {
    ImageView btnBack;
    Button btnSignup;
    EditText ed_username, ed_password,ed_email;
    String str_username,str_password,str_email,str_status;
    String url = "http://192.168.0.124:80/api/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSignup = findViewById(R.id.Register_signupTbl);
        btnBack = findViewById(R.id.Register_backTbl);
        ed_username = findViewById(R.id.Register_usernameTxt);
        ed_password = findViewById(R.id.Register_passwordTxt);
        ed_email = findViewById(R.id.Register_emailAddressTxt);


        btnSignup.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setTitle("Mohon Tunggu...");
                if(ed_username.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "Nama pengguna harus diisi", Toast.LENGTH_SHORT).show();
                }else if (ed_password.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Kata sandi harus diisi", Toast.LENGTH_SHORT).show();
                } else if (ed_email.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Alamat email harus diisi", Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.show();
                    str_username = ed_username.getText().toString().trim();
                    str_email = ed_email.getText().toString().trim();
                    str_password = ed_password.getText().toString().trim();
                    str_status = "warga";

//                    Response
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            ed_username.setText("");
                            ed_email.setText("");
                            ed_password.setText("");
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            Toast.makeText(RegisterActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                        }
//                     Error
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String,String>();

                            params.put("username",str_username);
                            params.put("password",str_password);
                            params.put("email",str_email);
                            params.put("status",str_status);
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                    requestQueue.add(request);

                }

            }

        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            }

        });
    }
}
//    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//        finish();