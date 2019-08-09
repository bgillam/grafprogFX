/*GrafSegment - graphs a line segment
 * 
 * @author Bill Gillam
 * @version 1/27/17]
 */
import java.io.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;



public class GrafSegment extends GrafObject implements IGrafable
{
        private String functionString="";
        private GrafProg myOwner;
        private GrafSettings gStuff;
        public enum EndType{RIGHTARROW, LEFTARROW, DOUBLEARROW, NOARROW}     
        private EndType ends = EndType.NOARROW;
        private double x1 = 0;
        private double x2 = 0;
        private double y1 = 0;
        private double y2 = 0;
        //private String yString = "";
        
        
   public GrafSegment(){
        setGrafType(GrafType.LINESEGMENT);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        
       }
        
   public GrafSegment(GrafProg sess){
        setGrafType(GrafType.LINESEGMENT);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
       }
   
   public GrafSegment(GrafProg sess, double x1, double y1, double x2, double y2){
        this(sess);
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
    }
    
    public GrafSegment(GrafProg sess, double x1, double y1, double x2, double y2, Color gColor){
        this(sess, x1, y1, x2, y2);
        setGrafColor(gColor);
    }
   
   
   @Override
   public void drawGraf(Graphics2D gc){
       gc.setColor(super.getGrafColor());
       GrafPrimitives.grafLine(gStuff,x1, y1, x2, y2 ,gc);
       gc.setColor(Color.BLACK);
       
    }
 
   @Override
   public   GrafInputDialog createInputDialog(GrafProg gs){
        GrafInputDialog gfd = new GrafInputDialog(gs);
        gfd.setTitle("LINESEGMENT"); 
        gfd.setPointPanel(gfd.addPointPanel());
        gfd.getPointPanel().addX1Y1();
        gfd.getPointPanel().addX2Y2();
        gfd.setMarkChooser(gfd.addMarkPanel(new FillColorMarkPanel(false, false)));  
        gfd.setDeleter(gfd.addDeleterPanel(GrafType.LINESEGMENT));
        gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.LINESEGMENT)));          
        gfd.getCreateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
                saveSegment(gs,gfd);
                gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(),GrafType.LINESEGMENT)));        
            }
        });
        gfd.getSaveChanges().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gfd.setFinalSave(true);
                saveSegment(gs,gfd);
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
                     GrafSegment lEdit = (GrafSegment)tempList.get(caller.getDeleter().getPlotIndex().get(index));
                     caller.getPointChooser().setX1(lEdit.getX1());
                     caller.getPointChooser().setY1(lEdit.getY1());
                     caller.getPointChooser().setX2(lEdit.getX2());
                     caller.getPointChooser().setY2(lEdit.getY2());
                     //caller.getMarkChooser().setTextString(ptEdit.getText());
                     //caller.getMarkChooser().setCurrentFont(ptEdit.getFont());
                     caller.getMarkChooser().setColor(lEdit.getGrafColor());   
       }
    
   public void setX1(double xval){ x1 = xval; }
   public double getX1() { return x1; }
   public void setX2(double xval){ x2 = xval; }
   public double getX2() { return x2; } 
   public void setY1(double xval){ y1 = xval;}
   public double getY1() { return y1; }
   public void setY2(double xval){ y2 = xval; }
   public double getY2() { return y2; } 
   public void setEnds(EndType et){ ends= et;}
   public EndType getEnds(){return ends;}
   
   public String toString(){
       return "LINESEGMENT: ("+getX1()+", "+getY1()+"); ("+getX2()+", "+getY2();
       //+               " "+getGrafColor()+")";
   }
   
   
    private static void saveSegment(GrafProg gs, GrafInputDialog gfd){
            if (gfd.getFinalSave() == true && Double.isNaN(gfd.getPointPanel().getX1())) return; 
            addSegment(gs, gfd);
            gfd.getPointPanel().blankX1();
            gfd.getPointPanel().blankY1();
            gfd.getPointPanel().blankX2();
            gfd.getPointPanel().blankY2();  
    
    }
    
    
    private static void addSegment(GrafProg gs, GrafInputDialog gfd){
            if (Double.isNaN(gfd.getPointPanel().getX1())){gfd.NumErrorMessage("x1", "valid number"); return;}
            if (Double.isNaN(gfd.getPointPanel().getY1())){gfd.NumErrorMessage("Y1", "valid number"); return;}    
            if (Double.isNaN(gfd.getPointPanel().getX2())){gfd.NumErrorMessage("x2", "valid number"); return;}
            if (Double.isNaN(gfd.getPointPanel().getY2())){gfd.NumErrorMessage("y2", "valid number"); return;}    
            GrafSegment gPlot = new GrafSegment(gs, gfd.getPointPanel().getX1(), gfd.getPointPanel().getY1(), gfd.getPointPanel().getX2(), gfd.getPointPanel().getY2(), gfd.getMarkChooser().getColor());
            gfd.getTempList().add(gPlot);
    
    }
   
 }
   
 

/* Inherited from GrafObject
   private GrafProg.GrafType grafType;
   private Color grafColor = Color.BLACK; 
   private boolean moveable;
   private GrafProg myOwner;
     
   
   public void drawGraf(Graphics2D g2D){};
   
   public void setGrafType(GrafProg.GrafType gt){grafType = gt;}
   public GrafProg.GrafType getType(){return grafType; }
   
   public boolean isMoveable(){ return moveable; } 
   public void setMoveable(boolean tf){ moveable = tf;  }
   public boolean getMoveable(){return moveable;}
   
   public void setOwner(GrafProg owner){myOwner = owner;}
   public GrafProg getOwner(){return myOwner;}
   
   public void setGrafColor(Color c){grafColor = c;   }
   public Color getGrafColor() { return grafColor;}
  */
   