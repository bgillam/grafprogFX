
/*  GrafFreqPolygon for GrafProg Project *
*  Store info and methods for graphing   *
*  Frequncy Polygons
*  @author Bill Gillam                   *
*  2/3/17                                *
*********************************        */
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;




//Class Header
public class GrafFreqPolygon extends GrafHistogram implements IGrafable{
    //Instance Variables
    private int columnNumber;
    private GrafProg myOwner;
    private GrafSettings gStuff;
    private GrafTable table;
    //private FrequencyChartDialog fcd;
    
    private boolean relative = false;
    private boolean labelAxisByBoundries = true;
    private boolean byNumClasses = true;
    private boolean displayCounts = true;
    private double  begin;
    private double  end;
    private int numClasses = 10;
    private double classWidth = (begin - end)/10;
    private Color fill = Color.WHITE;
    private boolean fillFlag = false;
    private double[] classLimits;
    private double[] counts;
    
    
        
    //Constructor
    
    public GrafFreqPolygon(){
     setGrafType(GrafType.FREQPOLYGON);
     setMoveable(false);
     setGrafColor(Color.BLACK);
     setColumnNumber(1);
    }
    public GrafFreqPolygon(GrafProg sess){
        super();
        setGrafType(GrafType.FREQPOLYGON);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
        table = myOwner.getData();//sess.setMessage1("Histogram for Column "+columnNumber);
    }
    
    //constructor 
    public GrafFreqPolygon(GrafProg sess, int column){
        this(sess);
        setColumnNumber(column);
        sess.setMessage1("Histogram for Column "+columnNumber);
    }
    
    public GrafFreqPolygon(GrafProg sess, int column, double b, double e, int numCl, Color c, boolean rel){
        this(sess, column);
        relative = rel;
        begin = b;
        end = e;
        numClasses = numCl;
        setGrafColor(c);
        byNumClasses = true;
        classLimits = GrafStats.getClassesByNumber(numClasses, begin, end);    //problem here!
        classWidth = classLimits[1] - classLimits[0];
    }
    public GrafFreqPolygon(GrafProg sess, int column, double b, double e, double classW, Color c, boolean rel){
        this(sess, column);
        relative = rel;
        begin = b;
        end = e;
        classWidth = classW;
        setGrafColor(c);
        byNumClasses = false;
        classLimits = GrafStats.getClassesByClassSize(classW, begin, end);  
        numClasses = classLimits.length;
    }
   
    //drawGraf overriding method in parent GrafObject
    @Override
    public void drawGraf(Graphics2D gc){
        //double xMin = gStuff.getXMin();
        //double xMax = gStuff.getXMax();
        
             
        
        gc.setColor(super.getGrafColor());
        Double[] temp = GrafStats.getRidOfNulls(myOwner.getData().getColumnValues(columnNumber));
        Arrays.sort(temp);
        int binCount = 0;
        int totalCount = 0;
        int i = 0;
        int upperBoundIndex = 1;
        int numValues = temp.length;
        String formatString = "%."+gStuff.getDecPlaces()+"f";
               
        double[] counts = new double[classLimits.length];
        while (i < numValues){
            if (temp[i] < classLimits[upperBoundIndex]){  
                binCount++;
                i++;
            }
            else{ 
                counts[upperBoundIndex-1] = binCount;
                binCount = 0;
                upperBoundIndex++;
            }
            
        }
        counts[upperBoundIndex-1] = binCount;
        boolean start = true;       
        double height1 = 0;
        double height2 = 0;
        for (int j = 0; j < classLimits.length-1; j++){
            if (relative){ 
                height2 = counts[j]/numValues; 
                if (!start) height1 =  counts[j-1]/numValues;
            }
            else{
                height2 = counts[j]; 
                if (!start) height1 = counts[j-1];
            }
            //if (fillFlag) {gc.setColor(fill); gStuff.fillRect(classLimits[j], counts[j]/numValues, classWidth, counts[j]/numValues, gc );} gc.setColor(super.getGrafColor());
            if (myOwner.getGrafSettings().getReverseXY()){
                if (start) {GrafPrimitives.grafLine(gStuff,0, classLimits[j]-classWidth/2,  height2, classLimits[j]+classWidth/2, gc); start = false;}
                else  GrafPrimitives.grafLine(gStuff,height1, classLimits[j]-classWidth/2 ,  height2, classLimits[j]+classWidth/2, gc);
                if (j==classLimits.length-2) GrafPrimitives.grafLine(gStuff,height2, classLimits[j]+classWidth/2,  0, classLimits[j+1]+classWidth/2, gc);
                if (displayCounts){
                    GrafPrimitives.grafString(gStuff,counts[j]+gStuff.getGrafHeight()/50, classLimits[j], ""+height2, gc);
                }
            }
            else{
                if (start) {GrafPrimitives.grafLine(gStuff,classLimits[j], 0, (classLimits[j]+classLimits[j+1])/2, height2, gc); start = false;}
                else  GrafPrimitives.grafLine(gStuff,(classLimits[j-1]+classLimits[j])/2 , height1 , (classLimits[j]+classLimits[j+1])/2, height2 , gc);
                if (j==classLimits.length-2) GrafPrimitives.grafLine(gStuff,classLimits[j]+classWidth/2, height2, classLimits[j+1]+classWidth/2, 0, gc);
                if (displayCounts){
                    GrafPrimitives.grafString(gStuff,classLimits[j], height2+gStuff.getGrafHeight()/50, ""+counts[j], gc);
                }
                gc.setColor(super.getGrafColor());
                if (labelAxisByBoundries){
                    GrafPrimitives.grafLine(gStuff,classLimits[j], gStuff.getGrafHeight()/50, classLimits[j], -gStuff.getGrafHeight()/50, gc);
                    GrafPrimitives.grafString(gStuff,classLimits[j], -gStuff.getGrafHeight()/25, ""+String.format(formatString, classLimits[j], gc), gc);
                }
            }
                
        }
       //gStuff.grafLine(GrafStats.getMin(table.getColumnValues(columnNumber)), , x2, y2, gc);
        myOwner.setMessage2("");
        myOwner.incrementBoxPlotsPlotted();     
        gc.setColor(Color.BLACK);
    }

       
        private static void saveFreqPolygon(GrafProg gs, GrafInputDialog gfd){
            int col = gfd.getColumnChooser().getInputColumn();
            if (gfd.getFinalSave() == true && col == 0) return; 
            addFreqPoly(gs, gfd);
            gfd.getColumnChooser().setInputIndex(0);
    
    }
    
