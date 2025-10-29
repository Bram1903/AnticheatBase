package com.deathmotion.anticheatbase.api.event;

public abstract class Event {

    public String getName() {
        return getClass().getSimpleName();
    }
}
