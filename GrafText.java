
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
       setFont(new TextField().getFont());
    }
        

   public GrafText(double x, double y, String t){
        this();
        setX(x);
        setY(y);
        setText(t);
   }

    public GrafText(double x, double y, String t, Color c){
        this(x, y, t);
        setGrafColor(c);
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

    @Override
    public boolean isValidInput(GrafDialogController gdf){
        if (gdf.getX1().equals("")) return false;
        if (gdf.getY1().equals("")) return false;
        if (gdf.getTextForDisplay().getText().equals("") ) return false;
        //eventually need to check font here
        return true;
    }

    @Override
    public boolean deepEquals(GrafObject o){
        GrafText gp = (GrafText) o;
        if (!getGrafColor().equals(gp.getGrafColor())) return false;
        if (getType() != o.getType()) return false;
        if (!getText().equals(gp.getText())) return false;
        if (!(getX() == gp.getX())) return false;
        if (!(getY() == gp.getY())) return false;
        //eventually need to check font here
        return true;
    }

    @Override
    public void loadObjectFields(GrafDialogController gdc){
        super.loadObjectFields(gdc);
        gdc.setX1(""+getX());
        gdc.setY1(""+getY());
        gdc.getTextForDisplay().setText(getText());
        javafx.scene.text.Font fxFont =  FontDialog.awtFontToFxFont(getFont());
        gdc.setFontName(fxFont.getName());
        gdc.setFontStyleText(fxFont.getStyle());
        gdc.setFontSizeText(fxFont.getSize()+"");
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

