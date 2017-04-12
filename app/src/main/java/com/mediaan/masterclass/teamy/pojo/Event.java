package com.mediaan.masterclass.teamy.pojo;

import com.google.gson.annotations.SerializedName;
import com.mediaan.masterclass.teamy.constants.EventConstants;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {

    @SerializedName(EventConstants.TITLE)
    private String title;

    @SerializedName(EventConstants.DESCRIPTION)
    private String description;

    @SerializedName(EventConstants.TYPE)
    private EventType type;

    @SerializedName(EventConstants.START)
    private Date start;

    @SerializedName(EventConstants.END)
    private Date end;

    @SerializedName(EventConstants.LOCATION)
    private EventLocation location;

    @SerializedName(EventConstants.ORGANISER)
    private EventOrganiser organiser;

    @SerializedName(EventConstants.MIN_PARTICIPANTS)
    private int minParticipants;

    @SerializedName(EventConstants.MAX_PARTICIPANTS)
    private int maxParticipants;

    @SerializedName(EventConstants.CURRENT_PARTICIPANTS)
    private int currentParticipants;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public EventType getType() {
        return type;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public EventLocation getLocation() {
        return location;
    }

    public EventOrganiser getOrganiser() {
        return organiser;
    }

    public int getMinParticipants() {
        return minParticipants;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public int getCurrentParticipants() {
        return currentParticipants;
    }
}
