package com.mediaan.masterclass.teamy.storage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;

public class ListDeserializer<T> implements JsonDeserializer<List<T>> {

    private final String listName;

    public ListDeserializer(final String listName) {
        this.listName = listName;
    }

    @Override
    public List<T> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext deserializationContext) throws JsonParseException {
        final JsonArray list = jsonElement.getAsJsonObject().getAsJsonArray(listName);
        // Use a new instance of Gson here to avoid infinite recursion to this deserializer
        return new Gson().fromJson(list, type);
    }
}
