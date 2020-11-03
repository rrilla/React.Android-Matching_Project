package com.project.matchingapp3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.matchingapp3.R;
import com.project.matchingapp3.fragment.TeamFragment1;
import com.project.matchingapp3.model.Team;

import java.util.ArrayList;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.ViewHolder>
        implements OnTeamItemClickListener  {
    ArrayList<Team> items = new ArrayList<Team>();

    OnTeamItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.teamlist_item, viewGroup, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Team item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Team item) {
        items.add(item);
    }

    public void setItems(ArrayList<Team> items) {
        this.items = items;
    }

    public Team getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Team item) {
        items.set(position, item);
    }

    public void setOnItemClickListener(OnTeamItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvLocation, tvCount;
        ImageView ivImage;
        View view;

        public ViewHolder(View itemView, final OnTeamItemClickListener listener) {
            super(itemView);
            this.view = itemView;

            tvName = itemView.findViewById(R.id.iTeam_tv_tName);
            tvLocation = itemView.findViewById(R.id.iTeam_tv_tLocation);
            tvCount = itemView.findViewById(R.id.iTeam_tv_tCount);
            ivImage = itemView.findViewById(R.id.iTeam_iv_tImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Team item) {
            tvName.setText(item.getName());
            tvLocation.setText(item.getLocation());
            tvCount.setText("1");
            //Glide.with(view).load("http://10.100.102.15:8000/image/"+item.getImage()).into(ivImage);
            Glide.with(view).load(item.getImage()).into(ivImage);
        }

    }
}
