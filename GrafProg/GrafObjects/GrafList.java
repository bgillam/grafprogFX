package GrafProg.GrafObjects;

import java.util.ArrayList;

//Need to make this singleton pattern

public class GrafList {

    private static final GrafList instance = new GrafList();

    private static ArrayList<GrafObject> grafObjectList;
    private static GrafAxes axes;
    private static String copiedText;

    private GrafList(){
        this.grafObjectList = new ArrayList<GrafObject>();
        this.axes = new GrafAxes();   //axes object
        this.copiedText = "";
    }

    public static GrafList getInstance(){
        return instance;
    }


    public static void add(GrafObject g){
        grafObjectList.add(g);
    }

    static void add(int i, GrafObject g){
        grafObjectList.add(i,g);
    }

    static void remove(int i){
        grafObjectList.remove(i);
    }


    public static int size(){
        return grafObjectList.size();
    }

    public static GrafAxes getAxes(){return axes;}
    public static void setAxes(GrafAxes ga){axes = ga;}

    public static void setGrafList(ArrayList<GrafObject> al){grafObjectList = al;}
    public static ArrayList<GrafObject> getGrafList(){return grafObjectList;}

//    public boolean isEmpty(){
//        if (grafObjectList.size()==1) return true; else return false;
//    }

}
