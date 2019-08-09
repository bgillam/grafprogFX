/**
 * 
 * Main menu for data table instance
 * @author (Bill Gillam)
 * @version (4/4/18)
 */
import javax.swing.*;

public class GrafTableMenu
{
       /**
     * Constructor for objects of class GrafMenu
     */
    public GrafTableMenu()
    {
        // initialise instance variables
        
    }

    /**
     Create the menu
     */
    public static JMenuBar createMenu(GrafTable gt){
        JMenuBar dataMenuBar = new JMenuBar(); //create the Menu Bar
        JMenu fMenu = new JMenu("File");
        JMenu eMenu = new JMenu("Edit");
        JMenu sMenu = new JMenu("Size");
        JMenu gMenu = new JMenu("Column Actions");
        JMenu sortMenu = new JMenu("Sort");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        JMenuItem heading = new JMenuItem("Heading");
        JMenuItem newData = new JMenuItem("New/Clear All");
        JMenuItem importData = new JMenuItem("Import Data");
        JMenuItem exportData = new JMenuItem("Export Data");
        JMenuItem exitData = new JMenuItem("Done");
        JMenuItem resize = new JMenuItem("Resize");
        JMenuItem drow = new JMenuItem("Delete Row");
        JMenuItem dcolumn = new JMenuItem("Delete Column");
        JMenuItem random = new JMenuItem("Random");
        JMenuItem sequence = new JMenuItem("Function");
        JMenuItem recursion = new JMenuItem("Recursion");
        JMenuItem clear = new JMenuItem("Clear Column");
        JMenuItem zero = new JMenuItem("Zero Column");
        JMenuItem ascending = new JMenuItem("Ascending");
        JMenuItem descending = new JMenuItem("Descending");
      
        //add listeners
        newData.addActionListener(gt);
        importData.addActionListener(gt);
        exportData.addActionListener(gt);
        exitData.addActionListener(gt);
        resize.addActionListener(gt);
        drow.addActionListener(gt);
        dcolumn.addActionListener(gt);
        random.addActionListener(gt);
        sequence.addActionListener(gt);
        recursion.addActionListener(gt);
        clear.addActionListener(gt);
        zero.addActionListener(gt);
        ascending.addActionListener(gt);
        descending.addActionListener(gt);
        heading.addActionListener(gt);
        cut.addActionListener(gt);
        copy.addActionListener(gt);
        paste.addActionListener(gt);
        
        fMenu.add(newData);
        fMenu.add(importData);
        fMenu.add(exportData);
        fMenu.addSeparator();
        fMenu.add(exitData);
        eMenu.add(cut);
        eMenu.add(copy);
        eMenu.add(paste);
        sMenu.add(resize);
        sMenu.add(drow);
        sMenu.add(dcolumn);
        gMenu.add(heading);
        gMenu.addSeparator();
        gMenu.add(random);
        gMenu.add(sequence);
        gMenu.add(recursion);
        gMenu.addSeparator();
        gMenu.add(clear);
        gMenu.add(zero);
        sortMenu.add(ascending);
        sortMenu.add(descending);
        gMenu.add(sortMenu);
        dataMenuBar.add(fMenu);
        dataMenuBar.add(eMenu);
        dataMenuBar.add(sMenu);
        dataMenuBar.add(gMenu);
          
        return dataMenuBar;
    }
}
