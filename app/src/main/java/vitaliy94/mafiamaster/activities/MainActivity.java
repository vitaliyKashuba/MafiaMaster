package vitaliy94.mafiamaster.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vitaliy94.mafiamaster.util.AlertDialogBuilder;
import vitaliy94.mafiamaster.R;
import vitaliy94.mafiamaster.util.PreferenceSaver;
import vitaliy94.mafiamaster.util.Randomizer;
import vitaliy94.mafiamaster.entitys.Roles;

public class MainActivity extends PreferenceSaver
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
    protected void onCreate(Bundle savedInstanceState) //TODO make it usable with landscape orientation
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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

        //settings = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);

        if (showIntro1)
        {
            showIntroDialog();
        }
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
            roles.add(Roles.CHOSEN_ONE);
        }

        for (int i = 0; i < mafiaCount; i++)
        {
            roles.add(Roles.MAFIA);
        }

        /**
         * check is there no math mistakes in players count and active roles selective
         */
        if (roles.size()>playersCount)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Проверьте количество ироков и выбранные роли", Toast.LENGTH_SHORT);
            toast.show();
            roles.clear();
            return;
        }

        for (int i = roles.size(); i < playersCount; i++)
        {
            roles.add(Roles.CITIZEN);
        }


        Randomizer.randomize(roles);

        Intent intent = new Intent(MainActivity.this, RolesRandomizeActivity.class);
        intent.putExtra("roles", roles);
        startActivity(intent);
    }

    /**
     * shows introduction
     * //TODO make it possible to disable it
     */
    void showIntroDialog()
    {
        /*String[] s = {getString(R.string.intro_do_not_show_again)};
        boolean[] b = {false};
        DialogInterface.OnClickListener onOkListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.cancel();
            }
        };

        DialogInterface.OnMultiChoiceClickListener onCheckListener = new DialogInterface.OnMultiChoiceClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b)
            {
                Log.d("CHECK LISTENER", "stub");
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.intro_title)
                //.setMultiChoiceItems(s, b, onCheckListener)
                .setMessage(R.string.intro_main_text)
                .setPositiveButton("ok", onOkListener)
                .setNegativeButton(getText(R.string.intro_do_not_show_again), onOkListener);

        AlertDialog alert = builder.create();
        alert.show();*/
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        AlertDialog alert = AlertDialogBuilder.buildIntroAlertDialog(builder, getString(R.string.intro_main_text));
        alert.show();
    }

}
