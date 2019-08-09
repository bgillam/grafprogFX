


/**
 * PointPanel input panel for GrafObjects defined by points
 * 
 * @author (Bill Gillam) 
 * @version (1/12/2017)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class PointPanel extends JPanel
{
    private JLabel x1Label = new JLabel("x1:");
    private  JLabel x2Label = new JLabel("x2:");
    private JLabel y1Label = new JLabel("y1:");
    private JLabel y2Label = new JLabel("y2:");
    private JLabel hLabel = new JLabel("height:");
    private JLabel wLabel = new JLabel("width:");
    private JLabel rLabel = new JLabel("radius:");
    private JLabel fLabel = new JLabel("f(x)= ");
    private JLabel nLabel = new JLabel("n: ");
    
    private JTextField x1 = new JTextField();
    private JTextField x2 = new JTextField();
    private JTextField y1 = new JTextField();
    private JTextField y2 = new JTextField();
    private JTextField h = new JTextField();  //height
    private JTextField w = new JTextField();  //width
    private JTextField r = new JTextField();  //radius
    private JTextField functionTextField = new JTextField();
    private JTextField n = new JTextField();  //number of iterations
    //private JButton copyButton = new JButton("Select");
    private JPanel topPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JPanel leftPanel2 = new JPanel();
    private JPanel rightPanel2 = new JPanel();
    private JComboBox fComboBox = new JComboBox();
    private JLabel functionText = new JLabel("Choose f(x)");
    private ArrayList<GrafObject> tempList;
    //private GrafType gType;
    
    public static void main(String[] args){
    PointPanel gpp = new PointPanel(new ArrayList<GrafObject>());
         gpp.setVisible(true);
        JFrame jf = new JFrame("test");
        jf.setVisible(true);
        
        jf.add(gpp);
        jf.pack();
        //m.setModal();
    }
  
   
   
    /**
     * Constructor for objects of class PointPanel
     */
    public PointPanel(ArrayList<GrafObject> tempListPassed)
    {
        tempList = tempListPassed;
        //gType = gTypePassed;
        x1.setColumns(8);
        y1.setColumns(8);
        setBackground(new Color(220, 220, 220));
        setLayout(new BorderLayout());
        leftPanel.setLayout(new BorderLayout()); 
        rightPanel.setLayout(new BorderLayout()); 
        topPanel.setLayout(new BorderLayout()); 
        bottomPanel.setLayout(new BorderLayout());
        rightPanel2.setLayout(new BorderLayout());
        leftPanel2.setLayout(new BorderLayout());
        add(topPanel,BorderLayout.NORTH);
        add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
    }
    
    //loads first function String, if function is present
    public void initFx(){
       if (!(fComboBox.getItemCount()==0)) {
          // System.out.println("Item Count: "fComboBox.getItemCount());
           String fString = (String)fComboBox.getItemAt(0);
           fString = fString.substring(9);
       functionTextField.setText(fString) ;
       }
    }
    
    //ads function inout field
    public void addFx(){
        functionTextField.setColumns(20);
        topPanel.add(fLabel, BorderLayout.WEST);
        topPanel.add(functionTextField, BorderLayout.EAST);
    }
   
     //sets up the function combobox
    public void setupFunctionChooser(){
            leftPanel.add(functionText, BorderLayout.WEST);
            fComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafFunction.getPlotList(tempList, GrafDeletePanel.indexPlots(tempList, GrafType.FUNCTION), GrafType.FUNCTION)));
            leftPanel.add(fComboBox, BorderLayout.CENTER);
            
            fComboBox.addItemListener(new ItemListener(){
                public void itemStateChanged(ItemEvent ie){
                    functionTextField.setText(((String)fComboBox.getSelectedItem()).substring(4));
                }
            });
            
            topPanel.add(leftPanel, BorderLayout.NORTH);
            topPanel.add(fLabel, BorderLayout.WEST);
            functionTextField.setEditable(false);
            topPanel.add(functionTextField,BorderLayout.CENTER);
            x1.setColumns(8);
            leftPanel2.add(x1Label, BorderLayout.WEST);
            leftPanel2.add(x1, BorderLayout.CENTER);
            bottomPanel.add(leftPanel2, BorderLayout.WEST);
    }

    //Input for a point
    public void addX1Y1(){
        leftPanel.add(x1Label, BorderLayout.WEST);
        leftPanel.add(x1, BorderLayout.CENTER);
        rightPanel.add(y1Label,BorderLayout.WEST);
        rightPanel.add(y1, BorderLayout.CENTER);
        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(rightPanel, BorderLayout.EAST);
    }
    
    //input for a second point
    public void addX2Y2(){
        x2.setColumns(8);
        y2.setColumns(8);
        leftPanel2.add(x2Label, BorderLayout.WEST);
        leftPanel2.add(x2, BorderLayout.CENTER);
        rightPanel2.add(y2Label, BorderLayout.WEST);
        rightPanel2.add(y2, BorderLayout.CENTER);
        bottomPanel.add(leftPanel2, BorderLayout.WEST);
        bottomPanel.add(rightPanel2, BorderLayout.EAST);  
    }
    
    //input for width and height
    public void addWH(){
        w.setColumns(8);
        h.setColumns(8);
        leftPanel2.add(wLabel, BorderLayout.WEST);
        leftPanel2.add(w, BorderLayout.CENTER);
        rightPanel2.add(hLabel, BorderLayout.WEST);
        rightPanel2.add(h, BorderLayout.CENTER);
        bottomPanel.add(leftPanel2, BorderLayout.WEST);
        bottomPanel.add(rightPanel2, BorderLayout.EAST);
    }
    
    //input for radius
    public void addR(){
        r.setColumns(8);
        bottomPanel.add(rLabel,BorderLayout.WEST);
        bottomPanel.add(r, BorderLayout.EAST);
    }
    
    //setters and getters
    public double getX1(){
        if (GrafInputHelpers.isDouble(x1.getText()))
            return Double.parseDouble(x1.getText());
        else
        return Double.NaN;
    }
    
    public void setX1(double x){
        x1.setText(x+"");
    }
    public void blankX1(){
         x1.setText("");
    }
    
    public JTextField getX1JText(){
        return x1;
    }
    
    public JLabel getX1Label(){
        return x1Label;
    }
    
    public double getY1(){
       if (GrafInputHelpers.isDouble(y1.getText()))
            return Double.parseDouble(y1.getText());
        else
        return Double.NaN;
    }
     public void setY1(double y){
        y1.setText(y+"");
    }
    public void blankY1(){
         y1.setText("");
    }
    public double getX2(){
       if (GrafInputHelpers.isDouble(x2.getText()))
            return Double.parseDouble(x2.getText());
        else
        return Double.NaN;
    }
    
    public JTextField getX2JText(){
        return x2;
    }
    
    public JLabel getX2Label(){
        return x2Label;
    }
    
    public void setX2(double x){
        x2.setText(x+"");
    }
     public void blankX2(){
         x2.setText("");
    }
    public double getY2(){
       if (GrafInputHelpers.isDouble(y2.getText()))
            return Double.parseDouble(y2.getText());
        else
        return Double.NaN;
    }
      public void setY2(double y){
        y2.setText(y+"");
    }
     public void blankY2(){
         y2.setText("");
    }
    public double getW(){
        if (GrafInputHelpers.isDouble(w.getText()))
            return Double.parseDouble(w.getText());
        else
        return Double.NaN;
    }
    public void setW(double width){
        w.setText(width+"");
    }
     public void blankW(){
         w.setText("");
    }
    public double getH(){
        if (GrafInputHelpers.isDouble(h.getText()))
            return Double.parseDouble(h.getText());
        else
        return Double.NaN;
    }
    public void setH(double height){
        h.setText(height+"");
    }
     public void blankH(){
         h.setText("");
    }
    public double getR(){
        if (GrafInputHelpers.isDouble(r.getText()))
            return Double.parseDouble(r.getText());
        else
        return Double.NaN;
    }
    public void setR(double radius){
        r.setText(radius+"");
    }
     public void blankR(){
         r.setText("");
    }
    public String getF(){return functionTextField.getText(); }
    public void blankF(){
        functionTextField.setText("");
    }
    public void setF(String s){
        functionTextField.setText(s);
    }
    public String getNString(){
              return n.getText();
            }
    public Integer getN(){
        if (GrafInputHelpers.isInt(n.getText()))
            return Integer.parseInt(n.getText());
        else
        return null;
    }
    
    public JTextField getNJText(){
        return n;
    
    }
    
    public JLabel getNLabel(){
        return nLabel;
    }
    
    public void setN(int num){
        n.setText(num+"");
    }
     public void blankN(){
         n.setText("");
    }
    public double getDx(){
        if (GrafInputHelpers.isDouble(n.getText()))
            return Double.parseDouble(n.getText());
        else
        return Double.NaN;
    }
    public void setDx(double num){
        n.setText(num+"");
    }
     public void blankDx(){
         n.setText("");
    }
    
    
    
    public  JPanel getRightPanel(){
        return rightPanel;
    }
    
    public  JPanel getRightPanel2(){
        return rightPanel2;
    }
    
    public JPanel getBottomPanel(){
        return bottomPanel;
        
    }
    
    
}   
