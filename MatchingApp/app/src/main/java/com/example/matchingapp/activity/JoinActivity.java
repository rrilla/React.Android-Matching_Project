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

import java.util.concurrent.ExecutionException;

public class JoinActivity extends AppCompatActivity {

    private Gson gson = new Gson();
    private Button btnJoin;
    private EditText etLoginId, etPassword, etUsername, etNickname, etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        btnJoin = findViewById(R.id.join_btn_join);
        etLoginId = findViewById(R.id.join_et_id);
        etPassword = findViewById(R.id.join_et_password);
        etUsername = findViewById(R.id.join_et_username);
        etNickname = findViewById(R.id.join_et_nickname);
        etPhone = findViewById(R.id.join_et_phone);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                RestAPITask task = new RestAPITask("join");
                user.setLoginid(etLoginId.getText().toString());
                user.setPassword(etPassword.getText().toString());
                user.setUsername(etUsername.getText().toString());
                task.execute(gson.toJson(user));
            }
        });
    }
}