package org.cosmo2d.engine.collision;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolygonCollision extends Collision {
    private PolygonPoints[] points;

    public PolygonCollision(int x, int y, PolygonPoints... points) {
        this.x = x;
        this.y = y;
        this.points = points;
    }
}
