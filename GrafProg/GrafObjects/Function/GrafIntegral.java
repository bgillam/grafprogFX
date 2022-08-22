package GrafProg.GrafObjects.Function;//import javax.swing.*;
import GrafProg.GrafObjects.Dialog.GrafDialogController;
import GrafProg.GrafObjects.GrafObject;
import GrafProg.GrafObjects.GrafType;
import GrafProg.GrafObjects.IGrafable;
import GrafProg.GrafPrimitives;
import GrafProg.GrafProg;
import GrafProg.GrafUI.GrafSettings;
import GrafProg.GrafUtils.GrafInputHelpers;

import java.awt.*;
import javax.swing.JOptionPane;

public class GrafIntegral extends GrafObject implements IGrafable
{
        private String functionString="";
        private GrafProg myOwner;
        private GrafSettings gStuff;
        private String mark =".";
        private double x1 = 0;
        private double x2 = 0;
        private int n=100;
        private Color fillColor = Color.WHITE;
        //private String yString = "";
        
   public GrafIntegral(){
       gStuff = super.initGrafObject(GrafType.INTEGRAL);
       
       }     
        

       private GrafIntegral(String yString, double firstX, double secondX, int nVal){
        this();
        setFunctionString(yString);
        x1 = firstX;
        x2 = secondX;
        n = nVal;

        //super.setGrafColor(c);
    }
       
       
   private GrafIntegral(String yString, double firstX, double secondX, int nVal, Color c, Color fillColor){
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
           //try {
               height = (FunctionString.fValue(functionString, left) + FunctionString.fValue(functionString, left+dx))/2;
           //} catch (GrafProg.GrafProg.GrafUtils.DomainViolationException | GrafProg.GrafObjects.Function.FunctionFormatException e) {
            //   height = 0;
           //}
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
           GrafProg.setMessage3("Sum = "+sum);
           GrafProg.setMessage2("Area from "+x1+" to "+x2+" estimated with "+n+" rectangles");
       }
       
       gc.setColor(Color.BLACK);
       //gStuff.getGrafPanel().repaint();
    }

    @Override
    public boolean isValidInput(GrafDialogController gdf){
       boolean ok = true;
        if (gdf.getFunctionString().equals("") && gdf.functionStringIsVisible()) return false;
        if (GrafDialogController.getX1().equals("")) return false;
        if (GrafDialogController.getX2().equals("")) return false;
        if (gdf.getNText().equals("")) return false;
        if (!GrafInputHelpers.isDouble(GrafDialogController.getX1())) {
            GrafInputHelpers.setTextFieldColor(GrafDialogController.getX1TextField(), "red" );
            ok = false;
        }
        if (!GrafInputHelpers.isDouble(GrafDialogController.getX2())) {
            GrafInputHelpers.setTextFieldColor(GrafDialogController.getX2TextField(), "red" );
            ok = false;
        }
        if (!GrafInputHelpers.isDouble(gdf.getDx())) {
            GrafInputHelpers.setTextFieldColor(GrafDialogController.getDxTextField(), "red" );
            ok = false;
        }
        return ok;
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
        return getN() == gi.getN();
    }

    @Override
    public void loadObjectFields(GrafDialogController gdc){
        super.loadObjectFields(gdc);
        gdc.setFunctionString(getFunctionString());
        GrafDialogController.setX1(""+getX1());
        GrafDialogController.setX2(""+getX2());
        gdc.setDx(""+getN());
        gdc.setfillColor(getFillColor());
   }

    @Override
    public void autoRange(){
        double y1;
        double y2;
        try{
            y1 = FunctionString.fValue(getFunctionString(), getX1());
            y2 = FunctionString.fValue(getFunctionString(),  getX2());
        }catch(Exception e){JOptionPane.showMessageDialog(null, "Invalid function! ", "Error!" , JOptionPane.ERROR_MESSAGE); return;}
        double max, min;
        if (y1<y2){ max = y2; min = y1;} else {max = y1; min = y2; }
        GrafProg.getGrafSettings().setYMax(max+ GrafProg.getGrafSettings().getTenthWindowY());
        GrafProg.getGrafSettings().setYMin(min- GrafProg.getGrafSettings().getTenthWindowY());
    }

    @Override
    public GrafObject createGrafObjectFromController(GrafDialogController gdc){
        return new GrafIntegral(gdc.getFunctionString(),Double.parseDouble(GrafDialogController.getX1()), Double.parseDouble(GrafDialogController.getX2()), Integer.parseInt(gdc.getNText()), gdc.getGrafColor(), gdc.getFillColor());
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
   private void setFillColor(Color c){this.fillColor=c;}
   
   public String toString(){
       return "INTEGRAL: "+getFunctionString()+", start: "+x1+" end: "+x2+", n: "+getN()+", ";//+getGrafColor();
   }
 }
   

