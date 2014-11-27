package ca.csf.rrrhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.badlogic.gdx.graphics.Texture.*;

public class AssetLoader {
    public static Texture texture;
    public static TextureRegion bg, ground;

    public static Animation runnerAnimation;
    public static TextureRegion runner_one, runner_two;

    public static TextureRegion box;

    public static void load(){
        texture = new Texture(Gdx.files.internal("sky.png"));
        bg = new TextureRegion(texture, 0, 0, 128, 32);
        bg.flip(false, true);
        /*
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        //TextureRegion(texture, x, y, width, height)
        bg = new TextureRegion(texture, 0,0,0,0);
        bg.flip(false, true);

        ground = new TextureRegion(texture, 0,0,0,0);
        ground.flip(false, true);

        runner_one = new TextureRegion(texture, 0,0,0,0);
        runner_one.flip(false, true);
        runner_two = new TextureRegion(texture, 0,0,0,0);
        runner_two.flip(false, true);

        TextureRegion[] runnerRun = {runner_one, runner_two};
        runnerAnimation = new Animation(0.06f, runnerRun);
        runnerAnimation.setPlayMode(Animation.PlayMode.LOOP);

        box = new TextureRegion(texture, 0,0,0,0);
        box.flip(false, true);
        */
    }

    public static void dispose(){
        texture.dispose();
    }
}
