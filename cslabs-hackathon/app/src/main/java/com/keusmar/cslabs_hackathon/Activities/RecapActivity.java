package com.keusmar.cslabs_hackathon.Activities;

import android.app.ListActivity;
import android.os.Bundle;
import com.keusmar.cslabs_hackathon.Data.GameImpactData;
import com.keusmar.cslabs_hackathon.Models.ActionRecapAdapter;
import com.keusmar.cslabs_hackathon.Models.Answer;
import com.keusmar.cslabs_hackathon.R;

import java.util.ArrayList;

public class RecapActivity extends ListActivity {

    private ArrayList<Answer> answers;
    private ActionRecapAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recap);

        answers = GameImpactData.getAnswers();
        adapter = new ActionRecapAdapter(this, answers);
        setListAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
}
