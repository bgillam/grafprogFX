//package sample;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private static StatUI statUI = new StatUI();
    private static DataGenUI dataGenUI = new DataGenUI();
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
    //private static int boxPlotsPlotted = 0;              //for formatting multiple boxplots

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
        ////setSizeChangeListener(GrafUI.getGrafStage(), GrafUI.getGrafPanel());
        grafObjectList.add(axes);
        GrafUI.getGrafStage().show();
        GrafUI.getGrafStage().setOnCloseRequest(event -> {
            if (GrafFiles.closeGraf()) System.exit(0);
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
        DataGenUI.getDataGenStage().initModality(Modality.APPLICATION_MODAL);

        //Set up Table
        TableUI.setTableController(createScene(TableUI.getTableStage(), initWidth, initHeight, "Table.fxml" , "Data").getController());
        TableUI.getSwingTableNode().setContent(TableUI.getData().getDataPanel());
        TableUI.getTableController().getTablePane().getChildren().add(TableUI.getSwingTableNode());
        anchorSwingNode(TableUI.getSwingTableNode());
        ////setSizeChangeListener(TableUI.getTableStage(), TableUI.getDataPanel());
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

    static int getNumPlots(){ return grafObjectList.size();}

    public static void setMessage1(String message){ GrafUI.getGrafController().setMessage1(message); }
    public static void setMessage2(String message){ GrafUI.getGrafController().setMessage2(message); }
    public static void setMessage3(String message){ GrafUI.getGrafController().setMessage3(message); }

    static File getGrafFile(){return grafFile;}
    static void setGrafFile(File f) {grafFile = f;}

    static void setGrafSaved(boolean tf){grafSaved = tf;}
    static boolean getGrafSaved(){return grafSaved;}

    static GrafAxes getAxes(){return axes;}
    static void setAxes(GrafAxes ga){axes = ga;}

    public static GrafSettings getGrafSettings() {return grafSet;}
    public static void setGrafSettings(GrafSettings gs) { grafSet = gs; }

    public static String getCopiedText(){return copiedText;}
    public static void setCopiedText(String s){ copiedText = s;}

    static void setGrafList(ArrayList<GrafObject> al){grafObjectList = al;}
    static ArrayList<GrafObject> getGrafList(){return grafObjectList;}


}


//should not need these listeners, but had to use them at one point. Sort of a hack. Breaking up UI's seems to have fixed it.
   /* private void setSizeChangeListener(Stage stage, JPanel gPanel){
        stage.widthProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(gPanel::repaint));
        stage.heightProperty().addListener((observable, oldValue, newValue) -> Platform.runLater(gPanel::repaint));
    }*/