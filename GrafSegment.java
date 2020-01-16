/*GrafSegment - graphs a line segment
 * 
 * @author Bill Gillam
 * @version 1/27/17]
 */
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;



public class GrafSegment extends GrafObject implements IGrafable
{
        private String functionString="";
        private GrafProg myOwner;
        private GrafSettings gStuff;
        public enum EndType{RIGHTARROW, LEFTARROW, DOUBLEARROW, NOARROW}     
        private EndType ends = EndType.NOARROW;
        private double x1 = 0;
        private double x2 = 0;
        private double y1 = 0;
        private double y2 = 0;
        private String mark =".";
        //private String yString = "";
        
        
   public GrafSegment(){
       gStuff = gStuff = super.initGrafObject(GrafType.LINESEGMENT);
        
       }
        

   public GrafSegment(double x1, double y1, double x2, double y2){
        this();
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
    }
    
    public GrafSegment(double x1, double y1, double x2, double y2, Color gColor){
        this(x1, y1, x2, y2);
        setGrafColor(gColor);
    }

    public GrafSegment(double x1, double y1, double x2, double y2, Color gColor, String mark){
        this(x1, y1, x2, y2, gColor);
        setMark(mark);
    }
   
   
   @Override
   public void drawGraf(Graphics2D gc){
       gc.setColor(super.getGrafColor());
       GrafPrimitives.grafString(gStuff,x1,y1,getMark(),gc);
       GrafPrimitives.grafString(gStuff,x2,y2,getMark(),gc);
       GrafPrimitives.grafLine(gStuff,x1, y1, x2, y2 ,gc);
       gc.setColor(Color.BLACK);
   }

    @Override
    public boolean isValidInput(GrafDialogController gdf){
       boolean ok = true;
        //if (gdf.getFunctionString().equals("") && gdf.functionStringIsVisible()) return false;
        if (gdf.getX1().equals("")) return false;
        if (gdf.getX2().equals("")) return false;
        if (gdf.getY1().equals("")) return false;
        if (gdf.getY2().equals("")) return false;
        if (!GrafInputHelpers.isDouble(gdf.getX1())) {
            GrafInputHelpers.setTextFieldColor(gdf.getX1TextField(), "red" );
            ok = false;
        }
        if (!GrafInputHelpers.isDouble(gdf.getX2())) {
            GrafInputHelpers.setTextFieldColor(gdf.getX2TextField(), "red" );
            ok = false;
        }
        if (!GrafInputHelpers.isDouble(gdf.getY1())) {
            GrafInputHelpers.setTextFieldColor(gdf.getY1TextField(), "red" );
            ok = false;
        }
        if (!GrafInputHelpers.isDouble(gdf.getY2())) {
            GrafInputHelpers.setTextFieldColor(gdf.getY2TextField(), "red" );
            ok = false;
        }
        return ok;
    }

    @Override
    public boolean deepEquals(GrafObject o){
        GrafSegment gc = (GrafSegment) o;
        if (getType() != o.getType()) return false;
        if (!getGrafColor().equals(gc.getGrafColor())) return false;
        if (!(getX1() == gc.getX1())) return false;
        if (!(getX2() == gc.getX2())) return false;
        if (!(getY1() == gc.getY1())) return false;
        if (!(getY2() == gc.getY2())) return false;
        if (!(getX1() == gc.getX1())) return false;
        if (!getMark().equals(gc.getMark())) return false;
        return true;
    }

    @Override
    public void loadObjectFields(GrafDialogController gdc){
        super.loadObjectFields(gdc);
        gdc.setX1(""+getX1());
        gdc.setX2(""+getX2());
        gdc.settDialogMark(getMark());
    }

    @Override
    public void autoRange(){
        double max, min;
        if (getY1() < getY2()){ max = getY2(); min = getY1();} else {max = getY1(); min = getY2(); }
        GrafProg.getGrafSettings().setYMax(max+GrafProg.getGrafSettings().getTenthWindowY());
        GrafProg.getGrafSettings().setYMin(min-GrafProg.getGrafSettings().getTenthWindowY());
    }

    @Override
    public GrafObject createGrafObjectFromController(GrafDialogController gdc){
        return new GrafSegment(Double.parseDouble(gdc.getX1()),
                Double.parseDouble(gdc.getY1()), Double.parseDouble(gdc.getX2()), Double.parseDouble(gdc.getY2()), gdc.getGrafColor(), gdc.getDialogMark());
    }

   public void setX1(double xval){ x1 = xval; }
   public double getX1() { return x1; }
   public void setX2(double xval){ x2 = xval; }
   public double getX2() { return x2; } 
   public void setY1(double xval){ y1 = xval;}
   public double getY1() { return y1; }
   public void setY2(double xval){ y2 = xval; }
   public double getY2() { return y2; } 
   public void setEnds(EndType et){ ends= et;}
   public EndType getEnds(){return ends;}
   
   public String toString(){
       return "LINESEGMENT: ("+getX1()+", "+getY1()+"); ("+getX2()+", "+getY2();
       //+               " "+getGrafColor()+")";
   }
   
   
    public void setMark(String m){mark = m;}
    public String getMark(){return mark;}
   
 }
   
 

