package dev.stonewake.events;

public abstract class Event<T, U> implements IStatefulEvent {
    public T pre;
    public U post;

    public Event(T pre) {
        this.pre = pre;
    }

    public abstract Class<? extends Event> getEventClass();

    @Override
    public Object getPre() {
        return pre;
    }

    @Override
    public Object getPost() {
        return post;
    }
}
