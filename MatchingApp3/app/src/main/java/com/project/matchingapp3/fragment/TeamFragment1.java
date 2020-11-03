package com.project.matchingapp3.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.matchingapp3.R;
import com.project.matchingapp3.adapter.OnTeamItemClickListener;
import com.project.matchingapp3.adapter.TeamListAdapter;
import com.project.matchingapp3.model.Team;
import com.project.matchingapp3.model.dto.NavDataDto;

import java.util.ArrayList;
import java.util.List;

public class TeamFragment1 extends Fragment {

    private ArrayList<Team> teams = new ArrayList<Team>();
    private RecyclerView recyclerView;
    TeamListAdapter adapter;

    public TeamFragment1(List<Team> teams){
        this.teams = (ArrayList<Team>) teams;
        //this.teams.addAll(teams);
        Log.e("test-팀리스트프래그받음?", teams.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.team_fragment1, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);

        //리사이클러뷰에 설정할 레이아웃 매니저 - 방향세로로 설정함.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TeamListAdapter();
        adapter.setItems(teams);
        Log.e("test어댑터관리 아이템개수",":"+adapter.getItemCount());

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnTeamItemClickListener() {
            @Override
            public void onItemClick(TeamListAdapter.ViewHolder holder, View view, int position) {
                Team item = adapter.getItem(position);

                Toast.makeText(getContext(), "아이템 선택됨 : " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
        //return inflater.inflate(R.layout.team_fragment1, container, false);
    }
}