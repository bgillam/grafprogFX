/**************************************** 
*  GrafStats  for GrafProg Project *
*  statistical calculation routines
*  @author Bill Gillam                  *
*  2/25/15                              *
*****************************************/
/**
* Static statistical calculations. 
 */


import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;


public class GrafStats {
	
	 public static boolean nullArray(Double[] dArray, String callerName){
		  if ((dArray.length == 0) || (dArray == null)) {
		  	String message = callerName+" was passed an empty column. Returning zero";
		  	Alert alert = new Alert(Alert.AlertType.ERROR, message);
            alert.showAndWait();
			// JOptionPane.showMessageDialog(null, callerName+" was passed an empty column. Returning zero", "Error!" , JOptionPane.ERROR_MESSAGE);
			 return true;
		 }
		 return false;
	}
	
     public static int getN(Double[] dArray){
    	 Double[] temp = getRidOfNulls(dArray); 
    	 if (nullArray(temp, "GetN()")) return 0;
    	 return temp.length;
     }
	
	public static double getMin(Double[] dArray)
     {   
		 Double[] temp = getRidOfNulls(dArray);
		 if (nullArray(temp, "GetMin()")) return 0;
		 double min = temp[0];
		 for (int i = 1; i < temp.length; i++){
    	     //if (cellIsNull(dArray, i)) continue;
    		 if (temp[i] < min) min = temp[i];
    	 }
    	 return min;
     }
     
     public static double getMax(Double[] dArray){
    	 Double[] temp = getRidOfNulls(dArray); 
    	 if (nullArray(temp, "GetMax()")) return 0;
    	 double max = temp[0];
    	 for (int i = 1; i < temp.length; i++){
    		 //if (cellIsNull(temp, i)) continue;
    		 if (temp[i] > max) max = temp[i];
    	 }
    	 return max;
     }
     
     public static double getMedian(Double[] dArray){
    	 Double[] temp = getRidOfNulls(dArray);  // Arrays.copyOf(dArray,dArray.length);
    	 if (nullArray(temp, "GetMedian()")) return 0;
    	 Arrays.sort(temp);
    	 if (evenLength(temp)){
    		 double firstMed = temp[(int)(temp.length/2)-1];
    		 double secondMed = dArray[ (int) (temp.length/2)];
    		 return (firstMed+secondMed)/2;
    	 }
    	 else
    		 return temp[(int) (temp.length/2)]; 
      } 
     
     public static double getQ1(Double[] dArray){
    	 Double[] temp = getRidOfNulls(dArray); 
    	 if (nullArray(temp, "GetQ1()")) return 0;
    	 Arrays.sort(temp);
    	 temp = Arrays.copyOf(temp, (int) temp.length/2-1);
    	 return getMedian(temp);
     }
     
     public static double getQ3(Double[] dArray){
    	 //copy array so original is not sorted
    	 Double[] temp = getRidOfNulls(dArray); 
    	 if (nullArray(temp, "GetQ3()")) return 0;
    	 Arrays.sort(temp);
    	 if (evenLength(temp)) temp = Arrays.copyOfRange(temp, (int) temp.length/2 , temp.length);
    	 else temp = Arrays.copyOfRange(temp, (int) temp.length/2 + 1, temp.length);
    	 return getMedian(temp);
     }

     public static double[] getModes(Double[] dArray){
    	//copy array so original is not sorted
    	 Double[] sortedArray = getRidOfNulls(dArray);
		 double[] returnNull = {};
    	 //return empty array, if null
    	 if (nullArray(sortedArray, "GetModes()")) {

    		 return returnNull;
    	 }
    	 Arrays.sort(sortedArray);

		 //row contains value column contains count
    	 double[][] countArray = new double[sortedArray.length][2];
         double previousValue;
		 countArray[0][0] = sortedArray[0];
         if (sortedArray[0] == -1) previousValue = 0; else previousValue = -1;
         int currentCount = 0;
         int maxCount = 1;
         for (int i = 0; i<sortedArray.length; i++){
         	 if (sortedArray[i] != previousValue) {
         	 	currentCount = 1;
			 }
         	 else {
         	 	currentCount = currentCount+1;
         	 	if (currentCount > maxCount) maxCount = currentCount;
         	 }
         	 countArray[i][0] = sortedArray[i];
			 countArray[i][1] = currentCount;
			 previousValue =  sortedArray[i];
		 }

         if (maxCount == 1) return returnNull;
         ArrayList<Double> modeList = new ArrayList<>();
         for (double[] row: countArray ){
			 if (row[1] == maxCount) modeList.add(row[0]);
		 }

         double[] modes = new double[modeList.size()];
         for (int i = 0; i<modeList.size(); i++){
         	 modes[i] = modeList.get(i);
		 }


    	 return modes;

     }
     
