package com.example.networking;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "HTTPS_URL_TO_JSON_DATA_CHANGE_THIS_URL";
    private final String JSON_FILE = "mountains.json";

    ArrayList<MountainListItem> mountains = new ArrayList<>(Arrays.asList(new MountainListItem("Kebbekaise"),
            new MountainListItem("Monte evereste"),
            new MountainListItem("Omberg")));

    private MountainAdapter mountainAdapter;
    private RecyclerView mountainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mountainAdapter = new MountainAdapter(this, mountains, new MountainAdapter.OnClickListener() {
            @Override
            public void onClick(MountainListItem mountain) {
                Log.d("click-event", mountain.getTitle());
            }
        });

        mountainView = findViewById(R.id.recycle_mountains);
        mountainView.setLayoutManager(new LinearLayoutManager(this));
        mountainView.setAdapter(mountainAdapter);
        System.out.println(mountainAdapter.getItemCount());

        new JsonFile(this, this).execute(JSON_FILE);



    }

    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
    }

}
