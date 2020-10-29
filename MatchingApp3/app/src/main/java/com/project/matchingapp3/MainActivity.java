package com.project.matchingapp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.project.matchingapp3.activity.LoginActivity;
import com.project.matchingapp3.adapter.ViewPagerAdapter;
import com.project.matchingapp3.fragment.Fragment1;
import com.project.matchingapp3.fragment.Fragment2;
import com.project.matchingapp3.fragment.Fragment3;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager pager;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //툴바
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //드로어 레이아웃
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //뷰 페이저
        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3); //미리 로딩해 놓을 아이템의 갯수, 스크롤 넘길시 빠른 조회가능

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Fragment1 fragment1 = new Fragment1();
        adapter.addItem(fragment1);

        Fragment2 fragment2 = new Fragment2();
        adapter.addItem(fragment2);

        Fragment3 fragment3 = new Fragment3();
        adapter.addItem(fragment3);

        pager.setAdapter(adapter);


        //하단 탭 네비
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab1:
                        Toast.makeText(getApplicationContext(), "하단-탭1 선택",Toast.LENGTH_SHORT).show();
                        pager.setCurrentItem(0,true);   // true = 페이지 전환시 스무스
                        toolbar.setTitle("탭 1");
                        return true;

                    case R.id.tab2:
                        Toast.makeText(getApplicationContext(), "하단-탭2 선택",Toast.LENGTH_SHORT).show();
                        pager.setCurrentItem(1,true);
                        toolbar.setTitle("탭 2");
                        return true;

                    case R.id.tab3:
                        Toast.makeText(getApplicationContext(), "하단-탭3 선택",Toast.LENGTH_SHORT).show();
                        pager.setCurrentItem(2,true);
                        toolbar.setTitle("탭 3");
                        return true;
                }
                return false;
            }
        });
    }

    //네비게이션 메뉴의 아이템 선택시 - 인텐트 액티비티 이동, 페이지 이동 구현
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_menu1) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "네비-메뉴1 선택", Toast.LENGTH_LONG).show();
            //onFragmentSelected(0, null);
        } else if (id == R.id.nav_menu2) {
            Toast.makeText(this, "네비-메뉴2 선택", Toast.LENGTH_LONG).show();
            //onFragmentSelected(1, null);
        } else if (id == R.id.nav_menu3) {
            Toast.makeText(this, "네비-메뉴3 선택", Toast.LENGTH_LONG).show();
            //onFragmentSelected(2, null);
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;

        //return false;
    }

    //앱바 메뉴의 아이템 선택시 -
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case R.id.appbar_menu1:
                Toast.makeText(this, "앱바-메뉴1 선택", Toast.LENGTH_SHORT).show();
                break;
            case R.id.appbar_menu2:
                Toast.makeText(this, "앱바-메뉴2 선택", Toast.LENGTH_SHORT).show();
                break;
            case R.id.appbar_menu3:
                Toast.makeText(this, "앱바-메뉴3 선택", Toast.LENGTH_SHORT).show();
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

        return true;
    }

    //얜 뭐임??
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}