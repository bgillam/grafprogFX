

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
/**
 * The test class GrafScatterplotTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */

public class GrafColumnplotTest
{
    private GrafProg  gSess;
    private GrafColumnPlot gCPlot;
    private ArrayList<GrafObject> aList;
    private ArrayList<Integer> indexList;
    /**
     * Default constructor for test class GrafPointTest
     */
    public GrafColumnplotTest()
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
       gCPlot = new GrafColumnPlot();
       aList = MockMaker.createMockObjectList(gSess);
       indexList = MockMaker.createMockIndexList(aList, GrafType.COLUMN);
                 
    }
    
    @Test
    public void drawGrafTest(){
        assertNotNull(gCPlot.getGrafColor());
        assertNotNull(GrafProg.getGrafPanel());
        assertNotNull(GrafProg.getGrafPanel().getGraphics());
        Graphics g = GrafProg.getGrafPanel().getGraphics();
        gCPlot.drawGraf((Graphics2D)g);
    }
    
   /* @Test
    public void createInputDialogTest(){
        gCPlot.createInputDialog(gSess);
        
    }
    
   *//*  @Test
    public void getPlotListTest(){
        gCPlot.getPlotList(aList, indexList, GrafType.COLUMN);
        
    }*//*
    
    @Test
    public void setDeleteValuesTest(){
        GrafInputDialog gid = gCPlot.createInputDialog(gSess);
        gid.getDeleter().setPlotIndex(indexList); 
        gCPlot.setDeleteValues(3, gid, aList );
    
    }*/
    
    @Test
    public void setAndGetMarkTest(){
         gCPlot.setMark("x");  
         assertEquals(gCPlot.getMark(), "x");
    }  
    
    @Test
    public void setAndGetColorRedTest(){
        gCPlot.setGrafColor(Color.RED);
        assertEquals(gCPlot.getGrafColor(),Color.RED);
    }
    
    @Test
    public void setAndGetConnectedAndColumns(){
        gCPlot.setColumnNumber(1);
        assertEquals(gCPlot.getColumnNumber(),1);
        
        gCPlot.setConnected(true);
        assertTrue(gCPlot.getConnected());
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
