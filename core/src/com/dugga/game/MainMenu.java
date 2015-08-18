package com.dugga.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

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
    private boolean arrowVisible;
    private Texture check;
    private BitmapFont titleFont;
    private BitmapFont tiltFont;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter titleParameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter tiltParameter;
    private GlyphLayout titleLayout;
    private GlyphLayout titleLayout2;
    private GlyphLayout tiltLayout;
    private GlyphLayout highScoreLayout;
    private GlyphLayout gamesPlayedLayout;
    private int count;
    private Vector2 phoneLoc;

    public MainMenu(){
        start=false;
        width=Gdx.graphics.getWidth();
        height=Gdx.graphics.getHeight();
        circleWidth=0;
        circleHeight=0;
        phoneWidth=78;
        phoneHeight=137;
        bg=new Texture("bg.png");
        circle=new Texture("circle.png");
        circleOutline=new Texture("circleOutline.png");
        phone=new Texture("phone.png");
        upArrow=new Texture("upArrow.png");
        downArrow=new Texture("downArrow.png");
        check=new Texture("check.png");
        generator=new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        arrowVisible=true;
        count=0;
        phoneLoc=new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2-phoneHeight/2);
        highScoreLayout=new GlyphLayout();
        gamesPlayedLayout=new GlyphLayout();

        titleLayout=new GlyphLayout();
        titleLayout2=new GlyphLayout();
        titleParameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        titleParameter.size=115;
        titleParameter.color=Color.BLACK;
        titleFont=generator.generateFont(titleParameter);

        tiltLayout=new GlyphLayout();
        tiltParameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        tiltParameter.size=36;
        tiltParameter.color=Color.BLACK;
        tiltFont=generator.generateFont(tiltParameter);
        generator.dispose();
    }

    public void draw(SpriteBatch batch){
        MyGdxGame.setPlayer(new Player(Gdx.graphics.getWidth() / 2 + (int) MyGdxGame.getPlayer().getWidth() / 2, Gdx.graphics.getHeight() / 2 + (int) MyGdxGame.getPlayer().getHeight() / 2));
        batch.draw(bg, 0, 0, width, height);
        batch.draw(circleOutline, Gdx.graphics.getWidth() / 2-290/2, Gdx.graphics.getHeight() / 2-290/2, 290, 290);
        batch.draw(circle, MyGdxGame.getPlayer().getLocPlayer().x-(float)circleWidth/2, MyGdxGame.getPlayer().getLocPlayer().y-(float)circleHeight/2, (float) circleWidth, (float) circleHeight);
        batch.draw(phone, phoneLoc.x - phoneWidth / 2, phoneLoc.y, phoneWidth, phoneHeight);

        count++;
        if (count>=25){
            arrowVisible=!arrowVisible;
            count=0;
        }

        highScoreLayout.setText(MyGdxGame.getScoreFont(), "High Score:"+MyGdxGame.getPlayer().getPrefs().getInteger("highScore"));
        gamesPlayedLayout.setText(MyGdxGame.getScoreFont(), "Games Played:"+MyGdxGame.getPlayer().getPrefs().getInteger("gamesPlayed"));

        titleLayout.setText(titleFont, "Bouncing");
        titleLayout2.setText(titleFont, "Circle");
        titleFont.draw(batch, "Bouncing", Gdx.graphics.getWidth() / 2 - titleLayout.width / 2, Gdx.graphics.getHeight() - 100 - titleLayout.height / 2);
        titleFont.draw(batch, "Circle", Gdx.graphics.getWidth() / 2 - titleLayout2.width / 2, Gdx.graphics.getHeight() - (float) (titleLayout.height * 2.3) - titleLayout2.height / 2);

        tiltLayout.setText(tiltFont, "Tilt Flat to Play!");
        tiltFont.getData().setScale(1, (float) 1.3);
        tiltFont.draw(batch, "Tilt Flat to Play!", phoneLoc.x - tiltLayout.width / 2, phoneLoc.y - 125 - tiltLayout.height / 2);

        MyGdxGame.getScoreFont().draw(batch, "High Score:" + MyGdxGame.getPlayer().getPrefs().getInteger("highScore"), phoneLoc.x - highScoreLayout.width / 2, 200);
        MyGdxGame.getScoreFont().draw(batch, "Games Played:" + MyGdxGame.getPlayer().getPrefs().getInteger("gamesPlayed"), phoneLoc.x - gamesPlayedLayout.width / 2, 100);

        if ((int)Gdx.input.getAccelerometerY()<=1 && (int)Gdx.input.getAccelerometerY()>=-1){
            batch.draw(check, phoneLoc.x-phoneWidth/2, phoneLoc.y+(float)(phoneHeight/4.125), phoneWidth, phoneWidth);
            if (circleWidth<300 && circleHeight<300){
                circleWidth+=5;
                circleHeight+=5;
            }
        }
        else{
            if ((int)Gdx.input.getAccelerometerY()>1 && arrowVisible){
                batch.draw(upArrow, phoneLoc.x - (float) (phoneWidth * 0.8) / 2, phoneLoc.y+(float)(phoneHeight/3.3), (float) (phoneWidth * 0.8), (float) (phoneWidth * 0.8));
            }
            else if ((int)Gdx.input.getAccelerometerY()<-1 && arrowVisible){
                batch.draw(downArrow, phoneLoc.x - (float) (phoneWidth * 0.8) / 2, phoneLoc.y+(float)(phoneHeight/3), (float) (phoneWidth * 0.8), (float) (phoneWidth * 0.8));
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

    public void setCircleWidth(double circleWidth){
        this.circleWidth=circleWidth;
    }

    public void setCircleHeight(double circleHeight){
        this.circleHeight=circleHeight;
    }
}
