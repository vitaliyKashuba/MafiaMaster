package vitaliy94.mafiamaster;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class RolesRandomizeActivity extends AppCompatActivity
{

    TextInputLayout textInputLayout;
    EditText inputName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles_randomize);
        getSupportActionBar().hide();

        textInputLayout = (TextInputLayout)findViewById(R.id.inputNameLayout);
        inputName = (EditText)textInputLayout.findViewById(R.id.inputName);

        textInputLayout.setHint(getString(R.string.inputNameHint));

    }

}
