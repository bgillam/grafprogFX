/*GrafZeros - graphs the zeros of a function
 * 
 * @author Bill Gillam
 * @version j1.0]
 */
//import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GrafZeros extends GrafObject implements IGrafable
{
        private String functionString="";
        private String zeroString = "";
        private GrafProg myOwner;
        private GrafSettings gStuff;
        private String mark ="X";
        private double startX = 0;
        private double endX = 0;
        private double dx = .01;
        private ArrayList<Double> zeroList = new ArrayList<Double>(); 
        //private String yString = "";
        
        public GrafZeros(){
        gStuff = gStuff = super.initGrafObject(GrafType.FZERO);

       }
        

       public GrafZeros(String yString, double firstX, double secondX, double dVal){
        this();
        setFunctionString(yString);
        startX = firstX;
        endX = secondX;
        dx = dVal;
        findZeroPoints();
        //super.setGrafColor(c);
    }
       
       
   public GrafZeros(String yString, double firstX, double secondX, double dVal, Color c, String m){
        this(yString, firstX, secondX, dVal);
        mark = m;
        super.setGrafColor(c);
    }
   
   @Override
   public void drawGraf(Graphics2D gc){
       double y = 0;
       gc.setColor(super.getGrafColor());
       for (double root: zeroList){
            try {
                y =  FunctionString.fValue(functionString, root);
            } catch (DomainViolationException e) {
                continue;
            }catch (FunctionFormatException e) {continue;}   
            GrafPrimitives.grafString(gStuff,root, y, getMark() , gc);   
      }
       gc.setColor(Color.BLACK);
       //gStuff.getGrafPanel().repaint();
    }


    @Override
    public void autoRange(){
        double y1 = 10;
        double y2 = -10;
        try{
            y1 = FunctionString.fValue(getFunctionString(), getX1());
            y2 = FunctionString.fValue(getFunctionString(),  getX2());
        }catch(Exception e){JOptionPane.showMessageDialog(null, "Invalid function! ", "Error!" , JOptionPane.ERROR_MESSAGE); return;};
        double max, min;
        if (y1<y2){ max = y2; min = y1;} else {max = y1; min = y2; }
        GrafProg.getGrafSettings().setYMax(max+GrafProg.getGrafSettings().getTenthWindowY());
        GrafProg.getGrafSettings().setYMin(min-GrafProg.getGrafSettings().getTenthWindowY());
    }

    
   public void setStartX(double xval){ startX = xval; }
   public double getStartX() { return startX; } 
   public void setEndX(double xval){ endX = xval; }
   public double getEndX() { return endX; } 
   public void setFunctionString(String fString){ functionString = fString; }
   public String getFunctionString() { return functionString; } 
   public void setMark(String m){mark = m;}
   public String getMark(){return mark;}
   public double getDx(){return dx;}
   public void setDx(int nVal){dx = nVal;}
   public ArrayList<Double> getZeroList(){
       return zeroList;
   }
   
          

    @Override
    public boolean isValidInput(GrafDialogController gdf){
        boolean ok = true;
        if (gdf.getFunctionString().equals("") && gdf.functionStringIsVisible()) return false;
        if (gdf.getX1().equals("")) return false;
        if (gdf.getX2().equals("")) return false;
        if (gdf.getDx().equals("")) return false;
        if (!GrafInputHelpers.isDouble(gdf.getX1())) {
            GrafInputHelpers.setTextFieldColor(gdf.getX1TextField(), "red" );
            ok = false;
        }
        if (!GrafInputHelpers.isDouble(gdf.getX2())) {
            GrafInputHelpers.setTextFieldColor(gdf.getX2TextField(), "red" );
            ok = false;
        }
        if (!GrafInputHelpers.isDouble(gdf.getDx())) {
            GrafInputHelpers.setTextFieldColor(gdf.getDxTextField(), "red" );
            ok = false;
        }
        return ok;
    }

    @Override
    public boolean deepEquals(GrafObject o){
        GrafZeros gz = (GrafZeros) o;
        if (getType() != o.getType()) return false;
        if (!getGrafColor().equals(gz.getGrafColor())) return false;
        if (!functionString.equals(gz.getFunctionString())) return false;
        if (!(getX1() == gz.getX1())) return false;
        if (!(getX2() == gz.getX2())) return false;
        if (!(getDx() == gz.getDx())) return false;
        return true;
    }

    @Override
    public void loadObjectFields(GrafDialogController gdc){
        super.loadObjectFields(gdc);
        gdc.setFunctionString(getFunctionString());
        gdc.setX1(""+getX1());
        gdc.setX2(""+getX2());
        gdc.setDx(""+getDx());
        gdc.settDialogMark(getMark());
    }
    
   private void findZeroPoints(){
        double f1, f2;
        double currentRoot;
        zeroString = "";
        //String functionStr = functionLabel.getText();
        for (double left = startX; left < endX; left=left+dx){
            try { f1 =  FunctionString.fValue(functionString, left);
            } catch (DomainViolationException e) {
                continue;
            }catch (FunctionFormatException e) {continue;}   
            try {
                f2 =  FunctionString.fValue(functionString, left+dx);
            } catch (DomainViolationException e) {
                continue;
            }catch (FunctionFormatException e) {continue;}   
            if (f1 == 0) { 
                currentRoot = left;
                zeroList.add(currentRoot);
                myOwner.setMessage1("Zero located at: "+currentRoot);
            }
            if (oppositeSigns(f1,f2)) {
                currentRoot = getCloseToRoot(functionString, left, left+dx);
                zeroList.add(currentRoot);
               myOwner.setMessage2("Zero located at: "+currentRoot);
               zeroString = zeroString+" "+currentRoot;
            }
        }
                
    }
   
   private boolean oppositeSigns(double f1, double f2){
        if ((f1>0) && (f2<0)) return true;
        if ((f1<0) && (f2>0)) return true;
        return false;
    }
    
    private double getCloseToRoot(String funct, double rootX1, double rootX2){
        double rootXK;
        double difference;
        do{
            rootXK = (rootX1 + rootX2)/2;
            try {
                if (!oppositeSigns(FunctionString.fValue(funct, rootX1), FunctionString.fValue(funct,  rootXK)))
                    rootX1 = rootXK; else rootX2 = rootXK;
            } catch (DomainViolationException e) {
                //need to do something here!
            } catch (FunctionFormatException e) {}   
                difference = Math.abs(rootX2 - rootX1);
                if (difference < 1E10) return rootX1;
        }while(true);
    }

    public void setX1(double xval){ startX = xval; }
    public double getX1() { return startX; }
    public void setX2(double xval){ endX = xval; }
    public double getX2() { return endX; }

    public String toString(){
       return "FZEROS: "+getFunctionString()+" "+zeroString;//+" "+getGrafColor();
   }
 }
   

