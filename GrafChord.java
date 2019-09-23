/*GrafChord - graphs a chord on a curve
 * 
 * @author Bill Gillam
 * @2-3-17
 */
//import javax.swing.*;
import java.io.*;
import java.awt.*;;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GrafChord extends GrafObject implements IGrafable
{
        private String functionString="";
        private GrafProg myOwner;
        private GrafSettings gStuff;
        private String mark =".";
        private double x1 = 0;
        private double y1 = 0;
        private double x2 = 0;
        private double y2 = 0;
        boolean segment = true;
        //private String yString = "";
        
   public GrafChord(){
        gStuff = super.initGrafObject(GrafType.CHORD);
   }
        

   public GrafChord(String yString, double firstX, double secondX){
        this();
        setFunctionString(yString);
        x1 = firstX;
        x2 = secondX;
        calcY1();
        calcY2();
    }
    
    public GrafChord(String yString, double firstX, double secondX, Color c, String m){
        this(yString, firstX, secondX);
        setGrafColor(c);
        setMark(m);
        
    }
   
   @Override
   public void drawGraf(Graphics2D gc){
       gc.setColor(super.getGrafColor());
       GrafPrimitives.grafString(gStuff,x1, y1, getMark() , gc);
       GrafPrimitives.grafString(gStuff,x2, y2, getMark(), gc);
       if (segment) GrafPrimitives.grafLine(gStuff,x1, y1, x2, y2 ,gc);
       else {
           double slope = (y2-y1)/(x2-x1);
           double yForXMin = slope*(gStuff.getXMin()-x1)+y1;
           double yForXMax = slope*(gStuff.getXMax()-x1)+y1;
           GrafPrimitives.grafLine(gStuff,gStuff.getXMin(), yForXMin, gStuff.getXMax(), yForXMax ,gc);
       }
       gc.setColor(Color.BLACK);
       //gStuff.getGrafPanel().repaint();
    }


    @Override
    public boolean isValidInput(GrafDialogController gdf){
        if (gdf.getFunctionString().equals("") && gdf.functionStringIsVisible()) return false;
        if (gdf.getX1().equals("")) return false;
        if (gdf.getX2().equals("")) return false;
        return true;
    }

    @Override
    public boolean deepEquals(GrafObject o){
        GrafChord gc = (GrafChord) o;
        if (getType() != o.getType()) return false;
        if (!getGrafColor().equals(gc.getGrafColor())) return false;
        if (!functionString.equals(gc.getFunctionString())) return false;
        if (!(getX1() == gc.getX1())) return false;
        if (!(getX2() == gc.getX2())) return false;
        return true;
    }

    @Override
    public void loadObjectFields(GrafDialogController gdc){
        super.loadObjectFields(gdc);
        gdc.setFunctionString(getFunctionString());
        gdc.setX1(""+getX1());
        gdc.setX2(""+getX2());
        gdc.settDialogMark(getMark());
    }



   public void setX1(double xval){ x1 = xval; }
   public double getX1() { return x1; } 
   public void setX2(double xval){ x2 = xval; }
   public double getX2() { return x2; } 
   /*public void sety1(double xval){ y1 = xval; }
   public double gety1() { return y1; } 
   public void sety2(double xval){ y2 = xval; }
   public double gety2() { return y2; } */
   

   

   
   public void calcY1(){
       try {
        y1 = FunctionString.fValue(functionString, x1);
    } catch (DomainViolationException e) {
        JOptionPane.showMessageDialog(null, "Error!", "x1 not in domain of function " , JOptionPane.ERROR_MESSAGE);
        x1 = 0; y1 = 0; x2 = 0; y2 = 0;
    } catch (FunctionFormatException e) {}   
       
   }
   public void calcY2(){
       try {
        y2 = FunctionString.fValue(functionString, x2);
    } catch (DomainViolationException e) {
        JOptionPane.showMessageDialog(null, "Error!", "x2 not in domain of function " , JOptionPane.ERROR_MESSAGE);
        x1 = 0; y1 = 0; x2 = 0; y2 = 0;
    }  catch (FunctionFormatException e) {}    
   }
   public void setFunctionString(String fString){ functionString = fString; }
   public String getFunctionString() { return functionString; } 
   public void setMark(String m){mark = m;}
   public String getMark(){return mark;}
   public boolean getSegment(){return segment;} 
   public void setSegment(boolean tf){segment = tf;}
   
   public String toString(){
       return "CHORD: "+getFunctionString()+", ( "+x1+","+y1+"), "+", ( "+x2+","+y2+"), ";//+getGrafColor();
   }
  
 }
   
 
