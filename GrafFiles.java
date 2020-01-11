
/**
 * File Handling Routines for GrafProg
 * @author (Bill Gillam)
 * @version (4/6/18)
 */
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;
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
    private static int boxPlotsPlotted = GrafProg.getBoxPlotsPlotted();
    private static GrafTable data = GrafProg.getData();

    /**
     * Constructor for objects of class GrafFiles
     */
    public GrafFiles()
    {
        // all methods are static. Constructor not used.
        
    }

    //import an comma delimmitted file into a GrafTable column
  public static void importFile(){
         System.out.println("Stub to importFile called");
         //read ascii data into a spreadsheet
  }
  
    
  
    public static File saveFile(){
        File f = GrafProg.getGrafFile();
        if (f.toString().equals("") || !f.exists()) f = getFile();
        if (!(f == null)) {
            GrafFiles gf = new GrafFiles();
            saveObjectToFile(f, gf);
        }
        return f;
    }


    
    public static File saveFileAs(){
        File f = GrafProg.getGrafFile();
        f = getFile();
        GrafFiles gf = new GrafFiles();
        saveObjectToFile(f,gf);
        return f;
    }

    private static void saveObjectToFile(File f, Object obj){
        try{
            FileOutputStream fout = new FileOutputStream(f.toString());
            ObjectOutputStream oos = new ObjectOutputStream(fout);   
            oos.writeObject(obj);
            oos.close();
        }catch(Exception ex){ GrafProg.setMessage1("File not saved");
        System.out.println(ex);}
    } 
     
   
    private static File getFile(){
        FileChooser saveChooser = new FileChooser();
        saveChooser.setTitle("Save CurrentGraf");
        saveChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Graf File, .grf", "*.grf"));
        File file = saveChooser.showSaveDialog(GrafProg.getGrafController().getGrafPane().getScene().getWindow());
        if (file == null) return null;
        String filePath = file.getAbsolutePath();
        if(!filePath.endsWith(".grf")){
            file = new File(filePath+".grf");
        }
    return file;
    }
  
  //read an object from a file - not finished yet
  public static Object openFileObject(String ext){


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
      File file = openChooser.showOpenDialog(GrafProg.getGrafController().getGrafPane().getScene().getWindow());
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
    
  
  public static GrafProg openGrafFromFile(){
     GrafFiles gf = (GrafFiles)openFileObject("grf");
     GrafProg grafProg = new GrafProg();
     grafProg.getGrafSettings().setXMin(gf.xMin);
     grafProg.getGrafSettings().setXMax(gf.xMax);
     grafProg.getGrafSettings().setYMin(gf.yMin);
     grafProg.getGrafSettings().setYMax(gf.yMax);
     grafProg.getGrafSettings().setXAxisScale(gf.xAxisScale);
     grafProg.getGrafSettings().setYAxisScale(gf.yAxisScale);
     grafProg.getGrafSettings().setDecPlaces(gf.decPlaces);
     grafProg.getGrafSettings().setShowYAxis(gf.showYAxis);
     grafProg.getGrafSettings().setShowXAxis(gf.showXAxis);
     grafProg.getGrafSettings().setShowXAxisScale(gf.showXAxisScale);
     grafProg.getGrafSettings().setShowYAxisScale(gf.showYAxisScale);
      grafProg.getGrafSettings().setReversXY(gf.reverseXY);
      grafProg.getGrafSettings().setLeftScale(gf.leftScale);
      grafProg.getGrafSettings().setAutoScale(gf.autoScale);
      grafProg.getGrafSettings().setScaleFormat(gf.scaleFormat);
      grafProg.getGrafSettings().setScaleProcedure(gf.scaleProcedure);
      grafProg.setGrafFile(gf.grafFile);
      grafProg.setGrafSaved(gf.grafSaved);
      grafProg.setGrafList(gf.grafObjectList);
      grafProg.getAxes().setCurrentFont(gf.currentFont);
      grafProg.setBoxPlotsPlotted(gf.boxPlotsPlotted);
      grafProg.setData(gf.data);
      return grafProg;
  }



}
