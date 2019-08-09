
/**
 * Panel in a dialog to choose color
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;

public class ColorChooserPanel extends JPanel
{
    private JButton colorButton = new JButton("Color");
    private JButton fillColorButton = new JButton("Fill Color");
    private JCheckBox fillCheckBox = new JCheckBox("Fill Shape");
    //private JPanel colorPanel = new JPanel();
    /*
     * Constructor for objects of class ColorChooserPanel
     */
    
    public static void main(String[] args){
        ColorChooserPanel ccp = new ColorChooserPanel();
        JFrame jf = new JFrame("test");
        jf.setVisible(true);
        jf.add(ccp);
        jf.pack();
    }
    
    
    
    public ColorChooserPanel()
    {
        //colorPanel.
        setBackground(UIManager.getColor("Button.background"));
        //colorPanel.
        setLayout(new BorderLayout());
        addColorButton();
        addFillButton();
        fillColorButton.setVisible(false);
        addFillCheckBox();
        setVisible(true);
    }
    
    public ColorChooserPanel(boolean fill){  
        this();
        if (!fill) fillCheckBox.setVisible(false);
        
    }
    
    
    
    
    private void addFillCheckBox(){
    fillCheckBox = new JCheckBox("Fill"); 
             fillCheckBox.addItemListener(new ItemListener() {
                 public void itemStateChanged(ItemEvent event) {
                        if (event.getStateChange() == ItemEvent.SELECTED)
                            showFillColorButton(); 
                        else hideFillColorButton();
                                           
                 }
             });
             add(fillCheckBox, BorderLayout.WEST);
    }
        
    
      
    private void addColorButton(){ 
    colorButton.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent arg0) {
                        ColorDialog cd = new ColorDialog();
                        colorButton.setForeground(cd.showDialog());
                }
             });
             //colorPanel.
             add(colorButton, BorderLayout.SOUTH);
             colorButton.setVisible(true);
   }
    
    private void addFillButton(){
      fillColorButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
              ColorDialog cd = new ColorDialog();
              fillColorButton.setForeground(cd.showDialog());
         }
      });
     //colorPanel.
     add(fillColorButton, BorderLayout.EAST);
     fillColorButton.setVisible(true);
   }

    public void showColorButton(){
        colorButton.setVisible(true);
    }
    
    public void hideColorButton(){
        colorButton.setVisible(false);
    }
    
    public void showFillColorButton(){
        fillColorButton.setVisible(true);
    }
    
    public void hideFillColorButton(){
        fillColorButton.setVisible(false);
    }
    
    //setters and getters
    public void setFillChecked(boolean tf){
       fillCheckBox.setSelected(tf);
    }
    
    public boolean fillChecked(){
        return fillCheckBox.isSelected();
    }
    
    public void hideFillCheckBox(){
        fillCheckBox.setVisible(false);
        fillCheckBox.setSelected(false);
    }
    
    public void showFillCheckBox(){
        fillCheckBox.setVisible(true);
    }
    
    public Color getColor(){
        return colorButton.getForeground();
    }
    
    public void setColor(Color c){
        colorButton.setForeground(c);
    }
    
    public Color getFillColor(){
        return fillColorButton.getForeground();
    }
    
    public void setFillColor(Color c){
        fillColorButton.setForeground(c);
    }
}
