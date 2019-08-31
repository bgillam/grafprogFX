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

//    @FXML
//    public AnchorPane testPane;

    @FXML
    public SwingNode testNode;

    @FXML
    public javafx.scene.control.Label message1;
    @FXML
    public javafx.scene.control.Label message2;
    @FXML
    public javafx.scene.control.Label message3;

    public AnchorPane grafPane;


    @FXML
    public void onMenuChoice(KeyEvent keyEvent) {
    }

    @FXML
    public void exitApplicaton(ActionEvent ae){
        Platform.exit();
    }


    public void fileNew(ActionEvent actionEvent) {
        //new GrafStage();
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

        GrafStage.dialogController.hideAll();
        GrafStage.dialogController.showFxEntry();
        GrafStage.dialogStage.show();

    }


    public void fxValue(ActionEvent actionEvent) {
      GrafStage.dialogController.hideAll();
      GrafStage.dialogController.showFxValue();
      GrafStage.dialogStage.show();

    }

    public void fxTangent(ActionEvent actionEvent) {
        GrafStage.dialogController.hideAll();
        GrafStage.dialogController.showFxTangent();
        GrafStage.dialogStage.show();
    }


    public void fxChord(ActionEvent actionEvent) {
        GrafStage.dialogController.hideAll();
        GrafStage.dialogController.showFxChord();
        GrafStage.dialogStage.show();

    }

    public void fxIntegral(ActionEvent actionEvent) {
        GrafStage.dialogController.hideAll();
        GrafStage.dialogController.showFxIntegral();
        GrafStage.dialogStage.show();
    }

    public void fxZeros(ActionEvent actionEvent) {
        GrafStage.dialogController.hideAll();
        GrafStage.dialogController.showFxZeros();
        GrafStage.dialogStage.show();
    }

    public void table(ActionEvent actionEvent) {
        //GrafStage.showData();

    }
}