/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goerie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 

public class CSVfileWriter {

     
    public static void main(String[] args) throws IOException {
         generateCsvFile();
         run();
         
    }
    public static void run() {
 
        String csvFile = "C:\\Users\\Bhavik Moradiya\\Desktop\\Book2.csv";
        String[][] arrayStrings=new String[4][6];
         
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
          
 
        try {
 
            
 
            br = new BufferedReader(new FileReader(csvFile));
          
           
            boolean firstTime=false;
            int i=0;
            while ((line = br.readLine()) != null) {
                 firstTime=false;
                  
                arrayStrings[i] = line.split(cvsSplitBy);
                 
                i++;
                if(i==4)
                {
                    i=0;
                    generateCsvFile(arrayStrings);
                }
            
            }
 
 
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
 
          
    }
     
     
    private static void generateCsvFile()
   {
    try
    {
        FileWriter writer = new FileWriter("C:\\Users\\Bhavik Moradiya\\Desktop\\Book2.csv",false);
  
            writer.append(" ");
        writer.append(',');
             
        writer.append("Estimate");
        writer.append(',');
        writer.append("StdError");
                    writer.append(',');
        writer.append("zValue");
                    writer.append(',');
        writer.append("Prz");
            writer.append('\n');
             
             
        //generate whatever data you want
  
        writer.flush();
        writer.close();
    }
    catch(IOException e)
    {
         e.printStackTrace();
    }
    }
     
     
     private static void generateCsvFile(String[][] values)
   {
    try
    {
        FileWriter writer = new FileWriter("C:\\Users\\Bhavik Moradiya\\Desktop\\Book2.csv",true);
  
              
        writer.append("Intersect");
        writer.append(',');
             
            writer.append(values[0][1]);
        writer.append(',');
            writer.append(values[1][1]);
        writer.append(',');
            writer.append(values[2][1]);
        writer.append(',');
            writer.append(values[3][1]);
        writer.append(',');
            writer.append(values[1][5]);
        
             
            writer.append('\n');
             
        writer.append("NewFeatures");
            writer.append(',');
                     
               writer.append(values[0][2]);
        writer.append(',');
            writer.append(values[1][2]);
        writer.append(',');
            writer.append(values[2][2]);
        writer.append(',');
            writer.append(values[3][2]);
        writer.append(',');
            writer.append(values[1][5]);
        
                        
                     
                     
            writer.append('\n');       
                     
        writer.append("Improvements");
            writer.append(',');
                writer.append(values[0][3]);
        writer.append(',');
            writer.append(values[1][3]);
        writer.append(',');
            writer.append(values[2][3]);
        writer.append(',');
            writer.append(values[3][3]);
        writer.append(',');
            writer.append(values[1][5]);
        
             
            writer.append('\n');
             
        writer.append("SqrCommits");
            writer.append(',');
            writer.append(values[0][4]);
        writer.append(',');
            writer.append(values[1][4]);
        writer.append(',');
            writer.append(values[2][4]);
        writer.append(',');
            writer.append(values[3][4]);
        writer.append(',');
            writer.append(values[1][5]);
                   
        writer.append('\n');
           
        //generate whatever data you want
  
        writer.flush();
        writer.close();
    }
    catch(IOException e)
    {
         e.printStackTrace();
    }
     
   }
}

