/*GrafRectangle - graphs a rectangle
 * 
 * @author Bill Gillam
 * @version 1/27/17]
 */
import java.io.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GrafRectangle extends GrafObject implements IGrafable {
    private String functionString = "";
    private GrafProg myOwner;
    private GrafSettings gStuff;
    private double x = 0;
    private double y = 0;
    private double width = 0;
    private double height = 0;
    private Color fill = Color.WHITE;
    private boolean fillFlag = false;
    //private String yString = "";


    public GrafRectangle() {
        gStuff = super.initGrafObject(GrafType.RECTANGLE);

    }


    public GrafRectangle(double x1, double y1, double width, double height) {
        this();
        setX(x1);
        setY(y1);
        setWidth(width);
        setHeight(height);
    }

    public GrafRectangle(double x1, double y1, double width, double height, Color gColor) {
        this(x1, y1, width, height);
        setGrafColor(gColor);
    }

    public GrafRectangle(double x1, double y1, double width, double height, Color gColor, Color fColor) {
        this(x1, y1, width, height, gColor);
        setFillColor(fColor);
    }

    @Override
    public void drawGraf(Graphics2D gc) {
        /*if (fillFlag) {
            gc.setColor(fill);
            GrafPrimitives.fillRect(gStuff, x, y, width, height, gc);
        }*/
        gc.setColor(getFillColor());
        GrafPrimitives.fillRect(gStuff, x, y, width, height, gc);
        gc.setColor(super.getGrafColor());
        GrafPrimitives.grafRect(gStuff, x, y, width, height, gc);

       /* gc.setColor(Color.BLACK);
        gc.setPaint(Color.WHITE);*/

    }

    @Override
    public boolean isValidInput(GrafDialogController gdf) {
        boolean ok = true;
        if (gdf.getX1().equals("")) return false;
        if (gdf.getX2().equals("")) return false;
        if (gdf.getY1().equals("")) return false;
        if (gdf.getY2().equals("")) return false;
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
        if (!GrafInputHelpers.isDouble(gdf.getY2())) {
            GrafInputHelpers.setTextFieldColor(gdf.getY2TextField(), "red" );
            ok = false;
        }
        return ok;
    }

    @Override
    public boolean deepEquals(GrafObject o) {
        GrafRectangle gr = (GrafRectangle) o;
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
    public void loadObjectFields(GrafDialogController gdc) {
        super.loadObjectFields(gdc);
        gdc.setX1("" + getX());
        gdc.setY1("" + getY());
        gdc.setX2("" + getWidth());
        gdc.setY2("" + getHeight());
        gdc.setfillColor(getFillColor());

    }

    @Override
    public void autoRange(){
        GrafProg.getGrafSettings().setYMax(getY()+GrafProg.getGrafSettings().getTenthWindowY());
        GrafProg.getGrafSettings().setYMin(getY()-getHeight()-GrafProg.getGrafSettings().getTenthWindowY());
    }

    @Override
    public GrafObject createGrafObjectFromController(GrafDialogController gdc){
        return new GrafRectangle(Double.parseDouble(gdc.getX1()),
                Double.parseDouble(gdc.getY1()), Double.parseDouble(gdc.getX2()),
                Double.parseDouble(gdc.getY2()), gdc.getGrafColor(), gdc.getFillColor());
    }


    public void setX(double xval) {
        x = xval;
    }

    public double getX() {
        return x;
    }

    public void setWidth(double val) {
        width = val;
    }

    public double getWidth() {
        return width;
    }

    public void setY(double val) {
        y = val;
    }

    public double getY() {
        return y;
    }

    public void setHeight(double val) {
        height = val;
    }

    public double getHeight() {
        return height;
    }

    public Color getFillColor() {
        return fill;
    }

    public void setFillColor(Color f) {
        fill = f;
    }

    public GrafSettings getGrafSettings() {
        return gStuff;
    }

    public void setFillFlag(boolean tf) {
        fillFlag = tf;
    }

    public boolean getFillFlag() {
        return fillFlag;
    }


    public String toString() {
        return "RECTANGLE: (" + getX() + ", " + getY() + "); (" + getWidth() + ", " + getHeight();//+ " "+getGrafColor()+" "+getFill()+")";
    }
}
   
