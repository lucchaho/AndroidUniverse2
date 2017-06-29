package com.example.lucch.androiduniverse2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    GridView androidGridView;
    List<String> Name = new ArrayList<String>();
    List<String> url_img = new ArrayList<String>();
    private getOverwatchInfos test = new getOverwatchInfos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        new LongOperation().execute("");

        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(Main2Activity.this, Name, url_img);
        androidGridView=(GridView)findViewById(R.id.grid_view_image_text);
        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                Toast.makeText(Main2Activity.this, "GridView Item: " + Name.get(+i), Toast.LENGTH_LONG).show();
            }
        });

    }


    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Intent intent = getIntent();
            String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            String lol = "";
            try {
                lol = test.getTopTwenty("CompetitiveRank","3");
                final JSONObject obj = new JSONObject(lol);
                final JSONArray call = obj.getJSONArray("lbs");
                final int n = call.length();
                for (int i = 0; i < n; ++i) {
                    final JSONObject person = call.getJSONObject(i);
                    Name.add(person.getString("Name"));
                    url_img.add(person.getString("EmblemPath"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ("OK");
        }

        @Override
        protected void onPostExecute(String result) {

        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
