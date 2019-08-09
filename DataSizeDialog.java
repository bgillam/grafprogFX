/**************************************** 
*  Data Size Dialog for GrafProg Project *
*  Sets rows and columns sizes
*  @author Bill Gillam                   *
*  2/25/15                               *
*****************************************/
/**
 *  Dialog shown when sizing spreadsheet
 */
import java.awt.BorderLayout;
import java.io.*;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;

public class DataSizeDialog extends JDialog implements ActionListener
{   int myRows = 0;
    int myCols = 0;
    JTextField rowInput = new JTextField();  
    JTextField colInput = new JTextField();
    
    //constructor
    public DataSizeDialog(JFrame parent, int rows, int cols)
    {
        super(parent, "Resize",true);
        setModal(true);
        myRows = rows;
        myCols = cols;
        setLocationRelativeTo(parent);
        //Add OK and Cancel buttons and their listeners to a panel
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        //add two inputs
        rowInput.setText(Integer.toString(myRows));
        colInput.setText(Integer.toString(myCols));
        
        JLabel rowLabel = new JLabel("#rows:");
        rowLabel.setForeground(Color.BLUE);
        JLabel colLabel = new JLabel("#cols:");
        colLabel.setForeground(Color.BLUE);
        JPanel textPanel = new JPanel();
        textPanel.add(rowLabel);
        textPanel.add(rowInput);
        textPanel.add(colLabel);
        textPanel.add(colInput);
        //add the message to a panel
        JLabel resizeMessage = new JLabel("Resize Data Matrix");
        resizeMessage.setForeground(Color.BLUE);
        JPanel messagePanel = new JPanel();
        messagePanel.add(resizeMessage);
        //put the panels in the frame
        getContentPane().add(messagePanel, BorderLayout.NORTH);
        getContentPane().add(textPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel,BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        //setVisible(true);
    }
  
    /* displays the data sizing dialog and returns the dimensions of the data spreadsheet
     *  return rows and columns stored as a Point object
     */
  public Point showDataSizeDialog(){
      setVisible(true);
      setModal(true);
      return new Point(myRows,myCols);
    }

  // This is what happens if you choose "OK"
  public void actionPerformed(ActionEvent event) {
    if (event.getActionCommand().equals("OK")) { 
    	String inputValue = rowInput.getText();
    	if (GrafInputHelpers.isAnInteger(inputValue)) myRows = Integer.parseInt(inputValue);
    	inputValue = colInput.getText();
        if (GrafInputHelpers.isAnInteger(inputValue)) myCols = Integer.valueOf(inputValue);
    }    
    setVisible(false); 
    dispose(); 
  }
}
