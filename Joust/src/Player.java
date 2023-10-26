

public class Player {

    private int color;
    private int playerNum;
    private char up;
    private char left;
    private char right;
    private float x,y;
    private float ySpeed;

    public Player (int playerNum, char up, char left, char right) {
        this.playerNum = playerNum;
        this.up = up;
        this.left = left;
        this.right = right;

        if (playerNum == 1) {
            color = 500;
            x = 800;
            y = 400;
        } else if (playerNum == 2) {
            color = 700;
            x = 0;
            y = 400;SSS
        }


    }




}