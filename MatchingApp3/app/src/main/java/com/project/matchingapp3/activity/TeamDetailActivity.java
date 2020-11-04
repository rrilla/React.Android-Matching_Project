package com.project.matchingapp3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.matchingapp3.R;
import com.project.matchingapp3.model.Team;
import com.project.matchingapp3.task.RestAPITask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TeamDetailActivity extends AppCompatActivity {

    private int selectTeamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);

        Intent intent = getIntent();
        selectTeamId = intent.getIntExtra("selectTeamId",0);

        String[] result = new String[1];
        RestAPITask task = new RestAPITask();

        try {
            result = task.execute("app/teamDetail/", Integer.toString(selectTeamId)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("test-팀받기",result[0]);
        Gson gson = new Gson();
        Team team = gson.fromJson(result[0], new TypeToken<Team>(){}.getType());
        Log.e("test-팀", team.toString());
    }
}