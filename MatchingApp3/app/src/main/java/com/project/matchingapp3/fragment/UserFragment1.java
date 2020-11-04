package com.project.matchingapp3.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.matchingapp3.R;
import com.project.matchingapp3.adapter.OnTeamItemClickListener;
import com.project.matchingapp3.adapter.OnUserItemClickListener;
import com.project.matchingapp3.adapter.TeamListAdapter;
import com.project.matchingapp3.adapter.UserListAdapter;
import com.project.matchingapp3.model.Team;
import com.project.matchingapp3.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserFragment1 extends Fragment {

    private ArrayList<User> users = new ArrayList<User>();
    private RecyclerView recyclerView;
    UserListAdapter adapter;

    public UserFragment1(List<User> users){
        this.users = (ArrayList<User>) users;
        //this.users.addAll(users);
        Log.e("test-유저리스트프래그받음?", users.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.user_fragment1, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);

        //리사이클러뷰에 설정할 레이아웃 매니저 - 방향세로로 설정함.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new UserListAdapter();
        adapter.setItems(users);
        Log.e("test-유저어댑터관리 아이템개수",":"+adapter.getItemCount());

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnUserItemClickListener() {
            @Override
            public void onItemClick(UserListAdapter.ViewHolder holder, View view, int position) {
                User item = adapter.getItem(position);

                Toast.makeText(getContext(), "아이템 선택됨 : " + item.getNickname(), Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
        //return inflater.inflate(R.layout.team_fragment1, container, false);
    }
}