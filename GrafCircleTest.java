import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;
/**
 * The test class GrafCircleTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */

public class GrafCircleTest
{
    private GrafProg  gSess;
    private GrafCircle gCircle;
    private ArrayList<GrafObject> aList;
    private ArrayList<Integer> indexList;
    /**
     * Default constructor for test class GrafPointTest
     */
    public GrafCircleTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
//    public void setUp()
//    {
//       gSess = new GrafStage();
//       gCircle = new GrafCircle(gSess, 2, 3, 4);
//       aList = MockMaker.createMockObjectList(gSess);
//       indexList = MockMaker.createMockIndexList(aList, GrafType.CIRCLE);
//    }
    
    @Test
    public void drawGrafTest(){
        assertNotNull(gCircle.getGrafColor());
        /*assertNotNull(GrafProg.getGrafPanel());
        assertNotNull(GrafProg.getGrafPanel().getGraphics());
        Graphics g = GrafProg.getGrafPanel().getGraphics();*/
        assertNotNull(GrafUI.getGrafPanel());
        assertNotNull(GrafUI.getGrafPanel().getGraphics());
        Graphics g = GrafUI.getGrafPanel().getGraphics();
        gCircle.drawGraf((Graphics2D)g);
    }
    
//    @Test
//    public void createInputDialogTest(){
//       gCircle.createInputDialog(gSess);
//
//    }
    
    /*@Test
    public void getPlotListTest(){
        gCircle.getPlotList(aList, indexList, GrafType.CIRCLE);
        
    }*/
    
//    @Test
//    public void setDeleteValuesTest(){
//        GrafInputDialog gid = gCircle.createInputDialog(gSess);
//        gid.getDeleter().setPlotIndex(indexList);
//        gCircle.setDeleteValues(3, gid, aList );
//
//    }
    
      
    @Test
    public void setAndGetColorTest(){
        gCircle.setGrafColor(Color.RED);
        assertEquals(gCircle.getGrafColor(),Color.RED);
        gCircle.setFill(Color.BLUE);
        assertEquals(gCircle.getFill(),Color.BLUE);
    }
    
  /*  @Test
    public void setAndGetCoords(){
        gCircle.setCx(1);
        gCircle.setCy(2);
        gCircle.setR(3);
        double d = .000001;
        assertEquals(gCircle.getCx(),1.0,d);
        assertEquals(gCircle.getCy(),2.0,d);
        assertEquals(gCircle.getR(),3.0,d);
        
    } */
   
   
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
//       gSess.close();
    }
}
