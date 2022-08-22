package GrafProg.GrafObjects;
/* *
 * GrafProg.GrafObjects.GrafObject - Abstract parent of all objects to be drawn
 * 
 * @author Bill Gillam
 * @version j1.0]
 */
//import javax.swing.*;

import GrafProg.GrafObjects.Dialog.GrafDialogController;
import GrafProg.GrafProg;
import GrafProg.GrafUI.GrafSettings;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


abstract public class GrafObject implements Serializable, IGrafable
{
   private GrafType grType;
   private Color grafColor = Color.BLACK; 
   private boolean moveable;
   private GrafProg myOwner;
   protected JDialog jd = new JDialog();

   public abstract void drawGraf(Graphics2D g2D);
   public abstract void autoRange();
   public abstract GrafObject createGrafObjectFromController(GrafDialogController gdc);
   public abstract boolean isValidInput(GrafDialogController gdf);

   public GrafSettings initGrafObject(GrafType gType){
       setGrafType(gType);
       setMoveable(false);
       setGrafColor(Color.BLACK);
       return GrafProg.getGrafSettings();
   }

    //load object fields into graf dialog
    public void loadObjectFields(GrafDialogController gdc){
        //Color awtColor = getGrafColor();
       /* javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(getGrafColor().getRed(),
                getGrafColor().getGreen(), getGrafColor().getBlue(), 1);
        gdc.getGrafColorPicker().setValue(fxColor);*/
    }

    //see if every field in object equal
    public boolean deepEquals(GrafObject g) {
       if (getType() != g.getType()) return false;
        return getGrafColor() == g.getGrafColor();
    }

   public javafx.scene.paint.Color getFXColor(){
       return javafx.scene.paint.Color.rgb(getGrafColor().getRed(),
               getGrafColor().getGreen(), getGrafColor().getBlue(), 1);
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
   
   //Getters and Setters
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
