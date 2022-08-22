package GrafProg; /****************************************
*  GrafStuff  for GrafProg.GrafProg Project      *
*  Basic graph routines 
*  @author Bill Gillam                  *
*  2/25/15                              *
*****************************************/
/**
* Basic Point, Line, Text and Shape drawing routines implementing virtual to screen coordinates.
*  
*/

import GrafProg.GrafUI.GrafPanel;
import GrafProg.GrafUI.GrafSettings;

import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;

//class header
public class GrafPrimitives implements Serializable
{  
      
   //Frame, Panel and Canvas references
   private GrafProg myFrame;
   private GrafPanel myPanel;

    /*public Plot.GrafProg.GrafPrimitives()
    {
        //myFrame = GrafProg.GrafProg.;
        myPanel = GrafProg.GrafProg.getGrafPanel();
        //mySettings = frame.getGrafSettings();
    }
      
   //Constructor for objects of class GrafStuff
    public Plot.GrafProg.GrafPrimitives(GrafProg.GrafProg frame)
    {
      myFrame = frame;  
      myPanel = frame.getGrafPanel();
      //mySettings = frame.getGrafSettings();
    }*/
       
     
    //Graph a point at (x,y) in the virtual plane
    public static void grafPoint(GrafSettings mySettings, double x, double y, Graphics2D gc){
       grafLine(mySettings,x,y,x,y , gc);
    //new Line2D.Double(x1, y1, x2, y2
    }
    
    //graph a line between (x,y) and (x2,y2) 
    public static void grafLine(GrafSettings mySettings, double x1, double y1, double x2, double y2, Graphics2D gc){
        //myPanel.getGrafCanvas().draw(new Line2D.Double(virtToFrame(x,y).getX(),virtToFrame(x,y).getY(),virtToFrame(x2,y2).getX(),virtToFrame(x2,y2).getY()));
        gc.draw(new Line2D.Double(mySettings.virtToFrameX(x1),mySettings.virtToFrameY(y1),mySettings.virtToFrameX(x2),mySettings.virtToFrameY(y2)));
    }
    
    public static void grafRect(GrafSettings mySettings, double startX, double startY, double rWidth, double rHeight, Graphics2D gc){
        startY = -startY;
        int newWidth = mySettings.virtToFrameX(startX+rWidth) - mySettings.virtToFrameX(startX);
        int newHeight = Math.abs(mySettings.virtToFrameY(startY+rHeight) - mySettings.virtToFrameY(startY));
        gc.draw(new Rectangle2D.Double(mySettings.virtToFrameX(startX), mySettings.virtToFrameY(-startY), newWidth, newHeight)) ; 
    }
    
    public static void fillRect(GrafSettings mySettings, double startX, double startY, double rWidth, double rHeight, Graphics2D gc){
        startY = -startY;
        int newWidth = mySettings.virtToFrameX(startX+rWidth) - mySettings.virtToFrameX(startX);
        int newHeight = Math.abs(mySettings.virtToFrameY(startY+rHeight) - mySettings.virtToFrameY(startY));
        gc.fill(new Rectangle2D.Double(mySettings.virtToFrameX(startX), mySettings.virtToFrameY(-startY), newWidth, newHeight)) ; 
    }
    
    public static void grafEllipse(GrafSettings mySettings, double startX, double startY, double rWidth, double rHeight, Graphics2D gc){
        startY = -startY;
        int newWidth = mySettings.virtToFrameX(startX+rWidth) - mySettings.virtToFrameX(startX);
        int newHeight = Math.abs(mySettings.virtToFrameY(startY+rHeight) - mySettings.virtToFrameY(startY));
        gc.draw(new Ellipse2D.Double(mySettings.virtToFrameX(startX), mySettings.virtToFrameY(-startY), newWidth, newHeight)) ; 
    }
    
    public static void fillEllipse(GrafSettings mySettings, double startX, double startY, double rWidth, double rHeight, Graphics2D gc){
        //System.out.println("this got called");
        startY = -startY;
        int newWidth = mySettings.virtToFrameX(startX+rWidth) - mySettings.virtToFrameX(startX);
        int newHeight = Math.abs(mySettings.virtToFrameY(startY+rHeight) - mySettings.virtToFrameY(startY));
        gc.fill(new Ellipse2D.Double(mySettings.virtToFrameX(startX), mySettings.virtToFrameY(-startY), newWidth, newHeight)) ; 
    }
    
    //Graph a String at (x,y)
    public static void grafString(GrafSettings mySettings, double x, double y, String s, Graphics2D gc){
       //myPanel.getGrafCanvas().drawString(s, (float)virtToFrame(x,y).getX() , (float)virtToFrame(x,y).getY());
       //gc.drawString(s, (float)virtToFrame(x,y).getX() , (float)virtToFrame(x,y).getY());
        gc.drawString(s, (float)mySettings.virtToFrameX(x) , (float)mySettings.virtToFrameY(y));
   }
   
    public static void  grafString(GrafSettings mySettings, double x, double y, String s, Graphics2D gc, Color c){
       //myPanel.getGrafCanvas().drawString(s, (float)virtToFrame(x,y).getX() , (float)virtToFrame(x,y).getY());
       //gc.drawString(s, (float)virtToFrame(x,y).getX() , (float)virtToFrame(x,y).getY());
        Color tempC = gc.getColor();
        gc.setColor(c);
        gc.drawString(s, (float)mySettings.virtToFrameX(x) , (float)mySettings.virtToFrameY(y));
        gc.setColor(tempC);
        
        
   }
    
   public static void drawCircle(GrafSettings mySettings, double cX, double cY, double r, Graphics2D gc){
       grafEllipse(mySettings,cX-r, cY+r, 2*r, 2*r, gc );
   }
   
   public static void fillCircle(GrafSettings mySettings, double cX, double cY, double r, Graphics2D gc){
       fillEllipse(mySettings,cX-r, cY+r, 2*r, 2*r, gc );
   }
    
    //public void grafShape(double x, double y,
}  