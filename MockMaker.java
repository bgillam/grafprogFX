
/**
 * Write a description of class MockMaker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;

public class MockMaker
{
    
    public MockMaker()
    {
        
    }
    
    // public static GrafTable createMockTable(GrafProg gSess){
                  
            // gSess.getData().setColumnValues(1,new double[]{1,3,5,7,9, 11, 15});
    // }
      
    
    public static ArrayList<GrafObject> createMockObjectList(GrafProg gSess){
           ArrayList<GrafObject> aList = new ArrayList<GrafObject>();
           aList.add(new GrafAxes());

           for (int i=0; i<5; i++){
               aList.add(new GrafPoint());
               aList.add(new GrafText());
               aList.add(new GrafSegment());
               aList.add(new GrafRectangle());
               aList.add(new GrafEllipse());
               aList.add(new GrafCircle());
               aList.add(new GrafScatterPlot());
               aList.add(new GrafColumnPlot());
               aList.add(new GrafBoxPlot());
               aList.add(new GrafHistogram());
               aList.add(new GrafOgive());
               aList.add(new GrafFreqPolygon());
               aList.add(new GrafFunction());
               aList.add(new GrafValue());
               aList.add(new GrafTangent());
               aList.add(new GrafChord());
               aList.add(new GrafIntegral());
               aList.add(new GrafZeros());
               
           }
           return aList;
    }
    
    public static ArrayList<Integer> createMockIndexList(ArrayList<GrafObject> aList, GrafType gType){
       ArrayList<Integer> indexList = new ArrayList<Integer>();
       for (int i = 0; i<aList.size(); i++){
           
           if (aList.get(i).getType() == gType) 
               indexList.add(i);
           
       }
       return indexList;
    }
}
