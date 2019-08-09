
/**
 * Panel in a dialog to set fill color for shapes and other GrafObjects
 * 
 * @Bill Gillam 
 * @4/24/18
 */
import javax.swing.JPanel;


import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import javax.swing.JFrame;
import java.awt.BorderLayout;



public class FillColorMarkPanel extends MarkPanel
{
       
    
    //private ColorChooserPanel colorPanel; //= new ColorChooserPanel();
    private JCheckBox fnsCheckBox;
    
    
    /**
     * Constructor for objects of class ColorChooserPanel
     */
    
    public static void main(String[] args){
        FillColorMarkPanel m = new FillColorMarkPanel(true,true);//UIManager.getDefaults().getFont("TabbedPane.font"), true, true, true, true, true, true, false);
        m.setVisible(true);
        JFrame jf = new JFrame("test");
        jf.setVisible(true);
        
        jf.add(m);
        jf.pack();
        //m.setModal();
    }
    
    
    public FillColorMarkPanel(boolean fill, boolean fns)
    {
            setBackground(new Color(220, 220, 220));
            setLayout(new BorderLayout());
            if (fill) colorPanel = new ColorChooserPanel(); else colorPanel = new ColorChooserPanel(false);
            fnsCheckBox = new JCheckBox("FNS");
            if (fns)  add(fnsCheckBox, BorderLayout.NORTH);
            add(colorPanel, BorderLayout.SOUTH);
            
    }
    
    public FillColorMarkPanel(){
        this(false, false);
   
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
    
    public boolean FNSChecked(){
        return fnsCheckBox.isSelected();
    }
   
  }