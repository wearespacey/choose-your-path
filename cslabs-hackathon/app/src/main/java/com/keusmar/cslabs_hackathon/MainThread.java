package com.keusmar.cslabs_hackathon;

import android.content.Context;

import com.keusmar.cslabs_hackathon.views.MainView;

public class MainThread implements Runnable {
    private MainView mainView;
    private Context context;

    public MainThread(MainView mainView, Context context) {
        this.mainView = mainView;
        this.context = context;
    }

    public void run() {
        int i = 0;
        while (true) {
            try {
                mainView.refresh();
                Thread.sleep(60);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
