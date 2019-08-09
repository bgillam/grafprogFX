
/*  GrafOgive for GrafProg Project *
*  Store info and methods for graphing   *
*  Ogives
*  @author Bill Gillam                   *
*  2/3/17                                *
*********************************        */
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;



//Class Header
public class GrafOgive extends GrafHistogram implements IGrafable{
    //Instance Variables
    private int columnNumber;
    private GrafProg myOwner;
    private GrafSettings gStuff;
    private GrafTable table;
    //private FrequencyChartDialog fcd;
    
    private boolean relative = false;
    private boolean labelAxisByBoundries = true;
    private boolean byNumClasses = true;
    private boolean displayCounts = true;
    private double  begin;
    private double  end;
    private int numClasses = 10;
    private double classWidth = (begin - end)/10;
    private Color fill = Color.WHITE;
    private boolean fillFlag = false;
    private double[] classLimits;
    private double[] counts;
    
    
        
    //Constructor
    public GrafOgive(){
     setGrafType(GrafType.OGIVE);
     setMoveable(false);
     setGrafColor(Color.BLACK);
     setColumnNumber(1);
    }
    
    public GrafOgive(GrafProg sess){
        super();
        setGrafType(GrafType.OGIVE);
        myOwner = sess;
        gStuff = myOwner.getGrafSettings();
        table = myOwner.getData();//sess.setMessage1("Histogram for Column "+columnNumber);
    }
    
    //constructor 
    public GrafOgive(GrafProg sess, int column){
        this(sess);
        setColumnNumber(column);
        sess.setMessage1("Histogram for Column "+columnNumber);
    }
    
    public GrafOgive(GrafProg sess, int column, double b, double e, int numCl, Color c, boolean rel){
        this(sess, column);
        relative = rel;
        begin = b;
        end = e;
        numClasses = numCl;
        setGrafColor(c);
        byNumClasses = true;
        classLimits = GrafStats.getClassesByNumber(numClasses, begin, end);    //problem here!
        classWidth = classLimits[1] - classLimits[0];
    }
    public GrafOgive(GrafProg sess, int column, double b, double e, double classW, Color c, boolean rel){
        this(sess, column);
        relative = rel;
        begin = b;
        end = e;
        classWidth = classW;
        setGrafColor(c);
        byNumClasses = false;
        classLimits = GrafStats.getClassesByClassSize(classW, begin, end);  
        numClasses = classLimits.length;
    }
   
    
    //drawGraf overriding method in parent GrafObject
    @Override
    public void drawGraf(Graphics2D gc){
        gc.setColor(super.getGrafColor());
        Double[] temp = GrafStats.getRidOfNulls(myOwner.getData().getColumnValues(columnNumber));
        Arrays.sort(temp);
        int binCount = 0;
        int totalCount = 0;
        int i = 0;
        int upperBoundIndex = 1;
        int numValues = temp.length;
        String formatString = "%."+gStuff.getDecPlaces()+"f";
               
        int[] counts = new int[classLimits.length];
        while (i < numValues){
            //System.out.println("i = "+i+" temp[i] = "+temp[i]+"upperBoundIndex = "+upperBoundIndex+" upperLimit :"+ classLimits[upperBoundIndex]);
            if (
                temp[i] < classLimits[upperBoundIndex]){  
                binCount++;
                i++;
            }
            else{ 
                counts[upperBoundIndex-1] = binCount;
                binCount = 0;
                upperBoundIndex++;
            }
            
        }
        counts[upperBoundIndex-1] = binCount;
        
        
        boolean start = true;       
        double height = 0;
        double startHeight, endHeight, oldEndHeight, x1, y1, x2, y2, x3, y3, x4, y4;
        for (int j = 0; j < classLimits.length-1; j++){
            gc.setColor(super.getGrafColor());
            if (relative) {
                startHeight = height/numValues;
                endHeight = (height+counts[j])/numValues;
            }
            else{
                startHeight = height;
                endHeight = (height+counts[j]);
            }
            if (myOwner.getGrafSettings().getReverseXY()){
                x1 = startHeight; 
                y1 = classLimits[j];
                x2 = endHeight;
                y2 = classLimits[j+1];
                x3 = gStuff.getGrafHeight()/50;
                x4 = -x3;
                y3 = y1;
                y4 = y3;
                
                
            }
            else{
                x1 = classLimits[j];
                y1 = startHeight;
                x2 = classLimits[j+1];
                y2 = endHeight;
                x3 = x1;
                y3 = gStuff.getGrafHeight()/50;
                x4 = x1;
                y4 = -y3;;
                
                
            }
            
            {
                GrafPrimitives.grafLine(gStuff,x1, y1, x2, y2, gc);
                if (displayCounts){
                    GrafPrimitives.grafString(gStuff,x1, y1, ""+endHeight, gc);
               }
               if (labelAxisByBoundries){
                  GrafPrimitives.grafLine(gStuff,x3, y3, x4, y4, gc);
                  GrafPrimitives.grafString(gStuff,x4, y4, ""+String.format(formatString,y4, gc), gc);
               }  
            }
           if ((j == classLimits.length - 2) && (counts[j] == 0)) break;
           height = height + counts[j];
        }
        myOwner.setMessage2("");
        myOwner.incrementBoxPlotsPlotted();     
        gc.setColor(Color.BLACK);
    }
    
