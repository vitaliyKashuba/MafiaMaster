package vitaliy94.mafiamaster;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;


public class PlayersAdapter extends BaseAdapter
{
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Player> players;

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
        ((TextView) v.findViewById(R.id.player_list_status)).setText("");

        return v;
    }
}
