package GrafProg.GrafUtils; /****************************************
*  Clipboard handler for GrafProg.GrafProg Project *
*  Copy and Paste methods
*  @author Bill Gillam                   *
*  2/25/15                               *
*****************************************/import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JOptionPane;


public class ClipboardHandler implements ClipboardOwner{

	//gets or sets clipboard
	
	public void setClipboardContents(String aString){
	    StringSelection stringSelection = new StringSelection(aString);
	    java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(stringSelection, this);
	  }
	
	 public String getClipboardContents() {
		    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		    Transferable copied = clipboard.getContents(null);
		    if (copied == null) return "";
		    boolean hasTransferableText = copied.isDataFlavorSupported(DataFlavor.stringFlavor);
		    if (hasTransferableText) {
		      try {
		        return (String)copied.getTransferData(DataFlavor.stringFlavor);
		      }
		      catch (UnsupportedFlavorException | IOException ex){
		    	  JOptionPane.showMessageDialog(null, "Format of information in clipboard is not supported for pasting into destination", "Unsupported Format." , JOptionPane.ERROR_MESSAGE);
		      }
		    }
			return "";
	 }

	@Override
	public void lostOwnership(java.awt.datatransfer.Clipboard arg0,
			Transferable arg1) {
		// required for implementation of clipboard
		
	}

}
