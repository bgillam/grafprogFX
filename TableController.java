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

    public AnchorPane getTablePane(){
        return tablePane;
    }

    public void onHeading(ActionEvent actionEvent) {
        HeaderDialog headerDialog = new HeaderDialog(GrafProg.getData());
        headerDialog.setVisible(true);
        headerDialog.setModal(true);
    }

    public void onRandom(ActionEvent actionEvent) {
        GrafProg.getTableGenController().hideAll();
        GrafProg.getTableGenController().showRandomDialog();
        GrafProg.getGenStage().show();
        /*RandomDialog rd = new RandomDialog(GrafProg.getData());
        rd.showDialog()*/;
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
        GrafProg.getData().clearColumns();
    }

    public void onZeroColumn(ActionEvent actionEvent) {
        GrafProg.getData().zeroColumns();
    }

    public void onSortAscend(ActionEvent actionEvent) {
        GrafProg.getData().sortColumns(true);
    }

    public void onSortDescend(ActionEvent actionEvent) {
        GrafProg.getData().sortColumns(false);
    }

    public void onDone(ActionEvent actionEvent) {
        GrafProg.getTableStage().hide();
    }

    public void onNewClear(ActionEvent actionEvent) {
        GrafProg.setData(new GrafTable(100,10));
    }

    public void onCut(ActionEvent actionEvent) {
        GrafProg.getData().cutValues();
    }

    public void onCopy(ActionEvent actionEvent) {
        GrafProg.getData().getClipper().setClipboardContents(GrafProg.getData().getSelectedData());
    }

    public void onPaste(ActionEvent actionEvent) {
        GrafProg.getData().pasteValues();
    }

    public void onDeleteRow(ActionEvent actionEvent) {
        GrafProg.getData().deleteRows();
    }

    public void onDeleteColumn(ActionEvent actionEvent) {
        GrafProg.getData().deleteColumns();
    }

    public void onResize(ActionEvent actionEvent) {
        GrafProg.getData().resizeData();
    }

    public String getTableMessage() {
        return tableMessage.getText();
    }

    public void setTableMessage(String tableMessage) {
        this.tableMessage.setText(tableMessage);
    }


}
