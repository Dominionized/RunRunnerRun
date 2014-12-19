package ca.csf.rrr.helpers;

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

    private final static String FONTS = "font.fnt";
    private final static String SHADOWS = "shadow.fnt";
    private final static String SPRITES_SHEET = "spritesheet.txt";
    private final static String SKY = "sky";
    private final static String MOUNTAINS = "mountains";
    private final static String GROUND = "ground";
    private final static String BOX1 = "box1";
    private final static String BOX2 = "box2";
    private final static String BOX3 = "box3";
    private final static String RUNNER_JUMP = "runner_jump";
    private final static String RUNNER_KICK = "runner_kick";
    private final static String RUNNER1 = "runner1";
    private final static String RUNNER2 = "runner2";
    private final static String RUNNER3 = "runner3";
    private final static String RUNNER4 = "runner4";
    private final static String ENEMY = "enemy";
    private final static String READY_MENU_BACKGROUND = "readymenubackground";
    private final static String GAME_MUSIC = "rrrGameSong.mp3";
    private final static String DYING_SOUND = "RRRDyingSong.mp3";
    private final static String JUMP_SOUND = "jump.wav";
    private final static String ENEMY_DYING_SOUND = "enemy dying sound.mp3";

    public static void load() {

        font = new BitmapFont(Gdx.files.internal(FONTS));
        font.setScale(0.5f, -0.5f);

        fontShadow = new BitmapFont(Gdx.files.internal(SHADOWS));
        fontShadow.setScale(0.5f, -0.5f);

        atlas = new TextureAtlas(SPRITES_SHEET);

        sky = new TextureRegion(atlas.createSprite(SKY));
        sky.flip(false, true);
        sky.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        mountains = new TextureRegion(atlas.createSprite(MOUNTAINS));
        mountains.flip(false, true);
        mountains.getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        ground = new TextureRegion(atlas.createSprite(GROUND));
        ground.flip(false, true);

        boxOne = new TextureRegion(atlas.createSprite(BOX1));
        boxTwo = new TextureRegion(atlas.createSprite(BOX2));
        boxThree = new TextureRegion(atlas.createSprite(BOX3));
        boxOne.flip(false, true);
        boxTwo.flip(false, true);
        boxThree.flip(false, true);

        TextureRegion[] boxes = {boxOne, boxTwo, boxThree};

        boxAnimation = new Animation(0.06f, boxes);
        boxAnimation.setPlayMode(Animation.PlayMode.LOOP_RANDOM);
        boxAnimation.setFrameDuration(0.1f);

        runnerJump = new TextureRegion(atlas.createSprite(RUNNER_JUMP));
        runnerKick = new TextureRegion(atlas.createSprite(RUNNER_KICK));
        runnerOne = new TextureRegion(atlas.createSprite(RUNNER1));
        runnerTwo = new TextureRegion(atlas.createSprite(RUNNER2));
        runnerThree = new TextureRegion(atlas.createSprite(RUNNER3));
        runnerFour = new TextureRegion(atlas.createSprite(RUNNER4));

        readyMenuBackground = new TextureRegion(atlas.createSprite(READY_MENU_BACKGROUND));

        gameMusic = Gdx.audio.newMusic(Gdx.files.internal(GAME_MUSIC));
        dyingMusic = Gdx.audio.newSound(Gdx.files.internal(DYING_SOUND));
        jumpingSound = Gdx.audio.newSound(Gdx.files.internal(JUMP_SOUND));
        enemyDyingSound = Gdx.audio.newSound(Gdx.files.internal(ENEMY_DYING_SOUND));

        runnerJump.flip(false, true);
        runnerKick.flip(false, true);

        TextureRegion[] runnerRun = {runnerOne, runnerTwo, runnerThree, runnerFour};

        for (TextureRegion runner : runnerRun) {
            runner.flip(false, true);
        }

        runnerAnimation = new Animation(0.06f, runnerRun);
        runnerAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        runnerAnimation.setFrameDuration(0.1f);

        enemy = new TextureRegion(atlas.createSprite(ENEMY));
        enemy.flip(false, true);
    }

    public static void dispose() {
        atlas.dispose();
    }
}
