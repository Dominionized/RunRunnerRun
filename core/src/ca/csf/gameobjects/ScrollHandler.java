package ca.csf.gameobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.csf.gameworld.GameRenderer;
import ca.csf.gameworld.GameWorld;

public class ScrollHandler {

    private final int SCROLL_SPEED = 300;
    private final int BOX_GAP = 175;
    private final int NBR_BOX = 3;

    private Grass frontGrass;
    private Grass backGrass;
    private BackgroundLayer frontSky, backSky, frontMountains, backMountains;
    private Enemy enemy;
    private List<Box> boxList;
    private GameWorld gameWorld;

    public ScrollHandler(GameWorld gameWorld) {

        this.gameWorld = gameWorld;

        frontGrass = new Grass(0, GameRenderer.getHeight() - gameWorld.getGroundRect().getHeight(), GameRenderer.getWidth(), (int)gameWorld.getGroundRect().getHeight(), SCROLL_SPEED);
        backGrass = new Grass(frontGrass.getTailX(), GameRenderer.getHeight() - gameWorld.getGroundRect().getHeight(), GameRenderer.getWidth(), (int)gameWorld.getGroundRect().getHeight(), SCROLL_SPEED);

        frontSky = new BackgroundLayer(0, 64, GameRenderer.getWidth(), 128, SCROLL_SPEED / 8);
        backSky = new BackgroundLayer(frontSky.getTailX(), 64, GameRenderer.getWidth(), 128, SCROLL_SPEED / 8);

        frontMountains = new BackgroundLayer(0, 128, GameRenderer.getWidth(), 200, SCROLL_SPEED / 4);
        backMountains = new BackgroundLayer(frontMountains.getTailX(), 128, GameRenderer.getWidth(), 200, SCROLL_SPEED / 4);

        enemy = new Enemy(GameRenderer.getWidth(), GameRenderer.getHeight() - gameWorld.getGroundRect().getHeight() - 128, 64, 128, SCROLL_SPEED);
        boxList = new ArrayList<Box>();

        initBoxes();
    }
    public BackgroundLayer getBackSky() {
        return backSky;
    }

    public BackgroundLayer getFrontSky() {
        return frontSky;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public BackgroundLayer getFrontMountains() {
        return frontMountains;
    }

    public BackgroundLayer getBackMountains() {
        return backMountains;
    }

    public void update(float delta) {
        frontGrass.update(delta);
        backGrass.update(delta);
        frontSky.update(delta);
        backSky.update(delta);
        frontMountains.update(delta);
        backMountains.update(delta);

        enemy.update(delta);

        int i = 0;
        for (Box box : boxList) {
            box.update(delta);

            Random random = new Random();
            float posX = BOX_GAP + random.nextInt(400);
            if (i == 0) {
                posX += boxList.get(boxList.size() - 1).getTailX();
            } else {
                posX += boxList.get(i - 1).getTailX();
            }

            int size = random.nextInt(32) + 32;

            if (box.isScrolledLeft()) {
                box.reset(posX);
                box.setWidth(size);
                box.setHeight(size);
                box.position.y = GameRenderer.getHeight() - gameWorld.getGroundRect().getHeight() - size;
            }

            i++;
        }

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

        if (frontMountains.isScrolledLeft()) {

            frontMountains.reset(backMountains.getTailX());

        } else if (backMountains.isScrolledLeft()) {

            backMountains.reset(frontMountains.getTailX());

        }

    }

    public void onRestart() {
        // TODO Ces calls de methodes ne sont pas necessairement les bons. Il faudra peut-etre les modifier.
        frontGrass.onRestart(0, SCROLL_SPEED);
        backGrass.onRestart(frontGrass.getTailX(), SCROLL_SPEED);
        frontSky.reset(0);
        backSky.reset(frontSky.getTailX());
        enemy.reset(0);
        initBoxes();
    }


    public Grass getFrontGrass() {
        return frontGrass;
    }

    public Grass getBackGrass() {
        return backGrass;
    }

    public List<Box> getBoxList() {
        return boxList;
    }

    private void initBoxes() {

        boxList.removeAll(boxList);

        Random random = new Random();

        for (int i = 0; i < NBR_BOX; ++i) {
            int gapToAdd = random.nextInt(300);
            float posX = 446 + gapToAdd;
            if (i > 0) {
                posX = boxList.get(i - 1).getTailX() + BOX_GAP + gapToAdd;
            }
            boxList.add(new Box(posX, GameRenderer.getHeight() - 64, 32, 32, SCROLL_SPEED));
        }

    }

}
