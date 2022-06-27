
/*  GrafBoxplot for GrafProg Project *
 * used to graph a boxplot
*  @author Bill Gillam           *
*  2/25/15                       *
**********************************/
import javafx.scene.control.Tab;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


public class GrafBoxPlot extends GrafObject implements IGrafable {
    //Instance Variables
    private int columnNumber;
    //private GrafProg myOwner;
    private GrafSettings gStuff;
    private String mark =".";
    private boolean showFNS = true;
    static int numBoxPlots = 0;
        
    //Constructor
    GrafBoxPlot(){
        gStuff = super.initGrafObject(GrafType.BOXPLOT);
        setColumnNumber(1);
        GrafTable table = TableUI.getData();
        GrafProg.setMessage1("Plotting Column "+columnNumber);
        setShowFNS(true);
        //numBoxPlots++;
    }
    

    //constructor 
    private GrafBoxPlot(int column){
        this();
        setColumnNumber(column);
    }
    
    private GrafBoxPlot(int column, Color c, boolean fns){
        this(column);
        setGrafColor(c);
        setShowFNS(fns);
    }
    
    //drawG   raf overriding method in parent GrafObject
    @Override
    public void drawGraf(Graphics2D gc){
        double xMin = gStuff.getXMin();
        double xMax = gStuff.getXMax();
        gc.setColor(super.getGrafColor());
        double gHeight = GrafProg.getGrafSettings().getGrafHeight();
        double numPlots = GrafProg.getNumPlots();
        double plotted = getBoxPlotsPlotted();
        double boxHeight = (gHeight/2)/(numPlots+1);
        double boxCenter = boxHeight + boxHeight*(plotted);
        double[] fns = GrafStats.getFiveNumberSummary(TableColumnActions.getColumnValues(columnNumber, TableUI.getData()));
        if (!GrafProg.getGrafSettings().getReverseXY()){
            GrafPrimitives.grafLine(gStuff,fns[0], boxCenter-boxHeight/10 , fns[0] ,boxCenter+boxHeight/10 , gc);  // Min tic
            GrafPrimitives.grafLine(gStuff,fns[0], boxCenter , fns[1] , boxCenter , gc);                           //left whisker
            GrafPrimitives.grafLine(gStuff,fns[1], boxCenter-boxHeight/5 , fns[1] ,boxCenter+boxHeight/5 , gc);    // Q1
            GrafPrimitives.grafLine(gStuff,fns[1], boxCenter-boxHeight/5 , fns[3] ,boxCenter-boxHeight/5 , gc);    //box bottom
            GrafPrimitives.grafLine(gStuff,fns[1], boxCenter+boxHeight/5 , fns[3] ,boxCenter+boxHeight/5 , gc);    //box top
            GrafPrimitives.grafLine(gStuff,fns[2], boxCenter-boxHeight/5 , fns[2] ,boxCenter+boxHeight/5 , gc);    //median
            GrafPrimitives.grafLine(gStuff,fns[3], boxCenter-boxHeight/5 , fns[3] ,boxCenter+boxHeight/5 , gc);    //Q3
            GrafPrimitives.grafLine(gStuff,fns[3], boxCenter , fns[4] , boxCenter , gc);                           //right whisker
            GrafPrimitives.grafLine(gStuff,fns[4], boxCenter-boxHeight/10 , fns[4] ,boxCenter+boxHeight/10 , gc);  // max tic
            if (showFNS)  {
                GrafPrimitives.grafString(gStuff,fns[0], boxCenter-boxHeight/5, ""+roundFNS(fns[0]), gc);
                GrafPrimitives.grafString(gStuff,fns[1], boxCenter-boxHeight/5, ""+roundFNS(fns[1]), gc);
                GrafPrimitives.grafString(gStuff,fns[2], boxCenter-boxHeight/5, ""+roundFNS(fns[2]), gc);
                GrafPrimitives.grafString(gStuff,fns[3], boxCenter-boxHeight/5, ""+roundFNS(fns[3]), gc);
                GrafPrimitives.grafString(gStuff,fns[4], boxCenter-boxHeight/5, ""+roundFNS(fns[4]), gc);
            }
      
        } 
       //GrafPrimitives.grafString(table.getCellValue(i, columnNumber), i , mark, gc);
        else{
            GrafPrimitives.grafLine(gStuff,boxCenter-boxHeight/10 , fns[0], boxCenter+boxHeight/10 , fns[0], gc);   //Min tic
            GrafPrimitives.grafLine(gStuff,boxCenter, fns[0], boxCenter , fns[1]  , gc);                             // left whisker
            GrafPrimitives.grafLine(gStuff, boxCenter-boxHeight/5 , fns[1] , boxCenter+boxHeight/5 , fns[1], gc);   //Q1
            GrafPrimitives.grafLine(gStuff,boxCenter-boxHeight/5 , fns[1] ,boxCenter-boxHeight/5 , fns[3] , gc);    //box bottom
            GrafPrimitives.grafLine(gStuff,boxCenter+boxHeight/5 , fns[1] ,boxCenter+boxHeight/5 , fns[3] , gc);    //box top
            GrafPrimitives.grafLine(gStuff,boxCenter+boxHeight/5 , fns[2] ,boxCenter-boxHeight/5 , fns[2] , gc);    //Median    
            GrafPrimitives.grafLine(gStuff,boxCenter+boxHeight/5 , fns[3] ,boxCenter-boxHeight/5 , fns[3] , gc);    // Q3
            GrafPrimitives.grafLine(gStuff,boxCenter, fns[3], boxCenter , fns[4]  , gc);                             //right whisker
            GrafPrimitives.grafLine(gStuff,boxCenter-boxHeight/10 , fns[4], boxCenter+boxHeight/10 , fns[4], gc);   //Max tic
            if (showFNS)  {
                GrafPrimitives.grafString(gStuff,boxCenter-boxHeight/5, fns[0], ""+roundFNS(fns[0]), gc);
                GrafPrimitives.grafString( gStuff,boxCenter-boxHeight/5, fns[1], ""+roundFNS(fns[1]), gc);
                GrafPrimitives.grafString( gStuff,boxCenter-boxHeight/5, fns[2], ""+roundFNS(fns[2]), gc);
                GrafPrimitives.grafString(gStuff, boxCenter-boxHeight/5, fns[3], ""+roundFNS(fns[3]), gc);
                GrafPrimitives.grafString( gStuff,boxCenter-boxHeight/5, fns[4], ""+roundFNS(fns[4]), gc);
            }
        }
            //GrafPrimitives.grafLine(gStuff,GrafStats.getMin(table.getColumnValues(columnNumber)), , x2, y2, gc);
        GrafProg.setMessage2("FNS: "+fns[0]+", "+fns[1]+", "+fns[2]+", "+fns[3]+", "+fns[4]);
        //GrafProg.incrementBoxPlotsPlotted();
        numBoxPlots++;
        gc.setColor(Color.BLACK);
    }

