package GrafProg.GrafTable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class TableController {


    @FXML    private Label tableMessage;
    @FXML    private AnchorPane tablePane;



    public void onMenuChoice(KeyEvent keyEvent) {
    }

    public AnchorPane getTablePane(){
        return tablePane;
    }

    public void onHeading(ActionEvent actionEvent) {
       // GrafProg.GrafProg.getData().editHeaders();
        TableHeaderActions.editHeaders(TableUI.getData());


       /* HeaderDialog headerDialog = new HeaderDialog(GrafProg.GrafProg.getData());
        headerDialog.setVisible(true);
        headerDialog.setModal(true);*/
    }

    public void onRandom(ActionEvent actionEvent) {
        DataGenUI.getDataGenController().hideAll();
        DataGenUI.getDataGenController().showRandomDialog();
        DataGenUI.getDataGenStage().show();
        /*GrafProg.GrafTable.RandomDialog rd = new GrafProg.GrafTable.RandomDialog(GrafProg.GrafProg.getData());
        rd.showDialog()*/
    }

    public void onFunction(ActionEvent actionEvent) {
        DataGenUI.getDataGenController().hideAll();
        DataGenUI.getDataGenController().showFunctionDialog();
        DataGenUI.getDataGenStage().show();
    }

    public void onRecursion(ActionEvent actionEvent) {
        DataGenUI.getDataGenController().hideAll();
        DataGenUI.getDataGenController().showRecursiveDialog();
        DataGenUI.getDataGenStage().show();

        /*RecursiveDialog recDialog = new RecursiveDialog(GrafProg.GrafProg.getData());
        recDialog.showDialog();*/
    }

    public void onClearColumn(ActionEvent actionEvent) {
        TableColumnActions.clearColumns(getData());
    }

    public void onZeroColumn(ActionEvent actionEvent) {
        TableColumnActions.zeroColumns(getData());
    }

    public void onSortAscend(ActionEvent actionEvent) {
        TableColumnActions.sortColumns(true, getData());
    }

    public void onSortDescend(ActionEvent actionEvent) {
        TableColumnActions.sortColumns(false,  getData());
    }

    public void onDone(ActionEvent actionEvent) {  TableUI.getTableStage().hide();  }

    public void onNewClear(ActionEvent actionEvent) {
        TableUI.setData(new GrafTable(100,10));
    }

    public void onCut(ActionEvent actionEvent) {
        TableEditActions.cutValues(getData());
    }

    public void onCopy(ActionEvent actionEvent) {TableEditActions.copyValues(getData());}

    public void onPaste(ActionEvent actionEvent) {
        TableEditActions.pasteValues(getData());
    }

    public void onDeleteRow(ActionEvent actionEvent) {
        TableEditActions.deleteRows(getData());
    }

    public void onDeleteColumn(ActionEvent actionEvent) {
        TableColumnActions.deleteColumns(getData());
    }

    public void onResize(ActionEvent actionEvent){ TableUI.getData().resizeData();
    }

    public String getTableMessage() {
        return tableMessage.getText();
    }

    void setTableMessage(String tableMessage) {
        this.tableMessage.setText(tableMessage);
    }

    public GrafTable getData(){
        return TableUI.getData();
    }


}
