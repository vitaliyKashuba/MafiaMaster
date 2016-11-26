package vitaliy94.mafiamaster;

import java.io.Serializable;

public class Player implements Serializable
{
    private String name;
    private Roles role;
    private Status status;

    public Player(String name, Roles role, Status status)
    {
        this.name = name;
        this.role = role;
        this.status = status;
    }

    public String getName()
    {
        return name;
    }

    public Roles getRole()
    {
        return role;
    }

    public Status getStatus()
    {
        return status;
    }
}
