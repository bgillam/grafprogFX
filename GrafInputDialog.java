

/**
 * GrafInputDialog for GrafProg 
 * gets parameters for object creation
 * @author (Bill Gillam) 
 * @version (1/1/2017)
 */

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class GrafInputDialog extends JDialog
{
    //class variables
    private JPanel optionPanel= new JPanel();
    private JPanel exitPanel = new JPanel();
    private PointPanel ptPanel;
    private ColumnChooserPanel columnChooser;  
    private HistoPanel histoPanel;
    private MarkPanel markChooser;
    private GrafDeletePanel deleter;
    private JButton createButton = new JButton("Create");
    private JButton cancelChanges = new JButton("Exit/No Changes"); 
    private JButton saveChanges =  new JButton("Exit/Save All");
    private ArrayList<GrafObject> tempList = new ArrayList<GrafObject>();
    private GrafProg gSess;
    private boolean finalSave = false;
       
    //tester
    public static void main(String[] args){
        GrafProg gSess = new GrafProg();
        GrafInputDialog gid = new GrafInputDialog(gSess);
       
    }
    
    //creates dialog with buttons used by all Graf dialogs    
    public GrafInputDialog(GrafProg gs){
        gSess = gs;
        tempList = (ArrayList<GrafObject>)(gSess.getGrafList().clone()); //make a temporary copy of list to modify. This provides opportunity to cancel or save changes when ending dialog
        getContentPane().setLayout(new BorderLayout());
        addSeparatorPanel();  
        //Create-Save-Exit Options on bottom panel
        optionPanel.setLayout(new BorderLayout());
        optionPanel.add(createButton, BorderLayout.WEST);
        cancelChanges.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent arg0) {
                dispose(); 
             }
        });
        exitPanel.add(cancelChanges, BorderLayout.WEST);
        exitPanel.add(saveChanges,BorderLayout.CENTER);
        optionPanel.add(exitPanel, BorderLayout.EAST);
        getContentPane().add(optionPanel, BorderLayout.SOUTH);
    }
    
    //adds a column chooser to the input dialog
    public void addColumnChooserPanel(String[] str, boolean inputOn, boolean outputOn) {
         columnChooser = new ColumnChooserPanel(str, inputOn, outputOn);
         columnChooser.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
         columnChooser.setBackground(UIManager.getColor("Button.background"));
         getContentPane().add(columnChooser, BorderLayout.NORTH);
         //return columnChooser;
    }    
    
     //Create panel to input points and place NORTH. Used for Shapes
    public PointPanel addPointPanel(){
        ptPanel = new PointPanel(tempList);
        ptPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        ptPanel.setBackground(UIManager.getColor("Button.background"));
        getContentPane().add(ptPanel, BorderLayout.NORTH);
        return ptPanel;
    }
    //create deleter panel and place EAST
    public GrafDeletePanel addDeleterPanel(GrafType gType){
        deleter = new GrafDeletePanel(this, gType, tempList);
        deleter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        deleter.setBackground(UIManager.getColor("Button.background"));
        getContentPane().add(deleter, BorderLayout.EAST);
        return deleter;
    }

//    public GrafDeletePanel addDeleterPanel(GrafObject g){
//        deleter = new GrafDeletePanel(this, tempList);
//        deleter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//        deleter.setBackground(UIManager.getColor("Button.background"));
//        getContentPane().add(deleter, BorderLayout.EAST);
//        return deleter;
//    }
   
    //Create mark and color chooser and place WEST
    public MarkPanel addMarkPanel(MarkPanel mp){
        mp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        mp.setBackground(UIManager.getColor("Button.background"));
        getContentPane().add(mp, BorderLayout.WEST);
        return mp;
    }
    
    
    //add separator to dialog at CENTER
    public void addSeparatorPanel(){
        JPanel separatorPanel = new JPanel();
        separatorPanel.add(new JSeparator(SwingConstants.VERTICAL));
        getContentPane().add(separatorPanel, BorderLayout.CENTER);
    }
    
    //displays error message in dialog
    public void NumErrorMessage(String str, String str2){
        JOptionPane.showMessageDialog(null,
                   "The value entered for "+str+" is not a "+str2 ,
                   "Number Format Error",
                   JOptionPane.ERROR_MESSAGE);   
         }
    
    //choose a column for input               
    public int getInput(){
            int input = columnChooser.getInputColumn();
            if (input == 0) { JOptionPane.showMessageDialog(null,
                   "You must choose an input column for your plot.",
                   "Choose an input column",
                   JOptionPane.ERROR_MESSAGE);
            }
          
            return input;
         
    }
    
    //choose a column for output
    public int getOutput(){
            int output = columnChooser.getOutputColumn();
             //output++;
             
             if (output == 0) { JOptionPane.showMessageDialog(null,
                    "You must choose an output column for your plot.",
                    "Choose an output column",
                   JOptionPane.ERROR_MESSAGE);
             }
             return output;
    }
    
    //getters and setter
    public ColumnChooserPanel getColumnChooser(){
        return columnChooser;
    }
    
    public void setColumnChooser(ColumnChooserPanel cp){
        columnChooser = cp;
    
    }
    
    public PointPanel getPointChooser(){
        return ptPanel;
    }
    public MarkPanel getMarkChooser(){
        return markChooser;
   }
    public HistoPanel getHistoPanel(){
        return histoPanel;
    }
    
    public HistoPanel getHisto(){
        return histoPanel;
    }
    
    public void setHistoPanel(HistoPanel hp){
            histoPanel= hp;
    
    }
  
   
    
    public String[] getColumnsString(){
         String[] colArray = {"","col1","col2","col3","col4","col5"};
        //need procedure to create this string from existing data spreadsheet numCols.
        //switch statement for different types setup 
        return colArray;
    }
    
    public String getMark(){
        if (markChooser.xMark())return "x"; 
        if (markChooser.oMark()) return "o"; 
        if (markChooser.periodMark()) return "."; 
        else return markChooser.getTextMark(); 
    }
    
    //getters and setters
    
    public void setMarkChooser(MarkPanel mp){
        markChooser = mp;
    }
    
    public void setPointPanel(PointPanel p){
    ptPanel = p;
    
    }
    
    public PointPanel getPointPanel(){
        return ptPanel;
    
    }
    
    

    public JButton getCreateButton(){
        return createButton;
    
    }
    
    public JButton getSaveChanges(){
        return saveChanges;
    }
    
    public void setFinalSave(boolean tf){
        finalSave = tf;
    }
    
    public boolean getFinalSave(){
        return finalSave;
    }
    
    public GrafProg getSess(){
        return gSess;
    }
    
    public ArrayList<GrafObject> getTempList(){
            return tempList;
    }
    
    public void setTemplist(ArrayList<GrafObject> tl){
        tempList = tl;
    }
    
    public GrafDeletePanel getDeleter(){
        return deleter;
    
    }
    
    public void setDeleter(GrafDeletePanel gdp){
        deleter = gdp;
    }
    
    
    
    public PointPanel getPtPanel(){
        return ptPanel;
    }
    
        
}
