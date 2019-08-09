  
/**
 * Special mmark panel for GrafText objects
 * 
 * @4/24/18
 */
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TextMarkPanel extends MarkPanel
{
       
    //private ColorChooserPanel colorPanel; 
    //private FontChooserPanel fontPanel;
    //private JTextField textString;
    
    
    //tester    
    public static void main(String[] args){
        TextMarkPanel m = new TextMarkPanel();//UIManager.getDefaults().getFont("TabbedPane.font"), true, true, true, true, true, true, false);
        m.setVisible(true);
        JFrame jf = new JFrame("test");
        jf.setVisible(true);
        
        jf.add(m);
        jf.pack();
        //m.setModal();
    }
    
    
    public TextMarkPanel()
    {
            setBackground(new Color(220, 220, 220));
            setLayout(new BorderLayout());
            colorPanel = new ColorChooserPanel(false);
            add(colorPanel, BorderLayout.SOUTH);
            textString = new JTextField();
            JPanel fontTextPanel = new JPanel();
            fontPanel = new FontChooserPanel(this.getFont());
            textString.setColumns(15);
            textString.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                       textString.setFont(fontPanel.getTextFont());
                       textString.setForeground(colorPanel.getColor());
                }
            });
            fontTextPanel.setLayout(new BorderLayout());
            fontTextPanel.add(fontPanel, BorderLayout.SOUTH);
            fontTextPanel.add(new JLabel("Text:"), BorderLayout.WEST);
            fontTextPanel.add(textString, BorderLayout.EAST);
            add(fontTextPanel, BorderLayout.NORTH);

    }
    
    public Color getColor(){
        return colorPanel.getColor();
    }
    
        
    public Font getTextFont(){
        return fontPanel.getTextFont();
    }
    
      
    public String getText(){
        return textString.getText();
    }
    
    public String getTextMark(){
        return textString.getText();
    }
    
    
    
    
    
    
   
  }