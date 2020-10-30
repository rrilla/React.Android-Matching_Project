package com.project.matchingapp3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.project.matchingapp3.R;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.task.RestAPITask;

import java.util.concurrent.ExecutionException;

public class JoinActivity extends AppCompatActivity {

    EditText etId, etPw, etPw2, etName, etNickname, etPhone, etEmail, etLocation;
    Button btnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        etId = findViewById(R.id.join_et_id);
        etPw = findViewById(R.id.join_et_pw);
        etPw2 = findViewById(R.id.join_et_pw2);
        etName = findViewById(R.id.join_et_name);
        etNickname = findViewById(R.id.join_et_nickname);
        etPhone = findViewById(R.id.join_et_phone);
        etEmail = findViewById(R.id.join_et_email);
        etLocation = findViewById(R.id.join_et_location);
        btnJoin = findViewById(R.id.join_btn_join);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                String[] result = new String[1];
                RestAPITask task = new RestAPITask("join");
                User user = new User();

                user.setLoginid(etId.getText().toString());
                user.setPassword(etPw.getText().toString());
                user.setUsername(etName.getText().toString());
                user.setNickname(etNickname.getText().toString());
                user.setPhone(etPhone.getText().toString());
                user.setEmail(etEmail.getText().toString());
                user.setLocation(etLocation.getText().toString());

                try {
                    result = task.execute(gson.toJson(user)).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("test",result[0]);
            }
        });

    }
}