package com.deathmotion.anticheatbase.api.event.impl;

import com.deathmotion.anticheatbase.api.models.ACUser;

/**
 * Called when an anticheat user joins the server.
 * <p>
 * This event does not include players with a bypass.
 */
public class ACUserJoinEvent extends ACUserEvent {

    /**
     * Constructs a new {@code ACUserJoinEvent}.
     *
     * @param user the {@link ACUser} who joined the server
     */
    public ACUserJoinEvent(ACUser user) {
        super(user);
    }
}
