/**************************************** 
*  TableFunctionDialog  for GrafProg Project 
*  Dialog to generates table values from a function
*  @author Bill Gillam                  
*  2/25/15                              
*****************************************/ 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JToggleButton;

import java.awt.Dialog.ModalityType;

import javax.swing.border.BevelBorder;
import javax.swing.JRadioButton;


public class TableFunctionDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField beginRowTextField;
    private JComboBox outputComboBox;
    private JComboBox inputComboBox;
    private int selectedOutputColumn = 1;
    private int selectedInputColumn = 0;
    private GrafTable myDaddy;
    private JLabel chooseLabel;
    private JTextField endRowTextField;
    private JTextField functionTextField;

    
    @SuppressWarnings("unchecked")
    public TableFunctionDialog(GrafTable gt) {
        setModal(true);
        myDaddy = gt;
        setTitle("Generate Function Values");
        //setModal(true);
        setBounds(100, 100, 407, 238);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        //setVisible(true);     
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.NORTH);
            panel.setLayout(new BorderLayout(0, 0));
            {
                JPanel panel_1 = new JPanel();
                panel.add(panel_1, BorderLayout.NORTH);
                {
                    JPanel panel_2 = new JPanel();
                    panel_1.add(panel_2);
                    panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
                    panel_2.setLayout(new BorderLayout(0, 0));
                    {
                        JPanel panel_1_1 = new JPanel();
                        panel_2.add(panel_1_1, BorderLayout.WEST);
                        panel_1_1.setLayout(new BorderLayout(0, 0));
                        {
                            JLabel lblBetween = new JLabel("Y=");
                            panel_1_1.add(lblBetween, BorderLayout.WEST);
                        }
                        {
                            functionTextField = new JTextField();
                            //beginNumTextField.setText("0");
                            panel_1_1.add(functionTextField, BorderLayout.EAST);
                            functionTextField.setColumns(10);
                        }
                    }
                    {
                        JPanel panel_1_1 = new JPanel();
                        panel_2.add(panel_1_1, BorderLayout.EAST);
                        panel_1_1.setLayout(new BorderLayout(0, 0));
                    }
                }
                {
                    JButton btnSave = new JButton("Save");
                    panel_1.add(btnSave);
                    btnSave.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            generateFunctionValues();
                        }
                    });
                }
            }
            {
                JPanel panel_1 = new JPanel();
                panel.add(panel_1, BorderLayout.SOUTH);
                panel_1.setLayout(new BorderLayout(0, 0));
                {
                    JPanel panel_2 = new JPanel();
                    panel_1.add(panel_2, BorderLayout.NORTH);
                    panel_2.setLayout(new BorderLayout(0, 0));
                    {
                        JPanel panel_3 = new JPanel();
                        panel_2.add(panel_3, BorderLayout.NORTH);
                        {
                            chooseLabel = new JLabel("Choose Column for Output Values");
                            panel_3.add(chooseLabel);
                        }
                        {
                            outputComboBox = new JComboBox();
                            panel_3.add(outputComboBox);
                            outputComboBox.setModel(new javax.swing.DefaultComboBoxModel(myDaddy.getHeaderArray()));
                            outputComboBox.addItemListener(new ItemListener() {
                                public void itemStateChanged(ItemEvent event) {
                                    if ((event.getStateChange() == ItemEvent.SELECTED) && !(outputComboBox.getSelectedIndex() == 0)) {
                                         selectedOutputColumn = outputComboBox.getSelectedIndex();
                                         //allowEdits();
                                         
                                    }
                                }
                            });
                            {
                                JPanel panel_4 = new JPanel();
                                panel_2.add(panel_4, BorderLayout.SOUTH);
                                {
                                    JLabel lblChooseColumnFor = new JLabel("Choose Column for Input Values");
                                    panel_4.add(lblChooseColumnFor);
                                }
                                {
                                    inputComboBox = new JComboBox();
                                    panel_4.add(inputComboBox);
                                    inputComboBox.setModel(new javax.swing.DefaultComboBoxModel(myDaddy.getHeaderArray()));
                                    inputComboBox.addItemListener(new ItemListener() {
                                        public void itemStateChanged(ItemEvent event) {
                                            if (event.getStateChange() == ItemEvent.SELECTED)  {
                                                 selectedInputColumn = inputComboBox.getSelectedIndex();
                                            //   allowEdits();
                                                 
                                            }
                                        }
                                    });
                                    
                                    {
                                        JLabel lbldefaultIs = new JLabel("(default is 1,2,3...)");
                                        panel_4.add(lbldefaultIs);
                                    }
                                    //inputComboBox.setModel(new javax.swing.DefaultComboBoxModel(myDaddy.getHeaderArray()));
                                    
                                }
                            }
                            
                        }
                    }
                }
            }
        }
        {
            JPanel panel = new JPanel();
            panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
            contentPanel.add(panel, BorderLayout.CENTER);
            {
                JPanel panel_1 = new JPanel();
                panel.add(panel_1);
                {
                    JLabel lblBeginRow = new JLabel("Begin row:");
                        panel_1.add(lblBeginRow);
                }
                {
                    beginRowTextField = new JTextField();
                    panel_1.add(beginRowTextField);
                    beginRowTextField.setColumns(10);
                }
            }
            {
                JPanel panel_1 = new JPanel();
                panel.add(panel_1);
                {
                    JLabel lblEndRow = new JLabel("End row:");
                    panel_1.add(lblEndRow);
                }
                {
                    endRowTextField = new JTextField();
                    panel_1.add(endRowTextField);
                    endRowTextField.setColumns(10);
                    
                }
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        generateFunctionValues();
                        dispose();
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            
        }
        
    }



    private void generateFunctionValues(){
        int beginRow, endRow;
        //myDaddy.setHeaderString(selectedColumn, beginTextField.getText());
        //headerComboBox.setModel(new javax.swing.DefaultComboBoxModel(myDaddy.getHeaderArray()));
        if (outputComboBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Choose and Output Column.", "Choose Output" , JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!GrafInputHelpers.isAnIntegerWithMessage(beginRowTextField.getText())) 
            return; 
        beginRow = Integer.parseInt(beginRowTextField.getText());
        if (!GrafInputHelpers.isAnIntegerWithMessage(endRowTextField.getText())) 
            return; 
        endRow = Integer.parseInt(endRowTextField.getText());
        if (endRow > myDaddy.getNumRows()) endRow = myDaddy.getNumRows();
        if (!FunctionString.isValidAtXIgnoreDomainError(functionTextField.getText(), 1)) {System.out.println("error"); return; }
        for (int row = beginRow; row <= endRow; row++)
            try {
                myDaddy.setCellValueDouble(row, selectedOutputColumn, FunctionString.fValue(functionTextField.getText(), myDaddy.getCellValue(row, selectedInputColumn)));
            } catch (NullPointerException e)  { } //empty cells
              catch (DomainViolationException e) { }   //we know we will not get an exception because tested in previous if statement
              catch (FunctionFormatException e) {}   
        //myDaddy.repaint();
    }
    
    public void showDialog(){
        //setVisible(true); 
        //setModal(false);
        beginRowTextField.setText("1");
        endRowTextField.setText(""+myDaddy.getNumRows());
        functionTextField.setText("");
        setVisible(true);   
        //setModal(true);
        
    }
    
}
