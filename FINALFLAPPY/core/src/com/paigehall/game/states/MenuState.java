package com.paigehall.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.paigehall.game.game;

public class MenuState extends State{
    //variables
    private final Texture background;
    private final Texture playBtn;


    //constructor
    public MenuState (GameStateManager gsm) {
        super(gsm);
        background = new Texture("ocean.png");
        playBtn = new Texture("np.png");
    }

    //methods
    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, game.WIDTH, game.HEIGHT);
        sb.draw(playBtn, (game.WIDTH/2) - (playBtn.getWidth() / 2), game.HEIGHT / 2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
