
/*  GrafColumn for GrafProg Project *
 * Graph a coolumn plot against row number in table
*  @author Bill Gillam           *
*  2/25/15                       *
**********************************/
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

//Class Header
public class GrafColumnPlot extends GrafObject implements IGrafable{
    //Instance Variables
    private int columnNumber;
    private GrafProg myOwner;
    private GrafSettings gStuff;
    private GrafTable table;
    private String mark =".";
    private boolean connected = false;
        
    //Constructor
    public GrafColumnPlot(){
        gStuff = super.initGrafObject(GrafType.COLUMN);
        setColumnNumber(1);
        table = myOwner.getData();
        GrafProg.setMessage1("Plotting Column "+columnNumber);
    }
    

        
    public GrafColumnPlot(int column){
        this();
        setColumnNumber(column);
        GrafProg.setMessage1("Plotting Column "+columnNumber);
    }

    public GrafColumnPlot(int column, String mark, boolean connected, Color c){
        this();
        setColumnNumber(column);
        GrafProg.setMessage1("Plotting Column "+columnNumber);
        setMark(mark);
        setConnected(connected);
        setGrafColor(c);
    }
    
       
    //drawGraf overriding method in parent GrafObject
    @Override
    public void drawGraf(Graphics2D gc){
        double xMin = gStuff.getXMin();
        double xMax = gStuff.getXMax();
      
        double x = xMin;
        gc.setColor(super.getGrafColor());
       
        for (int i = 1; i < table.getNumRows(); i++){
                Double val = table.getCellValue(i, columnNumber);
            System.out.println(i+" "+val);
                if (val != null) {
                    if (myOwner.getGrafSettings().getReverseXY()) GrafPrimitives.grafString(gStuff,val, i , mark, gc);
                    else GrafPrimitives.grafString(gStuff,i,val, mark, gc);
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
        if (!(getConnected() == gi.getConnected())) return false;
        return true;
    }

    @Override
    public void loadObjectFields(GrafDialogController gdc){
        super.loadObjectFields(gdc);
        gdc.setColumn1ChooserColumn(getColumnNumber());
        gdc.settDialogMark(getMark());
        gdc.setFNS(connected);
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
    
    private static void saveColumn(GrafProg gs, GrafInputDialog gfd){
        int col = gfd.getColumnChooser().getInputColumn();
        if (gfd.getFinalSave() == true && col == 0) return; 
        addColumn(gs, gfd); 
        gfd.getColumnChooser().setInputIndex(0);
    
    }
    
    private static void addColumn(GrafProg gs, GrafInputDialog gfd){
        int input = gfd.getInput();
        if (input == 0)  return;
        GrafColumnPlot gPlot = new GrafColumnPlot(input);
        gPlot.setGrafColor(gfd.getMarkChooser().getGrafColor());
        //set correct mark for points
        if (gfd.getMarkChooser().xMark()) gPlot.setMark("x"); 
        else if (gfd.getMarkChooser().oMark()) gPlot.setMark("o"); 
        else if (gfd.getMarkChooser().periodMark()) gPlot.setMark("."); 
        else { String text = gfd.getMarkChooser().getTextMark(); 
        gPlot.setMark(text);} 
        if (gfd.getMarkChooser().isLineGraph()) gPlot.setConnected(true); else gPlot.setConnected(false);
        gfd.getTempList().add(gPlot);
    
    }
    


}
