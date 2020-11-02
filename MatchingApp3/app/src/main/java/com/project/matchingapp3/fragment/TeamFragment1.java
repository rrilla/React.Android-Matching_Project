package com.project.matchingapp3.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.matchingapp3.R;
import com.project.matchingapp3.model.Team;
import com.project.matchingapp3.model.dto.NavDataDto;

public class TeamFragment1 extends Fragment {

    private Team team;
    private TextView tvName, tvLocation, tvCount;
    private ImageView ivImage;

    public TeamFragment1(Team team){
        this.team = team;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.team_fragment1, container, false);

        tvName = rootView.findViewById(R.id.iTeam_tv_tName);
        tvLocation = rootView.findViewById(R.id.iTeam_tv_tLocation);
        tvCount = rootView.findViewById(R.id.iTeam_tv_tCount);

        tvName.setText(team.getName());
        tvLocation.setText(team.getLocation());
        tvCount.setText(team.getUsers().size());
        Glide.with(this).load(team.getImage()).into(ivImage);

        return rootView;
        //return inflater.inflate(R.layout.team_fragment1, container, false);
    }
}