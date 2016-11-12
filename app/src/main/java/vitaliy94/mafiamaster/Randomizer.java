package vitaliy94.mafiamaster;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Randomizer
{
    public static void randomize(ArrayList<Roles> roles)
    {
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < roles.size(); i++)
        {
            int rnd = random.nextInt(roles.size());
            Roles r = roles.remove(rnd);
            roles.add(r);
        }

        for (Roles r : roles)
        {
            Log.d("roles", r.toString());
        }
    }
}
