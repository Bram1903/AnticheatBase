package com.deathmotion.anticheatbase.api.models;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Represents a user within the AntiCheat system.
 */
public interface ACUser {
    /**
     * Returns the unique identifier (UUID) of this user.
     *
     * @return the UUID of the user.
     */
    @NotNull UUID getUuid();

    /**
     * Returns the username of this user.
     *
     * @return the user's name as a non-null string.
     */
    @NotNull String getName();
}
