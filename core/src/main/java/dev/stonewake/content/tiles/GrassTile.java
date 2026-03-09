package dev.stonewake.content.tiles;

import dev.stonewake.tiles.TileType;
import dev.stonewake.content.autotilers.SimpleAutoTiler;

import java.util.HashSet;

public class GrassTile extends TileType {

    public GrassTile(int tileId) {
        super(tileId);
    }

    @Override
    public void setDefaults() {
        this.tileSprite = "tiles/grass.png";
        this.autoTiler = new SimpleAutoTiler(new HashSet<>(), 2);
    }
}
