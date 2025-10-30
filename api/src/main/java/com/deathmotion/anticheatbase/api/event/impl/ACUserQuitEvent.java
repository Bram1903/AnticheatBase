package com.deathmotion.anticheatbase.api.event.impl;

import com.deathmotion.anticheatbase.api.models.ACUser;

/*
    Called when an anticheat user quit the server (doesn't include players with a bypass)
 */
public class ACUserQuitEvent extends ACUserEvent {

    public ACUserQuitEvent(ACUser user) {
        super(user);
    }
}
