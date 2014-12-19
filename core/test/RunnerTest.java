import org.junit.Before;
import org.junit.Test;

import ca.csf.rrr.gameobjects.Runner;
import ca.csf.rrr.helpers.AssetLoader;

import static org.junit.Assert.*;

public class RunnerTest {
/*
    Runner runner;


    @Before
    public void setUp() throws Exception {

        //Else, there will be a NullPointerException...

        runner = new Runner(0.0f, 0.0f, 0, 0);

    }

    @Test(expected = NullPointerException.class)
    public void testOnKick() {
        runner.onKick();
        assertTrue(runner.isKicking());
    }

<<<<<<< HEAD
*/
=======
    @Test(expected = NullPointerException.class)
    public void testOnJump() {
        runner.onJump();
        assertTrue(runner.getVelocity().y > 0);
    }
>>>>>>> test
}