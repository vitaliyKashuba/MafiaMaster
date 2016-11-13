package vitaliy94.mafiamaster;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeMap;

public class RolesRandomizeActivity extends AppCompatActivity
{
    TextInputLayout textInputLayout;
    EditText inputName;
    TextView twRoleName;

    Button bStartGame;
    Button bNextPlayer;
    ImageButton ibGetRole;

    TreeMap<String, Roles> players;
    ArrayList<Roles> roles;

    int playersIterator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles_randomize);
        getSupportActionBar().hide();

        textInputLayout = (TextInputLayout)findViewById(R.id.inputNameLayout);
        inputName = (EditText)textInputLayout.findViewById(R.id.inputName);
        twRoleName = (TextView)findViewById(R.id.textViewRole);

        textInputLayout.setHint(getString(R.string.inputNameHint));

        bStartGame = (Button)findViewById(R.id.buttonStartGame);
        bNextPlayer = (Button)findViewById(R.id.buttonNextPlayer);
        ibGetRole = (ImageButton)findViewById(R.id.imageView);

        bStartGame.setClickable(false);
        bNextPlayer.setClickable(false);

        players = new TreeMap<>();

        roles = (ArrayList<Roles>)getIntent().getSerializableExtra("roles");
        playersIterator = 0;
    }

    /**
     * add new player to game
     *
     * button should be clicked after name entered
     */
    public void onGetRoleClick(View view) //TODO add input checker
    {
        String name = inputName.getText().toString();
        Roles role = roles.get(playersIterator);
        players.put(name, role);
        playersIterator++;

        ibGetRole.setClickable(false);

        changeCardImage(role);
        twRoleName.setText(getString(role.getResId()));

        if(players.size() == roles.size())
        {
            Snackbar.make(view, "Можно начинать игру", Snackbar.LENGTH_LONG).setDuration(4000).show();
            bStartGame.setClickable(true);
        }
        else
        {
            Snackbar.make(view, "Игрок добавлен, осталось " + (roles.size()-playersIterator), Snackbar.LENGTH_LONG).setDuration(4000).show();
            bNextPlayer.setClickable(true);
        }

        Log.d("players", String.valueOf(players.size()));
    }

    /**
     * starts game
     */
    public void onStartGameClick(View view)
    {
        //start game
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
        bNextPlayer.setClickable(false);
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
}
