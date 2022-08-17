/*GrafTangent - graphs a tangent to a curve
 * 
 * @author Bill Gillam
 * @version j1.0]
 */
//import javax.swing.*;
import GrafUtils.GrafInputHelpers;

import javax.swing.*;
import java.awt.*;

public class GrafTangent extends GrafObject implements IGrafable
{
        private String functionString="";
        private int segLength = 1000;
        private GrafProg myOwner;
        private GrafSettings gStuff;
        private String mark ="x";
        private double x = 0;
       
   GrafTangent(){
       gStuff = super.initGrafObject(GrafType.TANGENT);
       }     
        
        

   private GrafTangent(String yString, double x){
        this();
        setX(x);
        setFunctionString(yString);
       
   }
   
    
   private GrafTangent(String yString, double x, Color c, String mark){
        this(yString, x);
        setGrafColor(c);
        setMark(mark);
   }
   
   @Override
   public void drawGraf(Graphics2D gc){
       gc.setColor(super.getGrafColor());
       double dx = (gStuff.getXMax() - gStuff.getXMin())/1000;
       double x1 = x - dx;
       double x2 = x + dx;
       double y1;
    //try {
        y1 = FunctionString.fValue(functionString, x1);
    //} catch (DomainViolationException e) {
    //    JOptionPane.showMessageDialog(null, "Error!", x1+" not in domain of function " , JOptionPane.ERROR_MESSAGE);
    //    return;
    //}catch (FunctionFormatException e) {return;}
    double y2;
    //try {
        y2 = FunctionString.fValue(functionString, x2);
    //} catch (DomainViolationException e) {
   //    JOptionPane.showMessageDialog(null, "Error!", x2+" not in domain of function " , JOptionPane.ERROR_MESSAGE);
    //   return;
    //}catch (FunctionFormatException e) {return;}
       double yX;
    //try {
        yX = FunctionString.fValue(functionString, x);
    //} catch (DomainViolationException e) {
      //  JOptionPane.showMessageDialog(null, "Error!", x+" not in domain of function " , JOptionPane.ERROR_MESSAGE);
       // return;
   // }catch (FunctionFormatException e) {return;}
       GrafPrimitives.grafString(gStuff,x, yX, getMark() , gc);
       double slope = (y2-y1)/(x2-x1);
       double yForXMin = slope*(gStuff.getXMin()-x1)+y1;
       double yForXMax = slope*(gStuff.getXMax()-x1)+y1;
       GrafPrimitives.grafLine(gStuff,gStuff.getXMin(), yForXMin, gStuff.getXMax(), yForXMax ,gc);
       gc.setColor(Color.BLACK);
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
        GrafTangent gv = (GrafTangent) o;
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
        double max, min;
        GrafProg.getGrafSettings().setYMax(y1+GrafProg.getGrafSettings().getTenthWindowY());
        GrafProg.getGrafSettings().setYMin(y1-GrafProg.getGrafSettings().getTenthWindowY());
    }

    @Override
    public GrafObject createGrafObjectFromController(GrafDialogController gdc){
        return new GrafTangent(gdc.getFunctionString(), Double.parseDouble(GrafDialogController.getX1()), gdc.getGrafColor(), gdc.getDialogMark());
    }

   public void setX(double xval){ x = xval; }
   public double getX() { return x; }   
   public void setFunctionString(String fString){ functionString = fString; }
   public String getFunctionString() { return functionString; } 
   public void setMark(String m){mark = m;}
   public String getMark(){return mark;}
   
   public String toString(){
       return "TANGENT: "+getFunctionString()+", "+getX()+") ";//+getGrafColor();
   }
   
 }
   


