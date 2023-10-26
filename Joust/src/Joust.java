import java.util.HashMap;

import processing.core.PApplet;

public class Joust extends PApplet{

    public static final int DEFAULT_HEIGHT = 800;
    public static final int DEFAULT_WIDTH = 800;

    private static final float COLLISION_DISTANCE = 10.0f;

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

        // Check if the collision results in a win
        int win = collisions();
    }

    private void reset() {
        drawMap();
        p1.reset();
        p2.reset();
    }

    private int collisions() {
        float x1 = p1.getX(); float y1 = p1.getY();
        float x2 = p2.getX(); float y2 = p1.getY();

        double dist = Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2));

        if (dist < COLLISION_DISTANCE) {
            if (y1 > y2) {
                return 1;
            }
            
            if (y2 > y1) {
                return 2;
            }

            // No winner
            return 0;
        }

    }

    public static void main(String[] args) {
        PApplet.main("Joust");
    }
}
