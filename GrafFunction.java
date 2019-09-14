/********************************* 
*  GrafFunction for GrafProg Project *
*  Object for graphing a function
*  @author Bill Gillam           *
*  2/25/15                       *
**********************************/
import javafx.stage.Stage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import javax.swing.border.*;
import javax.swing.UIManager;
import java.util.ArrayList;

//Class Header
public class GrafFunction extends GrafObject implements IGrafable{
    //Instance Variables
    private String functionString;
    private int segLength = 200;
    private GrafProg myOwner;
    private GrafSettings gStuff;
        
    //Constructor
    
    /*public GrafFunction(){
        setGrafType(GrafType.FUNCTION);
        setMoveable(false);
        setGrafColor(Color.BLACK);
       
        
    }*/
    
    public GrafFunction(){
        setGrafType(GrafType.FUNCTION);
        setMoveable(false);
        setGrafColor(Color.BLACK);
        //myOwner = sess;
        gStuff = GrafProg.getGrafSettings();
        //GrafProg.setMessage1(functionString);
    }
    
    //constructor 
    public GrafFunction(String fString){
        this();
        setFunction(fString);
        GrafProg.setMessage1(functionString);
    }
    
    public GrafFunction(String fString, Color c){
        this(fString);
        setGrafColor(c);
       
   }
   

    //drawGraf overri[]ding method in parent GrafObject
    @Override
    public void drawGraf(Graphics2D gc){
        double xMin = gStuff.getXMin();
        double xMax = gStuff.getXMax();
        double  dx = (xMax - xMin)/segLength;
        double x = xMin;
        gc.setColor(super.getGrafColor());
        while (x < xMax){
        
            try {
                GrafPrimitives.grafLine(gStuff,x, FunctionString.fValue(functionString, x), x + dx , FunctionString.fValue(functionString, x+dx), gc);
            } catch (DomainViolationException e) {  }
            catch (FunctionFormatException e) {}   
            x = x+dx;
         }
        try {
            GrafPrimitives.grafLine(gStuff,x-dx, FunctionString.fValue(functionString, x), xMax , FunctionString.fValue(functionString, x+dx), gc);
        } catch (DomainViolationException e) { }
        catch (FunctionFormatException e) {}   
        gc.setColor(Color.BLACK);
        
    }
    
 /*  @Override
   public GrafInputDialog createInputDialog(GrafStage gs){
        GrafInputDialog gfd = new GrafInputDialog(new GrafProg());
        gfd.setTitle("FUNCTION");
        gfd.setPointPanel(gfd.addPointPanel());
        
        gfd.getPointPanel().addFx();
        gfd.setMarkChooser(gfd.addMarkPanel(new FillColorMarkPanel(false, false))); 
        gfd.setDeleter(gfd.addDeleterPanel(GrafType.FUNCTION));   
        gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.FUNCTION)));      
        gfd.getCreateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
                saveFunction(gs,gfd);
                gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.FUNCTION)));    
            }
        });
        gfd.getSaveChanges().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gfd.setFinalSave(true);
                saveFunction(gs,gfd);
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
   
      
   

    

    /* @Override
     public void setDeleteValues(int index, GrafInputDialog caller, ArrayList<GrafObject> tempList ){
                   GrafFunction gEdit = (GrafFunction)tempList.get(caller.getDeleter().getPlotIndex().get(index));
                   caller.getPointChooser().setF(gEdit.getFunction());
                   caller.getMarkChooser().setColor(gEdit.getGrafColor());
                  
                    
       }*/
    
    //Setters and Getters
    public void setFunction(String s){ functionString = s;}
    public String getFunction(){ return functionString;}
    
    public void setSegLength(int sl){segLength = sl;}
    public int getSegLength(){return segLength;}
    
    public String toString(){
        return "FUNCTION: "+ getFunction();//+", "+ getGrafColor();
    }

    /*public static void saveFunction(){
        GrafDialogController gdc = GrafProg.getDialogController();
        if (gdc.isFinalSave() == true && gdc.getFunctionString().equals("")) return;
        addFunction(gdc);
        gdc.setFunctionString("");
    }*/


    private static void addFunction(GrafDialogController gdc){
        
        if (!FunctionString.isValidAtXIgnoreDomainError(gdc.getFunctionString(), (GrafProg.getGrafSettings().getXMax()+GrafProg.getGrafSettings().getXMin())/2)) {
                   JOptionPane.showMessageDialog(null,
                   "The expression entered is not a valid function.",
                   "Function Format Error",
                   JOptionPane.ERROR_MESSAGE);  
                   return;
        }
       
        GrafFunction gf = new GrafFunction(gdc.getFunctionString(), gdc.getGrafColor());
        gdc.getTempGrafList().add(gf);
        System.out.println("Function added");
    }
    
    /* Setters and Getters from Parent GrafObject
     *  public void drawGraf(Graphics2D g2D){};
   
        public void setGrafType(GrafProg.GrafType gt){grafType = gt;}
        public GrafProg.GrafType getType(){return grafType; }
   
        public boolean isMoveable(){ return moveable; } 
        public void setMoveable(boolean tf){ moveable = tf;  }
        public boolean getMoveable(){return moveable;}
   
        public void setOwner(GrafProg owner){myOwner = owner;}
        public GrafProg getOwner(){return myOwner;}
   
        public void setGrafColor(Color c){grafColor = Color.BLACK;   }
        public Color getGrafColor() { return grafColor;}
     */
    
}
