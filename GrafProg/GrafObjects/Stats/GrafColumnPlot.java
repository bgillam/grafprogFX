package GrafProg.GrafObjects.Stats;
/*  GrafColumn for GrafProg.GrafProg Project *
 * Graph a coolumn plot against row number in table
*  @author Bill Gillam           *
*  2/25/15                       *
**********************************/

import GrafProg.CalcStats.GrafStats;
import GrafProg.GrafObjects.Dialog.GrafDialogController;
import GrafProg.GrafProg;
import GrafProg.GrafObjects.GrafObject;
import GrafProg.GrafObjects.GrafType;
import GrafProg.GrafObjects.IGrafable;
import GrafProg.GrafTable.GrafTable;
import GrafProg.GrafTable.TableColumnActions;
import GrafProg.GrafTable.TableUI;
import GrafProg.GrafUI.GrafSettings;
import GrafProg.GrafPrimitives;

import java.awt.Color;
import java.awt.Graphics2D;

//Class Header
public class GrafColumnPlot extends GrafObject implements IGrafable {
    //Instance Variables
    private int columnNumber;
    //private GrafProg.GrafProg myOwner;
    private GrafSettings gStuff;
    private GrafTable table;
    private String mark =".";
    private boolean connected = false;
        
    //Constructor
    public GrafColumnPlot(){
        gStuff = super.initGrafObject(GrafType.COLUMN);
        setColumnNumber(1);
        table = TableUI.getData();
        GrafProg.setMessage1("Plotting Column "+columnNumber);
    }
    

        
    public GrafColumnPlot(int column){
        this();
        setColumnNumber(column);
        GrafProg.setMessage1("Plotting Column "+columnNumber);
    }

    private GrafColumnPlot(int column, String mark, boolean connected, Color c){
        this();
        setColumnNumber(column);
        GrafProg.setMessage1("Plotting Column "+columnNumber);
        setMark(mark);
        setConnected(connected);
        setGrafColor(c);
    }
    
       
    //drawGraf overriding method in parent GrafProg.GrafObjects.GrafObject
    @Override
    public void drawGraf(Graphics2D gc){
        double xMin = gStuff.getXMin();
        double xMax = gStuff.getXMax();

        gc.setColor(super.getGrafColor());
        Double val, preval = 0.0;
        for (int i = 1; i < table.getNumRows(); i++){
              val = table.getCellValue(i, columnNumber);
              //System.out.println(i+" "+val);
                if (val != null) {
                    if (GrafProg.getGrafSettings().getReverseXY()) {
                        GrafPrimitives.grafString(gStuff,val, i , mark, gc);
                        if (connected) if (i != 1) GrafPrimitives.grafLine(gStuff, preval, i-1, val, i, gc);
                        preval=val;
                    }
                    else {
                        GrafPrimitives.grafString(gStuff,i,val, mark, gc);
                        if (connected) if (i != 1) GrafPrimitives.grafLine(gStuff, i-1, preval, i, val, gc);
                        preval=val;
                    }
                }
        }
        gc.setColor(Color.BLACK);
    }

    @Override
    public boolean isValidInput(GrafDialogController gdf){
        //if (gdf.getColumn1VaLue()).eq
        //if (gdf.getFunctionString().equals("") && gdf.functionStringIsVisible()) return false;
        return true;
    }

    @Override
    public boolean deepEquals(GrafObject o){
        GrafColumnPlot gi = (GrafColumnPlot) o;
        if (getType() != o.getType()) return false;
        if (!getGrafColor().equals(gi.getGrafColor())) return false;
        if (!getMark().equals(gi.getMark())) return false;
        if (!(getColumnNumber() == gi.getColumnNumber())) return false;
        return getConnected() == gi.getConnected();
    }

    @Override
    public void loadObjectFields(GrafDialogController gdc){
        super.loadObjectFields(gdc);
        gdc.setColumn1ChooserColumn(getColumnNumber()-1);
        gdc.settDialogMark(getMark());
        gdc.setFNS(connected);
    }

    @Override
    public void autoRange(){
            Double max = GrafStats.getMax(TableColumnActions.getColumnValues(getColumnNumber(), TableUI.getData()));
            if (max != null)
                GrafProg.getGrafSettings().setYMax(max + GrafProg.getGrafSettings().getTenthWindowY());
            Double min =  GrafStats.getMin(TableColumnActions.getColumnValues(getColumnNumber(), TableUI.getData()));
            if (min!=null)
                GrafProg.getGrafSettings().setYMin(min - GrafProg.getGrafSettings().getTenthWindowY());
    }

    @Override
    public GrafObject createGrafObjectFromController(GrafDialogController gdc){
        return new GrafColumnPlot(gdc.getColumn1ChooserColumn(), gdc.getDialogMark(), gdc.isConnected(), gdc.getGrafColor());
    }
    

    //Setters and Getters
    public void setColumnNumber(int c){ columnNumber = c;}
    public int getColumnNumber(){ return columnNumber;}
    public void setMark(String m){mark = m;}
    public String getMark(){return mark;}
    public void setConnected(boolean tf){
        connected = tf;
    }
    public boolean getConnected(){
        return connected;
    }
    
    public String toString(){
        return "COLUMNPLOT: Col "+getColumnNumber()+","+", "+getMark();//+", "+ getGrafColor();
    }
    
   /* private static GrafProg.GrafProg.GrafTable getData(){
        return GrafProg.GrafProg.getData();

    }*/


}
