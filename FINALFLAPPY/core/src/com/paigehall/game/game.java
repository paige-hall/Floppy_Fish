package com.paigehall.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.paigehall.game.states.GameStateManager;
import com.paigehall.game.states.MenuState;

public class game extends ApplicationAdapter {
	//variables
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = ("Jumping Fish");
	private GameStateManager gsm;
	private SpriteBatch batch;
	private Music music;

	//methods
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("song.mp3")); //ADD MUSIC
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		Gdx.gl.glClearColor(1,0,0,1);
		gsm.push(new MenuState(gsm));

	}

	@Override
	public void render () {
		//ScreenUtils.clear(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}

}
