import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class GrafController {

        //instance variables shared with fxml file
    //@FXML    private SwingNode testNode;
    @FXML    private javafx.scene.control.Label message1;
    @FXML    private javafx.scene.control.Label message2;
    @FXML    private javafx.scene.control.Label message3;
    @FXML    private AnchorPane grafPane;

    @ FXML  public BorderPane mainPane;

    @FXML
    public void showDialog(){
        javafx.scene.control.Dialog<ButtonType> dialog = new javafx.scene.control.Dialog<>();
        dialog.initOwner(mainPane.getScene().getWindow());
        FXMLLoader fxmlLoader= new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("GrafDialog.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }
        catch (IOException e){
            System.out.println("No Dialog");
        }
        dialog.showAndWait();
    }

    //
    @FXML    private void onMenuChoice(KeyEvent keyEvent) {
    }

/*    @FXML    private void exitApplicaton(ActionEvent ae){
        Platform.exit();
    }*/

    @FXML
    private void fileNew(ActionEvent actionEvent) {
       GrafFiles.closeGraf();
       GrafFiles.resetGraf();
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
        Platform.runLater(() -> message1.setText(message));
    }

    public void setMessage2(String message){
        Platform.runLater(() -> message2.setText(message));
    }

    void setMessage3(String message){
        Platform.runLater(() -> message3.setText(message));
    }

    public void onFxInput(ActionEvent actionEvent) {

       ObjectUI.getDialogController().hideAll();
       GrafDialogController.showFxEntryDialog();
       //GrafProg.getDialogController().showFxEntryDialog();
       ObjectUI.getDialogStage().show();

    }


    public void onFxValue(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
     GrafDialogController.showFxValueDialog();
     ObjectUI.getDialogStage().show();

    }

    public void onFxTangent(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
       GrafDialogController.showFxTangentDialog();
       ObjectUI.getDialogStage().show();
    }


    public void onFxChord(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
       GrafDialogController.showFxChordDialog();
       ObjectUI.getDialogStage().show();

    }

    public void onFxIntegral(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
       GrafDialogController.showFxIntegralDialog();
        ObjectUI.getDialogStage().show();
    }

    public void onFxZeros(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
       GrafDialogController.showFxZerosDialog();
        ObjectUI.getDialogStage().show();
    }

    public void onPoint(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
        GrafDialogController.showPointDialog();
        ObjectUI.getDialogStage().show();
    }

    public void onText(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
        GrafDialogController.showTextDialog();
        ObjectUI.getDialogStage().show();
    }

    public void onLineSegment(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
        GrafDialogController.showLineSegmentDialog();
        ObjectUI.getDialogStage().show();
    }

    public void onRectangle(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
        GrafDialogController.showRectangleDialog();
        ObjectUI.getDialogStage().show();
    }

    public void onEllipse(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
        GrafDialogController.showEllipseDialog();
        ObjectUI.getDialogStage().show();
    }

    public void onCircle(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
        GrafDialogController.showCircleDialog();
        ObjectUI.getDialogStage().show();
    }

    public void onFreqTable(ActionEvent actionEvent) {
         FrequencyChartDialog fcd = new FrequencyChartDialog();
         fcd.setVisible(true);
    }


    public void onColumnPlot(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
        GrafDialogController.showColumnPlotDialog();
        ObjectUI.getDialogStage().show();
    }

    public void onBoxplot(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
        GrafDialogController.showBoxplotDialog();
        ObjectUI.getDialogStage().show();
    }

    public void onHistogram(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
        GrafDialogController.showHistogramDialog();
        ObjectUI.getDialogStage().show();

    }

    public void onFreqPolygon(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
        GrafDialogController.showFreqPolyDialog();
        ObjectUI.getDialogStage().show();
    }

    public void onOgive(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
        GrafDialogController.showOgiveDialog();
        ObjectUI.getDialogStage().show();


    }

    public void onScatterPlot(ActionEvent actionEvent) {
        ObjectUI.getDialogController().hideAll();
        GrafDialogController.showScatterDialog();
        ObjectUI.getDialogStage().show();
    }

        public void onRegression(ActionEvent actionEvent) {
            RegressionDialog rd = new RegressionDialog();
            rd.setVisible(true);

    }

    public void onTable(ActionEvent actionEvent) {
        //GrafStage.showData();
        TableUI.getTableStage().show();
    }

    public void oneVStats(ActionEvent actionEvent) {StatUI.getStatStage().show();
    }


    AnchorPane getGrafPane(){return grafPane;}

    public void onCloseGraf(ActionEvent actionEvent) {
        if (GrafFiles.closeGraf())
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
        //GrafProg.getGrafPanel().repaint();
        GrafUI.getGrafPanel().repaint();
    }

    public void onAuto(ActionEvent actionEvent) {
           Platform.runLater(() -> {
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

           });

        //Scales.autoRange(GrafProg.getGrafSettings(), );
            }



}



