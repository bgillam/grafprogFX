/********************************* 
* GrafPanel for GrafProg Project *
* Panel graphs are painted on
*  @author Bill Gillam           *
*  2/25/15                       *
**********************************/
/**
 * Canvas for graphing.
 *  
 */
import javax.swing.*;

import java.io.*;
import java.awt.*;
//import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

//Class Header
public class GrafPanel extends JPanel implements Serializable 
{   //Instance Variable
    private transient Graphics2D grafCanvas;
    //private GrafProg gFrame;
    private GrafStage gStage;

    /**
     * Constructor for objects of class GrafPanel
     *
     */

    public GrafPanel(GrafStage g)
    {
        gStage = g;

    }


    
      
    public void paintComponent(Graphics g){
        //System.out.println("in paint");
        grafCanvas = (Graphics2D)g;

        grafCanvas.clearRect(0,0,  getWidth(), getHeight());

        String functionList = "";
        for (GrafObject graf: gStage.getGrafList()){
            graf.drawGraf(grafCanvas);
            if (graf.getType() == GrafType.FUNCTION) {
                GrafFunction gf = (GrafFunction)graf;
                functionList=functionList+"y="+gf.getFunction()+"; ";
            }
            //System.out.println();
        }
        gStage.zeroBoxPlotsPlotted();
        gStage.setMessage1(functionList);


      }
    
    public void printPanel(){
      PrinterJob printJob = PrinterJob.getPrinterJob();
      printJob.setJobName("Print GrafPanel");
      printJob.setPrintable (new Printable() {    
        public int print(Graphics g, PageFormat pf, int pageNum){
          if (pageNum > 0)  return Printable.NO_SUCH_PAGE;
          Graphics2D gc = (Graphics2D) g;
          gc.translate(pf.getImageableX(), pf.getImageableY());
          paint(gc);
          return Printable.PAGE_EXISTS;
        }
      });
      if (printJob.printDialog() != false) 
          try {
            printJob.print();
          } catch (PrinterException ex) {
              JOptionPane.showMessageDialog(null, "The Panel Did Not Print Because of a Printer Exception Error.", "Error!" , JOptionPane.ERROR_MESSAGE);
          }
    }
    
    //Getters and Setters
    public void setOwner(GrafStage gs) {gStage = gs;}
    
    //public Graphics2D getGrafCanvas(){
        //System.out.println(grafCanvas);
        //repaint();
        //return grafCanvas;
    //.}
 
}