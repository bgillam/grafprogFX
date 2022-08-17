
  /* *************************************
* FunctionString for GrafProg Project *
* function input parser
*  @author Bill Gillam                *
*  2/25/15                            *
***************************************/
/*
 *  Evaluates a function represented by a given string for an x input value
 */
import java.awt.event.KeyEvent;


//import java.io.*;
import javax.naming.NameNotFoundException;
import javax.swing.JOptionPane;
import java.util.Scanner;

//Orignal class FunctionString was large, so it has been broken into FunctionString, FunctionStringSubstring, FunctionShring Replacers, FunctionStringOperators and FunctionStringErrorChecks.
//Class FunctionString
public class FunctionString {
    //given a string representing a function  and an input value, returns an output value
    public static double fValue(String fString, double x) { //throws DomainViolationException, FunctionFormatException {

        double r;
        int errorCode = 0;
        if (fString.equals("")) return 0;
        fString = fString.toUpperCase(); //all caps
        fString = FunctionStringReplacers.removeString(" ",fString); //remove blanks
        fString = FunctionStringReplacers.addMultSigns(fString); //add * signs where needed. ex: 2(3+5 -> 2*(3+5)
        fString = FunctionStringReplacers.replaceMinusX(fString);
        fString = FunctionStringReplacers.replaceMinusParen(fString);
        fString = FunctionStringReplacers.replaceX(fString,x);  //replace x with its value
        fString = FunctionStringReplacers.replacePI(fString); //replace pi with its approximation
        FunctionStringErrorChecks.errorMsg(FunctionStringErrorChecks.checkErrors(fString)); //check for non-matching parentheses and other typo errors
        try{ r=Double.parseDouble(fString); } //if we can't parse what we have to a number,
        catch (NumberFormatException e){r = eval(fString); } //call eval to simplify
        return r;
    }

    //Recursive part called after all spaces removed, unaries changed to *1, multiplication signs added in front of parentheses where needed, 
    //and x replaced with value
    static double eval(String fString){   // throws DomainViolationException{
        int rp;
        String first,fs,middle,last;
        fString = FunctionStringReplacers.replaceDoubleOperator(fString);
        fString=fString.toLowerCase();
        fString = FunctionStringOperators.rightFunctions(fString);

        if (fString.equals("domainError")) return Double.NaN; //throw new DomainViolationException();
        int lp = fString.lastIndexOf('(');     //     getInnerLeftParensPos(fString); //find the number of left parentheses
        if (lp != -1){ 
            
            rp = fString.indexOf(')' , lp); //matchingRightParens(fString);  //find the number of right parentheses
            if (lp == 0) {
                first = "";
                middle = fString.substring(lp+1,rp);
                last = fString.substring(rp+1);
            }
            else{
                first = fString.substring(0, lp);
                middle = fString.substring(lp+1,rp);
                last = fString.substring(rp+1);
            }
        }
        else{
            
            first = "";
            middle = fString;
            last = "";
        }

        middle = FunctionStringOperators.doExponents(middle);
        if (middle.equals("imaginary number")) {
            return Double.NaN;
        }
        middle =FunctionStringOperators.multAndDiv(middle);

        middle = FunctionStringOperators.addAndSub(middle);

        fs = first+middle+last;

        double r;
        try {r = Double.parseDouble(fs);}
        catch (NumberFormatException e) {
            try {
                r = eval(fs);
            } catch (StackOverflowError stackOverflowError) {

                return Double.NaN;
            }
        }
        return r;

    }

    private static void printFStringAndWait(String fString){
         Scanner scan= new Scanner(System.in);
         System.out.println("fString = "+fString);
         System.out.println("Enter to contiue");
         String text = scan.nextLine();
    }

    public static void main(String[] args){
        String test = "x^2";
        double time2, time = System.currentTimeMillis();
        double y;
        try{
            for (double i = -10; i<10; i=i+.01) {
                y = 0.01;
                //if (Math.abs(i)>.000000000001)
                y= fValue("x^2", i);
                time2 = System.currentTimeMillis();
                if (time2-time > 10)
                    System.out.println("x= " +i + " y=" + y+" Elapsed Time "+(time2-time)/1000+"s");
                time = time2;
            }
            System.out.println("done");
            //for (int i=0; i<8; i++) System.out.println(i+" "+GrafUtils.GrafInputHelpers.isAnAlphaChar(test,i));
            //System.out.println(fValue("X",-1.0));
            //System.out.println(fValue("x-(-2)",-1.0));
            //System.out.println(fValue("x^2", -0.1000000000000188));
            //System.out.println(fValue("exp(-x^2)", 1.0));
            //System.out.println("Here we go");
            //System.out.println(fValue("x^4 - 2x + 5", -1.0));
            //System.out.println(fValue("x^4 - 2x + 5", 1.0));
            //System.out.println(fValue("3-2x", -1.0));
            //System.out.println("again:");
            //System.out.println(fValue("3-2x", 1.0));
            //  System.out.println(checkFunctionString("=x"));
        }catch (Exception e){System.out.println(e.toString());}
    }

}

