
/**
 * Fontchooser dialog holder
 *
 * @author Bill Gillam  
 * @version 4/30/18
 */
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.JFrame;
import java.awt.BorderLayout;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FontChooserPanel extends JPanel
{
    private JTextField fontDisplayString = new JTextField();
    private JButton fontButton = new JButton("Font");
    
   
    
   public static void main(String[] args){
        FontChooserPanel fcp = new FontChooserPanel(UIManager.getDefaults().getFont("TabbedPane.font"));
        
        JFrame jf = new JFrame("test");
        jf.setVisible(true);
        jf.add(fcp);
        jf.pack();
    } 
    
    
   /**
   * Constructor for objects of class FontChooserPanel
   */
    public FontChooserPanel(Font f) 
    {
        fontDisplayString.setColumns(15);
        fontDisplayString.setText(f.getName());
        fontButton.setText("Font");
        updateFont(f);
        fontButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                FontDialog fd = new FontDialog(f);
                updateFont(fd.showFontDialog());
                
            }
        });
        setLayout(new BorderLayout());
        add(fontButton, BorderLayout.WEST);
        add(fontDisplayString, BorderLayout.EAST);
        
  }
       
  public FontChooserPanel(){
      this(new Font("Dialog",Font.PLAIN, 12));
      
  }
  
  public void updateFont(Font f){
        fontDisplayString.setFont(f);
        fontDisplayString.setText(f.getName());
        
  }
  
  public Font getTextFont(){
         return fontDisplayString.getFont();
  }
  
  
   

}

 