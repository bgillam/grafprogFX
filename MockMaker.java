
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
      
    
    public static ArrayList<GrafObject> createMockObjectList(GrafStage g){
           ArrayList<GrafObject> aList = new ArrayList<GrafObject>();
           aList.add(new GrafAxes(g));
           GrafProg gSess = new GrafProg();
           for (int i=0; i<5; i++){
               aList.add(new GrafPoint(gSess));
               aList.add(new GrafText(gSess));
               aList.add(new GrafSegment(gSess));
               aList.add(new GrafRectangle(gSess));
               aList.add(new GrafEllipse(gSess));
               aList.add(new GrafCircle(gSess));
               aList.add(new GrafScatterPlot(gSess));
               aList.add(new GrafColumnPlot(gSess));
               aList.add(new GrafBoxPlot(gSess));
               aList.add(new GrafHistogram(gSess));
               aList.add(new GrafOgive(gSess));
               aList.add(new GrafFreqPolygon(gSess));
               aList.add(new GrafFunction(gSess));
               aList.add(new GrafValue(gSess));
               aList.add(new GrafTangent(gSess));
               aList.add(new GrafChord(gSess));
               aList.add(new GrafIntegral(gSess));
               aList.add(new GrafZeros(gSess));
               
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
