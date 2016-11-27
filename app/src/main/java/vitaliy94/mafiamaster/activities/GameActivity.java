package vitaliy94.mafiamaster.activities;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
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

import vitaliy94.mafiamaster.entitys.Player;
import vitaliy94.mafiamaster.util.AlertDialogBuilder;
import vitaliy94.mafiamaster.util.PlayersAdapter;
import vitaliy94.mafiamaster.R;
import vitaliy94.mafiamaster.util.PreferenceSaver;

public class GameActivity extends PreferenceSaver implements SwipeRefreshLayout.OnRefreshListener
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

        if (showIntro3)
        {
            showIntroDialog();
        }
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
                //Log.d("DEFAULT COLOR", ll.getBackground().toString());
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
            case R.id.silent:
                ll.setBackgroundColor(Color.GRAY);
                twStatus.setText("SILENT");
                return true;
            case R.id.clear:
                //ll.setBackgroundColor(Color.WHITE);
                ll.setBackground(null); //null is default background // TODO make it DRY. later
                twStatus.setText("");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    /**
     * calls at new round
     * clear suspect's and alibi player
     * leave only DEAD and non-status players
     */
    @Override
    public void onRefresh()
    {
        for (int i = 0; i < listView.getCount(); i++)
        {
            LinearLayout ll = (LinearLayout)listView.getChildAt(i);
            TextView twStatus = (TextView) ll.getChildAt(2);
            Log.d("STATUSS", twStatus.getText().toString());

            if("SILENT".contentEquals(twStatus.getText()) || "ALIBI".contentEquals(twStatus.getText()) || "SUSPECT".contentEquals(twStatus.getText()))//some kind of magic. it won't work like twStatus.getText().toString.equals("...")
            {
                ll.setBackground(null);// TODO make it DRY. later
                twStatus.setText("");
            }
        }

        srLayout.setRefreshing(false);
    }

    /**
     * shows introduction
     */
    void showIntroDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        AlertDialog alert = AlertDialogBuilder.buildIntroAlertDialog(builder, getString(R.string.intro_game_text), AlertDialogsEnum.GAME);
        alert.show();
    }

}