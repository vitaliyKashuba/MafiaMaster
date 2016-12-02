package vitaliy94.mafiamaster.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vitaliy94.mafiamaster.entitys.Player;
import vitaliy94.mafiamaster.R;
import vitaliy94.mafiamaster.entitys.Roles;
import vitaliy94.mafiamaster.entitys.Status;
import vitaliy94.mafiamaster.util.AlertDialogBuilder;
import vitaliy94.mafiamaster.util.PreferenceSaver;

public class RolesRandomizeActivity extends PreferenceSaver
{
    TextInputLayout textInputLayout;
    EditText inputName;
    TextView twRoleName;

    Button bStartGame;
    Button bNextPlayer;
    ImageButton ibGetRole;

    ArrayList<Roles> roles;
    ArrayList<Player> players;

    int playersIterator;

    @Override
    protected void onCreate(Bundle savedInstanceState) //TODO make it usable with landscape orientation
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles_randomize);
        getSupportActionBar().hide();

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        textInputLayout = (TextInputLayout)findViewById(R.id.inputNameLayout);
        inputName = (EditText)textInputLayout.findViewById(R.id.inputName);
        twRoleName = (TextView)findViewById(R.id.textViewRole);

        textInputLayout.setHint(getString(R.string.inputNameHint));

        bStartGame = (Button)findViewById(R.id.buttonStartGame);
        bNextPlayer = (Button)findViewById(R.id.buttonNextPlayer);
        ibGetRole = (ImageButton)findViewById(R.id.imageView);

        bStartGame.setVisibility(View.INVISIBLE);
        bNextPlayer.setVisibility(View.INVISIBLE);

        players = new ArrayList<>();

        roles = (ArrayList<Roles>)getIntent().getSerializableExtra("roles");
        playersIterator = 0;

        if (showIntro2)
        {
            showIntroDialog();
        }
    }

    /**
     * add new player to game
     *
     * button should be clicked after name entered
     */
    public void onGetRoleClick(View view) // TODO add unique name check
    {
        if(!isNameNotEmpty())
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Введите имя!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        String name = inputName.getText().toString();
        Roles role = roles.get(playersIterator);
        //players.put(name, role);
        players.add(new Player(name, role, Status.NONE));
        playersIterator++;

        ibGetRole.setClickable(false);

        changeCardImage(role);
        twRoleName.setText(getString(role.getResId()));

        if(players.size() == roles.size())
        {
            Snackbar.make(view, "Можно начинать игру", Snackbar.LENGTH_LONG).show();
            bStartGame.setVisibility(View.VISIBLE);
        }
        else
        {
            Snackbar.make(view, "Игрок добавлен, осталось " + (roles.size()-playersIterator), Snackbar.LENGTH_LONG).show();
            bNextPlayer.setVisibility(View.VISIBLE);
        }

        Log.d("players", String.valueOf(players.size()));
    }

    /**
     * starts game
     */
    public void onStartGameClick(View view)
    {
        Intent intent = new Intent(RolesRandomizeActivity.this, GameActivity.class);
        intent.putExtra("players", players);
        startActivity(intent);
    }

    /**
     * hide players role before next player enters name and get role
     */
    public void onNextPlayerClick(View view)
    {
        inputName.setText("");
        ibGetRole.setClickable(true);
        bNextPlayer.setVisibility(View.INVISIBLE);
        twRoleName.setText("");
        setDefaultCardImage();
    }

    void changeCardImage(Roles role)
    {
        switch (role)
        {
            case MAFIA:
                ibGetRole.setImageResource(R.drawable.mafia);
                break;
            case COMISSAR:
                ibGetRole.setImageResource(R.drawable.card_comissar);
                break;
            case DOCTOR:
                ibGetRole.setImageResource(R.drawable.card_doctor);
                break;
            case MANIAC:
                ibGetRole.setImageResource(R.drawable.card_maniac);
                break;
            case WHORE:
                ibGetRole.setImageResource(R.drawable.card_whore);
                break;
            case IMMORTAL:
                ibGetRole.setImageResource(R.drawable.card_immortal);
                break;
            case DON:
                ibGetRole.setImageResource(R.drawable.card_don);
                break;
            case SHERIFF:
                ibGetRole.setImageResource(R.drawable.card_sheriff);
                break;
            case CHOSEN_ONE:
                ibGetRole.setImageResource(R.drawable.card_chosen_one);
                break;
            case CITIZEN:
                ibGetRole.setImageResource(R.drawable.card_citizen);
                break;
        }
    }

    void setDefaultCardImage()
    {
        ibGetRole.setImageResource(R.drawable.card_unknown2);
    }

    boolean isNameNotEmpty()
    {
        if(inputName.getText().length()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * shows introduction
     */
    void showIntroDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(RolesRandomizeActivity.this);
        AlertDialog alert = AlertDialogBuilder.buildIntroAlertDialog(builder, getString(R.string.intro_randomizer_text), AlertDialogsEnum.RANDOMIZER);
        alert.show();
    }
}
