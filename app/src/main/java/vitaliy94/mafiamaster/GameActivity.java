package vitaliy94.mafiamaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class GameActivity extends AppCompatActivity
{
    //ListView listView;

    HashMap<String, Roles> players; //treeMap beomes hashMap after intent. some kind of magic

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();

        ListView listView = (ListView)findViewById(R.id.lV);

        players = (HashMap<String, Roles>)getIntent().getSerializableExtra("players");

        Set<String> p = players.keySet();
        String[] playersRoles = new String[players.size()];
        Object[]po = p.toArray();

        for(int i = 0; i < p.size(); i++)
        {
            playersRoles[i] = String.valueOf(po[i]) + "\t" + getString(players.get(String.valueOf(po[i])).getResId());
            //Log.d("str", playersRoles[i]);
        }

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playersRoles);
        listView.setAdapter(ad);
    }
}
