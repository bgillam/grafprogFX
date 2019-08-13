/*GrafTangent - graphs a tangent to a curve
 * 
 * @author Bill Gillam
 * @version j1.0]
 */
//import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GrafTangent extends GrafObject implements IGrafable
{
        private String functionString="";
        private int segLength = 1000;
        private GrafStage myOwner;
        private GrafSettings gStuff;
        private String mark ="x";
        private double x = 0;
       
   public GrafTangent(){
        setGrafType(GrafType.TANGENT);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        
       }     
        
        
   public GrafTangent(GrafStage sess){
        setGrafType(GrafType.TANGENT);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
       
       }
   
   public GrafTangent(GrafStage sess, String yString, double x){
        this(sess);
        setX(x);
        setFunctionString(yString);
       
   }
   
    
   public GrafTangent(GrafStage sess, String yString, double x, Color c, String mark){
        this(sess, yString, x);
        setGrafColor(c);
        setMark(mark);
   }
   
   @Override
   public void drawGraf(Graphics2D gc){
       gc.setColor(super.getGrafColor());
       double dx = (gStuff.getXMax() - gStuff.getXMin())/1000;
       double x1 = x - dx;
       double x2 = x + dx;
       double y1;
    try {
        y1 = FunctionString.fValue(functionString, x1);
    } catch (DomainViolationException e) {
        JOptionPane.showMessageDialog(null, "Error!", x1+" not in domain of function " , JOptionPane.ERROR_MESSAGE);
        return;
    }catch (FunctionFormatException e) {return;}   
       double y2;
    try {
        y2 = FunctionString.fValue(functionString, x2);
    } catch (DomainViolationException e) {
        JOptionPane.showMessageDialog(null, "Error!", x2+" not in domain of function " , JOptionPane.ERROR_MESSAGE);
        return;
    }catch (FunctionFormatException e) {return;}   
       double yX;
    try {
        yX = FunctionString.fValue(functionString, x);
    } catch (DomainViolationException e) {
        JOptionPane.showMessageDialog(null, "Error!", x+" not in domain of function " , JOptionPane.ERROR_MESSAGE);
        return;
    }catch (FunctionFormatException e) {return;}   
       GrafPrimitives.grafString(gStuff,x, yX, getMark() , gc);
       double slope = (y2-y1)/(x2-x1);
       double yForXMin = slope*(gStuff.getXMin()-x1)+y1;
       double yForXMax = slope*(gStuff.getXMax()-x1)+y1;
       GrafPrimitives.grafLine(gStuff,gStuff.getXMin(), yForXMin, gStuff.getXMax(), yForXMax ,gc);
       gc.setColor(Color.BLACK);
       //gStuff.getGrafPanel().repaint();
    }
 
    @Override
   public  GrafInputDialog createInputDialog(GrafStage gs){
       GrafInputDialog gfd = new GrafInputDialog(gs);
       gfd.setTitle("TANGENT");
       gfd.setPointPanel(gfd.addPointPanel());
       gfd.getPointPanel().setupFunctionChooser();
       gfd.getPointPanel().initFx();
       gfd.setMarkChooser(gfd.addMarkPanel(new ColorRadioMarkPanel(false)));
       gfd.setDeleter(gfd.addDeleterPanel(GrafType.TANGENT)); 
       gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.TANGENT)));  
       gfd.getCreateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
                saveTangent(gs,gfd);
                gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.TANGENT)));     
            }
        });
        gfd.getSaveChanges().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gfd.setFinalSave(true);
                saveTangent(gs,gfd);
                gs.setGrafList(gfd.getTempList());
                gfd.dispose();
            }
        });
        GrafObject.closeGFD(gfd);
      
        return gfd;
   } 
    
   
    
    @Override
     public void setDeleteValues(int index, GrafInputDialog caller, ArrayList<GrafObject> tempList ){
                     GrafTangent vEdit = (GrafTangent)tempList.get(caller.getDeleter().getPlotIndex().get(index));
                     caller.getPointChooser().setF(vEdit.getFunctionString());
                     caller.getPointChooser().setX1(vEdit.getX());
                     caller.getMarkChooser().setColor(vEdit.getGrafColor());
                  
                    
       }
       
       private static void saveTangent(GrafStage gs, GrafInputDialog gfd){
        if (gfd.getFinalSave() == true && gfd.getPointPanel().getF().equals("")) return; 
        addTangent(gs, gfd);
        gfd.getPointPanel().blankF();
        gfd.getPointPanel().blankX1();
   }
   
   private static void addTangent(GrafStage gSess, GrafInputDialog gfd){
       if (!FunctionString.isValidAtXIgnoreDomainError(gfd.getPointPanel().getF(), (gSess.getGrafSettings().getXMax()+gSess.getGrafSettings().getXMin())/2)) { 
                   JOptionPane.showMessageDialog(null,
                   "The expression entered is not a valid function.",
                   "Function Format Error",
                   JOptionPane.ERROR_MESSAGE);  
                   return;
        }
        if (Double.isNaN(gfd.getPointPanel().getX1())){gfd.NumErrorMessage("x1", "valid number"); return;}
        //System.out.println(gfd.getPointPanel().getF());
        GrafTangent gt = new GrafTangent(gSess, gfd.getPointPanel().getF(), gfd.getPointPanel().getX1(), gfd.getMarkChooser().getColor(), gfd.getMarkChooser().getMark());
        gfd.getTempList().add(gt); 
    }
    
     
   public void setX(double xval){ x = xval; }
   public double getX() { return x; }   
   public void setFunctionString(String fString){ functionString = fString; }
   public String getFunctionString() { return functionString; } 
   public void setMark(String m){mark = m;}
   public String getMark(){return mark;}
   
   public String toString(){
       return "TANGENT: "+getFunctionString()+", "+getX()+") ";//+getGrafColor();
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
   