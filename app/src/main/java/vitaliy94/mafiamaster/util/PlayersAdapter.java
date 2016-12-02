package vitaliy94.mafiamaster.util;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import vitaliy94.mafiamaster.entitys.Player;
import vitaliy94.mafiamaster.R;
import vitaliy94.mafiamaster.entitys.Status;

/**
 * used in game activity in custom list view
 */
public class PlayersAdapter extends BaseAdapter
{
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<Player> players;

    public PlayersAdapter(Context ctx, ArrayList<Player> players)
    {
        this.ctx = ctx;
        this.players = players;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return players.size();
    }

    @Override
    public Object getItem(int i)
    {
        return players.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    public void setStatus(String player, Status status)
    {
        for(Player p : players)
        {
            if ( p.getName().equals(player) )
            {
                p.setStatus(status);
                return;
            }
        }
    }

    public void refreshStatuses()
    {
        for (Player p : players)
        {
            if (p.getStatus().equals(Status.ALIBI) || p.getStatus().equals(Status.SILENT) || p.getStatus().equals(Status.SUSPECT))
            {
                p.setStatus(Status.NONE);
            }
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View v = view;
        if (v == null)
        {
            v = lInflater.inflate(R.layout.player, viewGroup, false);
        }

        Player p = (Player)getItem(i);

        ((TextView) v.findViewById(R.id.player_list_name)).setText(p.getName());
        ((TextView) v.findViewById(R.id.player_list_role)).setText(ctx.getString(p.getRole().getResId()));
        TextView tvStatus = (TextView) v.findViewById(R.id.player_list_status);
        Status s = p.getStatus();

        if (s.equals(Status.NONE))
        {
            tvStatus.setText("");
            v.setBackground(null);
        }
        else
        {
            tvStatus.setText(s.toString());
            switch(p.getStatus())           //smells un-DRY with GameActivity.onContextItemSelected
            {
                case DEAD:
                    v.setBackgroundColor(Color.RED);
                    break;
                case SUSPECT:
                    v.setBackgroundColor(Color.YELLOW);
                    break;
                case ALIBI:
                    v.setBackgroundColor(Color.GREEN);
                    break;
                case SILENT:
                    v.setBackgroundColor(Color.GRAY);
                    break;
            }
        }

        return v;
    }
}
