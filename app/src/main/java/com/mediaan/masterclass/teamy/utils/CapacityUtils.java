package com.mediaan.masterclass.teamy.utils;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.mediaan.masterclass.teamy.R;
import com.mediaan.masterclass.teamy.pojo.Event;

public final class CapacityUtils {
    private CapacityUtils() {}

    @DrawableRes
    public static int getCapacityDrawable(@NonNull final Event event, final boolean horizontal) {
        final int min = event.getMinParticipants();
        final int max = event.getMaxParticipants();
        final int current = event.getCurrentParticipants();

        if (max == current) {
            return horizontal ? R.drawable.bar_hor_full : R.drawable.bar_full;
        }

        if (current == 0) {
            return horizontal ? R.drawable.bar_hor_empty :  R.drawable.bar_empty;
        }

        if (current >= min) {
            return horizontal ? R.drawable.bar_hor_75 :  R.drawable.bar_75;
        }

        final float percentage = (float)current / min;
        if (percentage >= 0.45f) {
            return horizontal ? R.drawable.bar_hor_50 :  R.drawable.bar_50;
        }

        return horizontal ? R.drawable.bar_hor_25 :  R.drawable.bar_25;
    }

    @ColorRes
    public static int getCapacityColor(final Event event) {
        final int min = event.getMinParticipants();
        final int max = event.getMaxParticipants();
        final int current = event.getCurrentParticipants();

        if (current == 0 || max == current) {
            return R.color.participants_grey;
        }

        if (current >= min) {
            return R.color.participants_green;
        }

        final float percentage = (float)current / min;

        if (percentage >= 0.45f) {
            return R.color.participants_orange;
        }

        return R.color.participants_red;
    }
}
