package com.dugga.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by kenthall on 8/8/15.
 */
public class Button {
    public enum type{
        RESTART, QUIT
    }
    private Texture buttonImg;
    private int width;
    private int height;
    private Vector2 loc;
    private String buttonText;
    private type buttonType;
    private Rectangle hitBox;
    public Button(type buttonType, int locX, int locY){
        width = Gdx.graphics.getWidth()/2;
        height=Gdx.graphics.getHeight()/2/4;
        loc=new Vector2(locX-width/2, locY-height/2);
        buttonImg=new Texture("squareW.png");
        hitBox=new Rectangle(loc.x, loc.y, width, height);
        this.buttonType=buttonType;
        buttonText="null";
    }

    public void draw(SpriteBatch batch){
        batch.draw(buttonImg, loc.x, loc.y, width, height);
        MyGdxGame.getFont().draw(batch, buttonText, loc.x+width/2, loc.y+height/2);

        switch (buttonType){
            case RESTART:
                buttonText="Restart";
                if (hitBox.contains(Gdx.input.getX(), Gdx.input.getY()) && Gdx.input.isTouched()){
                    System.out.println(Gdx.input.getX());
                    MyGdxGame.reset();
                }
                break;
            case QUIT:
                buttonText="Quit";
                break;
        }
    }
}
