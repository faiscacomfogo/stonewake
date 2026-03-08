package dev.stonewake.world;

import dev.stonewake.tiles.TileType;
import dev.stonewake.content.tiles.GrassTile;

public class WorldConfig {
    public static final int NUM_LAYERS = 3;
    public static final int WORLD_WIDTH = 256;
    public static final int WORLD_HEIGHT = 256;
    public static final int TILE_SIZE = 8;
    public static final int CHUNK_SIMULATION_DISTANCE_X = 4;
    public static final int CHUNK_SIMULATION_DISTANCE_Y = 8;
    public static final Class<TileType>[] TILE_TYPES = new Class[] {GrassTile.class };
}
