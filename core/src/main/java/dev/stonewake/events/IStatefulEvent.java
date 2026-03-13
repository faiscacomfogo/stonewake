package dev.stonewake.events;

public interface IStatefulEvent {
    Object getPre();
    Object getPost();
}
