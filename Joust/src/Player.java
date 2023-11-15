import processing.core.PApplet;
import processing.core.PImage;

public class Player {
    
    /*
     * Notes:
     * 
     * image() is the method that displays the sprite. Params are:
     * PImage image, x, y, width-scale, height-scale
     * 
     * image, x, y are not optional; width-scale, height-scale is optional.
     * x, y are of the top-left, with x+ going right and y+ going down.
     * 
     * sprites are 16 x 16 pixels.
     */

    private static final int JUMP_VAL = 7;
    private static final float GRAVITY_VAL = 0.5f;
    private static final int SPEED = 5;

    // Width, Height
    private static final int[] SIZE = {16, 16};

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
            x = Joust.DEFAULT_WIDTH/4;
            y = (int) (Joust.DEFAULT_HEIGHT/2);
        } else if (playerNum == 2) {
            x = Joust.DEFAULT_WIDTH * 3/4 - SIZE[0];
            y = (int) (Joust.DEFAULT_HEIGHT/2);
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
        // Bottom
        if (y >= Joust.DEFAULT_HEIGHT - SIZE[1] + 1) {
            // ySpeed = 0;
            y = Joust.DEFAULT_HEIGHT - SIZE[1]; // Must be one less or else the condition keeps tripping
        }

        // Top
        if (y <= 100) {
        //     ySpeed = 0;
            y = Joust.DEFAULT_HEIGHT;
        }

        // Side
        if (x <= 50) {
            x = Joust.DEFAULT_WIDTH-81;
        }
        if (x >= Joust.DEFAULT_WIDTH-80) {
            x = 51;
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
        y = (int) (Joust.DEFAULT_HEIGHT/2);
        if (pNum == 1) {
            x = 100;
            currentImage = Joust.p1Image[0];
            app.image(Joust.p1Image[0], x, y);
        } else if (pNum == 2) {
            x = Joust.DEFAULT_WIDTH - 100;
            currentImage = Joust.p2Image[1];
            app.image(Joust.p2Image[1], x, y);
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }




}