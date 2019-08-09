
/**
 * RadioButton Panel for choosing marks for graphs
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.JFrame;
import java.awt.BorderLayout;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class RadioButtonPanel extends JPanel
{
    private JPanel buttonPanelLeft = new JPanel();
    private JPanel buttonPanelRight = new JPanel();
    private JPanel checkPanel = new JPanel();
    
    private JRadioButton xRadioButton = new JRadioButton("x");
    private JRadioButton oRadioButton = new JRadioButton("o");
    private JRadioButton periodRadioButton = new JRadioButton(".");;
    private JRadioButton rdButtonChar = new JRadioButton("char");
    private JTextField txtString = new JTextField();
    private JLabel lblMark = new JLabel("Mark:");
    private JCheckBox connectedCheckBox = new JCheckBox("Connected");
    private JCheckBox fnsCheckBox = new JCheckBox("show FNS");
    
   public static void main(String[] args){
        RadioButtonPanel rbp = new RadioButtonPanel();
        RadioButtonPanel rbp2 = new RadioButtonPanel(true);
        
        
        
        JFrame jf = new JFrame("test");
        jf.setVisible(true);
        jf.add(rbp);
        //jf.add(rbp2);
        //jf.add(rbp3);
        jf.pack();
       
    } 
    
    
   /**
   * Constructor for objects of class FontChooserPanel
   */
  public RadioButtonPanel() 
  {
       setLayout(new BorderLayout());
       buildRadioButtonPanel();
       //checkPanel.add(connectedCheckBox, BorderLayout.EAST);
       //checkPanel.add(fnsCheckBox, BorderLayout.WEST);
       
       add(buttonPanelLeft, BorderLayout.WEST);
       add(buttonPanelRight, BorderLayout.EAST);
       
  }
 
  public RadioButtonPanel(boolean connected){
       this();
       if (connected) {
           checkPanel.add(connectedCheckBox, BorderLayout.EAST);
           add(checkPanel, BorderLayout.SOUTH);
       }
  }
 
       
  
 
 private void buildRadioButtonPanel(){
            buttonPanelLeft.add(lblMark, BorderLayout.WEST);
            addRadioButton(periodRadioButton, buttonPanelLeft, BorderLayout.CENTER);
            addRadioButton(xRadioButton, buttonPanelLeft, BorderLayout.EAST);
            addRadioButton(oRadioButton, buttonPanelRight, BorderLayout.WEST);
            addRadioButton(rdButtonChar, buttonPanelRight, BorderLayout.CENTER);
            buttonPanelRight.add(txtString, BorderLayout.EAST);
            txtString.setColumns(1);
            periodRadioButton.setSelected(true);
            
 }
 
 public void addRadioButton(JRadioButton rb, JPanel jp, String borderSpot){
            jp.add(rb, borderSpot); //Layout.WEST);
            rb.addItemListener(new ItemListener() {
                 public void itemStateChanged(ItemEvent event) {
                        if (event.getStateChange() == ItemEvent.SELECTED){
                            setButtons(rb);
                            
                        }
                 }
             });
             rb.setBackground(UIManager.getColor("Button.background"));
        }
        
        public void setButtons(JRadioButton rb){
             if (rb == xRadioButton) {
                 xRadioButton.setSelected(true);
                 oRadioButton.setSelected(false);
                 periodRadioButton.setSelected(false);
                 rdButtonChar.setSelected(false);
                 txtString.setEditable(false);
                }
             else if (rb == oRadioButton) {
                 xRadioButton.setSelected(false);
                 oRadioButton.setSelected(true);
                 periodRadioButton.setSelected(false);
                 rdButtonChar.setSelected(false);
                 txtString.setEditable(false);
             }
             else if(rb == periodRadioButton) {
                xRadioButton.setSelected(false);
                oRadioButton.setSelected(false);
                periodRadioButton.setSelected(true);
                rdButtonChar.setSelected(false);
                txtString.setEditable(false);
             }
             else if (rb == rdButtonChar) {
                xRadioButton.setSelected(false);
                oRadioButton.setSelected(false);
                periodRadioButton.setSelected(false);
                rdButtonChar.setSelected(true);
                txtString.setEditable(true);
            }
        }
                
      
    public boolean getConnectedChecked(){
        return connectedCheckBox.isSelected();
    }
 
    public void setConnectedChecked(boolean tf){
         connectedCheckBox.setSelected(tf);
    }
    
    
    public boolean isLineGraph(){
        return connectedCheckBox.isSelected();
    }         
            
        
  
 public void setTextString(String s){
        txtString.setText(s);
    }
   
    public boolean xMark(){
        return xRadioButton.isSelected();
    }
    
    public boolean oMark(){
        return oRadioButton.isSelected();
    }
    
    public boolean periodMark(){
        return periodRadioButton.isSelected();
    }
   
     
   public String getTextMark(){
        return txtString.getText();
    }
   
   public String getMark(){
        if (xMark()) return "x";
        else if (oMark()) return "o";
        else if (periodMark()) return ".";
        else return txtString.getText();

    }
    
    public void setMark(String s){
        
        if (s.equals("x"))  setButtons(xRadioButton); //.setSelected(true);
        else if (s.equals("o")) setButtons(oRadioButton);//.setSelected(true);
        else if (s.equals(".")) setButtons(periodRadioButton);//.setSelected(true);
        else  setButtons(rdButtonChar);//.setSelected(true); txtString.setText(s);}
    }    
    
    
        
    public boolean getFNSChecked(){
        return fnsCheckBox.isSelected();
    }
    
    public void hideFNS(){
        fnsCheckBox.setVisible(false);
        fnsCheckBox.setSelected(false);
    }
    
    public void showFNS(){
        fnsCheckBox.setVisible(true);
    }
    
    
    public void hideConnectedCheckBox(){
        connectedCheckBox.setVisible(false);
        connectedCheckBox.setSelected(false);
    }
    
    public void showConnectedCheckBox(){
        connectedCheckBox.setVisible(true);
    }
    
}

 