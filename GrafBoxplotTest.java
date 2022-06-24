

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

public class GrafBoxplotTest
{
    private GrafProg  gSess;
    private GrafBoxPlot gBPlot;
    private ArrayList<GrafObject> aList;
    private ArrayList<Integer> indexList;
    /**
     * Default constructor for test class GrafPointTest
     */
    public GrafBoxplotTest()
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
       gBPlot = new GrafBoxPlot();
       aList = MockMaker.createMockObjectList(gSess);
       indexList = MockMaker.createMockIndexList(aList, GrafType.BOXPLOT);
       
    }
    
    @Test
    public void drawGrafTest(){
        assertNotNull(gBPlot.getGrafColor());
        assertNotNull(GrafProg.getGrafPanel());
        assertNotNull(GrafProg.getGrafPanel().getGraphics());
        Graphics g = GrafProg.getGrafPanel().getGraphics();
        gBPlot.drawGraf((Graphics2D)g);
    }
    
   /* @Test
    public void createInputDialogTest(){
        gBPlot.createInputDialog(gSess);
        
    }
    
    *//*@Test
    public void getPlotListTest(){
        gBPlot.getPlotList(aList, indexList, GrafType.BOXPLOT);
        
    }*//*
    
    @Test
    public void setDeleteValuesTest(){
        GrafInputDialog gid = gBPlot.createInputDialog(gSess);
        gid.getDeleter().setPlotIndex(indexList); 
        gBPlot.setDeleteValues(3, gid, aList );
    
    }*/
    
    @Test
    public void setAndGetMarkTest(){
         gBPlot.setMark("x");  
         assertEquals(gBPlot.getMark(), "x");
    }  
    
    @Test
    public void setAndGetColorRedTest(){
        gBPlot.setGrafColor(Color.RED);
        assertEquals(gBPlot.getGrafColor(),Color.RED);
    }
    
   

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
      // gSess.dispose();
    }
}
