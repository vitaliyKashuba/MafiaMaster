package vitaliy94.mafiamaster;

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
    Button bGetRole;
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
        Roles role = roles.get(playersIterator);
        players.put(name, role);
        playersIterator++;

        bGetRole.setClickable(false);

        if(players.size() == roles.size())
        {
            Snackbar.make(view, "Можно начинать игру", Snackbar.LENGTH_LONG).setDuration(4000).show();
            bStartGame.setClickable(true);
        }
        else
        {
            inputName.setText("");
            twRoleName.setText(getString(role.getResId()));
            getString(R.string.role_comissar);
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
    }

    /**
     * hide players role before next player enters name and get role
     */
    public void onNextPlayerClick(View view)
    {
        bGetRole.setClickable(true);
        bNextPlayer.setClickable(false);
        twRoleName.setText("");
    }
}
