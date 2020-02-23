//package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javax.crypto.SealedObject;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class GrafDialogController {



    //UI Areas
    @FXML   private HBox functionChoiceHBox;
    @FXML   private HBox functionStringBox;
    @FXML   private HBox xy1PointBox;
    @FXML   private HBox xy2PointBox;
    @FXML   private HBox markButtonBox;
    @FXML   private HBox textHBox;
    @FXML   private HBox fontHBox;
    @FXML   private VBox checkboxVBox;

    //instance variables tied to GUI
    //fxml variables
    @FXML    private ColorPicker fillColorPicker;
    @FXML    private Label fillLabel;
    @FXML    private ColorPicker grafColorPicker;
    @FXML    private Label chooseObject;
    @FXML    private Pane grafPane;
    @FXML    private TextField msg;
    @FXML    private ComboBox<Object> objectComboBox;

    //function/column input/choosing
    private Label fChoiceLabel = new Label("fx:");
    private ComboBox<Object> fComboBox = new ComboBox();
    private Label fChoiceLabel2 = new Label("Output Column");
    private ComboBox<Object> fComboBox2 = new ComboBox();
    private Label fxLabel = new Label("fx:");
    private TextField functionString = new TextField("");

    //Points
    private TextField x1Text = new TextField("");
    private Label x1Label = new Label("x1:");
    private Label y1Label = new Label("y1:");
    private TextField y1Text = new TextField("");
    private Label nLabel = new Label("n:");
    private TextField nText = new TextField("");
    private Button maxMinButton =  new Button("max/min");
    private Label x2Label = new Label("x2");
    private TextField x2Text = new TextField("");
    private Label y2Label = new Label("Y2");
    private TextField y2Text = new TextField("");

    //Histogram setup
    private TextField classWidthText = new TextField("");
    private ToggleGroup classToggleGroup = new ToggleGroup();
    private RadioButton classSizeButton = new RadioButton("Class Size:");
    private RadioButton numClassButton = new RadioButton("# Classes");
    private TextField numClasses = new TextField("");
    private Label classLabel = new Label("Class Width");

    //mark chooser
    private Label markLabel = new Label("Mark:");
    private ToggleGroup markToggleGroup = new ToggleGroup();
    private RadioButton pointMarkRButton = new RadioButton(".");
    private RadioButton xMarkRButton = new RadioButton("x");
    private RadioButton oMarkRButton = new RadioButton("o");
    private RadioButton charMarkRButton = new RadioButton("char");
    private TextField charMarkText = new TextField("x");

    //Text and Font
    private Label textLabel = new Label("Text: ");
    private TextField textForDisplay = new TextField("");
    private Label fontName = new Label("Font");
    private Label fontSize = new Label("Size");
    private Label fontStyle = new Label("Style");
    private Button fontButton =  new Button("Font");

    //checkboxes
    private CheckBox boundariesCheckBox = new CheckBox("Label by Boundaries");
    private CheckBox countCheckBox = new CheckBox("Show Counts");
    private CheckBox fns = new CheckBox("FNS");

    //other instance variables
    //private GrafType gType;
    private GrafObject workingObject;
    private ArrayList<GrafObject> tempGrafList = GrafProg.getGrafList();

    public void initialize(){
        boundariesCheckBox.setSelected(true);
        countCheckBox.setSelected(true);
        x1Text.setPrefWidth(75);
        x2Text.setPrefWidth(75);
        y1Text.setPrefWidth(75);
        y2Text.setPrefWidth(75);
        numClasses.setPrefWidth(75);
        textForDisplay.setPrefWidth(150);
        classWidthText.setPrefWidth(75);
        fontName.setPrefWidth(150);
        fontStyle.setPrefWidth(75);
        fontSize.setPrefWidth(50);
        numClassButton.setSelected(true);
        numClasses.setText("7");
        xMarkRButton.setSelected(true);
        classSizeButton.setToggleGroup(classToggleGroup);
        numClassButton.setToggleGroup(classToggleGroup);
        xMarkRButton.setToggleGroup(markToggleGroup);
        oMarkRButton.setToggleGroup(markToggleGroup);
        pointMarkRButton.setToggleGroup(markToggleGroup);
        charMarkRButton.setToggleGroup(markToggleGroup);
        fontName.setText(javafx.scene.text.Font.getDefault().getName());
        fontSize.setText(javafx.scene.text.Font.getDefault().getSize()+"  ");
        fontStyle.setText(javafx.scene.text.Font.getDefault().getStyle());
        msg.setText("");

        hideAll();
    }

    //Button responses tied to GUI
    @FXML
    private void onCreateButtonClicked(ActionEvent e){
            addGrafObject();
            objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(getGrafType())));
    }

    @FXML
    private void onDiscardButtonClicked(ActionEvent e){
        GrafProg.setMessage2("Changes Discarded");
        resetDialog();
        GrafProg.getGrafPanel().repaint();
    }

    @FXML
    private void onExitButtonClicked(ActionEvent e){
        boolean ok = addGrafObject();
        if (ok) {
            GrafProg.setGrafList(tempGrafList);
            GrafProg.setMessage1("changes saved");
            resetDialog();
        }
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
         objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(getGrafType())));
    }

    @FXML
    private void onClearButtonClicked(ActionEvent e){
        for (int i = 1; i<tempGrafList.size(); i++){
                if (tempGrafList.get(i).getType().equals(getGrafType())) {
                tempGrafList.remove(i);
                i--;
            }
        }
        objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(getGrafType())));
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

    private void onMaxMin(ActionEvent actionEvent) {
        Double[] currentColumn = TableColumnActions.getColumnValues(getColumn1ChooserColumn(), getData());
        setX1(""+GrafStats.getMin(currentColumn));
        setX2(""+GrafStats.getMax(currentColumn));
    }



    //display appropriate dialog setup for object editing

    /*private void setTextandTitle(GrafObject workingObject){
        GrafType objectType = workingObject.getType();
        String objectTypeString = workingObject.getType().toString();
        objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(objectType)));
        objectComboBox.setValue(objectTypeString);
        GrafProg.getDialogStage().setTitle(objectTypeString);
        chooseObject.setText("Choose "+objectTypeString);
    }*/

    @FXML
    void showFxEntryDialog()
    {
            workingObject = new GrafFunction();
            Platform.runLater(new Runnable() {
            @Override public void run() {
                fxLabel.setVisible(true);
                showFunctionString(true);
                GrafDialogView.setTextandTitle();
            }
        });
    }

    @FXML
    void showFxValueDialog()
    {
        workingObject = new GrafValue();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showFunctionString(false);
                showFunctionChooser();
                showX1();
                x1Label.setText("x1:");
                showMarks();
                GrafDialogView.setTextandTitle();

            }
        });
    }

    @FXML
    void showFxTangentDialog()
    {
        workingObject = new GrafTangent();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showFunctionString(false);
                showFunctionChooser();
                showX1();
                x1Label.setText("x1:");
                showMarks();
                GrafDialogView.setTextandTitle();
            }
        });
    }

    @FXML
    void showFxChordDialog()
    {
        workingObject = new GrafChord();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showFunctionString(false);
                showFunctionChooser();
                showX1X2();
                x1Label.setText("x1:");
                x2Label.setText("x2:");
                showMarks();
                GrafDialogView.setTextandTitle();

            }
        });
    }

    @FXML
    void showFxZerosDialog()
    {
        workingObject = new GrafZeros();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showFunctionString(false);
                showFunctionChooser();
                showMarks();
                showX1();
                x1Label.setText("Start:");
                showX2();
                x2Label.setText("End:");
                showDx();
                GrafDialogView.setTextandTitle();
            }
        });
    }
    @FXML
    void showFxIntegralDialog()
    {
        workingObject = new GrafIntegral();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showFunctionString(false);
                showFunctionChooser();
                fillColorPicker.setVisible(true);
                fillLabel.setVisible(true);
                showX1();
                showN();
                showX2();
                x1Label.setText("Begin:");
                x2Label.setText("End:");
                GrafDialogView.setTextandTitle();

            }
        });
    }

    @FXML
    void showColumnPlotDialog()
    {
        workingObject = new GrafColumnPlot();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showColumnChooser();
                showMarks();
                showConnectedCheckBox();
                GrafDialogView.setTextandTitle();

            }
        });
    }

    @FXML
    void showBoxplotDialog()
    {
        workingObject = new GrafBoxPlot();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showColumnChooser();
                showFNS();
                setFNS(true);
                GrafDialogView.setTextandTitle();
            }
        });
    }

    @FXML
    void showHistogramDialog()
    {
        workingObject = new GrafHistogram();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                setupHisto();
                GrafDialogView.setTextandTitle();
            }
        });
    }

    void showFreqPolyDialog() {
        workingObject = new GrafFreqPolygon();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                setupHisto();
                GrafDialogView.setTextandTitle();
            }
        });
    }

    void showOgiveDialog() {
        workingObject = new GrafOgive();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                setupHisto();
                GrafDialogView.setTextandTitle();
            }
        });
    }

    void showScatterDialog() {
        workingObject = new GrafScatterPlot();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showColumnChooser();
                showColumnChooser2();
                showMarks();
                showConnectedCheckBox();
                GrafDialogView.setTextandTitle();

            }
        });

    }


    void showPointDialog() {
        workingObject = new GrafPoint();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showMarks();
                showX1Y1();
                x1Label.setText("x1:");
                y1Label.setText("y1:");
                GrafDialogView.setTextandTitle();

            }
        });
    }



    void showTextDialog() {
        workingObject = new GrafText();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showTextAndFontButton();
                showX1Y1();
                x1Label.setText("x1:");
                y1Label.setText("y1:");
                fontButton.setOnAction((e) -> { onFontButtonClicked(e);});
                GrafDialogView.setTextandTitle();

            }
        });
    }

    void showLineSegmentDialog() {
        workingObject = new GrafSegment();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showMarks();
                showX1Y1();
                showX2Y2();
                x1Label.setText("x1:");
                y1Label.setText("y1:");
                x2Label.setText("x2:");
                y2Label.setText("y2:");
                GrafDialogView.setTextandTitle();

            }
        });
    }

    void showRectangleDialog() {
        workingObject = new GrafRectangle();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                fillColorPicker.setVisible(true);
                fillLabel.setVisible(true);
                showX1Y1();
                showWH();
                x1Label.setText("x1:");
                y1Label.setText("y1:");
                x2Label.setText("width:");
                y2Label.setText("height:");
                GrafDialogView.setTextandTitle();

            }
        });
    }

    void showEllipseDialog() {
        workingObject = new GrafEllipse();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                fillColorPicker.setVisible(true);
                fillLabel.setVisible(true);
                showX1Y1();
                showWH();
                x1Label.setText("x1:");
                y1Label.setText("y1:");
                x2Label.setText("width:");
                y2Label.setText("height:");
                GrafDialogView.setTextandTitle();

            }
        });
    }

    void showCircleDialog() {
        workingObject = new GrafCircle();
        Platform.runLater(new Runnable() {
            @Override public void run() {
                fillColorPicker.setVisible(true);
                fillLabel.setVisible(true);
                showX1Y1();
                x1Label.setText("x1:");
                y1Label.setText("y1:");
                x2Label.setText("r:");
                showR();
                GrafDialogView.setTextandTitle();  }
        });
    }



    //private halpers for setting up dialog
    private void showTextAndFontButton(){
        textHBox.getChildren().add(textLabel);
        textHBox.getChildren().add(textForDisplay);
        fontHBox.getChildren().add(fontButton);
        fontHBox.getChildren().add(fontName);
        fontHBox.getChildren().add(fontStyle);
        fontHBox.getChildren().add(fontSize);
        fontHBox.setVisible(true);
        textForDisplay.setVisible(true);
        fontName.setVisible(true);
        fontStyle.setVisible(true);
        fontSize.setVisible(true);
        fontButton.setVisible(true);
     }


    private void showFunctionChooser(){

        functionChoiceHBox.getChildren().add(fChoiceLabel);
        functionChoiceHBox.getChildren().add(fComboBox);
        fComboBox.setOnAction((e) -> { functionChosen();});
        fChoiceLabel.setVisible(true);
        fComboBox.setVisible(true);
        fComboBox.setItems(FXCollections.observableArrayList(createFunctionList()));
        fChoiceLabel.setText("Choose f(x): ");
        fChoiceLabel.setVisible(true);
        fComboBox.setVisible(true);
        try {
            fComboBox.setValue(getFirstInTempList(GrafType.FUNCTION));
            functionChosen();
        }catch (NullPointerException npe) {
            fComboBox.setValue("function");
        }
        fxLabel.setVisible(true);

    }

    private void showFunctionString(boolean editable){
        functionStringBox.getChildren().add(fxLabel);
        functionStringBox.getChildren().add(functionString);
        fxLabel.setVisible(true);
        functionString.setVisible(true);
        functionString.setEditable(editable);

    }

    private void showColumnChooser(){
        functionChoiceHBox.getChildren().add(fChoiceLabel);
        functionChoiceHBox.getChildren().add(fComboBox);
        fComboBox.setOnAction((e) -> { functionChosen();});
        //fComboBox.setItems(FXCollections.observableArrayList(TableHeaderActions.getHeaderArrayCdr(GrafProg.getData())));
        TableHeaderActions.setComboBox(fComboBox, GrafProg.getData());
        fChoiceLabel.setText("Choose Column: ");
        fChoiceLabel.setVisible(true);
        fComboBox.setVisible(true);
        //fComboBox.setValue("function");
        try {
            fComboBox.getSelectionModel().select(0);
        }catch (IndexOutOfBoundsException iob){ fComboBox.setValue("Column");}
    }

    private void showColumnChooser2(){
        functionChoiceHBox.getChildren().add(fChoiceLabel2);
        functionChoiceHBox.getChildren().add(fComboBox2);
        fComboBox.setOnAction((e) -> { functionChosen();});

        fComboBox2.setItems(FXCollections.observableArrayList(TableHeaderActions.getHeaderArrayCdr(GrafProg.getData())));
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
                markButtonBox.getChildren().add(markLabel);
                markButtonBox.getChildren().add(pointMarkRButton);
                markButtonBox.getChildren().add(xMarkRButton);
                markButtonBox.getChildren().add(oMarkRButton);
                markButtonBox.getChildren().add(charMarkRButton);
                markButtonBox.getChildren().add(charMarkText);
                markLabel.setVisible(true);
                pointMarkRButton.setVisible(true);
                xMarkRButton.setVisible(true);
                oMarkRButton.setVisible(true);
                charMarkRButton.setVisible(true);
                charMarkText.setVisible(true);
            }
        });
    }

    private void showX1()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                //xy1PointBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
                xy1PointBox.getChildren().add(x1Label);
                //x1Label.setText("X1:");
                xy1PointBox.getChildren().add(x1Text);
                x1Text.setVisible(true);
                x1Label.setVisible(true);

            }
        });
    }

    private void showY1()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                xy1PointBox.getChildren().add(new Separator(Orientation.VERTICAL));
                xy1PointBox.getChildren().add(y1Label);
                //y1Label.setText("Y1:");
                xy1PointBox.getChildren().add(y1Text);
                y1Text.setVisible(true);
                y1Label.setVisible(true);

            }
        });
    }


    private void showX2()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                //xy2PointBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
                xy2PointBox.getChildren().add(x2Label);
                //x2Label.setText("X2:");
                xy2PointBox.getChildren().add(x2Text);
                x2Text.setVisible(true);
                x2Label.setVisible(true);

            }
        });
    }

    private void showY2()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                xy2PointBox.getChildren().add(new Separator(Orientation.VERTICAL));
                xy2PointBox.getChildren().add(y2Label);
                //y2Label.setText("Y2:");
                xy2PointBox.getChildren().add(y2Text);
                y2Text.setVisible(true);
                y2Label.setVisible(true);

            }
        });
    }

    private void showX1X2()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showX1();
                showX2();
               /* x2Label.setVisible(true);
                x2Text.setVisible(true);*/

            }
        });
    }

    private void showX1Y1()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showX1();
                showY1();
            }
        });
    }

    private void showX2Y2()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showX2();
                showY2();
                /*y2Label.setVisible(true);
                y2Text.setVisible(true);*/

            }
        });
    }

    private void showWH()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showX2Y2();
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
                showX2Y2();
                /*x2Label.setVisible(true);
                x2Text.setVisible(true);
                y2Label.setVisible(true);
                y2Text.setVisible(true);*/
            }
        });
    }

    private void showN()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                xy1PointBox.getChildren().add(new Separator(Orientation.VERTICAL));
                xy1PointBox.getChildren().add(nLabel);
                xy1PointBox.getChildren().add(nText);
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
                xy1PointBox.getChildren().add(new Separator(Orientation.VERTICAL));
                xy1PointBox.getChildren().add(nLabel);
                xy1PointBox.getChildren().add(nText);
                nLabel.setVisible(true);
                nLabel.setText("dx:");
                nText.setVisible(true);
                nText.setText(".01");
            }
        });
    }

    private void showR()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                xy2PointBox.getChildren().add(x2Label);
                xy2PointBox.getChildren().add(x2Text);
                x2Label.setVisible(true);
                x2Label.setText("r:");
                x2Text.setVisible(true);
            }
        });
    }

    private void showFNS(){
        Platform.runLater(new Runnable() {
            @Override public void run() {
                checkboxVBox.getChildren().add(fns);
                fns.setVisible(true);
                fns.setText("FNS");

            }
        });
    }

    private void showConnectedCheckBox(){
        Platform.runLater(new Runnable() {
            @Override public void run() {
                checkboxVBox.getChildren().add(fns);
                fns.setVisible(true);
                fns.setText("Connected");

            }
        });
    }

    private void setupHisto(){
        showColumnChooser();
        showX1();
        showX2();
        x1Label.setText("Begin");
        x2Label.setText("End");
        showHistoBoxes();
        onMaxMin(new ActionEvent());
        maxMinButton.setOnAction((e) -> { onMaxMin(e);});

    }

    private void showHistoBoxes(){
        Platform.runLater(new Runnable() {
            @Override public void run() {
                checkboxVBox.getChildren().add(boundariesCheckBox);
                boundariesCheckBox.setVisible(true);
                checkboxVBox.getChildren().add(countCheckBox);
                countCheckBox.setVisible(true);
                checkboxVBox.getChildren().add(fns);
                fns.setVisible(true);
                fns.setText("Relative Frequency");
                fillColorPicker.setVisible(true);
                fillLabel.setVisible(true);
                xy1PointBox.getChildren().add(maxMinButton);
                maxMinButton.setVisible(true);
                xy2PointBox.getChildren().add(new Separator(Orientation.VERTICAL));
                xy2PointBox.getChildren().add(numClassButton);
                numClassButton.setVisible(true);
                xy2PointBox.getChildren().add(numClasses);
                numClasses.setVisible(true);
                xy2PointBox.getChildren().add(new Separator(Orientation.VERTICAL));
                xy2PointBox.getChildren().add(classSizeButton);
                classSizeButton.setVisible(true);
                xy2PointBox.getChildren().add(classWidthText);
                classWidthText.setVisible(true);


            }
        });
    }




    //had to make this one public for call from outside - probably should refactor relationship
    public void hideAll() //change to hide all
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                functionChoiceHBox.getChildren().clear();
                functionStringBox.getChildren().clear();
                xy1PointBox.getChildren().clear();
                xy2PointBox.getChildren().clear();
                markButtonBox.getChildren().clear();
                textHBox.getChildren().clear();
                fontHBox.getChildren().clear();
                checkboxVBox.getChildren().clear();
                fillColorPicker.setVisible(false);
                fillLabel.setVisible(false);
                initTempGrafList();
            }
        });
    }


    private void resetDialog(){
        GrafProg.getDialogStage().hide();
        functionString.setText("");
        x1Text.setText("");
        GrafInputHelpers.setTextFieldColor(getX1TextField(), "black");
        x2Text.setText("");
        GrafInputHelpers.setTextFieldColor(getX2TextField(), "black");
        y1Text.setText("");
        GrafInputHelpers.setTextFieldColor(getY1TextField(), "black");
        y2Text.setText("");
        GrafInputHelpers.setTextFieldColor(getY2TextField(), "black");
        nText.setText("");
        GrafInputHelpers.setTextFieldColor(getNTextField(), "black");
        textForDisplay.setText("");
        GrafInputHelpers.setTextFieldColor(getTextForDisplay(), "black");
        getGrafColorPicker().setValue(javafx.scene.paint.Color.BLACK);
        getFillColorPicker().setValue(javafx.scene.paint.Color.BLACK);
        msg.setText("");
        hideAll();
        GrafProg.getGrafPanel().repaint();
        //setTempGrafList(new ArrayList<>());
    }


    private boolean addGrafObject(){

        GrafObject newObject = workingObject;
        if (!newObject.isValidInput(this)) {
            msg.setText("Not a valid "+ getGrafType());
           return false;
        }
        newObject = newObject.createGrafObjectFromController(this);

        if (!duplicate(newObject)) getTempGrafList().add(newObject);
        msg.setText(newObject.getType()+" added.");
        return true;
    }

    private boolean duplicate(GrafObject newObject){
        for (GrafObject grafObject: tempGrafList) {
            if (grafObject.getType().equals(getGrafType()))
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

    private GrafObject getFirstInTempList(GrafType gt){
        for (GrafObject g: tempGrafList) if (g.getType().equals(gt)) return g;
        return null;
    }

    private void functionChosen() {
        //if (fComboBox.getValue().equals(null)) return;
        if (functionString.isVisible()) {
            try {
                //System.out.println(fComboBox.getValue());
                if (!fComboBox.getValue().equals("function")) {
                    GrafFunction gf = (GrafFunction) (fComboBox.getValue());
                    functionString.setText(gf.getFunction());
                }
            } catch (ClassCastException cce) {
                //System.out.println("Choose a Function.");
            } catch (NullPointerException npe) {
                System.out.println(npe.toString());
            }
        }else{
            try {
                if (!fComboBox.getValue().equals("Column")) {
                    TableHeaderActions.setComboBox(fComboBox, GrafProg.getData());
                    //fComboBox.setItems(FXCollections.observableArrayList(TableHeaderActions.getHeaderArrayCdr(GrafProg.getData())));

                }
            } catch (ClassCastException cce) {
                //System.out.println("Choose a Function.");
            } catch (NullPointerException npe) {
                System.out.println(npe.toString());
            }

        }
    }



    public String getFunctionString(){return functionString.getText();}
    public void setFunctionString(String s){ functionString.setText(s);}
    public boolean functionStringIsVisible(){
        return functionString.isVisible();
    }
    TextField getFunctionStringTextField(){return functionString;}


    public ArrayList<GrafObject> getTempGrafList(){return tempGrafList;}
    public void setTempGrafList(ArrayList<GrafObject> t){ tempGrafList = t;}
    private void initTempGrafList(){ tempGrafList = GrafProg.getGrafList();}

    public java.awt.Color getGrafColor(){
        //System.out.println(grafColorPicker.getValue());
        javafx.scene.paint.Color fxColor = grafColorPicker.getValue();
        java.awt.Color awtColor = new java.awt.Color((float)fxColor.getRed(), (float)fxColor.getGreen(), (float) fxColor.getBlue(), (float)fxColor.getOpacity());
        return awtColor;}

    public java.awt.Color getFillColor(){
        //System.out.println(grafColorPicker.getValue());
        javafx.scene.paint.Color fxColor = fillColorPicker.getValue();
        java.awt.Color awtColor = new Color((float)fxColor.getRed(), (float)fxColor.getGreen(), (float) fxColor.getBlue(), (float)fxColor.getOpacity());
        return awtColor;}


    void setfillColor(Color awtColor){
        javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(getFillColor().getRed(),
                getGrafColor().getGreen(), getGrafColor().getBlue(), 1);
        getFillColorPicker().setValue(fxColor);
    }

    private ColorPicker getFillColorPicker() {return fillColorPicker; }
    public void setFillColorPicker(ColorPicker fillColorPicker) {  this.fillColorPicker = fillColorPicker; }

    ColorPicker getGrafColorPicker() { return grafColorPicker; }
    public void setGrafColorPicker(ColorPicker grafColorPicker) {this.grafColorPicker = grafColorPicker; }

    public String getX1() { return x1Text.getText(); }
    public void setX1(String x1String) { this.x1Text.setText(x1String); }
    public TextField getX1TextField(){return x1Text;}

    public String getX2(){return x2Text.getText();}
    public void setX2(String x2String){this.x2Text.setText(x2String);}
    public TextField getX2TextField(){return x2Text;}

    public String getY1() { return y1Text.getText(); }
    public void setY1(String x1String) { this.y1Text.setText(x1String); }
    TextField getY1TextField(){return y1Text;}

    public String getY2(){return y2Text.getText();}
    public void setY2(String x2String){this.y2Text.setText(x2String);}
    TextField getY2TextField(){return y2Text;}

    String getDx(){return nText.getText();}
    void setDx(String dx){ nText.setText(dx);}
    TextField getDxTextField(){return nText;}

    String getNText(){return nText.getText();}
    public void setnText(int n){ nText.setText(""+n);}
    private TextField getNTextField(){return nText;}




    String getDialogMark(){
        if (markToggleGroup.getSelectedToggle().toString().contains(".")) return ".";
        else if (markToggleGroup.getSelectedToggle().toString().contains("x")) return "x";
        else if (markToggleGroup.getSelectedToggle().toString().contains("o")) return "o";
        else return charMarkText.toString();
    }

    void settDialogMark(String mark){
           if (mark.equals(".")) pointMarkRButton.setSelected(true);
           else if (mark.equals("x")) xMarkRButton.setSelected(true);
           else if (mark.equals("0")) oMarkRButton.setSelected(true);
           else {
               charMarkRButton.setSelected(true);
               charMarkText.setText(mark);
           }
    }

    private GrafType getGrafType(){return workingObject.getType();}

    TextField getTextForDisplay() {
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

    void setFontName(String fontNameString) {
        this.fontName.setText(fontNameString);
    }

    public String getFontStyleText() {
        return fontStyle.getText();
    }

    void setFontStyleText(String fontStyle) {
        this.fontStyle.setText(fontStyle);
    }

    void setFontSizeText(String fSize){fontSize.setText(fSize); }

    boolean getFNS() {
        return fns.isSelected();
    }
    void setFNS(boolean fnsFlag) {      this.fns.setSelected(fnsFlag);    }


    void setColumn1ChooserColumn(int choice){
        fComboBox.getSelectionModel().select(choice);
    }

    int getColumn1ChooserColumn(){
        return fComboBox.getSelectionModel().getSelectedIndex()+1;
    }

    public void setColumn2ChooserColumn(int choice){
        fComboBox2.getSelectionModel().select(choice);
    }

    int getColumn2ChooserColumn(){
        return fComboBox2.getSelectionModel().getSelectedIndex()+1;
    }

    String getClassWidthText(){        return classWidthText.getText();    }
    void setClassWidthText(String s){ classWidthText.setText(s);}
    TextField getClassWidthTextField(){return classWidthText;    }

    boolean isConnected(){
        return fns.isSelected();
    }

    public Object getColumn1VaLue(){
        return fComboBox.getValue();
    }

    CheckBox getCountCheckBox() {        return countCheckBox;    }
    public void setCountCheckBox(CheckBox countCheckBox) {        this.countCheckBox = countCheckBox;    }

    CheckBox getBoundariesCheckBox() {        return boundariesCheckBox;    }
    public void setBoundariesCheckBox(CheckBox boundariesCheckBox) {        this.boundariesCheckBox = boundariesCheckBox;    }

    RadioButton getNumClassButton() {       return numClassButton;    }
    public void setNumClassButton(RadioButton numClassButton) {        this.numClassButton = numClassButton;    }

    RadioButton getClassSizeButton(){ return classSizeButton;}
    public void setClassSizeButton(RadioButton classSizeButton) {        this.classSizeButton = classSizeButton;    }

    int getNumClasses(){
        if (GrafInputHelpers.isInt(numClasses.getText())) return Integer.parseInt(numClasses.getText());
        if (GrafInputHelpers.isDouble((numClasses.getText()))) return (int)(Math.round(Double.parseDouble(numClasses.getText())));
        return 7;
    }

    java.awt.Font getDefaultFont() {
           return FontDialog.fxFontToAwtFont(javafx.scene.text.Font.getDefault());

    }

    public ComboBox getObjectComboBox(){
        return objectComboBox;
    }

    public TextField getMsg() {
        return msg;
    }

    public void setMsg(TextField msg) {
        this.msg = msg;
    }

    public GrafTable getData(){
        return GrafProg.getData();
    }

    public Label getChooseObject() { return chooseObject;  }

    public GrafObject getWorkingObject(){
        return workingObject;
    }





}