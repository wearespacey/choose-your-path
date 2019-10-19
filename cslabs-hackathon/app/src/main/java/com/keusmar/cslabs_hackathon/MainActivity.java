package com.keusmar.cslabs_hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.keusmar.cslabs_hackathon.Data.GameImpactData;
import com.keusmar.cslabs_hackathon.Data.ReadData;
import com.keusmar.cslabs_hackathon.Models.Action;
import com.keusmar.cslabs_hackathon.Models.CaracteristicConstants;
import com.keusmar.cslabs_hackathon.Models.GameImpact;
import com.keusmar.cslabs_hackathon.Models.Impact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MainView(this));
        ReadData rd = new ReadData(this);
        ArrayList<Action> actions = rd.getActions();
        GameImpactData gid = new GameImpactData();

        for(Action action:actions){
            for(Impact impact:action.getImpacts()){
                gid.applyGameImpact(impact, true);
            }
        }

        ArrayList<GameImpact> impacts = gid.getGameImpacts();
        GameImpact earth = gid.getGameImpactByName(CaracteristicConstants.EARTH);
        GameImpact test = gid.getGameImpactByName("Yeet");

    }
}
