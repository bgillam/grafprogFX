
/*GrafText - Displays text on the graphing panel
 * 
 * @author Bill Gillam
 * @version j1.0]
 */
//import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;


public class GrafText extends GrafObject implements IGrafable
{       private String text;
        private GrafProg myOwner;
        protected GrafSettings gStuff;
        private Font font;
        //protected Color color;
        protected double x = 0;
        protected double y = 0;
        
        
        
   public GrafText(){
     super();
     setGrafType(GrafType.TEXT);
     setText("");
    }
        
   public GrafText(GrafProg sess){
        setText("");
        setGrafType(GrafType.TEXT);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
        //setFont(sess.getGraphics().getFont());
       }
   
   public GrafText(GrafProg sess, double x, double y, String t){
        this(sess);
        setX(x);
        setY(y);
        setText(t);
   }
   
   public GrafText(GrafProg sess, double x, double y, String t, Font f, Color c){
        this(sess, x, y, t); 
        setGrafColor(c);
        setFont(f);
  }
   
   
   @Override
   public void drawGraf(Graphics2D gc){
      
       gc.setColor(super.getGrafColor());
       Font oldFont = gc.getFont();
       gc.setFont(font);
       GrafPrimitives.grafString(gStuff,x, y, text,  gc);
       gc.setFont(oldFont);
       gc.setColor(Color.BLACK);
    }
 
 /* @Override
  public GrafInputDialog createInputDialog(GrafProg gs){
         GrafInputDialog gfd = new GrafInputDialog(new GrafProg());
         gfd.setTitle("TEXT"); 
         gfd.setPointPanel(gfd.addPointPanel());
         gfd.getPointPanel().addX1Y1();
         gfd.setMarkChooser(gfd.addMarkPanel(new TextMarkPanel()));
         gfd.setDeleter(gfd.addDeleterPanel(GrafType.TEXT));
         gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.TEXT)));  
         gfd.getCreateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
                saveText(gs,gfd);
                gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.TEXT)));  
                
            }
        });
        gfd.getSaveChanges().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gfd.setFinalSave(true);
                saveText(gs,gfd);
                gs.setGrafList(gfd.getTempList());
                gfd.dispose();
            }
        });
        GrafObject.closeGFD(gfd);
        
        return gfd;
     }
    */
  
    

 /*@Override
 public void setDeleteValues(int index, GrafInputDialog caller, ArrayList<GrafObject> tempList ){
     try{
         GrafText tEdit = (GrafText)tempList.get(caller.getDeleter().getPlotIndex().get(index));
         caller.getPointChooser().setX1(tEdit.getX());
         caller.getPointChooser().setY1(tEdit.getY());
         caller.getMarkChooser().setTextString(tEdit.getText());
         caller.getMarkChooser().setGrafFont(tEdit.getFont());
         caller.getMarkChooser().setColor(tEdit.getGrafColor());
       }catch (NullPointerException e){
     }
 }*/
 
 private static void saveText(GrafProg gSess, GrafInputDialog gfd){
         if (gfd.getFinalSave() == true && Double.isNaN(gfd.getPointPanel().getX1())) return; 
             addText(gSess,gfd );
             gfd.getPointPanel().blankX1();
             gfd.getPointPanel().blankY1();
           
   }

 private static void addText(GrafProg gSess, GrafInputDialog gfd){
       if (Double.isNaN(gfd.getPointPanel().getX1())){gfd.NumErrorMessage("x1", "valid number"); return;}
       if (Double.isNaN(gfd.getPointPanel().getY1())){gfd.NumErrorMessage("Y1", "valid number"); return;}    
       GrafText gPlot = new GrafText(gSess, gfd.getPointPanel().getX1(), gfd.getPointPanel().getY1(), gfd.getMarkChooser().getTextMark() , gfd.getMarkChooser().getFont() , gfd.getMarkChooser().getColor());
       gfd.getTempList().add(gPlot);
  }  
   
   public void setX(double xval){ x = xval; }
   public double getX() { return x; }   
   public void setY(double yval){ y = yval; }
   public double getY() { return y; }   
   public void setFont(Font f){font = f;}
   public Font getFont(){return font;}
   public String getText(){return text;}
   public void setText(String s){text = s;}
  
 
 public String toString(){
    return "TEXT: ("+getX()+", "+getY()+") "+getText();
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
 