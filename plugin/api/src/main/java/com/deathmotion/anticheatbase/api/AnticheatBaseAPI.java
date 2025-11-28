package com.deathmotion.anticheatbase.api;

import com.deathmotion.anticheatbase.api.event.EventBus;
import com.deathmotion.anticheatbase.api.versioning.ACVersion;
import org.jetbrains.annotations.NotNull;

/**
 * This is the main API class for the AntiCheatBase.
 */
public interface AnticheatBaseAPI {
    /**
     * Get the version of the anticheat.
     *
     * @return The version of the anticheat.
     */
    @NotNull ACVersion getVersion();

    /**
     * Get the event bus of the anticheat.
     *
     * @return The event bus of the anticheat.
     */
    @NotNull EventBus getEventBus();
}