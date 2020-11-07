package com.project.matchingapp3.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.matchingapp3.MainActivity;
import com.project.matchingapp3.R;
import com.project.matchingapp3.TeamActivity;
import com.project.matchingapp3.UserActivity;
import com.project.matchingapp3.adapter.BattleUsersAdapter;
import com.project.matchingapp3.adapter.OnBattleUClickListener;
import com.project.matchingapp3.model.Team;
import com.project.matchingapp3.model.TeamInfo;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.task.RestAPITask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class BattleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    DrawerLayout drawer;
    RecyclerView recyclerView;
    BattleUsersAdapter adapter;

    User loginUser;
    String jwtToken;

    HashMap checkUsers = new HashMap();
    TeamInfo teamInfo = new TeamInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        Intent intent = getIntent();
        jwtToken = intent.getStringExtra("jwtToken");
        loginUser = (User)intent.getSerializableExtra("loginUser");

        recyclerView = findViewById(R.id.recyclerView);
        //리사이클러뷰에 설정할 레이아웃 매니저 - 방향세로로 설정함.
        GridLayoutManager layoutManager = new GridLayoutManager(this,  2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BattleUsersAdapter();
        Log.e("test-Battle액티비티", "유저 데이터:" + loginUser);
        adapter.setItems((ArrayList) loginUser.getTeams().getUsers());
        Log.e("test-Battle액티비티", "유저 어댑터 관리수:" + adapter.getItemCount());

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnBattleUClickListener() {
            @Override
            public void onItemClick(BattleUsersAdapter.ViewHolder holder, View view, int position) {
                CheckBox cb = view.findViewById(R.id.battle_item_ck_choice);
                if(cb.isChecked()){
                    Log.e("test-Battle액티비티", adapter.getItem(position)+"체크됨");
                    checkUsers.put(position, adapter.getItem(position));
                }else{
                    Log.e("test-Battle액티비티", "체크해제됨");
                    checkUsers.remove(position);
                }
                Log.e("test-Battle액티비티", "저장데이터 : " + checkUsers.size());
                Log.e("test-Battle액티비티", "저장데이터 : " + checkUsers.get(position));
                Log.e("test-Battle액티비티", "저장데이터 : " + checkUsers.toString());
            }
        });
        Button btnChoice = findViewById(R.id.battle_btn_choice);
        btnChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUsers.size() != 10){
                    Snackbar.make(view, "선택 실패. 11명만 선택 가능합니다. 선택 수 = '" + checkUsers.size() + "'명", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                    teamInfo.setTeam(loginUser.getTeams());
                    teamInfo.setUser2((User)checkUsers.get(0));
                    teamInfo.setUser3((User)checkUsers.get(1));
                    teamInfo.setUser4((User)checkUsers.get(2));
                    teamInfo.setUser5((User)checkUsers.get(3));
                    teamInfo.setUser6((User)checkUsers.get(4));
                    teamInfo.setUser7((User)checkUsers.get(5));
                    teamInfo.setUser8((User)checkUsers.get(6));
                    teamInfo.setUser9((User)checkUsers.get(7));
                    teamInfo.setUser10((User)checkUsers.get(8));
                    teamInfo.setUser11((User)checkUsers.get(9));
                    //teamInfo.setUser11((User)checkUsers.get(10));
                String[] result = new String[1];
                RestAPITask task = new RestAPITask(jwtToken);
                Gson gson = new Gson();
                try {
                    result = task.execute("user/teamInfo", gson.toJson(teamInfo)).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e("test-Battle액티비티", "받은데이터 : " + result[0]);
                //List<Team> tList = gson.fromJson(result[0], new TypeToken<List<Team>>(){}.getType());
            }
        });


        //툴바
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //드로어 레이아웃
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


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
        if(loginUser.getImage() != null) {
            ImageView navImage = header.findViewById(R.id.navHeader_iv_image);
            Glide.with(this).load(loginUser.getUrlImage()).into(navImage);
        }
        //텍스트
        TextView navName = header.findViewById(R.id.navHeader_tv_username);
        TextView navTName = header.findViewById(R.id.navHeader_tv_tName);
        navName.setText(loginUser.getUsername()+"("+ loginUser.getNickname()+")");
        if(loginUser.getTeams() != null){
            navTName.setText(loginUser.getTeams().getName());
            navigationView.getMenu().getItem(1).setTitle("My Team");
        }


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
                        Intent intent2 = new Intent(getApplicationContext(), TeamActivity.class);
                        intent2.putExtra("jwtToken", jwtToken);
                        intent2.putExtra("loginUser", loginUser);
                        startActivity(intent2);
                        return true;
                    case R.id.tab3:
                        Intent intent3 = new Intent(getApplicationContext(), UserActivity.class);
                        intent3.putExtra("jwtToken", jwtToken);
                        intent3.putExtra("loginUser", loginUser);
                        startActivity(intent3);
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
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("jwtToken", jwtToken);
            startActivity(intent);
        } else if (id == R.id.nav_menu2) {
            if(loginUser.getTeams() != null){
                Intent intent = new Intent(getApplicationContext(), TeamDetailActivity.class);
                intent.putExtra("jwtToken", jwtToken);
                intent.putExtra("loginUser", loginUser);
                intent.putExtra("selectTeam", loginUser.getTeams());
                startActivity(intent);
            }else{
                Intent intent = new Intent(getApplicationContext(), TeamCreateActivity.class);
                intent.putExtra("jwtToken", jwtToken);
                intent.putExtra("loginUser", loginUser);
                startActivity(intent);
            }
        } else if (id == R.id.nav_menu3) {
            Toast.makeText(this, "네비-메뉴3 선택", Toast.LENGTH_LONG).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //앱바 메뉴의 아이템 선택시 -
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case R.id.appbar_search:
                Intent intent = new Intent(getApplicationContext(), PartyListActivity.class);
                intent.putExtra("jwtToken", jwtToken);
                intent.putExtra("loginUser", loginUser);
                startActivity(intent);
                break;
            case R.id.appbar_info:
                Intent intent2 = new Intent(getApplicationContext(), MyPageActivity.class);
                intent2.putExtra("jwtToken", jwtToken);
                intent2.putExtra("loginUser", loginUser);
                startActivity(intent2);
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
        toolbar.setTitle("매칭 신청");
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