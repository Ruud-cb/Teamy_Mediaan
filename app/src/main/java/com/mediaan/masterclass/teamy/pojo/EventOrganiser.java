package com.mediaan.masterclass.teamy.pojo;

import com.google.gson.annotations.SerializedName;
import com.mediaan.masterclass.teamy.constants.EventConstants;

import java.io.Serializable;

public class EventOrganiser implements Serializable {

    @SerializedName(EventConstants.ORGANISER_NAME)
    private String name;

    @SerializedName(EventConstants.ORGANISER_SCORE)
    private int score;

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
