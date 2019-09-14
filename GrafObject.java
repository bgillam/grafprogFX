
/**
 * GrafObject - Abstract parent of all objects to be drawn
 * 
 * @author Bill Gillam
 * @version j1.0]
 */
//import javax.swing.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

abstract public class GrafObject implements Serializable 
{
   private GrafType grType;
   private Color grafColor = Color.BLACK; 
   private boolean moveable;
   private GrafProg myOwner;
   
   
   protected ColumnChooserPanel columnChooser;
   protected ColorRadioMarkPanel mp;
   protected JDialog jd = new JDialog();
   protected PointPanel ptPanel;
   
   public void drawGraf(Graphics2D g2D){};
   
   public GrafInputDialog createInputDialog(GrafProg gs){return null;};
   
   public void setDeleteValues(int index, GrafInputDialog caller, ArrayList<GrafObject> tempList ){};
   
   public static GrafObject createGrafObjectFromController(GrafDialogController gdc, GrafType gType){
       switch (gType){

                case TEXT: return new GrafText();
                case COLUMN: return new GrafColumnPlot();
                case BOXPLOT: return new GrafBoxPlot();
                case SCATTER:return new GrafScatterPlot();
                case HISTOGRAM: return new GrafHistogram();
                case FREQPOLYGON: return new GrafFreqPolygon();
                case OGIVE: return new GrafOgive();
                case POINT: return new GrafPoint();
                case LINESEGMENT: return new GrafSegment();
                case RECTANGLE: return new GrafRectangle();
                case ELLIPSE: return new GrafEllipse();
                case CIRCLE: return new GrafCircle();
                case FUNCTION: return new GrafFunction(gdc.getFunctionString(), gdc.getGrafColor());
                case FVALUE: return new GrafValue();
                case TANGENT: return new GrafTangent();
                case CHORD: return new GrafChord();
                case INTEGRAL: return new GrafIntegral();
                case FZERO: return new GrafZeros();
                default: return null;

        }
    }
   
   public static void createPanel(){}
   
   public boolean axesAreReversible(){
       switch (grType) {
       //case COLUMN : return true;
       case HISTOGRAM: return true;
       case BOXPLOT:  return true;
       //case SCATTER: return true; 
       case FREQPOLYGON: return true;
       case OGIVE: return true;
       }
       return false;
   }
   
   public String[] getColumnsString(){
       return GrafProg.getData().getHeaderArray();
   }
   
   
   public void setGrafType(GrafType gt){grType = gt;}
   public GrafType getType(){return grType; }
   
   public boolean isMoveable(){ return moveable; } 
   public void setMoveable(boolean tf){ moveable = tf;  }
   public boolean getMoveable(){return moveable;}
   
   public void setOwner(GrafProg owner){myOwner = owner;}
   public GrafProg getOwner(){return myOwner;}
   
   public void setGrafColor(Color c){grafColor = c;   }
   public Color getGrafColor() { return grafColor;}
    
  /* protected void addColumnChooserPanel(String[] str, boolean inputOn, boolean outputOn) {//ColumnChooserPanel addColumnChooserPanel(String[] str, boolean inputOn, boolean outputOn) {
         ColumnChooserPanel columnChooser;
         columnChooser = new ColumnChooserPanel(str, inputOn, outputOn);
         columnChooser.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
         columnChooser.setBackground(UIManager.getColor("Button.background"));
         jd.add(columnChooser, BorderLayout.NORTH);
         //return columnChooser;
    }  
    
   protected void addMarkPanel(MarkPanel mp){ //MarkPanel addMarkPanel(MarkPanel mp){
        mp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        mp.setBackground(UIManager.getColor("Button.background"));
        jd.add(mp, BorderLayout.WEST);
        //return mp;
   }*/
   
   
   
  /* public static String[] getPlotList(ArrayList<GrafObject> tempList, ArrayList<Integer> plotIndex, GrafType gType){
        String con;
        GrafObject currentObj;
        GrafDeletePanel.indexPlots(tempList, gType);   
        String[] plotListArray = new String[plotIndex.size()];
        for (int i = 0; i < plotIndex.size(); i++){
            //GrafObject 
            currentObj = tempList.get(plotIndex.get(i)); 
            plotListArray[i] = currentObj.toString(); 
        }
       return plotListArray;
 }*/
   
   /*protected static void closeGFD(GrafInputDialog gfd){
        gfd.setModal(true);
        gfd.pack();
        gfd.setVisible(true); 
   }*/
      
}
