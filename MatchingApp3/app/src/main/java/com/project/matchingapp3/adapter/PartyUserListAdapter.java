package com.project.matchingapp3.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.matchingapp3.R;
import com.project.matchingapp3.activity.UserDetailActivity;
import com.project.matchingapp3.model.Party;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.model.dto.NavDataDto;

import java.util.ArrayList;

public class PartyUserListAdapter extends RecyclerView.Adapter<PartyUserListAdapter.ViewHolder> {

    ArrayList<Party> items = new ArrayList<Party>();
    static NavDataDto navDataDto;
    static String jwtToken;

    public PartyUserListAdapter(NavDataDto navDataDto, String jwtToken){
        this.navDataDto = navDataDto;
        this.jwtToken = jwtToken;
    }

    @NonNull
    @Override
    public PartyUserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.party_userlist_item, viewGroup, false);

        return new PartyUserListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PartyUserListAdapter.ViewHolder viewHolder, int position) {
        Party item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Party item) {
        items.add(item);
    }

    public void setItems(ArrayList<Party> items) {
        this.items = items;
    }

    public Party getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Party item) {
        items.set(position, item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvLocation, tvPosition;
        Button btnInfo, btnAccept;
        ImageView ivImage;
        View view;
        Party item;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.view = itemView;

            tvName = itemView.findViewById(R.id.pUser_tv_Name);
            tvLocation = itemView.findViewById(R.id.pUser_tv_location);
            tvPosition = itemView.findViewById(R.id.pUser_tv_position);
            ivImage = itemView.findViewById(R.id.pUser_tv_image);
            btnInfo = itemView.findViewById(R.id.pUser_btn_info);
            btnAccept = itemView.findViewById(R.id.pUser_btn_accept);

            btnInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(itemView.getContext(), UserDetailActivity.class);
                    intent.putExtra("jwtToken", jwtToken);
                    intent.putExtra("navDataDto", navDataDto);
                    intent.putExtra("selectUserId", item.getId());

                    if(item.getUser().getTeams() != null){
                        intent.putExtra("selectUserTeam", item.getUser().getTeams().getName());
                        if(item.getId() == item.getUser().getTeams().getOwner().getId()){
                            intent.putExtra("selectUserRole", "Owner");
                        }
                    }

                    startActivity(intent);
                }
            });

            btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }

        public void setItem(Party item) {
            this.item = item;
            tvName.setText(item.getUser().getNickname());
            tvLocation.setText(item.getUser().getLocation());
            tvPosition.setText(item.getUser().getPosition());
            Glide.with(view).load(item.getUser().getUrlImage()).into(ivImage);
        }

    }
}
