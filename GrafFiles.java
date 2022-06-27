
/*
 * File Handling Routines for GrafProg
 * @author (Bill Gillam)
 * @version (4/6/18)
 */
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GrafFiles implements Serializable
{

    private double xMin=GrafProg.getGrafSettings().getXMin();
    private double xMax=GrafProg.getGrafSettings().getXMax();
    private double yMin=GrafProg.getGrafSettings().getYMin();
    private double yMax=GrafProg.getGrafSettings().getYMax();
    private double xAxisScale=GrafProg.getGrafSettings().getXAxisScale();
    private double yAxisScale=GrafProg.getGrafSettings().getYAxisScale();
    private int decPlaces = GrafProg.getGrafSettings().getDecPlaces();
    private boolean showYAxis=GrafProg.getGrafSettings().showYAxis();
    private boolean showXAxis=GrafProg.getGrafSettings().showXAxis();
    private boolean showYAxisScale=GrafProg.getGrafSettings().getShowYAxisScale();
    private boolean showXAxisScale=GrafProg.getGrafSettings().getShowXAxisScale();
    private boolean reverseXY=GrafProg.getGrafSettings().getReverseXY();
    private boolean leftScale=GrafProg.getGrafSettings().getLeftScale();
    private boolean autoScale=GrafProg.getGrafSettings().isAutoScale();
    private GrafSettings.ScaleFormat scaleFormat = GrafProg.getGrafSettings().getScaleFormat();
    private GrafSettings.ScaleProcedure scaleProcedure = GrafProg.getGrafSettings().getScaleProcedure();
    private static File grafFile = GrafProg.getGrafFile();
    private static boolean grafSaved = GrafProg.getGrafSaved();
    private static ArrayList<GrafObject> grafObjectList = GrafProg.getGrafList();
    private Font currentFont = GrafProg.getAxes().getCurrentFont();
    //private static int boxPlotsPlotted = GrafProg.getBoxPlotsPlotted();
    private static GrafTable data = TableUI.getData();

    /**
     * Constructor for objects of class GrafFiles
     */
    private GrafFiles()
    {
        // all methods are static. Constructor not used.
        
    }

    //import an comma delimmitted file into a GrafTable column
  public static void importFile(){
         System.out.println("Stub to importFile called");
         //read ascii data into a spreadsheet
  }
  
    
  
    static void saveFile(){
        File f = GrafProg.getGrafFile();
        if (f.toString().equals("") || !f.exists()) f = getFile();
        if (!(f == null)) {
            GrafFiles gf = new GrafFiles();
            saveObjectToFile(f, gf);
        }
    }


    
    static void saveFileAs(){
        File f;
        f = getFile();
        GrafFiles gf = new GrafFiles();
        saveObjectToFile(f,gf);
    }

    private static void saveObjectToFile(File f, Object obj){
        try{
            FileOutputStream fout = new FileOutputStream(f.toString());
            ObjectOutputStream oos = new ObjectOutputStream(fout);   
            oos.writeObject(obj);
            oos.close();
        }catch(Exception ex){ GrafProg.setMessage1("File not saved");
        System.out.println(ex.toString());}
    } 
     
   
    private static File getFile(){
        FileChooser saveChooser = new FileChooser();
        saveChooser.setTitle("Save CurrentGraf");
        saveChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Graf File, .grf", "*.grf"));
        //File file = saveChooser.showSaveDialog(GrafProg.getGrafController().getGrafPane().getScene().getWindow());
        File file = saveChooser.showSaveDialog(GrafUI.getGrafController().getGrafPane().getScene().getWindow());
        if (file == null) return null;
        String filePath = file.getAbsolutePath();
        if(!filePath.endsWith(".grf")){
            file = new File(filePath+".grf");
        }
    return file;
    }


  
  //read an object from a file - not finished yet
  private static Object openFileObject(String ext){


      /*JFrame parent = new JFrame();
      File file;
      int typeCheck;
      JFileChooser openChooser = new JFileChooser();
      String extLow = ext.toLowerCase();
      String extHigh = ext.toUpperCase();
      FileNameExtensionFilter filter = new FileNameExtensionFilter(extLow, extHigh);
      openChooser.setFileFilter(filter);
      typeCheck = openChooser.showOpenDialog(parent);*/

      FileChooser openChooser = new FileChooser();
      openChooser.setTitle("Open Graf");
      openChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Graf File, .grf", "*.grf"));
      ////File file = openChooser.showOpenDialog(GrafProg.getGrafController().getGrafPane().getScene().getWindow());
      File file = openChooser.showOpenDialog(GrafUI.getGrafController().getGrafPane().getScene().getWindow());
      //if (file == null) return null;
      //String filePath = file.getAbsolutePath();
      Object obj = null;
      try{
             FileInputStream fileIn = new FileInputStream(file.toString());
             ObjectInputStream ois = new ObjectInputStream(fileIn);
             obj = ois.readObject();
             ois.close();

          }catch(Exception ex){
              GrafProg.setMessage1("Error Opening File"+ ex);
          }
          return obj;
   }
    
  
  static GrafProg openGrafFromFile(){
     GrafFiles gf = (GrafFiles)openFileObject("grf");
     GrafProg grafProg = new GrafProg();
     GrafProg.getGrafSettings().setXMin(gf.xMin);
     GrafProg.getGrafSettings().setXMax(gf.xMax);
     GrafProg.getGrafSettings().setYMin(gf.yMin);
     GrafProg.getGrafSettings().setYMax(gf.yMax);
     GrafProg.getGrafSettings().setXAxisScale(gf.xAxisScale);
     GrafProg.getGrafSettings().setYAxisScale(gf.yAxisScale);
     GrafProg.getGrafSettings().setDecPlaces(gf.decPlaces);
     GrafProg.getGrafSettings().setShowYAxis(gf.showYAxis);
     GrafProg.getGrafSettings().setShowXAxis(gf.showXAxis);
     GrafProg.getGrafSettings().setShowXAxisScale(gf.showXAxisScale);
     GrafProg.getGrafSettings().setShowYAxisScale(gf.showYAxisScale);
      GrafProg.getGrafSettings().setReversXY(gf.reverseXY);
      GrafProg.getGrafSettings().setLeftScale(gf.leftScale);
      GrafProg.getGrafSettings().setAutoScale(gf.autoScale);
      GrafProg.getGrafSettings().setScaleFormat(gf.scaleFormat);
      GrafProg.getGrafSettings().setScaleProcedure(gf.scaleProcedure);
      GrafProg.setGrafFile(grafFile);
      GrafProg.setGrafSaved(grafSaved);
      GrafProg.setGrafList(grafObjectList);
      GrafProg.getAxes().setCurrentFont(gf.currentFont);
      //grafProg.setBoxPlotsPlotted(boxPlotsPlotted);
      TableUI.setData(data);
      return grafProg;
  }

    static void resetGraf(){
        grafFile = new File("");  //File associated with the current Graf object
        grafSaved = false;     //has the current graf been saved?
        GrafProg.setGrafSettings(new GrafSettings());  //Stores window settings
        grafObjectList = new ArrayList<>();
        GrafProg.setAxes(new GrafAxes());   //axes object
        grafObjectList.add(GrafProg.getAxes());
        GrafProg.setCopiedText("");
        GrafProg.setMessage1("");
        GrafProg.setMessage2("");
        GrafProg.setMessage3("");
        //boxPlotsPlotted = 0;              //for formatting multiple boxplots
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

}
