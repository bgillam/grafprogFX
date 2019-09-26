  /**************************************
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
import javax.swing.JOptionPane;
import java.util.Scanner;

//Class FunctionString
public class FunctionString {
    
    public static void main(String args[]){
        String test = "123e56i8";
        try{
           //for (int i=0; i<8; i++) System.out.println(i+" "+GrafInputHelpers.isAnAlphaChar(test,i));
           //System.out.println(fValue("X",-1.0));
           //System.out.println(fValue("x-(-2)",-1.0));
           //System.out.println(fValue("exp(-x^2)", -1.0));
           //System.out.println(fValue("exp(-x^2)", 1.0));
           //System.out.println("Here we go");
           //System.out.println(fValue("x^4 - 2x + 5", -1.0));
           //System.out.println(fValue("x^4 - 2x + 5", 1.0));
           //System.out.println(fValue("3-2x", -1.0));
           //System.out.println("again:");
           //System.out.println(fValue("3-2x", 1.0));
            System.out.println(checkFunctionString("=x"));
        }catch (Exception e){}
    }

    //returns true if the character is a non minus operator
    private static boolean isaNonMinusOperator(char s){
        boolean nonMinusOperator = false;
        switch (s){
            case  '+':nonMinusOperator = true;
            case  '*':nonMinusOperator = true;
            case  '/':nonMinusOperator = true;
            case  '^':nonMinusOperator = true;
        }
        return nonMinusOperator;
    }
    
    //Checks that a string can be evaluated at a value. Useful to do before saving a function object.
    public static boolean isValidAtX(String functionString, double x){ //returns 0 for empty String. -1 for error 1 for OK
        if (functionString.equals("")) { JOptionPane.showMessageDialog(null, "Empty Function String! ", "Error!" , JOptionPane.ERROR_MESSAGE); return false;}
        try{
           if (Double.isNaN(fValue(functionString, x))) return false; else return true;
           } catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Invalid function! ", "Error!" , JOptionPane.ERROR_MESSAGE); return false;
           } catch (ArithmeticException e) { JOptionPane.showMessageDialog(null, "Function not defined at x = "+x+"!", "Error!" , JOptionPane.ERROR_MESSAGE); return false;
           } catch (DomainViolationException e) { return false; 
           } catch (FunctionFormatException e) { return false;  
           } catch (Exception e) {return false;}  
    }      
    
    //Ignores domain error
    public static boolean isValidAtXIgnoreDomainError(String functionString, double x){
        if (functionString.equals("")) { JOptionPane.showMessageDialog(null, "Empty Function String! ", "Error!" , JOptionPane.ERROR_MESSAGE); return false;}
        try{
           if (Double.isNaN(fValue(functionString, x))) return false; else return true;
           } catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Invalid function! ", "Error!" , JOptionPane.ERROR_MESSAGE); return false;
           } catch (ArithmeticException e) { JOptionPane.showMessageDialog(null, "Function not defined at x = "+x+"!", "Error!" , JOptionPane.ERROR_MESSAGE); return false;
           } catch (DomainViolationException e) { return true; 
           } catch (FunctionFormatException e) { return false;  
           } catch (Exception e) { return false;}
           
    }   
    
    
    // checks from counter-1 to left to return the left boundry of a numeric substring
    //used to find the number on the left of an operator located at counter
    private static int getBeginLeftNumPos(int counter, String fString){
        counter--;
        do {
           if (counter == 0) return counter;  
           if (GrafInputHelpers.isANumberChar(fString, counter)) {counter --; continue;}
           if (fString.charAt(counter) == '.') {counter--; continue;}
           if (fString.charAt(counter) == 'E') {counter--; continue; }
           if (fString.charAt(counter-1) == '(') return counter;
           if (isaNonMinusOperator(fString.charAt(counter))) return counter+1;
           if (fString.charAt(counter) == '-'){ 
                   if (fString.charAt(counter-1) == 'E') { counter--; continue;} 
                   else return counter;  
           }
           counter--;
        } while (true);
    }

       
  //checks from counter+1 to right to return the right boundry of a numeric substring
    //used to find the number to the right of an operator located at count
    private static int getEndRightNumPos(int counter, String fString){
        counter++;
        do {
            if (!GrafInputHelpers.isANumberChar(fString, counter)) 
                if (fString.charAt(counter) == '-') {  
                    if (GrafInputHelpers.isANumberChar(fString, counter-1) ) return counter-1;
                }              
                else if  (  (fString.charAt(counter) != '.')  &&  (fString.charAt(counter) != 'E')  ) return counter-1; 
                else if (counter +1 < fString.length()) 
                    if ( GrafInputHelpers.isAnAlphaChar(fString, counter) && GrafInputHelpers.isAnAlphaChar(fString, counter+1) )   return counter-1;
            counter++;
            if (counter > fString.length()-1) return fString.length()-1;
        } while (true);
    }
    
    
    //returns the first part of a string before the numeric string that includes position counter
    //used to return the part of the string that precedes the substring of interest
    private static String getPreString(int counter, String fString){
        int beginLeftNumPos = getBeginLeftNumPos(counter, fString);
        if (beginLeftNumPos == 0) return ""; 
        else try{
            //System.out.println("Last digit: "+fString.substring(beginLeftNumPos-1, beginLeftNumPos));
            Double.parseDouble(fString.substring(beginLeftNumPos-1, beginLeftNumPos));
            //System.out.println("return "+fString.substring(0, beginLeftNumPos)+"+");
            return fString.substring(0, beginLeftNumPos)+"+";
        }
        catch (NumberFormatException e){     
            return fString.substring(0, beginLeftNumPos);
        }
    }
    
    //returns// return the part of the string that comes after the substring of interest
    private static String getPostString(int counter, String fString){
        if (getEndRightNumPos(counter, fString) == fString.length()-1) return ""; else return fString.substring(getEndRightNumPos(counter,fString)+1, fString.length());
    }
    
      
    //Gets the number string to the left of position count. 
    //Used to get number to left of an operator
    private static double getLeftNum(int counter, String fString){
        String leftSubstr="";;
        try{
            leftSubstr = fString.substring(getBeginLeftNumPos(counter,fString),counter);
            return Double.parseDouble(leftSubstr);
        } catch (NumberFormatException e){
            
            System.out.println("number format exception: "+fString);
            
            
            return 0;
            
        }
    }
    
      
    //gets the number  string to the right of position count. 
    //Used to get number to right of an operator
    private static double getRightNum(int counter, String fString){
            int end = getEndRightNumPos(counter, fString)+1;
            return Double.parseDouble(fString.substring(counter+1, end));
    }
    
 // tests for symbols that are not a function, operator, decimal point or parentheses. returns false if it finds an invalid symbol
    private static boolean tokensOK(String fString){
           String middle = fString;
           int i;
           middle= removeString("ABS",middle);
           middle= removeString("ARCSIN",middle);
           middle= removeString("ARCTAB",middle);
           middle= removeString("ARCCOS",middle);
           middle= removeString("COS",middle);
           middle= removeString("COT",middle);
           middle= removeString("CSC",middle);
           middle= removeString("DTOR",middle);
           middle= removeString("EXP",middle);
           middle= removeString("frac",middle);
           middle= removeString("INT",middle);
           middle= removeString("LN",middle);
           middle= removeString("LOG",middle);
           middle= removeString("PI",middle);
           middle= removeString("RTOD",middle);
           middle= removeString("ROUND",middle);
           middle= removeString("SIN",middle);
           middle= removeString("SEC",middle);
           middle= removeString("SQRT",middle);
           middle= removeString("TAN",middle);
           middle= removeString("TRUNC",middle);
           i = 0;
           if (middle.equals("")) return true;
           do{
              switch (middle.charAt(i)){
                   case '+': break;
                   case '*': break;
                   case '/': break;
                   case '^': break;
                   case '.': break;
                   case '-': break;
                   //case '{': break;
                   //case '}': break;
                   case '(': break;
                   case ')': break;
                   case '0': break;
                   case '1': break;
                   case '2': break;
                   case '3': break;
                   case '4': break;
                   case '5': break;
                   case '6': break;
                   case '7': break;
                   case '8': break;
                   case '9': break;
                   case 'E': break;
                   default : return false;
                    }
                    i = i+1;
           } while (i < middle.length()); 
           return true;
      }

    
  

// checks for errors and returns a code; error 2: non matching parentheses; error 3: negative exponent; error 4: two consecutive operators; error 6: bad token
     private static int checkErrors(String fString) throws FunctionFormatException{
         int errorCode = 0;
         int leftCount = 0;
         int rightCount = 0;
     if (tokensOK(fString)){ 
         for (int counter = 0; counter < fString.length(); counter++){
             if (fString.charAt(counter) =='(')  leftCount++;
             else if (fString.charAt(counter) ==')')  rightCount++;
             //else if ((fString.charAt(counter) =='^') && (fString.charAt(counter+1) == '-')) errorCode = 3; //not sure why I disallowed negative exponents
             else if (isaNonMinusOperator(fString.charAt(counter)) && (isaNonMinusOperator(fString.charAt(counter+1)))) return 4; //don't allow two consecutive operators
             else if ((fString.charAt(counter) == '-') && (isaNonMinusOperator(fString.charAt(counter+1)))) return 4;
        }
     }else return 6; //bad tokens in string
     if (leftCount != rightCount)  return 2;//non-matching parentheses
     if (errorCode != 0) throw new FunctionFormatException();
     return errorCode;
    }
        
        
  //removes all occurrences of a particular string from passed fString
  private static String removeString(String token, String fString){
            int p;
            do {
                p = fString.indexOf(token);
                if (p != -1)
                    fString = fString.substring(0,p)+fString.substring(p+token.length(),fString.length());
                else return fString;            
            }while (true);
        }
    
    
    //adds multiplication signs in front of a variable if needed  
    private static String addMultSigns(String fString){
        int counter;
        counter = 1;
        //insert times signs between an letter and a number or ( and number 
        if (fString.length() == 1) return fString;
        do{
          // keyCode = KeyEvent.getExtendedKeyCodeForChar(fString.charAt(counter));
           if ( (GrafInputHelpers.isAnAlphaChar(fString,counter)) || (fString.charAt(counter) == '(')) //if a letter or parentheses
               if ( GrafInputHelpers.isANumberChar(fString, counter-1))                                  // and the previous character is a number
                        fString = fString.substring(0,counter)+'*'+fString.substring(counter, fString.length()); //add a *
           
           counter++;
        }while (counter < fString.length());
        return fString;
    }
    
    //replace each x in string with the passed double
    private static String replaceX(String fString, double x){
        int pos = -1;
        do{ 
                if (pos+1 >= fString.length()) break;
                pos = fString.indexOf('X',pos+1);
                if (pos == -1) break;  
                if (!GrafInputHelpers.isAnAlphaChar(fString, pos + 1)) 
                    if (pos == 0) fString = x+fString.substring(pos+1, fString.length());
                    else fString =  fString.substring(0,pos)+x+fString.substring(pos+1, fString.length());
                
        }while (true);
        fString = fString.toUpperCase();
        return fString;
    }
    
  
    //replace "pi" with an approximation of pi
    private static String replacePI(String fString){
        int pos = -1;
        //System.out.println("in replacePI"+" "+fString);
        do{ 
            pos = fString.indexOf("PI",pos+1);
            if (pos == -1) break;
            fString =  fString.substring(0,pos)+Double.toString(Math.PI)+fString.substring(pos+2, fString.length());
        }while (pos != -1);
        return fString;
    }
    
    private static String replaceDoubleOperator(String fString){
        int pos = -1;
        String token = "--";
        do{ 
            pos = fString.indexOf(token,pos+1);
            if (pos == -1) break;            
            fString =  fString.substring(0,pos)+"+"+fString.substring(pos+2, fString.length());
            //System.out.println(token);
        }while (pos != -1);
        token = "++";
        do{ 
            pos = fString.indexOf(token,pos+1);
            if (pos == -1) break;            
            fString =  fString.substring(0,pos)+"+"+fString.substring(pos+2, fString.length());
            //System.out.println(token);
        }while (pos != -1);
        token = "+-";
        do{ 
            pos = fString.indexOf(token,pos+1);
            if (pos == -1) break;            
            fString =  fString.substring(0,pos)+"-"+fString.substring(pos+2, fString.length());
            //System.out.println(token);
        }while (pos != -1);
        token = "-+";
        do{ 
            pos = fString.indexOf(token,pos+1);
            if (pos == -1) break;            
            fString =  fString.substring(0,pos)+"-"+fString.substring(pos+2, fString.length());
            //System.out.println(token);
        }while (pos != -1);
        return fString;
    }
    
    private static String replaceMinusParen(String fString){
        int pos = -1;
        String token = "-(";
        do{ 
            pos = fString.indexOf(token,pos+1);
            if (pos == -1) break;            
            fString =  fString.substring(0,pos)+"-1*("+fString.substring(pos+2, fString.length());
        }while (pos != -1);
        return fString;
    }
        
    private static String replaceMinusX(String fString){
        int minusXPos = fString.indexOf("-X");
         while (minusXPos != -1){
            String first = fString.substring(0,minusXPos);
            String last = fString.substring(minusXPos+2);
            fString = first+"-1*X"+last;
            minusXPos = fString.indexOf("-X");
         }
        
        
        return fString;
    }
    
    //parses string from left to right performing additions and subtractions
    private static String addAndSub(String middle){
        //System.out.println("in addAndSub: " +middle);
        int counter = 1;
        do{
            try{ 
                Double.parseDouble(middle); 
                return middle; 
            }
            catch (NumberFormatException e){} 
                if (middle.charAt(counter) == '+') {
                    middle = getPreString(counter, middle)+  (getLeftNum(counter,middle)+getRightNum(counter,middle)) +getPostString(counter, middle);
                    counter = 1;
                }
                else if ((middle.charAt(counter) == '-') && (!(middle.charAt(counter-1) == 'E'))){
                    middle = getPreString(counter, middle)+(getLeftNum(counter,middle)-getRightNum(counter,middle))+getPostString(counter, middle);
                    counter = 1;
                }
            
                counter++;
        } while (counter < middle.length()); 
          return middle;
    }
    
    
    //parses string from left to right performing multiplications and divisions
    private static String multAndDiv(String middle){
        int counter = 0;
        do {
            try{ 
                Double.parseDouble(middle); 
                return middle; 
            }
            catch (NumberFormatException e){ }
            
            if (middle.charAt(counter) == '*') {
                middle = getPreString(counter, middle)+(getLeftNum(counter,middle)*getRightNum(counter,middle))+getPostString(counter, middle);
                counter = 0;
            }   
            else if (middle.charAt(counter) == '/'){
                //if (getRightNum(counter, middle) != 0){
                    middle = getPreString(counter, middle)+(getLeftNum(counter,middle)/getRightNum(counter,middle))+getPostString(counter, middle);
                    counter = 0;
                //}else {} //some sort of error handling either throw or dialog
           }
           counter++;
           //System.out.println("pass "+counter+": "+middle);
        }while (counter < middle.length());
        return middle;  //addAndSub(middle);
    }
    
       
     //evaluates each occurence of a passed rightfunction and replace them with a equivalent double. Uses recursion if necessary   
     private static String replaceRightFunction(String token, String fString) throws DomainViolationException {
        
                int tokenStartPos;
                int tokenEndPos;
                int lpCounter;
                int rpCounter;
                int lastRightIndex = -1;
                double r;
                int i;
                do{
                    try{ return ""+Double.parseDouble(fString); } catch (NumberFormatException e){ } //return if a single number
                    tokenStartPos = fString.indexOf(token);
                    if (tokenStartPos == -1) return fString;                                         // return if token not found
                    tokenEndPos = tokenStartPos + token.length()-1;
                    lpCounter = 0;
                    rpCounter = 0;
                    i = 1;
                    do{
                       if (fString.charAt(tokenEndPos+i) == '(') lpCounter++;
                       else if (fString.charAt(tokenEndPos+i) == ')'){
                           rpCounter++;
                           lastRightIndex = tokenEndPos+i;
                       }
                       i++;
                   }while (rpCounter != lpCounter);
                   
                   if (lastRightIndex == -1) lastRightIndex = fString.length();
                   try{ r = Double.parseDouble(fString.substring(tokenEndPos+2, lastRightIndex));}
                   catch (NumberFormatException e){ r = eval(fString.substring(tokenEndPos+2, lastRightIndex));}
                   //boolean domainError = false;
                   switch (token){
                           case "ABS": {r = Math.abs(r); break;}
                           case "ARCTAN":{ r = Math.atan(r); break; }
                           case "ARCSIN": {if ((r >= -1) && (r <= 1))r = Math.asin(r); else { return "domainError"; } break;}
                           case "ARCCOS": {if ((r>=-1) && (r<=1)) r = Math.acos(r); else {return "domainError";}break;}
                           case "COS": {r = Math.cos(r); }
                           case "COT": {if (Math.sin(r) != 0) r = Math.cos(r)/Math.sin(r); else {return "domainError"; }break;}
                           case "CSC": {if (Math.sin(r) != 0) r = 1/Math.sin(r); else {return "domainError";}break;}
                           case "DTOR": {r = r*Math.PI/180;break;}
                           case "RTOD": {r = r*180/Math.PI;break;}
                           case "EXP": { r = Math.exp(r);break;}
                           case "FRAC": { r = r - (int)r; break;}
                           case "INT": {r = (int)(r);break;}
                           case "LN": {if (r > 0) r = Math.log(r); else {return "domainError";};break;}
                           case "LOG": {if (r>0) r = Math.log10(r); else {return "domainError";}break;}
                           case "ROUND": {r = Math.round(r); break;}
                           case "SIN": {r = Math.sin(r);break;}
                           case "SEC":{ if (Math.cos(r) != 0) r = 1/Math.cos(r); else {return "domainError";}break;}
                           case "SQRT":{ if (r>0) r = Math.sqrt(r); else {return "domainError";};break;}
                           case "TAN":{if (Math.cos(r) != 0) r = Math.sin(r)/Math.cos(r); else {return "domainError";} break; }
                                                    
                   }
                   //if (domainError) { throw new DomainViolationException(); }
                   if (lastRightIndex == fString.length()) lastRightIndex--;
                   if (tokenStartPos == 0) fString = r + fString.substring(lastRightIndex+1, fString.length());
                   else fString = fString.substring(0,tokenStartPos) + r + fString.substring(lastRightIndex+1, fString.length());
                  
              }while (tokenStartPos > -1);
             return fString;
          }

    //  calls sub to evaluate occurences of each right function
    private static String rightFunctions(String fString) throws DomainViolationException{
              boolean alphaFound = false;
              for (int i=0; i < fString.length(); i++){ 
                  //int keyCode = KeyEvent.getExtendedKeyCodeForChar(fString.charAt(i));
                  if (GrafInputHelpers.isAnAlphaChar(fString,i)) alphaFound = true;
              }   
              if (!alphaFound) return fString; 
              
              fString = replaceRightFunction("ABS",fString);
              fString = replaceRightFunction("ARCSIN",fString);
              fString = replaceRightFunction("ARCTAN",fString);
              fString = replaceRightFunction("ARCCOS",fString);
              fString = replaceRightFunction("COS",fString);
              fString = replaceRightFunction("COT",fString);
              fString = replaceRightFunction("CSC",fString);
              fString = replaceRightFunction("DTOR",fString);
              fString = replaceRightFunction("EXP",fString);
              fString = replaceRightFunction("INT",fString);
              fString = replaceRightFunction("LN",fString);
              fString = replaceRightFunction("LOG",fString);
              fString = replaceRightFunction("PI",fString);
              fString = replaceRightFunction("RTOD",fString);
              fString = replaceRightFunction("ROUND",fString);
              fString = replaceRightFunction("SEC",fString);
              fString = replaceRightFunction("SIN",fString);
              fString = replaceRightFunction("SQRT",fString);
              fString = replaceRightFunction("TAN",fString);
              
             return fString;
            
    }  
              
    //replaces exponent expressions with their double value. 
    private static String doExponents(String fString){
        double leftNumber, rightNumber;
        int pos = -1;
        do{
            pos = fString.indexOf('^');
            if (pos == -1) return fString;  //multAndDiv(fString);
            leftNumber = getLeftNum(pos,fString);
            rightNumber = getRightNum(pos,fString);
            if ((leftNumber <= 0) && ((int)(rightNumber) != rightNumber)){/*dialog or throw exception*/}
            else  fString = getPreString(pos,fString)+Math.pow(leftNumber, rightNumber)+getPostString(pos,fString);
        }while (true);
        
    }

    
         
    //Recursive part called after all spaces removed, unaries changed to *1, multiplication signs added in front of parentheses where needed, 
    //and x replaced with value
    private static double eval(String fString) throws DomainViolationException{
        int rp = 0;
        double r = 0;
        String first,fs,middle,last;
        fString = replaceDoubleOperator(fString);
        fString.toLowerCase();
        fString = FunctionString.rightFunctions(fString);
        if (fString.equals("domainError")) throw new DomainViolationException();
        int lp = fString.lastIndexOf('(');     //     getInnerLeftParensPos(fString); //find the number of left parentheses
        if (lp != -1){ 
            
            rp = fString.indexOf(')' , lp); //matchingRightParens(fString);  //find the number of right parentheses
            if (lp == 0) {
                first = "";
                middle = fString.substring(lp+1,rp);
                last = fString.substring(rp+1, fString.length());
            }
            else{
                first = fString.substring(0, lp);
                middle = fString.substring(lp+1,rp);
                last = fString.substring(rp+1, fString.length());
            }
        }
        else{
            
            first = "";
            middle = fString;
            last = "";
        }
        middle = doExponents(middle);
        middle = multAndDiv(middle);
        middle = addAndSub(middle);
        fs = first+middle+last;
        try {r = Double.parseDouble(fs);}
        catch (NumberFormatException e) {
            try {
                r = eval(fs);
            } catch (StackOverflowError stackOverflowError) {
                return -1;
            }
        }
        return r;
    }

    //returns error message for error code
    public static int errorMsg(int eCode)
    {     
        if (eCode == 0) return 0; 
        String message = "";
         switch (eCode){
         case 1: message = "Division by zero"; break;
         case 2: message = "Non-matching parenthesis in function"; break;
         case 3: message = "Must enclose negative exponents in parentheses"; break;
         case 4: message = "Incorrect Operator Syntax"; break;
         case 5: message = "Rightfunctions must use parenthesis. (example: ABS(-2);"; break;
         case 6: message = "Unrecognized token in function srtring."; break;
         case 8: message = "Can not raise negative number to a fractional power."; break;
         case 10:message = "Input a real number with no operators"; break;
         }
         JOptionPane.showMessageDialog(null, message, "Error "+eCode, JOptionPane.ERROR_MESSAGE);
         return 0;
    }
    
    private static void printFStringAndWait(String fString){
         Scanner scan= new Scanner(System.in);
         System.out.println("fString = "+fString);
         System.out.println("Enter to contiue");
         String text = scan.nextLine();
    }

    public static int checkFunctionString(String fString) {
        double r;
        int errorCode = 0;
        if (fString.equals("")) return -1;
        fString = fString.toUpperCase(); //all caps
        fString = removeString(" ",fString); //remove blanks
        fString = addMultSigns(fString); //add * signs where needed. ex: 2(3+5 -> 2*(3+5)
        fString = replaceMinusX(fString);
        fString = replaceMinusParen(fString);
        fString = replaceX(fString,0);  //replace x with its value
        fString = replacePI(fString); //replace pi with its approximation
        try {
            errorCode = checkErrors(fString);
            //errorMsg(errorCode); //check for non-matching parentheses and other typo errors
        }catch (Exception e){}

        //errorMsg(errorCode);
        if (errorCode != 0) {
            return errorCode; } //possibly do something, but more likely implement a try-catch later

        try{ r=Double.parseDouble(fString); } //if we can't parse what we have to a number,
        catch (NumberFormatException e){
            try{
                r = eval(fString);
            } catch (DomainViolationException dve){return 9;}
        } //call eval to simplify
        return 0 ;
    }
       
    //given a string representing a function  and an input value, returns an output value
    public static double fValue(String fString, double x) throws DomainViolationException, FunctionFormatException {
         
         double r;
         int errorCode = 0;
         if (fString.equals("")) return 0; 
         fString = fString.toUpperCase(); //all caps
         fString = removeString(" ",fString); //remove blanks
         fString = addMultSigns(fString); //add * signs where needed. ex: 2(3+5 -> 2*(3+5)
         fString = replaceMinusX(fString);
         fString = replaceMinusParen(fString);
         fString = replaceX(fString,x);  //replace x with its value
         fString = replacePI(fString); //replace pi with its approximation 
         errorMsg(checkErrors(fString)); //check for non-matching parentheses and other typo errors
         
        /* //errorMsg(errorCode);
         if (errorCode != 0) {
             return Double.NaN; } //possibly do something, but more likely implement a try-catch later*/
         
         try{ r=Double.parseDouble(fString); } //if we can't parse what we have to a number, 
         catch (NumberFormatException e){r = eval(fString); } //call eval to simplify 
         return r;
    }

}

