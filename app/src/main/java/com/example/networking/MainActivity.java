package com.example.networking;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";

    ArrayList<MountainListItem> mountains = new ArrayList<>();

    private MountainAdapter mountainAdapter;
    private RecyclerView mountainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mountainAdapter = new MountainAdapter(this, mountains, new MountainAdapter.OnClickListener() {
            @Override
            public void onClick(MountainListItem mountain) {
                Log.d("click-event", mountain.getName());
            }
        });

        mountainView = findViewById(R.id.recycle_mountains);
        mountainView.setLayoutManager(new LinearLayoutManager(this));
        mountainView.setAdapter(mountainAdapter);
        System.out.println(mountainAdapter.getItemCount());

        JsonTask jsonTask = new JsonTask(this);
        jsonTask.execute(JSON_URL);
    }

    @Override
    public void onPostExecute(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<MountainListItem>>() {}.getType();

        Log.d("Response", String.format("Got response from GET: %b", json != null) );

        ArrayList<MountainListItem> newMountains =  gson.fromJson(json, type);

        mountains.addAll(newMountains);
        mountainAdapter.notifyDataSetChanged();

    }
}
