package org.cosmo2d.engine.game;

import lombok.Getter;
import lombok.Setter;
import org.cosmo2d.engine.screen.Scene;
import org.cosmo2d.engine.screen.DefaultScreen;
import org.cosmo2d.engine.screen.Screen;

import java.awt.*;

@Getter
@Setter
public class Game implements Runnable {

    private boolean running = Boolean.TRUE;

    public final long FPS_120 = 1000000000 / 120;
    public final long FPS_60 = 1000000000 / 60;
    public final long FPS_30 = 1000000000 / 30;

    // Controle de tempo:
    private long targetTime;
    private long now;
    private long lastTime;
    private long elapsed;
    private final long DIVIDER = 1000000;
    //

    private Thread thread;

    private String name;

    private Screen screen;

    private double maxFps = 60;

    public Game(String name, Screen screen) {
        this.name = name;
        this.setScreen(screen);
        this.targetTime = FPS_60;
    }

    public Game (String name) {
        this.name = name;
        this.setScreen(new DefaultScreen());
        this.targetTime = FPS_60;
    }

    public void start() {
        screen.start(this);
        run();
    }

    public void loop() {
        screen.loop(this);
        Scene currentScene = screen.getCurrentScene();
        currentScene.loop(this);
    }

    public void render() {
        screen.render(this);
    }

    @Override
    public void run() {
        lastTime = System.nanoTime();

        while (running) {
            now = System.nanoTime();
            elapsed = (now - lastTime) / DIVIDER;
            lastTime = now;

            this.loop();
            this.render();

            try {
                Thread.sleep((targetTime - (System.nanoTime() - now)) / DIVIDER); // Calcula o tempo restante até o próximo quadro e pausa a thread
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Graphics getGraphics() {
        return screen.getGraphics();
    }
}
