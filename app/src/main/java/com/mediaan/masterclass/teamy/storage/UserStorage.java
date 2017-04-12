package com.mediaan.masterclass.teamy.storage;

import com.mediaan.masterclass.teamy.pojo.EventOrganiser;

/**
 * Created by Ruud on 12-4-2017.
 */

public class UserStorage {

    public EventOrganiser GetCurrentUser(){
        //simply get current organiser
        EventOrganiser organiser = new EventOrganiser();
        organiser.setName("Ruud");
        organiser.setScore(57);
        return organiser;
    }
}
