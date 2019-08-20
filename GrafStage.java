//package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class GrafStage extends Application {

    //static UI variables
    static Stage primaryStage;
    static Stage dialogStage = new Stage();
    static Stage spreadStage = new Stage();
    static GrafDialogController dialogController;
    static GrafMainController mainController;
    static SwingNode swingGrafNode = new SwingNode();
    GrafPanel grafPanel = new GrafPanel(this); //Graphics Panel

    private static final long serialVersionUID = 1L;

    //instance variables
    private File grafFile = new File("");  //File associated with the current Graf object
    private boolean grafSaved = false;     //has the current graf been saved?

    private static final int initWidth = 600;
    private static final int initHeight = 600;
    private GrafSettings grafSet = new GrafSettings(this);  //Stores window settings
    private GrafPrimitives grafPrim = new GrafPrimitives(this);  //draw line, point or character
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

        //Set up Main Window
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("GrafMain.fxml"));


        Parent grafRoot = (Parent)mainLoader.load();
        mainController = (GrafMainController)mainLoader.getController();
        Scene grafScene = new Scene (grafRoot , initWidth, initHeight);
        primaryStage.setScene(grafScene);
        primaryStage.setTitle("GrafProg: A Simple Graphing Program");
        mainController.putGrafPanelInGrafPane(swingGrafNode, grafPanel, initWidth, initHeight);  //puts swing panel in Pane
        primaryStage.show();

        //Set up Dialog Box
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GrafDialog.fxml"));
        Parent dialogRoot = loader.load();
        dialogController = (GrafDialogController)loader.getController();
        //Scene dialogScene = new Scene (dialogRoot, 400, 400);
        Scene dialogScene = new Scene (dialogRoot, primaryStage.getWidth(), primaryStage.getHeight());
        dialogStage.setScene(dialogScene);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogController.hideAll();


        //new GrafProg();
        grafObjectList.add(axes);
        data.setTitle("Data:");
        listenForSizeChange(primaryStage);



        //grafPanel.setPreferredSize(new Dimension(width, height-150));


}


    public void listenForSizeChange(Stage stage) {

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            grafPanel.setSize((new Dimension((int)stage.getWidth(), (int)stage.getHeight())));
            mainController.getGrafPane().resize((int)stage.getWidth(), (int)stage.getHeight());
            System.out.println("stage width =" + stage.getWidth()+"; grafPane width ="+mainController.grafPane.getWidth()+"; grafPanel width ="+grafPanel.getWidth());
            //grafPane is problem

        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            grafPanel.setSize((new Dimension((int)stage.getWidth(), (int)stage.getHeight()-100)));
            mainController.getGrafPane().resize((int)stage.getWidth(), (int)stage.getHeight()-100);
            System.out.println("stage height =" + stage.getHeight()+"; grafPane height ="+mainController.grafPane.getHeight()+ "grafPanel Height ="+grafPanel.getHeight());
        });


    }

    //Set Titles and saved status after saving file
    private void setAsSaved(){
        primaryStage.setTitle(grafFile.toString());
        data.setTitle("Data: "+grafFile.toString());
        grafSaved=true;

    }

    //Close an open file
    public void closeGraf(){
        if (!grafSaved)
            switch (JOptionPane.showConfirmDialog(null, "Save File?", "File"+grafFile.toString()+"not saved.", JOptionPane.YES_NO_CANCEL_OPTION)){
                case JOptionPane.YES_OPTION : { GrafFiles.saveFile(this); setAsSaved(); }
                case JOptionPane.CANCEL_OPTION : { return;}
            }
        data.dispose(); primaryStage.close();
    }


    public void setMessage1(String message){ mainController.setMessage1(message); }
    public void setMessage2(String message){ mainController.setMessage2(message); }
    public void setMessage3(String message){ mainController.setMessage3(message); }


    //Setters and Getters
    public static int getWidth(){return (int) primaryStage.getWidth();}
    //public static void setTheWidth(int w) {width = w;}

    public static int getHeight(){return (int) primaryStage.getHeight();}
    //public static void setTheHeight(int h) {height = h;}

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

    public void setData(GrafTable dt) { data = dt; }
    public GrafTable getData(){return data;}

    public void setGrafList(ArrayList<GrafObject> al){grafObjectList = al;}
    public ArrayList<GrafObject> getGrafList(){return grafObjectList;}

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
