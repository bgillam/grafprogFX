  
/**
 * Panel in a dialog to set fill color for shapes and other GrafObjects
 * 
 * @Bill Gillam 
 * @4/24/18
 */
 
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import javax.swing.JFrame;
import java.awt.BorderLayout;



public class ColorRadioMarkPanel extends MarkPanel
{
       
    private JPanel buttonPanel = new JPanel();
    private JPanel buttonPanelLeft = new JPanel();
    private JPanel buttonPanelRight = new JPanel();
    private JPanel buttonPanelSouth = new JPanel();
    //private ColorChooserPanel colorPanel; //= new ColorChooserPanel();
    //private RadioButtonPanel radioPanel;
    
    /**
     * Constructor for objects of class ColorChooserPanel
     */
    
    public static void main(String[] args){
        ColorRadioMarkPanel m = new ColorRadioMarkPanel();//UIManager.getDefaults().getFont("TabbedPane.font"), true, true, true, true, true, true, false);
        m.setVisible(true);
        JFrame jf = new JFrame("test");
        jf.setVisible(true);
        
        jf.add(m);
        jf.pack();
        //m.setModal();
    }
    
    
    public ColorRadioMarkPanel(boolean connectedCheckBox)
    {
            setBackground(new Color(220, 220, 220));
            setLayout(new BorderLayout());
            
            buttonPanel.setBackground(UIManager.getColor("Button.background"));
            buttonPanel.setLayout(new BorderLayout());
            
            buttonPanelLeft.setBackground(UIManager.getColor("Button.background"));
            buttonPanelLeft.setLayout(new BorderLayout());
            
            buttonPanelRight.setBackground(UIManager.getColor("Button.background"));
            buttonPanelRight.setLayout(new BorderLayout());
            
            colorPanel = new ColorChooserPanel(false);
            
            if (connectedCheckBox) radioPanel = new RadioButtonPanel(true); 
            else radioPanel = new RadioButtonPanel();
            
            add(radioPanel, BorderLayout.NORTH);
            add(colorPanel, BorderLayout.SOUTH);
            
    }
    
    public ColorRadioMarkPanel(){
            this(false);
            
    }
    
           
    public String getMark(){
        return radioPanel.getMark();
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
    
    public Color getColor(){
        return colorPanel.getColor();
    }
    
    public void setColor(Color c){
        colorPanel.setColor(c);
    }
    
    public boolean connected(){
        return radioPanel.getConnectedChecked();
    }
  }