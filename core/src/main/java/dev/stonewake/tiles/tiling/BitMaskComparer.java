package dev.stonewake.tiles.tiling;

public abstract class BitMaskComparer {
    public abstract int filter(int mask);

    public boolean matches(int mask) {
        return filter(mask) == 0;
    }
}
