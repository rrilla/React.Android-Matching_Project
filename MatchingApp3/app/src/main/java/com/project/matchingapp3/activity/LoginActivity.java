package com.project.matchingapp3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.matchingapp3.MainActivity;
import com.project.matchingapp3.R;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.task.RestAPITask;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    Button btnJoin, btnLogin;
    TextView tvResult;
    EditText etId, etPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvResult = findViewById(R.id.login_tv_result);
        etId = findViewById(R.id.login_et_id);
        etPw = findViewById(R.id.login_et_pw);
        btnJoin = findViewById(R.id.login_btn_join);
        btnLogin = findViewById(R.id.login_btn_login);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                User user = new User();
                String result = "";
                user.setLoginid(etId.getText().toString());
                user.setPassword(etPw.getText().toString());
                RestAPITask task = new RestAPITask("login");

                try {
                    result = task.execute(gson.toJson(user)).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "서버통신오류", Toast.LENGTH_SHORT).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (result.equals("ok")){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);  //데이터 담고 가야됨.
                }else if(result.equals("아이디x")){
                    tvResult.setText("아디 ㄴㄴ");
                    Toast.makeText(LoginActivity.this, "아디 ㄴㄴ", Toast.LENGTH_SHORT).show();
                }else{
                    tvResult.setText("비번 ㄴㄴ");
                    Toast.makeText(LoginActivity.this, "비번 ㄴㄴ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}