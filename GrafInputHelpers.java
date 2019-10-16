/**************************************** 
*  GrafProg  for GrafProg Project *
*  @author Bill Gillam                  *
*  2/25/15                              *
*****************************************/
/**************************************** 
*  GrafInputHelpers  for GrafProg  
*  Data Entry Helpers
*  @author Bill Gillam                  *
*  2/25/15                              *
*****************************************/

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

public class GrafInputHelpers {
    
    public static boolean isAnInteger(String str){ return isInt(str);}
    
    public static boolean isInt(String str){
    try {Integer.parseInt(str);}catch (NumberFormatException e){return false;} return true;
       
    }
    
    public static boolean isDouble(String str){
    try {Double.parseDouble(str);}catch (NumberFormatException e){return false;} return true;
       
    }
	
    
	public static boolean isAnIntegerWithMessage(String s){
    	try{
    			Integer.parseInt(s);
    			return true;
    	}catch (NumberFormatException e){
    		    JOptionPane.showMessageDialog(null, s+" is not an Integer. ", "Input Error!" , JOptionPane.ERROR_MESSAGE);
    		    	}
    	return false;
    }
	
	public static boolean isADoubleWithMessage(String s){
    	try{
    			Double.parseDouble(s);
    			return true;
    	}catch (NumberFormatException e){
    		    JOptionPane.showMessageDialog(null, s+" is not an number. ", "Input Error!" , JOptionPane.ERROR_MESSAGE);
    		    	}
    	return false;
    }
	
	public static boolean isANumberChar(String fString, int counter){
   	 int keyCode;
   	 if (  (counter <0) || (counter >fString.length()-1) ) return false;
   	 keyCode = KeyEvent.getExtendedKeyCodeForChar(fString.charAt(counter));
        if ((keyCode > 47) && (keyCode < 58 )) return true;
        return false;
	
	 //checks to see if char at position counter is an alphtebetic character
    public static boolean isAnAlphaChar(String fString, int counter){
    	 int keyCode;
    	 if (  (counter <0) || (counter >fString.length()-1) ) return false;
    	 keyCode = KeyEvent.getExtendedKeyCodeForChar(fString.charAt(counter));
    	 if  ((keyCode >64) && (keyCode < 91)) return true;
    	 else return false;
    }

    public static void setTextFieldColor( t, String c){
    	String css = "-fx-text-fill: "+c;
    	t.setStyle

	}


}
