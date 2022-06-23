//package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GrafProg extends Application {
    private static final long serialVersionUID = 1L;

    //Stages, Scenes and Nodes
    //UI for column data generator
    private static Stage genStage = new Stage();
    private static Scene genScene;
    private static TableColumnGeneratorController tableGenController;
    private static Parent genRoot;

    //UI for creating GrafObjects
    private static Stage dialogStage = new Stage();
    private static Scene dialogScene;
    private static GrafDialogController dialogController;
    private static Parent dialogRoot;

    //UI for one variable stats
    private static Stage statStage = new Stage();
    private static Scene statScene;
    private static OneVarStatsController statController;
    private static Parent statRoot;

    //UI for x-y axes and graphs
    private static Stage grafStage = new Stage();
    private static Scene grafScene;
    private static GrafController grafController;
    private static Parent grafRoot;
    private static SwingNode swingGrafNode = new SwingNode();   //swing holder for grafPanel
    private static GrafPanel grafPanel = new GrafPanel(); //Graphics Panel in swing

    //Data table for statistics
    private static Stage tableStage = new Stage();
    private static Scene tableScene;
    private static TableController tableController;
    private static Parent tableRoot;
    private static SwingNode swingTableNode = new SwingNode();
    private static GrafTable data = new GrafTable(100,10);  //Swing table for data

    //static variables
    private static File grafFile = new File("");  //File associated with the current Graf object
    private static boolean grafSaved = false;     //has the current graf been saved?
    private static final int initWidth = 750;
    private static final int initHeight = 750;
    private static GrafSettings grafSet = new GrafSettings();  //Stores window settings
    private static ArrayList<GrafObject> grafObjectList = new ArrayList<>(); //should make this class and use here and in DialogController
    private static GrafAxes axes = new GrafAxes();   //axes object
    private static String copiedText = "";
    private static JPanel messagePanel;
    private static int boxPlotsPlotted = 0;              //for formatting multiple boxplots
    private static GrafCalc calc;                 //calculator for enteriung expressions

    //UI thread start
    @Override
    public void start(Stage grafStage) throws Exception{
        GrafProg.grafStage = grafStage;

        //Set up Object Creation Dialog Box
        dialogController = createScene(dialogStage, 650, 375, "GrafDialog.fxml", "");
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        //Set up One Variable Stats Dialog Box
        statController = createScene(statStage, 580, 250, "OneVarStats.fxml" , "One-Variable Statistics");
        statStage.initModality(Modality.APPLICATION_MODAL);

        //Set up column generator Dialog Box
        tableGenController = createScene(genStage, 625, 200, "TableColumnGenerator.fxml", "Column Actions");

        //tableStage.initModality(Modality.APPLICATION_MODAL);

        //Set up Table
        tableController = createScene(tableStage, initWidth, initHeight, "Table.fxml" , "Data");
        swingTableNode.setContent(data.getDataPanel());
        tableController.getTablePane().getChildren().add(swingTableNode);
        anchorSwingNode(swingTableNode);
        setSizeChangeListener(tableStage, data.getDataPanel());
        EditContextMenu.editContextMenu(tableController.getTablePane(), getData());

        //Set up main graf window
        grafController = createScene(grafStage, initWidth, initHeight, "Graf.fxml", "GrafProg");
        swingGrafNode.setContent(getGrafPanel()); //put grafPanel into a JavaFX node
        grafController.getGrafPane().getChildren().add(swingGrafNode);   //place graphing window node in pane
        anchorSwingNode(swingGrafNode);
        setSizeChangeListener(grafStage, grafPanel);
        grafObjectList.add(axes);

        grafStage.show();

        grafStage.setOnCloseRequest(event -> {
           if (closeGraf()) System.exit(0);
           else
               event.consume();
           });
    }

    //creates a scene within passed stage
    private <T> T createScene(Stage stage, int width, int height, String path, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.setTitle(title);
        return (T) loader.getController();
    }

    //anchor a swing node to the pane edges
    private void anchorSwingNode(SwingNode node){
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
    }

    //should not need these listeners, but do. Sort of a hack.
    private void setSizeChangeListener(Stage stage, JPanel gPanel){
        stage.widthProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(gPanel::repaint));
        stage.heightProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(gPanel::repaint));

    }

    static void resetGraf(){
        grafFile = new File("");  //File associated with the current Graf object
        grafSaved = false;     //has the current graf been saved?
        grafSet = new GrafSettings();  //Stores window settings
        grafObjectList = new ArrayList<>();
        axes = new GrafAxes();   //axes object
        grafObjectList.add(axes);
        copiedText = "";
        setMessage1("");
        setMessage2("");
        setMessage3("");
        boxPlotsPlotted = 0;              //for formatting multiple boxplots
        repaintGraf();
    }

    //because sometimes just repainting grafPanel doesn't work. Another hack I should not need
    static void repaintGraf(){
        Platform.runLater(() -> {
            grafStage.hide();
            grafPanel.repaint();
            grafStage.show();
        });
    }

    //Set Titles and saved status after saving file
    private static void setGrafSavedAndTitle(boolean tf){
        if (tf) {
            grafStage.setTitle(grafFile.toString());
            tableStage.setTitle("Data: " + grafFile.toString());
            grafSaved = true;
        }else{
            grafSaved = false;
        }
    }


    //Close an open file
    static boolean closeGraf(){
        if (!grafSaved) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Save Graf?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.CANCEL) return false;
            if (alert.getResult() == ButtonType.YES) {
                GrafFiles.saveFile();
                setGrafSavedAndTitle(true);
            }
        }
        return true;
    }

    static int getNumType(GrafType gType){
        int count = 0;
        for (GrafObject o: grafObjectList)
            if (o.getType().equals(gType)) count++;
        return count;
    }

    public static void setMessage1(String message){ grafController.setMessage1(message); }
    public static void setMessage2(String message){ grafController.setMessage2(message); }
    static void setMessage3(String message){ grafController.setMessage3(message); }

    public static Stage getGrafStage(){ return grafStage;  }
    static Stage getDialogStage(){return dialogStage;}
    static Stage getTableStage() { return tableStage; }
    static Stage getStatStage(){
        return statStage;
    }

    static GrafDialogController getDialogController(){return dialogController;}

    public static void setData(GrafTable dt) { data = dt; }
    public static GrafTable getData(){return data;}

    static File getGrafFile(){return grafFile;}
    static void setGrafFile(File f) {grafFile = f;}

    static void setGrafSaved(boolean tf){grafSaved = tf;}
    static boolean getGrafSaved(){return grafSaved;}

    public static GrafPanel getGrafPanel(){return grafPanel;}
    public static void setPanel(GrafPanel gp) {grafPanel = gp;}

    static GrafAxes getAxes(){return axes;}
    public static void setAxes(GrafAxes ga){axes = ga;}

    public static GrafSettings getGrafSettings() {return grafSet;}
    public static void setGrafSettings(GrafSettings gs) { grafSet = gs; }

    public String getCopiedText(){return copiedText;}
    public void setCopiedText(String s){ copiedText = s;}

    static void setGrafList(ArrayList<GrafObject> al){grafObjectList = al;}
    static ArrayList<GrafObject> getGrafList(){return grafObjectList;}

    static int getBoxPlotsPlotted(){
        return boxPlotsPlotted;
    }
    void setBoxPlotsPlotted(int numBoxPlots){boxPlotsPlotted=numBoxPlots;}

    static void incrementBoxPlotsPlotted(){
        boxPlotsPlotted++;
    }

    public void decrementBoxPlotsPlotted(){
        boxPlotsPlotted--;
    }

    static void zeroBoxPlotsPlotted(){
        boxPlotsPlotted = 0;
    }

    static TableColumnGeneratorController getTableGenController() {
        return tableGenController;
    }

    public static void setTableGenController(TableColumnGeneratorController tableGenController) {
        GrafProg.tableGenController = tableGenController;
    }

    static Stage getGenStage() {return genStage; }

    static GrafController getGrafController(){
        return grafController;
    }

    static TableController getTableController(){return tableController;}



    public static void main(String[] args) {
        //new GrafProg().launch(args);
        launch(args);

    }

}


