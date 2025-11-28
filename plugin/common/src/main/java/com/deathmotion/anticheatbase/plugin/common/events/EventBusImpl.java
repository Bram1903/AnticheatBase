package com.deathmotion.anticheatbase.plugin.common.events;

import com.deathmotion.anticheatbase.api.event.*;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBusImpl implements EventBus {

    private final Map<Class<?>, Map<EventOrder, CopyOnWriteArrayList<EventListener<? super Event>>>> listeners = new ConcurrentHashMap<>();
    private final Map<Key, EventListener<? super Event>> boxedByKey = new ConcurrentHashMap<>();

    @Override
    public <T extends Event> @NotNull EventSubscription subscribe(
            @NotNull Class<T> eventType,
            @NotNull EventOrder order,
            @NotNull EventListener<? super T> listener
    ) {
        final Key key = new Key(eventType, order, listener);
        final EventListener<? super Event> boxed = boxedByKey.computeIfAbsent(key, k -> (Event e) -> listener.onEvent(eventType.cast(e)));

        Map<EventOrder, CopyOnWriteArrayList<EventListener<? super Event>>> perType = listeners.computeIfAbsent(eventType, k -> new EnumMap<>(EventOrder.class));
        CopyOnWriteArrayList<EventListener<? super Event>> bucket = perType.computeIfAbsent(order, k -> new CopyOnWriteArrayList<>());

        if (!bucket.contains(boxed)) bucket.add(boxed);

        return () -> {
            boolean removed = bucket.remove(boxed);
            if (removed) {
                boxedByKey.remove(key, boxed);
            }
        };
    }

    @Override
    public @NotNull EventSubscription subscribeAll(
            @NotNull EventOrder order,
            @NotNull EventListener<? super Event> listener
    ) {
        final Key key = new Key(Event.class, order, listener);
        final EventListener<? super Event> boxed = boxedByKey.computeIfAbsent(key, k -> listener);

        Map<EventOrder, CopyOnWriteArrayList<EventListener<? super Event>>> perType = listeners.computeIfAbsent(Event.class, k -> new EnumMap<>(EventOrder.class));
        CopyOnWriteArrayList<EventListener<? super Event>> bucket = perType.computeIfAbsent(order, k -> new CopyOnWriteArrayList<>());

        if (!bucket.contains(boxed)) bucket.add(boxed);

        return () -> {
            boolean removed = bucket.remove(boxed);
            if (removed) boxedByKey.remove(key, boxed);
        };
    }

    @Override
    public <T extends Event> boolean unsubscribe(
            @NotNull Class<T> eventType,
            @NotNull EventOrder order,
            @NotNull EventListener<? super T> listener
    ) {
        final Key key = new Key(eventType, order, listener);
        final EventListener<? super Event> boxed = boxedByKey.remove(key);
        if (boxed == null) return false;

        Map<EventOrder, CopyOnWriteArrayList<EventListener<? super Event>>> perType = listeners.get(eventType);
        if (perType == null) return false;

        CopyOnWriteArrayList<EventListener<? super Event>> bucket = perType.get(order);
        if (bucket == null) return false;

        return bucket.remove(boxed);
    }

    @Override
    public boolean unsubscribeAll(
            @NotNull EventOrder order,
            @NotNull EventListener<? super Event> listener
    ) {
        final Key key = new Key(Event.class, order, listener);
        final EventListener<? super Event> boxed = boxedByKey.remove(key);
        if (boxed == null) return false;

        Map<EventOrder, CopyOnWriteArrayList<EventListener<? super Event>>> perType = listeners.get(Event.class);
        if (perType == null) return false;

        CopyOnWriteArrayList<EventListener<? super Event>> bucket = perType.get(order);
        if (bucket == null) return false;

        return bucket.remove(boxed);
    }

    public @NotNull Event post(@NotNull Event event) {
        for (EventOrder order : EventOrder.values()) {
            dispatchBucketFor(event.getClass(), order, event);
            dispatchBucketFor(Event.class, order, event);
        }
        return event;
    }

    private void dispatchBucketFor(
            Class<?> key,
            EventOrder order,
            Event event
    ) {
        Map<EventOrder, CopyOnWriteArrayList<EventListener<? super Event>>> perType = listeners.get(key);
        if (perType == null) return;

        CopyOnWriteArrayList<EventListener<? super Event>> bucket = perType.get(order);
        if (bucket == null || bucket.isEmpty()) return;

        for (EventListener<? super Event> l : bucket) {
            l.onEvent(event);
        }
    }

    private static final class Key {
        private final Class<?> type;
        private final EventOrder order;
        private final EventListener<?> original;

        Key(Class<?> type, EventOrder order, EventListener<?> original) {
            this.type = Objects.requireNonNull(type, "type");
            this.order = Objects.requireNonNull(order, "order");
            this.original = Objects.requireNonNull(original, "original");
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key key = (Key) o;
            return type.equals(key.type) && order == key.order && original == key.original;
        }

        @Override
        public int hashCode() {
            int result = type.hashCode();
            result = 31 * result + order.hashCode();
            result = 31 * result + System.identityHashCode(original);
            return result;
        }
    }
}
