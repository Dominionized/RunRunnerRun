package ca.csf.rrr.gameobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ca.csf.rrr.gameworld.GameRenderer;
import ca.csf.rrr.gameworld.GameWorld;

public class ScrollHandler {

    private final static int SCROLL_SPEED = 300;
    private final static int FIRST_BOX_GAP = 175;
    private final static int MIN_BOX_GAP = 300;
    private final static int MAX_BOX_GAP = 446;
    private final static int NBR_BOX = 3;
    private final static int POSITION_Y_SKY = 64;
    private final static int HEIGHT_SKY = 128;
    private final static int HEIGHT_MOUNTAIN = 200;
    private final static int MIN_BOX_SIZE = 32;
    private final static int MAX_BOX_SIZE = 64;
    private final static int SCROLL_SPEED_SKY = SCROLL_SPEED/8;
    private final static int SCROLL_SPEED_MOUNTAIN = SCROLL_SPEED/8;

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

        frontSky = new BackgroundLayer(0, POSITION_Y_SKY, GameRenderer.getWidth(), HEIGHT_SKY, SCROLL_SPEED_SKY);
        backSky = new BackgroundLayer(frontSky.getTailX(), POSITION_Y_SKY, GameRenderer.getWidth(), HEIGHT_SKY, SCROLL_SPEED_SKY);

        frontMountains = new BackgroundLayer(0, HEIGHT_SKY, GameRenderer.getWidth(), HEIGHT_MOUNTAIN, SCROLL_SPEED_MOUNTAIN);
        backMountains = new BackgroundLayer(frontMountains.getTailX(), HEIGHT_SKY, GameRenderer.getWidth(), HEIGHT_MOUNTAIN, SCROLL_SPEED_MOUNTAIN);

        enemy = new Enemy(GameRenderer.getWidth(), GameRenderer.getHeight() - gameWorld.getGroundRect().getHeight() - HEIGHT_SKY, POSITION_Y_SKY, HEIGHT_SKY, SCROLL_SPEED);
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
            float posX = FIRST_BOX_GAP + random.nextInt(MAX_BOX_GAP);
            if (i == 0) {
                posX += boxList.get(boxList.size() - 1).getTailX();
            } else {
                posX += boxList.get(i - 1).getTailX();
            }

            int size = random.nextInt(MAX_BOX_SIZE - MIN_BOX_SIZE) + MIN_BOX_SIZE;

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
            int gapToAdd = random.nextInt(MIN_BOX_GAP);
            float posX = MAX_BOX_GAP + gapToAdd;
            if (i > 0) {
                posX = boxList.get(i - 1).getTailX() + FIRST_BOX_GAP + gapToAdd;
            }
            boxList.add(new Box(posX, GameRenderer.getHeight() - POSITION_Y_SKY, MIN_BOX_SIZE, MIN_BOX_SIZE, SCROLL_SPEED));
        }

    }

}
