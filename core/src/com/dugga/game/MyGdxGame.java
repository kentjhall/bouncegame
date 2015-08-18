package com.dugga.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class MyGdxGame extends Game {
	private static SpriteBatch batch;
    private static MainMenu mainMenu;
    private static Player player;
    private static Grid grid;
    private static DeathMenu deathMenu;
    private static boolean running=true;
    private static BitmapFont scoreFont;
    private static BitmapFont scoreFont2;
    private static BitmapFont scoreFont3;
    private static BitmapFont endFont;
    private static BitmapFont endFont2;
    private static FreeTypeFontGenerator generator;
    private static FreeTypeFontGenerator.FreeTypeFontParameter scoreParameter;
    private static FreeTypeFontGenerator.FreeTypeFontParameter scoreParameter2;
    private static FreeTypeFontGenerator.FreeTypeFontParameter scoreParameter3;
    private static FreeTypeFontGenerator.FreeTypeFontParameter endParameter;
    private static FreeTypeFontGenerator.FreeTypeFontParameter endParameter2;
    private static double scoreFontScale;
    private double endFontScale;
    private static GlyphLayout scoreFontLayout;
    private static GlyphLayout scoreFontLayout2;
    private static GlyphLayout endFontLayout;
    private static GlyphLayout endFontLayout2;
    private static GlyphLayout highScoreLayout;
    private static int count;
    private static boolean scoreWhite;
    public enum ScoreType{
        SCORE, END
    }

	@Override
	public void create () {
        batch = new SpriteBatch();
        player=new Player(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        grid=new Grid(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth());
        mainMenu=new MainMenu();
        deathMenu=new DeathMenu();
        generator=new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        scoreFontLayout=new GlyphLayout();
        scoreFontLayout2=new GlyphLayout();
        endFontLayout=new GlyphLayout();
        endFontLayout2=new GlyphLayout();
        highScoreLayout=new GlyphLayout();
        scoreFontScale=1;
        endFontScale=0.1;
        count=0;
        scoreWhite=false;

        scoreParameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        scoreParameter2=new FreeTypeFontGenerator.FreeTypeFontParameter();
        scoreParameter3=new FreeTypeFontGenerator.FreeTypeFontParameter();
        scoreParameter.size=63;
        scoreParameter2.size=63;
        scoreParameter3.size=63;
        scoreParameter.color= Color.BLACK;
        scoreParameter2.color= Color.WHITE;
        scoreParameter3.color= Color.BLACK;
        scoreParameter2.borderColor=Color.BLACK;
        scoreParameter2.borderWidth=5;
        scoreParameter3.borderColor=Color.WHITE;
        scoreParameter3.borderWidth=5;
        scoreFont = generator.generateFont(scoreParameter);
        scoreFont2=generator.generateFont(scoreParameter2);
        scoreFont3=generator.generateFont(scoreParameter3);
        scoreFont.getData().setScale((float) scoreFontScale, (float) scoreFontScale);
        scoreFont2.getData().setScale((float) scoreFontScale, (float) scoreFontScale);
        scoreFont3.getData().setScale((float) scoreFontScale, (float) scoreFontScale);

        endParameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        endParameter2=new FreeTypeFontGenerator.FreeTypeFontParameter();
        endParameter.size=144;
        endParameter2.size=144;
        endParameter.color= Color.WHITE;
        endParameter2.color=Color.BLACK;
        endParameter2.borderColor=Color.WHITE;
        endParameter2.borderWidth=10;
        endFont = generator.generateFont(endParameter);
        endFont2=generator.generateFont(endParameter2);
        endFont.getData().setScale((float) endFontScale, (float) endFontScale);
        endFont2.getData().setScale((float) endFontScale, (float) endFontScale);
        generator.dispose();
    }

	@Override
	public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        if (count<40){
            count++;
        }
        else if (count>=40 && !player.getDead()){
            scoreWhite=!scoreWhite;
            count=0;
        }

        if (!mainMenu.getStart()){
            scoreFont.getData().setScale(1, 1);
            mainMenu.draw(batch);
        }
        if (mainMenu.getStart()) {
            if (!player.getDead()) {
                grid.draw(batch);
                player.draw(batch);

                scoreFontLayout.setText(scoreFont, "" + player.getScore());
                scoreFontLayout2.setText(scoreFont2, "" + player.getScore());
                highScoreLayout.setText(scoreFont3, "Best:" + player.getPrefs().getInteger("highScore"));
                scoreFont.getData().setScale((float) scoreFontScale, (float) scoreFontScale);
                scoreFont2.getData().setScale((float) scoreFontScale, (float) scoreFontScale);
                scoreFont3.getData().setScale((float) scoreFontScale, (float) scoreFontScale);

                if (scoreFontScale < 1) {
                    scoreFontScale += 0.1;
                }
            } else {
                player.draw(batch);
                grid.draw(batch);

                scoreFontLayout.setText(scoreFont, "" + player.getScore());
                scoreFontLayout2.setText(scoreFont2, "" + player.getScore());
                highScoreLayout.setText(scoreFont3, "Best:"+player.getPrefs().getInteger("highScore"));
                scoreFont.getData().setScale((float) scoreFontScale, (float) scoreFontScale);
                scoreFont2.getData().setScale((float) scoreFontScale, (float) scoreFontScale);
                scoreFont3.getData().setScale((float) scoreFontScale, (float) scoreFontScale);

                if (scoreFontScale > 0.1) {
                    scoreFontScale -= 0.1;
                }
            }
            if (player.getWidth() <= 0 && player.getHeight() <= 0) {
                deathMenu.draw(batch);
                endFontLayout.setText(endFont, "" + player.getScore());
                endFontLayout2.setText(endFont2, "" + player.getScore());
                endFont.getData().setScale((float) endFontScale, (float) endFontScale);
                endFont2.getData().setScale((float) endFontScale, (float) endFontScale);
                if (endFontScale < 1) {
                    endFontScale += 0.05;
                }
            } else {
                endFontLayout.setText(endFont, "" + player.getScore());
                endFontLayout2.setText(endFont2, "" + player.getScore());
                endFont.getData().setScale((float) endFontScale, (float) endFontScale);
                endFont2.getData().setScale((float) endFontScale, (float) endFontScale);
                if (endFontScale > 0.1) {
                    endFontScale -= 0.05;
                }
            }
        }
        batch.end();
	}

    public static void reset(){
        grid=new Grid(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth());
        deathMenu=new DeathMenu();
    }

    public static void drawScore(float x, float y, ScoreType scoreType, Color color){
        switch (scoreType){
            case SCORE:
                if (color==Color.BLACK) {
                    scoreFont.draw(batch, "" + player.getScore(), x - scoreFontLayout.width / 2, y - scoreFontLayout.height / 2);
                }
                else{
                    if (scoreWhite && scoreFontScale>0.1){
                        scoreFont2.draw(batch, "" + player.getScore(), x - scoreFontLayout2.width / 2, y - scoreFontLayout2.height / 2);
                    }
                    else if (!scoreWhite && scoreFontScale>0.1){
                        scoreFont3.draw(batch, "" + player.getScore(), x - scoreFontLayout2.width / 2, y - scoreFontLayout2.height / 2);
                    }
                }
                break;
            case END:
                if (color==Color.WHITE) {
                    endFont.draw(batch, "" + player.getScore(), x - endFontLayout.width / 2, y - endFontLayout.height / 2);
                }
                else{
                    endFont2.draw(batch, "" + player.getScore(), x - endFontLayout2.width / 2, y - endFontLayout2.height / 2);
                }
                break;
        }
    }

    @Override
    public void dispose(){
        batch.dispose();
    }

    public static Player getPlayer(){
        return player;
    }

    public static void setPlayer(Player player){
        MyGdxGame.player=player;
    }

    public static Grid getGrid(){
        return grid;
    }

    public static void setGrid(Grid grid){
        MyGdxGame.grid=grid;
    }

    public static BitmapFont getScoreFont(){
        return scoreFont;
    }

    public static BitmapFont getEndFont(){
        return endFont;
    }

    public static DeathMenu getDeathMenu(){
        return deathMenu;
    }

    public static void setDeathMenu (DeathMenu deathMenu){
        MyGdxGame.deathMenu=deathMenu;
    }

    public static MainMenu getMainMenu(){
        return mainMenu;
    }

    public static void setMainMenu(MainMenu mainMenu){
        MyGdxGame.mainMenu=mainMenu;
    }

    public static FreeTypeFontGenerator.FreeTypeFontParameter getScoreParameter(){
        return scoreParameter;
    }

    public static BitmapFont getScoreFont3(){
        return scoreFont3;
    }

    public static GlyphLayout getHighScoreLayout(){
        return highScoreLayout;
    }

    public static GlyphLayout getEndFontLayout(){
        return endFontLayout;
    }

}
