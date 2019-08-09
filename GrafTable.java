/**************************************** 
*  GrafTable  for GrafProg Project 
*  Spreadsheet for data used for stat 
*  calculations and graphs *
*  @author Bill Gillam                  *
*  2/25/15    *
*****************************************/ 
/** 
 * Table/spreadsheet object for data input 
 * 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

//class header
class GrafTable extends JDialog implements ActionListener, KeyListener
 {
    private static final long serialVersionUID = 1L;
    //instance variables
    private JPanel      dataPanel;
    private JTable      table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private GrafProg gSess;   //owner
    private ClipboardHandler clipper = new ClipboardHandler();
    
    //dialogs for data generation
    private HeaderDialog headerDialog;
    private TableFunctionDialog tfd;
    private RandomDialog rd;
    private RecursiveDialog recDialog;
     
    
    // Constructor of Table
    public GrafTable(GrafProg sess, int row, int col)
    {   gSess = sess;
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle( "Data" );
        setSize( 600, 400);
        setLocationRelativeTo(sess);
        
        // Create a panel to hold all other components
        dataPanel = new JPanel();
        dataPanel.setLayout( new BorderLayout() );
        getContentPane().add(dataPanel);
        
        // Make column 0 non-editable 
        model = new DefaultTableModel()
        {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column)
            {
               return column > 0;
            }
        };
        
        // Create a new table instance
        table = new JTable(model);  
        model.setRowCount(row);
        model.setColumnCount(col+1);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        // Add the table to a scrolling pane
        scrollPane = new JScrollPane( table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dataPanel.add( scrollPane, BorderLayout.CENTER );
        
        setJMenuBar(GrafTableMenu.createMenu(this));//create menu including listeners
        add(dataPanel);
        setModal(false);
        labelHeaders();
        numberTheRows();
        
        //listeners
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                table.setColumnSelectionAllowed(true);
                table.setRowSelectionAllowed(false);
                table.setColumnSelectionInterval(col,col);
            }
        });
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
                if (table.isColumnSelected(0)){
                    table.setRowSelectionAllowed(true);
                    table.setColumnSelectionAllowed(false); 
                }else{
                    table.setColumnSelectionAllowed(true);
                    table.setRowSelectionAllowed(true);
               }
            }
      });
        
      table.addKeyListener(this);       
    }
      
        
    //Header and row index procedures 
    private void labelHeaders(){
        setHeaderString(0,"");
        //table.getColumnModel().getColumn(0).setHeaderValue("");
        int cols = getNumCols();
        for (int i = 1; i <= cols; i++) // have adjusted our column count by 1. 
            setHeaderString(i,"Data"+i);
            
    }
    
    public void numberTheRows(){
    int rows = getNumRows();
    for (int i = 1; i <= rows; i++)
        setCellValueInteger(i , 0, i );
        
    }
    
        
   //change the table dimensions
   private void resizeData(){
      DataSizeDialog dataDialog = new DataSizeDialog(new JFrame(), getNumRows(), getNumCols());
      Point xy = dataDialog.showDataSizeDialog();
      String [] oldHeaders = saveHeaders();
      try{
          setNumRows((int)xy.getX());
          setNumCols((int)xy.getY());
          restoreHeaders(oldHeaders);
          numberTheRows();
         } catch (NumberFormatException e){
          gSess.setMessage1("bad number format for row or column count");
      }
     
      
       
  }
   
   public String[] saveHeaders(){
       String[] headerHolder = new String[getNumCols()+1];
       int cols = getNumCols();
       for (int i = 1; i<= cols; i++) {           // don't use index 0 just to keep indexes concurrent with Table calls
               headerHolder[i] = getHeaderString(i);
            
       }
       return headerHolder;
   }
    
    public String[] getHeaderStringArray(){
       String[] headerHolder = new String[getNumCols()+1];
       int cols = getNumCols();
       for (int i = 1; i<= cols; i++) {           // don't use index 0 just to keep indexes concurrent with Table calls
               headerHolder[i] = getHeaderString(i);
            
       }
       return headerHolder;
   }
   
   private void restoreHeaders(String[] headerHolder){
       int len;
       int colCount = table.getColumnCount();
       int oldHeaderLength =  headerHolder.length;
       if (colCount > oldHeaderLength){ 
           len = oldHeaderLength;
           for (int i = len; i < colCount; i++ )
               setHeaderString(i, "Data"+i);
       }
       else len = colCount;
       setHeaderString(0,"");
       for (int i=1; i < len; i++)
           setHeaderString(i, headerHolder[i]);
   }
      
    //use column zero to number the rows
    //private void numberTheRows(){
    //int rows = getNumRows();
    //for (int i = 1; i <= rows; i++)
    //    setCellValueInteger(i , 0, i );
    //    
    //}
         
   //change the table dimensions
   //private void resizeData(){
   //   DataSizeDialog dataDialog = new DataSizeDialog(new JFrame(), getNumRows(), getNumCols());
   //   Point xy = dataDialog.showDataSizeDialog();
   //   String [] oldHeaders = saveHeaders();
   //   try{
   //       setNumRows((int)xy.getX());
   //       setNumCols((int)xy.getY());
   //       restoreHeaders(oldHeaders);
   //       numberTheRows();
   //   } catch (NumberFormatException e){
   //       gSess.setMessage1("bad number format for row or column count");
   //   }
   // }
   
   
   //row and column manipulations
   public void deleteRows(){
       int rowCount = table.getRowCount();
       ArrayList<Integer> selected = new ArrayList<Integer>();
       int deleteCount = 0;
       for (int i = 0; i< rowCount; i++)
           if (table.isRowSelected(i)){
               selected.add(i);
               deleteCount++;
           }
       if (selected.size() == 0) { gSess.setMessage1("No rows selected!"); return;}
       String msgString = "Delete rows ";
       for (int i = 0; i < deleteCount; i++)
            msgString = msgString +(selected.get(i)+1)+" ";
       msgString = msgString+"?";
       int dialogButton = JOptionPane.YES_NO_OPTION;
       int dialogResult = JOptionPane.showConfirmDialog(null, msgString, "Delete?" , dialogButton);
       
       if (dialogResult == JOptionPane.YES_OPTION){
            for (int i = selected.size()-1; i >= 0; i--){
                model.removeRow(selected.get(i));
            }
            numberTheRows();
        }       
              
   }
       
   public void deleteColumns(){
       ArrayList<Integer> selected = getSelectedColumns();
       int colCount = table.getColumnCount();
       if (selected.size() == 0) { gSess.setMessage1("No columns selected!"); return;}
       String msgString = "Delete columns ";
       for (int i = 0; i < selected.size(); i++)
            msgString = msgString +(selected.get(i))+" ";
       msgString = msgString+"?";
       int dialogButton = JOptionPane.YES_NO_OPTION;
       int dialogResult = JOptionPane.showConfirmDialog(null, msgString, "Delete?" , dialogButton);
       if (dialogResult == JOptionPane.YES_OPTION){
            for (int i = selected.size()-1; i >= 0; i--){
                removeColumn(selected.get(i));
            }
            //numberTheRows();
        }   
   }
   
   private void removeColumn(int toBeRemoved){  
        
        for (int currentColumn = toBeRemoved;  currentColumn<table.getColumnCount()-1;  currentColumn++)
            for (int currentRow = 0; currentRow< table.getRowCount(); currentRow++){
                model.setValueAt(table.getValueAt(currentRow, currentColumn+1), currentRow, currentColumn);
                setHeaderString(currentColumn, getHeaderString(currentColumn+1));
            }
        String[] headerHolder = saveHeaders();
        model.setColumnCount(model.getColumnCount()-1);
        restoreHeaders(headerHolder);
          //copy next column into this column and repeat until end of model
          //remove the last column
   }
   
   private ArrayList<Integer> getSelectedColumns(){
       int colCount = table.getColumnCount();
       ArrayList<Integer> selected = new ArrayList<Integer>();
       for (int i = 0; i< colCount; i++)
           if (table.isColumnSelected(i))
               selected.add(i);
       return selected;
   }
   
   private int getFirstSelectedColumn(){
       int colCount = table.getColumnCount();
       for (int i = 0; i< colCount; i++)
        if (table.isColumnSelected(i)) 
           return i;
       return 0;
   }
   
   private void clearColumns(){
       ArrayList<Integer> selected = getSelectedColumns();
       if (selected.size() == 0) { gSess.setMessage1("No columns selected!"); return;}
       String msgString = "Clear columns ";
       for (int i = 0; i < selected.size(); i++)
            msgString = msgString +(selected.get(i))+" ";
       msgString = msgString+"?";
       int dialogButton = JOptionPane.YES_NO_OPTION;
       int dialogResult = JOptionPane.showConfirmDialog(null, msgString, "Clear Columns?" , dialogButton);
       if (dialogResult == JOptionPane.YES_OPTION){
            for (int i = selected.size()-1; i >= 0; i--){
                for (int row = 1; row < getNumRows(); row++)
                    setCellValueNull(row , selected.get(i));
        
            }
            //numberTheRows();
        }   
   }


   private void sortColumns(boolean ascending){
       ArrayList<Integer> selected = getSelectedColumns();
       if (selected.size() == 0) { gSess.setMessage1("No columns selected!"); return;}
       String msgString = "Sort columns";
       for (int i = 0; i < selected.size(); i++)
            msgString = msgString +(selected.get(i))+" ";
       msgString = msgString+"?";
       int dialogButton = JOptionPane.YES_NO_OPTION;
       int dialogResult = JOptionPane.showConfirmDialog(null, msgString, "Sort Columns?" , dialogButton);
       if (dialogResult == JOptionPane.YES_OPTION){
           int numRows = table.getRowCount(); 
           Double[] columnSorted = new Double[numRows];
           int col;
           for (int i = selected.size()-1; i >= 0; i--){
                col = selected.get(i);
                columnSorted = getColumnValues(col);
                Arrays.sort(columnSorted);
                int row;
                for (int r = 0; r < numRows; r++){
                    if (!ascending) row = (numRows-1) - r; else row = r;
                    //System.out.println("Row "+row+" col "+col+" value "+columnSorted[row]);
                    model.setValueAt(columnSorted[r], row, col); 
                }
            }
       }
   }
   
   private void zeroColumns(){
       ArrayList<Integer> selected = getSelectedColumns();
       if (selected.size() == 0) { gSess.setMessage1("No columns selected!"); return;}
       String msgString = "Zero columns ";
       for (int i = 0; i < selected.size(); i++)
            msgString = msgString +(selected.get(i))+" ";
       msgString = msgString+"?";
       int dialogButton = JOptionPane.YES_NO_OPTION;
       int dialogResult = JOptionPane.showConfirmDialog(null, msgString, "Zero Columns?" , dialogButton);
       if (dialogResult == JOptionPane.YES_OPTION){
            for (int i = selected.size()-1; i >= 0; i--){
                for (int row = 1; row < getNumRows(); row++)
                    setCellValueDouble(row , selected.get(i) , 0.0);
            }
            //numberTheRows();
        }   
   }

     private void randomColumnValues(){
        RandomDialog rd = new RandomDialog(this);
        rd.showDialog();
    }
    
    
    //cut and paste procedures   
    private void cutValues(){
        clipper.setClipboardContents(getSelectedData()); 
        deleteSelectedCells();
    }
    
    private void pasteValues(){
        String toParse = clipper.getClipboardContents();
        String item = "";
        int row =table.getSelectedRow();
        int col = table.getSelectedColumn();
        int firstColumn = col;
        int tIndex = -1;
        int nlIndex = -1;
        int index = -1;
        boolean down = false;
        String value = "";
        while (!toParse.equals("")){
            tIndex = toParse.indexOf('\t');
            nlIndex = toParse.indexOf('\n');
            if ((tIndex == -1) && (nlIndex == -1)) { 
                if ((row < getNumRows()) && (col <= getNumCols()))  table.setValueAt(toParse, row, col);
                break;
            }
            if (tIndex == -1) {index = nlIndex; down = true; }
            else if (nlIndex == -1) {
                index = tIndex; 
                down = false;
            } 
            else if (tIndex > nlIndex) { 
                index = nlIndex; 
                down = true; 
            }
            else { 
                index = tIndex; 
                            down = false;
            }
            value = toParse.substring(0, index);
            //System.out.println("row: "+row+" "+"col: "+col+" "+"index: "+index+" "+"string: "+"\""+toParse+"\" value:"+value);
            value = toParse.substring(0, index);
            if (value.equals("null")) value = null;
            if ((row < getNumRows()) && (col <= getNumCols()))  table.setValueAt(value , row, col);
            toParse = toParse.substring(index+1, toParse.length());
            if (down) {
                row++; 
                col = firstColumn; 
            }else col++;
            
        }
    }
    
    private void charPrint(String passed){
       for (int i = 0; i < passed.length(); i++)
           System.out.println((int)passed.charAt(i)+":"+passed.charAt(i));
       
   }
   
   private String getSelectedData(){
        String copied = "";
        int rows = getNumRows();
        int cols = getNumCols();
        boolean newRow = false;
        boolean firstRow = true;
        for (int currentRow = 0; currentRow < rows; currentRow++){
            for (int currentCol = 1; currentCol < cols ; currentCol++){
                if (table.isCellSelected(currentRow, currentCol)) {
                    if (newRow) { 
                        if (!firstRow) copied = copied + "\n"; else firstRow = false; 
                        newRow = false;
                    }
                    copied = copied + model.getValueAt(currentRow, currentCol); 
                    copied = copied +"\t";
                }
            }
            newRow = true;
        }
        return copied;
   }
    
    private void deleteSelectedCells(){
        int rows = getNumRows();
        int cols = getNumCols();
        for (int currentRow = 0; currentRow < rows; currentRow++){
            for (int currentCol = 1; currentCol < cols ; currentCol++){
                if (table.isCellSelected(currentRow, currentCol)) model.setValueAt(null, currentRow, currentCol);
            }
        }
    }

    //respond to menu commands
    public void actionPerformed(ActionEvent event)
   {  
        if (event.getActionCommand().equals("Done")) setVisible(false); 
        if (event.getActionCommand().equals("Resize")) resizeData();
        if (event.getActionCommand().equals("Delete Row")) deleteRows();
        if (event.getActionCommand().equals("Delete Column")) deleteColumns(); 
        if (event.getActionCommand().equals("Cut")) cutValues();
        if (event.getActionCommand().equals("Copy")) clipper.setClipboardContents(getSelectedData());  
        if (event.getActionCommand().equals("Paste"))  pasteValues();   
        if (event.getActionCommand().equals("Heading")){ 
            headerDialog = new HeaderDialog(this); 
            headerDialog.setVisible(true); 
            headerDialog.setModal(true);
        }
        if (event.getActionCommand().equals("Random")) { 
            rd = new RandomDialog(this);
            rd.showDialog();
        }
        if (event.getActionCommand().equals("Function")) { 
            tfd = new TableFunctionDialog(this); 
            tfd.showDialog();
        }  
        if (event.getActionCommand().equals("Recursion")) { 
            recDialog = new RecursiveDialog(this); 
            recDialog.showDialog();
        }  
        if (event.getActionCommand().equals("Clear Column")) {  clearColumns(); }
        if (event.getActionCommand().equals("Zero Column")) {  zeroColumns(); }  
        if (event.getActionCommand().equals("Ascending")) {  sortColumns(true); }  
        if (event.getActionCommand().equals("Descending")) { sortColumns(false); }  
        
   }
    
     
   //   public getters and setters
    public int getNumRows(){
        return table.getRowCount();
    }
    public void setNumRows(int rows){
        model.setRowCount(rows);
    }
    
    public int getNumCols(){
        return table.getColumnCount()-1;
    }
    
    public void setNumCols(int colNum){
        model.setColumnCount(colNum+1);
    }
    
    public double[] getRowValues(int row){
        int cols = getNumCols();
        double[] returnStringArray = new double[cols];
        for (int i = 0; i < cols; i++){
            try{
                returnStringArray[i] = (double)model.getValueAt(row, i);
            }catch (NumberFormatException e) {gSess.setMessage1("Bad number format in cell "+i+".");}
        }
        return returnStringArray;
    }
    
    public Double[] getColumnValues(int col){
        int rows = getNumRows();
        Double[] returnStringArray = new Double[rows];
        Object currentObject; 
        Class currentObjectClass;
        for (int i = 0; i < rows; i++){
            try{
                currentObject = model.getValueAt(i, col);
                currentObjectClass = model.getValueAt(i,  col).getClass();
                if (currentObject instanceof String) {
                    if (GrafInputHelpers.isDouble((String) currentObject)) 
                        returnStringArray[i] = Double.parseDouble((String)currentObject);
                    continue;
                }
                returnStringArray[i] = (double)model.getValueAt(i, col); 
            //}catch (NumberFormatException e) {gSess.setMessage1("Bad number format in cell "+i+".");
            }catch (ClassCastException e) { System.out.println("row "+i+" class cast exception! "+model.getValueAt(i,  col).getClass()); returnStringArray[i] = null;} 
            catch (NullPointerException e) {returnStringArray[i] = null; }
            catch (Exception e) {System.out.println("row "+" "+"some other exception!"); returnStringArray[i] = null;}
            
        }
        return returnStringArray;
    }
  
    
    public Double getCellValue(int row, int col){
        Object o = model.getValueAt(row-1, col);
        if (o == null) return null;
        try{
            return (double)o;
        }
        catch (ClassCastException e){
                try{
                    return (double)(int)o;
                }
                catch (ClassCastException e2){
                    try{
                        return Double.parseDouble((String)o);
                    }
                    catch (ClassCastException e3){
                        System.out.println(o.getClass());
                    }
                }
        }
        return null;
    }
    
    
    public void setCellValueDouble(int row, int col, double value){
           model.setValueAt(value, row-1,  col);
    }
    
    public void setCellValueInteger(int row, int col, int value){
       model.setValueAt(value, row-1,  col); //we are using a starting row of 1 vs zero
   }
    
    public void setCellValueNull(int row, int col){
       model.setValueAt(null, row-1,  col); //we are using a starting row of 1 vs zero
    }
    
    public void setColumnValues(int col, double a[]){
        for (int i = 1; i<=a.length; i++){
            setCellValueDouble(i, col, a[i]);
        }
    }
    
    public void setHeaderString(int c, String s){
        table.getColumnModel().getColumn(c).setHeaderValue(s);
    }
    
    public String getHeaderString(int c){
        return (String)table.getColumnModel().getColumn(c).getHeaderValue();
    }
    
    public String[] getHeaderArray(){
        String[] headerArray = new String[getNumCols()+1];
        for (int i = 0; i <= getNumCols(); i++)
                headerArray[i] = getHeaderString(i);
        return headerArray;
    }
    
    public void setHeaderArray(String[] headers){
        for (int i=0; i <= getNumCols(); i++)
            setHeaderString(i , headers[i]);
    }


    // Key Handling override for paste methods
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            //System.out.println("ctr+V");
            pasteValues();
        }else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_X) {
            //System.out.println("ctr+V");
            cutValues();
        }
        //System.out.println("Key Pressed "+e.getKeyCode());
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
         //TODO Auto-generated method stub
        
    }
}