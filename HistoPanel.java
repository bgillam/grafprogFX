
/**
 * HistoPanel: Panel for histogram option inputs.
 * 
 * @author Bill Gillam 
 * @version 2/2/17
 */
import GrafUtils.GrafInputHelpers;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.border.BevelBorder;


public class HistoPanel extends JPanel
{
    JLabel beginLabel = new JLabel("Begin:");
    JLabel endLabel = new JLabel("End:");
    JLabel classLabel = new JLabel("compute class width by:");
    JTextField begin = new JTextField();
    JTextField end = new JTextField();
    JPanel beginPanel = new JPanel();
    JPanel endPanel = new JPanel();
    JPanel numClassesPanel = new JPanel();
    JPanel classSizePanel = new JPanel();
    JPanel beginEndPanel = new JPanel();
    JPanel classPanel = new JPanel();
    JPanel checkBoxPanel = new JPanel();
    JButton maxMinButton = new JButton("max/min");
    JRadioButton numClassRadioButton = new JRadioButton("# classes:");
    JRadioButton classSizeRadioButton = new JRadioButton("class size:");
    JTextField numClasses = new JTextField();
    JTextField classSize = new JTextField();
    JCheckBox axisCheckBox = new JCheckBox("Label Axis by Class Boundries");
    JCheckBox relativeCheckBox = new JCheckBox("Relative Frequency Histogram");
    JCheckBox showCountsCheckBox = new JCheckBox("Show Counts");
    
    /**
     * Constructor for objects of class HistoPanel
     */
    public HistoPanel()
    {
        setBackground(new Color(220, 220, 220));
        setLayout(new BorderLayout());
        begin.setColumns(5);
        end.setColumns(5);
        numClasses.setColumns(8);
        classSize.setColumns(8);
        axisCheckBox.setSelected(true);
        showCountsCheckBox.setSelected(true);
        
        //create panel for begin label and box
        beginPanel.setLayout(new BorderLayout());
        beginPanel.add(beginLabel, BorderLayout.WEST);
        beginPanel.add(begin, BorderLayout.CENTER);
        
        //create panel for end label and box
        endPanel.setLayout(new BorderLayout());
        endPanel.add(endLabel, BorderLayout.WEST);
        endPanel.add(end, BorderLayout.CENTER);
        
        JPanel topCheckPanel = new JPanel();
        topCheckPanel.setLayout(new BorderLayout());
        topCheckPanel.add(axisCheckBox, BorderLayout.NORTH);
        topCheckPanel.add(showCountsCheckBox, BorderLayout.CENTER);
        checkBoxPanel.setLayout(new BorderLayout());
        checkBoxPanel.add(topCheckPanel, BorderLayout.NORTH);
        checkBoxPanel.add(relativeCheckBox, BorderLayout.CENTER);
        
        JPanel separatorPanelTop = new JPanel();
        separatorPanelTop.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        checkBoxPanel.add(separatorPanelTop, BorderLayout.SOUTH);
        
        //put begin and in input panels into wrapper panel with axis label checkbox NORTH
        beginEndPanel.setLayout(new BorderLayout());
        beginEndPanel.add(checkBoxPanel, BorderLayout.NORTH);
        beginEndPanel.add(beginPanel, BorderLayout.WEST);
        beginEndPanel.add(endPanel, BorderLayout.EAST);
        beginEndPanel.add(maxMinButton, BorderLayout.SOUTH);
        
        //All this goes North in main panel
        add(beginEndPanel, BorderLayout.NORTH);
        
        //set up class panel in similar fashion as above
        classPanel.setLayout(new BorderLayout());
        classPanel.add(classLabel, BorderLayout.NORTH);
        classPanel.add(numClassesPanel, BorderLayout.WEST);
        classPanel.add(classSizePanel, BorderLayout.SOUTH);
        
        numClassesPanel.setLayout(new BorderLayout());
        numClassesPanel.add(numClassRadioButton, BorderLayout.WEST);
        numClassesPanel.add(numClasses, BorderLayout.CENTER);
        numClassesPanel.setBackground(UIManager.getColor("Button.background"));
        
        classSizePanel.setLayout(new BorderLayout());
        classSizePanel.add(classSizeRadioButton, BorderLayout.WEST);
        classSizePanel.add(classSize, BorderLayout.CENTER);
        //classSizePanel.add(relativeCheckBox, BorderLayout.SOUTH);
        classSizePanel.setBackground(UIManager.getColor("Button.background"));
        
        //Place at bottom
        add(classPanel, BorderLayout.SOUTH);
             
        
        JPanel separatorPanel = new JPanel();
        separatorPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        
        add(separatorPanel, BorderLayout.CENTER);
        numClassRadioButton.setSelected(true);
        classSizeRadioButton.setSelected(false);
        numClasses.setEditable(true);
        classSize.setEditable(false);
           
        numClassRadioButton.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent event) {
                       if (event.getStateChange() == ItemEvent.SELECTED){
                           classSizeRadioButton.setSelected(false);
                           numClassRadioButton.setSelected(true);
                           numClasses.setEditable(true);
                           classSize.setEditable(false);
                       }
                }
            });
            //periodRadioBtn.setSelected(true);
        
        classSizeRadioButton.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent event) {
                       if (event.getStateChange() == ItemEvent.SELECTED){
                          numClassRadioButton.setSelected(false);
                          classSizeRadioButton.setSelected(true);
                          numClasses.setEditable(false);
                          classSize.setEditable(true);
                       }
                }
            });
        classSizeRadioButton.setBackground(UIManager.getColor("Button.background"));
           
        
    }
    public double getBegin(){
         if (GrafInputHelpers.isDouble(begin.getText()))
            return Double.parseDouble(begin.getText());
        else
        return Double.NaN;
    }
    public void setBegin(double b){
        begin.setText(b+"");
    }
    
    public double getEnd(){
        if (GrafInputHelpers.isDouble(end.getText()))
            return Double.parseDouble(end.getText());
        else
        return Double.NaN;
    }
    public void setEnd(double e){
            end.setText(e+"");
    }
    public Integer getNumClasses(){
         if (GrafInputHelpers.isInt(numClasses.getText()))
            return Integer.parseInt(numClasses.getText());
        else
        return 10;
    }
    public void setNumClasses(double n){
        numClasses.setText(n+"");
    }
    public double getClassSize(){
         if (GrafInputHelpers.isDouble(classSize.getText()))
            return Double.parseDouble(classSize.getText());
        else
        return Double.NaN;
    }
    public void setClassSize(double cs){
        classSize.setText(cs+"");
    }
    public boolean classSizeChecked(){
        return classSizeRadioButton.isSelected();
    }
    public void setClassSizeChecked(boolean tf){
        classSizeRadioButton.setSelected(tf);
        numClassRadioButton.setSelected(!tf);
    }
    public boolean numClassesChecked(){
        return numClassRadioButton.isSelected();
    }
     public void setnumClassesChecked(boolean tf){
        classSizeRadioButton.setSelected(!tf);
        numClassRadioButton.setSelected(tf);
    }
    public boolean labelAxis(){
        return axisCheckBox.isSelected();
    }   
    public boolean relativeHisto(){
        return relativeCheckBox.isSelected();
    }
    public boolean showCounts(){
        return showCountsCheckBox.isSelected();
    }
      
}
