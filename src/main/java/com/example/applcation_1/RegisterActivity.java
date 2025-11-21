package com.example.applcation_1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText etFullName, etEmail, etPassword, etMobile, etAge;
    private RadioGroup rgRole;
    private Button btnRegister;
    private TextView tvLoginLink;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etMobile = findViewById(R.id.etMobile);
        etAge = findViewById(R.id.etAge);
        rgRole = findViewById(R.id.rgRole);
        btnRegister = findViewById(R.id.btnRegister);
        tvLoginLink = findViewById(R.id.tvLogin);

        btnRegister.setOnClickListener(v -> {
            String name = etFullName.getText().toString();
            String email = etEmail.getText().toString();
            String pwd = etPassword.getText().toString();
            String mobile = etMobile.getText().toString();
            String age = etAge.getText().toString();
            String role = ((RadioButton)findViewById(rgRole.getCheckedRadioButtonId())).getText().toString();

            if(name.isEmpty() || email.isEmpty() || pwd.isEmpty() || mobile.isEmpty() || age.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            // TODO: Save user to SQLite or API
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        tvLoginLink.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
