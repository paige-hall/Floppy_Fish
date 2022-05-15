package com.paigehall.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import org.w3c.dom.Text;

import java.util.Random;

public class Obstacle {
    //variables
    private Texture topObstacle;
    private Texture bottomObstacle;
    private Vector2 positionTop;
    private Vector2 positionBottom;
    private Random rand;
    private Rectangle boundsTop, boundsBottom;
    private static final int FLUCTUATION = 150;
    private static final int GAP = 110; //openings
    private static final int LOWEST_OPENING = 120;
    public static final int OBSTACLE_WIDTH = 60; //how far apart


    //constructor
    public Obstacle(float x) {
        topObstacle = new Texture("cs2.png");
        bottomObstacle = new Texture("cs.png");
        rand = new Random();
        positionTop = new Vector2(x, rand.nextInt(FLUCTUATION) + GAP + LOWEST_OPENING);
        positionBottom = new Vector2(x, ((positionTop.y - GAP) - bottomObstacle.getHeight()));

        boundsTop = new Rectangle(positionTop.x, positionTop.y, topObstacle.getWidth(), topObstacle.getHeight());
        boundsBottom = new Rectangle(positionBottom.x, positionBottom.y, bottomObstacle.getWidth(), bottomObstacle.getHeight());
    }

    //methods
    public void reposition(float x) {
        positionTop.set(x, rand.nextInt(FLUCTUATION) + GAP + LOWEST_OPENING);
        positionBottom.set(x, ((positionTop.y - GAP) - bottomObstacle.getHeight()));
        boundsTop.setPosition(positionTop.x, positionTop.y);
        boundsBottom.setPosition(positionBottom.x, positionBottom.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
    }

    public void dispose() {
        topObstacle.dispose();
        bottomObstacle.dispose();
    }

    //getters
    public Texture getBottomObstacle() {
        return bottomObstacle;
    }

    public Texture getTopObstacle() {
        return topObstacle;
    }

    public Vector2 getPositionTop() {
        return positionTop;
    }

    public Vector2 getPositionBottom() {
        return positionBottom;
    }
}
