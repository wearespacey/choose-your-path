package com.keusmar.cslabs_hackathon.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.keusmar.cslabs_hackathon.Models.Character;
import com.keusmar.cslabs_hackathon.Data.GameImpactData;
import com.keusmar.cslabs_hackathon.Data.ReadData;
import com.keusmar.cslabs_hackathon.MainThread;
import com.keusmar.cslabs_hackathon.views.MainView;
import com.keusmar.cslabs_hackathon.Models.Action;
import com.keusmar.cslabs_hackathon.Models.Answer;
import com.keusmar.cslabs_hackathon.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Integer currentPosition = 0;
    private ArrayList<Action> actions;
    private TextView questionContainer;
    private MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReadData readData = new ReadData(this);
        actions = readData.getActions();
        GameImpactData gid = new GameImpactData();
        Intent intent = getIntent();

        mainView = new MainView(this, (Character.CharacterColor)intent.getSerializableExtra(ChooseCharacterActivity.COLOR));
        mainView.setActions(actions);
        MainThread mainThread = new MainThread(mainView, this);
        Thread thread = new Thread(mainThread);
        //thread.start();
        LinearLayout linearLayout = findViewById(R.id.yeet);
        questionContainer = findViewById(R.id.questionContainer);
        questionContainer = findViewById(R.id.questionContainer);
        questionContainer.setText(getCurrentAction().getContent());
        questionContainer.setTextSize(20);

        ImageView yesButton = findViewById(R.id.yesButton);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerAction(questionContainer, actions.get(currentPosition), true);
            }
        });
        ImageView noButton = findViewById(R.id.noButton);
        noButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                answerAction(questionContainer, actions.get(currentPosition), false);
            }
        });

        linearLayout.addView(mainView);
    }

    private void answerAction(TextView questionContainer, Action action, boolean response){
        GameImpactData.addAnswer(new Answer(action, response));
        mainView.updateStatus(action, response);
        nextAction();
        if(currentPosition < actions.size())
            questionContainer.setText(getCurrentAction().getContent());
    }

    public Action getCurrentAction() {
        Action currentAction = this.actions.get(currentPosition);
        return currentAction;
    }
    public void nextAction() {
        if (currentPosition < actions.size()-1) {
            currentPosition++;
            if((currentPosition+1)%4==0)
                this.mainView.incrementDayNumber();
        }
    }
}
