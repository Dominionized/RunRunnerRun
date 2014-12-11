package ca.csf.rrrhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.badlogic.gdx.graphics.Texture.*;

public class AssetLoader {

    public static BitmapFont font;
    public static BitmapFont fontShadow;
    public static TextureAtlas atlas;
    public static TextureRegion sky, ground;

    public static Animation runnerAnimation;
    public static TextureRegion runnerIdle, runnerJump, runnerOne, runnerTwo, runnerThree, runnerFour;

    public static TextureRegion enemy;
    public static TextureRegion cone;
    public static TextureRegion box;

    public static Sound gameMusic;

    public static void load(){

        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        font.setScale(0.5f,-0.5f);

        fontShadow = new BitmapFont(Gdx.files.internal("shadow.fnt"));
        fontShadow.setScale(0.5f,-0.5f);

        atlas = new TextureAtlas("spritesheet.txt");

        sky = new TextureRegion(atlas.createSprite("sky"));
        sky.flip(false, true);

        sky.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        ground = new TextureRegion(atlas.createSprite("ground"));
        ground.flip(false, true);

        box = new TextureRegion(atlas.createSprite("box"));
        box.flip(false, true);

        runnerIdle = new TextureRegion(atlas.createSprite("runner_idle"));
        runnerJump = new TextureRegion(atlas.createSprite("runner_jump"));
        runnerOne = new TextureRegion(atlas.createSprite("runner1"));
        runnerTwo = new TextureRegion(atlas.createSprite("runner2"));
        runnerThree = new TextureRegion(atlas.createSprite("runner3"));
        runnerFour = new TextureRegion(atlas.createSprite("runner4"));

        gameMusic = Gdx.audio.newSound(Gdx.files.internal("rrrGameSong.mp3"));

        runnerJump.flip(false, true);

        TextureRegion[] runnerRun = {runnerOne, runnerTwo, runnerThree, runnerFour};

        for(TextureRegion runner : runnerRun){
            runner.flip(false, true);
        }

        runnerAnimation = new Animation(0.06f, runnerRun);
        runnerAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        runnerAnimation.setFrameDuration(0.1f);

        enemy = new TextureRegion(atlas.createSprite("enemy"));
        enemy.flip(false, true);
        cone = new TextureRegion(atlas.createSprite("cone"));
    }

    public static void dispose(){
        atlas.dispose();
    }
}
