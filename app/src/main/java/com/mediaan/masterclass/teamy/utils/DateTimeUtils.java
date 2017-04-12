package com.mediaan.masterclass.teamy.utils;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateTimeUtils {
    private DateTimeUtils() { }

    private static final String FORMAT_HOURS = "HH:mm";
    private static final String FORMAT_DATE = "DD mmm yyyy";
    private static final SimpleDateFormat DATEFORMAT_HOURS = new SimpleDateFormat(FORMAT_HOURS, Locale.getDefault());
    private static final SimpleDateFormat DATEFORMAT_DATE = new SimpleDateFormat(FORMAT_DATE, Locale.getDefault());

    @NonNull
    public static String formatToHours(@NonNull final Date datetime) {
        return DATEFORMAT_HOURS.format(datetime);
    }

    @NonNull
    public static String formatToDate(@NonNull final Date datetime) {
        return DATEFORMAT_DATE.format(datetime);
    }
}
