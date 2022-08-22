package GrafProg.GrafUI;
/* ********************************
 * GrafProg.GrafUI.GrafPanel for GrafProg.GrafProg Project *
 * Panel graphs are painted on
 *  @author Bill Gillam           *
 *  2/25/15                       *
 **********************************/
/* *
 * Canvas for graphing.
 *
 */

import GrafProg.GrafObjects.Function.GrafFunction;
import GrafProg.GrafObjects.GrafObject;
import GrafProg.GrafObjects.Stats.GrafBoxPlot;
import GrafProg.GrafProg;

import javax.swing.*;

import java.io.*;
import java.awt.*;
//import java.awt.*;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

//Class Header
public class GrafPanel extends JPanel implements Serializable
{   //Instance Variables
    private transient Graphics2D grafCanvas;
    //private GrafProg.GrafProg gFrame;

    /**
     * Constructor for objects of class GrafProg.GrafUI.GrafPanel
     */
    GrafPanel()
    {


    }


    public void paintComponent(Graphics g){
        //System.out.println("in paint");
        grafCanvas = (Graphics2D)g;
        for (GrafObject graf: GrafProg.getGrafList()){
            graf.drawGraf(grafCanvas);
        }
        //GrafProg.GrafProg.zeroBoxPlotsPlotted();
        GrafBoxPlot.numBoxPlots = 0;
        GrafProg.setMessage1(GrafFunction.yFunctionList());
    }

    public void printPanel(){
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setJobName("Print GrafProg.GrafUI.GrafPanel");
        printJob.setPrintable ((g, pf, pageNum) -> {
            if (pageNum > 0)  return Printable.NO_SUCH_PAGE;
            Graphics2D gc = (Graphics2D) g;
            gc.translate(pf.getImageableX(), pf.getImageableY());
            paint(gc);
            return Printable.PAGE_EXISTS;
        });
        if (printJob.printDialog())
            try {
                printJob.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(null, "The Panel Did Not Print Because of a Printer Exception Error.", "Error!" , JOptionPane.ERROR_MESSAGE);
            }
    }

    //Getters and Setters
    //public void setOwner(GrafProg.GrafProg gs) {gFrame = gs;}

    //public Graphics2D getGrafCanvas(){
    //System.out.println(grafCanvas);
    //repaint();
    //return grafCanvas;
    //.}

}