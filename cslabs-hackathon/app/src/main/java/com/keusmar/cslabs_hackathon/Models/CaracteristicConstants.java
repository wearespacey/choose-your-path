package com.keusmar.cslabs_hackathon.Models;

import java.util.ArrayList;

public final class CaracteristicConstants {
    public static final String MONEY = "Money";
    public static final String HEALTH = "Health";
    public static final String FRIENDSHIP = "Friendship";
    public static final String EARTH = "Earth";
    public static final ArrayList<String> ALL_CARACTERISITCS = new ArrayList<String>(){
        {
            add(MONEY);
            add(HEALTH);
            add(FRIENDSHIP);
            add(EARTH);
        }
    };
}
