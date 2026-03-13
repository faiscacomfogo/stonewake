package dev.stonewake.entities.events;

import dev.stonewake.entities.Entity;
import dev.stonewake.events.Event;
import dev.stonewake.events.subevents.EntitySubEvent;
import dev.stonewake.utils.NamespacedKey;
import dev.stonewake.world.GameWorld;

public class EntitySummonEvent extends Event {
    public static class Pre extends EntitySubEvent {
        public float summonX;
        public float summonY;

        public Pre(Object summonSource, NamespacedKey summonedEntityId, float summonX, float summonY, String[] tags, GameWorld world) {
            super(summonSource, summonedEntityId, tags, world);

            this.summonX = summonX;
            this.summonY = summonY;
        }
    }

    public static final class Post extends EntitySubEvent {
        public final float summonX;
        public final float summonY;
        public final float realSummonX;
        public final float realSummonY;

        public Post(Object summonSource, Entity summonedEntity, float summonX, float summonY, String[] tags, GameWorld world) {
            super(summonSource, summonedEntity, tags, world);

            this.summonX = summonX;
            this.summonY = summonY;
            this.realSummonX = summonedEntity.getEntityX();
            this.realSummonY = summonedEntity.getEntityY();
        }
    }

    public EntitySummonEvent(Pre pre) {
        super(pre);
    }

    @Override
    public Class<? extends Event> getEventClass() {
        return EntitySummonEvent.class;
    }
}
