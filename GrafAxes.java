
/********************************* 
*  GrafAxes for GrafProg Project *
*  @author Bill Gillam           *
*  2/25/15                       *
**********************************/
/**
 *  Axes object. Represents the x-y axes to be graphed.
 */
import java.awt.*;
//import java.io.*;

public class GrafAxes extends GrafObject 
{
    // instance variables 
    private GrafSettings gStuff;
    private Font currentFont = new Font("Ariel",Font.BOLD, 11);
      
    //Constructor 
    public GrafAxes(GrafProg gFrame)
    {
    	setGrafType(GrafType.AXES);
    	setGrafColor(Color.BLACK);
    	setMoveable(false); 
        setOwner(gFrame);
        gStuff = gFrame.getGrafSettings();
        
     }
    
    
    // draws the object. In this case: a set of axes
    public void drawGraf(Graphics2D gc){
        double xMin = gStuff.getXMin();
        double yMin = gStuff.getYMin();
        double xMax = gStuff.getXMax();
        double yMax = gStuff.getYMax();
        gc.setColor(super.getGrafColor());
        gc.setFont(currentFont);
        
        String formatString = "%."+gStuff.getDecPlaces()+"f";
        if (gStuff.showXAxis()){
            GrafPrimitives.grafLine(gStuff,xMin,0,xMax,0, gc);
            double ticLocation = Double.parseDouble(String.format(formatString, xMin));     
            while (ticLocation < xMax){
                GrafPrimitives.grafLine(gStuff,ticLocation,1/gStuff.getRatioY()*5,ticLocation,-(1/gStuff.getRatioY()*5), gc);
                if (gStuff.getShowXAxisScale()) GrafPrimitives.grafString(gStuff,ticLocation,-(1/gStuff.getRatioY()*12), String.format(formatString,ticLocation), gc);
                ticLocation = ticLocation + gStuff.getXAxisScale();
            }   
        }
        if (gStuff.showYAxis()){
            GrafPrimitives.grafLine(gStuff, 0,yMin,0, yMax,  gc); 
            //double ticLocation = yMin;
            double ticLocation = Double.parseDouble(String.format(formatString, yMin)); 
            while (ticLocation < yMax){
                GrafPrimitives.grafLine(gStuff,(1/gStuff.getRatioX()*5),ticLocation,-(1/gStuff.getRatioX()*5),ticLocation, gc);
                if (gStuff.getShowYAxisScale()) {
                    if (gStuff.getLeftScale()) GrafPrimitives.grafString(gStuff,-1/gStuff.getRatioX()*20,ticLocation, String.format(formatString,ticLocation), gc); 
                    else GrafPrimitives.grafString(gStuff,1/gStuff.getRatioX()*12,ticLocation,""+String.format(formatString, ticLocation, gc), gc);
                }
                ticLocation = ticLocation + gStuff.getYAxisScale();
            }
        }
        gc.setColor(Color.BLACK);
    }
    
    // Getters and setters 
    /* Defined in the parent class GrafObject
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