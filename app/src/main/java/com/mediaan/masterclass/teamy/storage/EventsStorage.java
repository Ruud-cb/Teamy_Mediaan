package com.mediaan.masterclass.teamy.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mediaan.masterclass.teamy.BuildConfig;
import com.mediaan.masterclass.teamy.constants.EventConstants;
import com.mediaan.masterclass.teamy.pojo.Event;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;

import okio.BufferedSource;
import okio.Okio;

public class EventsStorage {
    private static final String PREF_KEY = "events_list";
    private static final String FILENAME = "teamy_events.json";
    private static final Charset CHARSET = Charset.forName("utf-8");

    final Type eventsListType = new TypeToken<List<Event>>(){}.getType();

    private AssetManager assetManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public EventsStorage(Context context) {
        assetManager = context.getAssets();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        gson = new GsonBuilder()
                .registerTypeAdapter(eventsListType, new ListDeserializer<List<Event>>(EventConstants.LIST))
                .create();
    }

    public List<Event> getEvents() {
        final String configFromPrefs = sharedPreferences.getString(PREF_KEY, null);
        if (configFromPrefs == null) {
            final List<Event> eventList = getLocalEventsList();
            if (eventList != null) {
                saveEventsList(eventList);
            }
            return eventList;
        } else {
            try {
                return parseEventsList(configFromPrefs);
            } catch (Exception e) {
                if (BuildConfig.DEBUG) {
                    Log.e(this.getClass().getSimpleName(), "Saved events parse error", e);
                }
                return null;
            }
        }
    }

    public void saveEventsList(@NonNull final List<Event> eventList) {
        JsonObject base = new JsonObject();
        base.add(EventConstants.LIST, gson.toJsonTree(eventList));
        sharedPreferences.edit().putString(PREF_KEY, gson.toJson(base)).apply();
    }

    private List<Event> getLocalEventsList() {
        try {
            final InputStream inputStream = assetManager.open(FILENAME);
            final BufferedSource configSource = Okio.buffer(Okio.source(inputStream));
            final String contentAsString = configSource.readString(CHARSET);
            return parseEventsList(contentAsString);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                Log.e(this.getClass().getSimpleName(), "Local events parse error", e);
            }
            return null;
        }
    }

    private List<Event> parseEventsList(@NonNull final String json) throws Exception {
        return gson.fromJson(json, eventsListType);
    }
}
