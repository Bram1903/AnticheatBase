package com.deathmotion.anticheatbase.api.event;

import org.jetbrains.annotations.NotNull;

public abstract class Event {

    public @NotNull String getName() {
        return getClass().getSimpleName();
    }
}
