package dev.stonewake.events;

public interface Listener<T extends Event> {
    void firePre(T event);
    void firePost(T event);
    Object getOwner();
    ListenerPriority getPriority();
    boolean isGlobal();
}
