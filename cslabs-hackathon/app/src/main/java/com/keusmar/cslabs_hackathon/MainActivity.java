package com.keusmar.cslabs_hackathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.keusmar.cslabs_hackathon.Data.GameImpactData;
import com.keusmar.cslabs_hackathon.Data.ReadData;
import com.keusmar.cslabs_hackathon.Models.Action;
import com.keusmar.cslabs_hackathon.Models.Answer;
import com.keusmar.cslabs_hackathon.Models.CaracteristicConstants;
import com.keusmar.cslabs_hackathon.Models.GameImpact;
import com.keusmar.cslabs_hackathon.Models.Impact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Integer currentPosition = 0;
    private ArrayList<Action> actions;
    private ArrayList<Answer> answers = new ArrayList<Answer>();
    private TextView questionContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ReadData readData = new ReadData(this);
        actions = readData.getActions();
        GameImpactData gid = new GameImpactData();
        MainView mainView = new MainView(this);
        mainView.setActions(actions);
        MainThread mainThread = new MainThread(mainView, this);
        Thread thread = new Thread(mainThread);
        thread.start();
        LinearLayout linearLayout = findViewById(R.id.yeet);
        questionContainer = findViewById(R.id.questionContainer);
        questionContainer.setText(getCurrentAction().getContent());
        questionContainer.setTextSize(15);

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
        answers.add(new Answer(action, response));
        nextAction();
        if(currentPosition < actions.size())
            questionContainer.setText(getCurrentAction().getContent());
    }

    public Action getCurrentAction() {
        Action currentAction = this.actions.get(currentPosition);
        return currentAction;
    }
    public void nextAction() {
        currentPosition++;
    }
}
