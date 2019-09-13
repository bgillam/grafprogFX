
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
     setGrafType(GrafType.COLUMN);
     setMoveable(false);
     setGrafColor(Color.BLACK);
     setColumnNumber(1);
    }
    
    public GrafColumnPlot(GrafProg sess){
        int column = 1;
        setGrafType(GrafType.COLUMN);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
        setColumnNumber(column);
        table = myOwner.getData();
        sess.setMessage1("Plotting Column "+columnNumber);
    }
    
        
    public GrafColumnPlot(GrafProg sess, int column){
        setGrafType(GrafType.COLUMN);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
        setColumnNumber(column);
        table = myOwner.getData();
        sess.setMessage1("Plotting Column "+columnNumber);
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
                if (val != null) {
                    if (myOwner.getGrafSettings().getReverseXY()) GrafPrimitives.grafString(gStuff,val, i , mark, gc);
                    else GrafPrimitives.grafString(gStuff,i,val, mark, gc);
                        
                }
        }
        gc.setColor(Color.BLACK);
    }
    
  /*  @Override
      public GrafInputDialog createInputDialog(GrafProg gs){
        GrafInputDialog gfd = new GrafInputDialog(new GrafProg());
        gfd.setTitle("Column Plot");  
        //gfd.setColumnChooser(gfd.addColumnChooserPanel(gfd.getColumnsString(),true, false));
        gfd.addColumnChooserPanel(gfd.getColumnsString(),true, false);
        //gfd.setColumnChooser(gfd.getColumnChooser());
        gfd.setMarkChooser(gfd.addMarkPanel(new ColorRadioMarkPanel(true))); //addMarkPanel(gSess.getGraphics().getFont(), true, false, false, true, false, false, false);
        gfd.addSeparatorPanel();
        gfd.setDeleter(gfd.addDeleterPanel(GrafType.COLUMN)); 
        
        gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(),GrafType.COLUMN)));  
        
        gfd.getCreateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
                saveColumn(gs,gfd);
                gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(),GrafType.COLUMN)));  
            }
        });
        gfd.getSaveChanges().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gfd.setFinalSave(true);
                saveColumn(gs,gfd);
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
    */
    
    

    @Override
    public void setDeleteValues(int index, GrafInputDialog caller, ArrayList<GrafObject> tempList ){
             GrafColumnPlot cEdit = (GrafColumnPlot)tempList.get(caller.getDeleter().getPlotIndex().get(index));
             caller.getColumnChooser().setInputIndex(cEdit.getColumnNumber());
             caller.getMarkChooser().setMark(cEdit.getMark());
             caller.getMarkChooser().setConnectedChecked(cEdit.getConnected());
             caller.getMarkChooser().setColor(cEdit.getGrafColor()); 
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
        GrafColumnPlot gPlot = new GrafColumnPlot(gs, input);
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
