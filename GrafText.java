
/*GrafText - Displays text on the graphing panel
 * 
 * @author Bill Gillam
 * @version j1.0]
 */
//import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;


public class GrafText extends GrafObject implements IGrafable
{       private String text;
        private GrafProg myOwner;
        protected GrafSettings gStuff;
        private Font font;
        //protected Color color;
        protected double x = 0;
        protected double y = 0;
        
        
        
   public GrafText(){
     super();
       gStuff = super.initGrafObject(GrafType.TEXT);
       setText("");
    }
        

   public GrafText(double x, double y, String t){
        this();
        setX(x);
        setY(y);
        setText(t);
   }
   
   public GrafText(double x, double y, String t, Font f, Color c){
        this(x, y, t);
        setGrafColor(c);
        setFont(f);
  }
   
   
   @Override
   public void drawGraf(Graphics2D gc){
      
       gc.setColor(super.getGrafColor());
       Font oldFont = gc.getFont();
       gc.setFont(font);
       GrafPrimitives.grafString(gStuff,x, y, text,  gc);
       gc.setFont(oldFont);
       gc.setColor(Color.BLACK);
    }
 

  
    


 
 private static void saveText(GrafProg gSess, GrafInputDialog gfd){
         if (gfd.getFinalSave() == true && Double.isNaN(gfd.getPointPanel().getX1())) return; 
             addText(gSess,gfd );
             gfd.getPointPanel().blankX1();
             gfd.getPointPanel().blankY1();
           
   }

 private static void addText(GrafProg gSess, GrafInputDialog gfd){
       if (Double.isNaN(gfd.getPointPanel().getX1())){gfd.NumErrorMessage("x1", "valid number"); return;}
       if (Double.isNaN(gfd.getPointPanel().getY1())){gfd.NumErrorMessage("Y1", "valid number"); return;}    
       GrafText gPlot = new GrafText(gfd.getPointPanel().getX1(), gfd.getPointPanel().getY1(), gfd.getMarkChooser().getTextMark() , gfd.getMarkChooser().getFont() , gfd.getMarkChooser().getColor());
       gfd.getTempList().add(gPlot);
  }  
   
   public void setX(double xval){ x = xval; }
   public double getX() { return x; }   
   public void setY(double yval){ y = yval; }
   public double getY() { return y; }   
   public void setFont(Font f){font = f;}
   public Font getFont(){return font;}
   public String getText(){return text;}
   public void setText(String s){text = s;}
  
 
 public String toString(){
    return "TEXT: ("+getX()+", "+getY()+") "+getText()+" "+getGrafColor();
    }
}

/* Inherited from GrafObject
   private GrafProg.GrafType grafType;
   private Color grafColor = Color.BLACK; 
   private boolean moveable;
   private GrafProg myOwner;
     
   
   public void drawGraf(Graphics2D g2D){};
   
   public void setGrafType(GrafProg.GrafType gt){grafType = gt;}
   public GrafProg.GrafType getType(){return grafType; }
   
   public boolean isMoveable(){ return moveable; } 
   public void setMoveable(boolean tf){ moveable = tf;  }
   public boolean getMoveable(){return moveable;}
   
   public void setOwner(GrafProg owner){myOwner = owner;}
   public GrafProg getOwner(){return myOwner;}
   
   public void setGrafColor(Color c){grafColor = c;   }
   public Color getGrafColor() { return grafColor;}
  */
 