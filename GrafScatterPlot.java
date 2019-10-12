

/* GrafScatterplot for GrafProg Project *
*  Graph of scatterplot from table data 
*  @author Bill Gillam           *
*  2/25/15                       *
**********************************/
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import java.util.ArrayList;


//Class Header
public class GrafScatterPlot extends GrafObject implements IGrafable{
    //Instance Variables
    private int inputColumnNumber, outputColumnNumber;
    private GrafProg myOwner;
    private GrafSettings gStuff;
    private GrafTable table;
    private String mark =".";
    private boolean connected = false;
        
    //Constructor
    public GrafScatterPlot(){
       setGrafType(GrafType.SCATTER);
       setMoveable(false);
       setGrafColor(Color.BLACK);
       setInputColumnNumber(1);
       setOutputColumnNumber(2);
    }
    
    public GrafScatterPlot(GrafProg sess){
        setGrafType(GrafType.SCATTER);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
        table = myOwner.getData();
        sess.setMessage1("Plotting Column "+inputColumnNumber+" vs. column "+outputColumnNumber);
    }
    
    //constructor 
    public GrafScatterPlot(GrafProg sess, int inputCol, int outputCol){
        this(sess);
        setInputColumnNumber(inputCol);
        setOutputColumnNumber(outputCol);
        
    }
    
    //drawGraf overriding method in parent GrafObject
    @Override
    public void drawGraf(Graphics2D gc){
        double xMin = gStuff.getXMin();
        double xMax = gStuff.getXMax();
        //double x1, y1; 
        //double x = xMin;
        gc.setColor(super.getGrafColor());
        if (getConnected()) {
            Double x1 = table.getCellValue(1, inputColumnNumber);
            Double y1 = table.getCellValue(1, outputColumnNumber);
            Double x2, y2;
            for (int i = 2; i <= table.getNumRows(); i++){
                x2 = table.getCellValue(i, inputColumnNumber);
                y2 = table.getCellValue(i, outputColumnNumber);
                if (x1 != null && y1 != null && x2 != null && y2 != null) GrafPrimitives.grafLine(gStuff,x1, y1 , x2, y2, gc);
                x1 = x2;
                y1 = y2;
            }
        }
        else{
            for (int i = 1; i <= table.getNumRows(); i++){
                Double x = table.getCellValue(i, inputColumnNumber);
                Double y = table.getCellValue(i, outputColumnNumber);
                if (x != null && y != null) GrafPrimitives.grafString(gStuff,x, y,mark, gc);
            }
        }
        gc.setColor(Color.BLACK);
    }
    
    

    
  

   
    
    
    
    //Setters and Getters
    public void setInputColumnNumber(int c){ inputColumnNumber = c;}
    public int getInputColumnNumber(){ return inputColumnNumber;}
    public void setOutputColumnNumber(int c){ outputColumnNumber = c;}
    public int getOutputColumnNumber(){ return outputColumnNumber;}
    public void setMark(String m){mark = m;}
    public String getMark(){return mark;}
    public void setConnected(boolean tf){
        connected = tf;
    }
    public boolean getConnected(){
        return connected;
    }
    
    public String toString(){
        return "SCATTERPLOT: Col "+getInputColumnNumber()+", Col "+getOutputColumnNumber()+", "+getMark();//+", "+ getGrafColor();
    }
    

    
}
