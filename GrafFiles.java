
/**
 * File Handling Routines for GrafProg
 * @author (Bill Gillam)
 * @version (4/6/18)
 */
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class GrafFiles
{
   
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
  
    
  
    public  static File saveFile(GrafProg g){
        File f = g.getGrafFile();
        if (f.toString().equals("") || !f.exists()) f = getFile(f);
        saveObjectToFile(f, g);
        return f;
    }
    
    public static File saveFileAs(GrafProg g){
        File f = g.getGrafFile();
        getFile(f);
        saveObjectToFile(f,g);
        return f;
    }

    private static void saveObjectToFile(File f, Object obj){
        try{
            FileOutputStream fout = new FileOutputStream(f.toString());
            ObjectOutputStream oos = new ObjectOutputStream(fout);   
            oos.writeObject(obj);
            oos.close();
        }catch(Exception ex){ JOptionPane.showMessageDialog(null, "Error saving file! ", "Error!" , JOptionPane.ERROR_MESSAGE); }  
    } 
     
   
    private static File getFile(File f){
      int typeCheck;
      JFrame parent = new JFrame();
      JFileChooser saveChooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter("grf", "GRF");
      saveChooser.setFileFilter(filter);
      do{
          typeCheck = saveChooser.showSaveDialog(parent);
          //check for ".grf" and add if not there
          if (typeCheck == JFileChooser.APPROVE_OPTION) {
              f = saveChooser.getSelectedFile();
              if (!f.toString().substring(f.toString().length()-4, f.toString().length()).equals(".grf")){
                  File tempFile = new File(f.toString()+".grf");
                  f.renameTo(tempFile);                    
              }
              if (!f.exists()){ return f; }  
              else {       
                int n = JOptionPane.showConfirmDialog(parent, "File Name "+ f.toString()+ " Exists. \n Overwrite It?", "Overwrite "+ f.toString()+ " ?",  JOptionPane.YES_NO_OPTION);
                if (n == 1) {return f;}   
              }
          } else break;
      } while(true);
      return f;
    }
  
  //read an object from a file - not finished yet
  public static Object openFileObject(String ext){
      JFrame parent = new JFrame();
      File file;
      int typeCheck;
      JFileChooser openChooser = new JFileChooser();
      String extLow = ext.toLowerCase();
      String extHigh = ext.toUpperCase();
      FileNameExtensionFilter filter = new FileNameExtensionFilter(extLow, extHigh);
      openChooser.setFileFilter(filter);
      typeCheck = openChooser.showOpenDialog(parent);
      if (typeCheck == JFileChooser.APPROVE_OPTION){
         file = openChooser.getSelectedFile();
         try{
             FileInputStream fileIn = new FileInputStream(file.toString());
             ObjectInputStream ois = new ObjectInputStream(fileIn);
             Object obj = ois.readObject();

             ois.close();  
             return obj;
                      
          }catch(Exception ex){ JOptionPane.showMessageDialog(null, "Error opening file. ", "Error!" , JOptionPane.ERROR_MESSAGE); }
          
      } 
      return null;
   
  }
    
  
  public static GrafProg openGrafFromFile(){
         return (GrafProg)openFileObject("grf");
    
  }
}
