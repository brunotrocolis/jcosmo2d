package org.cosmo2d;

import org.cosmo2d.engine.Actor;
import org.cosmo2d.engine.game.Game;
import org.cosmo2d.engine.screen.Scene;

public class Main {

    public static void main(String[] args) {
        Game jogo = new Game("Jogo Teste");
        Scene cena0 = new Scene() {
            @Override
            public void start(Game game) {
                System.out.println("Iniciando cena0");
            }

            @Override
            public void loop(Game game) {
            }

            @Override
            public void over(Game game) {
            }
        };

        jogo.getScreen().addScene(cena0);

        Actor ator0 = new Actor() {
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

        cena0.add(ator0, 5);

        jogo.start();
    }
}
