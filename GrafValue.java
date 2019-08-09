/*GrafValue - graphs a value on a curve
 * 
 * @author Bill Gillam
 * @version 1/27/17]
 */
//import javax.swing.*;
import java.io.*;
import java.awt.*;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.border.*;
import javax.swing.UIManager;
import java.util.ArrayList;

public class GrafValue extends GrafObject implements IGrafable{
        private String functionString="";
        //private int segLength = 1000;
        private GrafProg myOwner;
        private GrafSettings gStuff;
        private String mark ="x";
        private double x = 0;
        private double y = 0;
       
    public GrafValue(){
        setGrafType(GrafType.FVALUE);
        setMoveable(false);
        setGrafColor(Color.BLACK);
      
       
       }    
        
        
   public GrafValue(GrafProg sess){
        setGrafType(GrafType.FVALUE);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
       
       }
   
   public GrafValue(GrafProg sess, String yString, double x){
        this(sess);
        setX(x);
        setFunctionString(yString);
        y = calcY();
   }
   
    
   public GrafValue(GrafProg sess, String yString, double x, Color c, String mark){
        this(sess, yString, x);
        setGrafColor(c);
        setMark(mark);
       
   }
   
   @Override
   public void drawGraf(Graphics2D gc){
       gc.setColor(super.getGrafColor());
       if (!Double.isNaN(y)) GrafPrimitives.grafString(gStuff,x, y, getMark() , gc);
       gc.setColor(Color.BLACK);
       myOwner.setMessage2(toString());
       //gStuff.getGrafPanel().repaint();
   }
   
   @Override
   public GrafInputDialog createInputDialog(GrafProg gs){
       GrafInputDialog gfd = new GrafInputDialog(gs);
       gfd.setTitle("FVALUE");
       gfd.setPointPanel(gfd.addPointPanel());
       gfd.getPointPanel().setupFunctionChooser();
       gfd.getPointPanel().initFx();
       gfd.setMarkChooser(gfd.addMarkPanel(new ColorRadioMarkPanel(false)));
       gfd.setDeleter(gfd.addDeleterPanel(GrafType.FVALUE));
       gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.FVALUE)));      
       gfd.getCreateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
                saveValue(gs,gfd);
                gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.FVALUE))); 
            }
        });
        gfd.getSaveChanges().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gfd.setFinalSave(true);
                saveValue(gs,gfd);
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
     public void setDeleteValues(int index, GrafInputDialog caller, ArrayList<GrafObject> tempList ){
                   GrafTangent fvEdit = (GrafTangent)tempList.get(caller.getDeleter().getPlotIndex().get(index));
                     caller.getPointChooser().setF(fvEdit.getFunctionString());
                     caller.getPointChooser().setX1(fvEdit.getX());
                     caller.getMarkChooser().setColor(fvEdit.getGrafColor());
                  
                    
       }
       
   private static void saveValue(GrafProg gs, GrafInputDialog gfd){
    if (gfd.getFinalSave() == true && gfd.getPointPanel().getF().equals("")) return; 
           addValue(gs,gfd);
           gfd.getPointPanel().blankF();
           gfd.getPointPanel().blankX1();
            
   }   
  private static void addValue(GrafProg gSess, GrafInputDialog gfd ){
        if (!FunctionString.isValidAtXIgnoreDomainError(gfd.getPointPanel().getF(), (gSess.getGrafSettings().getXMax()+gSess.getGrafSettings().getXMin())/2)) { 
                   JOptionPane.showMessageDialog(null,
                   "The expression entered is not a valid function.",
                   "Function Format Error",
                   JOptionPane.ERROR_MESSAGE);  
                   return;
        }
        if (Double.isNaN(gfd.getPointPanel().getX1())){gfd.NumErrorMessage("x1", "valid number"); return;}
        GrafValue gv = new GrafValue(gSess, gfd.getPointPanel().getF(), gfd.getPointPanel().getX1(), gfd.getMarkChooser().getColor(), gfd.getMarkChooser().getMark());
        gfd.getTempList().add(gv); 
    }
   
   public void setX(double xval){ x = xval; }
   public double getX() { return x; }   
   public void setFunctionString(String fString){ functionString = fString; }
   public String getFunctionString() { return functionString; } 
   public void setMark(String m){mark = m;}
   public String getMark(){return mark;}
   
   public String toString(){
       return "FVALUE: "+getFunctionString()+": ("+x+", "+y+") ";//+getGrafColor();
   }
   
   private double calcY(){
      try {
        y = FunctionString.fValue(functionString, x);
      } catch (DomainViolationException e) {
        JOptionPane.showMessageDialog(null, "Error!", x+" not in domain of function " , JOptionPane.ERROR_MESSAGE);
        return Double.NaN;
      }catch (FunctionFormatException e) {return Double.NaN;}   
      return y;
      //gStuff.getGrafPanel().repaint();
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
   