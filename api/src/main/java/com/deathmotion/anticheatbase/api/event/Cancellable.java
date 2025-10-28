package com.deathmotion.anticheatbase.api.event;

/**
 * Represents an event that listeners can cancel in the event bus system.
 */
public interface Cancellable {

    /**
     * Checks whether this event has been canceled.
     *
     * @return {@code true} if the event is canceled and should not be processed further,
     * {@code false} otherwise
     */
    boolean isCancelled();

    /**
     * Sets the cancellation state of this event.
     *
     * @param cancelled {@code true} to mark the event as canceled, {@code false} to unmark it
     */
    void setCancelled(boolean cancelled);
}
