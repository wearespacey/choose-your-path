package com.keusmar.cslabs_hackathon.Models;

public class Impact {
    private String caracteristic;
    private Float pointsYes;
    private Float pointsNo;

    public Impact(){}

    public Impact(String caracteristic) {
        setCaracteristic(caracteristic);
    }

    public String getCaracteristic() {
        return caracteristic;
    }

    public void setCaracteristic(String caracteristic) {
        this.caracteristic = caracteristic;
    }

    public Float getPointsYes() {
        return pointsYes;
    }

    public void setPointsYes(Float pointsYes) {
        this.pointsYes = pointsYes;
    }

    public Float getPointsNo() {
        return pointsNo;
    }

    public void setPointsNo(Float pointsNo) {
        this.pointsNo = pointsNo;
    }
}
