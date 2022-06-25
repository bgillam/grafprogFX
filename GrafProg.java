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

    //Create GUI's for graph, table, objects, data generation and statistics
    private static GrafUI grafUI = new GrafUI();
    private static TableUI tableUI = new TableUI();
    private static ObjectUI objectUI = new ObjectUI();
    private static DataGenUI dataGenUI = new DataGenUI();
    private static StatUI statUI = new StatUI();
    private static GrafCalc calc;

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

    public static void main(String[] args) {
        //new GrafProg().launch(args);
        launch(args);
    }

    //UI thread start
    @Override
    public void start(Stage gs) throws Exception{

        //Set up main graf window
        GrafUI.setGrafStage(gs);
        GrafUI.setGrafController(createScene(GrafUI.getGrafStage(), initWidth, initHeight, "Graf.fxml", "GrafProg").getController());
        GrafUI.getSwingGrafNode().setContent(GrafUI.getGrafPanel()); //put grafPanel into a JavaFX node
        GrafUI.getGrafController().getGrafPane().getChildren().add(GrafUI.getSwingGrafNode());   //place graphing window node in pane
        anchorSwingNode(GrafUI.getSwingGrafNode());
        setSizeChangeListener(GrafUI.getGrafStage(), GrafUI.getGrafPanel());
        grafObjectList.add(axes);
        GrafUI.getGrafStage().show();
        GrafUI.getGrafStage().setOnCloseRequest(event -> {
            if (closeGraf()) System.exit(0);
            else
                event.consume();
        });

        //Set up Object Creation Dialog Box
        ObjectUI.setDialogController(createScene(ObjectUI.getDialogStage(), 650, 375, "GrafDialog.fxml", "").getController());
        ObjectUI.getDialogStage().initModality(Modality.APPLICATION_MODAL);

        //Set up One Variable Stats Dialog Box
        StatUI.setStatController(createScene(StatUI.getStatStage(), 580, 250, "OneVarStats.fxml" , "One-Variable Statistics").getController());
        StatUI.getStatStage().initModality(Modality.APPLICATION_MODAL);

        //Set up column generator Dialog Box
        DataGenUI.setDataGenController(createScene(DataGenUI.getDataGenStage(), 625, 200, "TableColumnGenerator.fxml", "Column Actions").getController());
        //tableStage.initModality(Modality.APPLICATION_MODAL);

        ///Set up Table
        TableUI.setTableController(createScene(TableUI.getTableStage(), initWidth, initHeight, "Table.fxml" , "Data").getController());
        TableUI.getSwingTableNode().setContent(TableUI.getData().getDataPanel());
        TableUI.getTableController().getTablePane().getChildren().add(TableUI.getSwingTableNode());
        anchorSwingNode(TableUI.getSwingTableNode());
        setSizeChangeListener(TableUI.getTableStage(), TableUI.getDataPanel());
        EditContextMenu.editContextMenu(TableUI.getTableController().getTablePane(), TableUI.getData());
    }

    //creates a scene within passed stage
    private FXMLLoader createScene(Stage stage, int width, int height, String path, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.setTitle(title);
        return loader;
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
        GrafUI.repaintGraf();
    }


    //Close an open file
    static boolean closeGraf(){
        if (!grafSaved) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Save Graf?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.CANCEL) return false;
            if (alert.getResult() == ButtonType.YES) {
                GrafFiles.saveFile();
                ////grafStage.setTitle(grafFile.toString());
                GrafUI.getGrafStage().setTitle(grafFile.toString());
                TableUI.getTableStage().setTitle("Data: " + grafFile.toString());
                grafSaved = true;
            }
        }
        return true;
    }


    static int getNumPlots(){ return grafObjectList.size();}

    public static void setMessage1(String message){ GrafUI.getGrafController().setMessage1(message); }
    public static void setMessage2(String message){ GrafUI.getGrafController().setMessage2(message); }
    static void setMessage3(String message){ GrafUI.getGrafController().setMessage3(message); }

    static File getGrafFile(){return grafFile;}
    static void setGrafFile(File f) {grafFile = f;}

    static void setGrafSaved(boolean tf){grafSaved = tf;}
    static boolean getGrafSaved(){return grafSaved;}

    static GrafAxes getAxes(){return axes;}
    public static void setAxes(GrafAxes ga){axes = ga;}

    public static GrafSettings getGrafSettings() {return grafSet;}
    public static void setGrafSettings(GrafSettings gs) { grafSet = gs; }

    public String getCopiedText(){return copiedText;}
    public void setCopiedText(String s){ copiedText = s;}

    static void setGrafList(ArrayList<GrafObject> al){grafObjectList = al;}
    static ArrayList<GrafObject> getGrafList(){return grafObjectList;}

    static int getBoxPlotsPlotted(){ return boxPlotsPlotted; }
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
}


