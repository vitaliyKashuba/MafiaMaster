package vitaliy94.mafiamaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    int playersCount = 10;
    int mafiaCount = 2;

    TextView tvPlayersCount;
    TextView tvMafiaCount;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPlayersCount = (TextView)findViewById(R.id.playersCount);
        tvMafiaCount = (TextView)findViewById(R.id.mafiaCount);
    }

    public void onPlayersClick(View view)
    {
        switch(view.getId())
        {
            case R.id.buttonLessPlayers:
                playersCount--;
                break;
            case R.id.buttonMorePlayers:
                playersCount++;
                break;
            case R.id.buttonLessMafia:
                mafiaCount--;
                break;
            case R.id.buttonMoreMafia:
                mafiaCount++;
                break;
        }

        tvPlayersCount.setText(String.valueOf(playersCount));
        tvMafiaCount.setText(String.valueOf(mafiaCount));
    }

    public void onStartClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, RolesRandomizeActivity.class);
        startActivity(intent);

    }
}
