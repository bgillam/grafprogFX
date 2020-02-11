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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

//class header
class GrafTable implements KeyListener //ActionListener, KeyListener //extends JDialog implements ActionListener, KeyListener
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
    private TableFunctionDialog tfd;

    // Constructor of Table
    public GrafTable(int row, int col)              //GrafStage sess, int row, int col)
    {
        // Create a panel to hold all other components
        dataPanel = new JPanel();
        dataPanel.setLayout( new BorderLayout() );

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
       // table.addKeyListener(this);
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

    public void editHeaders(){

        Dialog headerChangeDialog = new javafx.scene.control.Dialog();
        headerChangeDialog.setTitle("Edit Column Headers");
        headerChangeDialog.setHeaderText("Choose Header to Edit:");
        headerChangeDialog.setContentText("testcontenttext");

        TextField headerChangeTextField = new TextField("");
        headerChangeTextField.setVisible(false);

        ComboBox headComboBox = new ComboBox();
        headComboBox.getItems().clear();
        headComboBox.getItems().addAll(getHeaderArrayCdr());
        headComboBox.setValue("Header List");
        headComboBox.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null)
                    headerChangeTextField.setText(newValue.toString());
                headerChangeTextField.setVisible(true);
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.add(headComboBox, 0,0);
        gridPane.add(headerChangeTextField, 1,0);
        headerChangeDialog.getDialogPane().setContent(gridPane);

        ButtonType saveButton = new ButtonType("Save");
        headerChangeDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL, saveButton);
        boolean done =  false;
        while (!done) {
            Optional<ButtonType> result = headerChangeDialog.showAndWait();
            if (result.get().equals(ButtonType.OK)) {
                //if (headerTextField.isEditable())
                setHeaderString(headComboBox.getSelectionModel().getSelectedIndex() + 1, headerChangeTextField.getText());
               refreshTable();
                done = true;
            } else if (result.get().equals(saveButton)) {
                setHeaderString(headComboBox.getSelectionModel().getSelectedIndex() + 1, headerChangeTextField.getText());
                headComboBox.getItems().clear();
                headComboBox.getItems().addAll(getHeaderArrayCdr());
                headComboBox.setValue("Header List");
                headerChangeTextField.setVisible(false);
                refreshTable();
            }else if (result.get().equals(ButtonType.CANCEL)) done = true;
        }
   }
   private void refreshTable(){
       GrafProg.getTableStage().hide();
       GrafProg.getTableStage().show();
   }

    //change the table dimensions
    public void resizeData(){
      String[] oldHeaders = getHeaderArray();
      for (int i=0; i<oldHeaders.length; i++) System.out.println("-"+oldHeaders[i]);
      //DataSizeDialog dataDialog = new DataSizeDialog(new JFrame(), getNumRows(), getNumCols());
      javafx.scene.control.Dialog<Pair<Integer, Integer>> dataSizeDialog = new javafx.scene.control.Dialog<>();
        dataSizeDialog.setTitle("Resize Table Dimensions");
        dataSizeDialog.setHeaderText("Enter number of rows and columns");
        dataSizeDialog.setContentText("Save Changes?");
        dataSizeDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        javafx.scene.control.Label rowLabel = new javafx.scene.control.Label("#rows:");
        javafx.scene.control.Label colLabel = new javafx.scene.control.Label("#cols:");
        javafx.scene.control.TextField rowInput = new javafx.scene.control.TextField(Integer.toString(getNumRows()));
        javafx.scene.control.TextField colInput = new TextField(Integer.toString(getNumCols()));
        grid.add(rowLabel, 0, 0);
        grid.add(rowInput, 1, 0);
        grid.add(colLabel, 0, 1);
        grid.add(colInput, 1, 1);
        dataSizeDialog.getDialogPane().setContent(grid);
        dataSizeDialog.setResultConverter(choice -> {
            if (choice == ButtonType.OK) {
                String inputValue = rowInput.getText();
                if (GrafInputHelpers.isAnInteger(inputValue)) setNumRows(Integer.parseInt(inputValue));
                else setTableMessage("Bad integer format for rows.");
                inputValue = colInput.getText();
                if (GrafInputHelpers.isAnInteger(inputValue)) setNumCols(Integer.valueOf(inputValue));
                else setTableMessage("Bad integer format for columns");
                //return new Pair<>(myRows, myCols);
            }
            return null;
        });
        Optional<Pair<Integer, Integer>> result = dataSizeDialog.showAndWait();
        model.setColumnCount(model.getColumnCount());
        model.setRowCount(model.getRowCount());
        restoreHeaders(oldHeaders);
        numberTheRows();

    }

    
     public String[] getHeaderArray(){
         String[] headerArray = new String[getNumCols()+1];
         for (int i = 0; i <= getNumCols(); i++) {
             headerArray[i] = getHeaderString(i);
             //System.out.println(getHeaderString(i));
         }
         return headerArray;
     }

     public String[] getHeaderArrayCdr(){
         String[] headerArray = new String[getNumCols()];
         for (int i = 1; i <= getNumCols(); i++)
             headerArray[i-1] = getHeaderString(i);
         return headerArray;
     }

     public void setHeaderString(int c, String s){
         table.getColumnModel().getColumn(c).setHeaderValue(s);
     }
     public String getHeaderString(int c){
         return (String)table.getColumnModel().getColumn(c).getHeaderValue();
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
       if (selected.size() == 0) { setTableMessage("No rows selected!"); return;}
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
       if (selected.size() == 0) { setTableMessage("No columns selected!"); return;}
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
       }
   }
   
   private void removeColumn(int toBeRemoved){  
        
        for (int currentColumn = toBeRemoved;  currentColumn<table.getColumnCount()-1;  currentColumn++)
            for (int currentRow = 0; currentRow< table.getRowCount(); currentRow++){
                model.setValueAt(table.getValueAt(currentRow, currentColumn+1), currentRow, currentColumn);
                setHeaderString(currentColumn, getHeaderString(currentColumn+1));
            }
        String[] headerHolder = getHeaderArray();
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
   

   public void clearColumns(){
       ArrayList<Integer> selected = getSelectedColumns();
       if (selected.size() == 0) { setTableMessage("No columns selected!"); return;}
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
       }
   }


   public void sortColumns(boolean ascending){
       ArrayList<Integer> selected = getSelectedColumns();
       if (selected.size() == 0) { gSess.getTableController().setTableMessage("No columns selected!"); return;}
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
                    model.setValueAt(columnSorted[r], row, col);
                }
            }
       }
   }
   
   public void zeroColumns(){
       ArrayList<Integer> selected = getSelectedColumns();
       if (selected.size() == 0) { setTableMessage("No columns selected!"); return;}
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
       }
   }


    
    //cut and paste procedures   
    public void cutValues(){
        clipper.setClipboardContents(getSelectedData()); 
        deleteSelectedCells();
    }
    
    public void pasteValues(){
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
    

   
   public String getSelectedData(){
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
            }catch (NumberFormatException e) {setTableMessage("Bad number format in cell "+i+".");}
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
            //}catch (NumberFormatException e) {setTableMessage("Bad number format in cell "+i+".");
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
                    catch (NumberFormatException nfe){return null;}
                }
        }
        return null;
    }

    public ClipboardHandler getClipper(){
         return clipper;
    }

    public JTable getTable(){
         return table;
     }

    public JPanel getDataPanel(){ return dataPanel; }

    public void setCellValueDouble(int row, int col, double value){
           model.setValueAt(value, row-1,  col);
    }
    
    public void setCellValueInteger(int row, int col, int value){
       model.setValueAt(value, row-1,  col); //we are using a starting row of 1 vs zero
   }
    
    public void setCellValueNull(int row, int col){
       model.setValueAt(null, row-1,  col); //we are using a starting row of 1 vs zero
    }
    


   public Double getMin(Double[] values){
         values = getRidOfNulls(values);
         if (values.length == 0) return null;
         Double min = values[0];
         for(Double d:values){
            // System.out.println(d+" "+min);
             if (d < min) min=d;
         }
         return min;
   }

     public Double getMax(Double[] values){
         values = getRidOfNulls(values);
         if (values.length == 0) return null;
         Double max = values[0];
         for(Double d:values){
             if (d > max) max = d;
             //System.out.println(d+" "+max);
         }
         return max;
     }


    // Key Handling override for paste methods******************************
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
    public void keyReleased(KeyEvent e) {// TODO Auto-generated method stub
         }

    @Override
    public void keyTyped(KeyEvent e) {   //TODO Auto-generated method stub
         }
 //*************************************************************************


    public static Double[] getRidOfNulls(Double[] d){
         return GrafStats.getRidOfNulls(d);
    }

    private void setTableMessage(String message){
        gSess.getTableController().setTableMessage(message);
    }


//mainfor testing
    public static void main(String[] args){
         GrafTable table = new GrafTable(10,10);
     }
}

//written but not used:

/* public void setColumnValues(int col, double a[]){
        for (int i = 1; i<=a.length; i++){
            setCellValueDouble(i, col, a[i]);
        }
    }*/

/* private void charPrint(String passed){
       for (int i = 0; i < passed.length(); i++)
           System.out.println((int)passed.charAt(i)+":"+passed.charAt(i));

   }*/

//Saves Headers in String[] and returns
     /*public String[] createHeaderArray() {
         String[] headerHolder = new String[getNumCols() + 1];
         int cols = getNumCols();
         for (int i = 1; i <= cols; i++) {           // don't use index 0 just to keep indexes concurrent with Table calls
             headerHolder[i] = getHeaderString(i);
         }
         return headerHolder;
     }*/

      /*public void setHeaderArray(String[] headers){
         for (int i=0; i <= getNumCols(); i++)
             setHeaderString(i , headers[i]);
     }*/

      /* private int getFirstSelectedColumn(){
       int colCount = table.getColumnCount();
       for (int i = 0; i< colCount; i++)
        if (table.isColumnSelected(i))
           return i;
       return 0;
   }*/