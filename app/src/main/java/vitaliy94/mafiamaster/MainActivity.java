package vitaliy94.mafiamaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    int playersCount = 10;
    int mafiaCount = 2;

    TextView tvPlayersCount;
    TextView tvMafiaCount;

    CheckBox cbComissar;
    CheckBox cbDoctor;
    CheckBox cbManiak;
    CheckBox cbWhore;
    CheckBox cbImmortal;
    CheckBox cbDon;
    CheckBox cbSheriff;
    CheckBox cbChosenOne;

    ArrayList<Roles> roles;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPlayersCount = (TextView)findViewById(R.id.playersCount);
        tvMafiaCount = (TextView)findViewById(R.id.mafiaCount);

        cbComissar = (CheckBox)findViewById(R.id.checkBoxComissar);
        cbDoctor = (CheckBox)findViewById(R.id.checkBoxDoctor);
        cbManiak = (CheckBox)findViewById(R.id.checkBoxManiac);
        cbWhore = (CheckBox)findViewById(R.id.checkBoxWhore);
        cbImmortal = (CheckBox)findViewById(R.id.checkBoxImmortal);
        cbDon = (CheckBox)findViewById(R.id.checkBoxDon);
        cbSheriff = (CheckBox)findViewById(R.id.checkBoxSheriff);
        cbChosenOne = (CheckBox)findViewById(R.id.checkBoxChosenOne);

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

    public void onStartClick(View view) //TODO add exception if roles more then players
    {
        roles = new ArrayList<>();

        if(cbComissar.isChecked())
        {
            roles.add(Roles.COMISSAR);
        }
        if(cbDoctor.isChecked())
        {
            roles.add(Roles.DOCTOR);
        }
        if(cbManiak.isChecked())
        {
            roles.add(Roles.MANIAC);
        }
        if(cbWhore.isChecked())
        {
            roles.add(Roles.WHORE);
        }
        if(cbImmortal.isChecked())
        {
            roles.add(Roles.IMMORTAL);
        }
        if(cbDon.isChecked())
        {
            roles.add(Roles.DON);
        }
        if(cbSheriff.isChecked())
        {
            roles.add(Roles.SHERIFF);
        }
        if(cbChosenOne.isChecked())
        {
            roles.add(Roles.SHOSEN_ONE);
        }

        for (int i = 0; i < mafiaCount; i++)
        {
            roles.add(Roles.MAFIA);
        }

        for (int i = roles.size(); i < playersCount; i++)
        {
            roles.add(Roles.CITIZEN);
        }


        Randomizer.randomize(roles);

        Intent intent = new Intent(MainActivity.this, RolesRandomizeActivity.class);
        startActivity(intent);
    }
}
