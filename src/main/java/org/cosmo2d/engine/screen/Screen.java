package org.cosmo2d.engine.screen;

import lombok.Getter;
import lombok.Setter;
import org.cosmo2d.engine.game.AbstractGameComponent;
import org.cosmo2d.engine.game.Game;
import org.cosmo2d.engine.game.GameWindow;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Screen extends AbstractGameComponent {

    public static final int[] QQVGA = {160, 120};
    public static final int[] HQVGA = {240, 160};
    public static final int[] QVGA = {320, 240};
    public static final int[] WQVGA = {400, 240};
    public static final int[] HVGA = {480, 320};
    public static final int[] nHD = {640, 360};
    public static final int[] VGA = {640, 480};
    public static final int[] WVGA = {800, 480};
    public static final int[] FWVGA = {854, 480};
    public static final int[] qHD = {960, 540};
    public static final int[] DVGA = {960, 640};
    public static final int[] SVGA = {800, 600};
    public static final int[] WSVGA = {1024, 600};
    public static final int[] XGA = {1024, 768};
    public static final int[] WXGA = {1280, 720};
    public static final int[] HD = {1280, 720};
    public static final int[] SXGA = {1280, 1024};
    public static final int[] SXGA_P = {1400, 1050};
    public static final int[] WXGA_P = {1440, 900};
    public static final int[] UXGA = {1600, 1200};
    public static final int[] UXGA_PP = {1680, 1050};
    public static final int[] WUXGA = {1920, 1200};
    public static final int[] FHD = {1920, 1080};
    public static final int[] FULL_HD = {1920, 1080};
    public static final int[] QWXGA = {2048, 1152};
    public static final int[] QXGA = {2048, 1536};
    public static final int[] WQHD = {2560, 1440};
    public static final int[] WQXGA = {2560, 1600};

    private GameWindow gameWindow;

    public BufferedImage buffer;

    //Resolução do jogo
    private final int resolutionWidtht;
    private final int resolutionHeigh;

    private final Canvas canvas = new Canvas();

    private List<Scene> sceneList = new ArrayList<>();

    private Integer currentScene = 0;

    private BufferStrategy bufferStrategy;

    private Camera camera = new Camera(null, this);
    private Graphics graphics;

    public Screen(int widtht, int heigh) {
        this.resolutionWidtht = widtht;
        this.resolutionHeigh = heigh;
        this.buffer = new BufferedImage(widtht, heigh, BufferedImage.TYPE_INT_RGB);
    }
    public Screen(int[] resolution) {
        this.resolutionWidtht = resolution[0];
        this.resolutionHeigh = resolution[1];
    }

    public final void start(Game game) {
        gameWindow = new GameWindow(game.getName(), resolutionWidtht, resolutionHeigh, canvas);
        gameWindow.showWindow();
        canvas.createBufferStrategy(3);
        bufferStrategy = canvas.getBufferStrategy();
    }

    @Override
    public final void update(Game game) {
        loop(game);
        getCurrentScene().update(game);
        camera.update(getCurrentScene());
    }

    @Override
    public final void preRender(Game game) {
        sceneList.get(currentScene).preRender(game);
    }

    @Override
    public final void render(Game game) {
        canvas.requestFocus();
        graphics = bufferStrategy.getDrawGraphics();
        graphics.setColor(new Color(0,0,0));
        graphics.clearRect(0, 0, resolutionWidtht, resolutionHeigh);
        sceneList.get(currentScene).render(game);
        bufferStrategy.show();
    }

    @Override
    public final void posRender(Game game) {
        sceneList.get(currentScene).posRender(game);
    }

    @Override
    public abstract void loop(Game game);

    public Scene getCurrentScene() {
        return sceneList.get(currentScene);
    }

    public void addScene(Scene scene) {
        sceneList.add(scene);
    }
}
