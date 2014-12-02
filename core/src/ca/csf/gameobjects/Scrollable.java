package ca.csf.gameobjects;

import com.badlogic.gdx.math.Vector2;

public abstract class Scrollable extends GameObject{

    protected Scrollable(float scrollAmount) {
        this.scrollAmount = scrollAmount;
    }

    protected onUpdate(){
        
    }
}
