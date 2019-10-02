/*GrafIntegral - graphs Reinman Sum Histogram and calcs area.
 * 
 * @author Bill Gillam
 * @version 1-31-17
 */
//import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GrafIntegral extends GrafObject implements IGrafable
{
        private String functionString="";
        private GrafProg myOwner;
        private GrafSettings gStuff;
        private String mark =".";
        private double x1 = 0;
        private double x2 = 0;
        int n=100;
        Color fillColor = Color.WHITE;
        //private String yString = "";
        
   public GrafIntegral(){
       gStuff = super.initGrafObject(GrafType.INTEGRAL);
       
       }     
        

       public GrafIntegral(String yString, double firstX, double secondX, int nVal){
        this();
        setFunctionString(yString);
        x1 = firstX;
        x2 = secondX;
        n = nVal;

        //super.setGrafColor(c);
    }
       
       
   public GrafIntegral(String yString, double firstX, double secondX, int nVal, Color c, Color fillColor){
        this(yString, firstX, secondX, nVal);
        super.setGrafColor(c);
        setFillColor(fillColor);
    }
   
   @Override  
   public void drawGraf(Graphics2D gc){
       //gc.setColor(super.getGrafColor());
       double dx = (x2-x1)/n;
       double height;
       double sum=0;
       for (double left = x1; left < x2; left+=dx){
           try {
               height = (FunctionString.fValue(functionString, left) + FunctionString.fValue(functionString, left+dx))/2;
           } catch (DomainViolationException e) {
               height = 0;
           }catch (FunctionFormatException e) { height = 0; }   
           sum = sum + dx*height;
           if (height >= 0) {
               gc.setColor(getFillColor());
               GrafPrimitives.fillRect(gStuff,left, height, dx, height, gc);
               gc.setColor(super.getGrafColor());
               GrafPrimitives.grafRect(gStuff,left, height, dx, height, gc);
           }
           else {
               gc.setColor(getFillColor());
               GrafPrimitives.fillRect(gStuff,left, 0, dx, height, gc);
               gc.setColor(super.getGrafColor());
               GrafPrimitives.grafRect(gStuff,left, 0, dx, height, gc);
           }
          // gStuff.grafRect(left, 0, dx, height, gc);
           myOwner.setMessage3("Sum = "+sum);
           myOwner.setMessage2("Area from "+x1+" to "+x2+" estimated with "+n+" rectangles");
       }
       
       gc.setColor(Color.BLACK);
       //gStuff.getGrafPanel().repaint();
    }

    @Override
    public boolean isValidInput(GrafDialogController gdf){
        if (gdf.getFunctionString().equals("") && gdf.functionStringIsVisible()) return false;
        if (gdf.getX1().equals("")) return false;
        if (gdf.getX2().equals("")) return false;
        if (gdf.getNText().equals("")) return false;
        return true;
    }

    @Override
    public boolean deepEquals(GrafObject o){
        GrafIntegral gi = (GrafIntegral) o;
        if (getType() != o.getType()) return false;
        if (!getGrafColor().equals(gi.getGrafColor())) return false;
        if (!getFillColor().equals(gi.getFillColor())) return false;
        if (!functionString.equals(gi.getFunctionString())) return false;
        if (!(getX1() == gi.getX1())) return false;
        if (!(getX2() == gi.getX2())) return false;
        if (!(getN() == gi.getN())) return false;
        return true;
    }

    @Override
    public void loadObjectFields(GrafDialogController gdc){
        super.loadObjectFields(gdc);
        gdc.setFunctionString(getFunctionString());
        gdc.setX1(""+getX1());
        gdc.setX2(""+getX2());
        gdc.setDx(""+getN());
        gdc.setfillColor(getFillColor());
   }



   public void setX1(double xval){ x1 = xval; }
   public double getX1() { return x1; } 
   public void setX2(double xval){ x2 = xval; }
   public double getX2() { return x2; } 
   public void setFunctionString(String fString){ functionString = fString; }
   public String getFunctionString() { return functionString; } 
   public void setMark(String m){mark = m;}
   public String getMark(){return mark;}
   public int getN(){return n;}
   public void setN(int nVal){n = nVal;}

   public Color getFillColor(){return fillColor;}
   public void setFillColor(Color c){this.fillColor=c;}
   
   public String toString(){
       return "INTEGRAL: "+getFunctionString()+", start: "+x1+" end: "+x2+", n: "+getN()+", ";//+getGrafColor();
   }
 }
   

