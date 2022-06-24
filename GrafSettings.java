
/* ***************************************
*  GrafSettings for GrafProg Project      *
*  settings for graphing window
*  and virtual screen conversions
*  @author Bill Gillam                  *
*  4/4/18                              *
*****************************************/
/*
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
   private GrafProg myStage;
   private GrafPanel myPanel;
 
   //enums
   public enum ScaleFormat {EXP,DEC}

    private ScaleFormat scaleFormat = ScaleFormat.DEC;
   public enum ScaleProcedure {TEN_POWER, FROM_RANGE, SET_SCALES }

    private ScaleProcedure scaleProcedure = ScaleProcedure.SET_SCALES;

   //Constructor for objects of class GrafStuff
    public GrafSettings()
    {
      //myStage = gs;
      myPanel = GrafProg.getGrafPanel();
    }
      
    //prints out instance variable values    
    public String toString(){
      return "xMin: "+xMin+" xMax:"+xMax+" yMin"+yMin+" yMax"+yMax+" xScale:"+xAxisScale+" yAxisScale"+yAxisScale+" Dec places"+decPlaces+" scaleformat:"+scaleFormat+" ScaleProcedure:"+scaleProcedure
              +" showYAxis:"+showYAxis+" showXAxis"+showXAxis+" showYscale:"+showYAxisScale+" showXScale:"+showXAxisScale+" reverseXY:"+reverseXY+" classesscale"+classesForScale+" leftscale"
              +leftScale+" auto:"+autoScale;
     } 
    
   //virtual screen math
   private double getGrafWidth(){return xMax - xMin;}
   double getGrafHeight(){return yMax - yMin;}
   int getPanelWidth(){ return myPanel.getWidth();}
   int getPanelHeight(){return myPanel.getHeight();}
   double getRatioX(){ return myPanel.getWidth()/getGrafWidth();}
   double getRatioY(){ return myPanel.getHeight()/getGrafHeight();}
   public Point virtToFrame(double x, double y){return new Point((int)((x-xMin)*getRatioX()),(int)((yMax-y)*getRatioY())); } //tab)
   int virtToFrameX(double x){return (int)((x-xMin)*getRatioX());}
   int virtToFrameY(double y){return (int)((yMax-y)*getRatioY());}
       
    //Setters and Getters
    
    //Sets the window to 10 x 10 and scales to 1
    void setStandardAxes(){
       setXMin(-10); setXMax(10); setYMin(-10); setYMax(10); setXAxisScale(1); setYAxisScale(1); 
   }

   public double getTenthWindowY(){
        return (getYMax() - getYMin())/10;
   }

   public double getTenthWindowX(){
        return (getXMax()- getXMin())/10;
   }
    
    public GrafProg getOwner(){return myStage;}
    public void setOwner(GrafProg g){myStage = g;}
    public void setGrafPanel(GrafPanel gp){myPanel = gp;}
    public GrafPanel getGrafPanel(){return myPanel;}
    boolean getShowYAxisScale(){return showYAxisScale;}
    boolean getShowXAxisScale(){return showXAxisScale;}
    public void toggleShowYScale(){
        showYAxisScale= !showYAxisScale;
    }
    public void toggleShowXScale(){
        showXAxisScale= !showXAxisScale;
    }

    void setShowXAxisScale(boolean tf) {this.showXAxisScale = tf;}
    void setShowYAxisScale(boolean tf) {this.showYAxisScale = tf;}

    boolean showYAxis(){return showYAxis;}
    void setShowYAxis(boolean tf){showYAxis=tf;}
    boolean showXAxis(){return showXAxis;}
    void setShowXAxis(boolean tf){showXAxis=tf;}
    public void toggleShowXAxis(){
        showXAxis= !showXAxis;
    }
    public void toggleShowYAxis(){
        showYAxis= !showYAxis;
    }
    boolean getLeftScale(){return leftScale;}
    void setLeftScale(boolean tf){leftScale=tf;}
    public void toggleLeftFlag(){
        leftScale= !leftScale;
    }
    void toggleReverseXY(){
        reverseXY = !reverseXY;
    }
    public double getXMin(){return xMin;}
    void setXMin(double x){xMin=x;}
    public double getXMax() {return xMax;}
    void setXMax(double r){xMax=r;}
    double getYMin(){return yMin;}
    public void setYMin(double r){yMin=r;}
    double getYMax(){return yMax;}
    public void setYMax(double r){yMax = r;}
    double getXAxisScale(){return xAxisScale;}
    double getYAxisScale(){return yAxisScale;}
    void setXAxisScale(double r){xAxisScale=r;}
    void setYAxisScale(double r){yAxisScale=r;}
    int getDecPlaces(){return decPlaces;}
    void setDecPlaces(int dp){decPlaces = dp;}
    ScaleFormat getScaleFormat(){return scaleFormat;}
    void setScaleFormat(ScaleFormat sf){scaleFormat=sf;}
    ScaleProcedure getScaleProcedure(){return scaleProcedure;}
    void setScaleProcedure(ScaleProcedure sp){scaleProcedure = sp;}
    public boolean getClassesForScale(){return classesForScale;}
    public void setClassesForScale(boolean tf){classesForScale = tf;}
    boolean getReverseXY(){return reverseXY;}
    void setReversXY(boolean tf) {reverseXY = tf;}
    boolean isAutoScale(){return autoScale;}
    void setAutoScale(boolean tf){autoScale = tf;}
}