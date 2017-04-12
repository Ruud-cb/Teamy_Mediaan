package com.mediaan.masterclass.teamy.pojo;

import android.support.annotation.DrawableRes;

import com.google.gson.annotations.SerializedName;
import com.mediaan.masterclass.teamy.R;
import com.mediaan.masterclass.teamy.constants.EventConstants;

public enum EventType {
    @SerializedName("" + EventConstants.TYPE_SOCCER)
    SOCCER(EventConstants.TYPE_SOCCER, R.drawable.ic_soccer),

    @SerializedName("" + EventConstants.TYPE_TABLETENNIS)
    TABLETENNIS(EventConstants.TYPE_TABLETENNIS, R.drawable.ic_tabletennis),

    @SerializedName("" + EventConstants.TYPE_BASKETBALL)
    BASKETBALL(EventConstants.TYPE_BASKETBALL, R.drawable.ic_basketball),

    @SerializedName("" + EventConstants.TYPE_CYCLING)
    CYCLING(EventConstants.TYPE_CYCLING, R.drawable.ic_cycling),

    @SerializedName("" + EventConstants.TYPE_TENNIS)
    TENNIS(EventConstants.TYPE_TENNIS, R.drawable.ic_tennis),

    @SerializedName("" + EventConstants.TYPE_GOLF)
    GOLF(EventConstants.TYPE_GOLF, R.drawable.ic_golf);

    private final int type;
    @DrawableRes private final int icon;

    EventType(final int type, @DrawableRes final int icon) {
        this.type = type;
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    @DrawableRes
    public int getIcon() {
        return icon;
    }
}
