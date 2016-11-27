package vitaliy94.mafiamaster.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PreferenceSaver extends AppCompatActivity
{
    public static final String PREFERENCES = "settings";
    public static final String PREFERENCES_SHOW_INTRO_1 = "intro1";
    public static final String PREFERENCES_SHOW_INTRO_2 = "intro2";
    public static final String PREFERENCES_SHOW_INTRO_3 = "intro3";
    public boolean showIntro1 = true;
    boolean showIntro2, showIntro3;

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

        showIntro1 = settings.getBoolean(PREFERENCES_SHOW_INTRO_1, true);
        showIntro2 = settings.getBoolean(PREFERENCES_SHOW_INTRO_2, true);
        showIntro3 = settings.getBoolean(PREFERENCES_SHOW_INTRO_3, true);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        showIntro1 = settings.getBoolean(PREFERENCES_SHOW_INTRO_1, true);
        showIntro2 = settings.getBoolean(PREFERENCES_SHOW_INTRO_2, true);
        showIntro3 = settings.getBoolean(PREFERENCES_SHOW_INTRO_3, true);
    }

    static void notShowAgain()
    {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(PREFERENCES_SHOW_INTRO_1, false);
        editor.apply();
    }

}
