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

import java.util.ArrayList;

public class PartyUserListAdapter extends RecyclerView.Adapter<PartyUserListAdapter.ViewHolder> implements OnPartyUserClickListener  {

    ArrayList<Party> items = new ArrayList<Party>();

    OnPartyUserClickListener listener1, listener2;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.party_userlist_item, viewGroup, false);

        return new ViewHolder(itemView, listener1, listener2);
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

    public void setOnItemClickListener(OnPartyUserClickListener listener1, OnPartyUserClickListener listener2) {
        this.listener1 = listener1;
        this.listener2 = listener2;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener1 != null) {
            listener1.onItemClick(holder, view, position);
        }
        if (listener2 != null) {
            listener2.onItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvLocation, tvPosition;
        Button btnInfo, btnAccept;
        ImageView ivImage;
        View view;
        Party item;

        public ViewHolder(final View itemView, final OnPartyUserClickListener listener1, final OnPartyUserClickListener listener2) {
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
                    int position = getAdapterPosition();
                    if (listener1 != null) {
                        listener1.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });

            btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener2 != null) {
                        listener2.onItemClick(ViewHolder.this, view, position);
                    }
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
