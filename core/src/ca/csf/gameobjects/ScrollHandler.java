package ca.csf.gameobjects;

import ca.csf.gameworld.GameRenderer;
import ca.csf.gameworld.GameWorld;

public class ScrollHandler {

    private final int SCROLL_SPEED = 200;
    private Grass frontGrass;
    private Grass backGrass;
    private Box box1, box2, box3, box4, box5;
    private GameWorld gameWorld;

    public ScrollHandler(GameWorld gameWorld) {

        this.gameWorld = gameWorld;

        frontGrass = new Grass(0, GameRenderer.getHeight() - 32, 480, 32, SCROLL_SPEED);
        backGrass = new Grass(frontGrass.getTailX(), GameRenderer.getHeight() - 32, 480, 32, SCROLL_SPEED);

    }

    public void update(float delta) {
        frontGrass.update(delta);
        backGrass.update(delta);

        if (frontGrass.isScrolledLeft()) {

            frontGrass.reset(backGrass.getTailX());

        } else if (backGrass.isScrolledLeft()) {

            backGrass.reset(frontGrass.getTailX());

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
