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

    @FXML
    public AnchorPane testPane;

    @FXML
    public SwingNode testNode;

    @FXML
    public javafx.scene.control.Label message1;
    @FXML
    public javafx.scene.control.Label message2;
    @FXML
    public Label message3;



    @FXML
    public void onButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");

    }

    @FXML
    public void onMenuChoice(KeyEvent keyEvent) {
    }

    @FXML
    public void exitApplicaton(ActionEvent ae){
        Platform.exit();
    }

    public void fxInput(ActionEvent actionEvent) {
        //GrafStage.dialogStage.initModality(Modality.APPLICATION_MODAL);

        GrafStage.dialogStage.show();
        GrafStage.dialogController.showFxEntry();

    }

    public void fileNew(ActionEvent actionEvent) {
        new GrafStage();
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


    public void putGrafPanelInGrafPane(SwingNode swingGrafNode, GrafPanel grafPanel, int width, int height){
        Platform.runLater(new Runnable() {
            @Override public void run() {
                grafPanel.setPreferredSize(new Dimension( width, height-55));
                grafPanel.setSize(new Dimension( width, height-55));

                swingGrafNode.setContent(grafPanel);
                grafPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                testPane.getChildren().add(swingGrafNode);
            }
        });
    }

    public AnchorPane getTestPane(){
        return testPane;
    }

}