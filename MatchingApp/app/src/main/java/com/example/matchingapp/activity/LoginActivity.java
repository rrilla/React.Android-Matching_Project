package com.example.matchingapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.matchingapp.R;
import com.example.matchingapp.model.User;
import com.example.matchingapp.task.RestAPITask;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    private Gson gson = new Gson();
    private EditText etLoginId, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginId = findViewById(R.id.login_et_id);
        etPassword = findViewById(R.id.login_et_password);
        btnLogin = findViewById(R.id.login_btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setLoginid(etLoginId.getText().toString());
                user.setPassword(etPassword.getText().toString());
                RestAPITask task = new RestAPITask("login");
                task.execute(gson.toJson(user));

            }
        });
    }
}