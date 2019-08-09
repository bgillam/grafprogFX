
/**
 * Loads list of current objects and lets user select items for deletion.
 * 
 * @author (Bill Gillam) 
 * @version (1/1/2017)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class GrafDeletePanel extends JPanel
{
    private JLabel deleteMessage;
    private JComboBox<String> deleteComboBox;
    private ArrayList<GrafObject> tempList;
    private GrafType gType;
    private static ArrayList<Integer> plotIndex = new ArrayList<Integer>();
    private GrafInputDialog caller;
   
    
    public GrafDeletePanel(GrafInputDialog c, GrafType gType, ArrayList<GrafObject> tempList)//, GrafObject g)
    {
       caller = c;  
       setBackground(new Color(220, 220, 220));
       setLayout(new BorderLayout());
       deleteMessage= new JLabel("Choose "+gType+" to delete/edit:");
       add(deleteMessage, BorderLayout.NORTH);
       deleteComboBox = new JComboBox();
       add(deleteComboBox, BorderLayout.CENTER);
       JPanel deleteButtonPanel = new JPanel();
       deleteButtonPanel.setLayout(new BorderLayout());
       //set up delete button
       JButton deleteButton = new JButton("Delete");
       deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
               if (JOptionPane.showConfirmDialog(null, "Delete "+gType+" "+deleteComboBox.getSelectedItem(), "Delete Plot?", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION){ 
                       deleteIndexedPlot(deleteComboBox.getSelectedIndex(), tempList, gType);  
                       deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafObject.getPlotList(tempList, plotIndex, gType)));
               }
            }
        });
       deleteButtonPanel.add(deleteButton, BorderLayout.NORTH);
       //set up edit button
       JButton editButton = new JButton("Edit");
       editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
               if (JOptionPane.showConfirmDialog(null, "Edit "+gType+" "+deleteComboBox.getSelectedItem(), "Edit Plot?", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION){ 
                       GrafObject.createGrafObject(gType).setDeleteValues(deleteComboBox.getSelectedIndex(), caller, tempList);
                       deleteIndexedPlot(deleteComboBox.getSelectedIndex(), tempList, gType);  
                       deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafObject.getPlotList(tempList, plotIndex, gType)));
               }
            }
        });
        deleteButtonPanel.add(editButton, BorderLayout.CENTER);
       
       //set up clear button
       JButton btnClearAll = new JButton("Clear All");
       btnClearAll.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    while (plotIndex.size() > 0)
                        deleteIndexedPlot(0, tempList, gType);
                        deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafObject.getPlotList(tempList, plotIndex, gType)));
                        
                }
       });
      deleteComboBox.setModel(new javax.swing.DefaultComboBoxModel(GrafObject.getPlotList(tempList, plotIndex, gType)));
      deleteButtonPanel.add(btnClearAll, BorderLayout.SOUTH);  
      add(deleteButtonPanel, BorderLayout.SOUTH);
    }
          
    
        
    // deletes plot given it's index. 
    private void deleteIndexedPlot(int index, ArrayList<GrafObject> tempList, GrafType gType ){
                int toBeRemoved = plotIndex.get(index); //get's master index from whole list - not just grafType
                tempList.remove(toBeRemoved);
                plotIndex.remove(index);
                indexPlots(tempList, gType);
                if (caller.getColumnChooser() != null) 
                    caller.getColumnChooser().setInputIndex(0);
                //System.out.println("After Removal: "+tempList);
                //return tempList;
    }
    
    //indexPlots creates an index of positions of GrafObjects of type gType
    public static ArrayList<Integer> indexPlots(ArrayList<GrafObject> tempList, GrafType gType){
            int i = 0;
            plotIndex.clear();
            if (tempList != null)
                for (GrafObject gObject: tempList){
                    if (gObject.getType() == gType) plotIndex.add(i);
                    i++;
                }
                return plotIndex;
    }

   public JComboBox getDeleteComboBox(){
       return deleteComboBox;
   }
        
   public ArrayList<Integer> getPlotIndex(){
       return plotIndex;
    }
    
    public void setPlotIndex(ArrayList<Integer> index){
        plotIndex = index;
    }
    
}


    