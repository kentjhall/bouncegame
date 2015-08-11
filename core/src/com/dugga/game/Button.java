package com.dugga.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by kenthall on 8/8/15.
 */
public class Button {
    public enum Type{
        RESTART, QUIT
    }
    private Texture buttonImg;
    private int width;
    private int height;
    private Vector2 loc;
    private Type buttonType;
    private Rectangle hitBox;
    private double growWidth;
    private double growHeight;private Sprite buttonSprite;
    public Button(Type buttonType, int locX, int locY){
        width = 540;
        height=240;
        loc=new Vector2(locX-width/2, locY-height/2);
        buttonImg=new Texture("squareW.png");
        switch (buttonType){
            case RESTART:
                buttonImg=new Texture("retryButton.png");
                break;
            case QUIT:
                buttonImg=new Texture("quitButton.png");
                break;
        }
        hitBox=new Rectangle(loc.x, loc.y, width, height);
        this.buttonType=buttonType;
        growWidth=0;
        growHeight=0;
        buttonSprite=new Sprite(buttonImg, width, height);
        buttonSprite.setOriginCenter();
        buttonSprite.setPosition(loc.x, loc.y);
    }

    public void draw(SpriteBatch batch){
        buttonSprite.setScale((float) growWidth, (float) growHeight);
        buttonSprite.draw(batch);
        if (growWidth<1) {
            growWidth += 0.05;
        }
        if (growHeight<1){
            growHeight+=0.05;
        }
        switch (buttonType){
            case RESTART:
                if (hitBox.contains(Gdx.input.getX(), Gdx.input.getY()) && Gdx.input.isTouched()){
                    MyGdxGame.reset();
                }
                break;
            case QUIT:
                if (hitBox.contains(Gdx.input.getX(), Gdx.input.getY()) && Gdx.input.isTouched()){
                    System.out.println("quit");
                }
                break;
        }
    }
}
