//package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;
import javafx.embed.swing.SwingNode;
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


public class GrafStage extends Application {
    private static final long serialVersionUID = 1L;

    //static UI variables
    private static Stage grafStage = new Stage();
    private static Stage dialogStage = new Stage();
    private static Stage tableStage = new Stage();
    private static Stage statStage = new Stage();
    //static Stage spreadStage = new Stage();  for better spreadsheet later

    static GrafController grafController;
    static GrafDialogController dialogController;
    static TableController tableController;
    static OneVarStatsController statController;

    static SwingNode swingGrafNode = new SwingNode();   //swing holder for grafPanel
    GrafPanel grafPanel = new GrafPanel(this); //Graphics Panel in swing
    static SwingNode swingTableNode = new SwingNode();
    static GrafTable data = new GrafTable(100,10);  //table for data

    //instance variables
    private File grafFile = new File("");  //File associated with the current Graf object
    private boolean grafSaved = false;     //has the current graf been saved?
    private static final int initWidth = 600;
    private static final int initHeight = 600;
    private GrafSettings grafSet = new GrafSettings(this);  //Stores window settings
    private GrafPrimitives grafPrim = new GrafPrimitives(this);  //draw line, point or character

    private static ArrayList<GrafObject> grafObjectList = new ArrayList<GrafObject>(); //list of objects to be graphed
    private GrafAxes axes = new GrafAxes(this);   //axes object
    private String copiedText = "";
    private JPanel messagePanel;
    private int boxPlotsPlotted = 0;              //for formatting multiple boxplots
    private static GrafCalc calc;                 //calculator for enteriung expressions

    //bottom status bar messages
    private JLabel message1;
    private JLabel message2;
    private JLabel message3;



    //UI elements start
    @Override
    public void start(Stage grafStage) throws Exception{

        //Set up Dialog Box
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GrafDialog.fxml"));
        Parent dialogRoot = loader.load();
        dialogController = loader.getController();
        Scene dialogScene = new Scene (dialogRoot, initWidth, initHeight); //.getWidth(), grafStage.getHeight());
        dialogStage.setScene(dialogScene);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogController.hideAll();

        //Set up One Variable Stats Dialog Box
        FXMLLoader statLoader = new FXMLLoader(getClass().getResource("OneVarStats.fxml"));
        Parent statRoot = statLoader.load();
        statController = statLoader.getController();
        Scene statScene = new Scene (statRoot, 580, 250); //.getWidth(), grafStage.getHeight());
        statStage.setScene(statScene);
        statStage.initModality(Modality.APPLICATION_MODAL);
        statStage.setTitle("DATA");


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

    public static void showData(){
       tableStage.show();
    }


    //Set Titles and saved status after saving file
    private void setGrafSaved(boolean tf){
        if (tf) {
            grafStage.setTitle(grafFile.toString());
            tableStage.setTitle("Data: " + grafFile.toString());
            grafSaved = true;
        }else{
            grafSaved = false;
        }

    }

    //Close an open file
    public void closeGraf(){
        if (!grafSaved)
            switch (JOptionPane.showConfirmDialog(null, "Save File?", "File"+grafFile.toString()+"not saved.", JOptionPane.YES_NO_CANCEL_OPTION)){
                case JOptionPane.YES_OPTION : { GrafFiles.saveFile(this); setGrafSaved(true); }
                case JOptionPane.CANCEL_OPTION : { return;}
            }
        tableStage.close(); grafStage.close();
    }


    public static void setMessage1(String message){ grafController.setMessage1(message); }
    public static void setMessage2(String message){ grafController.setMessage2(message); }
    public static void setMessage3(String message){ grafController.setMessage3(message); }



    /*//Setters and Getters
    public static int getWidth(){return (int) primaryStage.getWidth();}
    //public static void setTheWidth(int w) {width = w;}

    public static int getHeight(){return (int) primaryStage.getHeight();}
    //public static void setTheHeight(int h) {height = h;}*/

    public Stage getGrafStage(){ return grafStage;  }

    public static Stage getDialogStage(){return dialogStage;}

    public static Stage getTableStage() { return tableStage; }

    public static Stage getStatStage(){
        return statStage;
    }

    public void setData(GrafTable dt) { data = dt; }
    public static GrafTable getData(){return data;}

    public File getGrafFile(){return grafFile;}
    public void setGrafFile(File f) {grafFile = f;}

    public boolean getgrafSaved(){return grafSaved;}
    public void setgrafSaved(boolean tf){grafSaved = tf;}

    public GrafPanel getGrafPanel(){return grafPanel;}
    public void setPanel(GrafPanel gp) {grafPanel = gp;}

    public GrafAxes getAxes(){return axes;}
    public void setAxes(GrafAxes ga){axes = ga;}

    public GrafSettings getGrafSettings() {return grafSet;}
    public void setGrafSettings(GrafSettings gs) { grafSet = gs; }

    public GrafPrimitives getGrafPrimitives(){  return grafPrim; }
    public void setGrafPrim(GrafPrimitives gp){}

    public String getCopiedText(){return copiedText;}
    public void setCopiedText(String s){ copiedText = s;}

    public static void setGrafList(ArrayList<GrafObject> al){grafObjectList = al;}
    public static ArrayList<GrafObject> getGrafList(){return grafObjectList;}

    public int getBoxPlotsPlotted(){
        return boxPlotsPlotted;
    }

    public void incrementBoxPlotsPlotted(){
        boxPlotsPlotted++;
    }

    public void decrementBoxPlotsPlotted(){
        boxPlotsPlotted--;
    }

    public void zeroBoxPlotsPlotted(){
        boxPlotsPlotted = 0;
    }

    public int getNumType(GrafType gType){
        int count = 0;
        for (GrafObject o: grafObjectList)
            if (o.getType().equals(gType)) count++;
        return count;
    }




    public static void main(String[] args) {
        //new GrafProg();
        launch(args);
    }



}
