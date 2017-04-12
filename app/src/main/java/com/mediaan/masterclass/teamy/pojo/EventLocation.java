package com.mediaan.masterclass.teamy.pojo;

import com.google.gson.annotations.SerializedName;
import com.mediaan.masterclass.teamy.constants.EventConstants;

import java.io.Serializable;

public class EventLocation implements Serializable {

    @SerializedName(EventConstants.LOCATION_NAME)
    private String name;

    @SerializedName(EventConstants.LOCATION_DISTANCE)
    private double distance;

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
