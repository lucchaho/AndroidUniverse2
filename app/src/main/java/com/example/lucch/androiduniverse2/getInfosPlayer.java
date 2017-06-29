package com.example.lucch.androiduniverse2;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class getInfosPlayer extends AppCompatActivity {

    private EditText btag;
    private Button button;
    private getOverwatchInfos goi = new getOverwatchInfos();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_infos);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        button = (Button) findViewById(R.id.button);
    }

    public void buttonOnClick(View view) throws Exception {
        btag = (EditText) findViewById(R.id.Btag);
        String blob = goi.getUserInfos(btag.getText().toString(), "stats");
        if (blob != "404")
            appendContent(blob, btag.getText().toString());
        else
            popDialogBox(btag.getText().toString());
    }


    public void popDialogBox(String btag) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Erreur");
        builder.setMessage("BattleTag : '" + btag + "' introuvable.");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.show();
        TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
    }

    public void appendContent(String blob, String user) throws Exception {

        JSONObject obj = new JSONObject(blob);
        final JSONObject qp = obj.getJSONObject("eu").getJSONObject("stats").getJSONObject("quickplay").getJSONObject("overall_stats");

        TextView uname = (TextView) findViewById(R.id.Uname);
        uname.setText(user);

        ImageView avatar = (ImageView) findViewById(R.id.avatar);
        Picasso.with(this).load(qp.getString("avatar")).into(avatar);

        TextView nlevel = (TextView) findViewById(R.id.nlevel);
        int level = Integer.parseInt(qp.getString("prestige")) * 100 + Integer.parseInt(qp.getString("level"));
        nlevel.setText("Niveau : " + level);
        TextView ngames = (TextView) findViewById(R.id.ngames);
        ngames.setText("Nombre de parties : " + qp.getString("games"));
        TextView nwins = (TextView) findViewById(R.id.nwins);
        nwins.setText("Nombre de victoires : " + qp.getString("wins"));
        TextView nlooses = (TextView) findViewById(R.id.nloses);
        nlooses.setText("Nombre de défaites : " + qp.getString("losses"));
        TextView nwinrate = (TextView) findViewById(R.id.nwinrate);
        nwinrate.setText("Pourcentage de victoires : " + qp.getString("win_rate") + "%");


        try {
            final JSONObject compet = obj.getJSONObject("eu").getJSONObject("stats").getJSONObject("competitive").getJSONObject("overall_stats");
            TextView points = (TextView) findViewById(R.id.points);
            points.setText("Points compétitifs : " + compet.getString("comprank") + " (" + compet.getString("tier") + ")");
            TextView games = (TextView) findViewById(R.id.games);
            games.setText("Nombre de parties : " + compet.getString("games"));
            TextView wins = (TextView) findViewById(R.id.wins);
            wins.setText("Nombre de victoires : " + compet.getString("wins"));
            TextView looses = (TextView) findViewById(R.id.looses);
            looses.setText("Nombre de défaites : " + compet.getString("losses"));
            TextView ties = (TextView) findViewById(R.id.ties);
            ties.setText("Nombre de matchs nuls : " + compet.getString("ties"));
            TextView winrate = (TextView) findViewById(R.id.winrate);
            winrate.setText("Pourcentage de victoires : " + compet.getString("win_rate") + "%");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            TextView points = (TextView) findViewById(R.id.points);
            points.setText("Points compétitifs : Unranked");
            TextView games = (TextView) findViewById(R.id.games);
            games.setText("Nombre de parties : Unranked");
            TextView wins = (TextView) findViewById(R.id.wins);
            wins.setText("Nombre de victoires : Unranked");
            TextView looses = (TextView) findViewById(R.id.looses);
            looses.setText("Nombre de défaites : Unranked");
            TextView ties = (TextView) findViewById(R.id.ties);
            ties.setText("Nombre de matchs nuls : Unranked");
            TextView winrate = (TextView) findViewById(R.id.winrate);
            winrate.setText("Pourcentage de victoires : Unranked");
        }


    }
}