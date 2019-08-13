//package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;


public class GrafStage extends Application {

    //static UI variables
    static Stage dialogStage = new Stage();
    static Stage spreadStage = new Stage();
    static GrafDialogController dialogController;
    static GrafMainController mainController;

    private static final long serialVersionUID = 1L;

    //instance variables
    private File grafFile = new File("");  //File associated with the current Graf object
    private boolean grafSaved = false;     //has the current graf been saved?
    private GrafPanel grafPanel = new GrafPanel(this); //Graphics Panel
    private int width = 600;
    private int height = 600;
    private GrafSettings grafSet = new GrafSettings(this);  //Stores window settings
    //private GrafPrimitives grafPrim = new GrafPrimitives(this);  //draw line, point or character
    private GrafTable data = new GrafTable(this, 100,10);  //table for data
    private ArrayList<GrafObject> grafObjectList = new ArrayList<GrafObject>(); //list of objects to be graphed
    private GrafAxes axes = new GrafAxes(this);   //axes object
    private String copiedText = "";
    private JPanel messagePanel;
    private int boxPlotsPlotted = 0;              //for formatting multiple boxplots
    private static GrafCalc calc;                 //calculator for enteriung expressions
    //bottom status bar messages
//    private JLabel message1;
//    private JLabel message2;
//    private JLabel message3;




    @Override
    public void start(Stage primaryStage) throws Exception{
        //Set up Stage for Main Window
        Parent grafRoot = FXMLLoader.load(getClass().getResource("grafMain.fxml"));
        //FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("GrafMain.fxml"));
        //Parent grafRoot = (Parent)mainLoader.load();
//        mainController = (GrafMainController)mainLoader.getController();

        //Scene for main window
        Scene grafScene = new Scene (grafRoot, 500, 500);
        primaryStage.setScene(grafScene);

        primaryStage.setTitle("GrafProg: A Simple Graphing Program");
        primaryStage.show();

        //Set up Stage for Dialogs (implement object)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GrafDialog.fxml"));
        Parent dialogRoot = loader.load();
        dialogController = (GrafDialogController)loader.getController();

        //initial Scene for dialog
        Scene dialogScene = new Scene (dialogRoot, 400, 400);
        dialogStage.setScene(dialogScene);

        dialogStage.hide();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogController.hideAll();


        //new GrafProg();


    }

    public ArrayList<GrafObject> getGrafList(){return grafObjectList;}
    public void setGrafList(ArrayList<GrafObject> al){grafObjectList = al;}


    public int getNumType(GrafType gType){
        int count = 0;
        for (GrafObject o: grafObjectList)
            if (o.getType().equals(gType)) count++;
        return count;
    }

    //keep up with boxplots
    public void zeroBoxPlotsPlotted(){boxPlotsPlotted = 0;}
    public void incrementBoxPlotsPlotted(){
        boxPlotsPlotted++;
    }
    public int getBoxPlotsPlotted(){
        return boxPlotsPlotted;
    }

    public void setMessage1(String message){ mainController.setMessage1(message); }
    public void setMessage2(String message){ mainController.setMessage2(message); }
    public void setMessage3(String message){ mainController.setMessage3(message); }

    public GrafPanel getGrafPanel(){return grafPanel;}
    public GrafTable getData(){return data;}
    public GrafSettings getGrafSettings() {return grafSet;}

    public static void main(String[] args) {
        //new GrafProg();
        launch(args);
    }



}
