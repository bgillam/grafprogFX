
/**
 * GrafObject - Abstract parent of all objects to be drawn
 * 
 * @author Bill Gillam
 * @version j1.0]
 */
//import javax.swing.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

abstract public class GrafObject implements Serializable, IGrafable
{
   private GrafType grType;
   private Color grafColor = Color.BLACK; 
   private boolean moveable;
   private GrafProg myOwner;

   protected ColumnChooserPanel columnChooser;
   protected ColorRadioMarkPanel mp;
   protected JDialog jd = new JDialog();
   protected PointPanel ptPanel;

   public abstract void drawGraf(Graphics2D g2D);
   public boolean isValidInput(GrafDialogController gdf){return true;}; //make abstract after refactr


    public GrafSettings initGrafObject(GrafType gType){
       setGrafType(gType);
       setMoveable(false);
       setGrafColor(Color.BLACK);
       return GrafProg.getGrafSettings();
   }

   public static GrafObject createGrafObjectFromController(GrafDialogController gdc, GrafType gType){
       switch (gType){
                case TEXT: return new GrafText(Double.parseDouble(
                        gdc.getX1()), Double.parseDouble(gdc.getY1()), gdc.getTextForDisplay().getText(),   gdc.getGrafColor());
                case COLUMN: return new GrafColumnPlot(gdc.getColumn1ChooserColumn(), gdc.getDialogMark(), gdc.isConnected(), gdc.getGrafColor());
                case BOXPLOT: return new GrafBoxPlot(gdc.getColumn1ChooserColumn(), gdc.getGrafColor(), gdc.getFNS());
                case SCATTER:return new GrafScatterPlot();
                case HISTOGRAM:{
                    if (gdc.getNumClassButton().isSelected())
                        return new GrafHistogram(gdc.getColumn1ChooserColumn(), Double.parseDouble(gdc.getX1()), Double.parseDouble(gdc.getX2()),
                                gdc.getNumClasses(), gdc.getGrafColor(),   gdc.getFillColor(), gdc.getFNS());
                    else  return new GrafHistogram(gdc.getColumn1ChooserColumn(), Double.parseDouble(gdc.getX1()), Double.parseDouble(gdc.getX2()),
                            Double.parseDouble(gdc.getClassWidthText()), gdc.getGrafColor(),  gdc.getFillColor(), gdc.getFNS()); }
                case FREQPOLYGON: return new GrafFreqPolygon();
                case OGIVE: return new GrafOgive();
                case POINT: return new GrafPoint(Double.parseDouble(gdc.getX1()), Double.parseDouble(gdc.getY1()), gdc.getDialogMark(),   gdc.getGrafColor());
                case LINESEGMENT: return new GrafSegment(Double.parseDouble(gdc.getX1()),
                        Double.parseDouble(gdc.getY1()), Double.parseDouble(gdc.getX2()), Double.parseDouble(gdc.getY2()), gdc.getGrafColor(), gdc.getDialogMark());
                case RECTANGLE: return new GrafRectangle(Double.parseDouble(gdc.getX1()),
                        Double.parseDouble(gdc.getY1()), Double.parseDouble(gdc.getX2()),
                        Double.parseDouble(gdc.getY2()), gdc.getGrafColor(), gdc.getFillColor());
                case ELLIPSE: return new GrafEllipse(Double.parseDouble(gdc.getX1()),
                        Double.parseDouble(gdc.getY1()), Double.parseDouble(gdc.getX2()),
                        Double.parseDouble(gdc.getY2()), gdc.getGrafColor(), gdc.getFillColor());
                case CIRCLE: return new GrafCircle(Double.parseDouble(gdc.getX1()),
                        Double.parseDouble(gdc.getY1()), Double.parseDouble(gdc.getX2())
                       , gdc.getGrafColor(), gdc.getFillColor());
                case FUNCTION: return new GrafFunction(gdc.getFunctionString(), gdc.getGrafColor());
                case FVALUE: return new GrafValue( gdc.getFunctionString(), Double.parseDouble(gdc.getX1()), gdc.getGrafColor(), gdc.getDialogMark());
                case TANGENT: return new GrafTangent(gdc.getFunctionString(), Double.parseDouble(gdc.getX1()), gdc.getGrafColor(), gdc.getDialogMark());
                case CHORD: return new GrafChord(gdc.getFunctionString(), Double.parseDouble(gdc.getX1()), Double.parseDouble(gdc.getX2()),   gdc.getGrafColor(), gdc.getDialogMark());
                case INTEGRAL: return new GrafIntegral(gdc.getFunctionString(),Double.parseDouble(gdc.getX1()), Double.parseDouble(gdc.getX2()), Integer.parseInt(gdc.getNText()), gdc.getGrafColor(), gdc.getFillColor());
                case FZERO: return new GrafZeros(gdc.getFunctionString(), Double.parseDouble(gdc.getX1()), Double.parseDouble(gdc.getX2()),  Double.parseDouble(gdc.getDx()), gdc.getGrafColor(),gdc.getDialogMark() );
                default: return null;

        }
    }
        public static GrafObject createGrafObjectFromController(GrafType gType){
        switch (gType){
            case TEXT: return new GrafText();
            case COLUMN: return new GrafColumnPlot();
            case BOXPLOT: return new GrafBoxPlot();
            case SCATTER:return new GrafScatterPlot();
            case HISTOGRAM: return new GrafHistogram();
            case FREQPOLYGON: return new GrafFreqPolygon();
            case OGIVE: return new GrafOgive();
            case POINT: return new GrafPoint();
            case LINESEGMENT: return new GrafSegment();
            case RECTANGLE: return new GrafRectangle();
            case ELLIPSE: return new GrafEllipse();
            case CIRCLE: return new GrafCircle();
            case FUNCTION: return new GrafFunction();
            case FVALUE: return new GrafValue();
            case TANGENT: return new GrafTangent();
            case CHORD: return new GrafChord();
            case INTEGRAL: return new GrafIntegral();
            case FZERO: return new GrafZeros();
            default: return null;
        }
    }

    //load object fields into graf dialog
    public void loadObjectFields(GrafDialogController gdc){
        Color awtColor = getGrafColor();
        javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(getGrafColor().getRed(),
                getGrafColor().getGreen(), getGrafColor().getBlue(), 1);
        gdc.getGrafColorPicker().setValue(fxColor);
    }


    public boolean deepEquals(GrafObject g) {
       if (getType() != g.getType()) return false;
       if (getGrafColor() != g.getGrafColor()) return false;
       return true;
    }
   
   public static void createPanel(){}
   
   public boolean axesAreReversible(){
       switch (grType) {
       //case COLUMN : return true;
       case HISTOGRAM: return true;
       case BOXPLOT:  return true;
       //case SCATTER: return true; 
       case FREQPOLYGON: return true;
       case OGIVE: return true;
       }
       return false;
   }
   
   /*public String[] getColumnsString(){
       return GrafProg.getData().getHeaderArray();
   }*/
   
   
   public void setGrafType(GrafType gt){grType = gt;}
   public GrafType getType(){return grType; }
   
   public boolean isMoveable(){ return moveable; } 
   public void setMoveable(boolean tf){ moveable = tf;  }
   public boolean getMoveable(){return moveable;}
   
   public void setOwner(GrafProg owner){myOwner = owner;}
   public GrafProg getOwner(){return myOwner;}
   
   public void setGrafColor(Color c){grafColor = c;   }
   public Color getGrafColor() { return grafColor;}
    

      
}
