/*GrafValue - graphs a value on a curve
 * 
 * @author Bill Gillam
 * @version 1/27/17]
 */
//import javax.swing.*;
import java.io.*;
import java.awt.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.border.*;
import javax.swing.UIManager;
import java.util.ArrayList;

public class GrafValue extends GrafObject implements IGrafable{
        private String functionString="";
        //private int segLength = 1000;
        private GrafProg myOwner;
        private GrafSettings gStuff;
        private String mark ="x";
        private double x = 0;
        private double y = 0;
       

        
   public GrafValue(){
       gStuff = super.initGrafObject(GrafType.FVALUE);
       
       }
   
   public GrafValue(String yString, double x){
        this();
        setX(x);
        setFunctionString(yString);
        y = calcY();
   }
   
    
   public GrafValue(String yString, double x, Color c, String mark){
        this(yString, x);
        setGrafColor(c);
        setMark(mark);
       
   }
   
   @Override
   public void drawGraf(Graphics2D gc){
       gc.setColor(super.getGrafColor());
       if (!Double.isNaN(y)) GrafPrimitives.grafString(gStuff,x, y, getMark() , gc);
       gc.setColor(Color.BLACK);
       myOwner.setMessage2(toString());
       //gStuff.getGrafPanel().repaint();
   }

    @Override
    public boolean isValidInput(GrafDialogController gdf){
        if (gdf.getFunctionString().equals("") && gdf.functionStringIsVisible()) return false;
        if (gdf.getX1().equals("")) return false;
        if (GrafInputHelpers.isDouble(gdf.getX1())) return true;
        GrafInputHelpers.setTextFieldColor(gdf.getX1TextField(), "red");
        return false;
    }

    @Override
    public boolean deepEquals(GrafObject o){
        GrafValue gv = (GrafValue) o;
        if (getType() != o.getType()) return false;
        if (!getGrafColor().equals(gv.getGrafColor())) return false;
        if (!functionString.equals(gv.getFunctionString())) return false;
        if (!(getX() == gv.getX())) return false;
        return true;
    }

    @Override
    public void loadObjectFields(GrafDialogController gdc){
        super.loadObjectFields(gdc);
        gdc.setFunctionString(getFunctionString());
        gdc.setX1(""+getX());
        gdc.settDialogMark(getMark());

    }

    @Override
    public void autoRange(){
       double y1 = 0;
        try{
            y1 = FunctionString.fValue(getFunctionString(), getX());
        }catch(Exception e){JOptionPane.showMessageDialog(null, "Invalid function! ", "Error!" , JOptionPane.ERROR_MESSAGE); return;};
        GrafProg.getGrafSettings().setYMax(y1+GrafProg.getGrafSettings().getTenthWindowY());
        GrafProg.getGrafSettings().setYMin(y1-GrafProg.getGrafSettings().getTenthWindowY());
        GrafProg.getGrafPanel().repaint();
    }
   

   
   public void setX(double xval){ x = xval; }
   public double getX() { return x; }   
   public void setFunctionString(String fString){ functionString = fString; }
   public String getFunctionString() { return functionString; } 
   public void setMark(String m){mark = m;}
   public String getMark(){return mark;}
   
   public String toString(){
       return "FVALUE: "+getFunctionString()+": ("+x+", "+y+") ";//+getGrafColor();
   }
   
   private double calcY(){
      try {
        y = FunctionString.fValue(functionString, x);
      } catch (DomainViolationException e) {
        JOptionPane.showMessageDialog(null, "Error!", x+" not in domain of function " , JOptionPane.ERROR_MESSAGE);
        return Double.NaN;
      }catch (FunctionFormatException e) {return Double.NaN;}   
      return y;
      //gStuff.getGrafPanel().repaint();
    }
}
    
