/**************************************** 
*  FontDialog  							*
*  @author Bill Gillam                  *
*  2/25/15                              *
*****************************************/
/**
* Dialog for choosing a font 
 */
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;


//Class FontDialog
public class FontDialog extends JDialog {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//instance variables - most declared here to ease implementation of listeners
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JCheckBox chckbxPlain;
	private JCheckBox chckbxBold;
	private JCheckBox chckbxItalic;
	private JCheckBox chckbxBoldItalic;
	private Font chosenFont; 
	private Font passedFont;
	private Font[] fonts;
	private JList fontList;
	private JLabel lblFont;
	private DefaultListModel listModel;
	private int fontSize;
	private JList sizeList;
	private JTextField textFieldSize;
	private boolean cancelFlag;
	

	/**
	 * Create the dialog. Most of this code generated by Window Maker
	 */
	public FontDialog(Font currentFont) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		cancelFlag = false;
		passedFont = currentFont;
		chosenFont = currentFont;
		if (chosenFont == null) chosenFont = UIManager.getDefaults().getFont("TabbedPane.font");
		fontSize = chosenFont.getSize();
		setModal(true);
		setTitle("Font");
		setBounds(100, 100, 500, 201);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		fonts = e.getAllFonts(); 
		setVisible(false);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.WEST);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					listModel = new DefaultListModel();
					for (Font f: fonts)
						listModel.addElement(f.getFontName());
					fontList = new JList(listModel);
					fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(fontList);
					//fontList.setModel(new javax.swing.DefaultComboBoxModel(fontName));
				}
				{
					lblFont = new JLabel("Font:"+chosenFont.getFontName());
					scrollPane.setColumnHeaderView(lblFont);
				}
			}
			fontList.addListSelectionListener(new ListSelectionListener() {
		        public void valueChanged(ListSelectionEvent event) {
		        	chosenFont = fonts[fontList.getSelectedIndex()];
		        	refreshFont();
		        }
		    });
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.NORTH);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					chckbxPlain = new JCheckBox("Plain");
					chckbxPlain.setSelected(true);
					panel_1.add(chckbxPlain, BorderLayout.NORTH);
					chckbxPlain.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if (chckbxPlain.isSelected()) {
								chckbxBold.setSelected(false);
								chckbxItalic.setSelected(false);
								chckbxBoldItalic.setSelected(false);
								refreshFont();
							}
						}
					});
				}
				{
					chckbxBold = new JCheckBox("Bold");
					panel_1.add(chckbxBold, BorderLayout.CENTER);
					chckbxBold.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if (chckbxBold.isSelected()) {
								chckbxPlain.setSelected(false);
								chckbxItalic.setSelected(false);
								chckbxBoldItalic.setSelected(false);
								refreshFont();
							}
						}
					});
				}
				{
					JPanel panel_2 = new JPanel();
					panel_1.add(panel_2, BorderLayout.SOUTH);
					panel_2.setLayout(new BorderLayout(0, 0));
					{
						chckbxItalic = new JCheckBox("Italic");
						panel_2.add(chckbxItalic, BorderLayout.NORTH);
						chckbxItalic.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								if (chckbxItalic.isSelected()) {
									chckbxBold.setSelected(false);
									chckbxPlain.setSelected(false);
									chckbxBoldItalic.setSelected(false);
									refreshFont();
								}
							}
						});
					}
					{
						chckbxBoldItalic = new JCheckBox("Bold+Italic");
						panel_2.add(chckbxBoldItalic, BorderLayout.SOUTH);
						chckbxBoldItalic.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								if (chckbxBoldItalic.isSelected()) {
									chckbxBold.setSelected(false);
									chckbxItalic.setSelected(false);
									chckbxPlain.setSelected(false);
									refreshFont();
								}
							}
						});
					}
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.SOUTH);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.EAST);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.NORTH);
				{
					sizeList = new JList();
					sizeList.setModel(new AbstractListModel() {
						String[] values = new String[] {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "32", "36", "48", "72"};
						public int getSize() {
							return values.length;
						}
						public Object getElementAt(int index) {
							return values[index];
						}
					});
					sizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(sizeList);
				}
				{
					textFieldSize = new JTextField(""+fontSize);
					scrollPane.setColumnHeaderView(textFieldSize);
					textFieldSize.addActionListener(new ActionListener(){
		                public void actionPerformed(ActionEvent e){
		                	if (GrafInputHelpers.isAnInteger(textFieldSize.getText())) refreshFont();
		                	
		            }});
					
				}
			}
		}
		sizeList.addListSelectionListener(new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent event) {
	        	fontSize = Integer.parseInt((String)sizeList.getSelectedValue());
	        	refreshFont();
	        }
	    });
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblPreview = new JLabel("Preview:");
				buttonPane.add(lblPreview, BorderLayout.WEST);
			}
			{
				textField = new JTextField();
				buttonPane.add(textField, BorderLayout.CENTER);
				textField.setColumns(10);
				textField.setText("Sample Text");
			}
			{
				JPanel panel = new JPanel();
				buttonPane.add(panel, BorderLayout.EAST);
				{
					JButton okButton = new JButton("OK");
					panel.add(okButton);
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
					});
					okButton.setActionCommand("OK");
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("Cancel");
					panel.add(cancelButton);
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							cancelFlag = true;
							dispose();
						}
					});
					cancelButton.setActionCommand("Cancel");
				}
			}
		}
	}
	
	//Rewrite the dialog text field in the proper font
	private void refreshFont(){
		int fontStyle = 1;
		if (chckbxPlain.isSelected()) fontStyle = Font.PLAIN;
		else if (chckbxBold.isSelected()) fontStyle = Font.BOLD;
		else if (chckbxItalic.isSelected()) fontStyle = Font.ITALIC;
		else if (chckbxBoldItalic.isSelected()) fontStyle = Font.ITALIC | Font.BOLD;
		chosenFont = new Font(chosenFont.getName(), fontStyle, fontSize);
    	textField.setFont(chosenFont);
      	textField.setText("--- Sample Text ---");
    	lblFont.setText(chosenFont.getFontName());
	}
	  
	 /*showFontDialog
	  * return the chosen font
	  */
	  public Font showFontDialog(){
	      setVisible(true);
	      setModal(true);
	      if (cancelFlag) return passedFont;
	      return chosenFont;
	    }

	   public static java.awt.Font fxFontToAwtFont(javafx.scene.text.Font fxFont){
	  		String style = fxFont.getStyle();
	  		int fontStyle;
	  		if (style.equals("Regular")) fontStyle = 0;
	  		else if (style.equals("Bold")) fontStyle = 1;
	  		else if (style.equals("Italic")) fontStyle = 2;
	  		else if (style.equals("Bold Italic")) fontStyle = 3;
	  		else fontStyle = 0;
	  		java.awt.Font awtFont = new Font(fxFont.getName(), fontStyle, (int)fxFont.getSize());
	  		return awtFont;
		}

		public static javafx.scene.text.Font awtFontToFxFont(java.awt.Font awtFont){
	  	int style = awtFont.getStyle();
	  	FontWeight fw;
	  	FontPosture fp;
	  	switch (style){
	  		case 0: {
					fw = FontWeight.NORMAL;
					fp = FontPosture.REGULAR;
					break;
			}
			case 1: {
				fw = FontWeight.BOLD;
				fp = FontPosture.REGULAR;
				break;
			}
			case 2: {
				fw = FontWeight.NORMAL;
				fp = FontPosture.ITALIC;
				break;
			}
			case 3: {
				fw = FontWeight.BOLD;
				fp = FontPosture.ITALIC;
				break;
			}
			default: {
				fw = FontWeight.NORMAL;
				fp = FontPosture.REGULAR;
				break;
			}
			}

	  	return javafx.scene.text.Font.font(awtFont.getFamily(), fw, fp , awtFont.getSize());
		}

		public static void main(String[] args){
			javafx.scene.text.Font fxFont = new javafx.scene.text.Font(12);
			fxFont = javafx.scene.text.Font.font(fxFont.getFamily(),
					FontWeight.BOLD, FontPosture.ITALIC,  fxFont.getSize());
			String style = fxFont.getStyle();
			System.out.println(style);
			FontDialog fontDialog = new FontDialog(fxFontToAwtFont(fxFont));
			fontDialog.showFontDialog();
		}
}