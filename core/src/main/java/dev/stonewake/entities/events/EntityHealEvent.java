package dev.stonewake.entities.events;

import dev.stonewake.entities.LivingEntity;
import dev.stonewake.events.Event;
import dev.stonewake.events.subevents.EntitySubEvent;
import dev.stonewake.world.GameWorld;

public class EntityHealEvent extends Event {
    public static class Pre extends EntitySubEvent {
        public int healAmount;

        public Pre(Object source, LivingEntity healedEntity, int healAmount, String[] tags, GameWorld world) {
            super(source, healedEntity, tags, world);

            this.healAmount = healAmount;
        }
    }

    public static final class Post extends EntitySubEvent {
        public final int healAmount;
        public final int realHealAmount;

        public Post(Object source, LivingEntity healedEntity, Pre preEvent, int healAmount, String[] tags, GameWorld world) {
            super(source, healedEntity, tags, world);

            this.healAmount = healAmount;
            this.realHealAmount = preEvent.healAmount;
        }
    }

    public EntityHealEvent(Pre pre) {
        super(pre);
    }

    @Override
    public Class<? extends Event> getEventClass() {
        return EntityHealEvent.class;
    }
}
