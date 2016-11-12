package vitaliy94.mafiamaster;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.TreeMap;

public class RolesRandomizeActivity extends AppCompatActivity
{
    TextInputLayout textInputLayout;
    EditText inputName;

    Button bStartGame;
    Button bGetRole;
    Button bNextPlayer;

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

        textInputLayout.setHint(getString(R.string.inputNameHint));

        bStartGame = (Button)findViewById(R.id.buttonStartGame);
        bGetRole = (Button)findViewById(R.id.buttonGetRole);
        bNextPlayer = (Button)findViewById(R.id.buttonNextPlayer);

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
    public void onGetRoleClick(View view)
    {
        String name = inputName.getText().toString();
        players.put(name, roles.get(playersIterator));
        playersIterator++;

        bGetRole.setClickable(false);

        if(players.size() == roles.size())
        {
            bStartGame.setClickable(true);
        }
        else
        {
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
    }

    /**
     * hide players role before next player enters name and get role
     */
    public void onNextPlayerClick(View view)
    {
        bGetRole.setClickable(true);
        bNextPlayer.setClickable(false);
    }
}
