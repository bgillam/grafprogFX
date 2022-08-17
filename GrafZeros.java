/*GrafZeros - graphs the zeros of a function
 * 
 * @author Bill Gillam
 * @version j1.0]
 */
//import javax.swing.*;
import GrafUtils.GrafInputHelpers;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
        private ArrayList<Double> zeroList = new ArrayList<>();
        //private String yString = "";
        
        GrafZeros(){
        gStuff = super.initGrafObject(GrafType.FZERO);

       }
        

       private GrafZeros(String yString, double firstX, double secondX, double dVal){
        this();
        setFunctionString(yString);
        startX = firstX;
        endX = secondX;
        dx = dVal;
        findZeroPoints();
        //super.setGrafColor(c);
    }
       
       
   private GrafZeros(String yString, double firstX, double secondX, double dVal, Color c, String m){
        this(yString, firstX, secondX, dVal);
        mark = m;
        super.setGrafColor(c);
    }
   
   @Override
   public void drawGraf(Graphics2D gc){
       double y;
       gc.setColor(super.getGrafColor());
       for (double root: zeroList){
            //try {
                y =  FunctionString.fValue(functionString, root);
          //  } catch (DomainViolationException | FunctionFormatException e) {
         //       continue;
        //    }
           GrafPrimitives.grafString(gStuff,root, y, getMark() , gc);
      }
       gc.setColor(Color.BLACK);
       //gStuff.getGrafPanel().repaint();
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
        GrafProg.getGrafSettings().setYMax(max+GrafProg.getGrafSettings().getTenthWindowY());
        GrafProg.getGrafSettings().setYMin(min-GrafProg.getGrafSettings().getTenthWindowY());
    }

    @Override
    public GrafObject createGrafObjectFromController(GrafDialogController gdc){
        return new GrafZeros(gdc.getFunctionString(), Double.parseDouble(GrafDialogController.getX1()), Double.parseDouble(GrafDialogController.getX2()),  Double.parseDouble(gdc.getDx()), gdc.getGrafColor(),gdc.getDialogMark() );
    }

   public void setStartX(double xval){ startX = xval; }
   public double getStartX() { return startX; } 
   public void setEndX(double xval){ endX = xval; }
   public double getEndX() { return endX; } 
   public void setFunctionString(String fString){ functionString = fString; }
   public String getFunctionString() { return functionString; } 
   public void setMark(String m){mark = m;}
   public String getMark(){return mark;}
   private double getDx(){return dx;}
   public void setDx(int nVal){dx = nVal;}
   public ArrayList<Double> getZeroList(){
       return zeroList;
   }
   
          

    @Override
    public boolean isValidInput(GrafDialogController gdf){
        boolean ok = true;
        if (gdf.getFunctionString().equals("") && gdf.functionStringIsVisible()) return false;
        if (GrafDialogController.getX1().equals("")) return false;
        if (GrafDialogController.getX2().equals("")) return false;
        if (gdf.getDx().equals("")) return false;
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
        GrafZeros gz = (GrafZeros) o;
        if (getType() != o.getType()) return false;
        if (!getGrafColor().equals(gz.getGrafColor())) return false;
        if (!functionString.equals(gz.getFunctionString())) return false;
        if (!(getX1() == gz.getX1())) return false;
        if (!(getX2() == gz.getX2())) return false;
        return getDx() == gz.getDx();
    }

    @Override
    public void loadObjectFields(GrafDialogController gdc){
        super.loadObjectFields(gdc);
        gdc.setFunctionString(getFunctionString());
        GrafDialogController.setX1(""+getX1());
        GrafDialogController.setX2(""+getX2());
        gdc.setDx(""+getDx());
        gdc.settDialogMark(getMark());
    }
    
   private void findZeroPoints(){
        double f1, f2;
        double currentRoot;
        zeroString = "";
        //String functionStr = functionLabel.getText();
        for (double left = startX; left < endX; left=left+dx){
            //try {
                f1 =  FunctionString.fValue(functionString, left);
            //} catch (DomainViolationException | FunctionFormatException e) {
            //    continue;
          //  }
          //  try {
                f2 =  FunctionString.fValue(functionString, left+dx);
          //  } catch (DomainViolationException | FunctionFormatException e) {
         //       continue;
        //    }
            if (f1 == 0) { 
                currentRoot = left;
                zeroList.add(currentRoot);
                GrafProg.setMessage1("Zero located at: "+currentRoot);
            }
            if (oppositeSigns(f1,f2)) {
                currentRoot = getCloseToRoot(functionString, left, left+dx);
                zeroList.add(currentRoot);
               GrafProg.setMessage2("Zero located at: "+currentRoot);
               //zeroString = zeroString + " " + currentRoot;
               zeroString = String.format("%s %s", zeroString, currentRoot);
               //System.out.println(zeroString+" "+zeroString2);
            }
        }
                
    }
   
   private boolean oppositeSigns(double f1, double f2){
        if ((f1>0) && (f2<0)) return true;
       return (f1 < 0) && (f2 > 0);
   }
    
    private double getCloseToRoot(String funct, double rootX1, double rootX2){
        double rootXK;
        double difference;
        do{
            rootXK = (rootX1 + rootX2)/2;
           // try {
                if (!oppositeSigns(FunctionString.fValue(funct, rootX1), FunctionString.fValue(funct,  rootXK)))
                    rootX1 = rootXK; else rootX2 = rootXK;
          //  } catch (DomainViolationException e) {
                //need to do something here!
          //  } catch (FunctionFormatException e) {System.out.println(e.toString());}
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
   

