package com.paigehall.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.paigehall.game.game;
import com.paigehall.game.sprites.Ship;
import com.paigehall.game.sprites.Obstacle;


public class PlayState extends State{
    //variables
    private static final int SPACING = 125;
    private static final int COUNT = 4;
    private static final int Y_OFFSET = -30;
    private Ship ship;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Array<Obstacle> obstacles;

    //constructor
    public PlayState(GameStateManager gsm) {
        super(gsm);
        ship = new Ship(50, 300);
        cam.setToOrtho(false, game.WIDTH / 2, game.HEIGHT / 2);
        bg = new Texture("w1.png");
        ground = new Texture("bubs.png");
        groundPos1 = new Vector2((cam.position.x - cam.viewportWidth / 2), Y_OFFSET);
        groundPos2 = new Vector2(((cam.position.x - cam.viewportWidth / 2) + ground.getWidth()), Y_OFFSET);

        obstacles = new Array<Obstacle>();
        for (int i = 1; i <= COUNT; i++) {
            obstacles.add(new Obstacle(i * (SPACING + Obstacle.OBSTACLE_WIDTH)));
        }
    }

    //methods
    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            ship.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        ship.update(dt);
        cam.position.x = ship.getPosition().x + 80;
        //reposition
        for (Obstacle obstacle: obstacles) {
            if (cam.position.x - (cam.viewportWidth / 2) > obstacle.getPositionTop().x + obstacle.getTopObstacle().getWidth()) {
                obstacle.reposition(obstacle.getPositionTop().x + ((Obstacle.OBSTACLE_WIDTH + SPACING) * COUNT));
            }
            if (obstacle.collides(ship.getBounds())) {
                gsm.set(new PlayState(gsm));
                break;
            }
        }
        if (ship.getPosition().y <= ground.getHeight() + Y_OFFSET) {
            gsm.set(new PlayState(gsm));
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, (cam.position.x - (cam.viewportWidth / 2)), 0);
        sb.draw(ship.getTexture(), ship.getPosition().x, ship.getPosition().y);
        for (Obstacle obstacle: obstacles) {
            sb.draw(obstacle.getTopObstacle(), obstacle.getPositionTop().x, obstacle.getPositionTop().y);
            sb.draw(obstacle.getBottomObstacle(), obstacle.getPositionBottom().x, obstacle.getPositionBottom().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        ship.dispose();
        ground.dispose();
        for(Obstacle obstacle: obstacles) {
            obstacle.dispose();
        }
        System.out.println("Play State Disposed");
    }

    private void updateGround() {
        if (cam.position.x - (cam.viewportWidth / 2) > (groundPos1.x + ground.getWidth())) {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if (cam.position.x - (cam.viewportWidth / 2) > (groundPos2.x + ground.getWidth())) {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }

}
