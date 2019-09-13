
/**
 * Oject for graphing a point
 * 
 * @author Bill Gillam
 * @version 2/25/15   
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;

public class GrafPoint extends GrafText implements IGrafable
{
   //Constructors      
   public GrafPoint(){
       super();
       setGrafType(GrafType.POINT);
       super.setText(".");
   }
   
   public GrafPoint(GrafProg sess){
       super(sess);
       setGrafType(GrafType.POINT);
       super.setText(".");
       
   }
   
   public GrafPoint(GrafProg sess, double x, double y){
       super(sess, x, y, ".");
       setGrafType(GrafType.POINT);
   }
   

   public GrafPoint(GrafProg sess, double x, double y, String t){
        super(sess, x, y, t);
        setGrafType(GrafType.POINT);
    }
    
   public GrafPoint(GrafProg sess, double x, double y, String t, Color c){
        super(sess, x, y, t);
        setGrafType(GrafType.POINT);
        setGrafColor(c);
   }
   
   public GrafPoint(GrafProg sess, double x, double y, String t, Font f, Color c){
        super(sess, x, y, t, f, c);
        setGrafType(GrafType.POINT);
   }
   
     @Override
     public void drawGraf(Graphics2D gc){
       gc.setColor(getGrafColor());
       GrafPrimitives.grafString(gStuff,x, y, getMark(),  gc);
       gc.setColor(Color.BLACK);
    }
    
    
     /*@Override
     public GrafInputDialog createInputDialog(GrafProg gs){
         GrafInputDialog gfd = new GrafInputDialog(new GrafProg());
         gfd.setTitle("Point"); 
         gfd.setPointPanel(gfd.addPointPanel());
         gfd.getPointPanel().addX1Y1();
         //gfd.setMarkChooser(gfd.addMarkPanel(new FillColorMarkPanel(false, false))); 
         gfd.setMarkChooser(gfd.addMarkPanel(new ColorRadioMarkPanel(false)));
         gfd.setDeleter(gfd.addDeleterPanel(GrafType.POINT));   
         gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.POINT)));  
         gfd.getCreateButton().addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0    ) {
                savePoint(gs,gfd);
                gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.POINT)));  
                //gfd.getDeleter().resetPlotListModel(gfd.getTempList(), GrafType.POINT);    
         }
         });
         gfd.getSaveChanges().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gfd.setFinalSave(true);
                savePoint(gs,gfd);
                gs.setGrafList(gfd.getTempList());
                gfd.dispose();
            }
         });
         GrafObject.closeGFD(gfd);
         // gfd.setModal(true);
         // gfd.pack();
         // gfd.setVisible(true);  
         return gfd;
     }*/
    
     
    @Override
    public  void setDeleteValues(int index, GrafInputDialog caller, ArrayList<GrafObject> tempList ){
                    GrafPoint ptEdit = (GrafPoint)tempList.get(caller.getDeleter().getPlotIndex().get(index));
                    caller.getPointChooser().setX1(ptEdit.getX());
                    caller.getPointChooser().setY1(ptEdit.getY());
                    caller.getMarkChooser().setTextString(ptEdit.getMark());
                    //caller.getMarkChooser().setCurrentFont(ptEdit.getFont());
                    caller.getMarkChooser().setColor(ptEdit.getGrafColor());     
       }
   
   public void setMark(String s){super.setText(s);}
   public String getMark(){return super.getText();}
   
   public String toString(){
    return "POINT: ("+getX()+", "+getY()+") "+getMark();//+ ", "+getGrafColor();
    }
   
    private static void savePoint(GrafProg gSess, GrafInputDialog gfd){
         if (gfd.getFinalSave() == true && Double.isNaN(gfd.getPointPanel().getX1())) return; 
             addPoint(gSess,gfd );
             gfd.getPointPanel().blankX1();
             gfd.getPointPanel().blankY1();
    }

    private static void addPoint(GrafProg gSess, GrafInputDialog gfd){
       if (Double.isNaN(gfd.getPointPanel().getX1())){gfd.NumErrorMessage("x1", "valid number"); return;}
       if (Double.isNaN(gfd.getPointPanel().getY1())){gfd.NumErrorMessage("Y1", "valid number"); return;}    
       GrafPoint gPlot = new GrafPoint(gSess, gfd.getPointPanel().getX1(), gfd.getPointPanel().getY1(), gfd.getMark(), gfd.getMarkChooser().getColor());
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