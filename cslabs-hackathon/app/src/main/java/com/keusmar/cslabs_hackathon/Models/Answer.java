package com.keusmar.cslabs_hackathon.Models;

public class Answer {
    private Action action;
    private boolean answer;

    public Answer(){}

    public Answer(Action action, boolean answer) {
        setAction(action);
        setAnswer(answer);
    }
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
