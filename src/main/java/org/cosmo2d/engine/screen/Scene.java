package org.cosmo2d.engine.screen;

import lombok.Getter;
import lombok.Setter;
import org.cosmo2d.engine.game.AbstractGameComponent;
import org.cosmo2d.engine.game.Game;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Scene extends AbstractGameComponent {

    private long width;

    private long height;

    private List<List<AbstractGameComponent>> layers = new ArrayList<>();

    @Override
    public abstract void start(Game game);

    @Override
    public abstract void loop(Game game);

    @Override
    public final void update(Game game) {
        loop(game);
        layers.forEach(layer -> updateComponents(game, layer));
    }

    @Override
    public void preRender(Game game) {
        layers.forEach(layer -> runComponent(game, layer, "preRender"));
    }

    @Override
    public void render(Game game) {
        layers.forEach(layer -> renderComponents(game, layer));
    }

    @Override
    public void posRender(Game game) {
        layers.forEach(layer -> runComponent(game, layer, "posRender"));
    }

    @Override
    public abstract void over(Game game);

    public void add(AbstractGameComponent component, int layer) {
        try {
            layers.get(layer).add(component);
        } catch (IndexOutOfBoundsException e) {
            for(int i = layers.size(); i <= layer; i++)
                layers.add(new ArrayList<>());
            add(component, layer);
        }
    }
}
