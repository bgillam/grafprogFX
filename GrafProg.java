//package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.WindowEvent;


public class GrafProg extends Application {
    private static final long serialVersionUID = 1L;

    //static UI variables
    private static Stage grafStage = new Stage();
    private static Stage dialogStage = new Stage();
    private static Stage tableStage = new Stage();
    private static Stage statStage = new Stage();
    //private static Stage freqTableStage = new Stage();
    //static Stage spreadStage = new Stage();  for better spreadsheet later

    private static GrafController grafController;
    private static GrafDialogController dialogController;
    private static TableController tableController;


    private static OneVarStatsController statController;

    private static SwingNode swingGrafNode = new SwingNode();   //swing holder for grafPanel
    private static GrafPanel grafPanel = new GrafPanel(); //Graphics Panel in swing
    private static SwingNode swingTableNode = new SwingNode();
    private static GrafTable data = new GrafTable(100,10);  //table for data
    /*private static SwingNode swingHistoNode = new SwingNode();
    private static HistoPanel histoPanel = new HistoPanel();  //table for data*/

    private static FrequencyChartDialog frequencyChartDialog = new FrequencyChartDialog(); //Graphics Panel in swing


    //instance variables
    private static File grafFile = new File("");  //File associated with the current Graf object
    private static boolean grafSaved = false;     //has the current graf been saved?
    private static final int initWidth = 750;
    private static final int initHeight = 750;
    private static GrafSettings    grafSet = new GrafSettings();  //Stores window settings
    private GrafPrimitives grafPrim = new GrafPrimitives(this);  //draw line, point or character

    private static ArrayList<GrafObject> grafObjectList = new ArrayList<GrafObject>(); //should make this class and use here and in DialogController
    private static GrafAxes axes = new GrafAxes();   //axes object
    private String copiedText = "";
    private JPanel messagePanel;
    private static int boxPlotsPlotted = 0;              //for formatting multiple boxplots
    private static GrafCalc calc;                 //calculator for enteriung expressions

    //bottom status bar messages
    /*private JLabel message1;
    private JLabel message2;
    private JLabel message3;*/



    //UI elements start
    @Override
    public void start(Stage grafStage) throws Exception{
        this.grafStage = grafStage;
        //Set up Dialog Box
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GrafDialog.fxml"));
        Parent dialogRoot = loader.load();
        dialogController = loader.getController();
        Scene dialogScene = new Scene (dialogRoot, 650, 500); //.getWidth(), grafStage.getHeight());
        dialogStage.setScene(dialogScene);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        //dialogController.hideAll();

        //Set up One Variable Stats Dialog Box
        FXMLLoader statLoader = new FXMLLoader(getClass().getResource("OneVarStats.fxml"));
        Parent statRoot = statLoader.load();
        statController = statLoader.getController();
        Scene statScene = new Scene (statRoot, 580, 250); //.getWidth(), grafStage.getHeight());
        statStage.setScene(statScene);
        statStage.initModality(Modality.APPLICATION_MODAL);
        statStage.setTitle("DATA");

        //Set up Histogram Dialog Box


        //Set up Table
        FXMLLoader tableLoader = new FXMLLoader(getClass().getResource("Table.fxml"));
        Parent tableRoot = tableLoader.load();
        tableController = (tableLoader.getController());
        Scene tableScene = new Scene (tableRoot, initWidth, initHeight);
        tableStage.setScene(tableScene);
        tableStage.setTitle("Data");
        swingTableNode.setContent(data.getDataPanel());
        tableController.getTablePane().getChildren().add(swingTableNode);
        //anchor graphing node to root BorderPane - need to figure out how to do this in Graf.fxml
        AnchorPane.setTopAnchor(swingTableNode, 0.0);
        AnchorPane.setLeftAnchor(swingTableNode, 0.0);
        AnchorPane.setRightAnchor(swingTableNode, 0.0);
        AnchorPane.setBottomAnchor(swingTableNode, 0.0);
        setSizeChangeListener(tableStage, data.getDataPanel());
        tableStage.setAlwaysOnTop(true);
        //tableStage.show();


        //Set up main graf window
        FXMLLoader grafLoader = new FXMLLoader(getClass().getResource("Graf.fxml"));
        Parent grafRoot = grafLoader.load();
        grafController = (grafLoader.getController());
        Scene grafScene = new Scene (grafRoot, initWidth, initHeight);
        grafStage.setScene(grafScene);
        grafStage.setTitle("GrafProg");
        swingGrafNode.setContent(getGrafPanel());
        //place graphing window node in pane
        grafController.getGrafPane().getChildren().add(swingGrafNode);
        //anchor graphing node to root BorderPane - need to figure out how to do this in Graf.fxml
        AnchorPane.setTopAnchor(swingGrafNode, 0.0);
        AnchorPane.setLeftAnchor(swingGrafNode, 0.0);
        AnchorPane.setRightAnchor(swingGrafNode, 0.0);
        AnchorPane.setBottomAnchor(swingGrafNode, 0.0);
        setSizeChangeListener(grafStage, getGrafPanel());
        grafObjectList.add(axes);
        grafStage.show();

        grafStage.setOnCloseRequest(event -> {
            closeGraf();
            event.consume();
        });



    }

