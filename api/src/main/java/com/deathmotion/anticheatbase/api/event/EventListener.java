package com.deathmotion.anticheatbase.api.event;

@FunctionalInterface
public interface EventListener<T extends Event> {
    void onEvent(T event);
}
