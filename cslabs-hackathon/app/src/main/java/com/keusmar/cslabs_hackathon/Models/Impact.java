package com.keusmar.cslabs_hackathon.Models;

public class Impact {
    private Float points;
    private String caracteristic;

    public Impact(){}

    public Impact(Float points, String caracteristic) {
        setPoints(points);
        setCaracteristic(caracteristic);
    }

    public Float getPoints() {
        return points;
    }

    public void setPoints(Float points) {
        this.points = points;
    }

    public String getCaracteristic() {
        return caracteristic;
    }

    public void setCaracteristic(String caracteristic) {
        this.caracteristic = caracteristic;
    }
}
