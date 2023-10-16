import processing.core.PApplet;

import java.util.HashMap;

import Brawler.Brawler;

public class Game extends PApplet {

    public HashMap<String, Boolean> keys;

    public static final int WINDOW_HEIGHT = 1000;
    public static final int WINDOW_WIDTH = 1000;

    public Game() {
        keys = new HashMap<String, Boolean>();
        keys.put("w", false); // forward
        keys.put("a", false); // left
        keys.put("s", false); // backward
        keys.put("d", false); // right
        keys.put("k", false); // turn left
        keys.put("l", false); // turn right
        keys.put("TAB", false); // shoot

    }

    public void settings() {
        size(WINDOW_HEIGHT, WINDOW_WIDTH);
    }

    public void setup() {
        Brawler brawler = new Brawler(10, 2, 3, 3);
        brawler.setPosition(600, 600);
        brawler.draw(this);
    }

    public void draw() {

    }

    public static void main(String[] args) {
        PApplet.main("Game");
    }
}