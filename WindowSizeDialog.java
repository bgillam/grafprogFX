
/**************************************** 
*  WindowSizeDialog  for GrafProg Project
*  sets size of graphing window
*  @author Bill Gillam                  *
*  2/25/15                              *
*****************************************/
/**
* Dialog for setting graphing window parameters
 */

import java.awt.BorderLayout;
import java.io.*;
//import java.awt.Dimension;
//import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
//import javax.swing.JSeparator;
//import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;


//class header
public class WindowSizeDialog extends JDialog implements ActionListener  //, Serializable
{   
    //instance variables
    private GrafSettings.ScaleFormat myScaleFormat;
    private GrafSettings.ScaleProcedure myScaleProcedure;
    private JButton okButton = new JButton("OK");
    private JButton cancelButton = new JButton("Cancel");
    private JPanel buttonPanel = new JPanel();
    private JPanel minMaxPanel1 = new JPanel();
    private JTextField xMinInput = new JTextField();
    private JTextField xMaxInput = new JTextField();
    private JTextField yMinInput = new JTextField();
    private JTextField yMaxInput = new JTextField();
    private JTextField xScaleInput = new JTextField();
    private JTextField yScaleInput = new JTextField();
    private JLabel xMinLabel = new JLabel("xMin");
    private JLabel xMaxLabel = new JLabel("xMax");
    private JLabel yMinLabel = new JLabel("yMin");
    private JLabel yMaxLabel = new JLabel("yMax");
    private JPanel minMaxPanel = new JPanel();
    private JPanel xMinPanel = new JPanel();
    private JPanel xMaxPanel = new JPanel();
    private JPanel yMinPanel = new JPanel();
    private JPanel yMaxPanel = new JPanel();
    private JPanel xScalePanel = new JPanel();
    private JPanel yScalePanel = new JPanel();
    private JPanel midPanel = new JPanel();
    private JRadioButton tenx = new JRadioButton("10^x");
    private JRadioButton fromRange = new JRadioButton("Compute from Range");
    private JRadioButton setScale   = new JRadioButton("Input Scales");
    private JLabel xScaleLabel = new JLabel("x scale:");
    private JLabel yScaleLabel = new JLabel("y scale:");
    private ButtonGroup scaleButtonGroup =  new ButtonGroup();
    private ButtonGroup formatButtonGroup = new ButtonGroup();
    private JPanel scalePanel = new JPanel();
    private JPanel scalePanel1 = new JPanel();
    private JLabel scaleFormatLabel = new JLabel("Scale Format:");
    private JLabel setScaleLabel = new JLabel("Set Scales:");
    private JLabel setBoundriesLabel = new JLabel("Set Window Boundries:");
    private JPanel messagePanel = new JPanel();
    private JRadioButton dec = new JRadioButton("Decimal Places:");
    private JRadioButton exp = new JRadioButton("Exponential form                            ");
    private JTextField decPlaces = new JTextField();
    private JPanel decPanel = new JPanel();
    private GrafSettings grafSet;      
    private GrafPrimitives grafPrim;
    private final JPanel panel = new JPanel();
     
