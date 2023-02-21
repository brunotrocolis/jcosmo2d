package org.cosmo2d.engine.util;

import org.cosmo2d.engine.Actor;
import org.cosmo2d.engine.collision.*;

public abstract class CollisionUtil {
    public static boolean onCollision(Actor actor1, Actor actor2) {
        Collision collision1 = actor1.getCollision();
        Collision collision2 = actor2.getCollision();

        if (collision1 instanceof RectangleCollision rect1 && collision2 instanceof RectangleCollision rect2) {
            return onCollision(actor1, actor2, rect1, rect2);
        }
        if (collision1 instanceof RectangleCollision rect && collision2 instanceof CircleCollision circle) {
            return onCollision(actor1, actor2, rect, circle);
        }
        if (collision1 instanceof RectangleCollision rect && collision2 instanceof PolygonCollision poly) {
            return onCollision(actor1, actor2, rect, poly);
        }
        if (collision1 instanceof CircleCollision circle1 && collision2 instanceof CircleCollision circle2) {
            return onCollision(actor1, actor2, circle1, circle2);
        }
        if (collision1 instanceof CircleCollision circle && collision2 instanceof RectangleCollision rect) {
            return onCollision(actor1, actor2, circle, rect);
        }
        if (collision1 instanceof CircleCollision circle && collision2 instanceof PolygonCollision poly) {
            return onCollision(actor1, actor2, circle, poly);
        }
        if (collision1 instanceof PolygonCollision polygon1 && collision2 instanceof PolygonCollision polygon2) {
            return onCollision(actor1, actor2, polygon1, polygon2);
        }
        if (collision1 instanceof PolygonCollision poly && collision2 instanceof RectangleCollision rect) {
            return onCollision(actor1, actor2, poly, rect);
        }
        if (collision1 instanceof PolygonCollision poly && collision2 instanceof CircleCollision circle) {
            return onCollision(actor1, actor2, poly, circle);
        }

        return false;
    }

    private static boolean onCollision(Actor actor1, Actor actor2, CircleCollision circle, PolygonCollision poly) {
        int circleRadius = circle.getRadius();

        for (int i = 0; i < poly.getPoints().length; i++) {
            PolygonPoints point = poly.getPoints()[i];
            long x = actor2.getX() + poly.getX() + point.getX();
            long y = actor2.getY() + poly.getY() + point.getY();

            if (Math.sqrt(Math.pow(x - (actor1.getX() + circle.getX()), 2) + Math.pow(y - (actor1.getY() + circle.getY()), 2)) <= circleRadius) {
                return true;
            }
        }
        return false;
    }

    private static boolean onCollision(Actor actor1, Actor actor2, RectangleCollision rect, PolygonCollision poly) {
        long rectMinX = actor1.getX() + rect.getX();
        long rectMaxX = (actor1.getX() + rect.getX()) + rect.getWidth();
        long rectMinY = actor1.getY() - rect.getY();
        long rectMaxY = (actor1.getY() + rect.getY()) + rect.getHeight();

        for (int i = 0; i < poly.getPoints().length; i++) {
            PolygonPoints point = poly.getPoints()[i];
            long x = actor2.getX() + poly.getX() + point.getX();
            long y = actor2.getY() + poly.getY() + point.getY();

            if (x >= rectMinX && x <= rectMaxX && y >= rectMinY && y <= rectMaxY) {
                return true;
            }
        }
        return false;
    }

    private static boolean onCollision(Actor actor1, Actor actor2, CircleCollision circle1, CircleCollision circle2) {
        long x1 = actor1.getX() + circle1.getX();
        long x2 = actor2.getX() + circle2.getX();
        long y1 = actor1.getY() + circle1.getY();
        long y2 = actor2.getY() + circle2.getY();

        long distanceSquared = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);

