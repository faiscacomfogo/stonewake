package dev.stonewake.tiles.events;

import dev.stonewake.events.Event;
import dev.stonewake.events.subevents.TileSubEvent;
import dev.stonewake.tiles.pieces.Tile;
import dev.stonewake.utils.NamespacedKey;
import dev.stonewake.world.GameWorld;

public class TilePlaceEvent extends Event {
    public static class Pre extends TileSubEvent {
        public final NamespacedKey lastTileType;
        public NamespacedKey newTileType;

        public Pre(Object placeSource, Tile tile, short lastTileType, short newTileType, String[] tags, GameWorld world) {
            super(placeSource, tile, tags, world);

            this.lastTileType = lastTileType;
            this.newTileType = newTileType;
        }
    }

    public static final class Post extends TileSubEvent {
        public NamespacedKey lastTileType;
        public NamespacedKey newTileType;
        public NamespacedKey realNewTileType;

        public Post(Object placeSource, Tile tile, Pre preEvent,  short newTileType, String[] tags, GameWorld world) {
            super(placeSource, tile, tags, world);

            this.lastTileType = preEvent.lastTileType;
            this.newTileType = newTileType;
            this.realNewTileType = preEvent.newTileType;
        }
    }

    public TilePlaceEvent(Pre pre) {
        super(pre);
    }

    @Override
    public Class<? extends Event> getEventClass() {
        return TilePlaceEvent.class;
    }
}
