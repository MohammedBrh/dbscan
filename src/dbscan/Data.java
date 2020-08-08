/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbscan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author MohammedBenrabeh
 */
public class Data {
    ArrayList<String[]> instances;
    public Data(String path) throws IOException{
        loadcsv(path);
    }
    public void loadcsv(String path) throws FileNotFoundException, IOException{
        instances = new ArrayList<String[]>();
        BufferedReader bf = new BufferedReader(new FileReader(path));
        String line;
        while((line=bf.readLine())!=null){
            instances.add(line.split(","));
        }
    }
    
    public double numeric(String[] inst1, String[] inst2){
        double result=0;
        double dis;
        for(int i=0;i<inst1.length;i++){
           try{
            dis = Double.parseDouble(inst1[i])-Double.parseDouble(inst2[i]);
            dis = dis*dis;
            result+=dis;
           }catch(Exception e){}
        }
        result = Math.sqrt(result);
        return result;
    }
    public double jaccard(String[] inst1, String[] inst2) throws IOException{
        StringDistances oo = new StringDistances();
        double result=0;
        for(int i=0;i<inst1.length;i++){
            result+=oo.calculateJaccardSimilarity(inst1[i],inst2[i]);
        }
        return result;
    }
    public double levenstein(String[] inst1, String[] inst2) throws IOException{
        StringDistances oo = new StringDistances();
        double result=0;
        for(int i=0;i<inst1.length;i++){
            result+=StringDistances.calculatelv(inst1[i],inst2[i]);
        }
        return result;
    }
    public double distance(String[] inst1, String[] inst2, String distance_function) throws IOException{
        double result=0;
        if(distance_function.equals("numeric_distance")){
            result = numeric(inst1,inst2);
        }
        else if(distance_function.equals("jaccard")){
            result = jaccard(inst1, inst2);
        }
        else if(distance_function.equals("levenstein")){
            result = levenstein(inst1, inst2);
        }
        return result;
    }
     public void distances_insight(String distance_function) throws IOException{
        System.out.println("RUNNING INSIGHTS");
        double max=0, min=1000, average=0, d;
        for(int i=0;i<instances.size();i++){
            for(int j=0;j<instances.size();j++){
                
                if(i==j){continue;}
                d = distance(instances.get(i),instances.get(j), distance_function);
                System.out.println(i+"  "+j+"\t d="+d);
                if(d>max){max=d;}
                if(d<min){min=d;}
                average+=d;
            }
        }
        average=average/instances.size();
        average=average/instances.size();
        System.out.println("Number of instances = "+instances.size());
        System.out.println("Max distance: "+max);
        System.out.println("Min distance: "+min);
        System.out.println("Average distance: "+average);
    }
    

}
