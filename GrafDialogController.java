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
import javax.xml.soap.Text;
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



    public void initialize(){
        GrafDialogView.initFields();
       msg.setText("");
        hideAll();
    }

    //Button responses tied to GUI

    private void resetObjectList(){
        objectComboBox.setItems(FXCollections.observableArrayList(GrafDialogView.createObjectList(GrafDialogView.getGrafType())));
    }

    @FXML
    private void onCreateButtonClicked(ActionEvent e){
            addGrafObject();
            resetObjectList();
            //objectComboBox.setItems(FXCollections.observableArrayList(GrafDialogView.createObjectList(GrafDialogView.getGrafType())));
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
            GrafProg.setGrafList(GrafDialogView.getTempGrafList());
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
         int index = findGrafObject(currentObject, GrafDialogView.getTempGrafList());
         if (index != -1) GrafDialogView.getTempGrafList().remove(index);
        resetObjectList();
        // objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(getGrafType())));
    }

    @FXML
    private void onClearButtonClicked(ActionEvent e){
        for (int i = 1; i<GrafDialogView.getTempGrafList().size(); i++){
                if (GrafDialogView.getTempGrafList().get(i).getType().equals(GrafDialogView.getGrafType())) {
                    GrafDialogView.getTempGrafList().remove(i);
                i--;
            }
        }
        resetObjectList();
        //objectComboBox.setItems(FXCollections.observableArrayList(createObjectList(getGrafType())));
    }

      //had to make this one public for call from outside - probably should refactor relationship
      void hideAll() //change to hide all
    {
        Platform.runLater(() -> {
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
            GrafDialogView.initTempGrafList();
        });
    }


    private void resetDialog(){
        GrafDialogView.resetDialog();
        getGrafColorPicker().setValue(javafx.scene.paint.Color.BLACK);
        getFillColorPicker().setValue(javafx.scene.paint.Color.BLACK);
        msg.setText("");
        hideAll();
    }



    private ArrayList<GrafObject> getTempGrafList(){
        return GrafDialogView.getTempGrafList();
    }

    private boolean addGrafObject(){
        return GrafDialogView.addGrafObject();
    }

    private int findGrafObject(GrafObject grafObject, ArrayList<GrafObject> list){
        return GrafDialogView.findGrafObject(grafObject, list);
    }

    public java.awt.Color getGrafColor(){
        //System.out.println(grafColorPicker.getValue());
        javafx.scene.paint.Color fxColor = grafColorPicker.getValue();
        return new Color((float)fxColor.getRed(), (float)fxColor.getGreen(), (float) fxColor.getBlue(), (float)fxColor.getOpacity());}

    public java.awt.Color getFillColor(){
        //System.out.println(grafColorPicker.getValue());
        javafx.scene.paint.Color fxColor = fillColorPicker.getValue();
        return new Color((float)fxColor.getRed(), (float)fxColor.getGreen(), (float) fxColor.getBlue(), (float)fxColor.getOpacity());}


    void setfillColor(Color awtColor){
        javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(getFillColor().getRed(),
                getGrafColor().getGreen(), getGrafColor().getBlue(), 1);
        getFillColorPicker().setValue(fxColor);
    }

    ColorPicker getFillColorPicker() {return fillColorPicker; }
    public void setFillColorPicker(ColorPicker fillColorPicker) {  this.fillColorPicker = fillColorPicker; }

    ColorPicker getGrafColorPicker() { return grafColorPicker; }
    public void setGrafColorPicker(ColorPicker grafColorPicker) {this.grafColorPicker = grafColorPicker; }



    java.awt.Font getDefaultFont() {
           return FontDialog.fxFontToAwtFont(javafx.scene.text.Font.getDefault());

    }

    ComboBox getObjectComboBox(){
        return objectComboBox;
    }

    TextField getMsg() {
        return msg;
    }

    public void setMsg(TextField msg) {
        this.msg = msg;
    }

    public GrafTable getData(){
        return GrafProg.getData();
    }

    Label getChooseObject() { return chooseObject;  }



    HBox getXy1PointBox(){
        return xy1PointBox;
    }

    HBox getXy2PointBox(){
        return xy2PointBox;
    }

    VBox getCheckboxVBox() {
        return checkboxVBox;
    }

    HBox getMarkButtonBox(){
        return markButtonBox;
    }

    HBox getFunctionChoiceHBox() {
        return functionChoiceHBox;
    }

    public void setFunctionChoiceHBox(HBox functionChoiceHBox) {
        this.functionChoiceHBox = functionChoiceHBox;
    }


    HBox getFunctionStringBox() {
        return functionStringBox;
    }

    public void setFunctionStringBox(HBox functionStringBox) {
        this.functionStringBox = functionStringBox;
    }

    HBox getTextHBox() {
        return textHBox;
    }

    public void setTextHBox(HBox textHBox) {
        this.textHBox = textHBox;
    }
    public void setMarkButtonBox(HBox markButtonBox) {
        this.markButtonBox = markButtonBox;
    }
    HBox getFontHBox() {
        return fontHBox;
    }

    public void setFontHBox(HBox fontHBox) {
        this.fontHBox = fontHBox;
    }

    public void setCheckboxVBox(VBox checkboxVBox) {
        this.checkboxVBox = checkboxVBox;
    }
    public void setXy1PointBox(HBox xy1PointBox) {
        this.xy1PointBox = xy1PointBox;
    }

    Label getFillLabel() {
        return fillLabel;
    }

    public void setFillLabel(Label fillLabel) {
        this.fillLabel = fillLabel;
    }

    //Calls to GrafDialog View which handles view changes per context

    static void showFxEntryDialog(){
        GrafDialogView.showFxEntryDialog();
    }

    static void showFxValueDialog(){
        GrafDialogView.showFxValueDialog();
    }

    static void showFxTangentDialog(){
        GrafDialogView.showFxTangentDialog();
    }

    static void showFxChordDialog(){
        GrafDialogView.showFxChordDialog();
    }

    static void showFxIntegralDialog(){
        GrafDialogView.showFxIntegralDialog();
    }

    static void showFxZerosDialog(){
        GrafDialogView.showFxZerosDialog();
    }

    static void showPointDialog(){
        GrafDialogView.showPointDialog();
    }

    static void showTextDialog(){
        GrafDialogView.showTextDialog();
    }

    static void showLineSegmentDialog(){
        GrafDialogView.showLineSegmentDialog();
    }

    static void showRectangleDialog(){
        GrafDialogView.showRectangleDialog();
    }

    static void showEllipseDialog(){
        GrafDialogView.showEllipseDialog();
    }

    static void showCircleDialog(){
        GrafDialogView.showCircleDialog();
    }

    static void showColumnPlotDialog(){
        GrafDialogView.showColumnPlotDialog();
    }

    static void showBoxplotDialog(){
        GrafDialogView.showBoxplotDialog();
    }


    static void showHistogramDialog(){
        GrafDialogView.showHistogramDialog();
    }

    static void showFreqPolyDialog(){
        GrafDialogView.showFreqPolyDialog();
    }

    static void showOgiveDialog(){
        GrafDialogView.showOgiveDialog();
    }

    static void showScatterDialog(){
        GrafDialogView.showScatterDialog();
    }

    public static String  getX1(){
        return GrafDialogView.getX1();
    }

    public static void setX1(String s){
        GrafDialogView.setX1(s);
    }

    public static String  getX2(){
        return GrafDialogView.getX2();
    }

    public static void setX2(String s){
        GrafDialogView.setX2(s);
    }

    public static String  getY1(){
        return GrafDialogView.getY1();
    }

    public static void setY1(String s){
        GrafDialogView.setY1(s);
    }

    public static String  getY2(){
        return GrafDialogView.getY2();
    }

    public static void setY2(String s){
        GrafDialogView.setY2(s);
    }

    public static TextField  getX1TextField(){
        return GrafDialogView.getX1TextField();
    }

    public static TextField  getX2TextField(){
        return GrafDialogView.getX2TextField();
    }

    static TextField getY1TextField(){
        return GrafDialogView.getY1TextField();
    }

    static TextField  getY2TextField(){
        return GrafDialogView.getY2TextField();
    }

    static TextField getTextForDisplay(){
        return GrafDialogView.getTextForDisplay();
    }

    void setFontName(String fontNameString){
        GrafDialogView.setFontName(fontNameString);
    }

    void setFontStyleText(String fonts){
        GrafDialogView.setFontStyleText(fonts);
    }

    void setFontSizeText(String fonts){
        GrafDialogView.setFontSizeText(fonts);
    }

    void settDialogMark(String m){
        GrafDialogView.settDialogMark(m);
    }

    public String getDialogMark(){
        return GrafDialogView.getDialogMark();
    }

    public String getFunctionString(){
        return GrafDialogView.getFunctionString();
    }

    String getNText(){
        return GrafDialogView.getNText();
    }

    public boolean functionStringIsVisible(){
        return GrafDialogView.functionStringIsVisible();
    }

    public void setFunctionString(String s){
        GrafDialogView.setFunctionString(s);
    }

    static TextField getDxTextField(){
        return GrafDialogView.getDxTextField();
    }

    String getDx(){
        return GrafDialogView.getDx();
    }

    void setDx(String s){
        GrafDialogView.setDx(s);

    }

    void setColumn1ChooserColumn(int c){
        GrafDialogView.setColumn1ChooserColumn(c);
    }

    int getColumn1ChooserColumn(){
        return GrafDialogView.getColumn1ChooserColumn();
    }

    int getColumn2ChooserColumn(){
        return GrafDialogView.getColumn2ChooserColumn();
    }

    public void setColumn2ChooserColumn(int c){
        GrafDialogView.setColumn2ChooserColumn(c);
    }

    boolean getFNS(){
        return GrafDialogView.getFNS();
    }

    void setFNS(boolean tf){
        GrafDialogView.setFNS(tf);
    }

    boolean isConnected(){
        return GrafDialogView.isConnected();
    }

    RadioButton getClassSizeButton(){
        return GrafDialogView.getClassSizeButton();
    }

    TextField getClassWidthTextField(){
        return GrafDialogView.getClassWidthTextField();
    }

    String getClassWidthText(){
        return GrafDialogView.getClassWidthText();
    }

    void setClassWidthText(String s){
        GrafDialogView.setClassWidthText(s);
    }

    CheckBox getCountCheckBox(){
        return GrafDialogView.getCountCheckBox();
    }

    CheckBox getBoundariesCheckBox(){
        return GrafDialogView.getBoundariesCheckBox();
    }

    RadioButton getNumClassButton(){
        return GrafDialogView.getNumClassButton();
    }

    int getNumClasses(){
        return GrafDialogView.getNumClasses();
    }

    static TextField getFunctionStringTextField(){
        return GrafDialogView.getFunctionStringTextField();
    }



}