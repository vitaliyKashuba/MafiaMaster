package vitaliy94.mafiamaster.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * super class for activities
 * contains saving setting
 * realized 'not show again alert dialog' function without lots of code-copying but in bad style
 */
public class PreferenceSaver extends AppCompatActivity
{
    public static final String PREFERENCES = "settings";
    public static final String PREFERENCES_SHOW_INTRO_1 = "intro1";
    public static final String PREFERENCES_SHOW_INTRO_2 = "intro2";
    public static final String PREFERENCES_SHOW_INTRO_3 = "intro3";
    public boolean showIntro1 = true;
    public boolean showIntro2 = true;
    public boolean showIntro3 = true;

    public static SharedPreferences settings;

    void init()
    {
        settings = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        init();
        scanSettings();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        scanSettings();
    }

    void scanSettings()
    {
        showIntro1 = settings.getBoolean(PREFERENCES_SHOW_INTRO_1, true);
        showIntro2 = settings.getBoolean(PREFERENCES_SHOW_INTRO_2, true);
        showIntro3 = settings.getBoolean(PREFERENCES_SHOW_INTRO_3, true);
    }

    static void notShowAgain(AlertDialogsEnum en)
    {
        SharedPreferences.Editor editor = settings.edit();
        switch(en)
        {
            case MAIN:
                editor.putBoolean(PREFERENCES_SHOW_INTRO_1, false);
                break;
            case RANDOMIZER:
                editor.putBoolean(PREFERENCES_SHOW_INTRO_2, false);
                break;
            case GAME:
                editor.putBoolean(PREFERENCES_SHOW_INTRO_3, false);
                break;
        }
        editor.apply();
    }

    /**
     * to identify type of alert to apply 'not show again' function
     */
    public enum AlertDialogsEnum
    {
        MAIN, RANDOMIZER, GAME
    }
}
