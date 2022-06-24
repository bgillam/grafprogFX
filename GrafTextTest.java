

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
/**
 * The test class GrafTextTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */

public class GrafTextTest
{
    private GrafProg  gSess;
    private GrafText gText;
    private ArrayList<GrafObject> aList;
    private ArrayList<Integer> indexList;
    /**
     * Default constructor for test class GrafPointTest
     */
    public GrafTextTest()
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
       gText = new GrafText(2, 3, "x");
       aList = MockMaker.createMockObjectList(gSess);
       indexList = MockMaker.createMockIndexList(aList, GrafType.TEXT);
    }
    
    @Test
    public void drawGrafTest(){
        assertNotNull(gText.getGrafColor());
        assertNotNull(GrafProg.getGrafPanel());
        assertNotNull(GrafProg.getGrafPanel().getGraphics());
        Graphics g = GrafProg.getGrafPanel().getGraphics();
        gText.drawGraf((Graphics2D)g);
    }
    
   /* @Test
    public void createInputDialogTest(){
        gText.createInputDialog(gSess);
        
    }*/
    
    /*public void getPlotListTest(){
        gText.getPlotList(aList, indexList, GrafType.TEXT);
        
    }*/
    
   /* @Test
    public void setDeleteValuesTest(){
        GrafInputDialog gid = gText.createInputDialog(gSess);
        //gid.getMarkChooser().setTextString("");
        gid.getDeleter().setPlotIndex(indexList); 
        gText.setDeleteValues(3, gid, aList);
    
    }*/
  
    @Test
    public void setAndGetMarkTest(){
         gText.setText("x");  
         assertEquals(gText.getText(), "x");
    }  
    
    @Test
    public void setAndGetColorRedTest(){
        gText.setGrafColor(Color.RED);
        assertEquals(gText.getGrafColor(),Color.RED);
    }
    
    @Test
    public void setAndGetX1Y1Test(){
        gText.setX(5);
        gText.setY(7);
        double d = 0.000001;
        assertEquals(gText.getX(),5,d);
        assertEquals(gText.getY(),7,d);
        
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
