import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.*;

public class OneVarStatsController {


    @FXML private TextField textFieldModes;
    @FXML private TextField textFieldIQR;
    @FXML private TextField textFieldStDevS;
    @FXML private TextField textFieldStDevP;
    @FXML private TextField textFieldVarS;
    @FXML private TextField textFieldVarP;
    @FXML private TextField textFieldMean;
    @FXML private TextField textFieldMax;
    @FXML private TextField textFieldQ3;
    @FXML private TextField textFieldMedian;
    @FXML private TextField textFieldQ1;
    @FXML private TextField textFieldMin;
    @FXML private TextField textFieldN;
    @FXML private TextField textFieldRange;
    @FXML private  ComboBox functionComboBox;



    public void moused(MouseEvent mouseEvent) {
      // functionComboBox.setSelectionModel(FXCollections.observableArrayList(GrafStage.getData().getHeaderArray());
        // setModel(new javax.swing.DefaultComboBoxModel(GrafStage.getData().getHeaderArray()));
        //System.out.println("Headers:"+GrafStage.getData().getHeaderArray()[3]);
        functionComboBox.setItems(FXCollections.observableArrayList(GrafStage.getData().getHeaderArray()));

                //= new ComboBox(FXCollections.observableArrayList(GrafStage.getData().getHeaderArray()));
    }


    public void selected(ActionEvent actionEvent) {

        doStats();
       // System.out.println(functionComboBox.getValue()+" selected");
    }

    public void doStats(){
        int selectedColumn = functionComboBox.getSelectionModel().getSelectedIndex();
        /*if ((selectedColumn == 0)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Choose an output column", ButtonType.OK);
            alert.showAndWait();
            functionComboBox.setItems(FXCollections.observableArrayList(GrafStage.getData().getHeaderArray()));
        }*/


        //int selectedColumn = functionComboBox.getSelectionModel().getSelectedIndex();
        System.out.println("Column: "+selectedColumn+" Value: "+functionComboBox.getValue());
        //textFieldN.setText(""+GrafStats.getN(GrafStage.getData().getColumnValues(selectedColumn)));
//        textFieldMin.setText(""+GrafStats.getMin(GrafStage.getData().getColumnValues(getSelectedIndex())));
//        textFieldQ1.setText(""+GrafStats.getQ1(GrafStage.getData().getColumnValues(getSelectedIndex())));
//        textFieldMedian.setText(""+GrafStats.getMedian(GrafStage.getData().getColumnValues(getSelectedIndex())));
//        textFieldQ3.setText(""+GrafStats.getQ3(GrafStage.getData().getColumnValues(getSelectedIndex())));
//        textFieldMax.setText(""+GrafStats.getMax(GrafStage.getData().getColumnValues(getSelectedIndex())));
//        textFieldRange.setText(""+GrafStats.getRange(GrafStage.getData().getColumnValues(getSelectedIndex())));
//        textFieldMean.setText(""+GrafStats.getMean(GrafStage.getData().getColumnValues(getSelectedIndex())));
//        textFieldVarP.setText(""+GrafStats.getVarianceOfPopulation(GrafStage.getData().getColumnValues(getSelectedIndex())));
//        textFieldVarS.setText(""+GrafStats.getVarianceOfSample(GrafStage.getData().getColumnValues(getSelectedIndex())));
//        textFieldStDevP.setText(""+GrafStats.getStandardDeviationOfPopulation(GrafStage.getData().getColumnValues(getSelectedIndex())));
//        textFieldStDevS.setText(""+GrafStats.getStandardDeviationOfSample(GrafStage.getData().getColumnValues(getSelectedIndex())));
//        textFieldIQR.setText(""+GrafStats.getIQR(GrafStage.getData().getColumnValues(getSelectedIndex())));
//        double[] modes = GrafStats.getModes(GrafStage.getData().getColumnValues(getSelectedIndex()));
//        String modeString = "";
//        for (double m: modes)
//            modeString = modeString+m+",";
//        //modeString = modeString.substring(0,modeString.length() - 1);
//        textFieldModes.setText(modeString);
    }


}