        return distanceSquared <= (long) (circle1.getRadius() + circle2.getRadius()) * (circle1.getRadius() + circle2.getRadius());
    }

    private static boolean onCollision(Actor actor1, Actor actor2, RectangleCollision rect1, RectangleCollision rect2) {
        long rect1MinX = actor1.getX() + rect1.getX();
        long rect1MaxX = (actor1.getX() + rect1.getX()) + rect1.getWidth();
        long rect1MinY = actor1.getY() + rect1.getY();
        long rect1MaxY = (actor1.getY() + rect1.getY()) + rect1.getHeight();

        long rect2MinX = actor2.getX() + rect2.getX();
        long rect2MaxX = (actor2.getX() + rect2.getX()) + rect2.getWidth();
        long rect2MinY = actor2.getY() + rect2.getY();
        long rect2MaxY = (actor2.getY()  + rect2.getY()) + rect2.getHeight();

        return rect1MinX <= rect2MaxX && rect1MaxX >= rect2MinX && rect1MinY <= rect2MaxY && rect1MaxY >= rect2MinY;
    }

    public static boolean onCollision(Actor actor1, Actor actor2, RectangleCollision rect, CircleCollision circle) {
        long rectMinX = actor1.getX() + rect.getX();
        long rectMaxX = (actor1.getX() + rect.getX()) + rect.getWidth();
        long rectMinY = actor1.getY() - rect.getY();
        long rectMaxY = (actor1.getY() + rect.getY()) + rect.getHeight();

        long circleX = actor2.getX() + circle.getX();
        long circleY = actor2.getY() + circle.getY();
        long circle2Radius = circle.getRadius();

        long nearestX = clamp(circleX, rectMinX, rectMaxX);
        long nearestY = clamp(circleY, rectMinY, rectMaxY);

        long distanceSquared = (nearestX - circleX) * (nearestX - circleX) + (nearestY - circleY) * (nearestY - circleY);

        return distanceSquared <= circle2Radius * circle2Radius;
    }

    public static boolean onCollision(Actor actor1, Actor actor2, CircleCollision circle, RectangleCollision rect) {
        return onCollision(actor2, actor1, rect, circle);
    }

    private static boolean onCollision(Actor actor1, Actor actor2, PolygonCollision poly, RectangleCollision rect) {
        return onCollision(actor2, actor1, rect, poly);
    }

    private static boolean onCollision(Actor actor1, Actor actor2, PolygonCollision poly, CircleCollision circle) {
        return onCollision(actor2, actor1, circle, poly);
    }

    private static long clamp(long value, long min, long max) {
        return Math.max(min, Math.min(value, max));
    }

    public static boolean onCollision(Actor actor1, Actor actor2, PolygonCollision poly1, PolygonCollision poly2) {
        PolygonPoints[] points1 = poly1.getPoints();
        PolygonPoints[] points2 = poly2.getPoints();

        for (int i = 0; i < points1.length; i++) {
            long x1 = (actor1.getX() + poly1.getX()) + points1[i].getX();
            long y1 = (actor1.getY() + poly1.getY()) + points1[i].getY();
            long x2 = (actor1.getX() + poly1.getX()) + points1[(i + 1) % points1.length].getX();
            long y2 = (actor1.getY() + poly1.getY()) + points1[(i + 1) % points1.length].getY();

            for (int j = 0; j < points2.length; j++) {
                long x3 = (actor2.getX() + poly2.getX()) + points2[j].getX();
                long y3 = (actor2.getY() + poly2.getY()) + points2[j].getY();
                long x4 = (actor2.getX() + poly2.getX()) + points2[(j + 1) % points2.length].getX();
                long y4 = (actor2.getY() + poly2.getY()) + points2[(j + 1) % points2.length].getY();

                if (doLineSegmentsIntersect(x1, y1, x2, y2, x3, y3, x4, y4)) {
                    return true;
                }
            }
        }

        // Se chegou até aqui, os objetos não possuem implementação de PolygonCollision
        return false;
    }

    //Algoritmo de Shamos-Hoey
    private static boolean doLineSegmentsIntersect(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        long dx1 = x2 - x1;
        long dy1 = y2 - y1;
        long dx2 = x4 - x3;
        long dy2 = y4 - y3;

        long denominator = dy2 * dx1 - dx2 * dy1;

        if (denominator == 0) {
            return false;
        }

        long a = y1 - y3;
        long b = x1 - x3;
        long numerator1 = dx2 * a - dy2 * b;
        long numerator2 = dx1 * a - dy1 * b;

        double t1 = (double) numerator1 / denominator;
        double t2 = (double) numerator2 / denominator;

        return t1 >= 0 && t1 <= 1 && t2 >= 0 && t2 <= 1;
    }
}
