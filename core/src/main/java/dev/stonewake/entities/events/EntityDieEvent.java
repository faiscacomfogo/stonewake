package dev.stonewake.entities.events;

import dev.stonewake.entities.LivingEntity;
import dev.stonewake.events.Event;
import dev.stonewake.events.subevents.EntitySubEvent;
import dev.stonewake.world.GameWorld;

public class EntityDieEvent extends Event {
    public static final class Post extends EntitySubEvent {
        public final float deathX;
        public final float deathY;

        public Post(Object source, LivingEntity deadEntity, String[] tags, GameWorld world) {
            super(source, deadEntity, tags, world);
            deathX = deadEntity.getEntityX();
            deathY = deadEntity.getEntityY();
        }
    }

    public EntityDieEvent() {
        super(null);
    }

    @Override
    public Class<? extends Event> getEventClass() {
        return EntityDieEvent.class;
    }
}
