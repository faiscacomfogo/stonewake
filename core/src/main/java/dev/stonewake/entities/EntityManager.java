package dev.stonewake.entities;

import dev.stonewake.GameController;
import dev.stonewake.entities.events.EntityDisposeEvent;
import dev.stonewake.entities.events.EntityHealEvent;
import dev.stonewake.entities.events.EntitySummonEvent;
import dev.stonewake.entities.events.EntityDamageEvent;
import dev.stonewake.entities.types.EntityRegistry;
import dev.stonewake.entities.types.EntityType;
import dev.stonewake.events.EventBus;
import dev.stonewake.events.Listener;
import dev.stonewake.utils.NamespacedKey;
import dev.stonewake.world.GameWorld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class EntityManager {
    private static final class HealthChangeRequest {
        private final int entityUniqueId;
        private final int amount;
        private final Object source;
        private final String[] tags;

        public HealthChangeRequest(int entityUniqueId, int amount, Object source, String[] tags) {
            this.entityUniqueId = entityUniqueId;
            this.amount = amount;
            this.source = source;
            this.tags = tags;
        }
    }

    private GameController game;
    private GameWorld world;
    private final HashMap<Integer, Entity> activeEntities;

    private final List<Integer> entitiesToRemove;
    private final List<HealthChangeRequest> entitiesToHeal;
    private final List<HealthChangeRequest> entitiesToDamage;

    private final EntityRegistry entityRegistry;
    private final EventBus eventBus;

    public EntityManager(GameController game, GameWorld world) {
        activeEntities = new HashMap<>();
        entitiesToRemove = new ArrayList<>();
        entitiesToHeal = new ArrayList<>();
        entitiesToDamage = new ArrayList<>();
        entityRegistry = world.getEntityRegistry();
        eventBus = world.getEventBus();
        this.game = game;
    }

    public void summonEntity(Object summonSource, NamespacedKey entityTypeId, float summonX, float summonY, String... tags) {
        EntitySummonEvent entitySummonEvent = new EntitySummonEvent(new EntitySummonEvent.Pre(summonSource, entityTypeId, summonX, summonY, tags, world));

        eventBus.firePre(entitySummonEvent, (event, listener) -> {
            EntitySummonEvent.Pre pre = (EntitySummonEvent.Pre)event.pre;

            return matchesEntityType(listener, pre.targettedEntityType);
        });

        if (((EntitySummonEvent.Pre)entitySummonEvent.pre).cancelled) return;

        EntityType entityType = entityRegistry.getRegisteredEntityType(entityTypeId);
        Entity summonedEntity = entityType.summonEntity();
        activeEntities.put(summonedEntity.getUniqueEntityId(), summonedEntity);

        entitySummonEvent.post = new EntitySummonEvent.Post(summonSource, summonedEntity, summonX, summonY, ((EntitySummonEvent.Pre) entitySummonEvent.pre).eventTags, world);

        eventBus.firePost(entitySummonEvent, (event, listener) -> {
            EntitySummonEvent.Post post = (EntitySummonEvent.Post)event.post;

            return matchesEntityType(listener, post.targettedEntityType);
        });
    }

    public Entity getEntity(int uniqueEntityId) {
        return activeEntities.get(uniqueEntityId);
    }

    public void enqueueRemoveEntity(int uniqueEntityId) {
        entitiesToRemove.add(uniqueEntityId);
    }

    public void enqueueHealEntity(int uniqueEntityId, int healAmount, Object source, String[] tags) {
        entitiesToHeal.add(new HealthChangeRequest(uniqueEntityId, healAmount, source, tags));
    }

    public void enqueueDamageEntity(int uniqueEntityId, int damageAmount, Object source, String[] tags) {
        entitiesToDamage.add(new HealthChangeRequest(uniqueEntityId, damageAmount, source, tags));
    }

    public Collection<Entity> getEntities() {
        return activeEntities.values();
    }

    public void update() {
        updateEntities();

        healEntities();

        damageEntities();

        removeEntities();
    }

    private void updateEntities() {
        for (Entity entity : activeEntities.values()) {
            if (entity instanceof LivingEntity living) {
                if (!living.isAlive()) {
                    if (living instanceof PlayerEntity) continue;

                    enqueueRemoveEntity(entity.getUniqueEntityId());

                    continue;
                }
            }

            entity.update(world);
            entity.updateEntityTime(game.getDeltaTime());
        }
    }

    private void healEntities() {
        for (HealthChangeRequest healRequest : entitiesToHeal) {
            Entity entity = activeEntities.get(healRequest.entityUniqueId);
            if (!(entity instanceof LivingEntity entityToBeHealed)) continue;

            EntityHealEvent entityHealEvent = new EntityHealEvent(new EntityHealEvent.Pre(healRequest.source,
                    entityToBeHealed, healRequest.amount, healRequest.tags, world));

            eventBus.firePre(entityHealEvent, (event, listener) -> {
                EntityHealEvent.Pre pre = (EntityHealEvent.Pre)event.pre;

                return matchesEntityType(listener, pre.targettedEntityType);
            });

            if (((EntityHealEvent.Pre)entityHealEvent.pre).cancelled) continue;

            entityToBeHealed.heal(entityRegistry, ((EntityHealEvent.Pre)entityHealEvent.pre).healAmount);

            entityHealEvent.post = new EntityHealEvent.Post(healRequest.source, entityToBeHealed, (EntityHealEvent.Pre) entityHealEvent.pre,
                    healRequest.amount, ((EntityHealEvent.Pre) entityHealEvent.pre).eventTags, world);

            eventBus.firePost(entityHealEvent, (event, listener) -> {
                EntityHealEvent.Post post = (EntityHealEvent.Post)event.post;

                return matchesEntityType(listener, post.targettedEntityType);
            });
        }

        entitiesToHeal.clear();
    }

    public void damageEntities() {
        for (HealthChangeRequest damageRequest : entitiesToDamage) {
            Entity entity = activeEntities.get(damageRequest.entityUniqueId);
            if (!(entity instanceof LivingEntity entityToBeDamaged)) continue;

            EntityDamageEvent entityDamageEvent = new EntityDamageEvent(new EntityDamageEvent.Pre(damageRequest.source, entityToBeDamaged, damageRequest.amount, damageRequest.tags, world));

            eventBus.firePre(entityDamageEvent, (event, listener) -> {
                EntityDamageEvent.Pre pre = (EntityDamageEvent.Pre)event.pre;

                return matchesEntityType(listener, pre.targettedEntityType);
            });

            entityToBeDamaged.damage(entityRegistry, ((EntityDamageEvent.Pre)entityDamageEvent.pre).damageAmount);

            entityDamageEvent.post = new EntityDamageEvent.Post(damageRequest.source, entityToBeDamaged, damageRequest.amount,
                    (EntityDamageEvent.Pre) entityDamageEvent.pre, ((EntityDamageEvent.Pre) entityDamageEvent.pre).eventTags, world);

            eventBus.firePost(entityDamageEvent, (event, listener) -> {
                EntityDamageEvent.Post post = (EntityDamageEvent.Post)event.post;

                return matchesEntityType(listener, post.targettedEntityType);
            });
        }

        entitiesToDamage.clear();
    }

    private void removeEntities() {
        for (Integer id : entitiesToRemove) {
            Entity entity = activeEntities.get(id);
            if (entity == null) continue;

            entity.dispose();

            activeEntities.remove(id);

            EntityDisposeEvent entityDisposeEvent = new EntityDisposeEvent();
            entityDisposeEvent.post = new EntityDisposeEvent.Post(entity, world);

            eventBus.firePost(entityDisposeEvent, (event, listener) -> {
                EntityDisposeEvent.Post post = (EntityDisposeEvent.Post)event.post;

                return matchesEntityType(listener, post.targettedEntityType);
            });
        }

        entitiesToRemove.clear();
    }

    private boolean matchesEntityType(Listener listener, EntityType type) {
        if (listener.getOwner() instanceof EntityType owner) {
            return owner == type;
        }
        return listener.isGlobal();
    }
}
