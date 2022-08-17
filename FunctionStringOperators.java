import GrafUtils.GrafInputHelpers;

//Orignal class FunctionString was large, so it has been broken into FunctionString, FunctionStringSubstring, FunctionShring Replacers, FunctionStringOperators and FunctionStringErrorChecks.
class FunctionStringOperators {

    //parses string from left to right performing additions and subtractions
    static String addAndSub(String middle){
        //System.out.println("in addAndSub: " +middle);
        int counter = 1;
        do{
            try{
                Double.parseDouble(middle);
                return middle;
            }
            catch (NumberFormatException e){//System.out.println(e.toString()+"in addandsub");
            }
            if (middle.charAt(counter) == '+') {
                middle = FunctionStringSubstring.getPreString(counter, middle)+  (FunctionStringSubstring.getLeftNum(counter,middle)+FunctionStringSubstring.getRightNum(counter,middle)) +FunctionStringSubstring.getPostString(counter, middle);
                counter = 1;
            }
            else if ((middle.charAt(counter) == '-') && (!(middle.charAt(counter-1) == 'E'))){
                middle = FunctionStringSubstring.getPreString(counter, middle)+(FunctionStringSubstring.getLeftNum(counter,middle)-FunctionStringSubstring.getRightNum(counter,middle))+FunctionStringSubstring.getPostString(counter, middle);
                counter = 1;
            }

            counter++;
        } while (counter < middle.length());
        return middle;
    }

    //parses string from left to right performing multiplications and divisions
    static String multAndDiv(String middle){
        int counter = 0;
        do {
            try{
                Double.parseDouble(middle);
                return middle;
            }
            catch (NumberFormatException e){//System.out.println(e.toString()+"in multanddiv");
            }

            if (middle.charAt(counter) == '*') {
                middle = FunctionStringSubstring.getPreString(counter, middle)+(FunctionStringSubstring.getLeftNum(counter,middle)*FunctionStringSubstring.getRightNum(counter,middle))+FunctionStringSubstring.getPostString(counter, middle);
                counter = 0;
            }
            else if (middle.charAt(counter) == '/'){
                //if (getRightNum(counter, middle) != 0){
                middle = FunctionStringSubstring.getPreString(counter, middle)+(FunctionStringSubstring.getLeftNum(counter,middle)/FunctionStringSubstring.getRightNum(counter,middle))+FunctionStringSubstring.getPostString(counter, middle);
                counter = 0;
                //}else {} //some sort of error handling either throw or dialog
            }
            counter++;
            //System.out.println("pass "+counter+": "+middle);
        }while (counter < middle.length());
        return middle;  //addAndSub(middle);
    }

    //replaces exponent expressions with their double value.
    static String doExponents(String fString){
        double leftNumber, rightNumber;
        int pos;
        do{
            pos = fString.indexOf('^');
            if (pos == -1) return fString;  //multAndDiv(fString);
            leftNumber = FunctionStringSubstring.getLeftNum(pos,fString);
            rightNumber = FunctionStringSubstring.getRightNum(pos,fString);
           /* System.out.println("left: "+leftNumber);
            System.out.println("right: "+rightNumber);*/
            if (leftNumber == 0) return "0.0";
            if ((leftNumber < 0) && ((int)(rightNumber) != rightNumber)){return "imaginary number";}
            else  fString = FunctionStringSubstring.getPreString(pos,fString)+Math.pow(leftNumber, rightNumber)+FunctionStringSubstring.getPostString(pos,fString);
        }while (true);

    }

    //evaluates each occurence of a passed rightfunction and replace them with a equivalent double. Uses recursion if necessary
    private static String evalRightFunction(String token, String fString){  //} throws DomainViolationException {

        int tokenStartPos;
        int tokenEndPos;
        int lpCounter;
        int rpCounter;
        int lastRightIndex = -1;
        double r;
        int i;
        do{
            try{ return ""+Double.parseDouble(fString); } catch (NumberFormatException e){ //
            } //return if a single number
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
            catch (NumberFormatException e){ r = FunctionString.eval(fString.substring(tokenEndPos+2, lastRightIndex));}
            //boolean domainError = false;
            // System.out.println(("token " + token));
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
                case "LN": {if (r > 0) r = Math.log(r); else {return "domainError";}    break;}
                case "LOG": {if (r>0) r = Math.log10(r); else {return "domainError";}break;}
                case "ROUND": {r = Math.round(r); break;}
                case "SIN": {r = Math.sin(r);break;}
                case "SEC":{ if (Math.cos(r) != 0) r = 1/Math.cos(r); else {return "domainError";}break;}
                case "SQRT":{ if (r>0) r = Math.sqrt(r); else {return "domainError";}
                    break;}
                case "TAN":{if (Math.cos(r) != 0) r = Math.sin(r)/Math.cos(r); else {return "domainError";} break; }

            }
            //if (domainError) { throw new DomainViolationException(); }
            if (lastRightIndex == fString.length()) lastRightIndex--;
            if (tokenStartPos == 0) fString = r + fString.substring(lastRightIndex+1);
            else fString = fString.substring(0,tokenStartPos) + r + fString.substring(lastRightIndex+1);

        }while (true);
        //return fString;
    }

    //  calls sub to evaluate occurences of each right function
    static String rightFunctions(String fString){//} throws DomainViolationException{
        boolean alphaFound = false;
        for (int i=0; i < fString.length(); i++){
            //int keyCode = KeyEvent.getExtendedKeyCodeForChar(fString.charAt(i));
            if (GrafInputHelpers.isAnAlphaChar(fString,i)) alphaFound = true;
        }
        if (!alphaFound) return fString;

        fString = evalRightFunction("ABS",fString);
        fString = evalRightFunction("ARCSIN",fString);
        fString = evalRightFunction("ARCTAN",fString);
        fString = evalRightFunction("ARCCOS",fString);
        fString = evalRightFunction("COS",fString);
        fString = evalRightFunction("COT",fString);
        fString = evalRightFunction("CSC",fString);
        fString = evalRightFunction("DTOR",fString);
        fString = evalRightFunction("EXP",fString);
        fString = evalRightFunction("INT",fString);
        fString = evalRightFunction("LN",fString);
        fString = evalRightFunction("LOG",fString);
        fString = evalRightFunction("PI",fString);
        fString = evalRightFunction("RTOD",fString);
        fString = evalRightFunction("ROUND",fString);
        fString = evalRightFunction("SEC",fString);
        fString = evalRightFunction("SIN",fString);
        fString = evalRightFunction("SQRT",fString);
        fString = evalRightFunction("TAN",fString);

        return fString;

    }


}
