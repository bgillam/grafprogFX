import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

class TableEditActions {

    //row and column manipulations
    static void deleteRows(GrafTable gTable){
        JTable table = gTable.getTable();
        DefaultTableModel model = gTable.getModel();
        int rowCount = table.getRowCount();
        ArrayList<Integer> selected = new ArrayList<>();
        int deleteCount = 0;
        for (int i = 0; i< rowCount; i++)
            if (table.isRowSelected(i)){
                selected.add(i);
                deleteCount++;
            }
        if (selected.size() == 0) { gTable.setTableMessage("No rows selected!"); return;}
        StringBuilder msgString = new StringBuilder("Delete rows ");
        for (int i = 0; i < deleteCount; i++)
            msgString.append(selected.get(i) + 1).append(" ");
        msgString.append("?");
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, msgString.toString(), "Delete?" , dialogButton);

        if (dialogResult == JOptionPane.YES_OPTION){
            for (int i = selected.size()-1; i >= 0; i--){
                model.removeRow(selected.get(i));
            }
            gTable.numberTheRows();
        }

    }



    //cut and paste procedures
    static void cutValues(GrafTable gTable){
        ClipboardHandler clipper = gTable.getClipper();
        gTable.getClipper().setClipboardContents(getSelectedData(gTable));
        deleteSelectedCells(gTable);
    }

    static void copyValues(GrafTable gTable){
        gTable.getClipper().setClipboardContents(getSelectedData(gTable));
    }

    static void pasteValues(GrafTable gTable){
        JTable table = gTable.getTable();
        ClipboardHandler clipper = gTable.getClipper();
        String toParse = clipper.getClipboardContents();
        String item = "";
        int row =table.getSelectedRow();
        int col = table.getSelectedColumn();
        int firstColumn = col;
        int tIndex; // = -1;
        int nlIndex; // = -1;
        int index; // = -1;
        boolean down; // = false;
        String value; // = "";
        while (!toParse.equals("")){
            tIndex = toParse.indexOf('\t');
            nlIndex = toParse.indexOf('\n');
            if ((tIndex == -1) && (nlIndex == -1)) {
                if ((row < gTable.getNumRows()) && (col <= gTable.getNumCols()))  table.setValueAt(toParse, row, col);
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
            //value = toParse.substring(0, index);
            if (value.equals("null")) value = null;
            if ((row < gTable.getNumRows()) && (col <= gTable.getNumCols()))  table.setValueAt(value , row, col);
            toParse = toParse.substring(index+1);
            if (down) {
                row++;
                col = firstColumn;
            }else col++;

        }
    }



    private static String getSelectedData(GrafTable gTable){
        JTable table = gTable.getTable();
        DefaultTableModel model = gTable.getModel();
        StringBuilder copied = new StringBuilder();
        int rows = gTable.getNumRows();
        int cols = gTable.getNumCols();
        boolean itemFound = false;
        for (int currentRow = 0; currentRow < rows; currentRow++){
           for (int currentCol = 1; currentCol < cols ; currentCol++){
                if (table.isCellSelected(currentRow, currentCol)) {
                    copied.append(model.getValueAt(currentRow, currentCol));
                    copied.append("\t");
                    itemFound = true;
                }
           }
           if (itemFound) {
               copied.append("\n");
               itemFound = false;
           }
        }
        return copied.toString();
    }

    private static  void deleteSelectedCells(GrafTable gTable){
        JTable table = gTable.getTable();
        DefaultTableModel model = gTable.getModel();
        int rows = gTable.getNumRows();
        int cols = gTable.getNumCols();
        for (int currentRow = 0; currentRow < rows; currentRow++){
            for (int currentCol = 1; currentCol < cols ; currentCol++){
                if (table.isCellSelected(currentRow, currentCol)) model.setValueAt(null, currentRow, currentCol);
            }
        }
    }

}
