/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmproj;

import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author riadmarzouki
 */
public class Exemples {
    public static void main(String[] args) throws IOException, Exception{
        String distance_func = "levenstein";
        Data dt = new Data("./weather.csv");
        dt.distances_insight(distance_func);
        DBSCAN db = new DBSCAN();
        boolean debug = true;
        db.classify(dt, 9, 3, distance_func, debug);
        System.out.println("**----*** SUMMARY ***------**");
        System.out.println(">> FOUND "+db.clusters.size()+" clusters");
        for(int i=0;i<db.clusters.size();i++){
            System.out.println(">>> Cluster id "+i+" has "+db.clusters.get(i).points.size()+" points");
        }
        System.out.println("FOUND "+db.noise.size()+" noise points");
        System.out.println("**----*** SUMMARY ***------**");
        for(int i=0;i<db.clusters.size();i++){
            System.out.print("printing elements of cluster: "+i+" ("+db.clusters.get(i).points.size()+" elements)");
            for(int j=0;j<db.clusters.get(i).points.size();j++){
                System.out.println(Arrays.toString(db.clusters.get(i).points.get(j)));
            }
        }
        System.out.println("PRINTING NOISE: "+db.noise.size()+" elements)");
        for(int i=0;i<db.noise.size();i++){
            System.out.println(Arrays.toString(db.noise.get(i)));
            
        }
    }
}
