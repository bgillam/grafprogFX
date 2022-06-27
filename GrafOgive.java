
/*  GrafOgive for GrafProg Project *
*  Store info and methods for graphing   *
*  Ogives
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
public class GrafOgive extends GrafHistogram implements IGrafable{
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
    GrafOgive(){
         super();
         gStuff = super.getGStuff();
         setGrafType(GrafType.OGIVE);

    }
    

    //constructor 
    private GrafOgive(int column){
        this();
        setColumnNumber(column);
        GrafProg.setMessage1("Histogram for Column "+columnNumber);
    }
    
    public GrafOgive(int column, double b, double e, int numCl, Color c, boolean rel){
        this(column);
        relative = rel;
        begin = b;
        end = e;
        numClasses = numCl;
        setGrafColor(c);
        byNumClasses = true;
        classLimits = GrafStats.getClassesByNumber(numClasses, begin, end);    //problem here!
        classWidth = classLimits[1] - classLimits[0];
    }

    public GrafOgive(int column, double b, double e, double classW, Color c, boolean rel){
        this(column);
        relative = rel;
        begin = b;
        end = e;
        classWidth = classW;
        setGrafColor(c);
        byNumClasses = false;
        classLimits = GrafStats.getClassesByClassSize(classW, begin, end);  
        numClasses = classLimits.length;
    }


    private GrafOgive(int column, double b, double e, int numCl, Color c, Color fillColor, boolean boundries, boolean counts, boolean rel){
        this(column);
        setFillColor(fillColor);
        setLabelAxisByBoundries(boundries);
        setDisplayCounts(counts);
        relative = rel;
        begin = b;
        end = e;
        numClasses = numCl;
        setGrafColor(c);
        byNumClasses = true;
        classLimits = GrafStats.getClassesByNumber(numClasses, begin, end);    //problem here!
        classWidth = classLimits[1] - classLimits[0];
    }

    private GrafOgive(int column, double b, double e, double classW, Color c, Color fillColor, boolean boundries, boolean counts, boolean rel){
        this(column);
        setFillColor(fillColor);
        setLabelAxisByBoundries(boundries);
        setDisplayCounts(counts);
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
        gc.setColor(super.getGrafColor());
        Double[] temp = GrafStats.getRidOfNulls(TableColumnActions.getColumnValues(columnNumber, TableUI.getData()));
        Arrays.sort(temp);
        int binCount = 0;
        int totalCount = 0;
        int i = 0;
        int upperBoundIndex = 1;
        int numValues = temp.length;
        String formatString = "%."+gStuff.getDecPlaces()+"f";
               
        int[] counts = new int[classLimits.length];
        while (i < numValues){
            //System.out.println("i = "+i+" temp[i] = "+temp[i]+"upperBoundIndex = "+upperBoundIndex+" upperLimit :"+ classLimits[upperBoundIndex]);
            if (
                temp[i] < classLimits[upperBoundIndex]){  
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
        double height = 0;
        double startH, endH, oldEndHeight, x1, y1, x2, y2, x3, y3, x4, y4;
        for (int j = 0; j < classLimits.length-1; j++){
            gc.setColor(super.getGrafColor());
            if (relative) {
                startH = height/numValues;
                endH = (height+counts[j])/numValues;
            }
            else{
                startH = height;
                endH = (height+counts[j]);
            }
            if (GrafProg.getGrafSettings().getReverseXY()){
                x1 = startH;
                y1 = classLimits[j];
                x2 = endH;
                y2 = classLimits[j+1];
                x3 = gStuff.getGrafHeight()/50;
                x4 = -x3;
                y3 = y1;
                y4 = y3;
                
                
            }
            else{
                x1 = classLimits[j];
                y1 = startH;
                x2 = classLimits[j+1];
                y2 = endH;
                x3 = x1;
                y3 = gStuff.getGrafHeight()/50;
                x4 = x1;
                y4 = -y3;


            }
            
            {
                GrafPrimitives.grafLine(gStuff,x1, y1, x2, y2, gc);
                if (displayCounts){
                    GrafPrimitives.grafString(gStuff,x1, y1, ""+endH, gc);
               }
               if (labelAxisByBoundries){
                  GrafPrimitives.grafLine(gStuff,x3, y3, x4, y4, gc);
                  GrafPrimitives.grafString(gStuff,x4, y4, ""+String.format(formatString,y4, gc), gc);
               }  
            }
           if ((j == classLimits.length - 2) && (counts[j] == 0)) break;
           height = height + counts[j];
        }
        GrafProg.setMessage2("");
        GrafBoxPlot.numBoxPlots++;
        gc.setColor(Color.BLACK);
    }


    @Override
    public GrafObject createGrafObjectFromController(GrafDialogController gdc){
        if (gdc.getNumClassButton().isSelected())
            return new GrafOgive(gdc.getColumn1ChooserColumn(), Double.parseDouble(GrafDialogController.getX1()), Double.parseDouble(GrafDialogController.getX2()),
                    gdc.getNumClasses(), gdc.getGrafColor(),   gdc.getFillColor(), gdc.getBoundariesCheckBox().isSelected(), gdc.getCountCheckBox().isSelected(),  gdc.getFNS());
        else  return new GrafOgive(gdc.getColumn1ChooserColumn(), Double.parseDouble(GrafDialogController.getX1()), Double.parseDouble(GrafDialogController.getX2()),
                Double.parseDouble(gdc.getClassWidthText()), gdc.getGrafColor(),  gdc.getFillColor(), gdc.getBoundariesCheckBox().isSelected(), gdc.getCountCheckBox().isSelected(), gdc.getFNS());
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
        return "OGIVE: Col "+getColumnNumber();//+", "+ getGrafColor();
    }
    

    /*private static GrafTable getData(){
        return GrafProg.getData();
    }*/
}
