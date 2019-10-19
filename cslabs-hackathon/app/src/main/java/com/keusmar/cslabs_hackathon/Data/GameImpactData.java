package com.keusmar.cslabs_hackathon.Data;

import com.keusmar.cslabs_hackathon.Models.CaracteristicConstants;
import com.keusmar.cslabs_hackathon.Models.GameImpact;
import java.util.ArrayList;

public class GameImpactData {

    private  ArrayList<GameImpact> gameImpacts;
    private final Float MAX_POINTS = 100.0f;

    public GameImpactData(){
        this.gameImpacts = new ArrayList<GameImpact>(){
            {
                for(String caract:CaracteristicConstants.ALL_CARACTERISITCS){
                    add(new GameImpact(caract, MAX_POINTS, MAX_POINTS));
                }
            }
        };
    }

    public  ArrayList<GameImpact> getGameImpacts() {
        return gameImpacts;
    }

    public  void setGameImpacts(ArrayList<GameImpact> impacts) {
        this.gameImpacts = impacts;
    }


    public  void applyGameImpact(String impactName, Float value) {
        for (GameImpact g: getGameImpacts()) {
            if(g.getCaracteristic().equals(impactName))
                g.setActualPoints(g.getActualPoints()+value);
        }
    }
}