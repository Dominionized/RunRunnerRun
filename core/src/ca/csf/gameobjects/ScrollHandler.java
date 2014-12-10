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
    private Sky frontSky;
    private Sky backSky;

    public Sky getBackSky() {
        return backSky;
    }

    public Sky getFrontSky() {
        return frontSky;
    }

    private List<Box> boxList;
    private GameWorld gameWorld;

    public ScrollHandler(GameWorld gameWorld) {

        this.gameWorld = gameWorld;

        frontGrass = new Grass(0, GameRenderer.getHeight() - 32, 480, 32, SCROLL_SPEED);
        backGrass = new Grass(frontGrass.getTailX(), GameRenderer.getHeight() - 32, 480, 32, SCROLL_SPEED);

        frontSky = new Sky(0, 64, GameRenderer.getWidth(), 128, SCROLL_SPEED/8);
        backSky = new Sky(frontSky.getTailX(), 64, GameRenderer.getWidth(), 128, SCROLL_SPEED/8);

        boxList = new ArrayList<Box>();

        Random random = new Random();

        for (int i = 0; i < NBR_BOX; ++i){
            int gapToAdd = random.nextInt(300);
            float posX = 446 + gapToAdd;
            if(i > 0){
                posX = boxList.get(i-1).getTailX() + BOX_GAP + gapToAdd;
            }
            boxList.add(new Box(posX, GameRenderer.getHeight()-64, 32, 32, SCROLL_SPEED ));
        }

    }

    public void update(float delta) {
        frontGrass.update(delta);
        backGrass.update(delta);
        frontSky.update(delta);
        backSky.update(delta);

        int i = 0;
        for(Box box : boxList){
            box.update(delta);

            Random random = new Random();
            float posX = BOX_GAP + random.nextInt(400);
            if(i == 0){
               posX += boxList.get(boxList.size()-1).getTailX();
            } else {
               posX += boxList.get(i-1).getTailX();
            }

            int size = random.nextInt(32)+32;

            if(box.isScrolledLeft()){
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

}
