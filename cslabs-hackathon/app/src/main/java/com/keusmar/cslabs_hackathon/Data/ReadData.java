package com.keusmar.cslabs_hackathon.Data;


import androidx.appcompat.app.AppCompatActivity;

import com.keusmar.cslabs_hackathon.Models.Action;
import com.keusmar.cslabs_hackathon.Models.Impact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ReadData {

    private AppCompatActivity app;
    private final static String FILE_NAME = "Actions.json";

    public ReadData(AppCompatActivity app){this.app = app;}

    public ArrayList<Action> getActions(){
        ArrayList<Action> actions = new ArrayList<>();
        try {
            JSONObject jObject = new JSONObject(readJson());
            JSONArray jArray = jObject.getJSONArray("Actions");
            Action currentAction = null;
            Impact currentImpact = null;

            for (int i = 0; i < jArray.length(); ++i) {
                currentAction = new Action();
                currentAction.setContent(jArray.getJSONObject(i).getString("Content"));
                JSONArray impacts = jArray.getJSONObject(i).getJSONArray("Impacts");
                for(int u = 0; u < impacts.length(); u++){
                    currentImpact = new Impact();
                    currentImpact.setCaracteristic(impacts.getJSONObject(u).getString("Caracteristic"));
                    currentImpact.setPoints(Float.valueOf(impacts.getJSONObject(u).getString("Points")));
                    currentAction.addImpact((currentImpact));
                }
                actions.add(currentAction);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return actions;
    }

    private String readJson() {
        String json = null;
        try {
            InputStream is = app.getAssets().open(FILE_NAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
