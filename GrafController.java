import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.AnchorPaneBuilder;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Anchor;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

public class GrafController {

        //instance variables shared with fxml file
    @FXML    private SwingNode testNode;
    @FXML    private javafx.scene.control.Label message1;
    @FXML    private javafx.scene.control.Label message2;
    @FXML    private javafx.scene.control.Label message3;
    @FXML    private AnchorPane grafPane;
    //@ FXML  public BorderPane mainPane;

    //
    @FXML    private void onMenuChoice(KeyEvent keyEvent) {
    }

    @FXML    private void exitApplicaton(ActionEvent ae){
        Platform.exit();
    }

    @FXML
    private void fileNew(ActionEvent actionEvent) {
       GrafProg.closeGraf();
       GrafProg.resetGraf();
    }

    public void fileOpen(ActionEvent actionEvent) {
        GrafFiles.openGrafFromFile();
    }

    public void fileSave(ActionEvent actionEvent) {
       GrafFiles.saveFile();
    }

    public void fileSaveAs(ActionEvent actionEvent) {
        GrafFiles.saveFileAs();
    }



    public void showCalc(ActionEvent actionEvent) {

        GrafCalc calc= new GrafCalc();
        calc.setVisible(true);
    }


    public void setMessage1(String message){
        Platform.runLater(new Runnable() {
            @Override public void run() {
                message1.setText(message);
            }
        });
    }

    public void setMessage2(String message){
        Platform.runLater(new Runnable() {
            @Override public void run() {
                message2.setText(message);
            }
        });
    }

    public void setMessage3(String message){
        Platform.runLater(new Runnable() {
            @Override public void run() {
                message3.setText(message);
            }
        });
    }

    public void fxInput(ActionEvent actionEvent) {

       GrafProg.getDialogController().hideAll();
       GrafProg.getDialogController().showFxEntryDialog();
       GrafProg.getDialogStage().show();

    }


    public void fxValue(ActionEvent actionEvent) {
     GrafProg.getDialogController().hideAll();
     GrafProg.getDialogController().showFxValueDialog();
     GrafProg.getDialogStage().show();

    }

    public void fxTangent(ActionEvent actionEvent) {
       GrafProg.getDialogController().hideAll();
       GrafProg.getDialogController().showFxTangentDialog();
       GrafProg.getDialogStage().show();
    }


    public void fxChord(ActionEvent actionEvent) {
       GrafProg.getDialogController().hideAll();
       GrafProg.getDialogController().showFxChordDialog();
       GrafProg.getDialogStage().show();

    }

    public void fxIntegral(ActionEvent actionEvent) {
       GrafProg.getDialogController().hideAll();
       GrafProg.getDialogController().showFxIntegralDialog();
       GrafProg.getDialogStage().show();
    }

    public void fxZeros(ActionEvent actionEvent) {
       GrafProg.getDialogController().hideAll();
       GrafProg.getDialogController().showFxZerosDialog();
       GrafProg.getDialogStage().show();
    }

    public void onPoint(ActionEvent actionEvent) {
        GrafProg.getDialogController().hideAll();
        GrafProg.getDialogController().showPointDialog();
        GrafProg.getDialogStage().show();
    }

    public void onText(ActionEvent actionEvent) {
        GrafProg.getDialogController().hideAll();
        GrafProg.getDialogController().showTextDialog();
        GrafProg.getDialogStage().show();
    }

    public void onLineSegment(ActionEvent actionEvent) {
        GrafProg.getDialogController().hideAll();
        GrafProg.getDialogController().showLineSegmentDialog();
        GrafProg.getDialogStage().show();
    }

    public void onRectangle(ActionEvent actionEvent) {
        GrafProg.getDialogController().hideAll();
        GrafProg.getDialogController().showRectangleDialog();
        GrafProg.getDialogStage().show();
    }

    public void onEllipse(ActionEvent actionEvent) {
        GrafProg.getDialogController().hideAll();
        GrafProg.getDialogController().showEllipseDialog();
        GrafProg.getDialogStage().show();
    }

