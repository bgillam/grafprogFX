
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
    
    GrafCircle(){
        super();
        gStuff = super.getGrafSettings();
        setGrafType(GrafType.CIRCLE);
        
       }


     private GrafCircle(double x, double y, double r){
        this();
         setX(x-r);
         setY(y+r);
         setWidth(2*r);
         setHeight(2*r);
        
    }
        
    private GrafCircle(double x1, double y1, double r1, Color gColor){
        this(x1, y1, r1);
        setGrafColor(gColor);
                  
    }

    private GrafCircle(double x1, double y1, double r1, Color gColor, Color fColor){
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
        if (GrafDialogController.getX1().equals("")) return false;
        if (GrafDialogController.getX2().equals("")) return false;
        if (GrafDialogController.getY1().equals("")) return false;
            if (!GrafInputHelpers.isDouble(GrafDialogController.getX1())) {
                GrafInputHelpers.setTextFieldColor(GrafDialogController.getX1TextField(), "red" );
                ok = false;
            }
            if (!GrafInputHelpers.isDouble(GrafDialogController.getX2())) {
                GrafInputHelpers.setTextFieldColor(GrafDialogController.getX2TextField(), "red" );
                ok = false;
            }
            if (!GrafInputHelpers.isDouble(GrafDialogController.getY1())) {
                GrafInputHelpers.setTextFieldColor(GrafDialogController.getY1TextField(), "red" );
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
        GrafDialogController.setX1("" + getX());
        GrafDialogController.setY1("" + getY());
        GrafDialogController.setX2("" + getR());
        gdc.setfillColor(getFillColor());

    }

    public GrafObject createGrafObjectFromController(GrafDialogController gdc){
        return new GrafCircle(Double.parseDouble(GrafDialogController.getX1()),
                Double.parseDouble(GrafDialogController.getY1()), Double.parseDouble(GrafDialogController.getX2())
                , gdc.getGrafColor(), gdc.getFillColor());
    }


  /* public double getWidth() { return super.getWidth()
   public double getHeight() { return 2*r; }
   public double getDiameter(){return 2*r;}*/
  Color getFill(){return fill;}
   void setFill(Color f){fill = f;}
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