package com.deathmotion.anticheatbase.api.event.impl;

import com.deathmotion.anticheatbase.api.event.Event;
import com.deathmotion.anticheatbase.api.models.ACUser;

/*
    An event that includes an anticheat user
 */
public abstract class ACUserEvent extends Event {

    public final ACUser user;

    protected ACUserEvent(ACUser user) {
        this.user = user;
    }
}
