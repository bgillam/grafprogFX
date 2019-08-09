
/**
 * ColumnChooserPanel lets user choose one or two columns depending on GrafType.
 * After initial creation, public methods
 * @author (Bill Gillam) 
 * @version (1/1/17)
 * 
 */
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JSeparator;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

import javax.swing.JFrame;

public class ColumnChooserPanel extends JPanel
{
    private JPanel inputPanel = new JPanel();
    private JComboBox inputComboBox = new JComboBox();
    private JLabel inputMessage = new JLabel("Choose Column for Input");
  
    private JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
    
    private JPanel outputPanel = new JPanel();
    private JComboBox outputComboBox = new JComboBox();
    private JLabel outputMessage = new JLabel("Choose Column for Output");
       
    private String[] columnHeaders;
    
    public static void main(String args[]){
        String[] headerArray = {"","one","two","three","four","five","six"};
        ColumnChooserPanel ccp = new ColumnChooserPanel(headerArray);
        
        ColumnChooserPanel ccp2 = new ColumnChooserPanel(headerArray,true,false);
        JFrame jf = new JFrame("test");
        jf.setVisible(true);
        
        jf.add(ccp2);
        jf.pack();
    
    
    }
    

    /**
     * Constructor for objects of class ColumnChooserPanel
     */
    public ColumnChooserPanel(String[] columnHeadersPar)
    {
        columnHeaders = columnHeadersPar;
        setBackground(new Color(220, 220, 220));
        setLayout(new BorderLayout());
        buildInputPanel();   
        buildOutputPanel();
        add(new JSeparator(SwingConstants.VERTICAL), BorderLayout.CENTER);
        setVisible(true);
    
    }
    
    public ColumnChooserPanel(String[] columnHeaders, boolean showInputChooser, boolean showOutputChooser){
        this(columnHeaders);
        if (showInputChooser == true) turnOnInputColumn(); else turnOffInputColumn();
        if (showOutputChooser == true) turnOnOutputColumn(); else turnOffOutputColumn();
    }
    
    private void buildInputPanel(){
        inputPanel.add(inputMessage);
        inputPanel.add(inputComboBox);
        inputComboBox.setModel(new javax.swing.DefaultComboBoxModel(columnHeaders));inputComboBox.setSelectedIndex(1);
        inputComboBox.setSelectedIndex(1);
        add(inputPanel, BorderLayout.WEST);
    }
    
    private void buildOutputPanel(){
        outputPanel.add(outputMessage);
        outputPanel.add(outputComboBox);
        outputComboBox.setModel(new javax.swing.DefaultComboBoxModel(columnHeaders));
        outputComboBox.setSelectedIndex(1);
        add(outputPanel, BorderLayout.EAST);
    }
    
    //turn column choosers on or off
    private void turnOnInputColumn(){
        inputComboBox.setVisible(true);
        inputMessage.setVisible(true);
    }
    
    private void turnOffInputColumn(){
        inputComboBox.setVisible(false);
        inputMessage.setVisible(false);
    }
    
    private void turnOnOutputColumn(){
        outputComboBox.setVisible(true);
        outputMessage.setVisible(true);
    }
    
    private void turnOffOutputColumn(){
        outputComboBox.setVisible(false);
        outputMessage.setVisible(false);
    }
    
    //get or set chosen index
    
    public void setInputIndex(int index){
        inputComboBox.setSelectedIndex(index);
    }
    
    public void setOutputIndex(int index){
          outputComboBox.setSelectedIndex(index);
    }
    
    public int getInputColumn(){
        return inputComboBox.getSelectedIndex();
    }
    
    public int getOutputColumn(){
        return outputComboBox.getSelectedIndex();
    }
    
    
}
