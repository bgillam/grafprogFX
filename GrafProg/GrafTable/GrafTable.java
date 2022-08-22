package GrafProg.GrafTable;//
//GrafProg.GrafProg.GrafProg.GrafTable.GrafTable  for GrafProg.GrafProg Project
// Spreadsheet for data used for stat
// calculations and graphs
// @author Bill Gillam
// 2/25/15
// Table/spreadsheet object for data input
//

import GrafProg.CalcStats.GrafStats;
import GrafProg.GrafProg;
import GrafProg.GrafUtils.ClipboardHandler;
import GrafProg.GrafUtils.GrafInputHelpers;
import GrafProg.IPasteable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Optional;

//class header
public class GrafTable<IPasteAble> implements KeyListener, IPasteable  //ActionListener, KeyListener //extends JDialog implements ActionListener, KeyListener
 {
    private static final long serialVersionUID = 1L;
    //instance variables
    private JPanel      dataPanel;
    private JTable      table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private GrafProg gSess;   //owner
    private ClipboardHandler clipper = new ClipboardHandler();
    

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

        TableHeaderActions.labelHeaders(this);
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
      
        

    void numberTheRows(){
    int rows = getNumRows();
    for (int i = 1; i <= rows; i++)
        setCellValueInteger(i , 0, i );
    }

   void refreshTable(){
       TableUI.getTableStage().hide();
       TableUI.getTableStage().show();
   }

    //change the table dimensions
    void resizeData(){
      String[] oldHeaders = TableHeaderActions.getHeaderArray(this);
        for (String oldHeader : oldHeaders) System.out.println("-" + oldHeader);
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
                if (GrafInputHelpers.isAnInteger(inputValue)) setNumCols(Integer.parseInt(inputValue));
                else setTableMessage("Bad integer format for columns");
                //return new Pair<>(myRows, myCols);
            }
            return null;
        });
        Optional<Pair<Integer, Integer>> result = dataSizeDialog.showAndWait();
        model.setColumnCount(model.getColumnCount());
        model.setRowCount(model.getRowCount());
        TableHeaderActions.restoreHeaders(oldHeaders, this);
        numberTheRows();

    }


   //   public getters and setters
   public int getNumRows(){
        return table.getRowCount();
    }
    private void setNumRows(int rows){
        model.setRowCount(rows);
    }
    
    int getNumCols(){
        return table.getColumnCount()-1;
    }
    
    private void setNumCols(int colNum){
        model.setColumnCount(colNum+1);
    }
    
    /*public double[] getRowValues(int row){
        int cols = getNumCols();
        double[] returnStringArray = new double[cols];
        for (int i = 0; i < cols; i++){
            try{
                returnStringArray[i] = (double)model.getValueAt(row, i);
            }catch (NumberFormatException e) {setTableMessage("Bad number format in cell "+i+".");}
        }
        return returnStringArray;
    }*/
    

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

    ClipboardHandler getClipper(){
         return clipper;
    }

    public JTable getTable(){
         return table;
     }

    public JPanel getDataPanel(){ return dataPanel; }

    void setCellValueDouble(int row, int col, double value){
           model.setValueAt(value, row-1,  col);
    }
    
    private void setCellValueInteger(int row, int col, int value){
       model.setValueAt(value, row-1,  col); //we are using a starting row of 1 vs zero
   }
    
    void setCellValueNull(int row, int col){
       model.setValueAt(null, row-1,  col); //we are using a starting row of 1 vs zero
    }

     void setHeaderString(int c, String s){
         table.getColumnModel().getColumn(c).setHeaderValue(s);
     }
     String getHeaderString(int c){
         return (String)table.getColumnModel().getColumn(c).getHeaderValue();
     }


    // Key Handling override for paste methods******************************
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            //System.out.println("ctr+V");
            TableEditActions.pasteValues(this);
        }else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_X) {
            //System.out.println("ctr+V");
            TableEditActions.cutValues(this);
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

    void setTableMessage(String message){
        TableUI.getTableController().setTableMessage(message);
    }

    DefaultTableModel getModel(){
        return model;
    }

    public void cut(){TableEditActions.cutValues(this);}
    public void copy(){TableEditActions.copyValues(this);}
    public void paste(){TableEditActions.pasteValues(this);}


//mainfor testing
    public static void main(String[] args){
         GrafTable table = new GrafTable(10,10);
     }
}




