package org.cosmo2d.engine.game;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Getter
@Setter
public abstract class AbstractGameComponent implements GameComponent {
    protected long x;
    protected long y;
    boolean persistent = Boolean.TRUE;
    boolean visible = Boolean.TRUE;
    boolean onCamera = Boolean.FALSE;

    public boolean isOnCamera(Game game) {
        if(isVisible()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isPersistent() {
        return persistent;
    }

    protected void renderComponents(Game game, List<? extends AbstractGameComponent> components) {
        components.forEach(component -> {
            if (component.isOnCamera(game) && component.isVisible()) {
                component.render(game);
            }
        });
    }

    protected void updateComponents(Game game, List<? extends AbstractGameComponent> components) {
        components.forEach(component -> {
            if (component.isOnCamera(game) || component.isPersistent()) {
                component.loop(game);
                component.update(game);
            }
        });
    }

    // TODO: Substituir esse método por expecificos
    protected void runComponent(Game game, List<? extends AbstractGameComponent> componentList, String methodName) {
        componentList.forEach(component -> {
            try {
                Method method = component.getClass().getMethod(methodName);
                method.invoke(component, game);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
