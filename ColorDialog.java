/**************************************** 
*  ColorDialog  						*
*  @author Bill Gillam                  *
*  2/25/15                              *
*****************************************/
/**
*  For choosing colors  
*/
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JColorChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.colorchooser.ColorSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class ColorDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Color currentColor;
	private GrafObject gObject;

	/**
	 * Create the dialog.
	 */
	public ColorDialog() {
		setModal(true);
		setBounds(100, 100, 650, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			final JColorChooser colorChooser = new JColorChooser();
			contentPanel.add(colorChooser);
			//add listener for drop down
			ColorSelectionModel model = colorChooser.getSelectionModel();
		    ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		          currentColor = colorChooser.getColor();
		         		      }
		    };
		    model.addChangeListener(changeListener);
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
    /* showDialog makes color chooser visible and returns selected color 
     * 	
     */
	public Color showDialog(){
		setVisible(true);
		return currentColor;
	}

}
