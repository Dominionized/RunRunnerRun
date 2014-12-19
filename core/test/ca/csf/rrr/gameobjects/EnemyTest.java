package ca.csf.rrr.gameobjects;

import junit.framework.TestCase;

public class EnemyTest extends TestCase {
 //float x, float y, int width, int height, float scrollSpeed
    Enemy enemy;

    public void setUp() throws Exception {
        super.setUp();
        enemy = new Enemy(0.0f, 0.0f, 0, 0, 0.0f);
    }

    public void testOnKilled() throws Exception {
        enemy.onKilled();
        assertFalse(enemy.isAlive());
    }
}