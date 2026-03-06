package dev.stonewake.tiles.tiling.autotilers;

import dev.stonewake.tiles.Tile;
import dev.stonewake.tiles.tiling.AutoTiler;
import dev.stonewake.tiles.tiling.BitMask;
import dev.stonewake.tiles.tiling.comparers.ThreeBitMaskComparer;
import dev.stonewake.utils.TileUtils;

import java.util.Set;

public class SimpleAutoTiler extends AutoTiler {
    public SimpleAutoTiler(Set<Integer> connectableTileIds, int numVariants) {
        super(connectableTileIds, numVariants, 1);

        this.addBitMaskComparer(0, new ThreeBitMaskComparer());
    }

    @Override
    public int getTileSpriteIndex(BitMask bm, Tile occupiedTile, int tileSize) {
        int mask = bm.calculateBitMask(occupiedTile);
        ThreeBitMaskComparer comparer = (ThreeBitMaskComparer) getBitMaskComparer(0);
        int sX, sY;

        if (comparer.matches(mask, BitMask.S | BitMask.E | BitMask.SE, 0, BitMask.N | BitMask.W)) {
            sX = 1; sY = 0;
        }
        else if (comparer.matches(mask, BitMask.S | BitMask.W | BitMask.SW, 0, BitMask.N | BitMask.E)) {
            sX = 3; sY = 0;
        }
        else if (comparer.matches(mask, BitMask.N | BitMask.E | BitMask.NE, 0, BitMask.S | BitMask.W)) {
            sX = 1; sY = 2;
        }
        else if (comparer.matches(mask, BitMask.N | BitMask.W | BitMask.NW, 0, BitMask.S | BitMask.E)) {
            sX = 3; sY = 2;
        }
        else if (comparer.matches(mask, BitMask.S | BitMask.W | BitMask.E, 0, BitMask.N)) {
            sX = 2; sY = 0;
        }
        else if (comparer.matches(mask, BitMask.N | BitMask.S | BitMask.W, 0, BitMask.E)) {
            sX = 3; sY = 1;
        }
        else if (comparer.matches(mask, BitMask.N | BitMask.S | BitMask.E, 0, BitMask.W)) {
            sX = 1; sY = 1;
        }
        else if (comparer.matches(mask, BitMask.N | BitMask.W | BitMask.E, 0, BitMask.S)) {
            sX = 2; sY = 2;
        }
        else {
            sX = 2; sY = 1;
        }

        int variant = this.getDeterministicVariant(occupiedTile);
        return TileUtils.codifyTileSpriteIndex(sX, sY, occupiedTile.tileType, tileSize);
    }
}