    @Override
    public GrafInputDialog createInputDialog(GrafProg gs){
        GrafInputDialog gfd = new GrafInputDialog(gs); 
        gfd.setTitle("Ogive"); 
        gfd.setHistoPanel(addHistoPanel(gs, gfd));
        //gfd.setColumnChooser(gfd.addColumnChooserPanel(gfd.getColumnsString(),true, false));
        gfd.addColumnChooserPanel(gfd.getColumnsString(),true, false);
        //gfd.setColumnChooser(gfd.getColumnChooser());
        gfd.setMarkChooser(gfd.addMarkPanel(new FillColorMarkPanel(false, false)));  //addMarkPanel(gSess.getGraphics().getFont(), true, true, true, false, false, false, false);
        gfd.setDeleter(gfd.addDeleterPanel(GrafType.OGIVE)); 
        gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.OGIVE)));          
        gfd.getCreateButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0    ) {
                saveOgive(gs,gfd);
                gfd.getDeleter().getDeleteComboBox().setModel(new javax.swing.DefaultComboBoxModel(getPlotList(gfd.getTempList(), gfd.getDeleter().getPlotIndex(), GrafType.OGIVE)));     
            }
        });
        gfd.getSaveChanges().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gfd.setFinalSave(true);
                saveOgive(gs,gfd);
                gs.setGrafList(gfd.getTempList());
                gfd.dispose();
            }
        });
        GrafObject.closeGFD(gfd);
        // gfd.setModal(true);
        // gfd.pack();
        // gfd.setVisible(true);  
        return gfd;
    }
    
     
  
    @Override
    public void setDeleteValues(int index, GrafInputDialog caller, ArrayList<GrafObject> tempList ){
                    GrafOgive oEdit = (GrafOgive)tempList.get(caller.getDeleter().getPlotIndex().get(index));
                    caller.getColumnChooser().setInputIndex(oEdit.getColumnNumber());
                    caller.getMarkChooser().setFillChecked(oEdit.getFillFlag());
                    caller.getMarkChooser().setColor(oEdit.getGrafColor());  
                    caller.getMarkChooser().setFillColor(oEdit.getFill());  
                    caller.getHisto().setBegin(oEdit.getBegin());
                    caller.getHisto().setEnd(oEdit.getEnd());
                    caller.getHisto().setnumClassesChecked(oEdit.getByNumClassChecked());
                    caller.getHisto().setNumClasses(oEdit.getNumClasses());
                    caller.getHisto().setClassSize(oEdit.getClassWidth());   
       }
       
        private static void saveOgive(GrafProg gs, GrafInputDialog gfd){
        int col = gfd.getColumnChooser().getInputColumn();
        if (gfd.getFinalSave() == true && col == 0) return; 
        addOgive(gs, gfd);
        gfd.getColumnChooser().setInputIndex(0);
    
    }
    
    private static void addOgive(GrafProg gs, GrafInputDialog gfd){
          int input = gfd.getInput();
          if (input == 0) return;
          GrafOgive oPlot;
          if (gfd.getHistoPanel().numClassesChecked()){
              oPlot = new GrafOgive(gs, input, gfd.getHistoPanel().getBegin(), gfd.getHistoPanel().getEnd(), gfd.getHistoPanel().getNumClasses(), gfd.getMarkChooser().getColor(), gfd.getHistoPanel().relativeHisto());
          }else
          {
              oPlot = new GrafOgive(gs, input, gfd.getHistoPanel().getBegin(), gfd.getHistoPanel().getEnd(), gfd.getHistoPanel().getClassSize(), gfd.getMarkChooser().getColor(), gfd.getHistoPanel().relativeHisto());
          }
          // if (markChooser.verticalChecked()) gPlot.myOwner;
          gfd.getTempList().add(oPlot);
    
    }
    
    //Setters and Getters
    public void setColumnNumber(int c){ columnNumber = c;}
    public int getColumnNumber(){ return columnNumber;}
    public void setFillFlag(boolean tf){
          fillFlag = tf;
    }
    public boolean getFillFlag(){
        return fillFlag;
    }
    public Color getFill(){
        return fill;
    }
    public void setFill(Color c){
        fill = c;
    }
    public double getBegin(){
        return begin;
    }
    public double getEnd(){
        return end;
    }
    public boolean getByNumClassChecked(){
        return byNumClasses;
    }
    public int getNumClasses(){
            return numClasses;
    }
    public double getClassWidth(){
        return classWidth;
    }
    public boolean relativeHisto(){
        return relative;
    }
    public void setRelativeHisto(boolean tf){
        relative = tf;
    }
    public void setShowCounts(boolean tf){
        displayCounts = tf;
    }
    public boolean getShowCounts(){
        return displayCounts;
    }
    public boolean labelAxis(){
        return labelAxisByBoundries;
    }
    public void setLabelAxis(boolean tf){
        labelAxisByBoundries = tf;
    }
   
    public String toString(){
        return "OGIVE: Col "+getColumnNumber();//+", "+ getGrafColor();
    }
    
    /* Setters and Getters from Parent GrafObject
     *  public void drawGraf(Graphics2D g2D){};
   
        public void setGrafType(GrafProg.GrafType gt){grafType = gt;}
        public GrafProg.GrafType getType(){return grafType; }
   
        public boolean isMoveable(){ return moveable; } 
        public void setMoveable(boolean tf){ moveable = tf;  }
        public boolean getMoveable(){return moveable;}
   
        public void setOwner(GrafProg owner){myOwner = owner;}
        public GrafProg getOwner(){return myOwner;}
   
        public void setGrafColor(Color c){grafColor = Color.BLACK;   }
        public Color getGrafColor() { return grafColor;}
     */
    
}
