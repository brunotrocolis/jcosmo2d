package org.cosmo2d.engine;

import lombok.Getter;
import lombok.Setter;
import org.cosmo2d.engine.collision.Collision;
import org.cosmo2d.engine.game.AbstractGameComponent;
import org.cosmo2d.engine.game.Game;
import org.cosmo2d.engine.sprite.*;

@Getter
@Setter
public abstract class Actor extends AbstractGameComponent {

    private String name;

    private long x;

    private long y;

    private Sprite sprite;

    private Collision collision;

    public Actor () {

    }

    public Actor (long x, long y) {
        this.x = x;
        this.y = y;
    }

    public Actor (Sprite sprite) {
        this.sprite = sprite;
    }

    public Actor (Sprite sprite, Collision collision) {
        this.sprite = sprite;
        this.collision = collision;
    }

    @Override
    public abstract void start(Game game);

    @Override
    public abstract void loop(Game game);

    @Override
    public final void update(Game game) {
        loop(game);
        if(sprite != null) sprite.update(game);
    }

    @Override
    public void preRender(Game game) {
        // NONE
    }

    @Override
    public final void render(Game game) {
        if(sprite != null) sprite.render(game);
    }

    @Override
    public void posRender(Game game) {
        // NONE
    }

    @Override
    public abstract void over(Game game);
}
