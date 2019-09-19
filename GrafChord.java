/*GrafChord - graphs a chord on a curve
 * 
 * @author Bill Gillam
 * @2-3-17
 */
//import javax.swing.*;
import java.io.*;
import java.awt.*;;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GrafChord extends GrafObject implements IGrafable
{
        private String functionString="";
        private GrafProg myOwner;
        private GrafSettings gStuff;
        private String mark =".";
        private double x1 = 0;
        private double y1 = 0;
        private double x2 = 0;
        private double y2 = 0;
        boolean segment = true;
        //private String yString = "";
        
   public GrafChord(){
        setGrafType(GrafType.CHORD);
        setMoveable(false);
        setGrafColor(Color.BLACK);
       
       }     
        
   public GrafChord(GrafProg sess){
        setGrafType(GrafType.CHORD);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
       }
   
   public GrafChord(GrafProg sess, String yString, double firstX, double secondX){
        this(sess);
        setFunctionString(yString);
        x1 = firstX;
        x2 = secondX;
        calcY1();
        calcY2();
    }
    
    public GrafChord(GrafProg sess, String yString, double firstX, double secondX, Color c, String m){
        this(sess, yString, firstX, secondX);
        setGrafColor(c);
        setMark(m);
        
    }
   
   @Override
   public void drawGraf(Graphics2D gc){
       gc.setColor(super.getGrafColor());
       GrafPrimitives.grafString(gStuff,x1, y1, getMark() , gc);
       GrafPrimitives.grafString(gStuff,x2, y2, getMark(), gc);
       if (segment) GrafPrimitives.grafLine(gStuff,x1, y1, x2, y2 ,gc);
       else {
           double slope = (y2-y1)/(x2-x1);
           double yForXMin = slope*(gStuff.getXMin()-x1)+y1;
           double yForXMax = slope*(gStuff.getXMax()-x1)+y1;
           GrafPrimitives.grafLine(gStuff,gStuff.getXMin(), yForXMin, gStuff.getXMax(), yForXMax ,gc);
       }
       gc.setColor(Color.BLACK);
       //gStuff.getGrafPanel().repaint();
    }
 /*   @Override
   public  GrafInputDialog createInputDialog(GrafProg gs){
        GrafInputDialog gfd = new GrafInputDialog(new GrafProg());
        gfd.setTitle("CHORD");
        gfd.setPointPanel(gfd.addPointPanel());
        //gfd.getPointPanel().
        setupChord(gfd);
        gfd.getPointPanel().initFx();
        gfd.setMarkChooser(gfd.addMarkPanel(new ColorRadioMarkPanel(false)));
        gfd.setDeleter(gfd.addDeleterPanel(GrafType.CHORD)); 
        gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.CHORD)));  
        gfd.getCreateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
                saveChord(gs,gfd);
                gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.CHORD)));   
            }
        });
        gfd.getSaveChanges().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gfd.setFinalSave(true);
                saveChord(gs,gfd);
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
   
   
  
     
      /*@Override
      public void setDeleteValues(int index, GrafInputDialog caller, ArrayList<GrafObject> tempList ){
                     GrafChord chEdit = (GrafChord)tempList.get(caller.getDeleter().getPlotIndex().get(index));
                     caller.getPointChooser().setF(chEdit.getFunctionString());
                     caller.getPointChooser().setX1(chEdit.getX1());
                     caller.getPointChooser().setX2(chEdit.getX2());
                     caller.getMarkChooser().setColor(chEdit.getGrafColor());
                    
       }*/
     
   public void setX1(double xval){ x1 = xval; }
   public double getX1() { return x1; } 
   public void setX2(double xval){ x2 = xval; }
   public double getX2() { return x2; } 
   public void sety1(double xval){ y1 = xval; }
   public double gety1() { return y1; } 
   public void sety2(double xval){ y2 = xval; }
   public double gety2() { return y2; } 
   
   private static void saveChord(GrafProg gs, GrafInputDialog gfd){
        if (gfd.getFinalSave() == true && gfd.getPointPanel().getF().equals("")) return; 
        addChord(gs, gfd);
        gfd.getPointPanel().blankF();
        gfd.getPointPanel().blankX1();
        gfd.getPointPanel().blankX2();
    
   }

   private static void addChord(GrafProg gSess, GrafInputDialog gfd){
        if (!FunctionString.isValidAtXIgnoreDomainError(gfd.getPointPanel().getF(), (gSess.getGrafSettings().getXMax()+gSess.getGrafSettings().getXMin())/2)) { 
                   JOptionPane.showMessageDialog(null,
                   "The expression entered is not a valid function.",
                   "Function Format Error",
                   JOptionPane.ERROR_MESSAGE);  
                   return;
        }
        if (Double.isNaN(gfd.getPointPanel().getX1())){gfd.NumErrorMessage("x1", "valid number"); return;}
        if (Double.isNaN(gfd.getPointPanel().getX2())){gfd.NumErrorMessage("x2", "valid number"); return;}
        GrafChord gch = new GrafChord(gSess, gfd.getPointPanel().getF(), gfd.getPointPanel().getX1(), gfd.getPointPanel().getX2(), gfd.getMarkChooser().getColor(), gfd.getMarkChooser().getMark());
        gfd.getTempList().add(gch);      
   }
   
   /*private static void setupChord(GrafInputDialog gfd){
        PointPanel pointPanel = gfd.getPointPanel();
        JPanel rightPanel = pointPanel.getRightPanel();
        pointPanel.setupFunctionChooser();
        pointPanel.getX2JText().setColumns(8);
        rightPanel.add(pointPanel.getX2Label(), BorderLayout.WEST);
        rightPanel.add(pointPanel.getX2JText(), BorderLayout.CENTER);
        gfd.getPointPanel().getBottomPanel().add(rightPanel, BorderLayout.CENTER);
    }*/
   
   public void calcY1(){
       try {
        y1 = FunctionString.fValue(functionString, x1);
    } catch (DomainViolationException e) {
        JOptionPane.showMessageDialog(null, "Error!", "x1 not in domain of function " , JOptionPane.ERROR_MESSAGE);
        x1 = 0; y1 = 0; x2 = 0; y2 = 0;
    } catch (FunctionFormatException e) {}   
       
   }
   public void calcY2(){
       try {
        y2 = FunctionString.fValue(functionString, x2);
    } catch (DomainViolationException e) {
        JOptionPane.showMessageDialog(null, "Error!", "x2 not in domain of function " , JOptionPane.ERROR_MESSAGE);
        x1 = 0; y1 = 0; x2 = 0; y2 = 0;
    }  catch (FunctionFormatException e) {}    
   }
   public void setFunctionString(String fString){ functionString = fString; }
   public String getFunctionString() { return functionString; } 
   public void setMark(String m){mark = m;}
   public String getMark(){return mark;}
   public boolean getSegment(){return segment;} 
   public void setSegment(boolean tf){segment = tf;}
   
   public String toString(){
       return "CHORD: "+getFunctionString()+", ( "+x1+","+y1+"), "+", ( "+x2+","+y2+"), ";//+getGrafColor();
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
   