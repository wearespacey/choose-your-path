package com.keusmar.cslabs_hackathon.Data;

import com.keusmar.cslabs_hackathon.Models.GameImpact;
import com.keusmar.cslabs_hackathon.Models.Impact;

import java.util.ArrayList;

public class GlobalImpactData {

    public static ArrayList<GameImpact> getGlobalImpacts() {
        return null;
    }

    public static void setGlobalImpacts() {

    }

    public static void applyGlobalImpact(String impactName, Float value) {
        ArrayList<GameImpact> impacts = getGlobalImpacts();
        for (GameImpact g:impacts) {
            if(g.getCaracteristic().equals(impactName))
                g.setActualPoints(g.getActualPoints()+value);
        }
    }
}
