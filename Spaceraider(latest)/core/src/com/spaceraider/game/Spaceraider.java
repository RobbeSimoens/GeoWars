package com.spaceraider.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spaceraider.managers.GameInputProcessor;
import com.spaceraider.managers.GameKeys;
import com.spaceraider.managers.GameStateManager;

public class Spaceraider extends ApplicationAdapter {
    public static int WIDTH;
    public static int HEIGHT;
    public static OrthographicCamera cam;
	private String gameMode;
    private GameStateManager gsm;
	private int id;
	private String username;
	private LwjglApplicationConfiguration config;

	SpriteBatch batch;
	Texture img;

	public Spaceraider(String gameMode, LwjglApplicationConfiguration config, String username , int id) {
		this.gameMode = gameMode;
		this.username = username;
		this.id = id;
		this.config = config;

		if (gameMode.equals("multiplayer"))
		{
			System.out.println("Multiplayer game started");
		}
	}

	@Override
	public void create () {
	    WIDTH = Gdx.graphics.getWidth();
	    HEIGHT = Gdx.graphics.getHeight();

	    cam = new OrthographicCamera(WIDTH,HEIGHT);
	    cam.translate(WIDTH /2,HEIGHT/2);
	    cam.update();

	    Gdx.input.setInputProcessor(new GameInputProcessor());
		try {
			gsm = new GameStateManager(gameMode, config, username, id);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		batch = new SpriteBatch();
		img = new Texture("core/assets/background.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

		gsm.update(Gdx.graphics.getDeltaTime());
        gsm.draw();

        GameKeys.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
	public void resize(int width, int height){}
	public void pause(){}
	public void resume(){}
}
