package com.project.matchingapp3.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.project.matchingapp3.R;
import com.project.matchingapp3.task.RestAPITask;

import java.util.concurrent.ExecutionException;

public class MyPageActivity extends AppCompatActivity {

    Toolbar toolbar;
    String jwtToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        //툴바
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("My Info");

        Intent intent = getIntent();
        jwtToken = intent.getStringExtra("jwtToken");

        String[] result = new String[1];
        RestAPITask task = new RestAPITask(jwtToken);

        try {
            result = task.execute("user/myPage").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("test-info 데이터받음",result[0]);
    }

    //앱바 메뉴의 아이템 선택시 -
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case R.id.appbar_search:
                Toast.makeText(this, "앱바-메뉴1 검색 선택", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //앱바 메뉴 인플레이션
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        toolbar.setTitle("마이페이지");
        return true;
    }
}