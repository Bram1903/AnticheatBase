package com.deathmotion.anticheatbase.api.event.impl;

import com.deathmotion.anticheatbase.api.models.ACUser;

/**
 * Called when an anticheat user quits the server.
 * <p>
 * This event does not include players with a bypass.
 */
public class ACUserQuitEvent extends ACUserEvent {

    /**
     * Constructs a new {@code ACUserQuitEvent}.
     *
     * @param user the {@link ACUser} who quit the server
     */
    public ACUserQuitEvent(ACUser user) {
        super(user);
    }
}