    public void setSizeChangeListener(Stage stage, JPanel gPanel){
        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                gPanel.repaint();
                stage.show();
            }
        });
        stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                gPanel.repaint();
                stage.show();
            }
        });

    }


    //public static void showFreqTableDialog(){ frequencyChartDialog. }

    public static void showData(){
       tableStage.show();
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
    public static void closeGraf(){

        if (!grafSaved) {
            final JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            switch (JOptionPane.showConfirmDialog(dialog, "Save File?", "File" + grafFile.toString() + "not saved.", JOptionPane.YES_NO_CANCEL_OPTION)) {
                case JOptionPane.YES_OPTION: {
                    GrafFiles.saveFile();
                    setGrafSavedAndTitle(true);
                    System.exit(0);
                    break;
                }
                case JOptionPane.CANCEL_OPTION: {
                    break;
                }
                case JOptionPane.NO_OPTION:{
                    System.exit(0);
                    break;
                }
                default:{System.exit(0);}
            }
        }
    }


    public static void setMessage1(String message){ grafController.setMessage1(message); }
    public static void setMessage2(String message){ grafController.setMessage2(message); }
    public static void setMessage3(String message){ grafController.setMessage3(message); }



    /*//Setters and Getters
    public static int getWidth(){return (int) primaryStage.getWidth();}
    //public static void setTheWidth(int w) {width = w;}

    public static int getHeight(){return (int) primaryStage.getHeight();}
    //public static void setTheHeight(int h) {height = h;}*/

    public static Stage getGrafStage(){ return grafStage;  }

    public static Stage getDialogStage(){return dialogStage;}

    public static Stage getTableStage() { return tableStage; }

    public static Stage getStatStage(){
        return statStage;
    }

    public static GrafDialogController getDialogController(){return dialogController;}

    public static void setData(GrafTable dt) { data = dt; }
    public static GrafTable getData(){return data;}

    public static File getGrafFile(){return grafFile;}
    public static void setGrafFile(File f) {grafFile = f;}


    public static void setGrafSaved(boolean tf){grafSaved = tf;}
    public static boolean getGrafSaved(){return grafSaved;}

    public static GrafPanel getGrafPanel(){return grafPanel;}
    public static void setPanel(GrafPanel gp) {grafPanel = gp;}

    public static GrafAxes getAxes(){return axes;}
    public static void setAxes(GrafAxes ga){axes = ga;}

    public static GrafSettings getGrafSettings() {return grafSet;}
    public static void setGrafSettings(GrafSettings gs) { grafSet = gs; }

    public GrafPrimitives getGrafPrimitives(){  return grafPrim; }
    public void setGrafPrim(GrafPrimitives gp){}

    public String getCopiedText(){return copiedText;}
    public void setCopiedText(String s){ copiedText = s;}

    public static void setGrafList(ArrayList<GrafObject> al){grafObjectList = al;}
    public static ArrayList<GrafObject> getGrafList(){return grafObjectList;}



    public static int getBoxPlotsPlotted(){
        return boxPlotsPlotted;
    }
    public void setBoxPlotsPlotted(int numBoxPlots){boxPlotsPlotted=numBoxPlots;}

    public static void incrementBoxPlotsPlotted(){
        boxPlotsPlotted++;
    }

    public void decrementBoxPlotsPlotted(){
        boxPlotsPlotted--;
    }

    public static void zeroBoxPlotsPlotted(){
        boxPlotsPlotted = 0;
    }

    public static int getNumType(GrafType gType){
        int count = 0;
        for (GrafObject o: grafObjectList)
            if (o.getType().equals(gType)) count++;
        return count;
    }



    public static void main(String[] args) {
        //new GrafProg().launch(args);
        launch(args);
    }



}
