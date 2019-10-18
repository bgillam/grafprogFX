
/*GrafCircle in GrafProg 
 * used to graphs a circle
 * 
 * @author Bill Gillam
 * @version 1/27/17]
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

//import GrafProg.GrafType;


public class GrafCircle extends GrafEllipse implements IGrafable{
        
        //private String functionString="";
        private GrafProg myOwner; //changed changed
        private GrafSettings gStuff;
        private Color fill = Color.WHITE;
        private double r;
        //private boolean fillFlag = false;
        //private String yString = "";
    
    public GrafCircle(){
        super();
        gStuff = super.getGrafSettings();
        setGrafType(GrafType.CIRCLE);
        
       }


     public GrafCircle(double x, double y, double r){
        this();
         setX(x-r);
         setY(y+r);
         setWidth(2*r);
         setHeight(2*r);
        
    }
        
    public GrafCircle(double x1, double y1, double r1, Color gColor){
        this(x1, y1, r1);
        setGrafColor(gColor);
                  
    }

    public GrafCircle(double x1, double y1, double r1, Color gColor, Color fColor){
        this(x1, y1, r1,gColor);
        setFillColor(fColor);

    }

     @Override
     public void drawGraf(Graphics2D gc){
         //System.out.println(super.getFillFlag());
         /*if (getFillFlag()) {
               gc.setColor(getFill());
               GrafPrimitives.fillEllipse(gStuff,getX(), getY() , getWidth(), getHeight(), gc );
           }*/
           gc.setColor(getFillColor());
           GrafPrimitives.fillEllipse(gStuff,getX(), getY(), getWidth(), getHeight(), gc );
           gc.setColor(getGrafColor());
           GrafPrimitives.grafEllipse(gStuff,getX(), getY(), getWidth(), getHeight(), gc );
          /* gc.setColor(Color.BLACK);
           gc.setPaint(Color.WHITE);*/
           
        }

        @Override
    public boolean isValidInput(GrafDialogController gdf) {
        boolean ok = true;
        if (gdf.getX1().equals("")) return false;
        if (gdf.getX2().equals("")) return false;
        if (gdf.getY1().equals("")) return false;
            if (!GrafInputHelpers.isDouble(gdf.getX1())) {
                GrafInputHelpers.setTextFieldColor(gdf.getX1TextField(), "red" );
                ok = false;
            }
            if (!GrafInputHelpers.isDouble(gdf.getX2())) {
                GrafInputHelpers.setTextFieldColor(gdf.getX2TextField(), "red" );
                ok = false;
            }
            if (!GrafInputHelpers.isDouble(gdf.getY1())) {
                GrafInputHelpers.setTextFieldColor(gdf.getY1TextField(), "red" );
                ok = false;
            }

        return ok;
    }

    /*@Override
    public boolean deepEquals(GrafObject o) {
        GrafEllipse gr = (GrafEllipse) o;
        if (getType() != o.getType()) return false;
        if (!getGrafColor().equals(gr.getGrafColor())) return false;
        if (!getFillColor().equals(gr.getFillColor())) return false;
        if (!(getX() == gr.getX())) return false;
        if (!(getY() == gr.getY())) return false;
        if (!(getWidth() == gr.getWidth())) return false;
        if (!(getHeight() == gr.getHeight())) return false;
        return true;
    }*/

    @Override
    public void loadObjectFields(GrafDialogController gdc) {
        super.loadObjectFields(gdc);
        gdc.setX1("" + getX());
        gdc.setY1("" + getY());
        gdc.setX2("" + getR());
        gdc.setfillColor(getFillColor());

    }


  /* public double getWidth() { return super.getWidth()
   public double getHeight() { return 2*r; }
   public double getDiameter(){return 2*r;}*/
   public Color getFill(){return fill;}
   public void setFill(Color f){fill = f;}
   public GrafSettings getGrafSettings(){return gStuff;}
 /*  public void setFillFlag(boolean tf){fillFlag = tf;}
   public boolean getFillFlag(){return fillFlag;}   */
        
     public String toString(){
           return "CIRCLE: "+((getX()+r)+", "+(getY()-r) +"); "+getX()+getWidth()+"");   //+", "+ " "+getGrafColor()+")";
       }

       public double getR(){
         return getWidth()/2;
       }
       

}