//package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GrafDialogController {

    @FXML    private ComboBox fComboBox;
    @FXML    private Label fChoiceLabel;
    @FXML    private TextField x1Text;
    @FXML    private Label x1Label;
    @FXML    private Label separator1;
    @FXML    private Label y1Label;
    @FXML    private TextField y1Text;
    @FXML    private Label separator2;
    @FXML    private Label nLabel;
    @FXML    private TextField nText;
    @FXML    private Label x2Label;
    @FXML    private Label separator3;
    @FXML    private TextField x2Text;
    @FXML    private Label y2Label;
    @FXML    private TextField y2Text;
    @FXML    private Label fxLabel;
    @FXML    private TextField functionString;
    @FXML    private Label markLabel;
    @FXML    private RadioButton pointMarkRButton;
    @FXML    private RadioButton xMarkRButton;
    @FXML    private RadioButton oMarkRButton;
    @FXML    private RadioButton charMarkRButton;
    @FXML    private TextField charMarkText;
    @FXML    private Label textLabel;
    @FXML    private TextField textForDisplay;
    @FXML    private Label fontName;
    @FXML    private Button fontButton;
    @FXML    private Button fillButton;
    @FXML    private Button colorButton;
    @FXML    private Label chooseObject;
    @FXML    private Pane grafPane;
    @FXML    private CheckBox fns;
    @FXML    private Label fChoiceLabel2;
    @FXML    private ComboBox fComboBox2;
    @FXML    private ComboBox objectComboBox;
             private GrafType gType;
             private ArrayList<GrafObject> tempGrafList = GrafStage.getGrafList();

    @FXML
    private void onCreateButtonClicked(ActionEvent e){


        GrafStage.setMessage1("item created");
    }
    @FXML
    private void onDiscardButtonClicked(ActionEvent e){
        resetDialog();
    }

    @FXML
    private void onExitButtonClicked(ActionEvent e){
            saveChanges();
            resetDialog();
    }

    @FXML
    private void onEditButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    private void onDeleteButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    private void onClearButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    private void onFontButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    private void onFillButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    private void onColorButtonClicked(ActionEvent e) {

        System.out.println(e.getSource() + "was Clicked");
    }

    private void resetDialog(){
        GrafStage.getDialogStage().hide();
        GrafStage.dialogController.hideAll();  //this should hide all; add points for each box
    }

    private void saveChanges(){
        GrafStage.setGrafList(tempGrafList);
        GrafStage.setMessage1("changes saved");
    }

    private GrafFunction[] createFunctionList(){
            return (GrafFunction[]) createObjectList(GrafType.FUNCTION);
    }

    private GrafObject[] createObjectList(GrafType gtype){
        ArrayList<GrafObject> grafObjects = new ArrayList<>();
        for (GrafObject g: tempGrafList){
            if (g.getType() == gtype) grafObjects.add(g);
        }
        return (GrafObject[]) grafObjects.toArray();
    }

    public void showFxEntry()
    {
        gType = GrafType.FUNCTION;
        Platform.runLater(new Runnable() {
            @Override public void run() {
                GrafStage.getDialogStage().setTitle("FUNCTION");
                fxLabel.setVisible(true);
                functionString.setVisible(true);
                chooseObject.setText("Choose FUNCTION");
            }
        });
    }

    public void showFxValue()
    {
        gType = GrafType.FVALUE;
        Platform.runLater(new Runnable() {
            @Override public void run() {
                fComboBox.setItems(FXCollections.observableArrayList(createFunctionList()));
                objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
                GrafStage.getDialogStage().setTitle("VALUE");
                fChoiceLabel.setText("Choose f(x): ");
                fChoiceLabel.setVisible(true);
                fComboBox.setVisible(true);
                fxLabel.setVisible(true);
                functionString.setVisible(true);
                functionString.setEditable(false);
                showX1();
                showMarks();
                chooseObject.setText("Choose VALUE");
            }
        });
    }

    public void showFxTangent()
    {
        gType = GrafType.TANGENT;
        Platform.runLater(new Runnable() {
            @Override public void run() {
                GrafStage.getDialogStage().setTitle("TANGENT");
                fChoiceLabel.setText("Choose f(x): ");
                fChoiceLabel.setVisible(true);
                fComboBox.setVisible(true);
                fxLabel.setVisible(true);
                functionString.setVisible(true);
                functionString.setEditable(false);
                showX1();
                showMarks();
                chooseObject.setText("Choose TANGENT");
            }
        });
    }

    public void showFxChord()
    {
        gType = GrafType.CHORD;
        Platform.runLater(new Runnable() {
            @Override public void run() {
                GrafStage.getDialogStage().setTitle("FCHORD");
                fChoiceLabel.setText("Choose f(x): ");
                fChoiceLabel.setVisible(true);
                fComboBox.setVisible(true);
                fxLabel.setVisible(true);
                functionString.setVisible(true);
                functionString.setEditable(false);
                showX1X2();
                showMarks();
                chooseObject.setText("Choose FCHORD");

            }
        });
    }

    public void showFxZeros()
    {
        gType = GrafType.FZERO;
        Platform.runLater(new Runnable() {
            @Override public void run() {
                GrafStage.getDialogStage().setTitle("FZEROS");
                fChoiceLabel.setText("Choose f(x): ");
                fChoiceLabel.setVisible(true);
                fComboBox.setVisible(true);
                fxLabel.setVisible(true);
                functionString.setVisible(true);
                functionString.setEditable(false);
                showMarks();
                showX1X2();
                showDx();
                chooseObject.setText("Choose FZEROS");
            }
        });
    }

    public void showFxIntegral()
    {gType = GrafType.INTEGRAL;

        Platform.runLater(new Runnable() {
            @Override public void run() {
                GrafStage.getDialogStage().setTitle("INTEGRAL");
                fChoiceLabel.setText("Choose f(x): ");
                fChoiceLabel.setVisible(true);
                fComboBox.setVisible(true);
                fxLabel.setVisible(true);
                functionString.setVisible(true);
                functionString.setEditable(false);
                fillButton.setVisible(true);
                showX1X2();
                showN();
                chooseObject.setText("Choose INTEGRAL");

            }
        });
    }

    public void showMarks()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                markLabel.setVisible(true);
                pointMarkRButton.setVisible(true);
                xMarkRButton.setVisible(true);
                oMarkRButton.setVisible(true);
                charMarkRButton.setVisible(true);
                charMarkText.setVisible(true);
                //textLabel.setVisible(true);
                //textForDisplay.setVisible(true);
            }
        });
    }

    public void showX1()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                x1Text.setVisible(true);
                x1Label.setVisible(true);

            }
        });
    }


    public void showX1X2()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showX1();
                x2Label.setVisible(true);
                x2Text.setVisible(true);

            }
        });
    }

    public void showX1Y1()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showX1();
                y1Label.setVisible(true);
                y1Text.setVisible(true);

            }
        });
    }

    public void showX1Y1X2Y2()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showX1Y1();
                x2Label.setVisible(true);
                x2Text.setVisible(true);
                y2Label.setVisible(true);
                y2Text.setVisible(true);
            }
        });
    }

    public void showN()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                nLabel.setVisible(true);
                nLabel.setText("n:");
                nText.setVisible(true);
            }
        });
    }


    public void showDx()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                nLabel.setVisible(true);
                nLabel.setText("dx:");
                nText.setVisible(true);
            }
        });
    }


    public void hideAll() //change to hide all
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                fChoiceLabel.setVisible(false);
                fComboBox.setVisible(false);
                fChoiceLabel2.setVisible(false);
                fComboBox2.setVisible(false);
                fxLabel.setVisible(false);
                functionString.setVisible(false);
                x1Text.setVisible(false);
                x1Label.setVisible(false);
                y1Label.setVisible(false);
                y1Text.setVisible(false);
                nLabel.setVisible(false);
                nText.setVisible(false);
                x2Label.setVisible(false);
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
                textLabel.setVisible(false);
                textForDisplay.setVisible(false);
                fontName.setVisible(false);
                fontButton.setVisible(false);
                fns.setVisible(false);
                fillButton.setVisible(false);
            }
        });
    }


}
