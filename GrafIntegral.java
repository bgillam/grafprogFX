/*GrafIntegral - graphs Reinman Sum Histogram and calcs area.
 * 
 * @author Bill Gillam
 * @version 1-31-17
 */
//import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GrafIntegral extends GrafObject implements IGrafable
{
        private String functionString="";
        private GrafProg myOwner;
        private GrafSettings gStuff;
        private String mark =".";
        private double x1 = 0;
        private double x2 = 0;
        int n=100;
        //private String yString = "";
        
   public GrafIntegral(){
        setGrafType(GrafType.INTEGRAL);
        setMoveable(false);
        setGrafColor(Color.BLACK);
       
       }     
        
   public GrafIntegral(GrafProg sess){
        setGrafType(GrafType.INTEGRAL);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
       }
   
       public GrafIntegral(GrafProg sess, String yString, double firstX, double secondX, int nVal){
        this(sess);
        setFunctionString(yString);
        x1 = firstX;
        x2 = secondX;
        n = nVal;
        //super.setGrafColor(c);
    }
       
       
   public GrafIntegral(GrafProg sess, String yString, double firstX, double secondX, int nVal, Color c){
        this(sess, yString, firstX, secondX, nVal);
        super.setGrafColor(c);
    }
   
   @Override  
   public void drawGraf(Graphics2D gc){
       gc.setColor(super.getGrafColor());
       double dx = (x2-x1)/n;
       double height;
       double sum=0;
       for (double left = x1; left < x2; left+=dx){
           try {
               height = (FunctionString.fValue(functionString, left) + FunctionString.fValue(functionString, left+dx))/2;
           } catch (DomainViolationException e) {
               height = 0;
           }catch (FunctionFormatException e) { height = 0; }   
           sum = sum + dx*height;
           if (height >= 0) GrafPrimitives.grafRect(gStuff,left, height, dx, height, gc);
           else GrafPrimitives.grafRect(gStuff,left, 0, dx, height, gc);
          // gStuff.grafRect(left, 0, dx, height, gc);
           myOwner.setMessage3("Sum = "+sum);
           myOwner.setMessage2("Area from "+x1+" to "+x2+" estimated with "+n+" rectangles");
       }
       
       gc.setColor(Color.BLACK);
       //gStuff.getGrafPanel().repaint();
    }
    
   /* @Override
    public GrafInputDialog createInputDialog(GrafProg gs){
        GrafInputDialog gfd = new GrafInputDialog(gs);
        gfd.setTitle("INTEGRAL");
        gfd.setPointPanel(gfd.addPointPanel());
        setupIntegral(gfd);
        gfd.getPointPanel().initFx();
        gfd.setDeleter(gfd.addDeleterPanel(GrafType.INTEGRAL)); 
        gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.INTEGRAL)));  
        gfd.setMarkChooser(gfd.addMarkPanel(new FillColorMarkPanel(true, false)));//gSess.getGraphics().getFont(), true, false, false, false, false, false, false);
        
        gfd.getCreateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
                saveIntegral(gs,gfd);
                gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.INTEGRAL)));   
            }
        });
        gfd.getSaveChanges().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gfd.setFinalSave(true);
                saveIntegral(gs,gfd);
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
                   GrafIntegral intEdit = (GrafIntegral)tempList.get(caller.getDeleter().getPlotIndex().get(index));
                     caller.getPointChooser().setF(intEdit.getFunctionString());
                     caller.getPointChooser().setX1(intEdit.getX1());
                     caller.getPointChooser().setX2(intEdit.getX2());
                     caller.getPointChooser().setN(intEdit.getN());
                     caller.getMarkChooser().setColor(intEdit.getGrafColor());
                  
                    
       }
       
          private static void saveIntegral(GrafProg gs, GrafInputDialog gfd){
        if (gfd.getFinalSave() == true && gfd.getPointPanel().getF().equals("")) return; 
        addIntegral(gs, gfd);
        gfd.getPointPanel().blankF();
        gfd.getPointPanel().blankX1();
        gfd.getPointPanel().blankX2();
    }
    
    private static void addIntegral(GrafProg gs, GrafInputDialog gfd){
        if (!FunctionString.isValidAtXIgnoreDomainError(gfd.getPointPanel().getF(), (gs.getGrafSettings().getXMax()+gs.getGrafSettings().getXMin())/2)) { 
                   JOptionPane.showMessageDialog(null,
                   "The expression entered is not a valid function.",
                   "Function Format Error",
                   JOptionPane.ERROR_MESSAGE);  
                   return;
        }
        if (Double.isNaN(gfd.getPointPanel().getX1())){gfd.NumErrorMessage("x1", "valid number"); return;}
        if (Double.isNaN(gfd.getPointPanel().getX2())){gfd.NumErrorMessage("x2", "valid number"); return;}
        if (gfd.getPointPanel().getN()==null){ gfd.NumErrorMessage("n", "integer"); return;}
        GrafIntegral gint = new GrafIntegral(gs, gfd.getPointPanel().getF(), gfd.getPointPanel().getX1(), gfd.getPointPanel().getX2(), gfd.getPointPanel().getN(), gfd.getMarkChooser().getColor());
        gfd.getTempList().add(gint); 
    }
    
    /*public static void setupIntegral(GrafInputDialog gfd){
        PointPanel pointPanel = gfd.getPointPanel();
        pointPanel.setupFunctionChooser();
        pointPanel.getX2JText().setColumns(8);
        pointPanel.getNJText().setColumns(8);
        JPanel rightPanel = pointPanel.getRightPanel();
        rightPanel.add(pointPanel.getX2Label(), BorderLayout.WEST);
        rightPanel.add(pointPanel.getX2JText(), BorderLayout.CENTER);
        JPanel rightPanel2 = pointPanel.getRightPanel2();
        rightPanel2.add(pointPanel.getNLabel(), BorderLayout.WEST);
        rightPanel2.add(pointPanel.getNJText(), BorderLayout.CENTER);
        JPanel bottomPanel = pointPanel.getBottomPanel();
        bottomPanel.add(rightPanel, BorderLayout.CENTER);
        bottomPanel.add(rightPanel2, BorderLayout.EAST);
    }*/
    
   public void setX1(double xval){ x1 = xval; }
   public double getX1() { return x1; } 
   public void setX2(double xval){ x2 = xval; }
   public double getX2() { return x2; } 
   public void setFunctionString(String fString){ functionString = fString; }
   public String getFunctionString() { return functionString; } 
   public void setMark(String m){mark = m;}
   public String getMark(){return mark;}
   public int getN(){return n;}
   public void setN(int nVal){n = nVal;}
   
   public String toString(){
       return "INTEGRAL: "+getFunctionString()+", start: "+x1+" end: "+x2+", n: "+getN()+", ";//+getGrafColor();
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
  