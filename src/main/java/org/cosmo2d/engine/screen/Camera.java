package org.cosmo2d.engine.screen;

import lombok.Getter;
import lombok.Setter;
import org.cosmo2d.engine.game.AbstractGameComponent;

@Getter
@Setter
public class Camera {
    private int left;
    private int right;
    private int top;
    private int bottom;
    private int width;
    private int height;
    private AbstractGameComponent protagonist;

    public Camera(AbstractGameComponent protagonist, Screen screen) {
        this.protagonist = protagonist;
        width = screen.getResolutionWidtht();
        height = screen.getResolutionHeigh();

        left = right = (int) Math.round(width * 0.1);
        top = bottom = (int) Math.round(height * 0.1);
    }

    public void update(Scene scene) {
        if (protagonist.getX() < left - scene.getX() && scene.getX() < 0) {
            scene.setX(-(protagonist.getX() - left));
        } else if (protagonist.getX() > (width - right) - scene.getX() && scene.getX() > -(scene.getWidth() - width)) {
            scene.setX(-(protagonist.getX() - (width - right)));
        }
        if (protagonist.getY() < top - scene.getY() && scene.getY() < 0) {
            scene.setY(-(protagonist.getY() - top));
        } else if (protagonist.getY() > (height - bottom) - scene.getY() && scene.getY() > -(scene.getHeight() - height)) {
            scene.setY(-(protagonist.getY() - (height - bottom)));
        }
    }

}
