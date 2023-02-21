package org.cosmo2d;

import org.cosmo2d.engine.Actor;
import org.cosmo2d.engine.game.Game;
import org.cosmo2d.engine.collision.*;
import org.cosmo2d.engine.util.CollisionUtil;
import org.junit.Test;

public class CollisionTest {
    public Collision rec = new RectangleCollision(0, 0,10, 10);

    public Collision cir = new CircleCollision(5, 5,5);

    public Collision pol = new PolygonCollision(5, 5,
            new PolygonPoints(-5, -5),
            new PolygonPoints(5, -5),
            new PolygonPoints(2, 0),
            new PolygonPoints(5, 5),
            new PolygonPoints(-5, 5),
            new PolygonPoints(-2, 0),
            new PolygonPoints(-5, -5));

    public Actor actor1 = new Actor(0, 0) {
        @Override
        public void start(Game game) {

        }

        @Override
        public void loop(Game game) {

        }

        @Override
        public void over(Game game) {

        }
    };

    public Actor actor2 = new Actor(5, 5) {
        @Override
        public void start(Game game) {

        }

        @Override
        public void loop(Game game) {

        }

        @Override
        public void over(Game game) {

        }
    };

    @Test
    public void testarColisao2Retangulos() {
        actor1.setCollision(rec);
        actor2.setCollision(rec);

        boolean isColliding = CollisionUtil.onCollision(actor1, actor2);

        assert isColliding;

        actor2.setX(100);
        isColliding = CollisionUtil.onCollision(actor1, actor2);

        assert !isColliding;
    }

    @Test
    public void testarColisao2Circulos() {
        actor1.setCollision(cir);
        actor2.setCollision(cir);

        boolean isColliding = CollisionUtil.onCollision(actor1, actor2);

        assert isColliding;

        actor2.setX(100);
        isColliding = CollisionUtil.onCollision(actor1, actor2);

        assert !isColliding;
    }

    @Test
    public void testarColisao2Poligonos() {
        actor1.setCollision(pol);
        actor2.setCollision(pol);

        boolean isColliding = CollisionUtil.onCollision(actor1, actor2);

        assert isColliding;

        actor2.setX(8);
        isColliding = CollisionUtil.onCollision(actor1, actor2);

        assert !isColliding;
    }

    @Test
    public void testarColisaoRetanguloCirculo() {
        actor1.setCollision(rec);
        actor2.setCollision(cir);

        boolean isColliding = CollisionUtil.onCollision(actor1, actor2);

        assert isColliding;

        actor2.setX(100);
        isColliding = CollisionUtil.onCollision(actor1, actor2);

        assert !isColliding;
    }

    @Test
    public void testarColisaoRetanguloPoligono() {
        actor1.setCollision(rec);
        actor2.setCollision(pol);

        boolean isColliding = CollisionUtil.onCollision(actor1, actor2);

        assert isColliding;

        actor2.setX(11);
        isColliding = CollisionUtil.onCollision(actor1, actor2);

        assert !isColliding;
    }

    @Test
    public void testarColisaoCirculoPoligono() {
        actor1.setCollision(rec);
        actor2.setCollision(pol);

        boolean isColliding = CollisionUtil.onCollision(actor1, actor2);

        assert isColliding;

        actor2.setX(11);
        isColliding = CollisionUtil.onCollision(actor1, actor2);

        assert !isColliding;
    }


}
