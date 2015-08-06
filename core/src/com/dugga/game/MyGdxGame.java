package com.dugga.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class MyGdxGame extends ApplicationAdapter{
	private SpriteBatch batch;
    private static Player player;
    private static Grid grid;
    private static boolean running=true;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        //positioning is half of height and width, negative
        player=new Player(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        grid=new Grid(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        grid.draw(batch);
        player.draw(batch);
		batch.end();
	}

    public static boolean getRunning(){
        return running;
    }

    public static Player getPlayer(){
        return player;
    }

    public static Grid getGrid(){
        return grid;
    }
}
