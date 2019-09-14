

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
/**
 * The test class GrafPointTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */

public class GrafPointTest
{
    private GrafProg  gSess;
    private GrafPoint gPoint;
    private ArrayList<GrafObject> aList;
    private ArrayList<Integer> indexList;
    /**
     * Default constructor for test class GrafPointTest
     */
    public GrafPointTest()
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
       gPoint = new GrafPoint(gSess, 2, 3, "x", Color.BLACK);
       aList = MockMaker.createMockObjectList(gSess);
       indexList = MockMaker.createMockIndexList(aList, GrafType.POINT);
                 
    }
    
    @Test
    public void drawGrafTest(){
        assertNotNull(gPoint.getGrafColor());
        assertNotNull(gSess.getGrafPanel());
        assertNotNull(gSess.getGrafPanel().getGraphics());
        Graphics g = gSess.getGrafPanel().getGraphics();
        gPoint.drawGraf((Graphics2D)g);
    }
    
    @Test
    public void createInputDialogTest(){
        gPoint.createInputDialog(gSess);
        
    }
    
    /*public void getPlotListTest(){
        gPoint.getPlotList(aList, indexList, GrafType.POINT); 
        
    }*/
    
    @Test
     public void setDeleteValuesTest(){
        GrafInputDialog gid = gPoint.createInputDialog(gSess);
         gid.getDeleter().setPlotIndex(indexList); 
         gPoint.setDeleteValues(3, gid, aList );
    
     }
    
    @Test
    public void setAndGetMarkTest(){
         gPoint.setMark("x");  
         assertEquals(gPoint.getMark(), "x");
    }  
    
    @Test
    public void setAndGetColorRedTest(){
        gPoint.setGrafColor(Color.RED);
        assertEquals(gPoint.getGrafColor(),Color.RED);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
       //gSess.dispose();
    }
}
