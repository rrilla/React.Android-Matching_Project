package com.project.matchingapp3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.project.matchingapp3.R;
import com.project.matchingapp3.activity.UserDetailActivity;
import com.project.matchingapp3.adapter.OnPartyUserClickListener;
import com.project.matchingapp3.adapter.PartyUserListAdapter;
import com.project.matchingapp3.model.Party;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.task.RestAPITask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HomeFragment3 extends Fragment {

    private ArrayList<Party> party;
    private User loginUser;
    private String jwtToken;

    private RecyclerView recyclerView;
    PartyUserListAdapter adapter;

    public HomeFragment3(ArrayList<Party> party, User loginUser, String jwtToken){
        this.party = party;
        this.loginUser = loginUser;
        this.jwtToken = jwtToken;

        //this.users.addAll(party);
        if(party != null && party.size() != 0){
            Log.e("noteam-home3프래그먼트", "파티리스트받음 : " + party.toString());
        }else{
            Log.e("noteam-메인에 마이 팀", "파티리스트프래그 안받았다");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(loginUser.getTeams() == null){
            ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.noteam, container, false);
            return rootView;
        }

        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.home_fragment3, container, false);

        TextView f3TvName = rootView.findViewById(R.id.FHome3_tv_tName);
        TextView f3TvLocation = rootView.findViewById(R.id.FHome3_tv_tLocation);
        TextView f3TvExplain = rootView.findViewById(R.id.FHome3_tv_tExplin);
        TextView f3TvRequest = rootView.findViewById(R.id.FHome3_tv_tRequest);
        ImageView f3IvImage = rootView.findViewById(R.id.FHome3_iv_tImage);

        f3TvName.setText(loginUser.getTeams().getName());
        f3TvLocation.setText(loginUser.getTeams().getLocation());
        f3TvExplain.setText(loginUser.getTeams().getExplaintation());
        Glide.with(this).load(loginUser.getUrlTImage()).into(f3IvImage);


        if(party == null || party.size() == 0){
            f3TvRequest.setText("가입 신청한 유저가 없습니다.");
        }else {
            recyclerView = rootView.findViewById(R.id.recyclerView);

            //리사이클러뷰에 설정할 레이아웃 매니저 - 방향세로로 설정함.
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);

            adapter = new PartyUserListAdapter();
            adapter.setItems(party);
            Log.e("noteam-파티유저어댑터관리 아이템개수", ":" + adapter.getItemCount());

            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(new OnPartyUserClickListener() {
                @Override   //상세보기 클릭시
                public void onItemClick(PartyUserListAdapter.ViewHolder holder, View view, int position) {
                    //유저 정보 클릭
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
                    //가입 승인 클릭

                    if(loginUser.getId() != loginUser.getTeams().getOwner().getId()){
                        Snackbar.make(view, "구단주만 승인 가능합니다.", Snackbar.LENGTH_SHORT).show();
                        return;
                    }

                    Party item = adapter.getItem(position);

                    String[] result = new String[1];
                    RestAPITask task = new RestAPITask(jwtToken);
                    try {
                        result = task.execute("Acknowledgment/", Integer.toString(item.getId())).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Log.e("noteam-팀가입요청 결과", result[0]);

                    if(result[0].equals("ok")){
                        party.remove(position);
                        adapter.notifyItemRemoved(position);
                        adapter.notifyItemRangeChanged(position, party.size());
                        Toast.makeText(view.getContext(), "가입 수락 완료.",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(view.getContext(), "에러.  " + result[0],Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return rootView;
    }

}
