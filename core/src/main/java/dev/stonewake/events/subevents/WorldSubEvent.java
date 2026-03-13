package dev.stonewake.events.subevents;

import dev.stonewake.world.GameWorld;

public abstract class WorldSubEvent {
    public final Object eventSource;
    public final GameWorld world;
    public final String[] eventTags;
    public boolean cancelled;

    public WorldSubEvent(Object eventSource, GameWorld world, String[] eventTags) {
        this.eventSource = eventSource;
        this.world = world;
        this.eventTags = eventTags;
    }
}
