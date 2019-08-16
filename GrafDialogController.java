//package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;

import javax.swing.*;
import java.awt.*;

public class GrafDialogController {

    @FXML
    public ComboBox fComboBox;
    @FXML
    public Label fChoiceLabel;
    @FXML
    public TextField x1Text;
    @FXML
    public Label x1Label;
    @FXML
    public Label separator1;
    @FXML
    public Label y1Label;
    @FXML
    public TextField y1Text;
    @FXML
    public Label separator2;
    @FXML
    public Label nLabel;
    @FXML
    public TextField nText;
    @FXML
    public Label x2Label;
    @FXML
    public Label separator3;
    @FXML
    public TextField x2Text;
    @FXML
    public Label y2Label;
    @FXML
    public TextField y2Text;
    @FXML
    public Label fxLabel;
    @FXML
    public TextField functionString;
    @FXML
    public Label markLabel;
    @FXML
    public RadioButton pointMarkRButton;
    @FXML
    public RadioButton xMarkRButton;
    @FXML
    public RadioButton oMarkRButton;
    @FXML
    public RadioButton charMarkRButton;
    @FXML
    public TextField charMarkText;
    @FXML
    public Label textLabel;
    @FXML
    public TextField textForDisplay;

    @FXML
    public Pane grafPane;


    @FXML
    public void onSaveButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    public void onDiscardButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    public void onExitButtonClicked(ActionEvent e){


            GrafStage.dialogStage.hide();
            GrafStage.dialogController.hideAll();  //this should hide all; add points for each box



        System.out.println(e.getSource()+ "was Clicked");
    }

    @FXML
    public void onEditButtonClicked(ActionEvent e){

       //hidePoints();



        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    public void onDeleteButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    public void onClearButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }

    @FXML
    public void onFontButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    public void onFillButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    public void onColorButtonClicked(ActionEvent e) {
        System.out.println(e.getSource() + "was Clicked");
    }

    //public void hidePoints(){

    public void hideAll() //change to hide all
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                fChoiceLabel.setVisible(false);
                fComboBox.setVisible(false);
                fxLabel.setVisible(false);
                functionString.setVisible(false);
                x1Text.setVisible(false);
                x1Label.setVisible(false);
                separator1.setVisible(false);
                y1Label.setVisible(false);
                y1Text.setVisible(false);
                separator2.setVisible(false);
                nLabel.setVisible(false);
                nText.setVisible(false);
                x2Label.setVisible(false);
                separator3.setVisible(false);
                x2Text.setVisible(false);
                y2Label.setVisible(false);
                y2Text.setVisible(false);
                markLabel.setVisible(false);
                markLabel.setVisible(false);
                pointMarkRButton.setVisible(false);
                xMarkRButton.setVisible(false);
                oMarkRButton.setVisible(false);
                charMarkRButton.setVisible(false);
                charMarkText.setVisible(false);
                GrafStage.dialogStage.hide();           }
        });
    }

    public void showFxEntry() //change to hide all
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                GrafStage.dialogStage.setTitle("Function");
                fxLabel.setVisible(true);
                functionString.setVisible(true);

            }
        });
    }

    public void showPoints()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                x1Text.setVisible(true);
                x1Label.setVisible(true);
                separator1.setVisible(true);
                y1Label.setVisible(true);
                y1Text.setVisible(true);
                separator2.setVisible(true);
                nLabel.setVisible(true);
                nText.setVisible(true);
                x2Label.setVisible(true);
                separator3.setVisible(true);
                x2Text.setVisible(true);
                y2Label.setVisible(true);
                y2Text.setVisible(true);
            }
        });
    }






}
