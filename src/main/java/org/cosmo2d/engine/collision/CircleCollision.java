package org.cosmo2d.engine.collision;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CircleCollision extends Collision {
    private int radius;

    public CircleCollision(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
}
