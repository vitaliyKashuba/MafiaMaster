package vitaliy94.mafiamaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    int playersCount = 10;
    TextView tvPlayersCount;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView tvPlayersCount = (TextView)findViewById(R.id.playersCount);
        //tvPlayersCount.setText(String.valueOf(playersCount));
    }

    public void onLessPlayersClick(View view)
    {
        playersCount--;
        setPlayersCountText(String.valueOf(playersCount));
    }

    public void onMorePlayersClick(View view)
    {
        playersCount++;
        setPlayersCountText(String.valueOf(playersCount));
    }

    public void setPlayersCountText(String text)
    {
        TextView tvPlayersCount = (TextView)findViewById(R.id.playersCount);
        tvPlayersCount.setText(text);
    }

}