    private static void addFreqPoly(GrafProg gs, GrafInputDialog gfd){
              int input = gfd.getInput();
              if (input == 0) return;
              GrafFreqPolygon gfpPlot;
              if (gfd.getHistoPanel().numClassesChecked()){
                  gfpPlot = new GrafFreqPolygon(gs, input, gfd.getHistoPanel().getBegin(), gfd.getHistoPanel().getEnd(), gfd.getHistoPanel().getNumClasses(), gfd.getMarkChooser().getGrafColor(), gfd.getHistoPanel().relativeHisto());
              }else
              {
                  gfpPlot = new GrafFreqPolygon(gs, input, gfd.getHistoPanel().getBegin(), gfd.getHistoPanel().getEnd(), gfd.getHistoPanel().getClassSize(), gfd.getMarkChooser().getGrafColor(), gfd.getHistoPanel().relativeHisto());
              }
              // if (markChooser.verticalChecked()) gPlot.myOwner;
              gfd.getTempList().add(gfpPlot);
    
    }
    
    //Setters and Getters
    public void setColumnNumber(int c){ columnNumber = c;}
    public int getColumnNumber(){ return columnNumber;}
    public void setFillFlag(boolean tf){
          fillFlag = tf;
    }
    public boolean getFillFlag(){
        return fillFlag;
    }
    public Color getFill(){
        return fill;
    }
    public void setFill(Color c){
        fill = c;
    }
    public double getBegin(){
        return begin;
    }
    public double getEnd(){
        return end;
    }
    public boolean getByNumClassChecked(){
        return byNumClasses;
    }
    public int getNumClasses(){
            return numClasses;
    }
    public double getClassWidth(){
        return classWidth;
    }
    public boolean relativeHisto(){
        return relative;
    }
    public void setRelativeHisto(boolean tf){
        relative = tf;
    }
    public void setShowCounts(boolean tf){
        displayCounts = tf;
    }
    public boolean getShowCounts(){
        return displayCounts;
    }
    public boolean labelAxis(){
        return labelAxisByBoundries;
    }
    public void setLabelAxis(boolean tf){
        labelAxisByBoundries = tf;
    }
   
    public String toString(){
        return "FREQPOLYGON: Col "+getColumnNumber();//+", "+ getGrafColor();
    }
    
    /* Setters and Getters from Parent GrafObject
     *  public void drawGraf(Graphics2D g2D){};
   
        public void setGrafType(GrafProg.GrafType gt){grafType = gt;}
        public GrafProg.GrafType getType(){return grafType; }
   
        public boolean isMoveable(){ return moveable; } 
        public void setMoveable(boolean tf){ moveable = tf;  }
        public boolean getMoveable(){return moveable;}
   
        public void setOwner(GrafProg owner){myOwner = owner;}
        public GrafProg getOwner(){return myOwner;}
   
        public void setGrafColor(Color c){grafColor = Color.BLACK;   }
        public Color getGrafColor() { return grafColor;}
     */
    
}
