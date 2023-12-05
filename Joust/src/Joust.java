import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PImage;

public class Joust extends PApplet{

    // OS Stuff
    private static final String SPRITE_ROOT = "sprites/";

    public static final int DEFAULT_WIDTH = 1200;
    public static final int DEFAULT_HEIGHT = 700;

    private static final float COLLISION_DISTANCE = (float) (Math.sqrt(Player.SIZE[0] * Player.SIZE[0] + Player.SIZE[1] + Player.SIZE[1])) * 4/6;
    private static final float COLLISION_SENS = COLLISION_DISTANCE/2;

    private static boolean hasWon = false;

    public static HashMap<Character, Boolean> keys;

    private Player p1;
    private Player p2;

    // Sprites - 0 is right, 1 is left
    public static PImage[] p1Image, p2Image;

    // Terrain blocks
    public static PImage grassBlock;

    @Override
    public void settings() {
        size(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @Override
    public void setup() {

        // Drawing Options
        imageMode(CENTER);

        // Init images
        p1Image = new PImage[2]; p2Image = new PImage[2];
        p1Image[0] = loadImage(SPRITE_ROOT + "p1_right" + ".png"); p1Image[1] = loadImage(SPRITE_ROOT + "p1_left" + ".png");
        p2Image[0] = loadImage(SPRITE_ROOT + "p2_right" + ".png"); p2Image[1] = loadImage(SPRITE_ROOT + "p2_left" + ".png");

        grassBlock = loadImage(SPRITE_ROOT + "grassBlock.png");

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
        if (!hasWon) {
            clear();
            p1.move(this);
            p2.move(this);

            // Check if the collision results in a win
            int win = collisions();
            if (win == 1) hasWon = true;
            if (win == 2) hasWon = true;
        }
    }

    private void reset() {
        drawMap();
        p1.reset(this);
        p2.reset(this);
    }

    /**
     * Draws the Map, may not be used
     */
    private void drawMap() {
    }

    /**
     * Checks if someone has won
     * 
     * @return A collision number (1 = p1, 2 = p2, 0 = no win)
     */
    private int collisions() {
        float x1 = p1.getX(); float y1 = p1.getY();
        float x2 = p2.getX(); float y2 = p2.getY();

        double dist = Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2));

        if (dist < COLLISION_DISTANCE) {
            if (y1 > y2 + COLLISION_SENS) {
                return 1;
            }
            
            if (y2 > y1 + COLLISION_SENS) {
                return 2;
            }
        }

        // No winner
        return 0;

    }

    @Override
    public void keyPressed() {
        for (char maybePressed : keys.keySet()) {
            if (key == maybePressed) {
                keys.put(maybePressed, true);
            }
        }
    }

    @Override
    public void keyReleased() {
        for (char maybeReleased : keys.keySet()) {
            if (key == maybeReleased) {
                keys.put(maybeReleased, false);
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("Joust");
    }
}
