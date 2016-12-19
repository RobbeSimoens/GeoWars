package com.spaceraider.entities;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyCircleSprite implements ApplicationListener {

    private SpriteBatch batch;
    private Texture texture;
    private Sprite sprite;
    private int rotation;
    private OrthographicCamera camera;

    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(1, h/w);
        batch = new SpriteBatch();
        texture = new Texture("core/assets/playership.png");
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear); //** for scaling **//
        sprite = new Sprite(texture);
        sprite.setSize(0.1f, 0.3f); //** sprite size in screen coordinates **//
        sprite.setOrigin(0.05f,0); //** relative origin for rotation **//
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }

    @Override
    public void render() {
        rotation=(rotation + 1) % 360; //** angle keeps on increasing **//
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int i=0; i<12; i++) {
            sprite.setRotation(i*30+rotation);
            sprite.draw(batch);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
