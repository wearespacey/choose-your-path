package com.keusmar.cslabs_hackathon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainView mainView = new MainView(this);
        MainThread mainThread = new MainThread(mainView, this);
        Thread thread = new Thread(mainThread);
        thread.start();
        setContentView(mainView);
    }
}
