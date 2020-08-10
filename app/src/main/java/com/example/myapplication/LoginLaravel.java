package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginLaravel extends AppCompatActivity {

    EditText et_mail, et_pass;
    Button btn_reg, btn_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_laravel);

        et_mail = findViewById(R.id.L_email);
        et_pass = findViewById(R.id.L_password);
        btn_log = findViewById(R.id.btn_log);
        btn_reg = findViewById(R.id.btn_reg);

    }


}