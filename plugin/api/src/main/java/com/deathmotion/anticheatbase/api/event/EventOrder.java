package com.deathmotion.anticheatbase.api.event;

/**
 * Defines the order in which event listeners are called when an event is fired
 * on the event bus.
 * <p>
 * Multiple listeners can listen to the same event, and this enumeration
 * determines the relative execution order among them. Listeners with earlier
 * orders are invoked before those with later ones.
 * </p>
 *
 * <p><b>Execution order:</b></p>
 * <ol>
 *   <li>{@link #FIRST} – Called before all other listeners.</li>
 *   <li>{@link #EARLY} – Called after {@code FIRST} listeners but before {@code NORMAL}.</li>
 *   <li>{@link #NORMAL} – Default listener order.</li>
 *   <li>{@link #LATE} – Called after {@code NORMAL} listeners but before {@code LAST}.</li>
 *   <li>{@link #LAST} – Called after all other listeners.</li>
 * </ol>
 */
public enum EventOrder {
    /**
     * Called before all other listeners.
     */
    FIRST,

    /**
     * Called early, before {@link #NORMAL} listeners but after {@link #FIRST}.
     */
    EARLY,

    /**
     * Default order for most listeners.
     */
    NORMAL,

    /**
     * Called late, after {@link #NORMAL} listeners but before {@link #LAST}.
     */
    LATE,

    /**
     * Called after all other listeners.
     */
    LAST
}
