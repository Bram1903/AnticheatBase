package com.deathmotion.anticheatbase.api.event.impl;

import com.deathmotion.anticheatbase.api.event.Event;
import com.deathmotion.anticheatbase.api.models.ACUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents an event that involves an {@link ACUser}.
 * <p>
 * This is a base class for all anticheat events that are associated
 * with a specific user.
 */
@Getter
@RequiredArgsConstructor
public abstract class ACUserEvent extends Event {

    /**
     * The anticheat user involved in this event.
     */
    private final ACUser user;
}
