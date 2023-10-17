package Brawler;

import java.util.ArrayList;

import javax.management.monitor.GaugeMonitor;

import Game.Game;

import processing.core.PApplet;

public class Brawler {
    final static float SIZE = 100; // Diameter of the brawler

    final static int MAX_MAGAZINE = 3;

    final static double TURN_SPEED = 0.1;

    float x, y; // Position of the brawler

    int damage; // How much damage each bullet does

    int health;

    int speed;

    int reloadSpeed; // Measured in ticks

    int magazine;

    double direction; // Where the brawler is aimings

    public Brawler(int health, int damage, int speed, int reloadSpeed) {
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.reloadSpeed = reloadSpeed;
    }

    void updateHealth(int damage) {
        health -= damage;
    }

    boolean isTouchingBullet(Bullet bullet) {
        return true;
    }

    void takeDamage(ArrayList<Bullet> bullets) {
        for (Bullet bullet : bullets) {
            if (isTouchingBullet(bullet)) {
                updateHealth(bullet.damage);
            }
        }
    }

    void shoot(ArrayList<Bullet> bulletList) {
        if (hasAmmo()) {
            bulletList.add(new Bullet());
            magazine--;
        }
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean hasAmmo() {
        return magazine > 0;
    }



    public void main(PApplet window, long tick) {

        if (Game.isKeyPressed('w')) {
            y = y+ speed;
        }
        if (Game.isKeyPressed('a')) {
            x = x - speed;
        }
        if (Game.isKeyPressed('s')) {
            y = y - speed;
        }
        if (Game.isKeyPressed('d')) {
            x = x + speed;
        }

        if (Game.isKeyPressed('k')) {
            direction = direction + TURN_SPEED;
        }

        if (Game.isKeyPressed('l')) {
            direction = direction - TURN_SPEED;
        }


//        if (Game.isKeyPressed('q') && tick%reloadSpeed == 0) {
//            shoot(bulletList);
//        }



        draw(window);
    }
    void drawBrawler(PApplet window, int r, int g, int b) {
        window.fill(window.color(r, g, b));
        window.ellipse(x, y, SIZE, SIZE);
    }

    void drawSlingshot(PApplet window, int r, int g, int b) {
        window.fill(window.color(r, g, b));
        window.ellipse((float) ((0.6*SIZE) * Math.cos(direction) + x), (float) ((0.6*SIZE) * Math.sin(direction) + y), SIZE/3, SIZE/3);
        window.ellipse((float) ((0.85*SIZE) * Math.cos(direction) + x), (float) ((0.85*SIZE) * Math.sin(direction) + y), SIZE/3, SIZE/3);

    }

    public void draw(PApplet window) {
        drawBrawler(window, 255, 0, 0);
        drawSlingshot(window, 0, 255, 0);
    }

}
