import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.IOException;

public class GrafMainController {
    @FXML
    public Label message1;
    @FXML
    public Label message2;
    @FXML
    public Label message3;
    @FXML
    public Pane grafPane;
//    @FXML
//    public SwingNode swingGrafNode;


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

    public void putGrafPanelInGrafPane(SwingNode swingGrafNode, GrafPanel grafPanel, int width, int height){
        Platform.runLater(new Runnable() {
            @Override public void run() {
                grafPanel.setPreferredSize(new Dimension( width, height-55));
                swingGrafNode.setContent(grafPanel);
                grafPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
                //grafPanel.setMinimumSize(new Dimension(width,height));
                grafPane.getChildren().add(swingGrafNode);
            }
        });
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



    public void addGrafPanel(SwingNode swingNode){
        grafPane.getChildren().add(swingNode);


    }

    public void fileNew(ActionEvent actionEvent) {
        new GrafStage();
    }
}
