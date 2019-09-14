

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

public class GrafScatterplotTest
{
    private GrafProg  gSess;
    private GrafScatterPlot gScat;
    private ArrayList<GrafObject> aList;
    private ArrayList<Integer> indexList;
    /**
     * Default constructor for test class GrafPointTest
     */
    public GrafScatterplotTest()
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
       gScat = new GrafScatterPlot(gSess);
       aList = MockMaker.createMockObjectList(gSess);
       indexList = MockMaker.createMockIndexList(aList, GrafType.SCATTER);
                 
    }
    
    @Test
    public void drawGrafTest(){
        assertNotNull(gScat.getGrafColor());
        assertNotNull(gSess.getGrafPanel());
        assertNotNull(gSess.getGrafPanel().getGraphics());
        Graphics g = gSess.getGrafPanel().getGraphics();
        gScat.drawGraf((Graphics2D)g);
    }
    
//    @Test
//    public void createInputDialogTest(){
//        gScat.createInputDialog(gSess);
//
//    }
    
   /* public void getPlotListTest(){
        gScat.getPlotList(aList, indexList, GrafType.SCATTER);
        
    }*/
    
//    @Test
//    public void setDeleteValuesTest(){
//        GrafInputDialog gid = gScat.createInputDialog(gSess);
//        gid.getDeleter().setPlotIndex(indexList);
//        gScat.setDeleteValues(3, gid, aList );
//
//    }
    
    @Test
    public void setAndGetMarkTest(){
         gScat.setMark("x");  
         assertEquals(gScat.getMark(), "x");
    }  
    
    @Test
    public void setAndGetColorRedTest(){
        gScat.setGrafColor(Color.RED);
        assertEquals(gScat.getGrafColor(),Color.RED);
    }
    
    @Test
    public void setAndGetConnectedAndColumns(){
        gScat.setInputColumnNumber(1);
        assertEquals(gScat.getInputColumnNumber(),1);
        gScat.setOutputColumnNumber(2);
        assertEquals(gScat.getOutputColumnNumber(),2);
        gScat.setConnected(true);
        assertEquals(gScat.getConnected(),true);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {

        //gSess.close();
    }
}
