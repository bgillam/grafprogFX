package GrafProg.GrafObjects.Function;//import javax.swing.*;
import GrafProg.GrafObjects.Dialog.GrafDialogController;
import GrafProg.GrafObjects.GrafObject;
import GrafProg.GrafObjects.GrafType;
import GrafProg.GrafObjects.IGrafable;
import GrafProg.GrafProg;
import GrafProg.GrafUI.GrafSettings;
import GrafProg.GrafUI.GrafUI;
import GrafProg.GrafUtils.GrafInputHelpers;
import GrafProg.GrafPrimitives;

import java.awt.*;
import javax.swing.JOptionPane;

public class GrafValue extends GrafObject implements IGrafable {
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
   
   private GrafValue(String yString, double x){
        this();
        setX(x);
        setFunctionString(yString);
        y = calcY();
   }
   
    
   private GrafValue(String yString, double x, Color c, String mark){
        this(yString, x);
        setGrafColor(c);
        setMark(mark);
       
   }
   
   @Override
   public void drawGraf(Graphics2D gc){
       gc.setColor(super.getGrafColor());
       if (!Double.isNaN(y)) GrafPrimitives.grafString(gStuff,x, y, getMark() , gc);
       gc.setColor(Color.BLACK);
       GrafProg.setMessage2(toString());
       //gStuff.getGrafPanel().repaint();
   }

    @Override
    public boolean isValidInput(GrafDialogController gdf){
        if (gdf.getFunctionString().equals("") && gdf.functionStringIsVisible()) return false;
        if (GrafDialogController.getX1().equals("")) return false;
        if (GrafInputHelpers.isDouble(GrafDialogController.getX1())) return true;
        GrafInputHelpers.setTextFieldColor(GrafDialogController.getX1TextField(), "red");
        return false;
    }

    @Override
    public boolean deepEquals(GrafObject o){
        GrafValue gv = (GrafValue) o;
        if (getType() != o.getType()) return false;
        if (!getGrafColor().equals(gv.getGrafColor())) return false;
        if (!functionString.equals(gv.getFunctionString())) return false;
        return getX() == gv.getX();
    }

    @Override
    public void loadObjectFields(GrafDialogController gdc){
        super.loadObjectFields(gdc);
        gdc.setFunctionString(getFunctionString());
        GrafDialogController.setX1(""+getX());
        gdc.settDialogMark(getMark());

    }

    @Override
    public void autoRange(){
       double y1;
        try{
            y1 = FunctionString.fValue(getFunctionString(), getX());
        }catch(Exception e){JOptionPane.showMessageDialog(null, "Invalid function! ", "Error!" , JOptionPane.ERROR_MESSAGE); return;}
        GrafProg.getGrafSettings().setYMax(y1+ GrafProg.getGrafSettings().getTenthWindowY());
        GrafProg.getGrafSettings().setYMin(y1- GrafProg.getGrafSettings().getTenthWindowY());
        //GrafProg.GrafProg.getGrafPanel().repaint();
        GrafUI.getGrafPanel().repaint();
    }

    @Override
    public GrafObject createGrafObjectFromController(GrafDialogController gdc){
        return new GrafValue( gdc.getFunctionString(), Double.parseDouble(GrafDialogController.getX1()), gdc.getGrafColor(), gdc.getDialogMark());
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
      //try {
        y = FunctionString.fValue(functionString, x);
     //} catch (GrafProg.GrafProg.GrafUtils.DomainViolationException e) {
     //   JOptionPane.showMessageDialog(null, "Error!", x+" not in domain of function " , JOptionPane.ERROR_MESSAGE);
    //    return Double.NaN;
    //  }catch (GrafProg.GrafObjects.Function.FunctionFormatException e) {return Double.NaN;}
      return y;
      //gStuff.getGrafPanel().repaint();
    }
}
    
