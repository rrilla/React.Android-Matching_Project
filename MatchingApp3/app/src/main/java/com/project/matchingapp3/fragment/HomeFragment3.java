package com.project.matchingapp3.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.matchingapp3.MainActivity;
import com.project.matchingapp3.R;
import com.project.matchingapp3.activity.UserDetailActivity;
import com.project.matchingapp3.adapter.OnUserItemClickListener;
import com.project.matchingapp3.adapter.PartyUserListAdapter;
import com.project.matchingapp3.adapter.UserListAdapter;
import com.project.matchingapp3.model.Party;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.model.dto.NavDataDto;

import java.util.ArrayList;

public class HomeFragment3 extends Fragment {

    private ArrayList<Party> party = new ArrayList<Party>();
    private NavDataDto navDataDto;
    private String jwtToken;

    private RecyclerView recyclerView;
    PartyUserListAdapter adapter;

    public HomeFragment3(NavDataDto navDataDto){
        this.navDataDto = navDataDto;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.home_fragment3, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);

        //리사이클러뷰에 설정할 레이아웃 매니저 - 방향세로로 설정함.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PartyUserListAdapter(NavDataDto navDataDto, String jwtToken);
        adapter.setItems(party);
        Log.e("test-파티유저어댑터관리 아이템개수",":"+adapter.getItemCount());

        recyclerView.setAdapter(adapter);

//        adapter.setOnItemClickListener(new OnUserItemClickListener() {
//            @Override
//            public void onItemClick(UserListAdapter.ViewHolder holder, View view, int position) {
//                User item = adapter.getItem(position);
//
//                Intent intent = new Intent(getContext(), UserDetailActivity.class);
//                intent.putExtra("jwtToken", jwtToken);
//                intent.putExtra("navDataDto", navDataDto);
//                intent.putExtra("selectUserId", item.getId());
//
//                if(item.getTeams() != null){
//                    intent.putExtra("selectUserTeam", item.getTeams().getName());
//                    if(item.getId() == item.getTeams().getOwner().getId()){
//                        intent.putExtra("selectUserRole", "Owner");
//                    }
//                }
//
//                startActivity(intent);
//            }
//        });

        return rootView;
    }

}
