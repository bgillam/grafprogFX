import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.AnchorPaneBuilder;
import javafx.scene.layout.Pane;
import sun.plugin.javascript.navig.Anchor;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class GrafController {

    //instance variables shared with fxml file
    @FXML    private SwingNode testNode;
    @FXML    private javafx.scene.control.Label message1;
    @FXML    private javafx.scene.control.Label message2;
    @FXML    private javafx.scene.control.Label message3;
    @FXML    private AnchorPane grafPane;

    //
    @FXML    private void onMenuChoice(KeyEvent keyEvent) {
    }

    @FXML    private void exitApplicaton(ActionEvent ae){
        Platform.exit();
    }


    public void fileNew(ActionEvent actionEvent) {
        System.out.print("new");
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
       GrafProg.getDialogController().showFxEntry();
       GrafProg.getDialogStage().show();

    }


    public void fxValue(ActionEvent actionEvent) {
     GrafProg.getDialogController().hideAll();
     GrafProg.getDialogController().showFxValue();
     GrafProg.getDialogStage().show();

    }

    public void fxTangent(ActionEvent actionEvent) {
       GrafProg.getDialogController().hideAll();
       GrafProg.getDialogController().showFxTangent();
       GrafProg.getDialogStage().show();
    }


    public void fxChord(ActionEvent actionEvent) {
       GrafProg.getDialogController().hideAll();
       GrafProg.getDialogController().showFxChord();
       GrafProg.getDialogStage().show();

    }

    public void fxIntegral(ActionEvent actionEvent) {
       GrafProg.getDialogController().hideAll();
       GrafProg.getDialogController().showFxIntegral();
       GrafProg.getDialogStage().show();
    }

    public void fxZeros(ActionEvent actionEvent) {
       GrafProg.getDialogController().hideAll();
       GrafProg.getDialogController().showFxZeros();
       GrafProg.getDialogStage().show();
    }

    public void table(ActionEvent actionEvent) {
        //GrafStage.showData();
       GrafProg.getTableStage().show();
    }

    public void oneVStats(ActionEvent actionEvent) {
       GrafProg.getStatStage().show();
    }

    public AnchorPane getGrafPane(){return grafPane;}

    public void closeGraf(ActionEvent actionEvent) {
        GrafProg.closeGraf();

    }
}

