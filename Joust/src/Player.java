import processing.core.PApplet;
import processing.core.PImage;

public class Player {

    private static final int JUMP_VAL = 15;
    private static final float GRAVITY_VAL = 0.5f;
    private static final int SPEED = 5;

    private int pNum;
    private char up;
    private char left;
    private char right;
    private float x,y;
    private float ySpeed;

    private PImage currentImage;

    public Player (int playerNum, char up, char left, char right) {
        this.pNum = playerNum;
        this.up = up;
        this.left = left;
        this.right = right;

        if (playerNum == 1) {
            x = 700;
            y = 400;
        } else if (playerNum == 2) {
            x = 0;
            y = 400;
        }


    }

    /**
     * Main method, call to move Player
     */
    public void move(PApplet app) {

        PImage[] localImages;

        if (pNum == 1) localImages = Joust.p1Image;
        else localImages = Joust.p2Image;

        // Deal with y-value
        ySpeed += GRAVITY_VAL;
        y += ySpeed;
        if (Joust.keys.get(up)) {
            // Two options - reset, or addition. Addition works better
            // Reset use smaller values (0 - 3)
            // Addition can use bigger (5 - 20)
            ySpeed = -1 * JUMP_VAL;
        }

        // Check boundaries
        if (y >= 700) {
            ySpeed = 0;
            y = 699; // Must be one less or else the condition keeps tripping
        }

        if (y <= 100) {
            ySpeed = 0;
            y = 101;
        }

        // Deal with x-value
        if (Joust.keys.get(left)) {
            x -= SPEED;
            currentImage = localImages[1];
        } else if (Joust.keys.get(right)) {
            x += SPEED;
            currentImage = localImages[0];
        }

        // Display
        app.image(currentImage, x, y);
    }

    public void reset(PApplet app) {
        y = 400;
        if (pNum == 1) {
            x = 700;
            currentImage = Joust.p1Image[1];
            app.image(Joust.p1Image[1], x, y);
        } else if (pNum == 2) {
            x = 0;
            currentImage = Joust.p2Image[0];
            app.image(Joust.p2Image[0], x, y);
        }
    }

    public float getX() {
        return 0;
    }




}