    //constructor 
    public WindowSizeDialog()
    {   
        //super(sess, "Window Parameters",true);
        setTitle("Window Parameters");
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        grafSet = GrafProg.getGrafSettings();    //pointer to graph information
        myScaleFormat = grafSet.getScaleFormat();
        myScaleProcedure = grafSet.getScaleProcedure();
        setLocationRelativeTo(GrafProg.getGrafPanel());
        
        //Set up Yes/No button panel at bottem of Dialog
        buttonPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));        
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
               
        //add components to allow editing of min/max values
        //Set Layouts
        minMaxPanel.add(setBoundriesLabel); 
        minMaxPanel1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        minMaxPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        minMaxPanel1.setLayout(new BoxLayout(minMaxPanel1, BoxLayout.Y_AXIS)); 
        minMaxPanel.setLayout(new BoxLayout(minMaxPanel, BoxLayout.Y_AXIS));
        //get min/max values
        xMinInput.setText(Double.toString(grafSet.getXMin()));
        xMaxInput.setText(Double.toString(grafSet.getXMax()));
        yMinInput.setText(Double.toString(grafSet.getYMin()));
        yMaxInput.setText(Double.toString(grafSet.getYMax()));
        //add Labels and TextStrings to panel                
        xMinPanel.add(xMinLabel);
        xMinPanel.add(xMinInput);
        minMaxPanel1.add(xMinPanel);
        xMaxPanel.add(xMaxLabel);
        xMaxPanel.add(xMaxInput);
        minMaxPanel1.add(xMaxPanel);
        yMinPanel.add(yMinLabel);
        yMinPanel.add(yMinInput);
        minMaxPanel1.add(yMinPanel);
        yMaxPanel.add(yMaxLabel);
        yMaxPanel.add(yMaxInput);
        minMaxPanel1.add(yMaxPanel);
        minMaxPanel.add(minMaxPanel1);   
        
        
        //Create Panel for Scale Computation Options
        //set up panel layout
        scalePanel.setBorder(null);
        scalePanel.setLayout(new BoxLayout(scalePanel,BoxLayout.Y_AXIS));
        scalePanel1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        scalePanel1.setLayout(new BoxLayout(scalePanel1 ,BoxLayout.Y_AXIS));
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        scalePanel.add(setScaleLabel);
        
        //Put buttons in group
        scaleButtonGroup.add(tenx);
        scaleButtonGroup.add(fromRange);
        scaleButtonGroup.add(setScale);
        //put buttons in panel
        scalePanel1.add(tenx);
        scalePanel1.add(fromRange);
        scalePanel1.add(setScale);
        //Scale inputs. Get initial values
        xScaleInput.setText(Double.toString(grafSet.getXAxisScale()));
        yScaleInput.setText(Double.toString(grafSet.getYAxisScale()));
        //put labels and inputs in panel
        xScalePanel.add(xScaleLabel);
        xScalePanel.add(xScaleInput);
        yScalePanel.add(yScaleLabel);
        yScalePanel.add(yScaleInput);
        scalePanel1.add(xScalePanel);
        scalePanel1.add(yScalePanel);
        scalePanel.add(scalePanel1);
        scalePanel.add(panel);
        scalePanel.add(scaleFormatLabel);
        xScaleLabel.setVisible(false);
        yScaleLabel.setVisible(false);
        xScaleInput.setVisible(false);
        yScaleInput.setVisible(false);
        
        //create scale format radio button group    
        formatButtonGroup.add(dec);
        if (myScaleFormat.equals(GrafSettings.ScaleFormat.DEC)) dec.setSelected(true); else exp.setSelected(true);
        decPlaces.setText(Integer.toString(grafSet.getDecPlaces()));
        decPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        decPanel.setLayout(new BorderLayout(0, 0));
        decPanel.add(dec, BorderLayout.WEST);
        decPanel.add(decPlaces, BorderLayout.CENTER);
        scalePanel.add(decPanel);
        decPanel.add(exp, BorderLayout.NORTH);
        formatButtonGroup.add(exp);
        //read initial values and act appropriately
        switch (grafSet.getScaleProcedure()){
            case TEN_POWER : tenx.setSelected(true); break;
            case FROM_RANGE : fromRange.setSelected(true); break;
            case SET_SCALES : showScaleInput();  setScale.setSelected(true);  break; 
        }   
     
        //put the panels in the frame
        getContentPane().add(messagePanel, BorderLayout.NORTH);
        getContentPane().add(minMaxPanel, BorderLayout.WEST);
        getContentPane().add(midPanel,BorderLayout.CENTER);
        getContentPane().add(scalePanel, BorderLayout.EAST);
        getContentPane().add(buttonPanel,BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        
        
        //actionListeners
        tenx.addActionListener(this);
        fromRange.addActionListener(this);
        setScale.addActionListener(this);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }
    
    public static void createInputDialog(){
       WindowSizeDialog tempDialog = new WindowSizeDialog();
       tempDialog.showWindowSizeDialog(); 
       
       //gs.repaint();
    
    }
    
  //Show dialog and wait for OK, Cancel or X  
  public void showWindowSizeDialog(){
      setVisible(true);
      setModal(true);
      
    }

  //respond to ActionListeners
  public void actionPerformed(ActionEvent event) {
    if (event.getActionCommand().equals("OK")) {OKStuff(); }   
    if (event.getActionCommand().equals("Cancel")){ setVisible(false); dispose(); }
    if (event.getActionCommand().equals("10^x")){ myScaleProcedure = GrafSettings.ScaleProcedure.TEN_POWER;}
    if (event.getActionCommand().equals("Compute from Range")){ myScaleProcedure=GrafSettings.ScaleProcedure.FROM_RANGE;}
    if (event.getActionCommand().equals("Input Scales")) { showScaleInput(); myScaleProcedure = GrafSettings.ScaleProcedure.SET_SCALES;}
    if (event.getActionCommand().equals("Decimal Places:"))myScaleFormat= GrafSettings.ScaleFormat.DEC; 
    if (event.getActionCommand().equals("Exponential form                            ")) myScaleFormat=GrafSettings.ScaleFormat.EXP;
    
  }
    
  private void OKStuff(){
        if (saveValues()){ 
              setVisible(false); 
              dispose();
              GrafProg.getGrafPanel().repaint();
        }
//    }
//    else JOptionPane.showMessageDialog(null , "Range Entered is Invalid.", "ERROR!", JOptionPane.WARNING_MESSAGE);
 }
  
  //Save everything to GrafSettings record
  private boolean saveValues(){
      try{
        if (Double.parseDouble(xMinInput.getText()) > Double.parseDouble(xMaxInput.getText())){throw new NumberFormatException(); } 
        if (Double.parseDouble(yMinInput.getText()) > Double.parseDouble(yMaxInput.getText())){throw new NumberFormatException(); }   
        grafSet.setXMin(Double.valueOf(xMinInput.getText()));
        grafSet.setXMax(Double.valueOf(xMaxInput.getText()));
        grafSet.setYMin(Double.valueOf(yMinInput.getText()));
        grafSet.setYMax(Double.valueOf(yMaxInput.getText()));
        
      }
      catch (NumberFormatException nfe){
          JOptionPane.showMessageDialog(null , "At least one of the min or max values entered is invalid.", "ERROR!", JOptionPane.WARNING_MESSAGE);
          return false;
      }
      grafSet.setScaleProcedure(myScaleProcedure);
      grafSet.setScaleFormat(myScaleFormat);
        
        //error between here
      if (myScaleFormat.equals(GrafSettings.ScaleFormat.DEC)) {
         try{
           grafSet.setDecPlaces(Integer.parseUnsignedInt(decPlaces.getText()));
           grafSet.setScaleFormat(GrafSettings.ScaleFormat.DEC);
         }
         catch (NumberFormatException nfe) {
           JOptionPane.showMessageDialog(null , "Number of decimal places is invalid.", "ERROR!", JOptionPane.WARNING_MESSAGE);
           return false;
         }
      }
      else grafSet.setScaleFormat(GrafSettings.ScaleFormat.EXP);
      
      switch (grafSet.getScaleProcedure()){
            case TEN_POWER : 
                grafSet.setScaleProcedure(GrafSettings.ScaleProcedure.TEN_POWER);
                setScale10();
                break;
            case FROM_RANGE : 
                grafSet.setScaleProcedure(GrafSettings.ScaleProcedure.FROM_RANGE);
                computeFromRange();
                break;
            case SET_SCALES :
                try{                    
                    if (Double.parseDouble(xScaleInput.getText()) <= 0) {throw new NumberFormatException();}
                    if (Double.parseDouble(yScaleInput.getText()) <= 0) {throw new NumberFormatException();}
                    grafSet.setXAxisScale(Double.parseDouble(xScaleInput.getText())); 
                    grafSet.setYAxisScale(Double.parseDouble(yScaleInput.getText()));
                    grafSet.setScaleProcedure(GrafSettings.ScaleProcedure.SET_SCALES);
                    break;
                }
                catch (NumberFormatException nfe){
                    JOptionPane.showMessageDialog(null , "At least one of the axis scales entered is invalid.", "ERROR!", JOptionPane.WARNING_MESSAGE);
                    return false;
                }       
        } 
        
        setVisible(false); 
        dispose();
        return true;
      
    }
   
    
  //set scales to increments of a power of 10
  private void setScale10(){
      hideScaleInputs();
      Scales.scalesPowersOfTen(grafSet); 
      //System.out.println("xmin:"+grafSet.getXMin()+" xmax:"+grafSet.getXMax()+" width:"+grafSet.getPanelWidth());
      //System.out.println("xmin:"+grafSet.getYMin()+" xmax:"+grafSet.getYMax()+" width:"+grafSet.getPanelHeight());    
         
  }
  
  //Compute  scales from the visible window
  private void computeFromRange(){
      hideScaleInputs();
      Scales.scalesFromRange(grafSet);
      }
  
  //does what it says        
  private void hideScaleInputs(){
      xScaleLabel.setVisible(false); 
      yScaleLabel.setVisible(false); 
      xScaleInput.setVisible(false);
      yScaleInput.setVisible(false);
      pack();
 }
  
  //does what it says
  private void showScaleInput(){
      xScaleLabel.setVisible(true); 
      yScaleLabel.setVisible(true); 
      xScaleInput.setVisible(true);
      yScaleInput.setVisible(true);
      pack();
 }
  
}

  
