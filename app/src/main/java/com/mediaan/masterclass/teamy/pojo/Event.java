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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setLocation(EventLocation location) {
        this.location = location;
    }

    public void setOrganiser(EventOrganiser organiser) {
        this.organiser = organiser;
    }

    public void setMinParticipants(int minParticipants) {
        this.minParticipants = minParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setCurrentParticipants(int currentParticipants) {
        this.currentParticipants = currentParticipants;
    }
}