     public static double getMean(Double[] dArray){
    	 Double[] temp = getRidOfNulls(dArray); 
    	 if (nullArray(temp, "GetMean()")) return 0.0;
    	 double sum = 0;
    	 for (double d:temp)
    		 sum = sum + d;
    	 return sum/temp.length;
     }
     
     public static double getRange(Double[] dArray){
    	 Double[] temp = getRidOfNulls(dArray); 
    	 if (nullArray(temp, "GetRange()")) return 0.0;
    	 Arrays.sort(temp);
    	 return temp[temp.length-1] - temp[0];
     }
    
     public static double getVarianceOfSample(Double[] dArray){
    	 Double[] temp = getRidOfNulls(dArray);
    	 if (nullArray(temp, "GetVariance()")) return 0.0;
    	 double mean = getMean(temp);
    	 double sum = 0;
    	 for (double d:temp)
    		 sum = sum+(d - mean)*(d - mean);
    	 return sum/(temp.length-1);
     }
     
     public static double getVarianceOfPopulation(Double[] dArray){
    	 Double[] temp = getRidOfNulls(dArray); 
    	 if (nullArray(temp, "GetVariance()")) return 0.0;
    	 double mean = getMean(temp);
    	 double sum = 0;
    	 for (double d:temp)
    		 sum = sum+(d - mean)*(d - mean);
    	 return sum/(temp.length);
     }
     
     public static double getStandardDeviationOfSample(Double[] dArray){
    	 return Math.sqrt(getVarianceOfSample(dArray));
     }
     
     public static double getStandardDeviationOfPopulation(Double[] dArray){
    	 return Math.sqrt(getVarianceOfPopulation(dArray));
     }
     
     public static double getIQR(Double[] dArray){
    	 return getQ3(dArray) - getQ1(dArray);
     }
     
         
     public static double[] getFiveNumberSummary(Double[] dArray){
    	 double[] d = {GrafStats.getMin(dArray) , GrafStats.getQ1(dArray), GrafStats.getMean(dArray), GrafStats.getQ3(dArray), GrafStats.getMax(dArray) };
    	 return d;
     }
     
     public static double[] getClassesByNumber(int numClasses, double min, double max){
    	 double [] classBoundries = new double[numClasses+1];
         double classSize = (max - min)/numClasses;
         
         //double[] lowerClassBoundries = new double[numClasses+1];
    	 //double classSize = (max - min)/(numClasses);
    	 if (min+numClasses*classSize <= max) classSize = (max - min)/(numClasses-1);
    	   	 
    	 
    	 
    	 double n = min;
    	 for (int i = 0; i <= numClasses; i++){
    		 classBoundries[i] = n;
    		 n = n + classSize;   
    	 }
    	 return classBoundries;
     }
     
     public static double[] getClassesByClassSize(double classSize, double min, double max) {
    	 int numClasses = 0;
    	   for (double n = min;  n <= max; n = n + classSize)
    		   numClasses++;
    	   double[] boundries = new double[numClasses+1];
    	   for (int i = 0; i < numClasses+1; i++)
    		   boundries[i] = min+i*classSize;
    	   return boundries;
      }
     
     
     private static boolean evenLength(Double[] dArray){
    	 if ((double) dArray.length/2 == (double) (int) (dArray.length/2)) return true; 
    	 else return false;
     }
     
    // private static boolean cellIsNull(Double[] dArray, int index){
    // 	try{
    // 	    Double d = dArray[index];
    // 	    return false;
    // 	} catch (NullPointerException e){return true;}
    // }
     
     public static Double[] getRidOfNulls(Double[] dArray){
    	 if (nullArray(dArray, "GetRidOfNulls()")){ Double[] empty = {}; return empty; }
    	 ArrayList<Double> tempList = new ArrayList<Double>();  
    	 for (Double d: dArray){
    		 if (d != null) tempList.add(d);
    	 }
    	 Double[] returnArray = new Double[tempList.size()];
    	 for (int i = 0; i < returnArray.length; i++)
    		 returnArray[i]=tempList.get(i);
		 return returnArray;
     }

}
