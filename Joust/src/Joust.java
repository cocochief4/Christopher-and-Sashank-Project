import java.util.HashMap;

import processing.core.PApplet;

public class Joust extends PApplet{

    public static final int DEFAULT_HEIGHT = 800;
    public static final int DEFAULT_WIDTH = 800;

    private static HashMap<Character, Boolean> keys;

    private Player p1;
    private Player p2;

    @Override
    public void settings() {
        size(DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    @Override
    public void setup() {

        keys = new HashMap<Character, Boolean>();

        // Player 1
        keys.put('w', false); // up
        keys.put('a', false); // left
        keys.put('d', false); // right

        // Player 2
        keys.put('i', false); // up
        keys.put('j', false); // left
        keys.put('l', false); // right

        p1 = new Player(1, 'w', 'a', 'd');
        p2 = new Player(2, 'i', 'j', 'l');

        // Reset the game
        reset();

    }

    @Override
    public void draw() {
        p1.move();
        p2.move();

        collisions();
    }

    private void reset() {
        drawMap();
        p1.reset();
        p2.reset();
    }

    private void collisions() {

        

    }

    public static void main(String[] args) {
        PApplet.main("Joust");
    }
}
