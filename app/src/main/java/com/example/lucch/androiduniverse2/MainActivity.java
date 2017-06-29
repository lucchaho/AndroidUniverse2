package com.example.lucch.androiduniverse2;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private Button button;
    ListView lst;
    String[] months={"Top CompetitiveRank", "Top Kad", "Top EliminationsPM", "Top Score"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        TextView over = (TextView) findViewById(R.id.BigLabel);
        over.setText("Overwatch Top");
        lst= (ListView) findViewById(R.id.listvw);
        ArrayAdapter<String> arrayadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,months);
        lst.setAdapter(arrayadapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv= (TextView) view;
                Toast.makeText(MainActivity.this,tv.getText(),Toast.LENGTH_LONG).show();

                Intent myIntent = new Intent(MainActivity.this, RecycleActivity.class);
                String message = tv.getText().toString();
                myIntent.putExtra(EXTRA_MESSAGE, message);
                MainActivity.this.startActivity(myIntent);
            }
        });

    }

    public void onClickBtn(View v)
    {
        Intent myIntent = new Intent(MainActivity.this, getInfosPlayer.class);
        MainActivity.this.startActivity(myIntent);
    }
}
