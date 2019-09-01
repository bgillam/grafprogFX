/**************************************** 
*  RecursiveDialog for GrafProg Project *
*   Creates a Dialog Box for recursion  *
*  parameters                           *
*  @author Bill Gillam                  *
*  2/25/15                              *
* 
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


public class RecursiveDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField a1TextField;
	private JComboBox outputComboBox;
	private int selectedOutputColumn = 1;
	private int selectedInputColumn = 0;
	private GrafTable myDaddy;
	private JLabel chooseLabel;
	private JTextField anTextField;

	
	@SuppressWarnings("unchecked")
	public RecursiveDialog(GrafTable gt) {
		setModal(true);
		myDaddy = gt;
		setTitle("Generate Function Values");
		//setModal(true);
		setBounds(100, 100, 407, 197);
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
					JPanel panel_1_1 = new JPanel();
					panel_1.add(panel_1_1);
					{
						JLabel lblBeginRow = new JLabel("A1 =");
						panel_1_1.add(lblBeginRow);
					}
					{
						a1TextField = new JTextField();
						panel_1_1.add(a1TextField);
						a1TextField.setColumns(10);
					}
				}
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
							JLabel lblBetween = new JLabel("An =");
							panel_1_1.add(lblBetween, BorderLayout.WEST);
						}
						{
							anTextField = new JTextField();
							//beginNumTextField.setText("0");
							panel_1_1.add(anTextField, BorderLayout.EAST);
							anTextField.setColumns(10);
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
							//System.out.println("Save pushed");
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
								JLabel lbldefaultIsCol = new JLabel("(default is col 1)");
								panel_3.add(lbldefaultIsCol);
							}
							
						}
					}
				}
			}
		}
		{
			JLabel lblUseFor = new JLabel("Use @ for An-1. Example: A1 = 1 and An = 3*@+2    ->   5, 17, 53 .... ");
			contentPanel.add(lblUseFor, BorderLayout.CENTER);
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
		//System.out.println("in");
		double startA = 1;
		String functionString = "";
		int pos = -1;
		int fLength = -1;
		int beginRow, endRow;
		//myDaddy.setHeaderString(selectedColumn, beginTextField.getText());
		//headerComboBox.setModel(new javax.swing.DefaultComboBoxModel(myDaddy.getHeaderArray()));
		if (outputComboBox.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Choose an Output Column", "Choose Output" , JOptionPane.ERROR_MESSAGE); 
			return;
		}
		if (!GrafInputHelpers.isAnInteger(a1TextField.getText())) 	return; 
		startA = Integer.parseInt(a1TextField.getText());
		functionString =  anTextField.getText();
		fLength = functionString.length();
		String first, last;
		for (int i = 0; i < fLength; i++){
			   pos = functionString.indexOf("@");
			   if (pos == -1) break; 
			   first = functionString.substring(0, pos);
			   if (pos+1 == fLength) last = ""; else  last = functionString.substring(pos+1, fLength);
			   functionString = first+"X"+last;
			  			   
		}
		if (!FunctionString.isValidAtXIgnoreDomainError(functionString, 1)) return;
		beginRow = 1;
		endRow = myDaddy.getNumRows();
		double anMinus1 = startA; 
		double newVal = startA;
		for (int row = beginRow; row <= endRow; row++)
			try {
				newVal = FunctionString.fValue(functionString, anMinus1);
				myDaddy.setCellValueDouble(row, selectedOutputColumn, newVal);
				anMinus1=newVal;
			} catch (DomainViolationException e) { }  //we know we should not get an exception because tested previously
			  catch (FunctionFormatException e) {}   
		//myDaddy.repaint();
	}
	
    public void showDialog(){
    	a1TextField.setText("1");
		anTextField.setText("");
		setVisible(true);	
    	//setModal(true);
	}
	
}
