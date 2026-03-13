package dev.stonewake.events.subevents;

import dev.stonewake.tiles.pieces.Tile;
import dev.stonewake.world.GameWorld;

public abstract class TileSubEvent extends WorldSubEvent {
    public final Tile targetTile;

    public TileSubEvent(Object eventSource, Tile targetTile, String[] tags, GameWorld world) {
        super(eventSource, world, tags);

        this.targetTile = targetTile;
    }
}
