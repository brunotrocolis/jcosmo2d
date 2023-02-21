package org.cosmo2d.engine.sprite;

import lombok.Getter;
import lombok.Setter;
import org.cosmo2d.engine.game.AbstractGameComponent;
import org.cosmo2d.engine.game.Game;

@Getter
@Setter
public abstract class Sprite extends AbstractGameComponent {
    private int offsetX;
    private int offsetY;
    private int width;
    private int height;
    private double scale;
    private double rotation;
    private double opacity;
    private boolean visible;
    private int pivotX;
    private int pivotY;

    @Override
    public abstract void start(Game game);

    @Override
    public abstract void loop(Game game);

    public final void update(Game game) {
        // TODO:
    }

    @Override
    public void preRender(Game game) {
        // NONE
    }

    public void render(Game game) {
        // TODO:
    }

    @Override
    public void posRender(Game game) {
        // NONE
    }

    @Override
    public abstract void over(Game game);

}
