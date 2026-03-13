package dev.stonewake.world;

import dev.stonewake.GameController;
import dev.stonewake.entities.Entity;
import dev.stonewake.entities.EntityManager;
import dev.stonewake.entities.types.EntityRegistry;
import dev.stonewake.events.EventBus;
import dev.stonewake.tiles.TileMap;
import dev.stonewake.tiles.types.TileRegistry;

import java.util.Collection;
import java.util.HashMap;

public class GameWorld {
    private GameController game;
    private EventBus eventBus;
    private TileRegistry tileRegistry;
    private EntityRegistry entityRegistry;
    private TileMap tileMap;
    private EntityManager entityManager;
    private HashMap<Integer, Entity> players;

    public GameWorld() {
        eventBus = new EventBus();
        tileRegistry = new TileRegistry();
        entityRegistry = new EntityRegistry();
        tileMap = new TileMap(tileRegistry, this, 3, (short)32, (short)16, 32, 64, 8);
        entityManager = new EntityManager(game, this);
        players = new HashMap<>();
    }

    public void update(float fixedDeltaTime) {

    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public TileMap getTileMap() {
        return tileMap;
    }

    public EntityRegistry getEntityRegistry() {
        return entityRegistry;
    }

    public Collection<Entity> getEntities() {
        return entityManager.getEntities();
    }
}
