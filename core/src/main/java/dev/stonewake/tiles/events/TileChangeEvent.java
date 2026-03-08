package dev.stonewake.tiles.events;

import dev.stonewake.tiles.Tile;
import dev.stonewake.tiles.TileType;

public class TileChangeEvent {
    public final Tile changedTile;
    public final TileChangeEventType changeType;
    public final TileType lastTileType;
    public final TileType newTileType;

    public TileChangeEvent(Tile changedTile, TileChangeEventType changeType, TileType lastTileType, TileType newTileType) {
        this.changedTile = changedTile;
        this.changeType = changeType;
        this.lastTileType = lastTileType;
        this.newTileType = newTileType;
    }
}
