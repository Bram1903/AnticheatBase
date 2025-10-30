package com.deathmotion.anticheatbase.api.event.impl;

import com.deathmotion.anticheatbase.api.models.ACUser;

/*
    Called when an anticheat user joins the server (doesn't include players with a bypass)
 */
public class ACUserJoinEvent extends ACUserEvent {

    public ACUserJoinEvent(ACUser user) {
        super(user);
    }
}
