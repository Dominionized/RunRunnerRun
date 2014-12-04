package ca.csf.gameobjects;

import ca.csf.gameworld.GameRenderer;
import ca.csf.gameworld.GameWorld;

public class ScrollHandler {

    private final int SCROLL_SPEED = 300;
    private Grass frontGrass;
    private Grass backGrass;
    private Sky frontSky;
    private Sky backSky;

    public Sky getBackSky() {
        return backSky;
    }

    public Sky getFrontSky() {
        return frontSky;
    }

    private Box box1, box2, box3, box4, box5;
    private GameWorld gameWorld;

    public ScrollHandler(GameWorld gameWorld) {

        this.gameWorld = gameWorld;

        frontGrass = new Grass(0, GameRenderer.getHeight() - 32, 480, 32, SCROLL_SPEED);
        backGrass = new Grass(frontGrass.getTailX(), GameRenderer.getHeight() - 32, 480, 32, SCROLL_SPEED);

        frontSky = new Sky(0, 64, GameRenderer.getWidth(), 128, SCROLL_SPEED/8);
        backSky = new Sky(frontSky.getTailX(), 64, GameRenderer.getWidth(), 128, SCROLL_SPEED/8);


    }

    public void update(float delta) {
        frontGrass.update(delta);
        backGrass.update(delta);
        frontSky.update(delta);
        backSky.update(delta);

        if (frontGrass.isScrolledLeft()) {

            frontGrass.reset(backGrass.getTailX());

        } else if (backGrass.isScrolledLeft()) {

            backGrass.reset(frontGrass.getTailX());

        }

        if (frontSky.isScrolledLeft()) {

            frontSky.reset(backSky.getTailX());

        } else if (backSky.isScrolledLeft()) {

            backSky.reset(frontSky.getTailX());

        }

    }

    public Grass getFrontGrass() {
        return frontGrass;
    }

    public Grass getBackGrass() {
        return backGrass;
    }

    public Box getBox1() {
        return box1;
    }

    public Box getBox2() {
        return box2;
    }

    public Box getBox3() {
        return box3;
    }

    public Box getBox4() {
        return box4;
    }

    public Box getBox5() {
        return box5;
    }




}
