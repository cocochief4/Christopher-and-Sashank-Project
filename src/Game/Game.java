package Game;
import processing.core.PApplet;

import java.util.HashMap;

import Brawler.Brawler;

public class Game extends PApplet {

    Brawler brawler;

    private static HashMap<Character, Boolean> keys;
    private long tick;

    private static final int WINDOW_HEIGHT = 1000;
    private static final int WINDOW_WIDTH = 1000;

    public void settings() {
        size(WINDOW_HEIGHT, WINDOW_WIDTH);
    }

    public void setup() {
        keys = new HashMap<Character, Boolean>();
        keys.put('w', false); // forward
        keys.put('a', false); // left
        keys.put('s', false); // backward
        keys.put('d', false); // right
        keys.put('k', false); // turn left
        keys.put('l', false); // turn right
        keys.put('Q', false); // shoot

        tick = 0;

        brawler = new Brawler(10, 2, 3, 3);
        brawler.setPosition(600, 600);
        brawler.draw(this);
    }

    public static boolean isKeyPressed(char key) {
        return keys.get(key);
    }

    @Override
    public void keyPressed() {
        System.out.println("Pressed: " + key);
        for (char keyPressed : keys.keySet()) {
            if (keyPressed == key) keys.put(keyPressed, true);
            else System.out.println("false");
        }
    }

    @Override
    public void keyReleased() {
        System.out.println("Released: " + key);
        for (char keyReleased : keys.keySet()) {
            if (keyReleased == key) keys.put(keyReleased, false);
            else System.out.println("false");
        }
    }

    public void draw() {
        brawler.main(this, tick);
        tick++;
    }

    public static void main(String[] args) {
        PApplet.main("Game.Game");
    }
}