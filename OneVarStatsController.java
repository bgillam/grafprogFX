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



    public void pullDownMoused(MouseEvent mouseEvent) {
      // functionComboBox.setSelectionModel(FXCollections.observableArrayList(GrafStage.getData().getHeaderArray());
        // setModel(new javax.swing.DefaultComboBoxModel(GrafStage.getData().getHeaderArray()));
        //System.out.println("Headers:"+GrafStage.getData().getHeaderArray()[3]);

        functionComboBox.setItems(FXCollections.observableArrayList(GrafStage.getData().getHeaderArrayTrunc()));
                //= new ComboBox(FXCollections.observableArrayList(GrafStage.getData().getHeaderArray()));
    }



    public void doStats(){
        int selectedColumn = functionComboBox.getSelectionModel().getSelectedIndex()+1;

        //check for empty column.
        if (GrafStats.nullArray(GrafStats.getRidOfNulls(GrafStage.getData().getColumnValues(selectedColumn)), "Stats generator")) {
            clearForm();
            return;
        }
        Double[] columnValues = GrafStage.getData().getColumnValues(selectedColumn);


        textFieldN.setText(""+GrafStats.getN(columnValues));
        textFieldMin.setText(""+GrafStats.getMin(columnValues));
        textFieldQ1.setText(""+GrafStats.getQ1(columnValues));
        textFieldMedian.setText(""+GrafStats.getMedian(columnValues));
        textFieldQ3.setText(""+GrafStats.getQ3(columnValues));
        textFieldMax.setText(""+GrafStats.getMax(columnValues));
        textFieldRange.setText(""+GrafStats.getRange(columnValues));
        textFieldMean.setText(""+GrafStats.getMean(columnValues));
        textFieldVarP.setText(""+GrafStats.getVarianceOfPopulation(columnValues));
        textFieldVarS.setText(""+GrafStats.getVarianceOfSample(columnValues));
        textFieldStDevP.setText(""+GrafStats.getStandardDeviationOfPopulation(columnValues));
        textFieldStDevS.setText(""+GrafStats.getStandardDeviationOfSample(columnValues));
        textFieldIQR.setText(""+GrafStats.getIQR(columnValues));
        double[] modes = GrafStats.getModes(columnValues);
        String modeString = "";
        for (double m : modes)
                modeString = modeString + m + ",";
            //modeString = modeString.substring(0,modeString.length() - 1);
        textFieldModes.setText(modeString);
    }

    private void clearForm(){
        textFieldN.setText("");
        textFieldMin.setText("");
        textFieldQ1.setText("");
        textFieldMedian.setText("");
        textFieldQ3.setText("");
        textFieldMax.setText("");
        textFieldRange.setText("");
        textFieldMean.setText("");
        textFieldVarP.setText("");
        textFieldVarS.setText("");
        textFieldStDevP.setText("");
        textFieldStDevS.setText("");
        textFieldIQR.setText("");
        textFieldModes.setText("");
    }


    public void close(ActionEvent actionEvent) {
        GrafStage.getStatStage().hide();
    }
}
