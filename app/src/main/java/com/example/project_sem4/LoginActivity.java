package com.example.project_sem4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    ImageView btnBack;
    AppCompatButton btnSignin;
    EditText ed_username, ed_password;
    String str_username,str_password;
    String Login = constant.Home+"login";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    CheckBox showPw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("login", MODE_PRIVATE);
        editor =pref.edit();

        if (pref.getString("masuk", "").equals("true")){
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
        finishAffinity();
        }


        ed_username = findViewById(R.id.Login_usernameTxt);
        ed_password = findViewById(R.id.Login_passwordTxt);
        btnSignin =  findViewById(R.id.Login_signinTbl);
        btnBack = findViewById(R.id.Login_backTbl);
        showPw = findViewById(R.id.Login_showPassword);

//        Show Password checkbox
        showPw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    // show password
                    ed_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // hide password
                    ed_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

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

                        StringRequest request = new StringRequest(Request.Method.POST, Login, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                try {

                                    JSONObject json = new JSONObject(response);
                                    String success = json.getString("success");
                                    String message = json.getString("message");

//                                    Login Berhasil

                                    if (success.equals("true")){
                                        editor.putString("masuk","true");
                                        JSONObject user = json.getJSONObject("data");
                                        editor.putString("token",user.getString("token"));
                                        editor.putString("username",user.getString("username"));
                                        editor.apply();
                                        editor.commit();
                                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                    }else {
//                                        Login Gagal
                                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                                    }
                                }catch (JSONException e){
                                    e.printStackTrace();

                                }
                                ed_username.setText("");
                                ed_password.setText("");
                            }

                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        } ){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {

                                Map<String,String> map = new HashMap<>();
                                map.put("username",str_username);
                                map.put("password",str_password);
                                return map;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                        requestQueue.add(request);

                    }
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
