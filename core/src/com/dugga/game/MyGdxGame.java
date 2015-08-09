package com.dugga.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MyGdxGame extends ApplicationAdapter{
	private static SpriteBatch batch;
    private static Player player;
    private static Grid grid;
    private static DeathMenu deathMenu;
    private static boolean running=true;
    private boolean isRendering=true;
    private static BitmapFont font;
    private TextureAtlas atlas;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        //positioning is half of height and width, negative
        player=new Player(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        grid=new Grid(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth());
        atlas=new TextureAtlas();
        font = new BitmapFont(Gdx.files.internal("fontB36.fnt"), atlas.findRegion("fontB36.png"), false);
        deathMenu=new DeathMenu();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (!player.getDead()) {
            grid.draw(batch);
            player.draw(batch);
        } else {
            player.draw(batch);
            grid.draw(batch);
        }
        if (player.getWidth()<=0 && player.getHeight()<=0){
            deathMenu.draw(batch);
        }
        batch.end();
	}

    public static void reset(){
        grid.reset(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth());
        player.reset(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
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

    public static BitmapFont getFont(){
        return font;
    }

    public static DeathMenu getDeathMenu(){
        return deathMenu;
    }

}
