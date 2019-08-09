  
/**
 * for GrafProg Project
 * Dialog to choose mark for points in graph
 * 
 * @Bill Gillam 
 * @4/24/18
 */
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JColorChooser;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextField;


abstract public class MarkPanel extends JPanel
{
   
    protected ColorChooserPanel colorPanel; //= new ColorChooserPanel();
    protected FontChooserPanel fontPanel;
    protected RadioButtonPanel radioPanel;
    protected JTextField textString = new JTextField();
       
    
    public Color getGrafColor(){
        return colorPanel.getColor();
    }
    
     public Color getColor(){
        return colorPanel.getColor();
    }
    
    public void setColor(Color c){
        colorPanel.setColor(c);
    }
    
    public Color getFillColor(){
        return colorPanel.getFillColor();
    }
    
    public void setFillColor(Color c){
        colorPanel.setFillColor(c);
    }
    
    public Font getGrafFont(){
         return fontPanel.getTextFont();
    }
    
    public void setGrafFont(Font f){
        fontPanel.updateFont(f);
    }
    
    public void setTextString(String s){
        radioPanel.setTextString(s);
    }
    
    
    public void setMark(String s){
        radioPanel.setMark(s);
    }
    
    public boolean xMark(){
        return radioPanel.xMark();
    }
    
    public boolean oMark(){
        return radioPanel.oMark();
    }
    
    public boolean periodMark(){
        return radioPanel.periodMark();
    }
    
    public String getMark(){
        return radioPanel.getMark();
    }
    
    public String getTextMark(){
        return radioPanel.getTextMark();
    }
    
    public void setConnectedChecked(boolean tf){ 
        radioPanel.setConnectedChecked(tf);
    }
        
    public void setFillChecked(boolean tf){
        colorPanel.setFillChecked(tf);
    }
    
    public boolean fillChecked(){
        return colorPanel.fillChecked();
    }
    
    public boolean isLineGraph(){
        return radioPanel.isLineGraph();
    }
    
    public boolean FNSChecked(){
        return radioPanel.getFNSChecked();
    }
   
  }