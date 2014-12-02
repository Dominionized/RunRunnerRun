package ca.csf.rrrhelpers;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.badlogic.gdx.graphics.Texture.*;

public class AssetLoader {
    public static TextureAtlas atlas;
    public static TextureRegion sky, ground;

    public static Animation runnerAnimation;
    public static TextureRegion runner_idle, runner_one, runner_two, runner_three, runner_four;

    public static TextureRegion enemy;
    public static TextureRegion cone;
    public static TextureRegion box;

    public static void load(){

        atlas = new TextureAtlas("spritesheet.txt");

        sky = new TextureRegion(atlas.createSprite("sky"));
        sky.flip(false, true);
        ground = new TextureRegion(atlas.createSprite("ground"));
        ground.flip(false, true);

        box = new TextureRegion(atlas.createSprite("box"));
        box.flip(false, true);

        runner_idle = new TextureRegion(atlas.createSprite("runner_idle"));

        runner_one = new TextureRegion(atlas.createSprite("runner1"));
        runner_two = new TextureRegion(atlas.createSprite("runner2"));
        runner_three = new TextureRegion(atlas.createSprite("runner3"));
        runner_four = new TextureRegion(atlas.createSprite("runner4"));

        TextureRegion[] runnerRun = {runner_one, runner_two, runner_three, runner_four};

        for(TextureRegion runner : runnerRun){
            runner.flip(false, true);
        }

        runnerAnimation = new Animation(0.06f, runnerRun);
        runnerAnimation.setPlayMode(Animation.PlayMode.LOOP);

        enemy = new TextureRegion(atlas.createSprite("enemy"));
        cone = new TextureRegion(atlas.createSprite("cone"));
    }

    public static void dispose(){
        atlas.dispose();
    }
}
