package com.example.lucch.androiduniverse2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;


public class RecycleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<MyObject> cities = new ArrayList<>();

    private getOverwatchInfos test = new getOverwatchInfos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //remplir la ville

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ajouterTop();



    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //définit l'agencement des cellules, ici de façon verticale, comme une ListView
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //pour adapter en grille comme une RecyclerView, avec 2 cellules par ligne
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        //puis créer un MyAdapter, lui fournir notre liste de villes.
        //cet adapter servira à remplir notre recyclerview

        LinearLayoutManager llm = new LinearLayoutManager(RecycleActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(new MyAdapter(cities));
    }

    private void ajouterTop() {
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String[] tokens = message.split("[ ]");
        String lol = "";
        try {
            lol = test.getTopTwenty(tokens[1],"3");
            final JSONObject obj = new JSONObject(lol);
            final JSONArray call = obj.getJSONArray("lbs");
            final int n = call.length();
            for (int i = 0; i < n; ++i) {
                final JSONObject person = call.getJSONObject(i);
                cities.add(new MyObject(person.getString("Name"),person.getString("EmblemPath")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
