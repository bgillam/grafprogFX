
/**************************************** 
*  GrafSettings for GrafProg Project      *
*  settings for graphing window
*  and virtual screen conversions
*  @author Bill Gillam                  *
*  4/4/18                              *
*****************************************/
/**
* Created for each GrafProg 
* Holds info for graphingwindow. 
*/
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.io.Serializable;

import javax.swing.*;

//class header
public class GrafSettings implements Serializable
{  
   //Virtual window properties
   private double xMin=-10;
   private double xMax=10;
   private double yMin=-10;
   private double yMax=10;
   private double xAxisScale=1;
   private double yAxisScale=1;
   private int decPlaces = 1;
      
   //boolean flags 
   private boolean showYAxis=true;
   private boolean showXAxis=true;
   private boolean showYAxisScale=true;
   private boolean showXAxisScale=true;
   private boolean reverseXY=false;
   private boolean classesForScale=true;
   private boolean leftScale=false;
   private boolean autoScale=false;
      
   //Frame, Panel and Canvas references
   private GrafProg myFrame;
   private GrafPanel myPanel;
 
   //enums
   public enum ScaleFormat {EXP,DEC};
   private ScaleFormat scaleFormat = ScaleFormat.DEC;
   public enum ScaleProcedure {TEN_POWER, FROM_RANGE, SET_SCALES };
   private ScaleProcedure scaleProcedure = ScaleProcedure.SET_SCALES;
         
   //Constructor for objects of class GrafStuff
    public GrafSettings(GrafProg frame)
    {
      myFrame = frame; 
      myPanel = frame.getGrafPanel();
    }
      
    //prints out instance variable values    
    public String toString(){
      return "xMin: "+xMin+" xMax:"+xMax+" yMin"+yMin+" yMax"+yMax+" xScale:"+xAxisScale+" yAxisScale"+yAxisScale+" Dec places"+decPlaces+" scaleformat:"+scaleFormat+" ScaleProcedure:"+scaleProcedure
              +" showYAxis:"+showYAxis+" showXAxis"+showXAxis+" showYscale:"+showYAxisScale+" showXScale:"+showXAxisScale+" reverseXY:"+reverseXY+" classesscale"+classesForScale+" leftscale"
              +leftScale+" auto:"+autoScale;
     } 
    
   //virtual screen math
   public double getGrafWidth(){return xMax - xMin;} 
   public double getGrafHeight(){return yMax - yMin;}
   public int getPanelWidth(){ return myPanel.getWidth();}
   public int getPanelHeight(){return myPanel.getHeight();}
   public double getRatioX(){ return myPanel.getWidth()/getGrafWidth();}
   public double getRatioY(){ return myPanel.getHeight()/getGrafHeight();}
   public Point virtToFrame(double x, double y){return new Point((int)((x-xMin)*getRatioX()),(int)((yMax-y)*getRatioY())); } //tab)
   public int virtToFrameX(double x){return (int)((x-xMin)*getRatioX());}
   public int virtToFrameY(double y){return (int)((yMax-y)*getRatioY());}
       
    //Setters and Getters
    
    //Sets the window to 10 x 10 and scales to 1
   public void setStandardAxes(){
       setXMin(-10); setXMax(10); setYMin(-10); setYMax(10); setXAxisScale(1); setYAxisScale(1); 
   }
    
    public GrafProg getOwner(){return myFrame;}
    public void setOwner(GrafProg g){myFrame = g;}
    public void setGrafPanel(GrafPanel gp){myPanel = gp;}
    public GrafPanel getGrafPanel(){return myPanel;}
    public boolean getShowYAxisScale(){return showYAxisScale;}
    public boolean getShowXAxisScale(){return showXAxisScale;}
    public void toggleShowYScale(){if (showYAxisScale) showYAxisScale=false; else showYAxisScale=true;}
    public void toggleShowXScale(){if (showXAxisScale) showXAxisScale=false; else showXAxisScale=true;}
    public boolean showYAxis(){return showYAxis;}
    public boolean showXAxis(){return showXAxis;}
    public void toggleShowXAxis(){if (showXAxis) showXAxis=false; else showXAxis=true;} 
    public void toggleShowYAxis(){if (showYAxis) showYAxis=false; else showYAxis=true;} 
    public boolean getLeftScale(){return leftScale;}
    public void toggleLeftFlag(){if (leftScale)leftScale=false; else leftScale=true;}
    public void toggleReverseXY(){ if (reverseXY) reverseXY = false; else reverseXY = true;}
    public double getXMin(){return xMin;}
    public void setXMin(double x){xMin=x;}
    public double getXMax() {return xMax;}
    public void setXMax(double r){xMax=r;}
    public double getYMin(){return yMin;}
    public void setYMin(double r){yMin=r;}
    public double getYMax(){return yMax;}
    public void setYMax(double r){yMax = r;}
    public double getXAxisScale(){return xAxisScale;}
    public double getYAxisScale(){return yAxisScale;}
    public void setXAxisScale(double r){xAxisScale=r;}
    public void setYAxisScale(double r){yAxisScale=r;}
    public int getDecPlaces(){return decPlaces;}
    public void setDecPlaces(int dp){decPlaces = dp;}
    public ScaleFormat getScaleFormat(){return scaleFormat;}
    public void setScaleFormat(ScaleFormat sf){scaleFormat=sf;}
    public ScaleProcedure getScaleProcedure(){return scaleProcedure;}
    public void setScaleProcedure(ScaleProcedure sp){scaleProcedure = sp;}
    public boolean getClassesForScale(){return classesForScale;}
    public void setClassesForScale(boolean tf){classesForScale = tf;}
    public boolean getReverseXY(){return reverseXY;}
    public void setReversXY(boolean tf) {reverseXY = tf;}
}