    public void onCircle(ActionEvent actionEvent) {
        GrafProg.getDialogController().hideAll();
        GrafProg.getDialogController().showCircleDialog();
        GrafProg.getDialogStage().show();
    }

    public void onFreqTable(ActionEvent actionEvent) {
         FrequencyChartDialog fcd = new FrequencyChartDialog();
         fcd.setVisible(true);
    }


    public void onColumnPlot(ActionEvent actionEvent) {
        GrafProg.getDialogController().hideAll();
        GrafProg.getDialogController().showColumnPlotDialog();
        GrafProg.getDialogStage().show();
    }

    public void onBoxplot(ActionEvent actionEvent) {
        GrafProg.getDialogController().hideAll();
        GrafProg.getDialogController().showBoxplotDialog();
        GrafProg.getDialogStage().show();
    }

    public void onHistogram(ActionEvent actionEvent) {
        GrafProg.getDialogController().hideAll();
        GrafProg.getDialogController().showHistogramDialog();
        GrafProg.getDialogStage().show();

    }

    public void onFreqPolygon(ActionEvent actionEvent) {
        GrafProg.getDialogController().hideAll();
        GrafProg.getDialogController().showFreqPolyDialog();
        GrafProg.getDialogStage().show();
    }

    public void onOgive(ActionEvent actionEvent) {
        GrafProg.getDialogController().hideAll();
        GrafProg.getDialogController().showOgiveDialog();
        GrafProg.getDialogStage().show();


    }

    public void onScatterPlot(ActionEvent actionEvent) {
        GrafProg.getDialogController().hideAll();
        GrafProg.getDialogController().showScatterDialog();
        GrafProg.getDialogStage().show();
    }

        public void onRegression(ActionEvent actionEvent) {
            RegressionDialog rd = new RegressionDialog();
            rd.setVisible(true);

    }

    public void onTable(ActionEvent actionEvent) {
        //GrafStage.showData();
       GrafProg.getTableStage().show();
    }

    public void oneVStats(ActionEvent actionEvent) {
       GrafProg.getStatStage().show();
    }


    public AnchorPane getGrafPane(){return grafPane;}

    public void onCloseGraf(ActionEvent actionEvent) {
        if (GrafProg.closeGraf())
            System.exit(0);

    }


    public void onYaxis(ActionEvent actionEvent) {
        GrafProg.getGrafSettings().toggleReverseXY();
    }

    public void onAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("GrafProg");
        alert.setTitle("About");
        alert.setContentText("A Simple Graphing Program \nVerson FX1.0 \nhttp://www.bgillam.com");
        alert.show();
    }

    public void onSet(ActionEvent actionEvent) {
        WindowSizeDialog wsd = new WindowSizeDialog();
        wsd.setVisible(true);

    }

    public void onStandard(ActionEvent actionEvent) {
        GrafProg.getGrafSettings().setStandardAxes();
        GrafProg.getGrafPanel().repaint();
    }

    public void onAuto(ActionEvent actionEvent) {
           Platform.runLater(new Runnable() {
                @Override public void run() {
                    ObservableList objectList = FXCollections.observableArrayList(GrafProg.getGrafList());
                    if (objectList.size() > 1) {
                        objectList.remove(0);
                        ChoiceDialog autoDialog = new ChoiceDialog(objectList.get(0), objectList);
                        autoDialog.setTitle("AutoRange");
                        autoDialog.setHeaderText("Select Item for AutoRange\n Axes --> Standard Window");
                        Optional<GrafObject> result = autoDialog.showAndWait();
                        GrafObject chosenObject = result.get();
                        chosenObject.autoRange();
                    }
                    else {
                         Alert noObjects = new Alert(Alert.AlertType.ERROR);
                         noObjects.setTitle("No GrafObjects!");
                         noObjects.setHeaderText("No GrafObjects have been created. \nCan't AutoRange.");
                         noObjects.showAndWait();
                    }

                }
            });

        //Scales.autoRange(GrafProg.getGrafSettings(), );
            }



}



