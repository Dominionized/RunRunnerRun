package ca.csf.gameobjects;

public class BackgroundLayer extends Scrollable{
    private String filename;

    public BackgroundLayer(float x, float y, int width, int height, float scrollSpeed, String filename) {
        super(x, y, width, height, scrollSpeed);
        this.filename = filename;
    }
}
