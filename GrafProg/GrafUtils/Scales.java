package GrafProg.GrafUtils;/* **************************************
*  GrafProg.GrafUtils.Scales  for GrafProg.GrafProg Project *
*  sets scales for axes on graphs
*  @author Bill Gillam                  *
*  2/25/15                              *
*****************************************

 * Computes scaling schemes for graphing axes
 */

import GrafProg.GrafObjects.Function.FunctionString;
import GrafProg.GrafProg;
import GrafProg.GrafUI.GrafSettings;
import GrafProg.GrafUI.GrafUI;

//class header
public class Scales  // implements Serializable
{
	//instance variable	
	private static GrafSettings gStuff;
	
	//Set 10x10 box
	public static void standardScale(GrafSettings gStuff){
		gStuff.setXMin(-10);
		gStuff.setXMax(10);
		gStuff.setYMin(-10);
		gStuff.setYMax(10);
	}
	
	//Compute Scale from Range
	public static void scalesFromRange(GrafSettings stuff){
		gStuff=stuff;
	//	if (gStuff.getScaleProcedure().equals(Plot.GrafProg.GrafUI.GrafSettings.ScaleProcedure.FROM_RANGE)){
			gStuff.setXAxisScale(xScaleFromRange());
			gStuff.setYAxisScale(yScaleFromRange());
	//	} else JOptionPane.showMessageDialog(null , "Scaling Failed!", "ERROR!", JOptionPane.WARNING_MESSAGE); 
	}
	
	public static void autoRange(GrafSettings stuff, String f){

		double tempYMin = 0;
		double tempYMax = 0;
		double yVal;
		double stuffMin = stuff.getXMin();
		double stuffMax = stuff.getXMax();

		double dx = (stuffMax-stuffMin)/100;
		for (double i = stuffMin; i<stuffMax; i = i + dx){
			//try {
				yVal = FunctionString.fValue(f, i);
			//} catch (GrafProg.GrafProg.GrafUtils.DomainViolationException e) {
				//ignore for autorange
			//;}catch (GrafProg.GrafObjects.Function.FunctionFormatException e) {JOptionPane.showMessageDialog(null, "Invalid function! ", "Error!" , JOptionPane.ERROR_MESSAGE); return;}
			if (yVal < tempYMin) tempYMin = yVal;
			else if (yVal > tempYMax) tempYMax = yVal;

		}

		stuff.setYMin(tempYMin- GrafProg.getGrafSettings().getTenthWindowY());
		stuff.setYMax(tempYMax+GrafProg.getGrafSettings().getTenthWindowY());
		scalesFromRange(stuff);
		GrafUI.repaintGraf();
	}
	
	//compute scales that are powers of 10
	public static void scalesPowersOfTen(GrafSettings stuff){
		 long p;		 
	     gStuff = stuff;
	     
	     p =  -Math.round(Math.log10(Math.abs(gStuff.getXMin())));
	     gStuff.setXMin(Math.round(gStuff.getXMin()*Math.pow(10,p))/(Math.pow(10, p)));
	     p =  -Math.round(Math.log10(Math.abs(gStuff.getXMax())));
	     gStuff.setXMax(Math.round(gStuff.getXMax()*Math.pow(10,p))/(Math.pow(10,p)));
	     double newScale = (gStuff.getXMax()-gStuff.getXMin())/getDivider(gStuff.getPanelWidth());
	     p =  -Math.round(Math.log10(newScale));
	     gStuff.setXAxisScale((Math.round(newScale*Math.pow(10,p)))/(Math.pow(10, p)));   
	     	     	     	     
	     p =  -Math.round(Math.log10(Math.abs(gStuff.getYMin())));
	     gStuff.setYMin(Math.round(gStuff.getYMin()*Math.pow(10,p))/(Math.pow(10, p)));
	     p =  Math.round(Math.log10(Math.abs(gStuff.getYMax())));
	     gStuff.setYMax(Math.round(gStuff.getYMax()*Math.pow(10,p))/(Math.pow(10,p)));
	     newScale = (gStuff.getYMax()-gStuff.getYMin())/getDivider(gStuff.getPanelHeight());
	     p =  Math.round(Math.log10(newScale));
	     gStuff.setYAxisScale((Math.round(newScale*Math.pow(10,p)))/(Math.pow(10, p)));
	     	     
    }
	
		
	// compute an appropriate scale from the width of the window (Actually should have named this computeScaleFromDomain)
	private static double xScaleFromRange(){
		int divider = getDivider(gStuff.getPanelHeight());
		return getXRange()/divider; 
	}
	
	// compute an appropriate scale from the height of the window 
	private static double yScaleFromRange(){
		int divider = getDivider(gStuff.getPanelHeight());
		return getYRange()/divider; 
	}
	
	//getters and setters
	private static int getDivider(double panelSize){ if (gStuff.getPanelWidth()<300) return 5; else return 10; }
	private static double getXRange(){return gStuff.getXMax() - gStuff.getXMin();}
    private static double getYRange(){return gStuff.getYMax() - gStuff.getYMin();}
    
      
}   
