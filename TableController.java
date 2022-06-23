import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.TableView;
import java.util.Random;

public class TableController {


    @FXML    private Label tableMessage;
    @FXML    private AnchorPane tablePane;



    public void onMenuChoice(KeyEvent keyEvent) {
    }

    AnchorPane getTablePane(){
        return tablePane;
    }

    public void onHeading(ActionEvent actionEvent) {
       // GrafProg.getData().editHeaders();
        TableHeaderActions.editHeaders(GrafProg.getData());


       /* HeaderDialog headerDialog = new HeaderDialog(GrafProg.getData());
        headerDialog.setVisible(true);
        headerDialog.setModal(true);*/
    }

    public void onRandom(ActionEvent actionEvent) {
        GrafProg.getTableGenController().hideAll();
        GrafProg.getTableGenController().showRandomDialog();
        GrafProg.getGenStage().show();
        /*RandomDialog rd = new RandomDialog(GrafProg.getData());
        rd.showDialog()*/
    }

    public void onFunction(ActionEvent actionEvent) {
        GrafProg.getTableGenController().hideAll();
        GrafProg.getTableGenController().showFunctionDialog();
        GrafProg.getGenStage().show();
    }

    public void onRecursion(ActionEvent actionEvent) {
        GrafProg.getTableGenController().hideAll();
        GrafProg.getTableGenController().showRecursiveDialog();
        GrafProg.getGenStage().show();

        /*RecursiveDialog recDialog = new RecursiveDialog(GrafProg.getData());
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

    public void onDone(ActionEvent actionEvent) {
        GrafProg.getTableStage().hide();
    }

    public void onNewClear(ActionEvent actionEvent) {
        GrafProg.setData(new GrafTable(100,10));
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

    public void onResize(ActionEvent actionEvent) {
        GrafProg.getData().resizeData();
    }

    public String getTableMessage() {
        return tableMessage.getText();
    }

    void setTableMessage(String tableMessage) {
        this.tableMessage.setText(tableMessage);
    }

    public GrafTable getData(){
        return GrafProg.getData();
    }


}
