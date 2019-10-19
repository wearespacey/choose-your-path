package com.keusmar.cslabs_hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceView;

import com.keusmar.cslabs_hackathon.Data.GameImpactData;
import com.keusmar.cslabs_hackathon.Data.ReadData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameImpactData gid = new GameImpactData();
        MainView mainView = new MainView(this);
        MainThread mainThread = new MainThread(mainView, this);
        Thread thread = new Thread(mainThread);
        thread.start();
        setContentView(mainView);
    }
}
