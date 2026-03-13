package dev.stonewake.tiles.events;

import dev.stonewake.events.Event;
import dev.stonewake.events.subevents.TileSubEvent;
import dev.stonewake.tiles.pieces.Tile;
import dev.stonewake.utils.NamespacedKey;
import dev.stonewake.world.GameWorld;

public class TileBreakEvent extends Event {
    public static class Pre extends TileSubEvent {
        public final NamespacedKey lastTileType;

        public Pre(Object breakSource, Tile tile, String[] tags, GameWorld world) {
            super(breakSource, tile, tags, world);
            this.lastTileType = tile.tileType;
        }
    }

    public static final class Post extends TileSubEvent {
        public final NamespacedKey lastTileType;

        public Post(Object breakSource, Tile tile, Pre preEvent, String[] tags, GameWorld world) {
            super(breakSource, tile, tags, world);
            this.lastTileType = preEvent.lastTileType;
        }
    }

    public TileBreakEvent(Pre pre) {
        super(pre);
    }

    @Override
    public Class<? extends Event> getEventClass() {
        return TileBreakEvent.class;
    }
}
