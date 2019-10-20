package com.keusmar.cslabs_hackathon.Models;

import java.util.ArrayList;

public class Action {
    private String content;
    private CategorieEnum.CategorieEnumType category;

    public CategorieEnum.CategorieEnumType getCategory() {
        return category;
    }

    public void setCategory(String category) {
        switch (category){
            case "Clothe":
                this.category = CategorieEnum.CategorieEnumType.CLOTHE;
                break;
            case "Transport":
                this.category = CategorieEnum.CategorieEnumType.TRANSPORT;
                break;
            case "Biodiversity":
                this.category = CategorieEnum.CategorieEnumType.BIODIVERSITY;
                break;
            default:
                this.category = CategorieEnum.CategorieEnumType.FOOD;
                break;
        }
    }

    private ArrayList<Impact> impacts;

    public Action(){this.impacts = new ArrayList<>();}

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

    public void addImpact(Impact impact){this.impacts.add(impact);}
}
