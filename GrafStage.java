//package sample;

import javafx.application.Application;
import javafx.application.Platform;
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
    static Stage grafStage = new Stage();
    static Stage dialogStage = new Stage();
    //static Stage spreadStage = new Stage();  for better spreadsheet later

    static GrafController grafController;
    static GrafDialogController dialogController;

    static SwingNode swingGrafNode = new SwingNode();   //swing holder for grafPanel
    GrafPanel grafPanel = new GrafPanel(this); //Graphics Panel in swing

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

        //Set up main graf window
        FXMLLoader grafLoader = new FXMLLoader(getClass().getResource("Graf.fxml"));
        Parent grafRoot = grafLoader.load();
        grafController = (grafLoader.getController());
        Scene grafScene = new Scene (grafRoot, initWidth, initHeight);
        grafStage.setScene(grafScene);
        grafStage.setTitle("GrafProg");
        swingGrafNode.setContent(grafPanel);
        //place graphing window node in pane
        grafController.grafPane.getChildren().add(swingGrafNode);
        //anchor graphing node to root BorderPane - need to figure out how to do this in Graf.fxml
        AnchorPane.setTopAnchor(swingGrafNode, 0.0);
        AnchorPane.setLeftAnchor(swingGrafNode, 0.0);
        AnchorPane.setRightAnchor(swingGrafNode, 0.0);
        AnchorPane.setBottomAnchor(swingGrafNode, 0.0);
        grafObjectList.add(axes);
        data.setTitle("Data");
        grafStage.show();

    }



    //Set Titles and saved status after saving file
    private void setAsSaved(){
        grafStage.setTitle(grafFile.toString());
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
        data.dispose(); grafStage.close();
    }


    public void setMessage1(String message){ grafController.setMessage1(message); }
    public void setMessage2(String message){ grafController.setMessage2(message); }
    public void setMessage3(String message){ grafController.setMessage3(message); }


    /*//Setters and Getters
    public static int getWidth(){return (int) primaryStage.getWidth();}
    //public static void setTheWidth(int w) {width = w;}

    public static int getHeight(){return (int) primaryStage.getHeight();}
    //public static void setTheHeight(int h) {height = h;}*/

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
