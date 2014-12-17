package ca.csf.rrrhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.badlogic.gdx.graphics.Texture.TextureFilter;

public class AssetLoader {

    public static BitmapFont font;
    public static BitmapFont fontShadow;
    public static TextureAtlas atlas;
    public static TextureRegion sky, ground, mountains;

    public static Animation runnerAnimation;
    public static TextureRegion runnerJump, runnerKick, runnerOne, runnerTwo, runnerThree, runnerFour;

    public static TextureRegion enemy;
    public static TextureRegion boxOne, boxTwo, boxThree;
    public static Animation boxAnimation;

    public static TextureRegion readyMenuBackground;

    public static Sound dyingMusic, jumpingSound, enemyDyingSound;
    public static Music gameMusic;

    public static void load() {

        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        font.setScale(0.5f, -0.5f);

        fontShadow = new BitmapFont(Gdx.files.internal("shadow.fnt"));
        fontShadow.setScale(0.5f, -0.5f);

        atlas = new TextureAtlas("spritesheet.txt");

        sky = new TextureRegion(atlas.createSprite("sky"));
        sky.flip(false, true);
        sky.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        mountains = new TextureRegion(atlas.createSprite("mountains"));
        mountains.flip(false, true);
        mountains.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        ground = new TextureRegion(atlas.createSprite("ground"));
        ground.flip(false, true);

        boxOne = new TextureRegion(atlas.createSprite("box1"));
        boxTwo = new TextureRegion(atlas.createSprite("box2"));
        boxThree = new TextureRegion(atlas.createSprite("box3"));
        boxOne.flip(false, true);
        boxTwo.flip(false, true);
        boxThree.flip(false, true);

        TextureRegion[] boxes = {boxOne, boxTwo, boxThree};

        boxAnimation = new Animation(0.06f, boxes);
        boxAnimation.setPlayMode(Animation.PlayMode.LOOP_RANDOM);
        boxAnimation.setFrameDuration(0.1f);

        runnerJump = new TextureRegion(atlas.createSprite("runner_jump"));
        runnerKick = new TextureRegion(atlas.createSprite("runner_kick"));
        runnerOne = new TextureRegion(atlas.createSprite("runner1"));
        runnerTwo = new TextureRegion(atlas.createSprite("runner2"));
        runnerThree = new TextureRegion(atlas.createSprite("runner3"));
        runnerFour = new TextureRegion(atlas.createSprite("runner4"));

        readyMenuBackground = new TextureRegion(atlas.createSprite("readymenubackground"));

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("rrrGameSong.mp3"));
        dyingMusic = Gdx.audio.newSound(Gdx.files.internal("RRRDyingSong.mp3"));
        jumpingSound = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
        enemyDyingSound = Gdx.audio.newSound(Gdx.files.internal("enemy dying sound.mp3"));

        runnerJump.flip(false, true);
        runnerKick.flip(false, true);

        TextureRegion[] runnerRun = {runnerOne, runnerTwo, runnerThree, runnerFour};

        for (TextureRegion runner : runnerRun) {
            runner.flip(false, true);
        }

        runnerAnimation = new Animation(0.06f, runnerRun);
        runnerAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        runnerAnimation.setFrameDuration(0.1f);

        enemy = new TextureRegion(atlas.createSprite("enemy"));
        enemy.flip(false, true);
    }

    public static void dispose() {
        atlas.dispose();
    }
}
