package com.keusmar.cslabs_hackathon.Models;

import java.util.ArrayList;

public class Action {
    private String content;
    private ArrayList<Impact> impacts;

    public Action(String content, ArrayList<Impact> impacts) {
        setContent(content);
        setImpacts(impacts);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Impact> getImpacts() {
        return impacts;
    }

    public void setImpacts(ArrayList<Impact> impacts) {
        this.impacts = impacts;
    }
}
