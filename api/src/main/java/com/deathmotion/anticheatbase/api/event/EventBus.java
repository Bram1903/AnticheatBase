package com.deathmotion.anticheatbase.api.event;

import org.jetbrains.annotations.NotNull;

public interface EventBus {

    /**
     * Subscribe to a specific event type with an explicit order.
     */
    <T extends Event> @NotNull EventSubscription subscribe(
            @NotNull Class<T> eventType,
            @NotNull EventOrder order,
            @NotNull EventListener<? super T> listener
    );

    /**
     * Subscribe to a specific event type with the default order = NORMAL.
     */
    default <T extends Event> @NotNull EventSubscription subscribe(
            @NotNull Class<T> eventType,
            @NotNull EventListener<? super T> listener
    ) {
        return subscribe(eventType, EventOrder.NORMAL, listener);
    }

    /**
     * Subscribe to every event (wildcard) with an explicit order.
     */
    @NotNull EventSubscription subscribeAll(
            @NotNull EventOrder order,
            @NotNull EventListener<? super Event> listener
    );

    /**
     * Subscribe to every event with the default order = NORMAL.
     */
    default @NotNull EventSubscription subscribeAll(
            @NotNull EventListener<? super Event> listener
    ) {
        return subscribeAll(EventOrder.NORMAL, listener);
    }

    /**
     * Unsubscribe by instance (type + order specific).
     *
     * @return true if a listener was removed.
     */
    <T extends Event> boolean unsubscribe(
            @NotNull Class<T> eventType,
            @NotNull EventOrder order,
            @NotNull EventListener<? super T> listener
    );

    /**
     * Unsubscribe by instance with default order = NORMAL.
     */
    default <T extends Event> boolean unsubscribe(
            @NotNull Class<T> eventType,
            @NotNull EventListener<? super T> listener
    ) {
        return unsubscribe(eventType, EventOrder.NORMAL, listener);
    }

    /**
     * Unsubscribe a wildcard listener by instance (order-specific).
     */
    boolean unsubscribeAll(
            @NotNull EventOrder order,
            @NotNull EventListener<? super Event> listener
    );

    /**
     * Unsubscribe a wildcard listener by instance with default order = NORMAL.
     */
    default boolean unsubscribeAll(
            @NotNull EventListener<? super Event> listener
    ) {
        return unsubscribeAll(EventOrder.NORMAL, listener);
    }
}
