package com.project.matchingapp3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.project.matchingapp3.R;
import com.project.matchingapp3.TeamActivity;
import com.project.matchingapp3.model.User;


public class HomeFragment3 extends Fragment {

    private User loginUser;
    private String jwtToken;

    public HomeFragment3(User loginUser, String jwtToken){
        this.loginUser = loginUser;
        this.jwtToken = jwtToken;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(loginUser.getTeams() == null){
            final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.noitem, container, false);
            Button btnTeam = rootView.findViewById(R.id.noitem_btn_teampage);
            btnTeam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(rootView.getContext(), TeamActivity.class);
                    intent.putExtra("jwtToken", jwtToken);
                    intent.putExtra("loginUser", loginUser);
                    startActivity(intent);
                }
            });
            return rootView;
        }

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.home_fragment3, container, false);

        TextView f3TvName = rootView.findViewById(R.id.FHome3_tv_tName);
        TextView f3TvLocation = rootView.findViewById(R.id.FHome3_tv_tLocation);
        TextView f3TvExplain = rootView.findViewById(R.id.FHome3_tv_tExplin);
        ImageView f3IvImage = rootView.findViewById(R.id.FHome3_iv_tImage);

        f3TvName.setText(loginUser.getTeams().getName());
        f3TvLocation.setText(loginUser.getTeams().getLocation());
        f3TvExplain.setText(loginUser.getTeams().getExplaintation());
        Glide.with(this).load(loginUser.getUrlTImage()).into(f3IvImage);

        return rootView;
    }

}
