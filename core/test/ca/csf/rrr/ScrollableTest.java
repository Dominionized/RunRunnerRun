package ca.csf.rrr;

import com.badlogic.gdx.math.Vector2;

import org.junit.Before;
import org.junit.Test;

import ca.csf.rrr.gameobjects.Scrollable;
import ca.csf.rrr.helpers.AssetLoader;

import static org.junit.Assert.*;

public class ScrollableTest {

    Scrollable scrollable;

    @Before
    public void setUp() throws Exception {
        //Else, there will be a NullPointerException...
       // AssetLoader.load();

    }

    @Test(expected = NullPointerException.class)
    public void testReset() throws Exception {
        scrollable = new Scrollable(10,10,10,10,0);
        scrollable.setPosition(new Vector2(-10,0));
        scrollable.update(0);
        assertTrue(scrollable.isScrolledLeft());
    }
}