    private static double roundFNS(double val){
        BigDecimal vd = new BigDecimal(Double.toString(val));
        int dec = 2;
        if (val < .01) {
            dec = vd.scale()-vd.precision()+1;
        }
        vd =  vd.setScale(dec, RoundingMode.HALF_UP);
        return vd.doubleValue();
    }

    @Override
    public boolean isValidInput(GrafDialogController gdf){
        //if (gdf.getColumn1VaLue()).eq
        //if (gdf.getFunctionString().equals("") && gdf.functionStringIsVisible()) return false;
        return true;
    }

    @Override
    public boolean deepEquals(GrafObject o){
        GrafBoxPlot gi = (GrafBoxPlot) o;
        if (getType() != o.getType()) return false;
        if (!getGrafColor().equals(gi.getGrafColor())) return false;
        if (!(getColumnNumber() == gi.getColumnNumber())) return false;
        return getShowFNS() == gi.getShowFNS();
    }

    @Override
    public void loadObjectFields(GrafDialogController gdc){
        super.loadObjectFields(gdc);
        //System.out.println(columnNumber);
        gdc.setColumn1ChooserColumn(getColumnNumber()-1);
        gdc.setFNS(showFNS);

    }

    @Override
    public void autoRange(){
        GrafProg.getGrafSettings().setYMax(getBoxPlotsPlotted()*5);
        GrafProg.getGrafSettings().setYMin(-5);
    }

    @Override
    public GrafObject createGrafObjectFromController(GrafDialogController gdc){
        return new GrafBoxPlot(gdc.getColumn1ChooserColumn(), gdc.getGrafColor(), gdc.getFNS());
    }


    //Setters and Getters
    private void setColumnNumber(int c){ columnNumber = c;}
    private int getColumnNumber(){ return columnNumber;}
    public void setMark(String m){mark = m;}
    public String getMark(){return mark;}
    private void setShowFNS(boolean tf){showFNS = tf;}
    private boolean getShowFNS(){
        return showFNS;
    }

    private static int getBoxPlotsPlotted(){return numBoxPlots;}

    public String toString(){
        return "BOXPLOT: Col "+getColumnNumber()+", "+getMark();//+", "+ getGrafColor();
    }

    public static void main(String[] args){
        roundFNS(1.001);
        roundFNS(0.000023);
        roundFNS(.005);
    }

    /*private static GrafTable getData(){
        return TableUI.getData();
    }*/

}
