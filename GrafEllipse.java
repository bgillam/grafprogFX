
/*GrafEllipse in GrafProg
 * used to graph and ellipse
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


public class GrafEllipse extends GrafRectangle implements IGrafable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private GrafProg myOwner;
    private GrafSettings gStuff;
   
    
    public GrafEllipse() {
        super();
        this.setGrafType(GrafType.ELLIPSE);
        
    }

    public GrafEllipse(GrafProg sess) {
        super(sess);
        myOwner = sess;
        gStuff = super.getGrafSettings();
        this.setGrafType(GrafType.ELLIPSE);
        // TODO Auto-generated constructor stub
    }
    
     public GrafEllipse(GrafProg sess, double x1, double y1, double width, double height){
            super(sess, x1, y1, width, height);
            setGrafType(GrafType.ELLIPSE);
            setMoveable(false);
            setGrafColor(Color.BLACK);
            myOwner = sess;
            gStuff = myOwner.getGrafSettings();
            setX(x1);
            setY(y1);
            setWidth(width);
            setHeight(height);
        }
        
        public GrafEllipse(GrafProg sess, double x1, double y1, double width, double height, Color gColor){
            super(sess, x1, y1, width, height);
            setGrafType(GrafType.ELLIPSE);
            setMoveable(false);
            setGrafColor(gColor);
            myOwner = sess;
            gStuff = myOwner.getGrafSettings();
            setX(x1);
            setY(y1);
            setWidth(width);
            setHeight(height);
        }
        
    
     @Override
     public void drawGraf(Graphics2D gc){
         //System.out.println(super.getFillFlag());
         if (super.getFillFlag()) {
               gc.setColor(super.getFill());
               GrafPrimitives.fillEllipse(gStuff,getX(), getY(), getWidth(), getHeight(), gc );
           }
           gc.setColor(super.getGrafColor());
           GrafPrimitives.grafEllipse(gStuff,getX(), getY(), getWidth(), getHeight() ,gc);
           gc.setColor(Color.BLACK);
           gc.setPaint(Color.WHITE);
           
        }
     
    /* @Override
     public GrafInputDialog createInputDialog(GrafProg gs){
        GrafInputDialog gfd = new GrafInputDialog(gs);          
        gfd.setTitle("ELLIPSE");  
        gfd.setPointPanel(gfd.addPointPanel());
        gfd.getPointPanel().addX1Y1();
        gfd.getPointPanel().addWH();
        gfd.setMarkChooser(gfd.addMarkPanel(new FillColorMarkPanel(true, false))); 
        gfd.setDeleter(gfd.addDeleterPanel(GrafType.ELLIPSE)); 
        gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.ELLIPSE)));      
        gfd.getCreateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
                saveEllipse(gs,gfd);
                gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(),GrafType.ELLIPSE)));      
            }
        });
        gfd.getSaveChanges().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gfd.setFinalSave(true);
                saveEllipse(gs,gfd);
                gs.setGrafList(gfd.getTempList());
                gfd.dispose();
            }
        });
        GrafObject.closeGFD(gfd);
        // gfd.setModal(true);
        // gfd.pack();
        // gfd.setVisible(true);  
        return gfd;
     }  */
     
     
         
    
     
     /*@Override
     public  void setDeleteValues(int index, GrafInputDialog caller, ArrayList<GrafObject> tempList ){
                     GrafEllipse ellEdit = (GrafEllipse)tempList.get(caller.getDeleter().getPlotIndex().get(index));
                     caller.getPointChooser().setX1(ellEdit.getX());
                     caller.getPointChooser().setY1(ellEdit.getY());
                     caller.getPointChooser().setW(ellEdit.getWidth());
                     caller.getPointChooser().setH(ellEdit.getHeight());
                     caller.getMarkChooser().setFillChecked(ellEdit.getFillFlag());
                     caller.getMarkChooser().setColor(ellEdit.getGrafColor());  
                     caller.getMarkChooser().setFillColor(ellEdit.getFill());  
                  
                    
       }*/
        
     public String toString(){
           return "ELLIPSE: ("+getX()+", "+getY()+"); ("+getWidth()+", "+getHeight();//+ " "+getGrafColor()+")";
       }
       
        private static void saveEllipse(GrafProg gs, GrafInputDialog gfd){
        if (gfd.getFinalSave() == true && Double.isNaN(gfd.getPointPanel().getX1())) return; 
        addEllipse(gs, gfd);
        gfd.getPointPanel().blankX1();
        gfd.getPointPanel().blankY1();
        gfd.getPointPanel().blankW();
        gfd.getPointPanel().blankH();
       
       }
       
       private static void addEllipse(GrafProg gs, GrafInputDialog gfd){
           if (Double.isNaN(gfd.getPointPanel().getX1())){gfd.NumErrorMessage("x1", "valid number"); return;}
           if (Double.isNaN(gfd.getPointPanel().getY1())){gfd.NumErrorMessage("Y1", "valid number"); return;}    
           if (Double.isNaN(gfd.getPointPanel().getW())){gfd.NumErrorMessage("width", "valid number"); return;}
           if (Double.isNaN(gfd.getPointPanel().getH())){gfd.NumErrorMessage("Height", "valid number"); return;}    
           GrafEllipse gPlot = new GrafEllipse(gs, gfd.getPointPanel().getX1(), gfd.getPointPanel().getY1(), gfd.getPointPanel().getW(), gfd.getPointPanel().getH(),gfd.getMarkChooser().getColor());
           if (gfd.getMarkChooser().fillChecked()) {
               gPlot.setFillFlag(true);
               gPlot.setFill(gfd.getMarkChooser().getFillColor());
            }
           gfd.getTempList().add(gPlot);
        
        }
}
