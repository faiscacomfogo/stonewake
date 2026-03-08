package dev.stonewake.physics;

import com.badlogic.gdx.physics.box2d.Body;

import java.util.HashMap;
import java.util.Map;

public class TileMapPhysicsSolver {
    private Map<Long, Body> tileBodies;

    public TileMapPhysicsSolver() {
        tileBodies = new HashMap<>();
    }
}
