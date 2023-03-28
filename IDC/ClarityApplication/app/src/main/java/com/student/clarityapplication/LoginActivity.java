package com.student.clarityapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView btn=findViewById(R.id.signuplogin);
        btn.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        Button loginBtn = findViewById(R.id.btnlogin);
        loginBtn.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, dashboard.class)));
    }
}
