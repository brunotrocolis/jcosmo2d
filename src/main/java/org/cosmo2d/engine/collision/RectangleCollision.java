package org.cosmo2d.engine.collision;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RectangleCollision extends Collision {
    private int width;
    private int height;

    public RectangleCollision(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
