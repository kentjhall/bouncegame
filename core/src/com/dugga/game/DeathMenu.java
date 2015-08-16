package com.dugga.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by kenthall on 8/8/15.
 */
public class DeathMenu {
    private Texture menuImg;
    private int width;
    private int height;
    private Vector2 loc;
    private Button restartButton;
    private double growWidth;
    private double growHeight;
    private Sprite menuSprite;
    private BitmapFont highScoreFont;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter highScoreParameter;
    private GlyphLayout highScoreLayout;
    public DeathMenu(){
        menuImg=new Texture("menu.png");
        width = 810;
        height=960;
        loc=new Vector2(Gdx.graphics.getWidth()/2-width/2, Gdx.graphics.getHeight()/2-height/2);
        restartButton=new Button(Button.Type.RESTART, (int)loc.x+width/2, (int)loc.y+height/2-Gdx.graphics.getHeight()/7);
        growWidth=0.1;
        growHeight=0.1;
        menuSprite=new Sprite(menuImg, width, height);
        menuSprite.setOriginCenter();
        menuSprite.setPosition(loc.x, loc.y);
        generator=new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));

        highScoreLayout=new GlyphLayout();
        highScoreParameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        highScoreParameter.size=72;
        highScoreParameter.color= Color.WHITE;
        highScoreFont=generator.generateFont(highScoreParameter);
    }

    public void draw(SpriteBatch batch){
        highScoreLayout.setText(highScoreFont, ""+MyGdxGame.getPlayer().getPrefs().getInteger("highScore"));
        highScoreFont.getData().setScale((float)growWidth, (float)growHeight);
        menuSprite.setScale((float)growWidth, (float)growHeight);
        menuSprite.draw(batch);
        if (growWidth<1){
            growWidth+=0.05;
        }
        if (growHeight<1){
            growHeight+=0.05;
        }
        restartButton.draw(batch);
        MyGdxGame.drawScore(restartButton.getLocX(), (float) (restartButton.getLocY() + Gdx.graphics.getHeight() / 2.75), MyGdxGame.ScoreType.END, null);
        highScoreFont.draw(batch, ""+MyGdxGame.getPlayer().getPrefs().getInteger("highScore"), restartButton.getLocX()-highScoreLayout.width/2, (float) (restartButton.getLocY() + Gdx.graphics.getHeight() / 5.5));
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
