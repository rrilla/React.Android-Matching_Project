package com.project.matchingapp3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.project.matchingapp3.R;
import com.project.matchingapp3.activity.UserDetailActivity;
import com.project.matchingapp3.adapter.OnPartyUserClickListener;
import com.project.matchingapp3.adapter.PartyUserListAdapter;
import com.project.matchingapp3.model.Party;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.model.dto.NavDataDto;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment3 extends Fragment {

    private ArrayList<Party> party = new ArrayList<Party>();
    private User loginUser;
    private String jwtToken;

    private RecyclerView recyclerView;
    PartyUserListAdapter adapter;

    public HomeFragment3(List<Party> party, User loginUser, String jwtToken){
        this.party = (ArrayList<Party>) party;
        this.loginUser = loginUser;
        this.jwtToken = jwtToken;

        //this.users.addAll(party);
        Log.e("test-파티리스트프래그받음?", party.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.home_fragment3, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);

        //리사이클러뷰에 설정할 레이아웃 매니저 - 방향세로로 설정함.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PartyUserListAdapter();
        adapter.setItems(party);
        Log.e("test-파티유저어댑터관리 아이템개수",":"+adapter.getItemCount());

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnPartyUserClickListener() {
            @Override   //상세보기 클릭시
            public void onItemClick(PartyUserListAdapter.ViewHolder holder, View view, int position) {
                Party item = adapter.getItem(position);

                Intent intent = new Intent(getContext(), UserDetailActivity.class);
                intent.putExtra("jwtToken", jwtToken);
                intent.putExtra("loginUser", loginUser);
                intent.putExtra("selectUser", item.getUser());

                startActivity(intent);
            }
            //가입 수락 버튼 클릭시
        }, new OnPartyUserClickListener() {
            @Override
            public void onItemClick(PartyUserListAdapter.ViewHolder holder, View view, int position) {
                Snackbar.make(view, "가입수락클릭", Snackbar.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }


}
