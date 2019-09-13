/********************************* 
* About Box for GrafProg Project *
*  @author Bill Gillam           *
*  2/25/15                       *
**********************************/
import java.awt.BorderLayout;
//import java.io.*;
import javax.swing.*;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
//import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

//Class About displays and about box with program versions info and link to website
public class About extends JDialog {
    
    private final JPanel contentPanel = new JPanel();
    /**
    * Create the dialog. Code generated by WindowMaker
    */
    public About() {
        //gSess = sess;
        setModal(true);
        setLocationRelativeTo(GrafProg.getGrafPanel());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setTitle("About GrafProg");
        setResizable(false);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0,0));
        JLabel lblGrafProg = new JLabel("GrafProg");
        lblGrafProg.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
        lblGrafProg.setHorizontalAlignment(SwingConstants.CENTER);
        lblGrafProg.setForeground(new Color(0, 0, 255));
        lblGrafProg.setFont(new Font("Dialog", Font.BOLD, 32));
        panel.add(lblGrafProg, BorderLayout.NORTH);
        
        JPanel descrPanel = new JPanel();
        descrPanel.setLayout(new BorderLayout(0,0));
        JLabel descr = new JLabel("A Simple Graphing Program");
        descr.setHorizontalAlignment(SwingConstants.CENTER);
        descr.setFont(new Font("Tahoma", Font.PLAIN, 18));
        descrPanel.add(descr, BorderLayout.NORTH);
        
        JLabel lblJavaVersion = new JLabel("Version 3.1 10/13/18");
        lblJavaVersion.setHorizontalAlignment(SwingConstants.CENTER);
        lblJavaVersion.setFont(new Font("Tahoma", Font.PLAIN, 12));
        descrPanel.add(lblJavaVersion, BorderLayout.SOUTH);
        panel.add(descrPanel, BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.CENTER);
        
       JLabel lblNewLabel = new JLabel("Visit:  http://bgillam.com");
       lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
       lblNewLabel.setForeground(Color.BLUE);
       panel.add(lblNewLabel, BorderLayout.SOUTH);
       lblNewLabel.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
       lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
               dispose();
        }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
        
        
            
    }
      
    
    
    public static void createInputDialog(){
           About about = new About();
           about.setVisible(true); 
           about.setModal(true); 
       
    }

}   
