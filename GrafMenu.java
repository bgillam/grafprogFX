
/**
 * Main menu for GrafProg instance
 * @author (Bill Gillam)
 * @version (4/4/18)
 */
import javax.swing.*;

public class GrafMenu
{
       /**
     * Constructor for objects of class GrafMenu
     */
    public GrafMenu()
    {
        // initialise instance variables
        
    }

    /**
     Create the menu
     */
    public static JMenuBar createMenu(GrafProg gp){
         JMenuBar grafMenuBar = new JMenuBar(); //create the Menu Bar
         JMenu fileMenu = new JMenu("File");
         JMenuItem newMenuItem = new JMenuItem("New");
         JMenuItem openMenuItem = new JMenuItem("Open");
         JMenuItem importMenuItem = new JMenuItem("Import Data");
         JMenuItem saveMenuItem = new JMenuItem("Save");
         JMenuItem saveasMenuItem = new JMenuItem("Save As");
         JMenuItem saveimageMenuItem = new JMenuItem("Save Image");
         //private JMenuItem closeMenuItem = new JMenuItem("Close");
         JMenuItem printMenuItem = new JMenuItem("Print");
         //JMenuItem printsetMenuItem = new JMenuItem("Printer Setup");
         JMenuItem exitMenuItem = new JMenuItem("Exit");
        
         //Create the Edit Menu objects
         JMenu editMenu = new JMenu("Edit");
         JMenuItem cutMenuItem = new JMenuItem("Cut");
         JMenuItem copyMenuItem = new JMenuItem("Copy");
         JMenuItem pasteMenuItem = new JMenuItem("Paste");
         //Create the Plot Menu objects
         JMenu plotMenu = new JMenu("Plot");
         //function submenu
         JMenu functionMenu = new JMenu("Function"); 
         JMenuItem inputMenuItem = new JMenuItem("Input");
         JMenuItem valueMenuItem = new JMenuItem("Value");
         JMenuItem tangentMenuItem = new JMenuItem("Tangent");
         JMenuItem chordMenuItem = new JMenuItem("Chord");
         JMenuItem integrateMenuItem = new JMenuItem("Integrate");
         JMenuItem solveMenuItem = new JMenuItem("Roots");
         JMenuItem dataMenuItem = new JMenuItem("Data");
         //one variable stats submenu
         JMenu statMenu = new JMenu("Stats");
         JMenu onevarMenu = new JMenu("Distribution"); 
         JMenuItem freqMenuItem = new JMenuItem("Frequency Distribution Table");
         JMenuItem boxMenuItem = new JMenuItem("Boxplot");
         JMenuItem histoMenuItem = new JMenuItem("Histogram");
         JMenuItem polyMenuItem = new JMenuItem("Distribution Polygon");
         JMenuItem ogiveMenuItem = new JMenuItem("Ogive");
         JMenuItem flipMenuItem = new JCheckBoxMenuItem("Graphs On Y-Axis");
         JMenuItem cpMenuItem = new JMenuItem("Column Plot");
         JMenuItem scatterMenuItem = new JMenuItem("Scatterplot"); 
         //shape submenu
         JMenu shapeMenu = new JMenu("Shape"); 
         JMenuItem point2MenuItem = new JMenuItem("Point");
         JMenuItem lineMenuItem = new JMenuItem("Line Segment");
         JMenuItem rectMenuItem = new JMenuItem("Rectangle");
         //JMenuItem polydrawMenuItem = new JMenuItem("Polygon");
         JMenuItem ellipseMenuItem = new JMenuItem("Ellipse");
         JMenuItem circleMenuItem = new JMenuItem("Circle");
         //text submenu
         JMenuItem textMenuItem = new JMenuItem("Text");
         //Create the Calc Menu objects
         JMenuItem calcMenuItem = new JMenuItem("Calculator");
         JMenuItem statsMenuItem = new JMenuItem("Single Variable Statistics");
         JMenuItem regrMenuItem = new JMenuItem("Regression");
         //Create the Window Menu objects
         JMenu windowMenu = new JMenu("Window");
         JMenuItem setMenuItem = new JMenuItem("Set");
         JMenuItem autoMenuItem  = new JMenuItem("Auto");
         JMenuItem standardMenuItem = new JMenuItem("Standard");
         JMenuItem hideXMenuItem = new JCheckBoxMenuItem("Hide X Scale"); 
         JMenuItem hideYMenuItem = new JCheckBoxMenuItem("Hide Y Scale"); 
         JMenuItem xonlyMenuItem = new JCheckBoxMenuItem("Hide x-axis"); 
         JMenuItem yonlyMenuItem = new JCheckBoxMenuItem("Hide y-axis"); 
         //JMenuItem classesMenuItem = new JCheckBoxMenuItem("Use Classes for x scale"); 
         JMenuItem leftMenuItem = new JCheckBoxMenuItem("Left");
         //Create the Help Menu objects
         JMenu helpMenu = new JMenu("Help");
         JMenuItem contentMenuItem = new JMenuItem("Contents");
         JMenuItem aboutMenuItem = new JMenuItem("About");
         JMenuItem toolsMenuItem = new JMenuItem("Show Toolbar"); //checkbox 
      
          //add listeners
//          openMenuItem.addActionListener(gp);
//          newMenuItem.addActionListener(gp);
//          importMenuItem.addActionListener(gp);
//          saveMenuItem.addActionListener(gp);
//          saveasMenuItem.addActionListener(gp);
//          saveimageMenuItem.addActionListener(gp);
//          //closeMenuItem.addActionListener(gp);
//          printMenuItem.addActionListener(gp);
//          //printsetMenuItem.addActionListener(gp);
//          exitMenuItem.addActionListener(gp);
//          dataMenuItem.addActionListener(gp);
//          leftMenuItem.addActionListener(gp);
//          xonlyMenuItem.addActionListener(gp);
//          yonlyMenuItem.addActionListener(gp);
//          hideXMenuItem.addActionListener(gp);
//          hideYMenuItem.addActionListener(gp);
//          standardMenuItem.addActionListener(gp);
//          setMenuItem.addActionListener(gp);
//          autoMenuItem.addActionListener(gp);
//          calcMenuItem.addActionListener(gp);
//          aboutMenuItem.addActionListener(gp);
//          inputMenuItem.addActionListener(gp);
//          valueMenuItem.addActionListener(gp);
//          tangentMenuItem.addActionListener(gp);
//          chordMenuItem.addActionListener(gp);
//          integrateMenuItem.addActionListener(gp);
//          solveMenuItem.addActionListener(gp);
//          point2MenuItem.addActionListener(gp);
//          lineMenuItem.addActionListener(gp);
//          rectMenuItem.addActionListener(gp);
//          ellipseMenuItem.addActionListener(gp);
//          circleMenuItem.addActionListener(gp);
//          textMenuItem.addActionListener(gp);
//          scatterMenuItem.addActionListener(gp);
//          cpMenuItem.addActionListener(gp);
//          flipMenuItem.addActionListener(gp);
//          statsMenuItem.addActionListener(gp);
//          boxMenuItem.addActionListener(gp);
//          histoMenuItem.addActionListener(gp);
//          freqMenuItem.addActionListener(gp);
//          regrMenuItem.addActionListener(gp);
//          polyMenuItem.addActionListener(gp);
//          ogiveMenuItem.addActionListener(gp);
          
          //add menu items
          fileMenu.add(newMenuItem);
          fileMenu.add(openMenuItem);
          fileMenu.add(importMenuItem);
          fileMenu.addSeparator();
          fileMenu.add(saveMenuItem);
          fileMenu.add(saveasMenuItem);
          fileMenu.add(saveimageMenuItem);
          fileMenu.addSeparator();
          fileMenu.add(printMenuItem);
          //fileMenu.add(printsetMenuItem);
          fileMenu.addSeparator();
          fileMenu.add(exitMenuItem);
          editMenu.add(cutMenuItem);
          editMenu.add(copyMenuItem);
          editMenu.add(pasteMenuItem);
          functionMenu.add(inputMenuItem);
          functionMenu.addSeparator();
          functionMenu.add(valueMenuItem);
          functionMenu.add(tangentMenuItem);
          functionMenu.add(chordMenuItem);
          functionMenu.add(integrateMenuItem);
          functionMenu.add(solveMenuItem);
          plotMenu.add(functionMenu);
          statMenu.add(dataMenuItem);
          statMenu.addSeparator();
          plotMenu.add(statMenu);
          plotMenu.addSeparator();
          onevarMenu.add(freqMenuItem);
          onevarMenu.add(boxMenuItem);
          onevarMenu.add(histoMenuItem);
          onevarMenu.add(polyMenuItem);
          onevarMenu.add(ogiveMenuItem);
          onevarMenu.addSeparator();
          onevarMenu.add(flipMenuItem);
          statMenu.add(onevarMenu);
          statMenu.add(cpMenuItem);
          statMenu.add(statsMenuItem);
          statMenu.add(scatterMenuItem);
          statMenu.add(regrMenuItem);
          shapeMenu.add(point2MenuItem);
          shapeMenu.add(lineMenuItem);
          shapeMenu.add(rectMenuItem);
          //shapeMenu.add(polydrawMenuItem);
          shapeMenu.add(ellipseMenuItem);
          shapeMenu.add(circleMenuItem);
          plotMenu.add(shapeMenu);
          //textMenu.add(tnewMenuItem);
          //textMenu.add(fontMenuItem);
          //textMenu.add(teditMenuItem);
          plotMenu.add(textMenuItem);
          windowMenu.add(setMenuItem);
          windowMenu.add(autoMenuItem);
          windowMenu.add(standardMenuItem);
          windowMenu.addSeparator();
          windowMenu.add(hideXMenuItem);
          windowMenu.add(hideYMenuItem);
          windowMenu.add(xonlyMenuItem);
          windowMenu.add(yonlyMenuItem);
          windowMenu.add(leftMenuItem);
          helpMenu.add(contentMenuItem);
          helpMenu.add(aboutMenuItem);
          helpMenu.add(calcMenuItem);
          helpMenu.addSeparator();
          helpMenu.add(toolsMenuItem);
          grafMenuBar.add(fileMenu);
          grafMenuBar.add(editMenu);
          grafMenuBar.add(plotMenu);
          //grafMenuBar.add(calcMenu);
          grafMenuBar.add(windowMenu);
          grafMenuBar.add(helpMenu);      
          return grafMenuBar;
    }
}
