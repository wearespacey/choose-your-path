package com.keusmar.cslabs_hackathon.Data;

import com.keusmar.cslabs_hackathon.Models.CaracteristicConstants;
import com.keusmar.cslabs_hackathon.Models.GameImpact;
import com.keusmar.cslabs_hackathon.Models.Impact;

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

    public GameImpact getGameImpactByName(String name){
        for(GameImpact gi: getGameImpacts()){
            if(gi.getCaracteristic().equals(name))
                return gi;
        }
        return null;
    }

    public  void applyGameImpact(Impact impact, boolean isApplied) {
        for (GameImpact g: getGameImpacts()) {
            if(g.getCaracteristic().equals(impact.getCaracteristic())){
                Float caractValue = g.getActualPoints() + (isApplied? impact.getPointsYes() : impact.getPointsNo());
                if(caractValue < 0)
                    g.setActualPoints(0.0f);
                else if(caractValue > MAX_POINTS)
                    g.setActualPoints(MAX_POINTS);
                else
                    g.setActualPoints(g.getActualPoints()+ (isApplied? impact.getPointsYes() : impact.getPointsNo()));
            }

        }
    }
}