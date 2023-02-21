package org.cosmo2d.engine.collision;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolygonPoints {
    private int x;
    private int y;

    public PolygonPoints(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
