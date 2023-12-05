import processing.core.PApplet;
import processing.core.PImage;

public class Player {
    
    /*
     * Notes:
     * 
     * (0, 0) of the entire game board is the top left
     * 
     * image() is the method that displays the sprite. Params are:
     * PImage image, x, y, width-scale, height-scale
     * 
     * image, x, y are not optional; width-scale, height-scale is optional. Scaling is the pixel amount to scale to, not a ratio.
     * x, y are of the center, with x+ going right and y+ going down.
     * 
     * sprites are 16 x 16 pixels.
     */

    private static final int JUMP_VAL = 7;
    private static final float GRAVITY_VAL = 0.5f;
    private static final int SPEED = 5;

    // Width, Height
    public static final float[] SIZE = {32, 32};

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
        if (y >= Joust.DEFAULT_HEIGHT - SIZE[1]/2 + 1) {
            // ySpeed = 0;
            y = Joust.DEFAULT_HEIGHT - SIZE[1]/2; // Must be one less or else the condition keeps tripping
        }

        // Top
        if (y <= -1) {
        //     ySpeed = 0;
            y = Joust.DEFAULT_HEIGHT;
        }

        // Side
        if (x <= -1) {
            x = 1199;
        }
        if (x >= 1200) {
            x = 0;
        }

        // Check block collisions
        

        // Deal with x-value
        if (Joust.keys.get(left)) {
            x -= SPEED;
            currentImage = localImages[1];
        } else if (Joust.keys.get(right)) {
            x += SPEED;
            currentImage = localImages[0];
        }

        // Display
        image(currentImage, app);
    }

    public void reset(PApplet app) {
        y = (int) (Joust.DEFAULT_HEIGHT/2);
        if (pNum == 1) {
            x = Joust.DEFAULT_WIDTH/6;
            currentImage = Joust.p1Image[0];
            image(Joust.p1Image[0], app);
        } else if (pNum == 2) {
            x = Joust.DEFAULT_WIDTH*5/6;
            currentImage = Joust.p2Image[1];
            image(Joust.p2Image[1], app);
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float[] getSize() {
        return SIZE;
    }

    /**
     * newSize is in {WIDTH, HEIGHT}
     * 
     * @param image
     * @param newSize
     */
    private void image (PImage image, int[] newSize, PApplet app) {
        float wScale = newSize[0] / SIZE[0];
        float hScale = newSize[1] / SIZE[1];

        app.image(image, x, y, wScale, hScale);        
    }

    private void image (PImage image, float scale, PApplet app) {
        app.image(image, x, y, scale*SIZE[0], scale*SIZE[1]);
    }

    private void image(PImage image, PApplet app) {
        // app.image(image, x, y, SIZE[0]/16, SIZE[1]/16);
        app.image(image, x, y, SIZE[0], SIZE[1]);
    }

    // Note: image scaling is based on number of pixels, not a scale of original image.


}