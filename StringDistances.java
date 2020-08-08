/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmproj;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author riadmarzouki
 */
public class StringDistances {
    public static List<String> stopwords;
    public StringDistances() throws IOException{
        loadstopwords();
    }
    public void loadstopwords() throws IOException{
        //ArrayList<String> stopwords = new ArrayList<String>();
        stopwords = Files.readAllLines(Paths.get("english_stopwords.txt"));
    }
    /**
     * @param args the command line arguments
     */
    private String normalize(String val){
        val = val.toLowerCase();
        val = val.replaceAll("\\n", "");
        val = val.replaceAll("[0-9]+[a-z]+\\s", "");
        val = val.replaceAll("[^a-zA-Z0-9 #]", "");
        val = val.replaceAll("", "");
        return val;
    }
    public Double calculateJaccardSimilarity(String left, String right) {
        Set<String> intersectionSet = new HashSet<String>();
        Set<String> unionSet = new HashSet<String>();
        boolean unionFilled = false;
        String[] splittedleft = normalize(left).split(" ");
        String[] splittedright = normalize(right).split(" ");
        int leftLength = splittedleft.length;
        int rightLength = splittedright.length;
        if (leftLength == 0 || rightLength == 0) {
            return 0d;
        }

        for (int leftIndex = 0; leftIndex < leftLength; leftIndex++) {
            unionSet.add(splittedleft[leftIndex]);
            for (int rightIndex = 0; rightIndex < rightLength; rightIndex++) {
                if (!unionFilled) {
                    unionSet.add(splittedright[rightIndex]);
                }
                if (splittedleft[leftIndex].equals(splittedright[rightIndex])) {
                    intersectionSet.add(splittedleft[leftIndex]);
                }
            }
            unionFilled = true;
        }
        return Double.valueOf(intersectionSet.size()) / Double.valueOf(unionSet.size());
    }
    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }
    public static int min(int... numbers) {
        return Arrays.stream(numbers)
          .min().orElse(Integer.MAX_VALUE);
    }
    static int calculatelv(String x, String y) {
    int[][] dp = new int[x.length() + 1][y.length() + 1];
 
    for (int i = 0; i <= x.length(); i++) {
        for (int j = 0; j <= y.length(); j++) {
            if (i == 0) {
                dp[i][j] = j;
            }
            else if (j == 0) {
                dp[i][j] = i;
            }
            else {
                dp[i][j] = min(dp[i - 1][j - 1] 
                 + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)), 
                  dp[i - 1][j] + 1, 
                  dp[i][j - 1] + 1);
            }
        }
    }
 
    return dp[x.length()][y.length()];
}


    
}
