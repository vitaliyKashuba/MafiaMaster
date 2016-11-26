package vitaliy94.mafiamaster;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener
{
    ListView listView;

    ArrayList players;
    PlayersAdapter adapter;
    SwipeRefreshLayout srLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();

        srLayout = (SwipeRefreshLayout) findViewById(R.id.activity_game);
        srLayout.setOnRefreshListener(this);

        players = (ArrayList<Player>)getIntent().getSerializableExtra("players");

        adapter = new PlayersAdapter(this, players);

        listView = (ListView) findViewById(R.id.lV);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        LinearLayout ll = (LinearLayout) info.targetView;
        TextView twStatus = (TextView) ll.getChildAt(2);
        switch (item.getItemId())
        {
            case R.id.dead:
                ll.setBackgroundColor(Color.RED);
                twStatus.setText("DEAD");
                Log.d("layout", info.targetView.toString());
                return true;
            case R.id.suspect:
                ll.setBackgroundColor(Color.YELLOW);
                twStatus.setText("SUSPECT");
                return true;
            case R.id.alibi:
                ll.setBackgroundColor(Color.GREEN);
                twStatus.setText("ALIBI");
                return true;
            case R.id.clear:
                ll.setBackgroundColor(Color.WHITE);// TODO change to default theme color when add themes
                twStatus.setText("");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onRefresh()
    {



        srLayout.setRefreshing(false);
    }

}
