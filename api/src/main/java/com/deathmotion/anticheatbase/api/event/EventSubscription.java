package com.deathmotion.anticheatbase.api.event;

/**
 * Handle returned by the EventBus to allow unsubscription.
 */
public interface EventSubscription {
    void unsubscribe();
}
