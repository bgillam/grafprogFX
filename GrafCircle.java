
/*GrafCircle in GrafProg 
 * used to graphs a circle
 * 
 * @author Bill Gillam
 * @version 1/27/17]
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

//import GrafProg.GrafType;


public class GrafCircle extends GrafObject implements IGrafable{
        
        //private String functionString="";
        private GrafProg myOwner;
        private GrafSettings gStuff;
        private double cX = 0;
        private double cY = 0;
        private double r = 0;
        private Color fill = Color.WHITE;
        private boolean fillFlag = false;
        //private String yString = "";
    
    public GrafCircle(){
        setGrafType(GrafType.CIRCLE);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        
       }

    public GrafCircle(GrafProg sess){
        setGrafType(GrafType.CIRCLE);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
       }
    
     public GrafCircle(GrafProg sess, double x1, double y1, double r1){
        this(sess);
        setCx(x1);
        setCy(y1);
        setR(r1);
        
    }
        
    public GrafCircle(GrafProg sess, double x1, double y1, double r1, Color gColor){
        this(sess, x1, y1, r1);
        setGrafColor(gColor);
                  
    }
    
     @Override
     public void drawGraf(Graphics2D gc){
         //System.out.println(super.getFillFlag());
         if (getFillFlag()) {
               gc.setColor(getFill());
               GrafPrimitives.fillEllipse(gStuff,getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight(), gc );
           }
           gc.setColor(super.getGrafColor());
           GrafPrimitives.grafEllipse(gStuff,getUpperLeftX(), getUpperLeftY(), getWidth(), getHeight(), gc );
           gc.setColor(Color.BLACK);
           gc.setPaint(Color.WHITE);
           
        }
     
     @Override   
    public GrafInputDialog createInputDialog(GrafProg gs){
        GrafInputDialog gfd = new GrafInputDialog(gs);     
        gfd.setTitle("CIRCLE"); 
        gfd.setPointPanel(gfd.addPointPanel());
        gfd.getPointPanel().addX1Y1();
        gfd.getPointPanel().addR();
        gfd.setMarkChooser(gfd.addMarkPanel(new FillColorMarkPanel(true, false))); 
        gfd.setDeleter(gfd.addDeleterPanel(GrafType.CIRCLE)); 
        gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.CIRCLE)));      
        gfd.getCreateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
                saveCircle(gs,gfd);
                gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(),GrafType.CIRCLE)));     
            }
        });
        gfd.getSaveChanges().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gfd.setFinalSave(true);
                saveCircle(gs,gfd);
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
                     GrafCircle circEdit = (GrafCircle)tempList.get(caller.getDeleter().getPlotIndex().get(index));
                     caller.getPointChooser().setX1(circEdit.getCx());
                     caller.getPointChooser().setX2(circEdit.getCy());
                     caller.getPointChooser().setR(circEdit.getR());
                     //caller.getPointChooser().setH(circEdit.getHeight());
                     caller.getMarkChooser().setFillChecked(circEdit.getFillFlag());
                     caller.getMarkChooser().setColor(circEdit.getGrafColor());  
                     caller.getMarkChooser().setFillColor(circEdit.getFill());  
                  
                    
       }
        
   public void setCx(double xval){ cX = xval; }
   public double getCx() { return cX; }
   public void setCy(double xval){ cY = xval; }
   public double getCy() { return cY; }
   public void setR(double rval){ r = rval;}
   public double getR() {return r;}
   public double getUpperLeftX(){ return cX - r; }
   public double getUpperLeftY() { return cY+r; }
   public double getWidth() { return 2*r; } 
   public double getHeight() { return 2*r; }
   public double getDiameter(){return 2*r;}
   public Color getFill(){return fill;}
   public void setFill(Color f){fill = f;}
   public GrafSettings getGrafSettings(){return gStuff;}
   public void setFillFlag(boolean tf){fillFlag = tf;}
   public boolean getFillFlag(){return fillFlag;}   
        
     public String toString(){
           return "CIRCLE: ("+getCx()+", "+getCy()+"); "+getR();//+", "+ " "+getGrafColor()+")";
       }
       
        private static void saveCircle(GrafProg gs, GrafInputDialog gfd){
        if (gfd.getFinalSave() == true && Double.isNaN(gfd.getPointPanel().getX1())) return; 
        addCircle(gs, gfd);
        gfd.getPointPanel().blankX1();
        gfd.getPointPanel().blankY1();
        gfd.getPointPanel().blankR();

    
    }
        
    private static void addCircle(GrafProg gs, GrafInputDialog gfd){
       if (Double.isNaN(gfd.getPointPanel().getX1())){gfd.NumErrorMessage("x1", "valid number"); return;}
       if (Double.isNaN(gfd.getPointPanel().getY1())){gfd.NumErrorMessage("Y1", "valid number"); return;}    
       if (Double.isNaN(gfd.getPointPanel().getR())){gfd.NumErrorMessage("R", "valid number"); return;}
       GrafCircle gPlot = new GrafCircle(gs, gfd.getPointPanel().getX1(), gfd.getPointPanel().getY1(), gfd.getPointPanel().getR(), gfd.getMarkChooser().getColor());
       if (gfd.getMarkChooser().fillChecked()) {
           gPlot.setFillFlag(true);
           gPlot.setFill(gfd.getMarkChooser().getFillColor());
       }
       gfd.getTempList().add(gPlot);
    
    }
}