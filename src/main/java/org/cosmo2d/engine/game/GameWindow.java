package org.cosmo2d.engine.game;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public GameWindow(String gameName, int width, int height, Canvas canvas) {
        super(gameName);
        setSize(width, height);
        add(canvas);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(Boolean.FALSE);
    }

    public void showWindow() {
        this.setVisible(Boolean.TRUE);
    }
}
