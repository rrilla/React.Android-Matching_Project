package com.project.matchingapp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.project.matchingapp3.adapter.ViewPagerAdapter;
import com.project.matchingapp3.fragment.Fragment1;
import com.project.matchingapp3.fragment.Fragment2;
import com.project.matchingapp3.fragment.Fragment3;
import com.project.matchingapp3.fragment.FragmentCallback;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback {

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
                        Toast.makeText(getApplicationContext(), "첫 번째 탭 선택됨",Toast.LENGTH_SHORT).show();
                        pager.setCurrentItem(0,true);   // true = 페이지 전환시 스무스
                        toolbar.setTitle("탭 1");
                        return true;

                    case R.id.tab2:
                        Toast.makeText(getApplicationContext(), "두 번째 탭 선택됨",Toast.LENGTH_SHORT).show();
                        pager.setCurrentItem(1,true);
                        toolbar.setTitle("탭 2");
                        return true;

                    case R.id.tab3:
                        Toast.makeText(getApplicationContext(), "세 번째 탭 선택됨",Toast.LENGTH_SHORT).show();
                        pager.setCurrentItem(2,true);
                        toolbar.setTitle("탭 3");
                        return true;
                }
                return false;
            }
        });
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

    //네비게이션 아이템 선택시 - 인텐트 액티비티 이동, 페이지 이동 구현
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//        int id = item.getItemId();
//
//        if (id == R.id.menu1) {
//            Toast.makeText(this, "첫번째 메뉴 선택됨.", Toast.LENGTH_LONG).show();
//            onFragmentSelected(0, null);
//        } else if (id == R.id.menu2) {
//            Toast.makeText(this, "두번째 메뉴 선택됨.", Toast.LENGTH_LONG).show();
//            onFragmentSelected(1, null);
//        } else if (id == R.id.menu3) {
//            Toast.makeText(this, "세번째 메뉴 선택됨.", Toast.LENGTH_LONG).show();
//            onFragmentSelected(2, null);
//        }
//
//        drawer.closeDrawer(GravityCompat.START);
//
//        return true;

        return false;
    }

    @Override
    public void onFragmentSelected(int position, Bundle bundle) {
//        Fragment curFragment = null;
//
//        if (position == 0) {
//            curFragment = fragment1;
//            toolbar.setTitle("첫번째 화면");
//        } else if (position == 1) {
//            curFragment = fragment2;
//            toolbar.setTitle("두번째 화면");
//        } else if (position == 2) {
//            curFragment = fragment3;
//            toolbar.setTitle("세번째 화면");
//        }
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.container, curFragment).commit();
    }

}