/**************************************** 
*  RandomDialog for GrafProg Project *
*  Dialog to generate random values in 
*  GrafTable column
*  @author Bill Gillam                   *
*  2/25/15                               *
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


public class RandomDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField beginRowTextField;
	private JComboBox headerComboBox;
	private int selectedColumn = 0;
	private GrafTable myDaddy;
	private JLabel chooseLabel;
	private JTextField endRowTextField;
	private JTextField beginNumTextField;
	private JTextField endNumTextField;
	private JRadioButton rdbtnIntegers;
	private JRadioButton rdbtnDoubles;

	
	@SuppressWarnings("unchecked")
	public RandomDialog(GrafTable gt) {
		
		setModal(true);
		myDaddy = gt;
		setTitle("Place random values in column");
		//setModal(true);
		setBounds(100, 100, 367, 207);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		//setVisible(true);		
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BorderLayout(0, 0));
			chooseLabel = new JLabel("Choose Column to Place Values:");
			panel.add(chooseLabel, BorderLayout.WEST);
			{
				headerComboBox = new JComboBox();
				headerComboBox.setModel(new javax.swing.DefaultComboBoxModel(myDaddy.getHeaderArray()));
				panel.add(headerComboBox, BorderLayout.CENTER);

				headerComboBox.addItemListener(new ItemListener() {
			        public void itemStateChanged(ItemEvent event) {
							if ((event.getStateChange() == ItemEvent.SELECTED) && !(headerComboBox.getSelectedIndex() == 0)) {
								selectedColumn = headerComboBox.getSelectedIndex();
								allowEdits();
							}
			        }
			    });
			}
			{
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//if (beginRowTextField.isEditable()) {
							generateRandom();
						//}
					}
				});
				panel.add(btnSave, BorderLayout.EAST);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			contentPanel.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.WEST);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblBetween = new JLabel("Between ");
					panel_1.add(lblBetween, BorderLayout.WEST);
				}
				{
					beginNumTextField = new JTextField();
					//beginNumTextField.setText("0");
					panel_1.add(beginNumTextField, BorderLayout.EAST);
					beginNumTextField.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.EAST);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblAnd = new JLabel("and");
					panel_1.add(lblAnd, BorderLayout.WEST);
				}
				{
					endNumTextField = new JTextField();
					panel_1.add(endNumTextField, BorderLayout.EAST);
					endNumTextField.setColumns(10);
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
						if (beginRowTextField.isEditable()) generateRandom();
						dispose();
					}
				});
				{
					rdbtnDoubles = new JRadioButton("Doubles");
					buttonPane.add(rdbtnDoubles);
					rdbtnDoubles.setSelected(true);
					rdbtnDoubles.addItemListener(new ItemListener() {
				        public void itemStateChanged(ItemEvent event) {
				            if (event.getStateChange() == ItemEvent.SELECTED){
				            	rdbtnDoubles.setSelected(true);
				            	rdbtnIntegers.setSelected(false);
				            					            	
				            }
				        }
				    });
				}
				{
					rdbtnIntegers = new JRadioButton("Integers");
					buttonPane.add(rdbtnIntegers);
					rdbtnIntegers.addItemListener(new ItemListener() {
				        public void itemStateChanged(ItemEvent event) {
				            if (event.getStateChange() == ItemEvent.SELECTED){
				            	rdbtnDoubles.setSelected(false);
				            	rdbtnIntegers.setSelected(true);
				            					            	
				            }
				        }
				    });
				}
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
	}



    private void generateRandomIntegers(){
		double lower = Double.parseDouble(beginNumTextField.getText());
		double upper = Double.parseDouble(endNumTextField.getText());
		int begin = Integer.parseInt(beginRowTextField.getText());
		int end = Integer.parseInt(endRowTextField.getText());
		int numRows = myDaddy.getNumRows();
		if (upper > numRows) upper = numRows;
		double r;
		//int n;
		for (int i = begin; i <= end ; i++){
        	r = (Math.random() * (upper - lower)) + lower;
        	r =  (double) Math.round(r);
        	myDaddy.setCellValueDouble(i, selectedColumn, r);
		}
	}
	
	private void generateRandomDoubles(){
		double lower = Double.parseDouble(beginNumTextField.getText());
		double upper = Double.parseDouble(endNumTextField.getText());
		int begin = Integer.parseInt(beginRowTextField.getText());
		if (begin < 1) begin = 1;
		int end = Integer.parseInt(endRowTextField.getText());
		int numRows = myDaddy.getNumRows();
		if (upper > numRows) upper = numRows;
		double r;
		for (int i = begin; i <= end ; i++){
        	r = (Math.random() * (upper - lower)) + lower;
          	myDaddy.setCellValueDouble(i, selectedColumn, r);
		}
	}
	
	private void allowEdits(){
		 beginRowTextField.setEditable(true);
		 endRowTextField.setEditable(true);
		 beginNumTextField.setEditable(true);
		 endNumTextField.setEditable(true);
		 chooseLabel.setText("Enter Limits and Destination");
	}
	
	private void resetChooser(){
		headerComboBox.setSelectedIndex(0);
		beginRowTextField.setEditable(false);
		endRowTextField.setEditable(false);
		beginNumTextField.setEditable(false);
		endNumTextField.setEditable(false);
		chooseLabel.setText("Choose Column to Place Values");
	}
	
	private void generateRandom(){
		//myDaddy.setHeaderString(selectedColumn, beginTextField.getText());
		//headerComboBox.setModel(new javax.swing.DefaultComboBoxModel(myDaddy.getHeaderArray()));
		
		if (headerComboBox.getSelectedIndex() == 0) { 
			JOptionPane.showMessageDialog(null, "Choose an Output Column", "Choose Output" , JOptionPane.ERROR_MESSAGE); 
			resetChooser(); 
			return;
		}
		if (!GrafInputHelpers.isInt(beginRowTextField.getText())) return;
		if (!GrafInputHelpers.isInt(endRowTextField.getText())) return;
		if (!GrafInputHelpers.isInt(beginNumTextField.getText())) return;
		if (!GrafInputHelpers.isDouble(endNumTextField.getText())) return;
		if (rdbtnIntegers.isSelected()) generateRandomIntegers();
		else if (rdbtnDoubles.isSelected()) generateRandomDoubles();
		resetChooser();
		//myDaddy.repaint();
	}
	
    public void showDialog(){
    	//setVisible(true);	
    	//setModal(false);
		System.out.println("showDialog");
		beginRowTextField.setText("1");
		endRowTextField.setText(""+myDaddy.getNumRows());
		beginNumTextField.setText("0");
		endNumTextField.setText("1");
		resetChooser();
		setVisible(true);	
    	//setModal(true);
		//GrafProg.getTableStage().isAlwaysOnTop();
		
    }
	
}
