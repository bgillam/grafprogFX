/*GrafZeros - graphs the zeros of a function
 * 
 * @author Bill Gillam
 * @version j1.0]
 */
//import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GrafZeros extends GrafObject implements IGrafable
{
        private String functionString="";
        private String zeroString = "";
        private GrafStage myOwner;
        private GrafSettings gStuff;
        private String mark ="X";
        private double startX = 0;
        private double endX = 0;
        private double dx = .01;
        private ArrayList<Double> zeroList = new ArrayList<Double>(); 
        //private String yString = "";
        
        public GrafZeros(){
        setGrafType(GrafType.FZERO);
        setMoveable(false);
        setGrafColor(Color.BLACK);
       
       }
        
   public GrafZeros(GrafStage sess){
        setGrafType(GrafType.FZERO);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
       }
   
       public GrafZeros(GrafStage sess, String yString, double firstX, double secondX, double dVal){
        this(sess);
        setFunctionString(yString);
        startX = firstX;
        endX = secondX;
        dx = dVal;
        findZeroPoints();
        //super.setGrafColor(c);
    }
       
       
   public GrafZeros(GrafStage sess, String yString, double firstX, double secondX, double dVal, Color c, String m){
        this(sess, yString, firstX, secondX, dVal);  
        mark = m;
        super.setGrafColor(c);
    }
   
   @Override
   public void drawGraf(Graphics2D gc){
       double y = 0;
       gc.setColor(super.getGrafColor());
       for (double root: zeroList){
            try {
                y =  FunctionString.fValue(functionString, root);
            } catch (DomainViolationException e) {
                continue;
            }catch (FunctionFormatException e) {continue;}   
            GrafPrimitives.grafString(gStuff,root, y, getMark() , gc);   
      }
       gc.setColor(Color.BLACK);
       //gStuff.getGrafPanel().repaint();
    }
   
    @Override
   public GrafInputDialog createInputDialog(GrafStage gs){
       GrafInputDialog gfd = new GrafInputDialog(gs);
       gfd.setTitle("ZEROS");
       gfd.setPointPanel(gfd.addPointPanel());
       setupZeros(gfd);
       gfd.getPointPanel().setX1(gs.getGrafSettings().getXMin());
       gfd.getPointPanel().setX2(gs.getGrafSettings().getXMax());
       gfd.getPointPanel().initFx();
       gfd.setDeleter(gfd.addDeleterPanel(GrafType.FZERO)); 
       gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.FZERO)));
       gfd.setMarkChooser(gfd.addMarkPanel(new ColorRadioMarkPanel(false)));
       gfd.getCreateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
                saveZero(gs,gfd);
                gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.FZERO)));       
            }
        });
        gfd.getSaveChanges().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gfd.setFinalSave(true);
                saveZero(gs,gfd);
                gs.setGrafList(gfd.getTempList());
                gfd.dispose();
            }
        });
        GrafObject.closeGFD(gfd);
         
        return gfd;
   }
   
   
    

     @Override
     public void setDeleteValues(int index, GrafInputDialog caller, ArrayList<GrafObject> tempList ){
                   GrafZeros zEdit = (GrafZeros)tempList.get(caller.getDeleter().getPlotIndex().get(index));
                     caller.getPointChooser().setF(zEdit.getFunctionString());
                     caller.getPointChooser().setX1(zEdit.getStartX());
                     caller.getPointChooser().setX2(zEdit.getEndX());
                     caller.getPointChooser().setDx(zEdit.getDx());
                     caller.getMarkChooser().setColor(zEdit.getGrafColor());
                  
                    
       }
    
   public void setStartX(double xval){ startX = xval; }
   public double getStartX() { return startX; } 
   public void setEndX(double xval){ endX = xval; }
   public double getEndX() { return endX; } 
   public void setFunctionString(String fString){ functionString = fString; }
   public String getFunctionString() { return functionString; } 
   public void setMark(String m){mark = m;}
   public String getMark(){return mark;}
   public double getDx(){return dx;}
   public void setDx(int nVal){dx = nVal;}
   public ArrayList<Double> getZeroList(){
       return zeroList;
   }
   
          
   private static void saveZero(GrafStage gs, GrafInputDialog gfd){
       if (gfd.getFinalSave() == true && gfd.getPointPanel().getF().equals("")) return; 
       addZeros(gs, gfd);
       gfd.getPointPanel().blankF();
       gfd.getPointPanel().blankX1();
       gfd.getPointPanel().blankX2();
    
    }
    
    private static void addZeros(GrafStage gs, GrafInputDialog gfd){
        if (!FunctionString.isValidAtXIgnoreDomainError(gfd.getPointPanel().getF(), (gs.getGrafSettings().getXMax()+gs.getGrafSettings().getXMin())/2)) { 
                   JOptionPane.showMessageDialog(null,
                   "The expression entered is not a valid function.",
                   "Function Format Error",
                   JOptionPane.ERROR_MESSAGE);  
                   return;
        }
        if (Double.isNaN(gfd.getPointPanel().getX1())){gfd.NumErrorMessage("x1", "valid number"); return;}
        if (Double.isNaN(gfd.getPointPanel().getX2())){gfd.NumErrorMessage("x2", "valid number"); return;}
        if (gfd.getPointPanel().getDx()== Double.NaN){ gfd.NumErrorMessage("n", "integer"); return;}
        GrafZeros gint = new GrafZeros(gs, gfd.getPointPanel().getF(), gfd.getPointPanel().getX1(), gfd.getPointPanel().getX2(), gfd.getPointPanel().getDx(),gfd.getMarkChooser().getColor(), gfd.getMarkChooser().getMark());
        gfd.getTempList().add(gint);
    }
    
     private static void setupZeros(GrafInputDialog gfd){
        PointPanel pointPanel = gfd.getPointPanel();
        pointPanel.setupFunctionChooser();
        pointPanel.getX1Label().setText("Start x:");
        pointPanel.getX2Label().setText("End x:");
        pointPanel.getNLabel().setText("dx:");
        pointPanel.setDx(.01);
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
    }
    
   private void findZeroPoints(){
        //if (!checkInputValues()) return;
        //double leftValue = Double.parseDouble(textFieldLeft.getText());
        //double right =  Double.parseDouble(textFieldRight.getText());
        //double dx = Double.parseDouble(textFieldDx.getText());
        double f1, f2;
        double currentRoot;
        zeroString = "";
        //String functionStr = functionLabel.getText();
        for (double left = startX; left < endX; left=left+dx){
            try {
                f1 =  FunctionString.fValue(functionString, left);
            } catch (DomainViolationException e) {
                continue;
            }catch (FunctionFormatException e) {continue;}   
            try {
                f2 =  FunctionString.fValue(functionString, left+dx);
            } catch (DomainViolationException e) {
                continue;
            }catch (FunctionFormatException e) {continue;}   
            if (f1 == 0) { 
                currentRoot = left;
                zeroList.add(currentRoot);
                myOwner.setMessage1("Zero located at: "+currentRoot);
            }
            if (oppositeSigns(f1,f2)) {
                currentRoot = getCloseToRoot(functionString, left, left+dx);
                zeroList.add(currentRoot);
               myOwner.setMessage2("Zero located at: "+currentRoot);
               zeroString = zeroString+" "+currentRoot;
            }
        }
                
    }
   
   private boolean oppositeSigns(double f1, double f2){
        if ((f1>0) && (f2<0)) return true;
        if ((f1<0) && (f2>0)) return true;
        return false;
    }
    
    private double getCloseToRoot(String funct, double rootX1, double rootX2){
        double rootXK;
        double difference;
        do{
            rootXK = (rootX1 + rootX2)/2;
            try {
                if (!oppositeSigns(FunctionString.fValue(funct, rootX1), FunctionString.fValue(funct,  rootXK)))
                    rootX1 = rootXK; else rootX2 = rootXK;
            } catch (DomainViolationException e) {
                //need to do something here!
            } catch (FunctionFormatException e) {}   
                difference = Math.abs(rootX2 - rootX1);
                if (difference < 1E10) return rootX1;
        }while(true);
    }
    
    public String toString(){
       return "FZEROS: "+getFunctionString()+" "+zeroString;//+" "+getGrafColor();
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
    