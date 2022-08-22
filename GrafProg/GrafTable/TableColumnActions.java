package GrafProg.GrafTable;

import GrafProg.GrafUtils.GrafInputHelpers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;

public class TableColumnActions {

    public static void deleteColumns(GrafTable gTable){
        JTable table = gTable.getTable();
        ArrayList<Integer> selected = getSelectedColumns(gTable);
        int colCount = table.getColumnCount();
        if (selected.size() == 0) { gTable.setTableMessage("No columns selected!"); return;}
        String msgString = "Delete columns ";
        for (int i = 0; i < selected.size(); i++)
            msgString = msgString +(selected.get(i))+" ";
        msgString = msgString+"?";
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, msgString, "Delete?" , dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION){
            for (int i = selected.size()-1; i >= 0; i--){
                removeColumn(selected.get(i),gTable);
            }
        }
    }

    private static void removeColumn(int toBeRemoved, GrafTable gTable){
        JTable table = gTable.getTable();
        DefaultTableModel model = gTable.getModel();
        for (int currentColumn = toBeRemoved;  currentColumn<table.getColumnCount()-1;  currentColumn++)
            for (int currentRow = 0; currentRow< table.getRowCount(); currentRow++){
                model.setValueAt(table.getValueAt(currentRow, currentColumn+1), currentRow, currentColumn);
                gTable.setHeaderString(currentColumn, gTable.getHeaderString(currentColumn+1));
            }
        String[] headerHolder = TableHeaderActions.getHeaderArray(gTable);

        model.setColumnCount(model.getColumnCount()-1);
        TableHeaderActions.restoreHeaders(headerHolder, gTable);
        //copy next column into this column and repeat until end of model
        //remove the last column
    }

    private static ArrayList<Integer> getSelectedColumns(GrafTable gTable){
        JTable table = gTable.getTable();
        int colCount = table.getColumnCount();
        ArrayList<Integer> selected = new ArrayList<Integer>();
        for (int i = 0; i< colCount; i++)
            if (table.isColumnSelected(i))
                selected.add(i);
        return selected;
    }


    public static void clearColumns(GrafTable gTable){
        ArrayList<Integer> selected = getSelectedColumns(gTable);
        if (selected.size() == 0) { gTable.setTableMessage("No columns selected!"); return;}
        String msgString = "Clear columns ";
        for (int i = 0; i < selected.size(); i++)
            msgString = msgString +(selected.get(i))+" ";
        msgString = msgString+"?";
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, msgString, "Clear Columns?" , dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION){
            for (int i = selected.size()-1; i >= 0; i--){
                for (int row = 1; row < gTable.getNumRows(); row++)
                    gTable.setCellValueNull(row , selected.get(i));

            }
        }
    }


    public static void sortColumns(boolean ascending, GrafTable gTable){
        JTable table = gTable.getTable();
        DefaultTableModel model = gTable.getModel();
        ArrayList<Integer> selected = getSelectedColumns(gTable);
        if (selected.size() == 0) {
            TableUI.getTableController().setTableMessage("No columns selected!");
            return;
        }
        String msgString = "Sort columns ";
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
                columnSorted = getColumnValues(col, TableUI.getData());
                Arrays.sort(columnSorted);
                int row;
                for (int r = 0; r < numRows; r++){
                    if (!ascending) row = (numRows-1) - r; else row = r;
                    model.setValueAt(columnSorted[r], row, col);
                }
            }
        }
    }

    public static void zeroColumns(GrafTable gTable){
        ArrayList<Integer> selected = getSelectedColumns(gTable);
        if (selected.size() == 0) { gTable.setTableMessage("No columns selected!"); return;}
        String msgString = "Zero columns ";
        for (int i = 0; i < selected.size(); i++)
            msgString = msgString +(selected.get(i))+" ";
        msgString = msgString+"?";
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, msgString, "Zero Columns?" , dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION){
            for (int i = selected.size()-1; i >= 0; i--){
                for (int row = 1; row < gTable.getNumRows(); row++)
                    gTable.setCellValueDouble(row , selected.get(i) , 0.0);
            }
        }
    }

    public static Double[] getColumnValues(int col, GrafTable gTable){
        DefaultTableModel model = gTable.getModel();
        int rows = gTable.getNumRows();
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

   /* public static GrafProg.GrafProg.GrafProg.GrafTable.GrafTable getData(){
        return GrafProg.GrafProg.getData();
    }*/

}



/* public void setColumnValues(int col, double a[]){
        for (int i = 1; i<=a.length; i++){
            setCellValueDouble(i, col, a[i]);
        }
    }*/

 /* private int getFirstSelectedColumn(){
       int colCount = table.getColumnCount();
       for (int i = 0; i< colCount; i++)
        if (table.isColumnSelected(i))
           return i;
       return 0;
   }*/