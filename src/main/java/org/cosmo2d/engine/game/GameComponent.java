package org.cosmo2d.engine.game;

public interface GameComponent {

    void start(Game game);

    void loop(Game game);

    void update(Game game);

    void preRender(Game game);

    void render(Game game);

    void posRender(Game game);

    void over(Game game);

}
