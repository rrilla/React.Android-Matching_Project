package com.project.matchingapp3.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.project.matchingapp3.MainActivity;
import com.project.matchingapp3.R;
import com.project.matchingapp3.model.dto.MainDataDto;
import com.project.matchingapp3.task.RestAPITask;

import java.util.concurrent.ExecutionException;

public class MyPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout drawer;
    BottomNavigationView bottomNavigationView;

    MainDataDto mainDataDto;
    String jwtToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        //툴바
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        jwtToken = intent.getStringExtra("jwtToken");
        mainDataDto = (MainDataDto)intent.getSerializableExtra("mainDataDto");

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


        //드로어 레이아웃
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //하단 탭 네비
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab1:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("jwtToken", jwtToken);
                        startActivity(intent);
                        return true;
                    case R.id.tab2:
                        //팀 액티비티
                        return true;
                    case R.id.tab3:
                        //선수 액티비티
                        return true;
                }
                return false;
            }
        });


        //네비게이션 뷰
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //네비뷰의 로그아웃 버튼
        View header = navigationView.getHeaderView(0);
        Button btnLogout = header.findViewById(R.id.navHeader_btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("autoLogin", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.remove("id");
                editor.remove("pw");
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        //네비뷰 헤더의 사용자 정보
        //이미지
//        ImageView navImage = header.findViewById(R.id.navHeader_iv_image);
//        navImage.setImageURI(URI.);
        //텍스트
        TextView navName = header.findViewById(R.id.navHeader_tv_username);
        TextView navtName = header.findViewById(R.id.navHeader_tv_tName);
        navName.setText(mainDataDto.getUsername()+"("+mainDataDto.getNickname()+")");
        navtName.setText(mainDataDto.getT_name());
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

    //네비게이션 메뉴의 아이템 선택시 - 인텐트 액티비티 이동, 페이지 이동 구현
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_menu1) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("jwtToken", jwtToken);
            startActivity(intent);
        } else if (id == R.id.nav_menu2) {
            Toast.makeText(this, "네비-메뉴2 선택", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_menu3) {
            Toast.makeText(this, "네비-메뉴3 선택", Toast.LENGTH_LONG).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //뒤로가기 때 호출 - 네비창 닫기
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}