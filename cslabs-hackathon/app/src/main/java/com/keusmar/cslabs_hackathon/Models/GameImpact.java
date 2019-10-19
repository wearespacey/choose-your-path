package com.keusmar.cslabs_hackathon.Models;

public class GameImpact {
    private String caracteristic;
    private Float maxPoints;
    private Float actualPoints;

    public GameImpact(String caracteristic, Float maxPoints, Float actualPoints){
        setCaracteristic(caracteristic);
        setMaxPoints(maxPoints);
        setActualPoints(actualPoints);
    }

    public String getCaracteristic() {
        return caracteristic;
    }

    public void setCaracteristic(String caracteristic) {
        this.caracteristic = caracteristic;
    }

    public Float getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(Float maxPoints) {
        this.maxPoints = maxPoints;
    }

    public Float getActualPoints() {
        return actualPoints;
    }

    public void setActualPoints(Float actualPoints) {
        this.actualPoints = actualPoints;
    }
}
