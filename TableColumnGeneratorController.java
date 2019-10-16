import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;

public class TableColumnGeneratorController {

    @FXML private RadioButton rdbtnDoubles;
    @FXML private RadioButton rdbtnIntegers;
    @FXML private Label numStart;
    @FXML private TextField numStartText;
    @FXML private Label numEnd;
    @FXML private TextField numEndText;
    @FXML private Label rowEnd;
    @FXML private TextField rowEndText;
    @FXML private Button cancelButton;
    @FXML private Button okButton;
    @FXML private Label col1ChoiceLabel;
    @FXML private ChoiceBox col1Chooser;
    @FXML private ChoiceBox col2Chooser;
    @FXML private Label col2ChoiceLabel;
    @FXML private Label rowStart;
    @FXML private TextField rowStartText;
    @FXML private Label ya1;
    @FXML private TextField t1;
    @FXML private Label an;
    @FXML private TextField t2;
    @FXML private Label anLabel;

    private GrafTable myDaddy = GrafProg.getData();


    public void hideAll(){
        ya1.setVisible(false);
        t1.setVisible(false);
        an.setVisible(false);
        t2.setVisible(false);
        anLabel.setVisible(false);

        col1ChoiceLabel.setVisible(false);
        col2ChoiceLabel.setVisible(false);
        col1Chooser.setVisible(false);
        col2Chooser.setVisible(false);
        rowStart.setVisible(false);
        rowStartText.setVisible(false);
        rowEnd.setVisible(false);
        rowEndText.setVisible(false);
        cancelButton.setVisible(false);
        okButton.setVisible(false);

        numStart.setVisible(false);
        numStartText.setVisible(false);
        numEnd.setVisible(false);
        numEndText.setVisible(false);
    }

    public void showRandomDialog(){
        col1ChoiceLabel.setVisible(true);
        col1Chooser.setItems(FXCollections.observableArrayList(GrafProg.getData().getHeaderArrayCdr()));
        col1Chooser.getSelectionModel().select(0);
        col1Chooser.setVisible(true);
        numStart.setVisible(true);
        numStartText.setVisible(true);
        numEnd.setVisible(true);
        numEndText.setVisible(true);
        rowStart.setVisible(true);
        rowStartText.setVisible(true);
        rowStartText.setText("1");
        rowEnd.setVisible(true);
        rowEndText.setVisible(true);
        rowEndText.setText(GrafProg.getData().getNumRows()+"");
        okButton.setVisible(true);
        cancelButton.setVisible(true);
        GrafProg.getTableStage().setAlwaysOnTop(false);
        GrafProg.getGenStage().setAlwaysOnTop(true);
        resetChooser();
    }

    private void generateRandomIntegers(){
        double lower = Double.parseDouble(numStartText.getText());
        double upper = Double.parseDouble(numEndText.getText());
        int begin = Integer.parseInt(rowStartText.getText());
        int end = Integer.parseInt(rowEndText.getText());
        int numRows = myDaddy.getNumRows();
        if (upper > numRows) upper = numRows;
        double r;
        //int n;
        for (int i = begin; i <= end ; i++){
            r = (Math.random() * (upper - lower)) + lower;
            r =  (double) Math.round(r);
            myDaddy.setCellValueDouble(i, col1Chooser.getSelectionModel().getSelectedIndex()+1, r);
        }
    }

    private void generateRandomDoubles(){
        double lower = Double.parseDouble(numStartText.getText());
        double upper = Double.parseDouble(numEndText.getText());
        int begin = Integer.parseInt(rowStartText.getText());
        if (begin < 1) begin = 1;
        int end = Integer.parseInt(rowEndText.getText());
        int numRows = myDaddy.getNumRows();
        if (upper > numRows) upper = numRows;
        double r;
        for (int i = begin; i <= end ; i++){
            r = (Math.random() * (upper - lower)) + lower;
            myDaddy.setCellValueDouble(i, col1Chooser.getSelectionModel().getSelectedIndex()+1, r);
        }
    }

    private void allowEdits(){
        rowStartText.setEditable(true);
        rowEndText.setEditable(true);
        numStartText.setEditable(true);
        numEndText.setEditable(true);
        //.setText("Enter Limits and Destination");
    }

    private void resetChooser(){
        col1Chooser.getSelectionModel().select(0);
        rowStartText.setEditable(false);
        rowEndText.setEditable(false);
        numStartText.setEditable(false);
        numEndText.setEditable(false);
        col1ChoiceLabel.setText("Choose Column to Place Values");
    }

    private void generateRandom(){
        //myDaddy.setHeaderString(selectedColumn, beginTextField.getText());
        //headerComboBox.setModel(new javax.swing.DefaultComboBoxModel(myDaddy.getHeaderArray()));

        if (!GrafInputHelpers.isInt(rowStartText.getText())) return;
        if (!GrafInputHelpers.isInt(rowEndText.getText())) return;
        if (!GrafInputHelpers.isInt(numStartText.getText())) return;
        if (!GrafInputHelpers.isDouble(numEndText.getText())) return;
        if (rdbtnIntegers.isSelected()) generateRandomIntegers();
        else if (rdbtnDoubles.isSelected()) generateRandomDoubles();
        resetChooser();
        //myDaddy.repaint();
    }


    public void onCancel(ActionEvent actionEvent) {
        GrafProg.getGenStage().hide();
    }

    public void onOK(ActionEvent actionEvent) {
        generateRandom();
    }
}
