import processing.core.PImage;

public class Powerup {

    // Going to be circular.
    private static final int SIZE = 32;

    private enum Type {GHOST};
    
    float x, y;

    Type powerupType;

    PImage sprite;

    public Powerup(Block[] blockArr) { // Generate a random powerup on a random block.
        // Write code for random powerup if more are added
        sprite = Joust.ghost;
        powerupType = Type.GHOST;

        int randomBlockInt = (int) (Math.random() * blockArr.length);

        float blockX = blockArr[randomBlockInt].x


    }

}
