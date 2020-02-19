import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class TableHeaderActions {


    //Header and row index procedures
    public static void labelHeaders(GrafTable table){
        table.setHeaderString(0,"");
        //table.getColumnModel().getColumn(0).setHeaderValue("");
        int cols = table.getNumCols();
        for (int i = 1; i <= cols; i++) // have adjusted our column count by 1.
            table.setHeaderString(i,"Data"+i);
    }

    public static void editHeaders(GrafTable table){

        Dialog headerChangeDialog = new javafx.scene.control.Dialog();
        headerChangeDialog.setTitle("Edit Column Headers");
        headerChangeDialog.setHeaderText("Choose Header to Edit:");
        headerChangeDialog.setContentText("testcontenttext");

        TextField headerChangeTextField = new TextField("");
        headerChangeTextField.setVisible(false);

        ComboBox headComboBox = new ComboBox();
        headComboBox.getItems().clear();
        headComboBox.getItems().addAll(getHeaderArrayCdr(table));
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
                table.setHeaderString(headComboBox.getSelectionModel().getSelectedIndex() + 1, headerChangeTextField.getText());
                table.refreshTable();
                done = true;
            } else if (result.get().equals(saveButton)) {
                table.setHeaderString(headComboBox.getSelectionModel().getSelectedIndex() + 1, headerChangeTextField.getText());
                headComboBox.getItems().clear();
                headComboBox.getItems().addAll(getHeaderArrayCdr(table));
                headComboBox.setValue("Header List");
                headerChangeTextField.setVisible(false);
                table.refreshTable();
            }else if (result.get().equals(ButtonType.CANCEL)) done = true;
        }
    }

    public static String[] getHeaderArray(GrafTable table){
        String[] headerArray = new String[table.getNumCols()+1];
        for (int i = 0; i <= table.getNumCols(); i++) {
            headerArray[i] = table.getHeaderString(i);
            //System.out.println(getHeaderString(i));
        }
        return headerArray;
    }

    public static String[] getHeaderArrayCdr(GrafTable table){
        String[] headerArray = new String[table.getNumCols()];
        for (int i = 1; i <= table.getNumCols(); i++)
            headerArray[i-1] = table.getHeaderString(i);
        return headerArray;
    }

    public static void setComboBox(ComboBox fComboBox,GrafTable table){
        fComboBox.setItems(FXCollections.observableArrayList(TableHeaderActions.getHeaderArrayCdr(table)));
    }




    public static void restoreHeaders(String[] headerHolder, GrafTable gTable){
        int len;
        int colCount = gTable.getTable().getColumnCount();
        int oldHeaderLength =  headerHolder.length;
        if (colCount > oldHeaderLength){
            len = oldHeaderLength;
            for (int i = len; i < colCount; i++ )
                gTable.setHeaderString(i, "Data"+i);
        }
        else len = colCount;
        gTable.setHeaderString(0,"");
        for (int i=1; i < len; i++)
            gTable.setHeaderString(i, headerHolder[i]);
    }

}

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