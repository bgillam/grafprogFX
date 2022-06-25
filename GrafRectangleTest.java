


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
/**
 * The test class GrafRectangleTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */

public class GrafRectangleTest
{
    private GrafProg  gSess;
    private GrafRectangle gRect;
    private ArrayList<GrafObject> aList;
    private ArrayList<Integer> indexList;
    /**
     * Default constructor for test class GrafPointTest
     */
    public GrafRectangleTest()
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
       gRect = new GrafRectangle(2, 3, 4, 5);
       aList = MockMaker.createMockObjectList(gSess);
       indexList = MockMaker.createMockIndexList(aList, GrafType.RECTANGLE);
    }
    
    @Test
    public void drawGrafTest(){
        assertNotNull(gRect.getGrafColor());
        ////assertNotNull(GrafProg.getGrafPanel());
        ////assertNotNull(GrafProg.getGrafPanel().getGraphics());
        ////Graphics g = GrafProg.getGrafPanel().getGraphics();
        assertNotNull(GrafUI.getGrafPanel());
        assertNotNull(GrafUI.getGrafPanel().getGraphics());
        Graphics g = GrafUI.getGrafPanel().getGraphics();
        gRect.drawGraf((Graphics2D)g);
    }
    
  /*  @Test
    public void createInputDialogTest(){
       gRect.createInputDialog(gSess);
        
    }
    
   *//* @Test
    public void getPlotListTest(){
        gRect.getPlotList(aList, indexList, GrafType.RECTANGLE);
        
    }*//*
    
    @Test
    public void setDeleteValuesTest(){
        GrafInputDialog gid = gRect.createInputDialog(gSess);
        gid.getDeleter().setPlotIndex(indexList); 
        gRect.setDeleteValues(3, gid, aList );
    
    }  */
      
    @Test
    public void setAndGetColorTest(){
        gRect.setGrafColor(Color.RED);
        assertEquals(gRect.getGrafColor(),Color.RED);
        gRect.setFillColor(Color.BLUE);
        assertEquals(gRect.getFillColor(),Color.BLUE);
    }
    
    @Test 
    public void setAndGetCoords(){
        gRect.setX(1);
        gRect.setY(2);
        gRect.setWidth(3);
        gRect.setHeight(4);
        double d = .000001;
        assertEquals(gRect.getX(),1.0,d);
        assertEquals(gRect.getY(),2.0,d);
        assertEquals(gRect.getWidth(),3.0,d);
        assertEquals(gRect.getHeight(),4.0,d);
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
