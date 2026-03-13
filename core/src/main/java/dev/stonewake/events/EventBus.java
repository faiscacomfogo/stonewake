package dev.stonewake.events;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiPredicate;

public class EventBus {
    private final HashMap<Class<? extends Event>, List<Listener<? extends Event>>> listeners = new HashMap<>();

    public <T extends Event> void addListener(Class<T> eventClass, Listener<T> listener) {
        List<Listener<? extends Event>> list = listeners.computeIfAbsent(eventClass, k -> new ArrayList<>());
        list.add(listener);

        list.sort(Comparator.comparing(Listener::getPriority));
    }

    public <T extends Event> void removeListener(Class<T> eventClass, Listener<T> listener) {
        List<Listener<? extends Event>> list = listeners.get(eventClass);
        if (list != null) {
            list.remove(listener);
        }
    }

    public <T extends Event> void firePre(T event, BiPredicate<T, Listener<T>> condition) {
        List<Listener<? extends Event>> rawList = listeners.get(event.getClass());

        if (rawList == null) return;

        @SuppressWarnings("unchecked")
        List<Listener<T>> listenerList = (List<Listener<T>>) (List<?>) rawList;

        for (Listener<T> listener : listenerList) {
            if (condition.test(event, listener) && event.getPre() != null) {
                listener.firePre(event);
            }
        }
    }

    public <T extends Event> void firePost(T event, BiPredicate<T, Listener<T>> condition) {
        List<Listener<? extends Event>> rawList = listeners.get(event.getClass());

        if (rawList == null) return;

        @SuppressWarnings("unchecked")
        List<Listener<T>> listenerList = (List<Listener<T>>) (List<?>) rawList;

        for (Listener<T> listener : listenerList) {
            if (condition.test(event, listener) && event.getPost() != null) {
                listener.firePost(event);
            }
        }
    }
}