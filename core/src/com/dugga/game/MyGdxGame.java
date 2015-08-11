package com.dugga.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;

public class MyGdxGame extends ApplicationAdapter{
	private static SpriteBatch batch;
    private static Player player;
    private static Grid grid;
    private static DeathMenu deathMenu;
    private static boolean running=true;
    private static BitmapFont scoreFont;
    private static BitmapFont endFont;
    private static FreeTypeFontGenerator generator;
    private static FreeTypeFontGenerator.FreeTypeFontParameter scoreParameter;
    private static FreeTypeFontGenerator.FreeTypeFontParameter endParameter;
    private double scoreFontScale;
    private double endFontScale;
    private static GlyphLayout scoreFontLayout;
    private static GlyphLayout endFontLayout;
    public enum ScoreType{
        SCORE, END
    }

	@Override
	public void create () {
		batch = new SpriteBatch();
        //positioning is half of height and width, negative
        player=new Player(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        grid=new Grid(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth());
        deathMenu=new DeathMenu();
        generator=new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        scoreFontLayout=new GlyphLayout();
        endFontLayout=new GlyphLayout();
        scoreFontScale=1;
        endFontScale=0.1;

        scoreParameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        scoreParameter.size=72;
        scoreParameter.color= Color.BLACK;
        scoreFont = generator.generateFont(scoreParameter);
        scoreFont.getData().setScale((float) scoreFontScale, (float) scoreFontScale);

        endParameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        endParameter.size=144;
        endParameter.color= Color.WHITE;
        endFont = generator.generateFont(endParameter);
        endFont.getData().setScale((float)endFontScale, (float)endFontScale);
        generator.dispose();
	}

	@Override
	public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (!player.getDead()) {
            grid.draw(batch);
            player.draw(batch);

            scoreFontLayout.setText(scoreFont, "" + MyGdxGame.getPlayer().getScore());
            scoreFont.getData().setScale((float) scoreFontScale, (float) scoreFontScale);
            if (scoreFontScale<1) {
                scoreFontScale+=0.1;
            }
        } else {
            player.draw(batch);
            grid.draw(batch);

            scoreFontLayout.setText(scoreFont, "" + player.getScore());
            scoreFont.getData().setScale((float) scoreFontScale, (float) scoreFontScale);
            if (scoreFontScale>0.1) {
                scoreFontScale-=0.1;
            }
        }
        if (player.getWidth()<=0 && player.getHeight()<=0){
            deathMenu.draw(batch);
            endFontLayout.setText(endFont, ""+player.getScore());
            endFont.getData().setScale((float) endFontScale, (float) endFontScale);
            if (endFontScale<1) {
                endFontScale+=0.05;
            }
        }
        else{
            endFontLayout.setText(endFont, ""+player.getScore());
            endFont.getData().setScale((float) endFontScale, (float) endFontScale);
            if (endFontScale>0.1) {
                endFontScale-=0.05;
            }
        }
        batch.end();
	}

    public static void reset(){
        grid=new Grid(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth());
        player=new Player(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        deathMenu=new DeathMenu();
    }

    public static void drawScore(float x, float y, ScoreType scoreType){
        switch (scoreType){
            case SCORE:
                scoreFont.draw(batch, "" + player.getScore(), x - scoreFontLayout.width / 2, y - scoreFontLayout.height / 4);
                break;
            case END:
                endFont.draw(batch, ""+player.getScore(), x - endFontLayout.width/2, y - endFontLayout.height/2);
                break;
        }
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

    public static BitmapFont getScoreFont(){
        return scoreFont;
    }

    public static DeathMenu getDeathMenu(){
        return deathMenu;
    }

    public static FreeTypeFontGenerator.FreeTypeFontParameter getScoreParameter(){
        return scoreParameter;
    }

}
