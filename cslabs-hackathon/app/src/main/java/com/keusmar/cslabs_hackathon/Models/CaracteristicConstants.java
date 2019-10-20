package com.keusmar.cslabs_hackathon.Models;

import java.util.ArrayList;

public final class CaracteristicConstants {
    public static final String ECONOMY = "Economy";
    public static final String ECOLOGY = "Ecology";
    public static final String COMFORT = "Comfort";
    public static final ArrayList<String> ALL_CARACTERISITCS = new ArrayList<String>(){
        {
            add(ECONOMY);
            add(ECOLOGY);
            add(COMFORT);
        }
    };
}
