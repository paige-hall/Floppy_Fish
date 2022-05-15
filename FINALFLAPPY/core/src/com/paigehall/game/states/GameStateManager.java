package com.paigehall.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    //constructor
    public GameStateManager() {
        states = new Stack<State>();
    }

    //pushes game states
    public void push(State state) {
        states.push(state);
    }

    //gets state off stack
    public void pop() {
        states.pop().dispose();
    }

    //if need to pop and push state
    public void set(State state) {
        states.pop().dispose();
        states.push(state);
    }

    //update
    public void update(float dt) {
        states.peek().update(dt);
    }

    //render
    public void render(SpriteBatch sb) {
        states.peek().render(sb);
    }
}
