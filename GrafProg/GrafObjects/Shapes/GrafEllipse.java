package GrafProg.GrafObjects.Shapes;
/*GrafProg.GrafObjects.Shapes.GrafEllipse in GrafProg.GrafProg
 * used to graph and ellipse
 * 
 * @author Bill Gillam
 * @version 1/27/17]
 */

import GrafProg.GrafObjects.Dialog.GrafDialogController;
import GrafProg.GrafObjects.GrafType;
import GrafProg.GrafObjects.IGrafable;
import GrafProg.GrafPrimitives;
import GrafProg.GrafProg;
import GrafProg.GrafUI.GrafSettings;
import GrafProg.GrafObjects.GrafObject;

import java.awt.Color;
import java.awt.Graphics2D;


//import GrafProg.GrafProg.GrafProg.GrafObjects.GrafType;


public class GrafEllipse extends GrafRectangle implements IGrafable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private GrafProg myOwner;
    private GrafSettings gStuff;
   
    
    public GrafEllipse() {
        super();
        gStuff = super.getGrafSettings();
        setGrafType(GrafType.ELLIPSE);

    }


     public GrafEllipse(double x1, double y1, double width, double height){
         this();
         setX(x1);
         setY(y1);
         setWidth(width);
         setHeight(height);
     }
        
     private GrafEllipse(double x1, double y1, double width, double height, Color gColor){
            this(x1, y1, width, height);
            setGrafColor(gColor);

     }

     private GrafEllipse(double x1, double y1, double width, double height, Color gColor, Color fColor){
        this(x1, y1, width, height, gColor);
        setFillColor(fColor);
     }
        
    
     @Override
     public void drawGraf(Graphics2D gc){
         //System.out.println(super.getFillFlag());
         /*if (super.getFillFlag()) {
               gc.setColor(super.getFillColor());
               Plot.GrafProg.GrafPrimitives.fillEllipse(gStuff,getX(), getY(), getWidth(), getHeight(), gc );
           }*/
         gc.setColor(getFillColor());
         GrafPrimitives.fillEllipse(gStuff, getX(), getY(), getWidth(), getHeight(), gc);
         gc.setColor(super.getGrafColor());
         GrafPrimitives.grafEllipse(gStuff,getX(), getY(), getWidth(), getHeight() ,gc);
          /* gc.setColor(Color.BLACK);
           gc.setPaint(Color.WHITE);*/
           
        }


    /*@Override
    public boolean isValidInput(GrafProg.GrafObjects.Dialog.GrafDialogController gdf) {
        if (gdf.getX1().equals("")) return false;
        if (gdf.getX2().equals("")) return false;
        if (gdf.getY1().equals("")) return false;
        if (gdf.getY2().equals("")) return false;
        return true;
    }

    @Override
    public boolean deepEquals(GrafProg.GrafObjects.GrafObject o) {
        GrafProg.GrafObjects.Shapes.GrafEllipse gr = (GrafProg.GrafObjects.Shapes.GrafEllipse) o;
        if (getType() != o.getType()) return false;
        if (!getGrafColor().equals(gr.getGrafColor())) return false;
        if (!getFillColor().equals(gr.getFillColor())) return false;
        if (!(getX() == gr.getX())) return false;
        if (!(getY() == gr.getY())) return false;
        if (!(getWidth() == gr.getWidth())) return false;
        if (!(getHeight() == gr.getHeight())) return false;
        return true;
    }

    @Override
    public void loadObjectFields(GrafProg.GrafObjects.Dialog.GrafDialogController gdc) {
        super.loadObjectFields(gdc);
        gdc.setX1("" + getX());
        gdc.setY1("" + getY());
        gdc.setX2("" + getWidth());
        gdc.setY2("" + getHeight());
        gdc.setfillColor(getFillColor());

    }*/

    public GrafObject createGrafObjectFromController(GrafDialogController gdc){
        return new GrafEllipse(Double.parseDouble(GrafDialogController.getX1()),
                Double.parseDouble(GrafDialogController.getY1()), Double.parseDouble(GrafDialogController.getX2()),
                Double.parseDouble(GrafDialogController.getY2()), gdc.getGrafColor(), gdc.getFillColor());
    }
        
     public String toString(){
           return "ELLIPSE: ("+getX()+", "+getY()+"); ("+getWidth()+", "+getHeight();//+ " "+getGrafColor()+")";
       }
       

}
