package dev.stonewake.entities.events;

import dev.stonewake.entities.Entity;
import dev.stonewake.events.Event;
import dev.stonewake.events.subevents.EntitySubEvent;
import dev.stonewake.world.GameWorld;

public class EntityDisposeEvent extends Event {
    public static final class Post extends EntitySubEvent {
        public final float disposeX;
        public final float disposeY;

        public Post(Entity disposedEntity, GameWorld world) {
            super(null, disposedEntity, new String[1], world);

            this.disposeX = disposedEntity.getEntityX();
            this.disposeY = disposedEntity.getEntityY();
        }
    }

    public EntityDisposeEvent() {
        super(null);
    }

    @Override
    public Class<? extends Event> getEventClass() {
        return EntityDisposeEvent.class;
    }
}
