

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
    
    
    @Override
    public GrafInputDialog createInputDialog(GrafProg gs){//GrafInputDialog createInputDialog(GrafProg gs){
        GrafInputDialog gfd = new GrafInputDialog(gs); 
        gfd.setTitle("ScatterPlot"); 
        gfd.addColumnChooserPanel(gfd.getColumnsString(),true, true);
        //gfd.setColumnChooser(gfd.getColumnChooser());
        gfd.setMarkChooser(gfd.addMarkPanel(new ColorRadioMarkPanel(true))); 
        gfd.setDeleter(gfd.addDeleterPanel(GrafType.SCATTER)); 
        
        gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(),GrafType.SCATTER )));          
        
        gfd.getCreateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
                saveScatter(gs,gfd);
                gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(),GrafType.SCATTER)));  
            }
        });
        gfd.getSaveChanges().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gfd.setFinalSave(true);
                saveScatter(gs,gfd);
                gs.setGrafList(gfd.getTempList());
                gfd.dispose();
            }
        });
        GrafObject.closeGFD(gfd);
        // gfd.setModal(true);
        // gfd.pack();
        // gfd.setVisible(true); 
        return gfd;
    }
    
    
    
  
    @Override
    public  void setDeleteValues(int index, GrafInputDialog caller, ArrayList<GrafObject> tempList ){
         GrafScatterPlot scEdit = (GrafScatterPlot)tempList.get(caller.getDeleter().getPlotIndex().get(index));
         caller.getColumnChooser().setInputIndex(scEdit.getInputColumnNumber());
         caller.getColumnChooser().setOutputIndex(scEdit.getOutputColumnNumber());
         caller.getMarkChooser().setMark(scEdit.getMark());
         caller.getMarkChooser().setConnectedChecked(scEdit.getConnected());
         caller.getMarkChooser().setColor(scEdit.getGrafColor()); 
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
    
    private static void saveScatter(GrafProg gs, GrafInputDialog gfd){
            int col = gfd.getColumnChooser().getInputColumn();
            if (gfd.getFinalSave() == true && col == 0) return; 
            addScatter(gs, gfd);
            gfd.getColumnChooser().setInputIndex(0);
            gfd.getColumnChooser().setOutputIndex(0);
    
    }
    
    private static void addScatter(GrafProg gs, GrafInputDialog gfd){
        int input = gfd.getInput();
        int output = gfd.getOutput();
        if (input == 0 || output == 0) return;
        GrafScatterPlot gPlot = new GrafScatterPlot(gs, input, output);
        gPlot.setGrafColor(gfd.getMarkChooser().getGrafColor());
        //set correct mark for points
        //markChooser = (ColorRadioMarkPanel)markChooser;
        if (gfd.getMarkChooser().xMark()) gPlot.setMark("x"); 
        else if (gfd.getMarkChooser().oMark()) gPlot.setMark("o"); 
        else if (gfd.getMarkChooser().periodMark()) gPlot.setMark("."); 
        else { String text = gfd.getMarkChooser().getTextMark(); 
        gPlot.setMark(text);} 
        if (gfd.getMarkChooser().isLineGraph()) gPlot.setConnected(true); else gPlot.setConnected(false);
        gfd.getTempList().add(gPlot);
    
    }
    
    /* Setters and Getters from Parent GrafObject
     *  public void drawGraf(Graphics2D g2D){};
   
        public void setGrafType(GrafProg.GrafType gt){grafType = gt;}
        public GrafProg.GrafType getType(){return grafType; }
   
        public boolean isMoveable(){ return moveable; } 
        public void setMoveable(boolean tf){ moveable = tf;  }
        public boolean getMoveable(){return moveable;}
   
        public void setOwner(GrafProg owner){myOwner = owner;}
        public GrafProg getOwner(){return myOwner;}
   
        public void setGrafColor(Color c){grafColor = Color.BLACK;   }
        public Color getGrafColor() { return grafColor;}
     */
    
}
