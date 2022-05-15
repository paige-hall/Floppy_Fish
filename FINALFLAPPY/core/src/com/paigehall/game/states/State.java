package com.paigehall.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    //variables
    protected OrthographicCamera cam; //camera
    protected Vector3 mouse; //vector3 xyz coordinate system
    protected GameStateManager gsm; //manage states of game- play state, pause state - will need stack of states

    //constructor
    protected State(GameStateManager gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    //methods
    protected abstract void handleInput();

    public abstract void update(float dt); //dt delta time, delta time is difference between one frame rendered and next frame rendered

    public abstract void render(SpriteBatch sb); //container for everything needed to render to screen and puts on screen together

    public abstract void dispose(); //call when transition states

}
