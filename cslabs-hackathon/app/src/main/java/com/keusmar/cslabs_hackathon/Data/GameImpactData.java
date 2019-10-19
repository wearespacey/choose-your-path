package com.keusmar.cslabs_hackathon.Data;

import com.keusmar.cslabs_hackathon.Models.CaracteristicConstants;
import com.keusmar.cslabs_hackathon.Models.GameImpact;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        this.saveGameImpacts();
    }

    public  ArrayList<GameImpact> getGameImpacts() {
        return gameImpacts;
    }

    public  void setGameImpacts(ArrayList<GameImpact> impacts) {
        this.gameImpacts = impacts;
    }

    public  void loadGameImpacts() {
        try {
            FileInputStream fis = new FileInputStream("GameImpacts.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            gameImpacts = (ArrayList<GameImpact>)ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  void saveGameImpacts() {
        try {
            FileOutputStream fos = new FileOutputStream("GameImpacts.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(getGameImpacts());
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void applyGameImpact(String impactName, Float value) {
        for (GameImpact g: getGameImpacts()) {
            if(g.getCaracteristic().equals(impactName))
                g.setActualPoints(g.getActualPoints()+value);
        }
    }
}