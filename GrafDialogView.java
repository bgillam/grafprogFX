/****************************************
 GrafDialogView
 This class implements changes in the active GrafDialog
 All calls to this classes static methods should come through GrafDialogController
 2-20-2020
 Bill Gillam
 */


import GrafUtils.GrafInputHelpers;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class GrafDialogView {
    //function/column input/choosing
    private static Label fChoiceLabel = new Label("fx:");
    private static ComboBox<Object> fComboBox = new ComboBox<Object>();
    private static Label fChoiceLabel2 = new Label("Output Column");
    private static ComboBox<Object> fComboBox2 = new ComboBox<Object>();
    private static Label fxLabel = new Label("fx:");
    private static TextField functionString = new TextField("");

    //Points
    private static TextField x1Text = new TextField("");
    private static Label x1Label = new Label("x1:");
    private static Label y1Label = new Label("y1:");
    private static TextField y1Text = new TextField("");
    private static Label nLabel = new Label("n:");
    private static TextField nText = new TextField("");
    private static Button maxMinButton =  new Button("max/min");
    private static Label x2Label = new Label("x2");
    private static TextField x2Text = new TextField("");
    private static Label y2Label = new Label("Y2");
    private static TextField y2Text = new TextField("");

    //Histogram setup
    private static TextField classWidthText = new TextField("");
    private static ToggleGroup classToggleGroup = new ToggleGroup();
    private static RadioButton classSizeButton = new RadioButton("Class Size:");
    private static RadioButton numClassButton = new RadioButton("# Classes");
    private static TextField numClasses = new TextField("");
    private static Label classLabel = new Label("Class Width");

    //mark chooser
    private static Label markLabel = new Label("Mark:");
    private static ToggleGroup markToggleGroup = new ToggleGroup();
    private static RadioButton pointMarkRButton = new RadioButton(".");
    private static RadioButton xMarkRButton = new RadioButton("x");
    private static RadioButton oMarkRButton = new RadioButton("o");
    private static RadioButton charMarkRButton = new RadioButton("char");
    private static TextField charMarkText = new TextField("x");

    //Text and Font
    private static Label textLabel = new Label("Text: ");
    private static TextField textForDisplay = new TextField("");
    private static Label fontName = new Label("Font");
    private static Label fontSize = new Label("Size");
    private static Label fontStyle = new Label("Style");
    private static Button fontButton =  new Button("Font");

    //checkboxes
    private static CheckBox boundariesCheckBox = new CheckBox("Label by Boundaries");
    private static CheckBox countCheckBox = new CheckBox("Show Counts");
    private static CheckBox fns = new CheckBox("FNS");

    //other instance variables
    //private GrafType gType;
    private static GrafObject workingObject;
    private static ArrayList<GrafObject> tempGrafList = GrafProg.getGrafList();

    static void initFields(){
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
    }

    static void showFxEntryDialog()
    {
        setWorkingObject(new GrafFunction());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                getFxLabel().setVisible(true);
                showFunctionString(true);
                setTextandTitle();
            }
        });
    }

    static void showFxValueDialog()
    {
        setWorkingObject(new GrafValue());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showFunctionString(false);
                showFunctionChooser();
                showX1();
                getX1Label().setText("x1:");
                showMarks();
                setTextandTitle();

            }
        });
    }


    static void showFxTangentDialog()
    {
        setWorkingObject(new GrafTangent());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showFunctionString(false);
                showFunctionChooser();
                showX1();
                getX1Label().setText("x1:");
                showMarks();
                setTextandTitle();
            }
        });
    }


    static void showFxChordDialog()
    {
        setWorkingObject(new GrafChord());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showFunctionString(false);
                showFunctionChooser();
                showX1X2();
                getX1Label().setText("x1:");
                getX2Label().setText("x2:");
                showMarks();
                setTextandTitle();

            }
        });
    }

    static void showFxZerosDialog()
    {
        setWorkingObject(new GrafZeros());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showFunctionString(false);
                showFunctionChooser();
                showMarks();
                showX1();
                getX1Label().setText("Start:");
                showX2();
                getX2Label().setText("End:");
                showDx();
                setTextandTitle();
            }
        });
    }


    static void showFxIntegralDialog()
    {
        setWorkingObject(new GrafIntegral());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showFunctionString(false);
                showFunctionChooser();
                getFillColorPicker().setVisible(true);
                getFillLabel().setVisible(true);
                showX1();
                showN();
                showX2();
                getX1Label().setText("Begin:");
                getX2Label().setText("End:");
                setTextandTitle();

            }
        });
    }

    @FXML
    static void showColumnPlotDialog()
    {
        setWorkingObject(new GrafColumnPlot());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showColumnChooser();
                showMarks();
                showConnectedCheckBox();
                setTextandTitle();

            }
        });
    }

    @FXML
    static void showBoxplotDialog()
    {
        setWorkingObject(new GrafBoxPlot());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showColumnChooser();
                showFNS();
                setFNS(true);
                setTextandTitle();
            }
        });
    }

    @FXML
    static void showHistogramDialog()
    {
        setWorkingObject(new GrafHistogram());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                setupHisto();
                setTextandTitle();
            }
        });
    }

    static void showFreqPolyDialog() {
        setWorkingObject(new GrafFreqPolygon());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                setupHisto();
                setTextandTitle();
            }
        });
    }

    static void showOgiveDialog() {
        setWorkingObject(new GrafOgive());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                setupHisto();
                setTextandTitle();
            }
        });
    }

    static void showScatterDialog() {
        setWorkingObject(new GrafScatterPlot());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showColumnChooser();
                showColumnChooser2();
                showMarks();
                showConnectedCheckBox();
                setTextandTitle();

            }
        });

    }


    static  void showPointDialog() {
        setWorkingObject(new GrafPoint());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showMarks();
                showX1Y1();
                getX1Label().setText("x1:");
                getY1Label().setText("y1:");
                setTextandTitle();

            }
        });
    }



    static void showTextDialog() {
        setWorkingObject(new GrafText());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showTextAndFontButton();
                showX1Y1();
                getX1Label().setText("x1:");
                getY1Label().setText("y1:");
                fontButton.setOnAction(GrafDialogView::onFontButtonClicked);
                setTextandTitle();

            }
        });
    }

    static void showLineSegmentDialog() {
        setWorkingObject(new GrafSegment());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showMarks();
                showX1Y1();
                showX2Y2();
                getX1Label().setText("x1:");
                getY1Label().setText("y1:");
                x2Label.setText("x2:");
                y2Label.setText("y2:");
                setTextandTitle();

            }
        });
    }

    static void showRectangleDialog() {
        setWorkingObject(new GrafRectangle());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                getFillColorPicker().setVisible(true);
                getFillLabel().setVisible(true);
                showX1Y1();
                showWH();
                getX1Label().setText("x1:");
                getY1Label().setText("y1:");
                x2Label.setText("width:");
                y2Label.setText("height:");
                setTextandTitle();

            }
        });
    }

    static void showEllipseDialog() {
        setWorkingObject(new GrafEllipse());
        Platform.runLater(new Runnable() {
            @Override public void run() {
                getFillColorPicker().setVisible(true);
                getFillLabel().setVisible(true);
                showX1Y1();
                showWH();
                getX1Label().setText("x1:");
                getY1Label().setText("y1:");
                x2Label.setText("width:");
                y2Label.setText("height:");
                setTextandTitle();

            }
        });
    }

    static void showCircleDialog() {

        Platform.runLater(new Runnable() {
            @Override public void run() {
                setWorkingObject(new GrafCircle());
                getFillColorPicker().setVisible(true);
                getFillLabel().setVisible(true);
                showX1Y1();
                getX1Label().setText("x1:");
                getY1Label().setText("y1:");
                x2Label.setText("r:");
                showR();
                setTextandTitle();  }
        });
    }

    private static void setupHisto(){
        showColumnChooser();
        showX1();
        showX2();
        x1Label.setText("Begin");
        x2Label.setText("End");
        showHistoBoxes();
        onMaxMin(new ActionEvent());
        maxMinButton.setOnAction(GrafDialogView::onMaxMin);

    }

    private static void showHistoBoxes(){
        Platform.runLater(new Runnable() {
            @Override public void run() {
                HBox xy1PointBox = getXy1PointBox();
                HBox xy2PointBox = getXy2PointBox();
                VBox checkboxVBox = getCheckboxVBox();
                checkboxVBox.getChildren().add(boundariesCheckBox);
                boundariesCheckBox.setVisible(true);
                checkboxVBox.getChildren().add(countCheckBox);
                countCheckBox.setVisible(true);
                checkboxVBox.getChildren().add(fns);
                fns.setVisible(true);
                fns.setText("Relative Frequency");
                getFillColorPicker().setVisible(true);
                getFillLabel().setVisible(true);
                xy1PointBox.getChildren().add(maxMinButton);
                maxMinButton.setVisible(true);
                getXy2PointBox().getChildren().add(new Separator(Orientation.VERTICAL));
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

    //private halpers for setting up dialog
    private static void showTextAndFontButton(){
        HBox textHBox = getTextHBox();
        HBox fontHBox = getFontHBox();
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

    private static void showFunctionString(boolean editable){
        HBox functionStringBox = getFunctionStringBox();
        functionStringBox.getChildren().add(fxLabel);
        functionStringBox.getChildren().add(functionString);
        fxLabel.setVisible(true);
        functionString.setVisible(true);
        functionString.setEditable(editable);

    }

    private static void showColumnChooser(){
        HBox functionChoiceHBox = getFunctionChoiceBox();
        functionChoiceHBox.getChildren().add(fChoiceLabel);
        functionChoiceHBox.getChildren().add(fComboBox);
        fComboBox.setOnAction((e) -> { functionChosen();});
        //fComboBox.setItems(FXCollections.observableArrayList(TableHeaderActions.getHeaderArrayCdr(GrafProg.getData())));
        TableHeaderActions.setComboBox(fComboBox, TableUI.getData());
        fChoiceLabel.setText("Choose Column: ");
        fChoiceLabel.setVisible(true);
        fComboBox.setVisible(true);
        //fComboBox.setValue("function");
        try {
            fComboBox.getSelectionModel().select(0);
        }catch (IndexOutOfBoundsException iob){ fComboBox.setValue("Column");}
    }

    private static void showColumnChooser2(){
        HBox functionChoiceHBox = getFunctionChoiceBox();
        functionChoiceHBox.getChildren().add(fChoiceLabel2);
        functionChoiceHBox.getChildren().add(fComboBox2);
        fComboBox.setOnAction((e) -> { functionChosen();});

        fComboBox2.setItems(FXCollections.observableArrayList(TableHeaderActions.getHeaderArrayCdr(TableUI.getData())));
        fChoiceLabel2.setText("Choose Column: ");
        fChoiceLabel2.setVisible(true);
        fComboBox2.setVisible(true);
        //fComboBox.setValue("function");
        try {
            fComboBox2.getSelectionModel().select(0);
        }catch (IndexOutOfBoundsException iob){ fComboBox2.setValue("Column");}

    }

    private static void showMarks()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                HBox markButtonBox = ObjectUI.getDialogController().getMarkButtonBox();
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

    private static void showX1()
    {
            Platform.runLater(new Runnable() {
            @Override public void run() {
                HBox xy1PointBox = getXy1PointBox();
                //xy1PointBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
                xy1PointBox.getChildren().add(x1Label);
                //x1Label.setText("X1:");
                xy1PointBox.getChildren().add(x1Text);
                x1Text.setVisible(true);
                x1Label.setVisible(true);

            }
        });
    }

    private static void showY1()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                HBox xy1PointBox = getXy1PointBox();
                xy1PointBox.getChildren().add(new Separator(Orientation.VERTICAL));
                xy1PointBox.getChildren().add(y1Label);
                //y1Label.setText("Y1:");
                xy1PointBox.getChildren().add(y1Text);
                y1Text.setVisible(true);
                y1Label.setVisible(true);

            }
        });
    }

    private static void showX2()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                HBox xy2PointBox = ObjectUI.getDialogController().getXy2PointBox();
                //xy2PointBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
                xy2PointBox.getChildren().add(x2Label);
                //x2Label.setText("X2:");
                xy2PointBox.getChildren().add(x2Text);
                x2Text.setVisible(true);
                x2Label.setVisible(true);

            }
        });
    }

    private static void showY2()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                HBox xy2PointBox = ObjectUI.getDialogController().getXy2PointBox();
                xy2PointBox.getChildren().add(new Separator(Orientation.VERTICAL));
                xy2PointBox.getChildren().add(y2Label);
                //y2Label.setText("Y2:");
                xy2PointBox.getChildren().add(y2Text);
                y2Text.setVisible(true);
                y2Label.setVisible(true);

            }
        });
    }

    private static void showX1X2()
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

    private static void showX1Y1()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showX1();
                showY1();
            }
        });
    }

    private static void showX2Y2()
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

    private static void showWH()
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

    private static void showX1Y1X2Y2()
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

    private static void showN()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                HBox xy1PointBox = getXy1PointBox();
                xy1PointBox.getChildren().add(new Separator(Orientation.VERTICAL));
                xy1PointBox.getChildren().add(nLabel);
                xy1PointBox.getChildren().add(nText);
                nLabel.setVisible(true);
                nLabel.setText("n:");
                nText.setVisible(true);
            }
        });
    }

    private static void showDx()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                HBox xy1PointBox = getXy1PointBox();
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
    private static void showR()
    {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                HBox xy2PointBox = getXy2PointBox();
                xy2PointBox.getChildren().add(x2Label);
                xy2PointBox.getChildren().add(x2Text);
                x2Label.setVisible(true);
                x2Label.setText("r:");
                x2Text.setVisible(true);
            }
        });
    }

    private static void showFNS(){
        Platform.runLater(new Runnable() {
            @Override public void run() {
                getCheckboxVBox().getChildren().add(fns);
                fns.setVisible(true);
                fns.setText("FNS");

            }
        });
    }

    private static  void showConnectedCheckBox(){
        Platform.runLater(new Runnable() {
            @Override public void run() {
                getCheckboxVBox().getChildren().add(fns);
                fns.setVisible(true);
                fns.setText("Connected");

            }
        });
    }

    private static void setTextandTitle(){
        ComboBox objectComboBox = ObjectUI.getDialogController().getObjectComboBox();
        Label chooseObject = ObjectUI.getDialogController().getChooseObject();
        GrafObject workingObject = getWorkingObject();
        GrafType objectType = workingObject.getType();
        String objectTypeString = workingObject.getType().toString();
        objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(objectType)));
        objectComboBox.setValue(objectTypeString);
        ObjectUI.getDialogStage().setTitle(objectTypeString);
        chooseObject.setText("Choose "+objectTypeString);


    }

    static void resetDialog(){
        ObjectUI.getDialogStage().hide();
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
       /* getGrafColorPicker().setValue(javafx.scene.paint.Color.BLACK);
        getFillColorPicker().setValue(javafx.scene.paint.Color.BLACK);
        msg.setText("");
        hideAll();*/
        ////GrafProg.getGrafPanel().repaint();
        GrafUI.getGrafPanel().repaint();
        //setTempGrafList(new ArrayList<>());
    }

    static ArrayList<GrafObject> createObjectList(GrafType gtype){
        ArrayList<GrafObject> grafObjects = new ArrayList<>();
        for (GrafObject g: tempGrafList){

            if (g.getType() == gtype) grafObjects.add(g);
        }

        //return (GrafObject[]) grafObjects.toArray();
        return grafObjects;
    }

    private static void onFontButtonClicked(ActionEvent e){
        java.awt.Font awtFont = FontDialog.fxFontToAwtFont(fontName.getFont());
        FontDialog fontDialog = new FontDialog(awtFont);
        awtFont = fontDialog.showFontDialog();
        javafx.scene.text.Font fxFont = FontDialog.awtFontToFxFont(awtFont);
        fontName.setFont(fxFont);
        fontName.setText(fxFont.getName()+"  ");
        getFontSize().setText(fxFont.getSize()+"  ");
        getFontStyle().setText(fxFont.getStyle());

    }

    private static void onMaxMin(ActionEvent actionEvent) {
        Double[] currentColumn = TableColumnActions.getColumnValues(getColumn1ChooserColumn(), getData());
        setX1(""+GrafStats.getMin(currentColumn));
        setX2(""+GrafStats.getMax(currentColumn));
    }

    private static void showFunctionChooser(){
        HBox functionChoiceHBox = getFunctionChoiceBox();
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

    private static void functionChosen() {
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
                //System.out.println(npe.toString());
            }
        }else{
            try {
                if (!fComboBox.getValue().equals("Column")) {
                    TableHeaderActions.setComboBox(fComboBox, TableUI.getData());
                    //fComboBox.setItems(FXCollections.observableArrayList(TableHeaderActions.getHeaderArrayCdr(GrafProg.getData())));

                }
            } catch (ClassCastException cce) {
                //System.out.println("Choose a Function.");
            } catch (NullPointerException npe) {
                System.out.println(npe.toString());
            }

        }
    }

    static boolean addGrafObject(){
        GrafObject newObject = workingObject;
        if (!newObject.isValidInput(ObjectUI.getDialogController())) {
            setMessage("Not a valid "+ getGrafType());
            return false;
        }
        newObject = newObject.createGrafObjectFromController(ObjectUI.getDialogController());
        if (!duplicate(newObject)) getTempGrafList().add(newObject);
        setMessage(newObject.getType()+" added.");
        return true;
    }

    private static boolean duplicate(GrafObject newObject){
        for (GrafObject grafObject: tempGrafList) {
            if (grafObject.getType().equals(getGrafType()))
                if (grafObject.deepEquals(newObject)) return true;
        }
        return false;
    }

    static int findGrafObject(GrafObject grafObject, ArrayList<GrafObject> list){
        for (int i = 0; i<list.size(); i++) if (grafObject == list.get(i)) return i;
        return -1;
    }

    public static String getDialogMark(){
        if (markToggleGroup.getSelectedToggle().toString().contains(".")) return ".";
        else if (markToggleGroup.getSelectedToggle().toString().contains("x")) return "x";
        else if (markToggleGroup.getSelectedToggle().toString().contains("o")) return "o";
        else return charMarkText.toString();
    }

    static void settDialogMark(String mark){

        switch (mark) {
            case ".":
                pointMarkRButton.setSelected(true);
                break;
            case "x":
                xMarkRButton.setSelected(true);
                break;
            case "0":
                oMarkRButton.setSelected(true);
                break;
            default:
                charMarkRButton.setSelected(true);
                charMarkText.setText(mark);
                break;
        }
    }

    private static  GrafObject getFirstInTempList(GrafType gt){
        for (GrafObject g: tempGrafList) if (g.getType().equals(gt)) return g;
        return null;
    }

    private static  ArrayList<GrafObject> createFunctionList(){
        return createObjectList(GrafType.FUNCTION);
    }


    private static GrafObject getWorkingObject(){
        return workingObject;
    }

    private static void setWorkingObject(GrafObject grafObject){
        workingObject = grafObject;
    }

    static ArrayList<GrafObject> getTempGrafList(){
        return tempGrafList;
    }

    //public static Label getFontName(){
    //    return fontName;
   // }

    public static  String getFunctionString(){return functionString.getText();}
    public static  void setFunctionString(String s){ functionString.setText(s);}
    public static boolean functionStringIsVisible(){
        return functionString.isVisible();
    }

    static TextField getFunctionStringTextField(){return functionString;}

    public static  void setTempGrafList(ArrayList<GrafObject> t){ tempGrafList = t;}
    static void initTempGrafList(){ tempGrafList = GrafProg.getGrafList();}

    private static Label getFontSize(){
        return fontSize;
    }

    private static Label getFontStyle(){
        return fontStyle;
    }

    private static Label getFxLabel(){
        return fxLabel;
    }

    //public static void get

    private static Label getX1Label() {
        return x1Label;
    }

    private static Label getX2Label(){
        return x2Label;
    }

    private static Label getY1Label(){
        return y1Label;
    }

    public static String getX1() { return x1Text.getText(); }
    public static void setX1(String x1String) { x1Text.setText(x1String); }
    public static TextField getX1TextField(){return x1Text;}

    public static String getX2(){return x2Text.getText();}
    public static void setX2(String x2String){x2Text.setText(x2String);}
    public static TextField getX2TextField(){return x2Text;}

    public static String getY1() { return y1Text.getText(); }
    public static void setY1(String x1String) {y1Text.setText(x1String); }
    static TextField getY1TextField(){return y1Text;}

    public static String getY2(){return y2Text.getText();}
    public static void setY2(String x2String){y2Text.setText(x2String);}
    static TextField getY2TextField(){return y2Text;}

    static  String getDx(){return nText.getText();}
    static void setDx(String dx){ nText.setText(dx);}
    static TextField getDxTextField(){return nText;}

    static String getNText(){return nText.getText();}
    public static void setnText(int n){ nText.setText(""+n);}
    private static TextField getNTextField(){return nText;}
    static GrafType getGrafType(){return workingObject.getType();}

    static TextField getTextForDisplay() {
        return textForDisplay;
    }

    public static void setTextForDisplay(TextField text) {
        textForDisplay = text;
    }

    public static RadioButton getCharMarkRButton() {
        return charMarkRButton;
    }

    public static void setCharMarkRButton(RadioButton charMarkRB) {
        charMarkRButton = charMarkRB;
    }

    public static  String getFontName() {
        return fontName.getText();
    }

    static void setFontName(String fontNameString) {
        fontName.setText(fontNameString);
    }

    public String getFontStyleText() {
        return fontStyle.getText();
    }

    static void setFontStyleText(String fontS) {
        fontStyle.setText(fontS);
    }

    static void setFontSizeText(String fSize){fontSize.setText(fSize); }

    static boolean getFNS() {
        return fns.isSelected();
    }
    static void setFNS(boolean fnsFlag) {    fns.setSelected(fnsFlag);    }


    static void setColumn1ChooserColumn(int choice){
        fComboBox.getSelectionModel().select(choice);
    }

    static int getColumn1ChooserColumn(){
        return fComboBox.getSelectionModel().getSelectedIndex()+1;
    }

    static void setColumn2ChooserColumn(int choice){
        fComboBox2.getSelectionModel().select(choice);
    }

    static int getColumn2ChooserColumn(){
        return fComboBox2.getSelectionModel().getSelectedIndex()+1;
    }

    static String getClassWidthText(){        return classWidthText.getText();    }
    static void setClassWidthText(String s){ classWidthText.setText(s);}
    static TextField getClassWidthTextField(){return classWidthText;    }

    static boolean isConnected(){
        return fns.isSelected();
    }

    public static Object getColumn1VaLue(){
        return fComboBox.getValue();
    }

    static CheckBox getCountCheckBox() {        return countCheckBox;    }
    public static void setCountCheckBox(CheckBox countC) {        countCheckBox = countC;    }

    static CheckBox getBoundariesCheckBox() {        return boundariesCheckBox;    }
    public static void setBoundariesCheckBox(CheckBox boundariesC) {      boundariesCheckBox = boundariesC;    }

    static RadioButton getNumClassButton() {       return numClassButton;    }
    public static void setNumClassButton(RadioButton numClassBut) {    numClassButton = numClassBut;    }

    static RadioButton getClassSizeButton(){ return classSizeButton;}
    public static void setClassSizeButton(RadioButton classSizeB) {     classSizeButton = classSizeB;    }

    static int getNumClasses(){
        if (GrafInputHelpers.isInt(numClasses.getText())) return Integer.parseInt(numClasses.getText());
        if (GrafInputHelpers.isDouble((numClasses.getText()))) return (int)(Math.round(Double.parseDouble(numClasses.getText())));
        return 7;
    }

    private static GrafTable getData(){
        return TableUI.getData();
    }

    /*private void showFunctionString(boolean tf){
        GrafProg.getDialogController().showFunctionString(tf);
    }*/

    private static HBox getFunctionChoiceBox(){
        return ObjectUI.getDialogController().getFunctionChoiceHBox();
    }

    /*private String getFunctionChosen(){
        return GrafProg.getDialogController().functionChosen();
    }*/

    public static void setMessage(String message){
        ObjectUI.getDialogController().getMsg().setText(message);
    }

    private static ColorPicker getFillColorPicker(){
        return ObjectUI.getDialogController().getFillColorPicker();
    }

    private static Label getFillLabel(){
        return ObjectUI.getDialogController().getFillLabel();
    }

    public static void setCharMarkText(String s){
        charMarkText.setText(s);
    }

    public static String getCharMarkText(){
        return charMarkText.getText();
    }

    private static HBox getXy1PointBox(){
        return ObjectUI.getDialogController().getXy1PointBox();
    }

    private static HBox getXy2PointBox(){
        return ObjectUI.getDialogController().getXy2PointBox();
    }

    private static VBox getCheckboxVBox(){
        return ObjectUI.getDialogController().getCheckboxVBox();
    }

    private static HBox getTextHBox(){
        return ObjectUI.getDialogController().getTextHBox();
    }

    private static HBox getFontHBox(){
        return ObjectUI.getDialogController().getFontHBox();
    }

    private static HBox getFunctionStringBox(){
        return ObjectUI.getDialogController().getFunctionStringBox();
    }

    private static HBox getFunctionChoicHBox(){
        return ObjectUI.getDialogController().getFunctionChoiceHBox();
    }



}
