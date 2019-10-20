package com.keusmar.cslabs_hackathon.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.keusmar.cslabs_hackathon.R;

import java.util.ArrayList;

public class MoralActivity extends AppCompatActivity {
    private ArrayList<Integer> steps = new ArrayList<>();
    private ImageView step, next_button;
    private Button restart;
    private Integer current_step = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moral);

        steps.add(R.drawable.step1);
        steps.add(R.drawable.step2);
        steps.add(R.drawable.step3);

        step = findViewById(R.id.moral);
        next_button = findViewById(R.id.next_step);
        restart = findViewById(R.id.restart);

        step.setImageResource(steps.get(current_step));

        next_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                current_step++;

                if(current_step == steps.size()){
                    restart.setVisibility(View.VISIBLE);
                    next_button.setVisibility(View.INVISIBLE);
                }
                else
                    step.setImageResource(steps.get(current_step));
            }
        });

        restart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoralActivity.this, ChooseCharacterActivity.class);
                startActivity(intent);
            }
        });
    }
}
