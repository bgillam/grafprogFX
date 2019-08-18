//Dialog for generating statistics from table data
//Generated by graphical tool, so it is nasty
//Bill Gillam
//5/2/15
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.UIManager;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.SystemColor;


public class GrafStatsDialog extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    //private ArrayList<Integer> columnplotIndex = new ArrayList<Integer>();  // indexes which grafList objects are columnplots
    private GrafProg gSess;
    private JComboBox functionComboBox;
    private JTextField textFieldN;
    private JTextField textFieldMin;
    private JTextField textFieldQ1;
    private JTextField textFieldMedian;
    private JTextField textFieldQ3;
    private JTextField textFieldMax;
    private JTextField textFieldRange;
    private JTextField textFieldMean;
    private JTextField textFieldVarP;
    private JTextField textFieldVarS;
    private JTextField textFieldStDevS;
    private JTextField textFieldStDevP;
    private JTextField textFieldModes;
    private JTextField textFieldIQR;
    
    
        
    /**
     * Create the dialog.
     */
    public GrafStatsDialog(GrafProg sess) {
        gSess=sess;
        setBounds(100, 100, 366, 286);
        getContentPane().setLayout(new BorderLayout());
        setModal(true);
        setTitle("Column Plot");
        //indexColumnplots();
        {
            {
                {
                    {
                        {
                        }
                    }
                }
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            buttonPane.setLayout(new BorderLayout(0, 0));
            {
                JPanel panel = new JPanel();
                buttonPane.add(panel, BorderLayout.EAST);
                {
                    JButton okButton = new JButton("Close");
                    okButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            gSess.repaint();     
                            gSess.getGrafPanel().repaint();
                            dispose();
                            }
                    });
                    panel.add(okButton);
                    okButton.setActionCommand("OK");
                    getRootPane().setDefaultButton(okButton);
                }
            }
            {
                {
                    JPanel panel = new JPanel();
                    getContentPane().add(panel, BorderLayout.WEST);
                    panel.setLayout(new BorderLayout(0, 0));
                    {
                        JPanel panel_1 = new JPanel();
                        panel.add(panel_1, BorderLayout.NORTH);
                        panel_1.setLayout(new BorderLayout(0, 0));
                        {
                            JPanel panel_1_1 = new JPanel();
                            panel_1.add(panel_1_1, BorderLayout.WEST);
                            panel_1_1.setLayout(new BorderLayout(0, 0));
                            {
                                JLabel lblMin = new JLabel("Min:");
                                panel_1_1.add(lblMin, BorderLayout.WEST);
                            }
                        }
                        {
                            textFieldMin = new JTextField();
                            panel_1.add(textFieldMin, BorderLayout.CENTER);
                            textFieldMin.setColumns(10);
                        }
                        {
                            JPanel panel_2 = new JPanel();
                            panel_1.add(panel_2, BorderLayout.EAST);
                        }
                    }
                    {
                        JPanel panel_1 = new JPanel();
                        panel.add(panel_1, BorderLayout.WEST);
                        panel_1.setLayout(new BorderLayout(0, 0));
                        {
                            JPanel panel_2 = new JPanel();
                            panel_1.add(panel_2, BorderLayout.NORTH);
                            panel_2.setLayout(new BorderLayout(0, 0));
                            {
                                JLabel lblQ = new JLabel("Q1:");
                                panel_2.add(lblQ, BorderLayout.WEST);
                            }
                            {
                                textFieldQ1 = new JTextField();
                                panel_2.add(textFieldQ1, BorderLayout.CENTER);
                                textFieldQ1.setColumns(10);
                            }
                            {
                                JPanel panel_3 = new JPanel();
                                panel_2.add(panel_3, BorderLayout.EAST);
                            }
                        }
                        {
                            JPanel panel_2 = new JPanel();
                            panel_1.add(panel_2, BorderLayout.CENTER);
                            panel_2.setLayout(new BorderLayout(0, 0));
                            {
                                JPanel panel_3 = new JPanel();
                                panel_2.add(panel_3, BorderLayout.NORTH);
                                panel_3.setLayout(new BorderLayout(0, 0));
                                {
                                    JLabel lblMedian = new JLabel("Median");
                                    panel_3.add(lblMedian, BorderLayout.WEST);
                                }
                                {
                                    textFieldMedian = new JTextField();
                                    panel_3.add(textFieldMedian, BorderLayout.CENTER);
                                    textFieldMedian.setColumns(10);
                                }
                                {
                                    JPanel panel_4 = new JPanel();
                                    panel_3.add(panel_4, BorderLayout.EAST);
                                }
                            }
                            {
                                JPanel panel_3 = new JPanel();
                                panel_2.add(panel_3, BorderLayout.CENTER);
                                panel_3.setLayout(new BorderLayout(0, 0));
                                {
                                    JPanel panel_3_1 = new JPanel();
                                    panel_3.add(panel_3_1, BorderLayout.NORTH);
                                    panel_3_1.setLayout(new BorderLayout(0, 0));
                                    {
                                        JLabel lblQ_1 = new JLabel("Q3:");
                                        panel_3_1.add(lblQ_1, BorderLayout.WEST);
                                    }
                                    {
                                        textFieldQ3 = new JTextField();
                                        panel_3_1.add(textFieldQ3, BorderLayout.CENTER);
                                        textFieldQ3.setColumns(10);
                                    }
                                    {
                                        JPanel panel_4 = new JPanel();
                                        panel_3_1.add(panel_4, BorderLayout.EAST);
                                    }
                                }
                                {
                                    JPanel panel_4 = new JPanel();
                                    panel_3.add(panel_4, BorderLayout.CENTER);
                                    panel_4.setLayout(new BorderLayout(0, 0));
                                    {
                                        JPanel panel_2_1 = new JPanel();
                                        panel_4.add(panel_2_1, BorderLayout.NORTH);
                                        panel_2_1.setLayout(new BorderLayout(0, 0));
                                        {
                                            JPanel panel_3_1 = new JPanel();
                                            panel_2_1.add(panel_3_1, BorderLayout.WEST);
                                            panel_3_1.setLayout(new BorderLayout(0, 0));
                                        }
                                        {
                                            JPanel panel_5 = new JPanel();
                                            panel_2_1.add(panel_5, BorderLayout.NORTH);
                                            panel_5.setLayout(new BorderLayout(0, 0));
                                            {
                                                JPanel panel_6 = new JPanel();
                                                panel_5.add(panel_6, BorderLayout.EAST);
                                            }
                                            {
                                                JLabel lblMax = new JLabel("Max:");
                                                panel_5.add(lblMax, BorderLayout.WEST);
                                            }
                                            {
                                                textFieldMax = new JTextField();
                                                panel_5.add(textFieldMax, BorderLayout.CENTER);
                                                textFieldMax.setColumns(10);
                                            }
                                        }
                                        {
                                            JPanel panel_5 = new JPanel();
                                            panel_2_1.add(panel_5, BorderLayout.SOUTH);
                                            panel_5.setLayout(new BorderLayout(0, 0));
                                            {
                                                JLabel lblIqr = new JLabel("IQR:");
                                                panel_5.add(lblIqr, BorderLayout.WEST);
                                            }
                                            {
                                                textFieldIQR = new JTextField();
                                                panel_5.add(textFieldIQR, BorderLayout.CENTER);
                                                textFieldIQR.setColumns(10);
                                            }
                                            {
                                                JPanel panel_5_1 = new JPanel();
                                                panel_5.add(panel_5_1, BorderLayout.EAST);
                                            }
                                        }
                                    }
                                    {
                                        JPanel panel_2_1 = new JPanel();
                                        panel_4.add(panel_2_1, BorderLayout.CENTER);
                                        panel_2_1.setLayout(new BorderLayout(0, 0));
                                        {
                                            JPanel panel_9 = new JPanel();
                                            panel_2_1.add(panel_9, BorderLayout.SOUTH);
                                            panel_9.setLayout(new BorderLayout(0, 0));
                                            {
                                                JPanel panel_8 = new JPanel();
                                                panel_9.add(panel_8, BorderLayout.NORTH);
                                                panel_8.setLayout(new BorderLayout(0, 0));
                                                {
                                                    JLabel lblModes = new JLabel("Modes:");
                                                    panel_8.add(lblModes, BorderLayout.WEST);
                                                }
                                                {
                                                    textFieldModes = new JTextField();
                                                    panel_8.add(textFieldModes, BorderLayout.CENTER);
                                                    textFieldModes.setColumns(10);
                                                }
                                                {
                                                    JPanel panel_5 = new JPanel();
                                                    panel_8.add(panel_5, BorderLayout.EAST);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                {
                    JPanel panel = new JPanel();
                    getContentPane().add(panel, BorderLayout.EAST);
                }
                {
                    JPanel panel = new JPanel();
                    getContentPane().add(panel, BorderLayout.NORTH);
                    JPanel panel_3 = new JPanel();
                    panel.add(panel_3);
                    {
                        JPanel panel_3_1 = new JPanel();
                        panel_3.add(panel_3_1);
                        {
                            JLabel lblChooseColumnFor = new JLabel("Choose Column for Statistics");
                            panel_3_1.add(lblChooseColumnFor);
                        }
                        {
                            functionComboBox = new JComboBox();
                            panel_3_1.add(functionComboBox);
                            functionComboBox.setModel(new javax.swing.DefaultComboBoxModel(gSess.getData().getHeaderArray()));
                        }
                    }
                    JButton calcBtn = new JButton("Calc");
                    panel_3.add(calcBtn);
                    {
                        JPanel panel_5 = new JPanel();
                        getContentPane().add(panel_5, BorderLayout.CENTER);
                        panel_5.setLayout(new BorderLayout(0, 0));
                        {
                            JPanel panel_6 = new JPanel();
                            panel_5.add(panel_6, BorderLayout.NORTH);
                            panel_6.setLayout(new BorderLayout(0, 0));
                        }
                        {
                            JPanel panel_1 = new JPanel();
                            panel_5.add(panel_1, BorderLayout.CENTER);
                            panel_1.setLayout(new BorderLayout(0, 0));
                            {
                                JPanel panel_2 = new JPanel();
                                panel_1.add(panel_2, BorderLayout.NORTH);
                                panel_2.setLayout(new BorderLayout(0, 0));
                                {
                                    JLabel lblMean = new JLabel("Mean:");
                                    panel_2.add(lblMean, BorderLayout.WEST);
                                }
                                {
                                    textFieldMean = new JTextField();
                                    panel_2.add(textFieldMean, BorderLayout.CENTER);
                                    textFieldMean.setColumns(10);
                                }
                                {
                                    JPanel panel_5_1 = new JPanel();
                                    panel_2.add(panel_5_1, BorderLayout.NORTH);
                                    panel_5_1.setLayout(new BorderLayout(0, 0));
                                    {
                                        JPanel panel_6 = new JPanel();
                                        panel_5_1.add(panel_6, BorderLayout.NORTH);
                                        panel_6.setLayout(new BorderLayout(0, 0));
                                        {
                                            JLabel lblN = new JLabel("n:");
                                            panel_6.add(lblN, BorderLayout.WEST);
                                        }
                                        {
                                            textFieldN = new JTextField();
                                            panel_6.add(textFieldN, BorderLayout.CENTER);
                                            textFieldN.setColumns(10);
                                        }
                                    }
                                }
                            }
                            {
                                JPanel panel_2 = new JPanel();
                                panel_1.add(panel_2, BorderLayout.CENTER);
                                panel_2.setLayout(new BorderLayout(0, 0));
                                {
                                    JPanel panel_4 = new JPanel();
                                    panel_2.add(panel_4, BorderLayout.NORTH);
                                    panel_4.setLayout(new BorderLayout(0, 0));
                                    {
                                        JLabel lblVarpop = new JLabel("VarPop:");
                                        panel_4.add(lblVarpop, BorderLayout.WEST);
                                    }
                                    {
                                        textFieldVarP = new JTextField();
                                        panel_4.add(textFieldVarP, BorderLayout.CENTER);
									}
								}
								{
									JPanel panel_4 = new JPanel();
									panel_2.add(panel_4, BorderLayout.CENTER);
									panel_4.setLayout(new BorderLayout(0, 0));
									{
										JPanel panel_6 = new JPanel();
										panel_4.add(panel_6, BorderLayout.NORTH);
										panel_6.setLayout(new BorderLayout(0, 0));
										{
											JLabel lblVarsample = new JLabel("VarSample:");
											panel_6.add(lblVarsample, BorderLayout.WEST);
										}
										{
											textFieldVarS = new JTextField();
											panel_6.add(textFieldVarS, BorderLayout.CENTER);
											textFieldVarS.setColumns(10);
										}
									}
									{
										JPanel panel_5_1 = new JPanel();
										panel_4.add(panel_5_1, BorderLayout.CENTER);
										panel_5_1.setLayout(new BorderLayout(0, 0));
										{
											JPanel panel_6 = new JPanel();
											panel_5_1.add(panel_6, BorderLayout.NORTH);
											panel_6.setLayout(new BorderLayout(0, 0));
											{
												textFieldStDevS = new JTextField();
												panel_6.add(textFieldStDevS, BorderLayout.CENTER);
												textFieldStDevS.setColumns(10);
											}
											{
												JLabel lblStdevsample = new JLabel("StDevSample:");
												panel_6.add(lblStdevsample, BorderLayout.WEST);
											}
										}
										{
											JPanel panel_6 = new JPanel();
											panel_5_1.add(panel_6, BorderLayout.CENTER);
											panel_6.setLayout(new BorderLayout(0, 0));
											{
												JPanel panel_7 = new JPanel();
												panel_6.add(panel_7, BorderLayout.NORTH);
												panel_7.setLayout(new BorderLayout(0, 0));
												{
													textFieldStDevP = new JTextField();
													panel_7.add(textFieldStDevP, BorderLayout.CENTER);
													textFieldStDevP.setColumns(10);
												}
												{
													JLabel lblStdevpop = new JLabel("StDevPop:");
													panel_7.add(lblStdevpop, BorderLayout.WEST);
												}
											}
											{
												JPanel panel_7 = new JPanel();
												panel_6.add(panel_7, BorderLayout.CENTER);
												panel_7.setLayout(new BorderLayout(0, 0));
												{
													JPanel panel_8 = new JPanel();
													panel_7.add(panel_8);
													panel_8.setLayout(new BorderLayout(0, 0));
												}
												{
													JPanel panel_8 = new JPanel();
													panel_7.add(panel_8);
													panel_8.setLayout(new BorderLayout(0, 0));
													{
														JPanel panel_9 = new JPanel();
														panel_8.add(panel_9, BorderLayout.NORTH);
														panel_9.setLayout(new BorderLayout(0, 0));
														{
															JLabel lblRange = new JLabel("Range:");
															panel_9.add(lblRange, BorderLayout.WEST);
														}
														{
															textFieldRange = new JTextField();
															panel_9.add(textFieldRange, BorderLayout.CENTER);
															textFieldRange.setColumns(10);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					calcBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							saveColumnplot();
						}
					});
				}
			}
		}
	//	resetListModels();
	}
	
	
	
	public void saveColumnplot(){
		    int output = functionComboBox.getSelectedIndex();
			if (output == 0) { JOptionPane.showMessageDialog(null,
				    "You must choose an output column for your column plot.",
				    "Choose an output column",
				    JOptionPane.ERROR_MESSAGE);
					return;
			}
			textFieldN.setText(""+GrafStats.getN(gSess.getData().getColumnValues(functionComboBox.getSelectedIndex())));
			textFieldMin.setText(""+GrafStats.getMin(gSess.getData().getColumnValues(functionComboBox.getSelectedIndex())));
			textFieldQ1.setText(""+GrafStats.getQ1(gSess.getData().getColumnValues(functionComboBox.getSelectedIndex())));
			textFieldMedian.setText(""+GrafStats.getMedian(gSess.getData().getColumnValues(functionComboBox.getSelectedIndex())));
			textFieldQ3.setText(""+GrafStats.getQ3(gSess.getData().getColumnValues(functionComboBox.getSelectedIndex())));
			textFieldMax.setText(""+GrafStats.getMax(gSess.getData().getColumnValues(functionComboBox.getSelectedIndex())));
			textFieldRange.setText(""+GrafStats.getRange(gSess.getData().getColumnValues(functionComboBox.getSelectedIndex())));
			textFieldMean.setText(""+GrafStats.getMean(gSess.getData().getColumnValues(functionComboBox.getSelectedIndex())));
			textFieldVarP.setText(""+GrafStats.getVarianceOfPopulation(gSess.getData().getColumnValues(functionComboBox.getSelectedIndex())));
			textFieldVarS.setText(""+GrafStats.getVarianceOfSample(gSess.getData().getColumnValues(functionComboBox.getSelectedIndex())));
			textFieldStDevP.setText(""+GrafStats.getStandardDeviationOfPopulation(gSess.getData().getColumnValues(functionComboBox.getSelectedIndex())));
			textFieldStDevS.setText(""+GrafStats.getStandardDeviationOfSample(gSess.getData().getColumnValues(functionComboBox.getSelectedIndex())));
			textFieldIQR.setText(""+GrafStats.getIQR(gSess.getData().getColumnValues(functionComboBox.getSelectedIndex())));
			double[] modes = GrafStats.getModes(gSess.getData().getColumnValues(functionComboBox.getSelectedIndex()));
			String modeString = "";
			for (double m: modes) 
				modeString = modeString+m+",";
			//modeString = modeString.substring(0,modeString.length() - 1);
			textFieldModes.setText(modeString);
	}
	
	
	public static void createInputDialog(GrafProg gs){
	    GrafStatsDialog statDialog = new GrafStatsDialog(gs);
            statDialog.setVisible(true); 
            statDialog.setModal(true); 
	   
	 
	}
	
	 //private void resetListModels(){
	    	//autoComboBox.setModel(new javax.swing.DefaultComboBoxModel(getFunctionListArray()));
	    	
	 //   }
		
			
	
	
	
			
}