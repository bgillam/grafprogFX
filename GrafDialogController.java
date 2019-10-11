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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GrafDialogController {



    //instance variables tied to GUI
    @FXML    private ColorPicker fillColorPicker;
    @FXML    private Label fillLabel;
    @FXML    private ColorPicker grafColorPicker;
    @FXML    private Label fChoiceLabel;
    @FXML    private TextField x1Text;
    @FXML    private Label x1Label;
    @FXML    private Label y1Label;
    @FXML    private TextField y1Text;
    @FXML    private Label nLabel;
    @FXML    private TextField nText;
    @FXML    private Label x2Label;
    @FXML    private TextField x2Text;
    @FXML    private Label y2Label;
    @FXML    private TextField y2Text;
    @FXML    private Label fxLabel;
    @FXML    private TextField functionString;
    @FXML    private Label markLabel;
    @FXML    private ToggleGroup markToggleGroup;
    @FXML    private RadioButton pointMarkRButton;
    @FXML    private RadioButton xMarkRButton;
    @FXML    private RadioButton oMarkRButton;
    @FXML    private RadioButton charMarkRButton;
    @FXML    private TextField charMarkText;
    @FXML    private Label textLabel;
    @FXML    private TextField textForDisplay;
    @FXML    private Label fontName;
    @FXML    private Label fontSize;
    @FXML    private Label fontStyle;
    @FXML    private Button fontButton;
    @FXML    private Label chooseObject;
    @FXML    private Pane grafPane;
    @FXML    private CheckBox fns;
    @FXML    private Label fChoiceLabel2;
    @FXML    private ComboBox fComboBox;
    @FXML    private ComboBox fComboBox2;
    @FXML    private ComboBox objectComboBox;
    @FXML    private CheckBox countCheckBox;
    @FXML    private CheckBox boundariesCheckBox;
    @FXML    private Button maxMinButton;
    @FXML    private TextField classWidthText;
    @FXML    private RadioButton classSizeButton;
    @FXML    private RadioButton numClassButton;
    @FXML private TextField numClasses;

    //other instance variables
    private GrafType gType;
    private ArrayList<GrafObject> tempGrafList = GrafProg.getGrafList();

    public void initialize(){
        fontName.setText(javafx.scene.text.Font.getDefault().getName());
        fontSize.setText(javafx.scene.text.Font.getDefault().getSize()+"  ");
        fontStyle.setText(javafx.scene.text.Font.getDefault().getStyle());
        hideAll();
    }

    //Button responses tied to GUI
    @FXML
    private void onCreateButtonClicked(ActionEvent e){
            addGrafObject();
            objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
    }

    @FXML
    private void onDiscardButtonClicked(ActionEvent e){
        resetDialog();
        //GrafProg.getGrafPanel().repaint();
    }

    @FXML
    private void onExitButtonClicked(ActionEvent e){
        addGrafObject();
        GrafProg.setGrafList(tempGrafList);
        GrafProg.setMessage1("changes saved");
        resetDialog();
        //GrafProg.getGrafPanel().repaint();
    }

    @FXML
    private void onEditButtonClicked(ActionEvent e){
        try {
            if (objectComboBox.getValue().equals("Object") || objectComboBox.getValue().equals("")) {
                if (getTempGrafList().size() > 1) objectComboBox.setValue(getTempGrafList().get(1));
                else return;
            }
        }catch(NullPointerException nullPointer){return;}
        GrafObject currentObject = (GrafObject) objectComboBox.getValue();
        currentObject.loadObjectFields(this);
    }

    @FXML
    private void onDeleteButtonClicked(ActionEvent e){
        try {
            if (objectComboBox.getValue().equals("Object") || objectComboBox.getValue().equals("")) {
                if (getTempGrafList().size() > 1) objectComboBox.setValue(getTempGrafList().get(1));
                else return;
            }
        }catch(NullPointerException nullPointer){return;}
         GrafObject currentObject = (GrafObject)objectComboBox.getValue();
         int index = findGrafObject(currentObject, tempGrafList);
         if (index != -1) tempGrafList.remove(index);
         objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
    }

    @FXML
    private void onClearButtonClicked(ActionEvent e){
        for (int i = 1; i<tempGrafList.size(); i++){
                if (tempGrafList.get(i).getType().equals(gType)) {
                tempGrafList.remove(i);
                i--;
            }
        }
        objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
    }

    @FXML
    private void onFontButtonClicked(ActionEvent e){
        java.awt.Font awtFont = FontDialog.fxFontToAwtFont(fontName.getFont());
        FontDialog fontDialog = new FontDialog(awtFont);
        awtFont = fontDialog.showFontDialog();
        javafx.scene.text.Font fxFont = FontDialog.awtFontToFxFont(awtFont);
        fontName.setFont(fxFont);
        fontName.setText(fxFont.getName()+"  ");
        fontSize.setText(fxFont.getSize()+"  ");
        fontStyle.setText(fxFont.getStyle());

    }



    //display appropriate dialog setup for object editing
    @FXML
    public void showFxEntryDialog()
    {
            Platform.runLater(new Runnable() {
            @Override public void run() {
                gType = GrafType.FUNCTION;
                objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
                GrafProg.getDialogStage().setTitle("FUNCTION");
                fxLabel.setVisible(true);
                functionString.setVisible(true);
                functionString.setEditable(true);
                chooseObject.setText("Choose FUNCTION");
                //pack();
            }
        });
    }

    @FXML
    public void showFxValueDialog()
    {
        gType = GrafType.FVALUE;
        Platform.runLater(new Runnable() {
            @Override public void run() {
                functionString.setVisible(true);
                functionString.setEditable(false);
                objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
                GrafProg.getDialogStage().setTitle("VALUE");
                showFunctionChooser();
                showX1();
                showMarks();
                chooseObject.setText("Choose VALUE");

            }
        });
    }

    @FXML
    public void showFxTangentDialog()
    {
        gType = GrafType.TANGENT;
        Platform.runLater(new Runnable() {
            @Override public void run() {
                functionString.setVisible(true);
                functionString.setEditable(false);
                objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
                GrafProg.getDialogStage().setTitle("TANGENT");
                showFunctionChooser();
                showX1();
                showMarks();
                chooseObject.setText("Choose TANGENT");
            }
        });
    }

    @FXML
    public void showFxChordDialog()
    {
        gType = GrafType.CHORD;
        Platform.runLater(new Runnable() {
            @Override public void run() {
                functionString.setVisible(true);
                functionString.setEditable(false);
                objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
                GrafProg.getDialogStage().setTitle("FCHORD");
                showFunctionChooser();
                showX1X2();
                showMarks();
                chooseObject.setText("Choose FCHORD");

            }
        });
    }

    @FXML
    public void showFxZerosDialog()
    {
        gType = GrafType.FZERO;
        Platform.runLater(new Runnable() {
            @Override public void run() {
                functionString.setVisible(true);
                functionString.setEditable(false);
                objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
                GrafProg.getDialogStage().setTitle("FZEROS");
                showFunctionChooser();
                showMarks();
                showX1X2();
                showDx();
                chooseObject.setText("Choose FZEROS");
            }
        });
    }
    @FXML
    public void showFxIntegralDialog()
    {gType = GrafType.INTEGRAL;

        Platform.runLater(new Runnable() {
            @Override public void run() {
                functionString.setVisible(true);
                functionString.setEditable(false);
                objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
                GrafProg.getDialogStage().setTitle("INTEGRAL");
                showFunctionChooser();
                fillColorPicker.setVisible(true);
                fillLabel.setVisible(true);
                showX1X2();
                showN();
                chooseObject.setText("Choose INTEGRAL");

            }
        });
    }

    @FXML
    public void showColumnPlotDialog()
    {gType = GrafType.COLUMN;

        Platform.runLater(new Runnable() {
            @Override public void run() {
                objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
                GrafProg.getDialogStage().setTitle("COLUMN PLOT");
                showColumnChooser();
                showMarks();
                showConnectedCheckBox();
                chooseObject.setText("Choose COLUMN");

            }
        });
    }

    @FXML
    public void showBoxplotDialog()
    {gType = GrafType.BOXPLOT;
        Platform.runLater(new Runnable() {
            @Override public void run() {
                objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
                GrafProg.getDialogStage().setTitle("BOXPLOT");
                showColumnChooser();
                showFNS();
                chooseObject.setText("Choose BOXPLOT");

            }
        });
    }

    @FXML
    public void showHistogramDialog()
    {gType = GrafType.HISTOGRAM;
        Platform.runLater(new Runnable() {
            @Override public void run() {
                objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
                GrafProg.getDialogStage().setTitle("HISTOGRAM");
                showColumnChooser();
                showX1X2();
                x1Label.setText("Begin");
                x2Label.setText("End");
                showHistoBoxes();
                chooseObject.setText("Choose HISTOGRAM");

            }
        });
    }

    public void showPointDialog() {
        gType = GrafType.POINT;

        Platform.runLater(new Runnable() {
            @Override public void run() {
                functionString.setVisible(true);
                objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
                GrafProg.getDialogStage().setTitle("POINT");
                showMarks();
                showX1Y1();
                chooseObject.setText("Choose POINT");

            }
        });
    }

    public void showTextDialog() {
        gType = GrafType.TEXT;

        Platform.runLater(new Runnable() {
            @Override public void run() {
                objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
                GrafProg.getDialogStage().setTitle("TEXT");
                showTextAndFontButton();
                showX1Y1();
                chooseObject.setText("Choose TEXT");

            }
        });
    }

    public void showLineSegmentDialog() {
        gType = GrafType.LINESEGMENT;

        Platform.runLater(new Runnable() {
            @Override public void run() {
                objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
                GrafProg.getDialogStage().setTitle("LINESEGMENT");
                showMarks();
                showX1Y1();
                showX2Y2();
                chooseObject.setText("Choose LINESEGMENT");

            }
        });
    }

    public void showRectangleDialog() {
        gType = GrafType.RECTANGLE;

        Platform.runLater(new Runnable() {
            @Override public void run() {
                objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
                GrafProg.getDialogStage().setTitle("RECTANGLE");
                fillColorPicker.setVisible(true);
                fillLabel.setVisible(true);
                showX1Y1();
                showWH();
                chooseObject.setText("Choose RECTANGLE");

            }
        });
    }

    public void showEllipseDialog() {
        gType = GrafType.ELLIPSE;

        Platform.runLater(new Runnable() {
            @Override public void run() {
                objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
                GrafProg.getDialogStage().setTitle("ELLIPSE");
                fillColorPicker.setVisible(true);
                fillLabel.setVisible(true);
                showX1Y1();
                showWH();
                chooseObject.setText("Choose ELLIPSE");

            }
        });
    }

    public void showCircleDialog() {
        gType = GrafType.CIRCLE;

        Platform.runLater(new Runnable() {
            @Override public void run() {
                objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(gType)));
                GrafProg.getDialogStage().setTitle("CIRCLE");
                fillColorPicker.setVisible(true);
                fillLabel.setVisible(true);
                showX1Y1();
                showR();
                chooseObject.setText("Choose CIRCLE");

            }
        });
    }



    //private halpers for setting up dialog
    private void showTextAndFontButton(){
        textLabel.setVisible(true);
        textForDisplay.setVisible(true);
        fontName.setVisible(true);
        fontStyle.setVisible(true);
        fontSize.setVisible(true);
        fontButton.setVisible(true);
     }

     private void hideTextAndFontButtons(){
         textLabel.setVisible(false);
         textForDisplay.setVisible(false);
         fontName.setVisible(false);
         fontStyle.setVisible(false);
         fontSize.setVisible(false);
         fontButton.setVisible(false);
     }

    private void showFunctionChooser(){
        fComboBox.setItems(FXCollections.observableArrayList(createFunctionList()));
        fChoiceLabel.setText("Choose f(x): ");
        fChoiceLabel.setVisible(true);
        fComboBox.setVisible(true);
        //fComboBox.setValue("function");
        try {
            fComboBox.setValue(getTempGrafList().get(1));
        }catch (IndexOutOfBoundsException iob){ fComboBox.setValue("function");}
        fxLabel.setVisible(true);
        //functionString.setVisible(true);
        //functionString.setEditable(false);
    }

    private void showColumnChooser(){
        fComboBox.setItems(FXCollections.observableArrayList(GrafProg.getData().getHeaderArrayCdr()));
        fChoiceLabel.setText("Choose Column: ");
        fChoiceLabel.setVisible(true);
        fComboBox.setVisible(true);
        //fComboBox.setValue("function");
        try {
            fComboBox.getSelectionModel().select(0);
        }catch (IndexOutOfBoundsException iob){ fComboBox.setValue("Column");}

    }

    private void showColumnChooser2(){
        fComboBox2.setItems(FXCollections.observableArrayList(GrafProg.getData().getHeaderArrayCdr()));
        fChoiceLabel2.setText("Choose Column: ");
        fChoiceLabel2.setVisible(true);
        fComboBox2.setVisible(true);
        //fComboBox.setValue("function");
        try {
            fComboBox2.getSelectionModel().select(0);
        }catch (IndexOutOfBoundsException iob){ fComboBox2.setValue("Column");}

    }


    private void showMarks()
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

    private void showX1()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                x1Text.setVisible(true);
                x1Label.setVisible(true);

            }
        });
    }

    private void showX2()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                x2Text.setVisible(true);
                x2Label.setVisible(true);

            }
        });
    }

    private void showX1X2()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showX1();
                x2Label.setVisible(true);
                x2Text.setVisible(true);

            }
        });
    }

    private void showX1Y1()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showX1();
                y1Label.setVisible(true);
                y1Text.setVisible(true);

            }
        });
    }

    private void showX2Y2()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showX2();
                y2Label.setVisible(true);
                y2Text.setVisible(true);

            }
        });
    }

    private void showWH()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showX1Y1();
                x2Label.setVisible(true);
                x2Text.setVisible(true);
                x2Label.setText("Width");
                y2Label.setVisible(true);
                y2Text.setVisible(true);y2Label.setText("Height");


            }
    });
    }

    private void showX1Y1X2Y2()
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

    private void showN()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                nLabel.setVisible(true);
                nLabel.setText("n:");
                nText.setVisible(true);
            }
        });
    }


    private void showDx()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                nLabel.setVisible(true);
                nLabel.setText("dx:");
                nText.setVisible(true);
            }
        });
    }

    private void showR()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                x2Label.setVisible(true);
                x2Label.setText("r:");
                x2Text.setVisible(true);
            }
        });
    }

    private void showFNS(){
        Platform.runLater(new Runnable() {
            @Override public void run() {
                fns.setVisible(true);
                fns.setText("FNS");

            }
        });
    }

    private void showConnectedCheckBox(){
        Platform.runLater(new Runnable() {
            @Override public void run() {
                fns.setVisible(true);
                fns.setText("Connected");

            }
        });
    }

    private void showHistoBoxes(){
        Platform.runLater(new Runnable() {
            @Override public void run() {
                boundariesCheckBox.setVisible(true);
                countCheckBox.setVisible(true);
                fns.setVisible(true);
                fns.setText("Relative Frequency");
                fillColorPicker.setVisible(true);
                fillLabel.setVisible(true);
                maxMinButton.setVisible(true);
                classWidthText.setVisible(true);
                numClassButton.setVisible(true);
                classSizeButton.setVisible(true);

            }
        });
    }


    //had to make this one public for call from outside - probably should refactor relationship
    public void hideAll() //change to hide all
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                //functionString.setText("");
                fChoiceLabel.setVisible(false);
                fComboBox.setVisible(false);
                fChoiceLabel2.setVisible(false);
                fComboBox2.setVisible(false);
                fxLabel.setVisible(false);
                functionString.setVisible(false);
                x1Text.setVisible(false);
                x1Label.setText("x1");
                x2Label.setText("x2");
                x1Label.setVisible(false);
                y1Label.setVisible(false);
                y1Text.setVisible(false);
                nLabel.setVisible(false);
                nText.setVisible(false);
                x2Label.setVisible(false);
                x2Label.setText("x2:");
                x2Text.setVisible(false);
                y2Label.setVisible(false);
                y2Label.setText("y2:");
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
                fillColorPicker.setVisible(false);
                fillLabel.setVisible(false);
                hideTextAndFontButtons();
                boundariesCheckBox.setVisible(false);
                countCheckBox.setVisible(false);
                maxMinButton.setVisible(false);
                classWidthText.setVisible(false);
                classSizeButton.setVisible(false);
                numClassButton.setVisible(false);
            }
        });
    }


    private void resetDialog(){
        GrafProg.getDialogStage().hide();
        functionString.setText("");
        x1Text.setText("");
        x2Text.setText("");
        y1Text.setText("");
        y2Text.setText("");
        nText.setText("");
        textForDisplay.setText("");
        getGrafColorPicker().setValue(javafx.scene.paint.Color.BLACK);
        getFillColorPicker().setValue(javafx.scene.paint.Color.BLACK);
        GrafProg.getDialogController().hideAll();  //this should hide all; add points for each box
        GrafProg.getGrafPanel().repaint();
    }

    /*private void saveChanges(){

        GrafProg.setGrafList(tempGrafList);
        GrafProg.setMessage1("changes saved");
    }*/

    private void addGrafObject(){

        GrafObject newObject = GrafObject.createGrafObjectFromController(gType);
        if (!newObject.isValidInput(this)) {
            GrafProg.setMessage1("not a valid "+gType);
            return;
        }
        newObject = GrafObject.createGrafObjectFromController(this, gType);
        if (!duplicate(newObject)) getTempGrafList().add(newObject);
    }

    private boolean duplicate(GrafObject newObject){
        for (GrafObject grafObject: tempGrafList) {
            if (grafObject.getType().equals(gType))
                if (grafObject.deepEquals(newObject)) return true;
        }
        return false;
    }

    private int findGrafObject(GrafObject grafObject, ArrayList<GrafObject> list){
            for (int i = 0; i<list.size(); i++) if (grafObject == list.get(i)) return i;
            return -1;
    }

    private ArrayList<GrafObject> createFunctionList(){
        return createObjectList(GrafType.FUNCTION);
    }

    private ArrayList<GrafObject> createObjectList(GrafType gtype){
        ArrayList<GrafObject> grafObjects = new ArrayList<>();
        for (GrafObject g: tempGrafList){
            if (g.getType() == gtype) grafObjects.add(g);
        }
        //return (GrafObject[]) grafObjects.toArray();
        return grafObjects;
    }

    public void functionChosen(ActionEvent actionEvent) {
        if (functionString.isVisible()) {
            try {
                if (!fComboBox.getValue().equals("function")) {
                    GrafFunction gf = (GrafFunction) (fComboBox.getValue());
                    functionString.setText(gf.getFunction());
                }
            } catch (ClassCastException cce) {
                //System.out.println("Choose a Function.");
            } catch (NullPointerException npe) {
            }
        }else{
            try {
                if (!fComboBox.getValue().equals("Column")) {
                    fComboBox.setItems(FXCollections.observableArrayList(GrafProg.getData().getHeaderArrayCdr()));

                }
            } catch (ClassCastException cce) {
                //System.out.println("Choose a Function.");
            } catch (NullPointerException npe) {
            }
        }
    }




    public void checkForEnter(KeyEvent keyEvent) {
        if ((keyEvent.getCode() == KeyCode.ENTER) && (functionString.isEditable())){
            onCreateButtonClicked(new ActionEvent());

        }

    }

    public String getFunctionString(){return functionString.getText();}
    public void setFunctionString(String s){ functionString.setText(s);}
    public boolean functionStringIsVisible(){
        return functionString.isVisible();
    }


    public ArrayList<GrafObject> getTempGrafList(){return tempGrafList;}
    public void setTempGrafList(ArrayList<GrafObject> t){ tempGrafList = GrafProg.getGrafList();}

    public java.awt.Color getGrafColor(){
        //System.out.println(grafColorPicker.getValue());
        javafx.scene.paint.Color fxColor = grafColorPicker.getValue();
        java.awt.Color awtColor = new java.awt.Color((float)fxColor.getRed(), (float)fxColor.getGreen(), (float) fxColor.getBlue(), (float)fxColor.getOpacity());
        return awtColor;}

    public Color getFillColor(){
        //System.out.println(grafColorPicker.getValue());
        javafx.scene.paint.Color fxColor = fillColorPicker.getValue();
        java.awt.Color awtColor = new Color((float)fxColor.getRed(), (float)fxColor.getGreen(), (float) fxColor.getBlue(), (float)fxColor.getOpacity());
        return awtColor;}
    public void setfillColor(Color awtColor){
        javafx.scene.paint.Color fxColor = new javafx.scene.paint.Color((double)awtColor.getRed(), (double)awtColor.getRed(), (double)awtColor.getBlue(), 1);
        getFillColorPicker().setValue(fxColor);
    }

    public ColorPicker getFillColorPicker() {return fillColorPicker; }
    public void setFillColorPicker(ColorPicker fillColorPicker) {  this.fillColorPicker = fillColorPicker; }

    public ColorPicker getGrafColorPicker() { return grafColorPicker; }
    public void setGrafColorPicker(ColorPicker grafColorPicker) {this.grafColorPicker = grafColorPicker; }

    public String getX1() { return x1Text.getText(); }
    public void setX1(String x1String) { this.x1Text.setText(x1String); }

    public String getX2(){return x2Text.getText();}
    public void setX2(String x2String){this.x2Text.setText(x2String);}

    public String getY1() { return y1Text.getText(); }
    public void setY1(String x1String) { this.y1Text.setText(x1String); }

    public String getY2(){return y2Text.getText();}
    public void setY2(String x2String){this.y2Text.setText(x2String);}

    public String getDx(){return nText.getText();}
    public void setDx(String dx){ nText.setText(dx);}

    public String getNText(){return nText.getText();}
    public void setnText(int n){ nText.setText(""+n);}




    public String getDialogMark(){
        if (markToggleGroup.getSelectedToggle().toString().contains(".")) return ".";
        else if (markToggleGroup.getSelectedToggle().toString().contains("x")) return "x";
        else if (markToggleGroup.getSelectedToggle().toString().contains("o")) return "o";
        else return charMarkText.toString();
    }

    public void settDialogMark(String mark){
           if (mark.equals(".")) pointMarkRButton.setSelected(true);
           else if (mark.equals("x")) xMarkRButton.setSelected(true);
           else if (mark.equals("0")) oMarkRButton.setSelected(true);
           else {
               charMarkRButton.setSelected(true);
               charMarkText.setText(mark);
           }
    }


    public TextField getTextForDisplay() {
        return textForDisplay;
    }

    public void setTextForDisplay(TextField textForDisplay) {
        this.textForDisplay = textForDisplay;
    }

    public RadioButton getCharMarkRButton() {
        return charMarkRButton;
    }

    public void setCharMarkRButton(RadioButton charMarkRButton) {
        this.charMarkRButton = charMarkRButton;
    }

    public String getFontName() {
        return this.fontName.getText();
    }

    public void setFontName(String fontNameString) {
        this.fontName.setText(fontNameString);
    }

    public String getFontStyleText() {
        return fontStyle.getText();
    }

    public void setFontStyleText(String fontStyle) {
        this.fontStyle.setText(fontStyle);
    }

    public void setFontSizeText(String fSize){fontSize.setText(fSize); }

    public boolean getFNS() {
        return fns.isSelected();
    }
    public void setFNS(boolean fnsFlag) {
        this.fns.setSelected(fnsFlag);
    }

    public void setColumn1ChooserColumn(int choice){
        fComboBox.getSelectionModel().select(choice);
    }

    public int getColumn1ChooserColumn(){
        return fComboBox.getSelectionModel().getSelectedIndex()+1;
    }

    public String getClassWidthText(){        return classWidthText.getText();    }
    public void setClassWidthText(String s){ classWidthText.setText(s);}

    public boolean isConnected(){
        return fns.isSelected();
    }

    public Object getColumn1VaLue(){
        return fComboBox.getValue();
    }

    public CheckBox getCountCheckBox() {        return countCheckBox;    }
    public void setCountCheckBox(CheckBox countCheckBox) {        this.countCheckBox = countCheckBox;    }

    public CheckBox getBoundariesCheckBox() {        return boundariesCheckBox;    }
    public void setBoundariesCheckBox(CheckBox boundariesCheckBox) {        this.boundariesCheckBox = boundariesCheckBox;    }

    public RadioButton getNumClassButton() {       return numClassButton;    }
    public void setNumClassButton(RadioButton numClassButton) {        this.numClassButton = numClassButton;    }

    public RadioButton getClassSizeButton(){ return classSizeButton;}
    public void setClassSizeButton(RadioButton classSizeButton) {        this.classSizeButton = classSizeButton;    }

    public int getNumClasses(){
        if (GrafInputHelpers.isInt(numClasses.getText())) return Integer.parseInt(numClasses.getText());
        if (GrafInputHelpers.isDouble((numClasses.getText()))) return (int)(Math.round(Double.parseDouble(numClasses.getText())));
        return 7;
    }


}