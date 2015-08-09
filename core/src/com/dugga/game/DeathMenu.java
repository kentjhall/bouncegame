package com.dugga.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by kenthall on 8/8/15.
 */
public class DeathMenu {
    private Texture menuImg;
    private int width;
    private int height;
    private Vector2 loc;
    private Button resumeButton;
    public DeathMenu(){
        menuImg=new Texture("menu.png");
        width = Gdx.graphics.getWidth();
        height=Gdx.graphics.getHeight()/2;
        loc=new Vector2(Gdx.graphics.getWidth()/2-width/2, Gdx.graphics.getHeight()/2-height/2);
        resumeButton=new Button(Button.type.RESTART, (int)loc.x+width/2, (int)loc.y+height/2);
    }
    public void draw(SpriteBatch batch){
        batch.draw(menuImg, loc.x, loc.y, width, height);
        resumeButton.draw(batch);
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
