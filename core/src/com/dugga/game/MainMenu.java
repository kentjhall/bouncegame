package com.dugga.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by kenthall on 8/14/15.
 */
public class MainMenu {
    private boolean start;
    private int width;
    private int height;
    private double circleWidth;
    private double circleHeight;
    private int phoneWidth;
    private int phoneHeight;
    private Texture bg;
    private Texture circle;
    private Texture circleOutline;
    private Texture phone;
    private Texture upArrow;
    private Texture downArrow;
    private Texture check;
    private Pixmap arrowPix;
    private BitmapFont titleFont;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter titleParameter;
    private GlyphLayout titleLayout;

    public MainMenu(){
        start=false;
        width=Gdx.graphics.getWidth();
        height=Gdx.graphics.getHeight();
        circleWidth=0;
        circleHeight=0;
        phoneWidth=186;
        phoneHeight=330;
        bg=new Texture("squareW.png");
        circle=new Texture("circle.png");
        circleOutline=new Texture("circleOutline.png");
        phone=new Texture("phone.png");
        upArrow=new Texture("upArrow.png");
        downArrow=new Texture("downArrow.png");
        check=new Texture("check.png");
        generator=new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        arrowPix=new Pixmap((int) (Gdx.graphics.getWidth() / 2 - (phoneWidth * 0.8) / 2), 200, Pixmap.Format.Alpha);
        arrowPix.setColor(Color.CLEAR);

        titleLayout=new GlyphLayout();
        titleParameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        titleParameter.size=144;
        titleParameter.color=Color.BLACK;
        titleFont=generator.generateFont(titleParameter);
    }

    public void draw(final SpriteBatch batch){
        MyGdxGame.setPlayer(new Player(Gdx.graphics.getWidth() / 2 + (int)MyGdxGame.getPlayer().getWidth()/2, Gdx.graphics.getHeight() / 2+ (int)MyGdxGame.getPlayer().getHeight()/2));
        batch.draw(bg, 0, 0, width, height);
        batch.draw(circleOutline, MyGdxGame.getPlayer().getLocPlayer().x-300/2, MyGdxGame.getPlayer().getLocPlayer().y-300/2, 300, 300);
        batch.draw(circle, MyGdxGame.getPlayer().getLocPlayer().x-(float)circleWidth/2, MyGdxGame.getPlayer().getLocPlayer().y-(float)circleHeight/2, (float) circleWidth, (float) circleHeight);
        batch.draw(phone, Gdx.graphics.getWidth()/2-phoneWidth/2, 100, phoneWidth, phoneHeight);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
            }
        }, 0, (float) 0.25);

        titleLayout.setText(titleFont, "NAME");
        titleFont.draw(batch, "NAME", Gdx.graphics.getWidth() / 2 - titleLayout.width / 2, Gdx.graphics.getHeight() - titleLayout.height / 2);

        //MyGdxGame.drawFont(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, "Hold Phone Flat to Start", 36, Color.BLACK);
        if ((int)Gdx.input.getAccelerometerY()<=1 && (int)Gdx.input.getAccelerometerY()>=-1){
            batch.draw(check, Gdx.graphics.getWidth()/2-phoneWidth/2, 180, phoneWidth, phoneWidth);
            if (circleWidth<300 && circleHeight<300){
                circleWidth+=5;
                circleHeight+=5;
            }
        }
        else{
            if ((int)Gdx.input.getAccelerometerY()>1){
                batch.draw(upArrow, Gdx.graphics.getWidth() / 2 - (float) (phoneWidth * 0.8) / 2, 200, (float) (phoneWidth * 0.8), (float) (phoneWidth * 0.8));
            }
            else if ((int)Gdx.input.getAccelerometerY()<-1){
                batch.draw(downArrow, Gdx.graphics.getWidth() / 2 - (float) (phoneWidth * 0.8) / 2, 200, (float) (phoneWidth * 0.8), (float) (phoneWidth * 0.8));
            }

            if (circleWidth>0 && circleHeight>0){
                circleWidth-=5;
                circleHeight-=5;
            }
        }

        if (circleWidth>=300 && circleHeight>= 300){
            MyGdxGame.reset();
            start=true;
        }
    }

    public boolean getStart(){
        return start;
    }

    public void setStart(boolean start){
        this.start=start;
    }
}
