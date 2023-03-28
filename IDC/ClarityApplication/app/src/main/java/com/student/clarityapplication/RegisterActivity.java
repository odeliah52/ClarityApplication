package com.student.clarityapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView btn=findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this,LoginActivity.class)));

        Button signupButton = findViewById(R.id.btnRegister);
        signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, dashboard.class);
            startActivity(intent);
            finish();
        });
    }
}
