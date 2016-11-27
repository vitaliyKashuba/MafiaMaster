package vitaliy94.mafiamaster.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import vitaliy94.mafiamaster.entitys.Roles;

public class Randomizer
{
    /**
     * mix roles in array list
     * used because when array list firstly filled all active roles are first, then mafia, then citizens
     * @param roles Array list with roles
     */
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
