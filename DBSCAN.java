/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmproj;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author riadmarzouki
 */
class Cluster{
    ArrayList<String[]> points;
    int id;
    public Cluster(){
        points = new ArrayList<String[]>();
    }
    public void add(String[] point){
        points.add(point);
    }
}
public class DBSCAN {
    Data dt;
    ArrayList<Cluster> clusters = new ArrayList<Cluster>();
    ArrayList<String[]> instances;
    ArrayList<String[]> visited = new ArrayList<String[]>();
    ArrayList<String[]> noise = new ArrayList<String[]>();
    String distancefunc;
    public ArrayList<String[]> getneighbors(Data dt, double r, String[] point, String distance_function) throws IOException{
        String[] instance = point;
        ArrayList<String[]> neighbors = new ArrayList<String[]>();
        for(int i=0;i<dt.instances.size();i++){
            String[] x = dt.instances.get(i);
            if(dt.distance(instance, x, distance_function)<r){neighbors.add(x);}
        }
        return neighbors;
    }
    public void expandcluster(int index, ArrayList<String[]> neighbors, Cluster c, double r, double min, String distance_function ) throws IOException{
        String[] point = instances.get(index);
        System.out.println("EXPANDING CLUSTER "+index);
        c.add(point);
        for(int i=0;i<neighbors.size();i++){
            point = neighbors.get(i);
            if(!visited.contains(point)){
                visited.add(point);
                ArrayList<String[]> neighbors1 = getneighbors(dt, r, point, distance_function);
                if(neighbors1.size()>min){neighbors.addAll(neighbors1);}
            }
            boolean belongs = false;
            for(Cluster cl:clusters){
                if(cl.points.contains(point)){
                    belongs=true;
                }
            }
            if(belongs==false){
                c.add(point);
            }
        }
    }
    public void classify(Data dt, double r, double min, String distance_function, boolean debug) throws IOException{
        this.instances = dt.instances;
        this.dt = dt;
        System.out.println("*--*-----------------------------------*--*");
        System.out.println("*--* CLASSIFYING WITH DBSCAN: "+instances.size()+" INSTANCES *--*");
        System.out.println("*--* PARAMS: \n\t+EPS:"+r+" +MINPTS: "+min+" DISTANCE FUNCTION: "+distance_function+" *--*");
        System.out.println("*--*-----------------------------------*--*");
        for(int i=0;i<instances.size();i++){
            String[] point = instances.get(i);
            if(visited.contains(point)){
                if(debug){System.out.println("point already visited :D");}
                continue;}
            visited.add(point);
            ArrayList<String[]> neighbors = getneighbors(dt,r,point, distance_function);
            if(neighbors.size()<min){
                if(debug){System.out.println("found a noise ");}
                noise.add(point);}
            else{
                Cluster c = new Cluster();
                clusters.add(c);
                expandcluster(i, neighbors, c, r, min, distance_function);
            }
        }
    }
}
