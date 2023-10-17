import processing.core.PApplet;

import java.util.HashMap;

import Brawler.Brawler;

public class Game extends PApplet {

    Brawler brawler;

    private HashMap<String, Boolean> keys;
    private long tick;

    private static final int WINDOW_HEIGHT = 1000;
    private static final int WINDOW_WIDTH = 1000;

    public void settings() {
        size(WINDOW_HEIGHT, WINDOW_WIDTH);
    }

    public void setup() {
        keys = new HashMap<String, Boolean>();
        keys.put("w", false); // forward
        keys.put("a", false); // left
        keys.put("s", false); // backward
        keys.put("d", false); // right
        keys.put("k", false); // turn left
        keys.put("l", false); // turn right
        keys.put("TAB", false); // shoot

        tick = 0;

        brawler = new Brawler(10, 2, 3, 3);
        brawler.setPosition(600, 600);
        brawler.draw(this);
    }

    public boolean isKeyPressed(String key) {
        return keys.get(key);
    }

    @Override
    public void keyPressed() {
        for (String keyPressed : keys.keySet()) {
            if (keyPressed.equals(key)) keys.put(keyPressed, true);
        }
    }

    @Override
    public void keyReleased() {
        for (String keyPressed : keys.keySet()) {
            if (keyPressed.equals(key)) keys.put(keyPressed, false);
        }
    }

    public void draw() {
        brawler.main(this, tick, null);
        tick++;
    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}