package com.keusmar.cslabs_hackathon.Data;

import com.keusmar.cslabs_hackathon.Models.Answer;
import java.util.ArrayList;

public class GameImpactData {

    private static ArrayList<Answer> answers = new ArrayList<>();

    public static ArrayList<Answer> getAnswers() {
        return answers;
    }

    public static void setAnswers(ArrayList<Answer> answers) {
        GameImpactData.answers = answers;
    }

    public GameImpactData(){}

    public static void addAnswer(Answer answer){
        answers.add(answer);
    }
}