
/* *
 * Oject for graphing a point
 * 
 * @author Bill Gillam
 * @version 2/25/15   
 */
import GrafUtils.GrafInputHelpers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class GrafPoint extends GrafText implements IGrafable
{
   //Constructors      
   GrafPoint(){
       super();
       //gStuff = super.initGrafObject(GrafType.POINT);
       setGrafType(GrafType.POINT);
       super.setText(".");
   }
   

   public GrafPoint(double x, double y){
       super(x, y, ".");
       setGrafType(GrafType.POINT);
   }
   

   public GrafPoint(double x, double y, String t){
        super(x, y, t);
        setGrafType(GrafType.POINT);
    }
    
   GrafPoint(double x, double y, String t, Color c){
        super(x, y, t);

        setGrafType(GrafType.POINT);
        setGrafColor(c);
   }
   
   private GrafPoint(double x, double y, String t, Font f, Color c){
        super(x, y, t, f, c);
        setGrafType(GrafType.POINT);
   }
   
     @Override
     public void drawGraf(Graphics2D gc){
       gc.setColor(getGrafColor());
    //   System.out.println(this);
       GrafPrimitives.grafString(gStuff, getX(), getY(), getText(),  gc);
       gc.setColor(Color.BLACK);
    }


    @Override
    public boolean isValidInput(GrafDialogController gdf){
       boolean ok = true;
        if (GrafDialogController.getX1().equals("")) return false;
        if (GrafDialogController.getY1().equals("")) return false;
        if (!GrafInputHelpers.isDouble(GrafDialogController.getX1())) {
            GrafInputHelpers.setTextFieldColor(GrafDialogController.getX1TextField(), "red" );
            ok = false;
        }
        if (!GrafInputHelpers.isDouble(GrafDialogController.getY1())) {
            GrafInputHelpers.setTextFieldColor(GrafDialogController.getY1TextField(), "red" );
            ok = false;
        }
        return ok;
    }

    @Override
    public boolean deepEquals(GrafObject o){
        GrafPoint gp = (GrafPoint) o;
        if (getType() != o.getType()) return false;
        if (!getMark().equals(gp.getMark())) return false;
        if (!getGrafColor().equals(gp.getGrafColor())) return false;
        if (!(getX() == gp.getX())) return false;
        return getY() == gp.getY();
    }

    @Override
    public void loadObjectFields(GrafDialogController gdc){
        super.loadObjectFields(gdc);

        GrafDialogController.setX1(""+getX());
        GrafDialogController.setY1(""+getY());
        gdc.settDialogMark(getMark());
    }
   
   public void setMark(String s){super.setText(s);}
   public String getMark(){return super.getText();}
   
   public String toString(){
    return "POINT: ("+getX()+", "+getY()+") "+getMark();//+ ", "+getGrafColor();
    }
   
    /*private static void savePoint(GrafProg gSess, GrafInputDialog gfd){
         if (gfd.getFinalSave() == true && Double.isNaN(gfd.getPointPanel().getX1())) return; 
             addPoint(gSess,gfd );
             gfd.getPointPanel().blankX1();
             gfd.getPointPanel().blankY1();
    }*/

    /*private static void addPoint(GrafProg gSess, GrafInputDialog gfd){
       if (Double.isNaN(gfd.getPointPanel().getX1())){gfd.NumErrorMessage("x1", "valid number"); return;}
       if (Double.isNaN(gfd.getPointPanel().getY1())){gfd.NumErrorMessage("Y1", "valid number"); return;}    
       GrafPoint gPlot = new GrafPoint(gfd.getPointPanel().getX1(), gfd.getPointPanel().getY1(), gfd.getMark(), gfd.getMarkChooser().getColor());
       gfd.getTempList().add(gPlot);
    }*/

    @Override
    public GrafObject createGrafObjectFromController(GrafDialogController gdc){
        return new GrafPoint(Double.parseDouble(GrafDialogController.getX1()), Double.parseDouble(GrafDialogController.getY1()), gdc.getDialogMark(), gdc.getDefaultFont(),  gdc.getGrafColor());
    }
    
}


