//Orignal class FunctionString was large, so it has been broken into FunctionString, FunctionStringSubstring, FunctionShring Replacers, FunctionStringOperators and FunctionStringErrorChecks.
class FunctionStringSubstring {
    // checks from counter-1 to left and returns the left boundry of a numeric substring
    //used to find the number on the left of an operator located at counter
    private static int getBeginLeftNumPos(int counter, String fString){
        counter--;
        do {
            if (counter == 0) return counter;
            if (GrafInputHelpers.isANumberChar(fString, counter)) {counter --; continue;}
            if (fString.charAt(counter) == '.') {counter--; continue;}
            if (fString.charAt(counter) == 'E') {counter--; continue; }
            if (fString.charAt(counter-1) == '(') return counter;
            if (FunctionStringErrorChecks.isaNonMinusOperator(fString.charAt(counter))) return counter+1;
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
        //if (counter > fString.length()-1) return fString.length()-1;
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
    static String getPreString(int counter, String fString){
        int beginLeftNumPos = getBeginLeftNumPos(counter, fString);
        if (beginLeftNumPos == 0) return "";
        else try{
            Double.parseDouble(fString.substring(beginLeftNumPos-1, beginLeftNumPos));
            return fString.substring(0, beginLeftNumPos)+"+";
        }
        catch (NumberFormatException e){
            return fString.substring(0, beginLeftNumPos);
        }
    }

    //returns// return the part of the string that comes after the substring of interest
    static String getPostString(int counter, String fString){
        if (getEndRightNumPos(counter, fString) == fString.length()-1) return ""; else return fString.substring(getEndRightNumPos(counter,fString)+1);
    }

    //Gets the number string to the left of position count.
    //Used to get number to left of an operator
    static double getLeftNum(int counter, String fString){
        String leftSubstr;
        try{
            leftSubstr = fString.substring(getBeginLeftNumPos(counter,fString),counter);
            return Double.parseDouble(leftSubstr);
        } catch (NumberFormatException e){
            return 0;
        }
    }

    //gets the number  string to the right of position count.
    //Used to get number to right of an operator
    static double getRightNum(int counter, String fString){
        int end = getEndRightNumPos(counter, fString)+1;
        return Double.parseDouble(fString.substring(counter+1, end));
    }

}
