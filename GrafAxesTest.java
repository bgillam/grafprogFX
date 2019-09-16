

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * The test class GrafAxesTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */

public class GrafAxesTest
{
    private GrafProg  gSess;
    private GrafAxes gAxes;
    /**
     * Default constructor for test class GrafPointTest
     */
    public GrafAxesTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
       gSess = new GrafProg();
       gAxes = new GrafAxes();
    }
    
    @Test
    public void drawGrafTest(){
        assertNotNull(gSess.getGrafPanel());
        assertNotNull(gSess.getGrafPanel().getGraphics());
        Graphics g = gSess.getGrafPanel().getGraphics();
        gAxes.drawGraf((Graphics2D)g);
    }
    
  
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() throws Exception {
       gSess.stop();
    }
}
