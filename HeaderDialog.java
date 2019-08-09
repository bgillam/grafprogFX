/**************************************** 
*  Header editor for GrafTable          *
*  @author Bill Gillam                  *
*  2/25/15                              *
*****************************************/ 
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class HeaderDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField headerTextField;
	private JComboBox headerComboBox;
	private int selectedColumn = 0;
	private GrafTable myDaddy;
	private JLabel chooseLabel;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		try {
			HeaderDialog dialog = new HeaderDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	
	@SuppressWarnings("unchecked")
	public HeaderDialog(GrafTable gt) {
		myDaddy = gt;
		setTitle("Change Header Titles");
		setModal(true);
		setBounds(100, 100, 450, 131);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				chooseLabel = new JLabel("Choose Header to Edit:");
				panel.add(chooseLabel);
			}
			{
				headerComboBox = new JComboBox();
				headerComboBox.setModel(new javax.swing.DefaultComboBoxModel(myDaddy.getHeaderArray()));
				panel.add(headerComboBox);
				headerComboBox.addItemListener(new ItemListener() {
			        public void itemStateChanged(ItemEvent event) {
			            if (event.getStateChange() == ItemEvent.SELECTED){
			            	 selectedColumn = headerComboBox.getSelectedIndex();
			                 headerTextField.setText((String) headerComboBox.getSelectedItem());
			            	 headerTextField.setEditable(true);
			            	 chooseLabel.setText("Edit Header "+selectedColumn+".");
			            	 //headerComboBox.setModel(new javax.swing.DefaultComboBoxModel(myDaddy.getHeaderArray()));
			            }
			        }
			    });
			}
			{
				headerTextField = new JTextField();
				headerTextField.setEditable(false);
				panel.add(headerTextField);
				headerTextField.setColumns(10);
			}
			{
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (headerTextField.isEditable()) {
							saveHeaderEdit();
						}
					}
				});
				panel.add(btnSave);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (headerTextField.isEditable()) saveHeaderEdit();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	private void saveHeaderEdit(){
		myDaddy.setHeaderString(selectedColumn, headerTextField.getText());
		headerComboBox.setModel(new javax.swing.DefaultComboBoxModel(myDaddy.getHeaderArray()));
		headerTextField.setEditable(false);
		chooseLabel.setText("Choose Header to Edit:");
		myDaddy.repaint();
	}
	

}
