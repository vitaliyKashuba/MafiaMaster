package vitaliy94.mafiamaster;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

        Set<String> p = players.keySet();                       //
        String[] playersRoles = new String[players.size()];     //TODO refactor it. later
        Object[]po = p.toArray();                               //

        for(int i = 0; i < p.size(); i++)
        {
            playersRoles[i] = fillWithSpaces(String.valueOf(po[i]), 12) + " " + getString(players.get(String.valueOf(po[i])).getResId());
        }

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playersRoles);
        listView.setAdapter(ad);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                TextView tv = (TextView) view;
                String txt = tv.getText().toString();
                if(!txt.contains("DEAD"))
                {
                    tv.setText(txt + " DEAD");
                    tv.setBackgroundColor(Color.RED);
                }
                else
                {
                    tv.setText(txt.substring(0, txt.length()-5));
                    tv.setBackgroundColor(Color.WHITE);
                }
                Log.d("html", tv.getText().toString());
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) //TODO finish it
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.edit:
                //editItem(info.position); // метод, выполняющий действие при редактировании пункта меню
                return true;
            case R.id.delete:
                //deleteItem(info.position); //метод, выполняющий действие при удалении пункта меню
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    /**
     * fill string with spaces
     * used to align collumns
     *
     * doesn't work :(
     *
     * @return
     */
    String fillWithSpaces(String str, int toSize)
    {
        if(str.length()>=toSize)
        {
            return str;
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            for (int i = 0; i < toSize-str.length(); i++)
            {
                sb.append(" ");
            }
            return sb.toString();
        }
    }
}
